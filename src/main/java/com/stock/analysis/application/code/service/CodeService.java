package com.stock.analysis.application.code.service;

import com.stock.analysis.application.code.repository.CodeRepository;
import com.stock.analysis.domain.contant.CodeType;
import com.stock.analysis.domain.entity.Code;
import com.stock.analysis.dto.request.CodeRequestDto;
import com.stock.analysis.dto.response.CodeResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CodeService {

    private final CodeRepository codeRepository;

    // 코드는 아래처럼 구분하여 사용하는것도 좋지만 캐쉬를 통한 사용도 바람직할거 같다.

    @Transactional(readOnly = true)
    public List<CodeResponseDto> selectCodes(CodeType codeType) {
        // 개별적인 분류검색에 codeType을 사용하면 될듯하다.
        // 모든 코드를 프론트로 가져와 프론트에서 작업하는것도 방법이다.
        System.out.println("codeType = " + codeType);
        if (codeType != null) {
            return null;
        } else {
            //
            return codeRepository.findAllByParentIdIsNull().stream()
                    .map(CodeResponseDto::from).toList();
        }
    }

    @Transactional(readOnly = true)
    public CodeResponseDto getCode(Long codeId) {
        return null;
    }

    public void createCode(CodeRequestDto requestDto) {
        codeRepository.save(requestDto.to());
    }

    public void updateCode(Long codeId, CodeRequestDto requestDto) {
        Code code = codeRepository.getReferenceById(codeId);
        code.updateCode(requestDto);
    }
}
