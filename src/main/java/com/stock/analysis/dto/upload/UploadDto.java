package com.stock.analysis.dto.upload;

import com.stock.analysis.domain.entity.upload.ArticleUpload;

public abstract class UploadDto {

    public ArticleUpload toEntity(
            String name,
            String storedName,
            String path,
            String contentType
    ) {
        return null;
    }
}
