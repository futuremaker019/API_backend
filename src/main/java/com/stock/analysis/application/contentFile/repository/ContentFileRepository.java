package com.stock.analysis.application.contentFile.repository;

import com.stock.analysis.domain.contant.UploadType;
import com.stock.analysis.domain.entity.ContentFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContentFileRepository extends JpaRepository<ContentFile, Long> {

    List<ContentFile> findByContentIdAndUploadType(Long contentId, UploadType uploadType);

}
