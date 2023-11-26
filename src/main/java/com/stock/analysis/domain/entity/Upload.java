package com.stock.analysis.domain.entity;

import com.stock.analysis.domain.AuditingFields;
import com.stock.analysis.domain.contant.UploadType;
import lombok.*;

import javax.persistence.*;

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
    private String contentType;

    @Column(length = 100)
    private String path;

    @Enumerated(EnumType.STRING)
    private UploadType uploadType;

    @Setter
    @ManyToOne(optional = false)
    private Article article;

    private Upload(String name, String contentType, String path, UploadType uploadType) {
        this.name = name;
        this.contentType = contentType;
        this.path = path;
        this.uploadType = uploadType;
    }

    public static Upload of(String name, String contentType, String path, UploadType uploadType) {
        return new Upload(name, contentType, path, uploadType);
    }
}
