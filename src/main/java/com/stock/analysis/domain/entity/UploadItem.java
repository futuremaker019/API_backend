package com.stock.analysis.domain.entity;

import com.stock.analysis.domain.AuditingFields;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorColumn(name = "upload_type", discriminatorType = DiscriminatorType.STRING)
public abstract class UploadItem extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(length = 100)
    private String name;

    @Setter
    @Column(length = 100)
    private String storedName;

    @Setter
    @Column(length = 100)
    private String contentType;

    @Setter
    @Column(length = 100)
    private String path;

    public UploadItem(String name, String storedName, String contentType, String path) {
        this.name = name;
        this.storedName = storedName;
        this.contentType = contentType;
        this.path = path;

    }
}
