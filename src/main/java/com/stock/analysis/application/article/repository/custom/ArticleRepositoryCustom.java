package com.stock.analysis.application.article.repository.custom;

import com.stock.analysis.dto.ArticleDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ArticleRepositoryCustom {

    Page<ArticleDto> findArticles(Pageable pageable);

}
