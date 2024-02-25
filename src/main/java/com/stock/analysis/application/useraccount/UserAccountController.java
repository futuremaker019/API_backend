package com.stock.analysis.application.useraccount;

import com.stock.analysis.application.useraccount.service.UserAccountService;
import com.stock.analysis.dto.request.UserJoinRequest;
import com.stock.analysis.dto.request.UserLoginRequest;
import com.stock.analysis.dto.response.CodeResponseDto;
import com.stock.analysis.dto.response.Response;
import com.stock.analysis.dto.response.UserLoginResponse;
import com.stock.analysis.exception.AuthenticationException;
import com.stock.analysis.exception.ErrorCode;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Objects;

@Tag(name = "UserAccount Api", description = "UserAccount Application Api")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "성공"),
        @ApiResponse(responseCode = "404", description = "코드가 존재하지 않습니다."),
})
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserAccountController {

    private final UserAccountService userAccountService;

    /**
     * 가입성공 시, "SUCCESS" 메시지 전송
     */
    @PostMapping("/signup")
    public Response<Void> signup(@Valid @RequestBody UserJoinRequest request, Errors errors) {
        if (errors.hasErrors()) {
            String defaultMessage = Objects.requireNonNull(errors.getFieldError()).getDefaultMessage();
            System.out.println("errors = " + defaultMessage);
            throw new AuthenticationException(ErrorCode.HAS_NO_ARGUMENTS, defaultMessage);
        }

        userAccountService.saveUserAccount(request);
        return Response.success();
    }

    /**
     * 로그인 성공시, "SUCCESS" 메시지 및 바디 전송
     */
    @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(examples = {
            @ExampleObject(name = "code", value = """ 
                        {
                            "userId" : "noah",
                            "password" : "1122"
                        }
                    """)
    }))
    @PostMapping("/signin")
    public Response<UserLoginResponse> signin(
            @Valid @RequestBody UserLoginRequest request,
            Errors errors,
            HttpServletResponse response
    ) {
        if (errors.hasErrors()) {
            String defaultMessage = Objects.requireNonNull(errors.getFieldError()).getDefaultMessage();
            System.out.println("errors = " + defaultMessage);
            throw new AuthenticationException(ErrorCode.HAS_NO_ARGUMENTS, defaultMessage);
        }

        return Response.success(userAccountService.loginUserAccount(request, response));
    }

    @PostMapping("/signout")
    public Response<Void> signout(HttpServletRequest request) {
        userAccountService.signout(request);
        return Response.success();
    }

    @GetMapping("/reissue")
    public ResponseEntity<String> reissue(HttpServletRequest request) {
        String accessToken = userAccountService.reissueToken(request);
        return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, accessToken).body(accessToken);
    }
}

