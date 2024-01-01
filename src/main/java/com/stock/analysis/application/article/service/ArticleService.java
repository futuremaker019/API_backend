package com.stock.analysis.application.article.service;

import com.stock.analysis.application.article.repository.ArticleRepository;
import com.stock.analysis.domain.contant.SearchType;
import com.stock.analysis.domain.entity.Article;
import com.stock.analysis.domain.entity.UserAccount;
import com.stock.analysis.dto.ArticleDto;
import com.stock.analysis.dto.ArticleWithCommentsDto;
import com.stock.analysis.application.upload.service.UploadService;
import com.stock.analysis.application.useraccount.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final UserAccountRepository userAccountRepository;
    private final UploadService uploadService;

    @Transactional(readOnly = true)
    public Page<ArticleDto> searchArticles(SearchType searchType, String searchKeyword, Pageable pageable) {
        if (searchKeyword == null || searchKeyword.isBlank()) {
            return articleRepository.findAll(pageable).map(ArticleDto::from);
        }

        return switch (searchType) {
            case TITLE -> articleRepository.findByTitleContaining(searchKeyword, pageable).map(ArticleDto::from);
//            case ID -> articleRepository.findByUserAccount_UserIdContaining(searchKeyword, pageable).map(ArticleDto::from);
//            case CONTENT -> articleRepository.findByContentContaining(searchKeyword, pageable).map(ArticleDto::from);
//            case HASHTAG -> articleRepository.findByHashtag(searchKeyword, pageable).map(ArticleDto::from);
//            case NICKNAME -> articleRepository.findByUserAccount_NicknameContaining("#" + searchKeyword, pageable).map(ArticleDto::from);
        };
    }

    @Transactional(readOnly = true)
    public ArticleWithCommentsDto getArticle(Long id) {

        return articleRepository.findById(id)
                .map(ArticleWithCommentsDto::from)
                .orElseThrow(() -> new EntityNotFoundException("게시글을 찾지 못했습니다. articleId : " + id));
    }

    public Article saveArticle(ArticleDto dto) {
        UserAccount userAccount = userAccountRepository.getReferenceById(dto.userAccountDto().id());
        Article savedArticle = articleRepository.save(dto.toEntity(userAccount));
        return savedArticle;
    }

    public void updateArticle(Long articleId, ArticleDto dto) {
        Article article = articleRepository.getReferenceById(articleId);
        UserAccount userAccount = userAccountRepository.getReferenceById(dto.userAccountDto().id());

        try {
            if (article.getUserAccount().equals(userAccount)) {
                if (dto.title() != null) {
                    article.setTitle(dto.title());
                }
                if (dto.content() != null) {
                    article.setContent(dto.content());
                }
            }
        } catch (EntityNotFoundException e) {
            log.warn("update failed. 게시글을 수정하는데 필요한 정보를 찾을수 업습니다. - {}", e.getLocalizedMessage());
        }
    }
}
