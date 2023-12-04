package com.stock.analysis.repository;

import com.stock.analysis.domain.entity.Article;
import com.stock.analysis.repository.custom.ArticleRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long>, ArticleRepositoryCustom {

    Page<Article> findByTitleContaining(String title, Pageable pageable);

}
