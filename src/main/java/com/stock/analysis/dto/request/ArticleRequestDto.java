package com.stock.analysis.dto.request;


import com.stock.analysis.dto.ArticleDto;
import com.stock.analysis.dto.UserAccountDto;

public record ArticleRequestDto(
        String title,
        String content
) {
    public static ArticleRequestDto of(String title, String content) {
        return new ArticleRequestDto(title, content);
    }

    public ArticleDto toDto(UserAccountDto accountDto) {
        return ArticleDto.of(
                title,
                content,
                accountDto
        );
    }
}
