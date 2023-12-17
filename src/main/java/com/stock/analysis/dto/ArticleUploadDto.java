package com.stock.analysis.dto;

import com.stock.analysis.domain.entity.Article;
import com.stock.analysis.utils.Utils;

import java.time.LocalDateTime;

public record ArticleUploadDto(
        Long id,
        String name,
        String storedName,
        String contentType,
        String path,
        Article article,
        String createdBy,
        String createdAt,
        String modifiedBy,
        String modifiedAt
) {
    public static ArticleUploadDto of(Long id,
                                      String name,
                                      String storedName,
                                      String path,
                                      String contentType,
                                      Article article,
                                      String createdBy,
                                      LocalDateTime createdAt,
                                      String modifiedBy,
                                      LocalDateTime modifiedAt) {
        return new ArticleUploadDto(
                id,
                name,
                storedName,
                path,
                contentType,
                article,
                createdBy,
                Utils.ConvertDate(createdAt),
                modifiedBy,
                Utils.ConvertDate(modifiedAt)
        );
    }


}
