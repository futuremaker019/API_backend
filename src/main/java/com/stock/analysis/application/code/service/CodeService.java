package com.stock.analysis.application.code.service;

import com.stock.analysis.application.code.repository.CodeRepository;
import com.stock.analysis.domain.contant.CodeType;
import com.stock.analysis.domain.entity.Code;
import com.stock.analysis.dto.request.CodeRequestDto;
import com.stock.analysis.dto.response.CodeResponseDto;
import com.stock.analysis.exception.CodeAppException;
import com.stock.analysis.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CodeService {

    private final CodeRepository codeRepository;

    // 코드는 아래처럼 구분하여 사용하는것도 좋지만 캐쉬를 통한 사용도 바람직할거 같다.

    @Transactional(readOnly = true)
    public List<CodeResponseDto> getCodes(CodeType codeType) {
        // 개별적인 분류검색에 codeType을 사용하면 될듯하다.
        // 모든 코드를 프론트로 가져와 프론트에서 작업하는것도 방법이다.
        System.out.println("codeType = " + codeType);
        if (codeType != null) {
            return null;
        } else {
            //
            return codeRepository.findAllByParentIdIsNullOrderByCreatedAtAsc().stream().map(CodeResponseDto::from).toList();
        }
    }

    @Transactional(readOnly = true)
    public List<CodeResponseDto> getCodesByParentId(Long parentId) {
        return codeRepository.findAllByParentId(parentId).stream().map(CodeResponseDto::from).toList();
    }

    @Transactional(readOnly = true)
    public List<CodeResponseDto> selectCodesById(Long codeId) {
        return codeRepository.findAllById(codeId).stream().map(CodeResponseDto::from).toList();
    }

    @Transactional(readOnly = true)
    public CodeResponseDto getCodeById(Long codeId) {
        Code code = codeRepository.findById(codeId).orElseThrow(() -> new CodeAppException(ErrorCode.CODE_NOT_FOUND));
        return CodeResponseDto.from(code);
    }

    public void createCode(CodeRequestDto requestDto) {
        codeRepository.findByNameAndParentId(requestDto.name(), requestDto.parentId()).ifPresent(c -> {
            throw new CodeAppException(ErrorCode.CODE_NAME_EXISTED, "code name existed : %s".formatted(c.getName()));
        });
        codeRepository.save(requestDto.to());
    }

    public void updateCode(CodeRequestDto requestDto) {
        Code code = codeRepository.findById(requestDto.id()).orElseThrow(
                () -> new CodeAppException(ErrorCode.CODE_NOT_FOUND, "code not found : id - %d".formatted(requestDto.id()))
        );
        code.updateCode(requestDto);
    }

    public void deleteCode(Long codeId) {
        Code code = codeRepository.findById(codeId).orElseThrow(
                () -> new CodeAppException(ErrorCode.CODE_NOT_FOUND, "code not found : id - %d".formatted(codeId))
        );
        if (!code.getChildren().isEmpty()) {
            throw new CodeAppException(ErrorCode.CODE_CHILDREN_EXISTED, "children existed");
        }
        codeRepository.deleteById(codeId);
    }
}
