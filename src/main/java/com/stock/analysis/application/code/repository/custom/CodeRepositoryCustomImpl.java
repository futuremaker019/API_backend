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

    /**
     * 어떻게 재귀적으로 코드값을 불러와야 할까
     *  유저에 따라 코드를 재귀적으로 불러와야한다.
     */
    @Override
    public List<Code> selectCodesByUser(UserAccount userAccount) {
        return queryFactory
                .selectFrom(code)
                .where(code.userAccount.eq(userAccount))
                .fetch();
    }

}
