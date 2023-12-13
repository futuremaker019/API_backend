package com.stock.analysis.service;

import com.stock.analysis.repository.UploadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UploadService {

    private final UploadRepository uploadRepository;

    public void saveUploads(Long articleId, List<MultipartFile> files) {
        files.forEach(file -> {
            /**
             * content-type, name, path, upload-type, article-id
             *
             * 이름을 id와 년/월/일 로 하는것도 괜찮을거 같다.
             */



            System.out.println("file.getOriginalFilename() = " + file.getOriginalFilename());
            System.out.println("file.getContentType() = " + file.getContentType());
        });
    }
}
