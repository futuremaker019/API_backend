package com.stock.analysis.application.code.repository.custom;

import com.stock.analysis.domain.entity.Code;
import com.stock.analysis.domain.entity.UserAccount;

import java.util.List;

public interface CodeRepositoryCustom {

    List<Code> selectCodesByUserAndParentIsNull(UserAccount userAccount);

    List<Code> selectCodesByUserAndParentId(Long codeId, UserAccount userAccount);

    List<Code> selectCodesByUserAndPrimeCodeName(String primeCodeName, UserAccount userAccount);

}
