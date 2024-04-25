package com.stock.analysis.application.contentFile;

import com.stock.analysis.application.contentFile.dto.ContentFileRequestDto;
import com.stock.analysis.application.contentFile.repository.ContentFileRepository;
import com.stock.analysis.application.contentFile.service.ContentFileService;
import com.stock.analysis.dto.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/contentFile")
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

}
