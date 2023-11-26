package com.stock.analysis.dto.request;


import com.stock.analysis.dto.ArticleDto;
import com.stock.analysis.dto.UserAccountDto;

public record ArticleRequest(
        String title,
        String content
) {
    public static ArticleRequest of(String title, String content) {
        return new ArticleRequest(title, content);
    }

    public ArticleDto toDto(UserAccountDto accountDto) {
        return ArticleDto.of(
                title,
                content,
                accountDto
        );
    }
}
