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
public class ContentFileResponseDto {

    private Long id;
    private String name;
    private String storedName;
    private String contentType;
    private String path;
    private Long joinKey;
    private UploadType uploadType;

}
