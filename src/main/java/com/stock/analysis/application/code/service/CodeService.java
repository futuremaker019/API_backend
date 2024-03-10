package com.stock.analysis.application.code.service;

import com.stock.analysis.domain.contant.CodeType;
import com.stock.analysis.domain.entity.UserAccount;
import com.stock.analysis.dto.CodeDto;
import com.stock.analysis.dto.request.CodeRequestDto;
import com.stock.analysis.dto.response.CodeResponseDto;

import java.util.List;

public interface CodeService {

    List<CodeResponseDto> selectCodes(CodeType codeType);
    CodeResponseDto getCode(Long codeId);
    void createCode(CodeRequestDto requestDto);
    void updateCode(CodeRequestDto requestDto);
    void deleteCode(Long codeId);
    List<CodeResponseDto> selectCodesByUserAndParentId(Long codeId, UserAccount userAccount);
    List<String> selectSequencingCodes(Long codeId);
    List<CodeDto> selectFlatCodes(UserAccount userAccount);
}
