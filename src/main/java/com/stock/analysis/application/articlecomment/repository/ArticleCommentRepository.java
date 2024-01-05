package com.stock.analysis.application.articlecomment.repository;

import com.stock.analysis.domain.entity.ArticleComment;
import com.stock.analysis.dto.ArticleCommentDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleCommentRepository extends JpaRepository<ArticleComment, Long> {

    List<ArticleComment> findByArticle_Id(Long articleId);
}
