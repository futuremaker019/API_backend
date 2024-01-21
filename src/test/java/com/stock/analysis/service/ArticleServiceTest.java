package com.stock.analysis.service;

import com.stock.analysis.application.article.service.ArticleService;
import com.stock.analysis.domain.contant.RoleType;
import com.stock.analysis.domain.contant.SearchType;
import com.stock.analysis.domain.entity.Article;
import com.stock.analysis.domain.entity.UserAccount;
import com.stock.analysis.dto.ArticleDto;
import com.stock.analysis.dto.ArticleWithCommentsDto;
import com.stock.analysis.dto.UserAccountDto;
import com.stock.analysis.application.article.repository.ArticleRepository;
import com.stock.analysis.application.useraccount.repository.UserAccountRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@DisplayName("비즈니스 로직 - 게시글")
@ExtendWith(MockitoExtension.class)
class ArticleServiceTest {

    @InjectMocks
    private ArticleService sut;

    @Mock
    private ArticleRepository articleRepository;
    @Mock
    private UserAccountRepository userAccountRepository;

    @DisplayName("검색어 없이 검색하면, 게시글 페이지를 반환한다.")
    @Test
    public void givenNoSearchParameters_whenSearchingArticles_thenReturnsArticlePage() {
        // given
        Pageable pageable = Pageable.ofSize(10);
        given(articleRepository.findAll(pageable)).willReturn(Page.empty(pageable));

        // when
        Page<ArticleDto> articles = sut.searchArticles(null, null, pageable);

        // then
        assertThat(articles).isEmpty();
        then(articleRepository).should().findAll(pageable);
    }

    @DisplayName("검색어와 함께 게시글을 검색하면, 게시글 페이지를 반환한다.")
    @Test
    public void givenSearchParameters_whenSearchingArticles_thenReturnsArticlePage() {
        // given
        SearchType searchType = SearchType.TITLE;
        String searchKeyword = "title";
        Pageable pageable = Pageable.ofSize(10);
        given(articleRepository.findByTitleContaining(searchKeyword, pageable)).willReturn(Page.empty());  // 여기를 empty로 두는것이 맞는가...

        // when
        Page<ArticleDto> articles = sut.searchArticles(searchType, searchKeyword, pageable);

        // then
        assertThat(articles).isEmpty();
        then(articleRepository).should().findByTitleContaining(searchKeyword, pageable);

    }

    @DisplayName("게시글을 id로 조회하면 댓글이 달린 게시글을 반환한다.")
    @Test
    public void givenArticleId_whenCallingGetArticle_thenReturnArticle() {
        // given
        long articleId = 1L;
        Article article = createArticle();
        given(articleRepository.findById(articleId)).willReturn(Optional.of(article));

        // when
        ArticleWithCommentsDto dto = sut.getArticle(articleId);

        // then
        // 필드 내부의 속성을 확인
        assertThat(dto)
                .hasFieldOrPropertyWithValue("title", article.getTitle())
                .hasFieldOrPropertyWithValue("content", article.getContent());

        // given에서 주어진 상황에서 then의 수행이 잘 됬는지 확인
        then(articleRepository).should().findById(articleId);
    }

    @DisplayName("게시글을 id로 조회후 게시글이 없으면, 예외를 던진다.")
    @Test
    public void givenArticleId_whenCallingGetArticle_thenThrowException() {
        // given
        long articleId = 0L;
        given(articleRepository.findById(articleId)).willReturn(Optional.empty());

        // when
        Throwable throwable = catchThrowable(() -> sut.getArticle(articleId));

        // then
        assertThat(throwable)
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("게시글을 찾지 못했습니다. articleId : " + articleId);

        then(articleRepository).should().findById(articleId);
    }

    @DisplayName("새로운 게시글이 들어오면 게시글을 저장한다.")
    @Test
    public void givenNewArticle_whenSavingArticle_thenSavesArticle() {
        // given
        ArticleDto articleDto = createArticleDto();

        given(userAccountRepository.getReferenceById(articleDto.userAccountDto().id())).willReturn(createUserAccount());
        given(articleRepository.save(any(Article.class))).willReturn(createArticle());

        // when
        sut.saveArticle(articleDto);

        // then
        then(userAccountRepository).should().getReferenceById(articleDto.userAccountDto().id());
        then(articleRepository).should().save(any(Article.class));
    }

    private ArticleDto createArticleDto() {
        return ArticleDto.of(
                "new title",
                "new content",
                UserAccountDto.from(createUserAccount())
        );
    }

    private Article createArticle() {
        return Article.of(
                "title",
                "content",
                createUserAccount()
        );
    }

    private UserAccount createUserAccount() {
        return UserAccount.of(
                "noah",
                "1122",
                "noah00o@naver.com",
                "noah",
                Set.of(RoleType.USER)
        );
    }
}