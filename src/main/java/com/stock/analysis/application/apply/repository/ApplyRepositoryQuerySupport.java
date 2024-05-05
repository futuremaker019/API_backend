package com.stock.analysis.application.apply.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.stock.analysis.application.apply.dto.ApplyResponseDto;
import com.stock.analysis.application.apply.dto.QApplyResponseDto;
import com.stock.analysis.application.apply.dto.SearchApplyDto;
import com.stock.analysis.application.contentFile.dto.QContentFileResponseDto;
import com.stock.analysis.domain.contant.ApplyEnum;
import com.stock.analysis.domain.contant.UploadType;
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
import java.util.Optional;

import static com.querydsl.core.group.GroupBy.groupBy;
import static com.querydsl.core.group.GroupBy.list;
import static com.stock.analysis.domain.entity.QApply.apply;
import static com.stock.analysis.domain.entity.QContentFile.contentFile;

@Repository
public class ApplyRepositoryQuerySupport extends QuerydslRepositorySupport {

    private final JPAQueryFactory queryFactory;

    public ApplyRepositoryQuerySupport(JPAQueryFactory queryFactory) {
        super(Apply.class);
        this.queryFactory = queryFactory;
    }

    public Page<ApplyResponseDto> searchSelectApplies(SearchApplyDto searchApplyDto, Pageable pageable, UserAccount userAccount) {
        Map<Apply, ApplyResponseDto> result = queryFactory.selectFrom(apply)
                .where(getBooleanBuilder(searchApplyDto), apply.userId.eq(userAccount.getId()))
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
                        apply.applyStatus,
                        apply.applyType,
                        apply.passType,
                        apply.passResumeType
                )));
        List<ApplyResponseDto> list = result.keySet().stream().map(result::get).toList();
        JPAQuery<Long> countQuery = queryFactory.select(apply.id.count()).from(apply).where(getBooleanBuilder(searchApplyDto));
        return PageableExecutionUtils.getPage(list, pageable, countQuery::fetchOne);
    }

    /**
     * 기본 지원필드, 진행단계를 위한 채용전형코드, 파일다운로드를 위한 파일정보들
     */
    public Optional<ApplyResponseDto> getApplyById(Long applyId) {
        Map<Apply, ApplyResponseDto> result = queryFactory.selectFrom(apply)
                .leftJoin(contentFile).on(contentFile.joinKey.eq(apply.id).and(contentFile.uploadType.eq(UploadType.APPLY)))
                .where(apply.id.eq(applyId))
                .transform(groupBy(apply).as(new QApplyResponseDto(
                        apply.id, apply.companyName, apply.companyLocation, apply.platform,
                        apply.applyDate, apply.jobOpeningDate, apply.jobCloseDate,
                        apply.applyStatus, apply.applyType, apply.passType, apply.passResumeType,
                        apply.processCodeId, apply.headhunterCompany,
                        list(new QContentFileResponseDto(
                                contentFile.id, contentFile.name, contentFile.storedName,
                                contentFile.contentType, contentFile.path
                        ))
                )));
        return result.keySet().stream().map(result::get).findFirst();
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
            case PASSED -> apply.passType.eq(ApplyEnum.PassType.PASSED);
            case NOT_PASSED -> apply.passType.eq(ApplyEnum.PassType.NOT_PASSED);
            default -> null;
        };
    }

    private BooleanExpression applyEq(String isAppliedValue) {
        if (!StringUtils.hasText(isAppliedValue)) {
            return null;
        }
        ApplyEnum.ApplyStatus applyStatus = Arrays.stream(ApplyEnum.ApplyStatus.values())
                .filter(value -> value.name().equals(isAppliedValue))
                .findFirst().orElse(null);
        if (applyStatus == null) {
            return null;
        }
        return switch (applyStatus) {
            case APPLIED -> apply.applyStatus.eq(ApplyEnum.ApplyStatus.APPLIED);
            case NOT_APPLIED -> apply.applyStatus.eq(ApplyEnum.ApplyStatus.NOT_APPLIED);
            case NONE -> apply.applyStatus.eq(ApplyEnum.ApplyStatus.NONE);
        };
    }
}
