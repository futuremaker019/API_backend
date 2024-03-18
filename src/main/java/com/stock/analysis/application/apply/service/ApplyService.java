package com.stock.analysis.application.apply.service;

import com.stock.analysis.domain.entity.Apply;
import com.stock.analysis.domain.entity.UserAccount;
import com.stock.analysis.dto.request.ApplyRequestDto;
import com.stock.analysis.dto.response.ApplyResponseDto;
import com.stock.analysis.dto.common.SearchDto;
import com.stock.analysis.application.apply.repository.ApplyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.awt.print.Pageable;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ApplyService {

    private final ApplyRepository applyRepository;

    public List<ApplyResponseDto> selectApplies(SearchDto searchDto, Pageable pageable) {
        return null;
    }

    public Apply createApply(ApplyRequestDto responseDto, UserAccount userAccount) {
        return applyRepository.saveAndFlush(responseDto.toEntity(userAccount));
    }
}
