package com.stock.analysis.domain.entity.upload;

import com.stock.analysis.domain.entity.Apply;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Getter
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("APPLY")
@Entity
@Where(clause = "upload_type = 'APPLY'")
public class ApplyUpload extends Upload {

    @Setter
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Apply apply;

    @Builder
    public ApplyUpload(String name, String storedName, String contentType, String path, Apply apply) {
        super(name, storedName, contentType, path);
        this.apply = apply;
    }
}
