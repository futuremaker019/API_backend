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

    public void saveUploads(Long articleId, List<MultipartFile> attachments) {

    }
}
