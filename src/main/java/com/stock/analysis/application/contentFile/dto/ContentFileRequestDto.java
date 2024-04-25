package com.stock.analysis.application.contentFile.dto;

import com.stock.analysis.domain.contant.UploadType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContentFileRequestDto {

    private Long contentId;
    private UploadType uploadType;

}
