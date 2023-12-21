package com.stock.analysis.service;

import com.stock.analysis.domain.entity.upload.ArticleUpload;
import com.stock.analysis.dto.upload.UploadDto;
import com.stock.analysis.repository.ArticleUploadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
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

    public String getFullPath(String fileName) {
        return rootPath + fileName;
    }

    public void saveUploads(UploadDto uploadDto, List<MultipartFile> files) {
        String filePath = DateTimeFormatter.ofPattern("YYYY/MM/dd").format(LocalDate.now());
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
                e.printStackTrace();
            }

            ArticleUpload articleUpload = uploadDto.toEntity(
                    originalFilename,
                    storedFileName,
                    file.getContentType(),
                    String.format("%s/%s", filePath, storedFileName)
            );

            articleUploadRepository.save(articleUpload);
        });
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

    public void getFile(Long id) {

    }
}
