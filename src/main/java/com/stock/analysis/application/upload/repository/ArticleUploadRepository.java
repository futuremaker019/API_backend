package com.stock.analysis.application.upload.repository;

import com.stock.analysis.domain.entity.upload.ArticleUpload;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleUploadRepository extends JpaRepository<ArticleUpload, Long> {
}
