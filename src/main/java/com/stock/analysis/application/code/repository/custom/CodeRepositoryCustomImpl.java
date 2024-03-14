package com.stock.analysis.application.code.repository.custom;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.stock.analysis.domain.entity.Code;
import com.stock.analysis.domain.entity.UserAccount;
import com.stock.analysis.dto.response.CodeResponseDto;
import com.stock.analysis.dto.response.QCodeResponseDto;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

import static com.stock.analysis.domain.entity.QCode.code;

public class CodeRepositoryCustomImpl extends QuerydslRepositorySupport implements CodeRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public CodeRepositoryCustomImpl(JPAQueryFactory queryFactory) {
        super(Code.class);
        this.queryFactory = queryFactory;
    }

    @Override
    public List<Code> selectCodesByUserAndParentIsNull(UserAccount userAccount) {
        return queryFactory
                .selectFrom(code)
                .where(code.userAccount.eq(userAccount).and(code.parentId.isNull()))
                .fetch();
    }

    @Override
    public List<Code> selectCodesByUserAndParentId(Long codeId, UserAccount userAccount) {
        return queryFactory
                .selectFrom(code)
                .where(code.userAccount.eq(userAccount).and(code.parentId.eq(codeId)))
                .fetch();
    }

    @Override
    public List<Code> selectCodesByUserAndPrimeCodeName(String primeCodeName, UserAccount userAccount) {
        return queryFactory
                .selectFrom(code)
                .where(code.userAccount.eq(userAccount).and(code.primeCodeName.eq(primeCodeName)))
                .fetch();
    }
}
