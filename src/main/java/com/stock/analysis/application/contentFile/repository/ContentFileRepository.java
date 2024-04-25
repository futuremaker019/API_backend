package com.stock.analysis.application.contentFile.repository;

import com.stock.analysis.domain.entity.ContentFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentFileRepository extends JpaRepository<ContentFile, Long> {
}
