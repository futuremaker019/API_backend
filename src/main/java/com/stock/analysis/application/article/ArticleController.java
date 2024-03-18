package com.stock.analysis.application.article;

import com.stock.analysis.domain.contant.UploadType;
import com.stock.analysis.domain.entity.Article;
import com.stock.analysis.dto.ArticleDto;
import com.stock.analysis.dto.ArticleWithCommentsDto;
import com.stock.analysis.dto.request.ArticleRequestDto;
import com.stock.analysis.dto.security.UserPrincipal;
import com.stock.analysis.dto.upload.ArticleUploadDto;
import com.stock.analysis.application.article.service.ArticleService;
import com.stock.analysis.application.upload.service.UploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/articles")
public class ArticleController {

    private final ArticleService articleService;
    private final UploadService uploadService;

    @GetMapping
    public ResponseEntity<Page<ArticleDto>> getArticles(
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        Page<ArticleDto> articles = articleService.searchArticles(null, null, pageable);
        return ResponseEntity.ok().body(articles);
    }

    @GetMapping("/{articleId}")
    public ResponseEntity<ArticleWithCommentsDto> getArticle(@PathVariable Long articleId) {
        ArticleWithCommentsDto dto = articleService.getArticle(articleId);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping( "/create")
    public ResponseEntity<Long> saveArticle(
            @RequestPart(value = "dto") ArticleRequestDto dto,
            @RequestPart(value = "attachments", required = false) List<MultipartFile> attachments,
            @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {
        Article savedArticle = articleService.saveArticle(dto.toDto(userPrincipal.toDto()));
        uploadService.saveUploads(
                ArticleUploadDto.builder().article(savedArticle).build(),
                attachments, UploadType.ARTICLE);
        return ResponseEntity.ok().body(savedArticle.getId());
    }

    @PutMapping("/{articleId}")
    public ResponseEntity<Boolean> updateArticle(
            @RequestBody ArticleRequestDto dto,
            @PathVariable Long articleId,
            @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {

        articleService.updateArticle(articleId, dto.toDto(userPrincipal.toDto()));

        return ResponseEntity.ok().body(true);
    }
}
