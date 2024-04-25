package com.stock.analysis.application.contentFile.service;

import com.stock.analysis.application.contentFile.repository.ContentFileRepository;
import com.stock.analysis.domain.contant.UploadType;
import com.stock.analysis.domain.entity.ContentFile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class ContentFileServiceImpl implements ContentFileService{

    @Value("${rootPath}")
    private String rootPath;
    private final String filePath;
    private final File rootDir;
    private final ContentFileRepository contentFileRepository;

    public ContentFileServiceImpl(@Autowired ContentFileRepository contentFileRepository) {
        this.contentFileRepository = contentFileRepository;
        filePath = DateTimeFormatter.ofPattern("yyyy/MM/dd").format(LocalDate.now());
        rootDir = new File(getFullPath(filePath));
    }

    public String getFullPath(String fileName) {
        return rootPath + fileName;
    }

    @Override
    public void saveContentFiles(List<MultipartFile> files, Long joinKey, UploadType uploadType) {
        if (files != null) {
            files.forEach(file -> saveContentFile(file, joinKey, uploadType));
        }
    }

    @Override
    @Transactional
    public void saveContentFile(MultipartFile file, Long joinKey, UploadType uploadType) {
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
                .joinKey(joinKey)
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
        return null;
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
