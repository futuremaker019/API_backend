package com.stock.analysis.application.apply.service;

import com.stock.analysis.application.apply.dto.ApplyRequestDto;
import com.stock.analysis.application.apply.dto.ApplyResponseDto;
import com.stock.analysis.domain.entity.Apply;
import com.stock.analysis.domain.entity.UserAccount;
import com.stock.analysis.application.apply.dto.SearchApplyDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ApplyService {
    Page<ApplyResponseDto> selectApplies(SearchApplyDto searchApplyDto, Pageable pageable, UserAccount userAccount);

    ApplyResponseDto getApplyById(Long applyId);

    Apply createApply(ApplyRequestDto responseDto, UserAccount userAccount);

    void updateApply(ApplyRequestDto requestDto);
    void deleteApply(ApplyResponseDto responseDto);
}
