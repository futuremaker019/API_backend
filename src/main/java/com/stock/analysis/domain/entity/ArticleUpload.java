package com.stock.analysis.domain.entity;

import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("ARTICLE")
@Entity
public class ArticleUpload extends UploadItem{

    @Setter
    @ManyToOne(optional = false)
    @JoinColumn(name = "parent_id")
    private Article article;

    private ArticleUpload(String name, String storedName, String path, String contentType, Article article) {
        super(name, storedName, path, contentType);
        this.article = article;
    }

    public static ArticleUpload of(String name, String storedName, String path, String contentType, Article article) {
        return new ArticleUpload(name, storedName, path, contentType, article);
    }


}
