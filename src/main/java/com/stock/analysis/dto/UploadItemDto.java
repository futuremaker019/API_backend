package com.stock.analysis.dto;

import com.stock.analysis.domain.entity.Article;
import com.stock.analysis.domain.entity.ArticleUpload;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UploadItemDto {

    private Long id;
    private String name;
    private String storedName;
    private String path;
    private String contentType;
    private String createdBy;
    private String createdAt;
    private String modifiedBy;
    private String modifiedAt;

    private Article article;

    @Builder
    public UploadItemDto(Long id, String name, String storedName, String path, String contentType, String createdBy, String createdAt, String modifiedBy, String modifiedAt, Article article) {
        this.id = id;
        this.name = name;
        this.storedName = storedName;
        this.path = path;
        this.contentType = contentType;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.modifiedBy = modifiedBy;
        this.modifiedAt = modifiedAt;
        this.article = article;
    }

    public ArticleUpload toArticleUpload(
            String name,
            String storedName,
            String path,
            String contentType
    ) {
        return ArticleUpload.of(
                name,
                storedName,
                path,
                contentType,
                this.article
        );
    }

    public static UploadItemDto from(ArticleUpload articleUpload) {
        return new UploadItemDto();
    }
}
