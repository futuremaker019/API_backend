package com.stock.analysis.application.code.service;

import com.stock.analysis.application.code.repository.CodeRepository;
import com.stock.analysis.application.code.repository.CodeRepositorySupport;
import com.stock.analysis.domain.contant.CodeType;
import com.stock.analysis.domain.entity.Code;
import com.stock.analysis.domain.entity.UserAccount;
import com.stock.analysis.dto.CodeDto;
import com.stock.analysis.dto.request.CodeRequestDto;
import com.stock.analysis.dto.response.CodeResponseDto;
import com.stock.analysis.exception.CodeAppException;
import com.stock.analysis.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CodeServiceImpl implements CodeService {

    private final CodeRepository codeRepository;

    @Transactional(readOnly = true)
    public List<CodeResponseDto> selectCodes(CodeType codeType) {
        if (codeType != null) {
            return null;
        } else {
            return codeRepository.findAllByParentIdIsNullOrderByCreatedAtAsc()
                    .stream().map(CodeResponseDto::from).toList();
        }
    }

    @Override
    public CodeResponseDto getCode(Long codeId) {
        Code code = codeRepository.findById(codeId).orElseThrow(
                () -> new CodeAppException(ErrorCode.CODE_NOT_FOUND));
        return CodeResponseDto.from(code);
    }

    @Override
    public void createCode(CodeRequestDto requestDto) {
        codeRepository.findByNameAndParentId(requestDto.name(), requestDto.parentId()).ifPresent(c -> {
            throw new CodeAppException(ErrorCode.CODE_NAME_EXISTED, "code name existed : %s".formatted(c.getName()));
        });
        codeRepository.save(requestDto.to());
    }

    @Override
    public void updateCode(CodeRequestDto requestDto) {
        Code code = codeRepository.findById(requestDto.id()).orElseThrow(
                () -> new CodeAppException(ErrorCode.CODE_NOT_FOUND, "code not found : id - %d".formatted(requestDto.id()))
        );
        code.updateCode(requestDto);
    }

    @Override
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
     * 코드 드로워에서 사용함 - 채용전형을 선택하여 보여줄 수 있도록 만듬
     * (유저마다 서로다른 채용전형의 키값을 특정할수 없다. primeCodeName을 추가함)
     */
    @Override
    public List<CodeResponseDto> selectCodesByUserAndParentId(Long codeId, UserAccount userAccount) {
        return codeRepository.selectCodesByUserAndParentId(codeId, userAccount)
                .stream().map(CodeResponseDto::from).toList();
    }

    @Override
    public List<CodeResponseDto> selectCodesByUserAndPrimeCodeName(String primeCodeName, UserAccount userAccount) {
        Code code = codeRepository.findByPrimeCodeNameAndUserAccount(primeCodeName, userAccount).orElseThrow(
                () -> new CodeAppException(ErrorCode.CODE_NOT_FOUND));
        return codeRepository.selectCodesByUserAndParentId(code.getId(), userAccount)
                .stream().map(CodeResponseDto::from).toList();
    }

    @Override
    public List<CodeDto> selectSequencingCodes(Long codeId, UserAccount userAccount) {
        List<CodeDto> result = new ArrayList<>();
        Map<Long, CodeDto> resultMap = selectFlatCodes(userAccount);
        Set<Long> codeIds = resultMap.keySet();
        findParentCode(resultMap, codeIds, result, codeId);
        result.sort(Comparator.comparing(CodeDto::id));
        return result;
    }

    private void findParentCode(Map<Long, CodeDto> codes, Set<Long> codeIds, List<CodeDto> list, Long codeId) {
        if (codeId != null && codeIds.contains(codeId)) {
            CodeDto codeDto = codes.get(codeId);
            Long parentId = codeDto.parentId();
            if (parentId != null) {
                list.add(codeDto);
                findParentCode(codes, codeIds, list, parentId);
            }
        }
    }

    /**
     * user_id에 따른 code의 리스트를 가져온다.
     * 하위코드들의 아이디 리스트로 변경하여 가져온다.
     */
    @Override
    public Map<Long, CodeDto> selectFlatCodes(UserAccount userAccount) {
        List<Code> codes = codeRepository.selectCodesByUser(userAccount);
        if (codes.isEmpty()) {
            throw new CodeAppException(ErrorCode.CODE_NOT_FOUND);
        }
        return codes.stream().collect(Collectors.toMap(Code::getId, CodeDto::from));
    }
}
