package com.stock.analysis.domain.entity.upload;

import com.stock.analysis.domain.AuditingFields;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorColumn(name = "upload_type")
public abstract class Upload extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String name;

    @Column(length = 100)
    private String storedName;

    @Column(length = 100)
    private String contentType;

    @Column(length = 100)
    private String path;


    public Upload(String name, String storedName, String contentType, String path) {
        this.name = name;
        this.storedName = storedName;
        this.contentType = contentType;
        this.path = path;
    }
}
