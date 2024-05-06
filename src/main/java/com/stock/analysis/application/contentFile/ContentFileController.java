package com.stock.analysis.application.contentFile;

import com.stock.analysis.application.contentFile.dto.ContentFileRequestDto;
import com.stock.analysis.application.contentFile.service.ContentFileService;
import com.stock.analysis.dto.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/contentFiles")
@RequiredArgsConstructor
public class ContentFileController {

    private final ContentFileService contentFileService;

    @PostMapping("/upload")
    public Response<Void> saveContentFile(
            @RequestPart ContentFileRequestDto requestDto,
            @RequestPart MultipartFile attachment
    ) {
        contentFileService.saveContentFile(attachment, requestDto.getContentId(), requestDto.getUploadType());
        return Response.success();
    }

    @DeleteMapping("/{id}")
    public Response<Void> deleteContentFile(@PathVariable("id") Long id) {
        contentFileService.deleteContentFile(id);
        return Response.success();
    }
}
