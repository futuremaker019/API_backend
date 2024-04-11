package com.stock.analysis.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.stock.analysis.application.apply.dto.ApplyResponseDto;
import com.stock.analysis.application.apply.repository.ApplyRepositoryQuerySupport;
import com.stock.analysis.config.JpaTestConfig;
import com.stock.analysis.config.QuerydslTestConfig;
import com.stock.analysis.domain.contant.ApplyEnum;
import com.stock.analysis.domain.contant.RoleType;
import com.stock.analysis.domain.entity.UserAccount;
import com.stock.analysis.application.apply.dto.SearchApplyDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("ApplyRepository 테스트")
@Import({JpaTestConfig.class, QuerydslTestConfig.class})
@DataJpaTest
public class ApplyRepositoryTest {

//    private final ApplyRepository applyRepository;
//    public ApplyRepositoryTest(@Autowired ApplyRepository applyRepository) {
//        this.applyRepository = applyRepository;
//    }

    private final ApplyRepositoryQuerySupport applyRepositoryQuerySupport;

    public ApplyRepositoryTest(@Autowired JPAQueryFactory queryFactory) {
        this.applyRepositoryQuerySupport = new ApplyRepositoryQuerySupport(queryFactory);
    }

    @Test
    void 회사검색_지원목록페이지_페이징목록_반환() {
        Page<ApplyResponseDto> dtos = applyRepositoryQuerySupport.searchSelectApplies(searchCompanyName(), Pageable.unpaged(), createUserAccount());
        assertThat(dtos.getSize()).isEqualTo(0);
    }

    SearchApplyDto searchCompanyName() {
        return SearchApplyDto.builder()
                .companyName("쿠팡2")
                .isAppliedValue(ApplyEnum.IsApplied.APPLIED.name())
                .build();
    }

    SearchApplyDto searchIsApplied() {
        return SearchApplyDto.builder()
                .isAppliedValue(ApplyEnum.IsApplied.APPLIED.name())
                .build();
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
