package com.stock.analysis.application.useraccount.service;

import com.stock.analysis.application.useraccount.repository.UserAccountRepository;
import com.stock.analysis.config.jwt.JwtUtils;
import com.stock.analysis.domain.contant.RoleType;
import com.stock.analysis.domain.entity.UserAccount;
import com.stock.analysis.dto.UserAccountDto;
import com.stock.analysis.dto.request.UserJoinRequest;
import com.stock.analysis.dto.request.UserLoginRequest;
import com.stock.analysis.dto.response.Response;
import com.stock.analysis.dto.response.UserLoginResponse;
import com.stock.analysis.dto.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class UserAccountService {

    private final PasswordEncoder passwordEncoder;
    private final UserAccountRepository userAccountRepository;
    private final JwtUtils jwtUtils;

    @Transactional(readOnly = true)
    public Optional<UserAccountDto> searchUser(String userId) {
        return userAccountRepository.findByUserId(userId).map(UserAccountDto::from);
    }

    public UserAccountDto saveUserAccount(UserJoinRequest userJoinRequest) {
        userAccountRepository.findByUserId(userJoinRequest.userId()).ifPresent(e -> {
            throw new UsernameNotFoundException("UserId Existed");
        });

        UserAccountDto dto = userJoinRequest.toDto();
        System.out.println("dto = " + dto);
        UserAccountDto result = UserAccountDto.from(userAccountRepository.save(dto.toEntity()));
        System.out.println("result = " + result);
        return result;
    }

    /**
     * 
     * token과 user의 정보를 같이 전달
     * 
     */
    public Response<UserLoginResponse> loginUserAccount(UserLoginRequest request) {

        

        return null;
    }
}
