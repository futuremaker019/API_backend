package com.stock.analysis.repository;

import com.stock.analysis.domain.entity.UploadItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UploadItemRepository extends JpaRepository<UploadItem, Long> {


}
