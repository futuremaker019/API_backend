package com.stock.analysis.application.code.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.stock.analysis.domain.entity.Code;
import com.stock.analysis.domain.entity.UserAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.stock.analysis.domain.entity.QCode.code;

@Repository
@RequiredArgsConstructor
public class CodeRepositorySupport {

    private final JPAQueryFactory queryFactory;

    public List<Code> selectCodesByUserAndParentId(Long codeId, UserAccount userAccount) {
        return queryFactory
                .selectFrom(code)
                .where(code.userAccount.eq(userAccount).and(code.parentId.eq(codeId)))
                .fetch();
    }
}
