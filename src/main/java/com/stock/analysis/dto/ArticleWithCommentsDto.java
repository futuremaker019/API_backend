package com.stock.analysis.dto;


import com.stock.analysis.domain.entity.Article;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public record ArticleWithCommentsDto(
        Long id,
        Set<ArticleCommentDto> articleCommentDtos,
        Set<UploadDto> uploadDtos,
        String title,
        String content,
        UserAccountDto userAccountDto,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) {

    public static ArticleWithCommentsDto of(
            Long id,
            Set<ArticleCommentDto> articleCommentDtos,
            Set<UploadDto> uploadDtos,
            String title,
            String content,
            UserAccountDto accountDto,
            LocalDateTime createdAt,
            String createdBy,
            LocalDateTime modifiedAt,
            String modifiedBy
    ) {
        return new ArticleWithCommentsDto(id, articleCommentDtos, uploadDtos, title, content, accountDto, createdAt, createdBy, modifiedAt, modifiedBy);
    }

    public static ArticleWithCommentsDto from(Article article) {
        return new ArticleWithCommentsDto(
                article.getId(),
                article.getArticleComments().stream()
                        .map(ArticleCommentDto::from)
                        .collect(Collectors.toCollection(LinkedHashSet::new)),
                article.getArticleUploads().stream()
                        .map(UploadDto::from)
                        .collect(Collectors.toCollection(LinkedHashSet::new)),
                article.getTitle(),
                article.getContent(),
                UserAccountDto.from(article.getUserAccount()),
                article.getCreatedAt(),
                article.getCreatedBy(),
                article.getModifiedAt(),
                article.getModifiedBy()
        );
    }
}
