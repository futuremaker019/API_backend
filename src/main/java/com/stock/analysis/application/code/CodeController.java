package com.stock.analysis.application.code;

import com.stock.analysis.application.code.service.CodeService;
import com.stock.analysis.domain.contant.CodeType;
import com.stock.analysis.dto.request.CodeRequestDto;
import com.stock.analysis.dto.response.CodeResponseDto;
import com.stock.analysis.dto.response.Response;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Code Api", description = "Code Application Api")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "성공", content = {@Content(schema = @Schema(implementation = CodeResponseDto.class))}),
        @ApiResponse(responseCode = "404", description = "코드가 존재하지 않습니다."),
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

    @GetMapping("/{parentId}/parentId")
    @Parameter(name = "parentId", description = "")
    public Response<List<CodeResponseDto>> selectCodesByParentId(@PathVariable(value = "parentId") Long parentId) {
        return Response.success(codeService.selectCodesById(parentId));
    }

    @GetMapping("/{id}/id")
    @Parameter(name = "id", description = "")
    public Response<List<CodeResponseDto>> selectCodesById(@PathVariable(value = "id") Long id) {
        return Response.success(codeService.selectCodesById(id));
    }

    @GetMapping("/{parentId}/one")
    @Parameter(name = "parentId", description = "")
    public Response<CodeResponseDto> getCodeById(@PathVariable(value = "parentId") Long parentId) {
        return Response.success(codeService.getCodeById(parentId));
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
