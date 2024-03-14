package com.stock.analysis.dto.upload;

import com.stock.analysis.domain.entity.Apply;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplyUploadDto extends UploadDto{

    private Apply apply;

}
