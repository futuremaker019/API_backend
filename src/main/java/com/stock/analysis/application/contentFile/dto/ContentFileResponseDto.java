package com.stock.analysis.application.contentFile.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.stock.analysis.domain.SuperDto;
import com.stock.analysis.domain.contant.UploadType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContentFileResponseDto extends SuperDto {

    private Long id;
    private String name;
    private String storedName;
    private String type;
    private String path;
    private Long joinKey;
    private UploadType uploadType;

    @QueryProjection
    public ContentFileResponseDto(Long id, String name, String storedName, String contentType, String path) {
        this.id = id;
        this.name = name;
        this.storedName = storedName;
        this.type = contentType;
        this.path = path;
    }
}
