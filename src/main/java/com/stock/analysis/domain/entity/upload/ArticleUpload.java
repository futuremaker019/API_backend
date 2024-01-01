package com.stock.analysis.domain.entity.upload;

import com.stock.analysis.domain.entity.Article;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Getter
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("ARTICLE")
@Entity
@Where(clause = "upload_type = 'ARTICLE'")
public class ArticleUpload extends Upload {

    @Setter
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Article article;

    @Builder
    public ArticleUpload(String name, String storedName, String contentType, String path, Article article) {
        super(name, storedName, contentType, path);
        this.article = article;
    }
}
