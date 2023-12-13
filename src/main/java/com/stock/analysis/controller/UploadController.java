package com.stock.analysis.controller;

import com.stock.analysis.domain.entity.Upload;
import com.stock.analysis.dto.UploadResult;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UploadController {

    public UploadResult upload(
            @RequestParam(value = "file") MultipartFile file
    ) {
        return UploadResult.builder()
                .code(100)
                .path("/files/" + file.getOriginalFilename())
                .build();
    }

}
