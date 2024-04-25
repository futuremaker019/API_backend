package com.stock.analysis.application.contentFile.service;

import com.stock.analysis.domain.contant.UploadType;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ContentFileService {

    void saveContentFiles(List<MultipartFile> attachments, Long joinKey, UploadType uploadType);

    void saveContentFile(MultipartFile file, Long joinKey, UploadType uploadType);

    Resource downloadFile(Long id);

}