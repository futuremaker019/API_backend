package com.stock.analysis.application.code.repository;

import com.stock.analysis.domain.entity.Code;
import com.stock.analysis.utils.Querydsl4RepositorySupport;
import org.springframework.stereotype.Repository;

/**
 * QueryDslRepositorySupport를 구현하여 사용 (by 김영한)
 */
//@Repository
public class CodeSupportRepository extends Querydsl4RepositorySupport {

    public CodeSupportRepository() {
        super(Code.class);
    }
}
