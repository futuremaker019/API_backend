package com.stock.analysis.application.apply;

import com.stock.analysis.application.upload.service.UploadService;
import com.stock.analysis.domain.contant.UploadType;
import com.stock.analysis.domain.entity.Apply;
import com.stock.analysis.domain.entity.UserAccount;
import com.stock.analysis.dto.request.ApplyRequestDto;
import com.stock.analysis.dto.response.ApplyResponseDto;
import com.stock.analysis.dto.common.SearchDto;
import com.stock.analysis.application.apply.service.ApplyService;
import com.stock.analysis.dto.response.Response;
import com.stock.analysis.dto.security.CurrentUser;
import com.stock.analysis.dto.upload.ApplyUploadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.print.Pageable;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/applies")
public class ApplyController {

    private final ApplyService applyService;
    private final UploadService uploadService;

    /**
     * 파라미터로 받아야할 조건
     *  검색, orderBy
     */
    @GetMapping
    public Response<List<ApplyResponseDto>> selectApplies(
            SearchDto searchDto, Pageable pageable
    ) {
        return Response.success(applyService.selectApplies(searchDto, pageable));
    }

    @GetMapping("{applyId}")
    public Response<ApplyResponseDto> getApply(@PathVariable("applyId") String applyId) {
        return Response.success();
    }

    @PostMapping
    public Response<Void> createApply(
            @RequestPart ApplyRequestDto responseDto,
            @RequestPart(required = false) List<MultipartFile> attachments,
            @CurrentUser UserAccount userAccount
    ) {
        Apply apply = applyService.createApply(responseDto, userAccount);
        uploadService.saveUploads(
                ApplyUploadDto.builder().apply(apply).build(),
                attachments,
                UploadType.APPLY
        );
        return Response.success();
    }

    @DeleteMapping("{applyId}")
    public Response<Void> deleteApply(@PathVariable("applyId") String applyId) {
        return Response.success();
    }

}
