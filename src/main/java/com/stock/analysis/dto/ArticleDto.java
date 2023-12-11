package com.stock.analysis.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.stock.analysis.domain.entity.Article;
import com.stock.analysis.domain.entity.UserAccount;
import com.stock.analysis.utils.Utils;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link Article}
 */
public record ArticleDto(
        Long id,
        String title,
        String content,
        UserAccountDto accountDto,
        String createdAt,
        String createdBy,
        String modifiedAt,
        String modifiedBy
) implements Serializable {

    @QueryProjection
    public ArticleDto {
    }

    public static ArticleDto of(String title, String content, UserAccountDto userAccountDto) {
        return new ArticleDto(null, title, content,userAccountDto, null, null, null, null);
    }

    public static ArticleDto of(Long id, String title, String content, UserAccountDto userAccountDto, LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
        return new ArticleDto(id, title, content, userAccountDto, Utils.ConvertDate(createdAt), createdBy, Utils.ConvertDate(modifiedAt), modifiedBy);
    }

    // entity -> dto (from)
    public static ArticleDto from(Article entity) {
        return new ArticleDto(
                entity.getId(),
                entity.getTitle(),
                entity.getContent(),
                UserAccountDto.from(entity.getUserAccount()),
                Utils.ConvertDate(entity.getCreatedAt()),
                entity.getCreatedBy(),
                Utils.ConvertDate(entity.getModifiedAt()),
                entity.getModifiedBy()
        );
    }

    // dto -> entity (toEntity)
    public Article toEntity(UserAccount account) {
        return Article.of(
                title,
                content,
                account
        );
    }
}