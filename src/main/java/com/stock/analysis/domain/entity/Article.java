package com.stock.analysis.domain.entity;

import com.stock.analysis.domain.AuditingFields;
import lombok.*;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@ToString(callSuper = true)
@Table(indexes = {
        @Index(columnList = "title"),
        @Index(columnList = "createdBy"),
        @Index(columnList = "createdAt"),
})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Article  extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(nullable = false)
    private String title;

    @Setter
    @Column(nullable = false, length = 10000)
    private String content;

    @Setter
    @ManyToOne(optional = false)
    @JoinColumn(name = "accountId")
    private UserAccount userAccount;

    @ToString.Exclude
    @OrderBy("createdAt DESC")
    @OneToMany(mappedBy = "article", fetch = FetchType.LAZY)
    private final Set<ArticleComment> articleComments = new LinkedHashSet<>();

    @ToString.Exclude
    @OrderBy("createdAt DESC")
    @OneToMany(mappedBy = "article", fetch = FetchType.LAZY)
    private final Set<Upload> articleUploads = new LinkedHashSet<>();

    public Article(String title, String content, UserAccount userAccount) {
        this.title = title;
        this.content = content;
        this.userAccount = userAccount;
    }

    public static Article of(String title, String content, UserAccount userAccount) {
        return new Article(title, content, userAccount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Article article)) return false;
        return id != null && id.equals(article.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
