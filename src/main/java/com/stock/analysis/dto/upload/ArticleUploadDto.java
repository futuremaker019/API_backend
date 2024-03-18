package com.stock.analysis.dto.upload;

import com.stock.analysis.domain.entity.Article;
import com.stock.analysis.domain.entity.upload.ArticleUpload;
import com.stock.analysis.utils.Utils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ArticleUploadDto extends UploadDto {

    private Article article;

    @Builder
    public ArticleUploadDto(Long id, String name, String storedName, String path, String contentType, Article article) {
        super(id, name, storedName, path, contentType);
        this.article = article;
    }

    @Override
    public ArticleUpload toArticleUpload(String name, String storedName, String path, String contentType) {
        return ArticleUpload.builder()
                .article(this.article)
                .name(name)
                .storedName(storedName)
                .contentType(contentType)
                .path(path)
                .build();
    }

    public static ArticleUploadDto from(ArticleUpload articleUpload) {
        return ArticleUploadDto.builder()
                .id(articleUpload.getId())
                .name(articleUpload.getName())
                .storedName(articleUpload.getStoredName())
                .contentType(articleUpload.getContentType())
                .path(articleUpload.getPath())
                .build();
    }
}
