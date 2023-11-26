package com.stock.analysis.dto;


import com.stock.analysis.domain.contant.UploadType;
import com.stock.analysis.domain.entity.Upload;

import java.time.LocalDateTime;

public record UploadDto(
        Long id,
        String name,
        String contentType,
        String path,
        UploadType uploadType,
        String createdBy,
        LocalDateTime createdAt,
        String modifiedBy,
        LocalDateTime modifiedAt
) {
    public static UploadDto of(Long id,
                               String name,
                               String path,
                               String contentType,
                               UploadType type,
                               String createdBy,
                               LocalDateTime createdAt,
                               String modifiedBy,
                               LocalDateTime modifiedAt) {
        return new UploadDto(id, name, path, contentType, type, createdBy, createdAt, modifiedBy, modifiedAt);
    }

    // 선택시 entity -> dto
    // from
    public static UploadDto from(Upload entity) {
        return new UploadDto(
                entity.getId(),
                entity.getName(),
                entity.getPath(),
                entity.getContentType(),
                entity.getUploadType(),
                entity. getCreatedBy(),
                entity.getCreatedAt(),
                entity.getModifiedBy(),
                entity.getModifiedAt()
        );
    }

    // dto -> entity
    // repository 에서
    // toEntity
    public Upload toEntity() {
        return Upload.of(
                name,
                contentType,
                path,
                uploadType
        );
    }
}
