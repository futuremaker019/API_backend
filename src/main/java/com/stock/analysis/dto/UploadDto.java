package com.stock.analysis.dto;


import com.stock.analysis.domain.contant.UploadType;
import com.stock.analysis.domain.entity.Article;
import com.stock.analysis.domain.entity.Upload;
import com.stock.analysis.utils.Utils;

import java.time.LocalDateTime;

public record UploadDto(
        Long id,
        String name,
        String storeName,
        String contentType,
        String path,
        UploadType uploadType,
        String createdBy,
        String createdAt,
        String modifiedBy,
        String modifiedAt
) {
    public static UploadDto of(Long id,
                               String name,
                               String storeName,
                               String path,
                               String contentType,
                               UploadType type,
                               String createdBy,
                               LocalDateTime createdAt,
                               String modifiedBy,
                               LocalDateTime modifiedAt) {
        return new UploadDto(id, name, storeName, path, contentType, type, createdBy, Utils.ConvertDate(createdAt), modifiedBy, Utils.ConvertDate(modifiedAt));
    }

    // 선택시 entity -> dto
    // from
    public static UploadDto from(Upload entity) {
        return new UploadDto(
                entity.getId(),
                entity.getName(),
                entity.getStoreName(),
                entity.getPath(),
                entity.getContentType(),
                entity.getUploadType(),
                entity. getCreatedBy(),
                Utils.ConvertDate(entity.getCreatedAt()),
                entity.getModifiedBy(),
                Utils.ConvertDate(entity.getModifiedAt())
        );
    }

    // dto -> entity
    // repository 에서
    // toEntity
    public Upload toEntity(Article article) {
        return Upload.of(
                name,
                storeName,
                contentType,
                path,
                uploadType,
                article
        );
    }
}
