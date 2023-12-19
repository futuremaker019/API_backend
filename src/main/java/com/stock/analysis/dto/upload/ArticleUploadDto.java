package com.stock.analysis.dto.upload;

import com.stock.analysis.domain.entity.Article;
import com.stock.analysis.domain.entity.upload.ArticleUpload;
import com.stock.analysis.utils.Utils;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
//@Setter
@NoArgsConstructor
public class ArticleUploadDto extends UploadDto {

    private Long id;
    private String name;
    private String storedName;
    private String path;
    private String contentType;
    private String createdBy;
    private String createdAt;
    private String modifiedAt;
    private String modifiedBy;

    private Article article;

    @Builder
    public ArticleUploadDto(Long id, String name, String storedName, String path, String contentType, String createdBy, String createdAt, String modifiedAt, String modifiedBy, Article article) {
        this.id = id;
        this.name = name;
        this.storedName = storedName;
        this.path = path;
        this.contentType = contentType;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.modifiedBy = modifiedBy;
        this.article = article;
    }

    public ArticleUpload toEntity(String name, String storedName, String contentType, String path) {
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
                .createdAt(Utils.ConvertDate(articleUpload.getCreatedAt()))
                .createdBy(articleUpload.getCreatedBy())
                .modifiedAt(Utils.ConvertDate(articleUpload.getModifiedAt()))
                .modifiedBy(articleUpload.getModifiedBy())
                .build();
    }
}
