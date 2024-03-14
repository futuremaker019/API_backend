package com.stock.analysis.dto.upload;

import com.stock.analysis.domain.entity.Article;
import com.stock.analysis.domain.entity.upload.ArticleUpload;
import com.stock.analysis.utils.Utils;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ArticleUploadDto extends UploadDto {

    private Long id;
    private String name;
    private String storedName;
    private String path;
    private String contentType;

    private Article article;

    @Builder
    public ArticleUploadDto(Long id, String name, String storedName, String path, String contentType, Article article) {
        this.id = id;
        this.name = name;
        this.storedName = storedName;
        this.path = path;
        this.contentType = contentType;
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
