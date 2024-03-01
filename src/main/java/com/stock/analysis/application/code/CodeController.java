package com.stock.analysis.application.code;

import com.stock.analysis.application.code.service.CodeService;
import com.stock.analysis.domain.contant.CodeType;
import com.stock.analysis.domain.entity.UserAccount;
import com.stock.analysis.dto.request.CodeRequestDto;
import com.stock.analysis.dto.response.CodeResponseDto;
import com.stock.analysis.dto.response.Response;
import com.stock.analysis.dto.security.CurrentUser;
import com.stock.analysis.dto.security.CustomUser;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Code Api", description = "Code Application Api")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "성공", content = {@Content(schema = @Schema(implementation = CodeResponseDto.class))}),
        @ApiResponse(responseCode = "404", description = "코드가 존재하지 않는다."),
        @ApiResponse(responseCode = "409", description = "중복된 코드명이 존재한다."),    // 코드 등록시
        @ApiResponse(responseCode = "409", description = "하위코드가 존재한다."),        // 코드 삭제시
})
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/codes")
public class CodeController {

    private final CodeService codeService;

    @GetMapping
    public Response<List<CodeResponseDto>> select(
            @RequestParam(value = "codeType", required = false) CodeType codeType
    ) {
        return Response.success(codeService.getCodes(codeType));
    }

    @GetMapping("/user")
    public Response<List<CodeResponseDto>> selectCodesByUserId(
            Authentication authentication,
            @AuthenticationPrincipal CustomUser customUser,
            @CurrentUser UserAccount userAccount
            ) {
        System.out.println("authentication = " + authentication);
        System.out.println("userPrincipal = " + customUser);
        System.out.println("userAccount = " + userAccount);
//        return Response.success(codeService.selectCodesByUserId(userPrincipal));
        return Response.success();
    }

    @PostMapping
    public Response<Void> create(@RequestBody CodeRequestDto requestDto) {
        codeService.createCode(requestDto);
        return Response.success();
    }

    @PutMapping
    public Response<CodeResponseDto> update(@RequestBody CodeRequestDto requestDto) {
        codeService.updateCode(requestDto);
        return Response.success();
    }

    @DeleteMapping("/{codeId}")
    public Response<Void> delete(@PathVariable(value = "codeId") Long codeId) {
        codeService.deleteCode(codeId);
        return Response.success();
    }
}
