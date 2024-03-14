package com.stock.analysis.dto.upload;

import com.stock.analysis.domain.entity.upload.ApplyUpload;
import com.stock.analysis.domain.entity.upload.ArticleUpload;

public abstract class UploadDto {

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
