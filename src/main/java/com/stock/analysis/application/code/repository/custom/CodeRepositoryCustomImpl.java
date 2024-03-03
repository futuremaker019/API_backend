package com.stock.analysis.application.code.repository.custom;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.stock.analysis.domain.entity.Code;
import com.stock.analysis.domain.entity.UserAccount;
import com.stock.analysis.dto.response.CodeResponseDto;
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
    public List<CodeResponseDto> selectCodesByUser(UserAccount userAccount) {
        List<Code> codes = queryFactory
                .select(code)
                .from(code)
                .fetch();

        return null;
    }
}
