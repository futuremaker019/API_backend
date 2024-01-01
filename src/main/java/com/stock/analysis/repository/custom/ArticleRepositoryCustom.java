package com.stock.analysis.repository.custom;

import com.stock.analysis.domain.entity.Article;
import com.stock.analysis.dto.ArticleDto;
import com.stock.analysis.dto.ArticleWithCommentsDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ArticleRepositoryCustom {

    Page<ArticleDto> findArticles(Pageable pageable);

}
