package com.stock.analysis.application.upload.dto;

import com.stock.analysis.domain.entity.upload.ApplyUpload;
import com.stock.analysis.domain.entity.upload.ArticleUpload;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public abstract class UploadDto {

    private Long id;
    private String name;
    private String storedName;
    private String path;
    private String contentType;

    public UploadDto(Long id, String name, String storedName, String path, String contentType) {
        this.id = id;
        this.name = name;
        this.storedName = storedName;
        this.path = path;
        this.contentType = contentType;
    }

    public ArticleUpload toArticleUpload(
            String name,
            String storedName,
            String path,
            String contentType
    ) {
        return null;
    }

    public ApplyUpload toApplyUpload(
            String name,
            String storedName,
            String path,
            String contentType
    ) {
        return null;
    }
}
