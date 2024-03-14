package com.stock.analysis.application.code.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.stock.analysis.domain.entity.Code;
import com.stock.analysis.domain.entity.UserAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.stock.analysis.domain.entity.QCode.code;

/**
 *  bean으로 등록된 JPAQueryFactory를 생성자 주입으로도 querydsl을 사용가능하다.
 *      그렇지만 applyingPagination을 사용못함...
 */
//@Repository
//@RequiredArgsConstructor
public class CodeRepositorySupport {

//    private final JPAQueryFactory queryFactory;

//    public List<Code> selectCodesByUserAndParentId(Long codeId, UserAccount userAccount) {
//        return queryFactory
//                .selectFrom(code)
//                .where(code.userAccount.eq(userAccount).and(code.parentId.eq(codeId)))
//                .fetch();
//    }
}
