package com.stock.analysis.domain.entity;

import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("ASSET")
@Entity
public class AssetUpload extends UploadItem {

    @Setter
    @ManyToOne(optional = false)
    @JoinColumn(name = "parent_id")
    private Asset asset;

}
