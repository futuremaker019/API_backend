package com.stock.analysis.application.apply.repository.custom;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.stock.analysis.application.apply.dto.ApplyResponseDto;
import com.stock.analysis.domain.contant.ApplyEnum;
import com.stock.analysis.domain.entity.Apply;
import com.stock.analysis.application.apply.dto.SearchApplyDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.util.StringUtils;

import java.util.Arrays;

import static com.querydsl.core.group.GroupBy.groupBy;
import static com.stock.analysis.domain.entity.QApply.apply;

public class ApplyRepositoryCustomImpl extends QuerydslRepositorySupport implements ApplyRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public ApplyRepositoryCustomImpl(JPAQueryFactory queryFactory) {
        super(Apply.class);
        this.queryFactory = queryFactory;
    }

    @Override
    public Page<ApplyResponseDto> searchSelectApplies(SearchApplyDto searchApplyDto, Pageable pageable) {
//        Map<Apply, ApplyResponseDto> result = queryFactory.selectFrom(apply)
//                .where(getBooleanBuilder(searchApplyDto))
//                .orderBy(Utils.getOrderList(pageable.getSort(), Apply.class).toArray(OrderSpecifier[]::new))
//                .transform(groupBy(apply).as(new QApplyResponseDto(
//                        apply.id,
//                        apply.companyLocation,
//                        apply.companyName,
//                        apply.applyDate,
//                        apply.jobOpeningDate,
//                        apply.jobCloseDate,
//                        apply.isApplied,
//                        apply.applyType,
//                        apply.pass,
//                        apply.passResume
//                )));
//        List<ApplyResponseDto> list = result.keySet().stream().map(result::get).toList();
//        JPAQuery<Long> countQuery = queryFactory.select(apply.count()).from(apply).where(getBooleanBuilder(searchApplyDto));
        return Page.empty();
    }

    private BooleanBuilder getBooleanBuilder(SearchApplyDto searchDto) {
        BooleanBuilder builder = new BooleanBuilder();
        return builder
                .and(companyNameEq(searchDto.getCompanyName()))
                .and(passEq(searchDto.getPass()))
                .and(applyEq(searchDto.getIsAppliedValue()));
    }

    private BooleanExpression companyNameEq(String companyName) {
        return StringUtils.hasText(companyName) ? apply.companyName.like(companyName) : null;
    }
    private BooleanExpression passEq(Boolean pass) {
        return pass != null ? apply.pass.eq(pass) : null;
    }
    private BooleanExpression applyEq(String isAppliedValue) {
        ApplyEnum.IsApplied isApplied = Arrays.stream(ApplyEnum.IsApplied.values())
                .filter(value -> value.name().equals(isAppliedValue))
                .findFirst().orElse(null);

        return switch (isApplied) {
            case APPLIED -> apply.isApplied.eq(ApplyEnum.IsApplied.APPLIED);
            case NOT_APPLIED -> apply.isApplied.eq(ApplyEnum.IsApplied.NOT_APPLIED);
            case NONE -> apply.isApplied.eq(ApplyEnum.IsApplied.NONE);
            default -> null;
        };
    }
}
