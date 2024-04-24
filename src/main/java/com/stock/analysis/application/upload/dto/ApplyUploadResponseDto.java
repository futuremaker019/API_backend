package com.stock.analysis.application.upload.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
public class ApplyUploadResponseDto {

    private Long id;
    private String name;
    private String storedName;
    private String path;
    private String contentType;

    @QueryProjection
    public ApplyUploadResponseDto(Long id, String name, String storedName, String path, String contentType) {
        this.id = id;
        this.name = name;
        this.storedName = storedName;
        this.path = path;
        this.contentType = contentType;
    }
}
