package com.stock.analysis.domain.entity;

import com.stock.analysis.domain.AuditingFields;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Getter
@ToString(callSuper = true)
@Table(indexes = {
        @Index(columnList = "content"),
        @Index(columnList = "createdBy"),
        @Index(columnList = "createdAt"),
})
@NoArgsConstructor
@Entity
public class ArticleComment extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @ManyToOne(optional = false)
    private Article article;

    @Setter
    @ManyToOne(optional = false)
    private UserAccount userAccount;

    @Setter
    @Column(nullable = false, length = 500)
    private String content;

    private ArticleComment(Article article, String content, UserAccount userAccount) {
        this.article = article;
        this.content = content;
        this.userAccount = userAccount;
    }

    public static ArticleComment of(Article article, String content, UserAccount userAccount) {
        return new ArticleComment(article, content, userAccount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArticleComment articleComment)) return false;
        return id != null && id.equals(articleComment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
