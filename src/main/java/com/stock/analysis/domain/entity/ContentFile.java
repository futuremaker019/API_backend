package com.stock.analysis.domain.entity;

import com.stock.analysis.domain.contant.UploadType;
import lombok.*;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(indexes = @Index(name = "joinkey_upload_type_idx", columnList = "joinkey, uploadType"))
public class ContentFile {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter @Column(length = 100)
    private String name;

    @Setter @Column(length = 100)
    private String storedName;

    @Setter @Column(length = 100)
    private String contentType;

    @Setter @Column(length = 100)
    private String path;

    @Setter @Comment("조인키")
    private Long joinKey;

    @Setter
    @Column(columnDefinition = "varchar(20) NOT NULL COMMENT '컨텐츠 종류'")
    @Enumerated(value = EnumType.STRING)
    private UploadType uploadType;

}
