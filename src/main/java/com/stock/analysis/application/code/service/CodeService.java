package com.stock.analysis.application.code.service;

import com.stock.analysis.application.code.repository.CodeRepository;
import com.stock.analysis.application.code.repository.CodeRepositorySupport;
import com.stock.analysis.domain.contant.CodeType;
import com.stock.analysis.domain.entity.Code;
import com.stock.analysis.domain.entity.UserAccount;
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
    private final CodeRepositorySupport codeRepositorySupport;

    @Transactional(readOnly = true)
    public List<CodeResponseDto> getCodes(CodeType codeType) {
        if (codeType != null) {
            return null;
        } else {
            //
            return codeRepository.findAllByParentIdIsNullOrderByCreatedAtAsc().stream().map(CodeResponseDto::from).toList();
        }
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

    /**
     * 코드 드로워에서 사용할수 있도록
     */
    public List<CodeResponseDto> selectCodesByUserAndParentId(Long codeId, UserAccount userAccount) {
        return codeRepositorySupport.selectCodesByUserAndParentId(codeId, userAccount)
                .stream().map(CodeResponseDto::from).toList();
    }
}
