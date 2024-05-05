package com.stock.analysis.application.apply.service;

import com.stock.analysis.application.apply.dto.ApplyRequestDto;
import com.stock.analysis.application.apply.dto.ApplyResponseDto;
import com.stock.analysis.application.apply.dto.SearchApplyDto;
import com.stock.analysis.application.apply.repository.ApplyRepository;
import com.stock.analysis.application.apply.repository.ApplyRepositoryQuerySupport;
import com.stock.analysis.application.process.repository.ApplyProcessRepository;
import com.stock.analysis.domain.entity.Apply;
import com.stock.analysis.domain.entity.ApplyProcess;
import com.stock.analysis.domain.entity.UserAccount;
import com.stock.analysis.exception.ApplyAppException;
import com.stock.analysis.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Service
@RequiredArgsConstructor
public class ApplyServiceImpl implements ApplyService{

    private final ApplyRepository applyRepository;
    private final ApplyRepositoryQuerySupport applyRepositoryQuerySupport;
    private final ApplyProcessRepository applyProcessRepository;

    @Transactional(readOnly = true)
    public Page<ApplyResponseDto> selectApplies(SearchApplyDto searchApplyDto, Pageable pageable, UserAccount userAccount) {
        return applyRepositoryQuerySupport.searchSelectApplies(searchApplyDto, pageable, userAccount);
    }

    @Override
    @Transactional(readOnly = true)
    public ApplyResponseDto getApplyById(Long applyId) {
        ApplyResponseDto applyResponseDto = applyRepositoryQuerySupport.getApplyById(applyId).orElseThrow(
                () -> new ApplyAppException(ErrorCode.CONTENT_NOT_FOUND, "apply not found, id : %d".formatted(applyId)));
        List<ApplyProcess> processes = applyProcessRepository.findAllByIdApplyId(applyId);
        if (!processes.isEmpty()) {
            applyResponseDto.setProcesses(processes);
        }

        return applyResponseDto;
    }

    @Transactional
    public Apply createApply(ApplyRequestDto responseDto, UserAccount userAccount) {
        Apply apply = applyRepository.saveAndFlush(responseDto.toEntity(userAccount));
        AtomicInteger integer = new AtomicInteger();
        responseDto.getProcessCodes().forEach(code -> {
            applyProcessRepository.saveAndFlush(code.to(apply.getId(), userAccount.getId(), integer.incrementAndGet()));}
        );
        return apply;
    }

    @Transactional
    public void updateApply(ApplyRequestDto requestDto) {
        Apply apply = findApply(requestDto.getId());
        apply.update(requestDto);
    }

    @Transactional
    public void deleteApply(ApplyResponseDto responseDto) {

    }

    private Apply findApply(Long id) {
        return applyRepository.findById(id).orElseThrow(
                () -> new ApplyAppException(ErrorCode.CONTENT_NOT_FOUND, "apply not found, id : %d".formatted(id)));
    }
}
