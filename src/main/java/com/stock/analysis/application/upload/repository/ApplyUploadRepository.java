package com.stock.analysis.application.upload.repository;

import com.stock.analysis.domain.entity.upload.ApplyUpload;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplyUploadRepository extends JpaRepository<ApplyUpload, Long> {
}
