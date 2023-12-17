package com.stock.analysis.service;

import com.stock.analysis.domain.contant.UploadType;
import com.stock.analysis.domain.entity.ArticleUpload;
import com.stock.analysis.dto.UploadItemDto;
import com.stock.analysis.repository.UploadItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
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

    private final UploadItemRepository uploadItemRepository;

    public String getFullPath(String fileName) {
        return rootPath + fileName;
    }

    public void saveUploads(UploadItemDto uploadItemDto, List<MultipartFile> files, UploadType uploadType) {
        String filePath = DateTimeFormatter.ofPattern("YYYY/MM/dd").format(LocalDate.now());
        File rootDir = new File(getFullPath(filePath));
        if (!rootDir.exists()) {
            try {
                rootDir.mkdirs();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /**
         *  TODO : upload는 article만 할게 아닌데 어떻게 여러 엔티티가 upload를 바라볼수 있게 만들까?
         */
        files.forEach(file -> {
            String originalFilename = file.getOriginalFilename();
            String storedFileName = createStoredFileName(originalFilename);

            try {
                file.transferTo(new File(rootDir, storedFileName));
            } catch (IOException e) {
                e.printStackTrace();
            }

            ArticleUpload articleUpload = uploadItemDto.toArticleUpload(
                    originalFilename,
                    storedFileName,
                    file.getContentType(),
                    String.format("%s/%s", filePath, storedFileName)
            );

            uploadItemRepository.save(articleUpload);
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
}
