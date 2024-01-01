package com.stock.analysis.application.useraccount.service;

import com.stock.analysis.application.useraccount.repository.UserAccountRepository;
import com.stock.analysis.dto.UserAccountDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserAccountService {

    private final UserAccountRepository userAccountRepository;

    @Transactional(readOnly = true)
    public Optional<UserAccountDto> searchUser(String userId) {
        return userAccountRepository.findByUserId(userId)
                .map(UserAccountDto::from);
    }

}
