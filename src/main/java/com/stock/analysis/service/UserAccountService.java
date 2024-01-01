package com.stock.analysis.service;

import com.stock.analysis.dto.UserAccountDto;
import com.stock.analysis.repository.UserAccountRepository;
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
