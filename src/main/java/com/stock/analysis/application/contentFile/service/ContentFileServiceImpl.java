package com.stock.analysis.application.contentFile.service;

import com.stock.analysis.application.contentFile.dto.ContentFileRequestDto;
import com.stock.analysis.application.contentFile.dto.ContentFileResponseDto;
import com.stock.analysis.application.contentFile.repository.ContentFileRepository;
import com.stock.analysis.domain.contant.UploadType;
import com.stock.analysis.domain.entity.ContentFile;
import com.stock.analysis.exception.ContentAppException;
import com.stock.analysis.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ContentFileServiceImpl implements ContentFileService{

    @Value("${rootPath}")
    private String rootPath;
    private final ContentFileRepository contentFileRepository;

    public String getFullPath(String fileName) {
        return rootPath + fileName;
    }

    @Override
    public void saveContentFiles(List<MultipartFile> files, Long contentId, UploadType uploadType) {
        if (files != null) {
            files.forEach(file -> saveContentFile(file, contentId, uploadType));
        }
    }

    @Override
    public void saveContentFile(MultipartFile file, Long contentId, UploadType uploadType) {
        String filePath = DateTimeFormatter.ofPattern("yyyy/MM/dd").format(LocalDate.now());
        File rootDir = new File(getFullPath(filePath));
        if (!rootDir.exists()) {
            try {
                rootDir.mkdirs();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        String originalFilename = file.getOriginalFilename();
        String storedFileName = createStoredFileName(originalFilename);
        try {
            file.transferTo(new File(rootDir, storedFileName));         // 파일 데이터 옮김
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ContentFile contentFile = ContentFile.builder()
                .contentId(contentId)
                .name(originalFilename)
                .storedName(storedFileName)
                .contentType(file.getContentType())
                .path(filePath)
                .uploadType(uploadType)
                .build();
        contentFileRepository.saveAndFlush(contentFile);
    }

    @Override
    public Resource downloadFile(Long id) {
        ContentFile contentFile = contentFileRepository.findById(id)
                .orElseThrow(() -> new ContentAppException(ErrorCode.CONTENT_NOT_FOUND, "file not found: id - %d".formatted(id)));
        String fullPath = getFullPath(contentFile.getPath());
        Path filePath = Paths.get(fullPath).resolve(Paths.get(contentFile.getStoredName()));

        try {
            return new UrlResource(filePath.toUri());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteContentFile(Long id) {
        Optional<ContentFile> savedContentFile = contentFileRepository.findById(id);
        if (savedContentFile.isEmpty()) {
            log.error("%s, file not found: %d".formatted(ErrorCode.CONTENT_NOT_FOUND, id));
        }
        savedContentFile.ifPresent(contentFile -> {
            contentFileRepository.deleteById(contentFile.getId());
            File file = new File(rootPath, "%s/%s".formatted(contentFile.getPath(), contentFile.getStoredName()));
            if (file.exists()) {
                if (file.delete()) {
                    log.info("file deleted: %s, %s".formatted(contentFile.getName(), contentFile.getStoredName()));
                }
            }
        });
    }

    @Override
    public List<ContentFileResponseDto> selectContentFilesByContentIdAndUploadType(ContentFileRequestDto requestDto) {
        return contentFileRepository.findByContentIdAndUploadType(requestDto.getContentId(), requestDto.getUploadType())
                .stream().map(ContentFileResponseDto::from).toList();
    }


    private String createStoredFileName(String originalFilename) {
        String ext = extractExt(originalFilename);
        String uuid = UUID.randomUUID().toString();
        return uuid + "." + ext;
    }

    private String extractExt(String originalFileName) {
        int delimiter = originalFileName.lastIndexOf(".");
        return originalFileName.substring(delimiter + 1);
    }
}
