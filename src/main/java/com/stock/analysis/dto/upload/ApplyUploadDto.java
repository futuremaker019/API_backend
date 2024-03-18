package com.stock.analysis.dto.upload;

import com.stock.analysis.domain.entity.Apply;
import com.stock.analysis.domain.entity.upload.ApplyUpload;
import lombok.*;

@Getter
@NoArgsConstructor
public class ApplyUploadDto extends UploadDto{

    private Apply apply;

    @Builder
    public ApplyUploadDto(Long id, String name, String storedName, String path, String contentType, Apply apply) {
        super(id, name, storedName, path, contentType);
        this.apply = apply;
    }

    @Override
    public ApplyUpload toApplyUpload(String name, String storedName, String path, String contentType) {
        return ApplyUpload.builder()
                .apply(this.apply)
                .name(name)
                .storedName(storedName)
                .contentType(contentType)
                .path(path)
                .build();
    }
}
