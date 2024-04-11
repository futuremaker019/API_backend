package com.stock.analysis.application.apply.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.stock.analysis.application.apply.dto.ApplyResponseDto;
import com.stock.analysis.application.apply.dto.QApplyResponseDto;
import com.stock.analysis.application.apply.dto.SearchApplyDto;
import com.stock.analysis.domain.contant.ApplyEnum;
import com.stock.analysis.domain.entity.Apply;
import com.stock.analysis.domain.entity.UserAccount;
import com.stock.analysis.utils.Utils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.Arrays;
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
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .transform(groupBy(apply).as(new QApplyResponseDto(
                        apply.id,
                        apply.companyName,
                        apply.companyLocation,
                        apply.applyDate,
                        apply.jobOpeningDate,
                        apply.jobCloseDate,
                        apply.isApplied,
                        apply.applyType,
                        apply.pass,
                        apply.passResume
                )));
        List<ApplyResponseDto> list = result.keySet().stream().map(result::get).toList();
        JPAQuery<Long> countQuery = queryFactory.select(apply.id.count())
                .from(apply).where(getBooleanBuilder(searchApplyDto));
        return PageableExecutionUtils.getPage(list, pageable, countQuery::fetchOne);
    }

    private BooleanBuilder getBooleanBuilder(SearchApplyDto searchDto) {
        BooleanBuilder builder = new BooleanBuilder();
        return builder
                .and(companyNameEq(searchDto.getCompanyName()))
                .and(passEq(searchDto.getPassValue()))
                .and(applyEq(searchDto.getIsAppliedValue()))
                .and(jobCloseDateBetween(searchDto.getJobCloseStartDate(), searchDto.getJobCloseEndDate()));
    }

    private BooleanExpression jobCloseDateBetween(LocalDate jobCloseStartDate, LocalDate jobCloseEndDate) {
        if (jobCloseStartDate == null || jobCloseEndDate == null) {
            return null;
        }
        return apply.jobCloseDate.between(jobCloseStartDate, jobCloseEndDate);
    }

    private BooleanExpression companyNameEq(String companyName) {
        /**
         * like (string), contains (%string%), startWith (string%), endWith(%string)
         */
        return StringUtils.hasText(companyName) ? apply.companyName.contains(companyName) : null;
    }
    private BooleanExpression passEq(String passValue) {
        if (!StringUtils.hasText(passValue)) {
            return null;
        }
        ApplyEnum.PassType passType = Arrays.stream(ApplyEnum.PassType.values())
                    .filter(value -> value.name().equals(passValue))
                    .findFirst().orElse(null);

        if (passType == null) {
            return null;
        }
        return switch (passType) {
            case PASS -> apply.pass.eq(ApplyEnum.PassType.PASS.getPass());
            case NOT_PASS -> apply.pass.eq(ApplyEnum.PassType.NOT_PASS.getPass());
            default -> null;
        };
    }
    private BooleanExpression applyEq(String isAppliedValue) {
        if (!StringUtils.hasText(isAppliedValue)) {
            return null;
        }
        ApplyEnum.IsApplied isApplied = Arrays.stream(ApplyEnum.IsApplied.values())
                .filter(value -> value.name().equals(isAppliedValue))
                .findFirst().orElse(null);
        if (isApplied == null) {
            return null;
        }
        return switch (isApplied) {
            case APPLIED -> apply.isApplied.eq(ApplyEnum.IsApplied.APPLIED);
            case NOT_APPLIED -> apply.isApplied.eq(ApplyEnum.IsApplied.NOT_APPLIED);
            case NONE -> apply.isApplied.eq(ApplyEnum.IsApplied.NONE);
        };
    }

}
