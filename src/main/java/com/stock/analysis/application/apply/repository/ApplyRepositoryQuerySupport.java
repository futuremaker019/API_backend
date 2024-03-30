package com.stock.analysis.application.apply.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.stock.analysis.application.apply.dto.QApplyResponseDto;
import com.stock.analysis.domain.contant.ApplyType;
import com.stock.analysis.domain.entity.Apply;
import com.stock.analysis.domain.entity.UserAccount;
import com.stock.analysis.application.apply.dto.SearchApplyDto;
import com.stock.analysis.application.apply.dto.ApplyResponseDto;
import com.stock.analysis.utils.Utils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

import static com.querydsl.core.group.GroupBy.groupBy;
import static com.stock.analysis.domain.entity.QApply.apply;

@Repository
public class ApplyRepositoryQuerySupport extends QuerydslRepositorySupport {

    private final JPAQueryFactory queryFactory;

    public ApplyRepositoryQuerySupport(JPAQueryFactory queryFactory) {
        super(Apply.class);
        this.queryFactory = queryFactory;
    }

    public Page<ApplyResponseDto> searchSelectApplies(SearchApplyDto searchApplyDto, Pageable pageable, UserAccount userAccount) {
        Map<Apply, ApplyResponseDto> result = queryFactory.selectFrom(apply)
                .where(getBooleanBuilder(searchApplyDto), apply.userAccount.eq(userAccount))
                .orderBy(Utils.getOrderList(pageable.getSort(), Apply.class).toArray(OrderSpecifier[]::new))
                .transform(groupBy(apply).as(new QApplyResponseDto(
                        apply.id,
                        apply.companyLocation,
                        apply.companyName,
                        apply.applyDate,
                        apply.jobOpeningDate,
                        apply.jobCloseDate,
                        apply.isApplied,
                        apply.applyType,
                        apply.pass,
                        apply.passResume
                )));
        List<ApplyResponseDto> list = result.keySet().stream().map(result::get).toList();
        JPAQuery<Long> countQuery = queryFactory.select(apply.count())
                .from(apply).where(getBooleanBuilder(searchApplyDto));
        return PageableExecutionUtils.getPage(list, pageable, countQuery::fetchOne);
    }

    private BooleanBuilder getBooleanBuilder(SearchApplyDto searchDto) {
        BooleanBuilder builder = new BooleanBuilder();
        return builder
                .and(companyNameEq(searchDto.getCompanyName()))
                .and(passEq(searchDto.getPass()))
                .and(applyEq(searchDto.getIsApplied()));
    }

    private BooleanExpression companyNameEq(String companyName) {
        return StringUtils.hasText(companyName) ? apply.companyName.like(companyName) : null;
    }
    private BooleanExpression passEq(Boolean pass) {
        return pass != null ? apply.pass.eq(pass) : null;
    }
    private BooleanExpression applyEq(ApplyType isApplied) {
        return switch (isApplied) {
            case APPLIED -> apply.isApplied.eq(ApplyType.APPLIED);
            case NOT_APPLIED -> apply.isApplied.eq(ApplyType.NOT_APPLIED);
            default -> apply.isApplied.eq(ApplyType.NONE);
        };
    }

}
