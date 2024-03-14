package com.stock.analysis.application.code.repository.custom;

import com.stock.analysis.domain.entity.Code;
import com.stock.analysis.domain.entity.UserAccount;
import com.stock.analysis.dto.response.CodeResponseDto;

import java.util.List;

public interface CodeRepositoryCustom {

    List<Code> selectCodesByUser(UserAccount userAccount);

    List<Code> selectCodesByUserAndParentId(Long codeId, UserAccount userAccount);

    List<Code> selectCodesByUserAndPrimeCodeName(String primeCodeName, UserAccount userAccount);

}
