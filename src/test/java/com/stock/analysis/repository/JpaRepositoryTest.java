package com.stock.analysis.repository;

import com.stock.analysis.domain.entity.Article;
import com.stock.analysis.domain.entity.UserAccount;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("JPA 연결 테스트")
@Import({JpaRepositoryTest.TestJpaConfig.class})
@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class JpaRepositoryTest {

    private final ArticleRepository articleRepository;
    private final ArticleCommentRepository articleCommentRepository;
    private final UserAccountRepository userAccountRepository;

    public JpaRepositoryTest(
            @Autowired ArticleRepository articleRepository,
            @Autowired ArticleCommentRepository articleCommentRepository,
            @Autowired UserAccountRepository userAccountRepository
    ) {
        this.articleRepository = articleRepository;
        this.articleCommentRepository = articleCommentRepository;
        this.userAccountRepository = userAccountRepository;
    }

    @DisplayName("article select 테스트")
    @Test
    void givenTestData_whenAllSelecting_thenReturnTotalSize() {
        // given
        // when
        List<Article> articles = articleRepository.findAll();

        // then
        assertThat(articles)
                .isNotNull()
                .hasSize(55);
    }

    @DisplayName("article insert 테스트")
    @Test
    public void givenDummyData_whenInserting_then() {
        // given
        long prevArticleTotalCount = articleRepository.count();
        UserAccount userAccount = userAccountRepository.save(UserAccount.of("noah00o", "1122", "noah@gmail.com", "noah"));
        Article newArticle = Article.of("title", "content", userAccount);

        // when
        articleRepository.save(newArticle);

        // then
        assertThat(articleRepository.count()).isEqualTo(prevArticleTotalCount + 1);
    }

    @DisplayName("")
    @Test
    public void givenTestData_whenUpdating_then() {
        // given
        Article article = articleRepository.findById(1L).orElseThrow();
        String title = "another title set";
        article.setTitle(title);

        // when
        Article updatedArticle = articleRepository.saveAndFlush(article);

        // then
        assertThat(updatedArticle).hasFieldOrPropertyWithValue("title", updatedArticle.getTitle());

    }

    @EnableJpaAuditing
    @TestConfiguration
    public static class TestJpaConfig {

        @Bean
        public AuditorAware<String> auditorAware() {
            return () -> Optional.of("noah00o");
        }
    }
}
