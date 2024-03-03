package com.stock.analysis.application.code.repository.custom;

import com.stock.analysis.domain.entity.UserAccount;
import com.stock.analysis.dto.response.CodeResponseDto;

import java.util.List;

public interface CodeRepositoryCustom {

    List<CodeResponseDto> selectCodesByUser(UserAccount userAccount);

}
