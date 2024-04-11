package com.stock.analysis.application.upload.service;

import com.stock.analysis.application.upload.repository.ApplyUploadRepository;
import com.stock.analysis.application.upload.repository.ArticleUploadRepository;
import com.stock.analysis.domain.contant.UploadType;
import com.stock.analysis.domain.entity.upload.ApplyUpload;
import com.stock.analysis.domain.entity.upload.ArticleUpload;
import com.stock.analysis.application.upload.dto.UploadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class UploadService {

    @Value("${rootPath}")
    private String rootPath;

    private final ArticleUploadRepository articleUploadRepository;
    private final ApplyUploadRepository applyUploadRepository;

    public String getFullPath(String fileName) {
        return rootPath + fileName;
    }

    public void saveUploads(UploadDto uploadDto, List<MultipartFile> files, UploadType uploadType) {
        String filePath = DateTimeFormatter.ofPattern("yyyy/MM/dd").format(LocalDate.now());
        File rootDir = new File(getFullPath(filePath));
        if (!rootDir.exists()) {
            try {
                rootDir.mkdirs();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        files.forEach(file -> {
            String originalFilename = file.getOriginalFilename();
            String storedFileName = createStoredFileName(originalFilename);
            try {
                file.transferTo(new File(rootDir, storedFileName));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            saveUploadByUploadType(uploadDto, uploadType, file, originalFilename, storedFileName, filePath);
        });
    }

    private void saveUploadByUploadType(UploadDto uploadDto, UploadType uploadType, MultipartFile file, String originalFilename, String storedFileName, String filePath) {
        switch (uploadType) {
            case APPLY -> {
                ApplyUpload applyUpload = uploadDto.toApplyUpload(originalFilename, storedFileName, filePath, file.getContentType());
                applyUploadRepository.save(applyUpload);
            }
            case ARTICLE -> {
                ArticleUpload articleUpload = uploadDto.toArticleUpload(originalFilename, storedFileName, filePath, file.getContentType());
                articleUploadRepository.save(articleUpload);
            }
        }
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

    public Resource getFile(Long id) {
        ArticleUpload articleUpload = articleUploadRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("upload file not found, id: " + id));

        String fullRootPath = getFullPath(articleUpload.getPath());
        Path filePath = Paths.get(fullRootPath).resolve(Paths.get(articleUpload.getStoredName()));

        try {
            return new UrlResource(filePath.toUri());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
