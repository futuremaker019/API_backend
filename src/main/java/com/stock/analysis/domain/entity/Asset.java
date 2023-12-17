package com.stock.analysis.domain.entity;

import com.stock.analysis.domain.AuditingFields;
import lombok.*;

import javax.persistence.*;
import java.util.LinkedHashSet;
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
public class Asset extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(length = 100)
    private String title;

    @Setter
    @Column(length = 100)
    private String Content;

    @ToString.Exclude
    @OrderBy("createdAt DESC")
    @OneToMany(mappedBy = "asset", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private final Set<AssetUpload> assetUploads = new LinkedHashSet<>();
}
