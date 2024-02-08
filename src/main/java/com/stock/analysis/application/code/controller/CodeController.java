package com.stock.analysis.application.code.controller;

import com.stock.analysis.application.code.service.CodeService;
import com.stock.analysis.domain.contant.CodeType;
import com.stock.analysis.dto.request.CodeRequestDto;
import com.stock.analysis.dto.response.CodeResponseDto;
import com.stock.analysis.dto.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/{codeId}")
    public Response<CodeResponseDto> get(@PathVariable(value = "codeId") Long codeId) {
        CodeResponseDto responseDto = codeService.getCode(codeId);
        return Response.success(responseDto);
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
    public Response<Void> delete(@PathVariable("codeId") Long codeId) {
        codeService.deleteCode(codeId);
        return Response.success();
    }
}
