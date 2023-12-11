package com.stock.analysis.dto;


import com.stock.analysis.domain.entity.Article;
import com.stock.analysis.utils.Utils;

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
        String createdAt,
        String createdBy,
        String modifiedAt,
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
        return new ArticleWithCommentsDto(id, articleCommentDtos, uploadDtos, title, content, accountDto, Utils.ConvertDate(createdAt), createdBy, Utils.ConvertDate(modifiedAt), modifiedBy);
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
                Utils.ConvertDate(article.getCreatedAt()),
                article.getCreatedBy(),
                Utils.ConvertDate(article.getModifiedAt()),
                article.getModifiedBy()
        );
    }
}
