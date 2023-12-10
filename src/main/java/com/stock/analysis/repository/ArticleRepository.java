package com.stock.analysis.repository;

import com.stock.analysis.domain.entity.Article;
import com.stock.analysis.repository.custom.ArticleRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long>, ArticleRepositoryCustom {

    Page<Article> findByTitleContaining(String title, Pageable pageable);

    @Override
    @EntityGraph(attributePaths = {"userAccount"})
    Page<Article> findAll(Pageable pageable);

    @Override
    @EntityGraph(attributePaths = {"userAccount"})
    List<Article> findAll();

    @Override
    @EntityGraph(attributePaths = {"userAccount", "articleUploads"})
    Optional<Article> findById(Long id);
}
