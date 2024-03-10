package com.stock.analysis.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.stock.analysis.config.JpaTestConfig;
import com.stock.analysis.config.QuerydslTestConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.test.context.ContextConfiguration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@DisplayName("Code Repository 테스트")
@Import({JpaTestConfig.class, QuerydslTestConfig.class})
@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CodeRepositoryTest {

    @Test
    void given_when_then() {
        //given

        // when

        // then
    }
}
