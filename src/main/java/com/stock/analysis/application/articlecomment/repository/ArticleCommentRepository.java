package com.stock.analysis.application.articlecomment.repository;

import com.stock.analysis.domain.entity.ArticleComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleCommentRepository extends JpaRepository<ArticleComment, Long> {
}
