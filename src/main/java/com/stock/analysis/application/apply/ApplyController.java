package com.stock.analysis.application.apply;

import com.stock.analysis.application.apply.service.ApplyService;
import com.stock.analysis.application.upload.service.UploadService;
import com.stock.analysis.domain.contant.UploadType;
import com.stock.analysis.domain.entity.Apply;
import com.stock.analysis.domain.entity.UserAccount;
import com.stock.analysis.application.apply.dto.ApplyRequestDto;
import com.stock.analysis.application.apply.dto.ApplyResponseDto;
import com.stock.analysis.application.apply.dto.SearchApplyDto;
import com.stock.analysis.dto.response.Response;
import com.stock.analysis.dto.security.CurrentUser;
import com.stock.analysis.application.upload.dto.ApplyUploadDto;
import com.stock.analysis.exception.ApplyAppException;
import com.stock.analysis.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/applies")
public class ApplyController {

    private final ApplyService applyService;
    private final UploadService uploadService;

    @PostMapping
    public Response<Page<ApplyResponseDto>> selectApplies(
            @RequestBody SearchApplyDto searchApplyDto,
            @PageableDefault(size = 10, page = 0, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
            @CurrentUser UserAccount userAccount
    ) {
        return Response.success(applyService.selectApplies(searchApplyDto, pageable, userAccount));
    }

    @GetMapping("{applyId}")
    public Response<ApplyResponseDto> getApply(@PathVariable("applyId") Long applyId) {
        return Response.success(applyService.getApplyById(applyId));
    }

    @PostMapping("/create")
    public Response<Void> createApply(
            @Valid @RequestPart(value = "requestDto") ApplyRequestDto responseDto,
            @RequestPart(value = "attachments", required = false) List<MultipartFile> attachments,
            @CurrentUser UserAccount userAccount, Errors errors
    ) {
        if (errors.hasErrors()) {
            String defaultMessage = Objects.requireNonNull(errors.getFieldError()).getDefaultMessage();
            System.out.println("errors = " + defaultMessage);
            throw new ApplyAppException(ErrorCode.HAS_NO_ARGUMENTS, defaultMessage);
        }

        Apply apply = applyService.createApply(responseDto, userAccount);
        if (attachments != null) {
            uploadService.saveUploads(
                    ApplyUploadDto.builder().apply(apply).build(),
                    attachments,
                    UploadType.APPLY
            );
        }
        return Response.success();
    }

    @DeleteMapping("{applyId}")
    public Response<Void> deleteApply(@PathVariable("applyId") String applyId) {
        return Response.success();
    }

}
