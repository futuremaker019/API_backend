package com.stock.analysis.domain.entity;

import com.stock.analysis.domain.AuditingFields;
import com.stock.analysis.domain.contant.UploadType;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Upload extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String name;

    @Column(length = 100)
    private String storeName;

    @Column(length = 100)
    private String contentType;

    @Column(length = 100)
    private String path;

    @Enumerated(EnumType.STRING)
    private UploadType uploadType;

    @Setter
    @ManyToOne(optional = false)
    private Article article;

    @Setter
    @ManyToOne(optional = false)
    private Asset asset;

    private Upload(String name, String storeName, String contentType, String path, UploadType uploadType, Article article) {
        this.name = name;
        this.storeName = storeName;
        this.contentType = contentType;
        this.path = path;
        this.uploadType = uploadType;
        this.article = article;
    }

    public static Upload of(String name, String savedName, String contentType, String path, UploadType uploadType, Article article) {
        return new Upload(name, savedName, contentType, path, uploadType, article);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Upload upload)) return false;
        return Objects.equals(id, upload.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
