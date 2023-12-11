package com.stock.analysis.dto;


import com.stock.analysis.domain.entity.Article;
import com.stock.analysis.domain.entity.ArticleComment;
import com.stock.analysis.domain.entity.UserAccount;
import com.stock.analysis.utils.Utils;

import javax.swing.text.Utilities;
import java.time.LocalDateTime;

public record ArticleCommentDto(
        Long id,
        Long articleId,
        String content,
        UserAccountDto userAccountDto,
        String createdAt,
        String createdBy,
        String modifiedAt,
        String modifiedBy
) {
    public static ArticleCommentDto of(Long id, Long articleId, String content, UserAccountDto userAccountDto, LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
        return new ArticleCommentDto(id, articleId, content, userAccountDto, Utils.ConvertDate(createdAt), createdBy, Utils.ConvertDate(modifiedAt), modifiedBy);
    }

    // ArticleComment -> from -> ArticleCommentDto
    public static ArticleCommentDto from(ArticleComment entity) {
        return new ArticleCommentDto(
                entity.getId(),
                entity.getArticle().getId(),
                entity.getContent(),
                UserAccountDto.from(entity.getUserAccount()),
                Utils.ConvertDate(entity.getCreatedAt()),
                entity.getCreatedBy(),
                Utils.ConvertDate(entity.getModifiedAt()),
                entity.getModifiedBy()
        );
    }

    // Article -> toEntity -> ArticleComment
    public ArticleComment toEntity(Article article, UserAccount userAccount) {
        return ArticleComment.of(
                article,
                content,
                userAccount
        );
    }
}
