package com.stock.analysis.application.code.service;

import com.stock.analysis.domain.contant.CodeType;
import com.stock.analysis.domain.entity.UserAccount;
import com.stock.analysis.dto.CodeDto;
import com.stock.analysis.dto.request.CodeRequestDto;
import com.stock.analysis.dto.response.CodeResponseDto;

import java.util.List;
import java.util.Map;

public interface CodeService {

    List<CodeResponseDto> selectCodesByUserAndParentId(Long codeId, UserAccount userAccount);
    List<CodeResponseDto> selectCodesByUserAndPrimeCodeName(String primeCodeName, UserAccount userAccount);
    List<CodeDto> selectSequencingCodes(Long codeId, UserAccount userAccount);
    List<CodeResponseDto> selectCodesByUserAndParentIdIsNull(UserAccount userAccount);

    CodeResponseDto getCode(Long codeId);

    void createCode(CodeRequestDto requestDto, UserAccount userAccount);
    void updateCode(CodeRequestDto requestDto);
    void deleteCode(Long codeId);

    Map<Long, CodeDto> selectFlatCodes(UserAccount userAccount);

    //    List<CodeResponseDto> selectCodes(CodeType codeType);

}
