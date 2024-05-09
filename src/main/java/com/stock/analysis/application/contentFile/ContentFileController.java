package com.stock.analysis.application.contentFile;

import com.stock.analysis.application.contentFile.dto.ContentFileRequestDto;
import com.stock.analysis.application.contentFile.dto.ContentFileResponseDto;
import com.stock.analysis.application.contentFile.service.ContentFileService;
import com.stock.analysis.dto.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/contentFiles")
@RequiredArgsConstructor
public class ContentFileController {

    private final ContentFileService contentFileService;

    @PostMapping("/upload")
    public Response<List<ContentFileResponseDto>> saveContentFile(
            @RequestPart(value = "requestDto") ContentFileRequestDto requestDto,
            @RequestPart(value = "attachments") MultipartFile attachment
    ) {
        contentFileService.saveContentFile(attachment, requestDto.getContentId(), requestDto.getUploadType());
        List<ContentFileResponseDto> savedAttachments = contentFileService.selectContentFilesByContentIdAndUploadType(requestDto);
        return Response.success(savedAttachments);
    }

    @DeleteMapping("/{id}")
    public Response<Void> deleteContentFile(@PathVariable("id") Long id) {
        contentFileService.deleteContentFile(id);
        return Response.success();
    }

    @PostMapping
    public Response<List<ContentFileResponseDto>> selectContentFiles(@RequestBody ContentFileRequestDto requestDto) {
        List<ContentFileResponseDto> files = contentFileService.selectContentFilesByContentIdAndUploadType(requestDto);
        return Response.success(files);
    }

    @GetMapping("/{id}/download")
    public ResponseEntity<Resource> downloadFile(@PathVariable("id") Long id) {
        Resource resource = contentFileService.downloadFile(id);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment: filename=" + resource.getFilename());
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);

        return ResponseEntity.ok().headers(headers).body(resource);
    }
}
