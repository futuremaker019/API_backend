package com.stock.analysis.repository;

import com.stock.analysis.domain.entity.upload.Upload;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UploadRepository extends JpaRepository<Upload, Long> {


}
