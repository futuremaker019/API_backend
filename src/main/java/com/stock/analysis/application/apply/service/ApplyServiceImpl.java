package com.stock.analysis.application.apply.service;

import com.stock.analysis.application.apply.dto.ApplyRequestDto;
import com.stock.analysis.application.apply.dto.ApplyResponseDto;
import com.stock.analysis.application.apply.repository.ApplyRepository;
import com.stock.analysis.application.apply.repository.ApplyRepositoryQuerySupport;
import com.stock.analysis.domain.entity.Apply;
import com.stock.analysis.domain.entity.UserAccount;
import com.stock.analysis.application.apply.dto.SearchApplyDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ApplyServiceImpl implements ApplyService{

    private final ApplyRepository applyRepository;
    private final ApplyRepositoryQuerySupport applyRepositoryQuerySupport;

    @Transactional(readOnly = true)
    public Page<ApplyResponseDto> selectApplies(SearchApplyDto searchApplyDto, Pageable pageable, UserAccount userAccount) {
        return applyRepositoryQuerySupport.searchSelectApplies(searchApplyDto, pageable, userAccount);
    }

    @Transactional
    public Apply createApply(ApplyRequestDto responseDto, UserAccount userAccount) {
        return applyRepository.saveAndFlush(responseDto.toEntity(userAccount));
    }

    @Transactional
    public void updateApply(ApplyRequestDto requestDto) {

    }

    @Transactional
    public void deleteApply(ApplyResponseDto responseDto) {

    }
}
