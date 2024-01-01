package com.stock.analysis.domain.entity.upload;

import com.stock.analysis.domain.entity.Asset;
import lombok.*;

import javax.persistence.*;

@Getter
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("ASSET")
@Entity
public class AssetUpload extends Upload{

    @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_id")
    private Asset asset;

    @Builder
    public AssetUpload(String name, String storedName, String contentType, String path, Asset asset) {
        super(name, storedName, contentType, path);
        this.asset = asset;
    }
}
