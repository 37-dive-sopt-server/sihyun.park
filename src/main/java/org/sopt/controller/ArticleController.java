package org.sopt.controller;

import jakarta.validation.Valid;
import org.sopt.domain.Article;
import org.sopt.dto.request.ArticleCreateRequestDto;
import org.sopt.dto.response.ArticleResponseDto;
import org.sopt.global.response.GlobalBaseResponse;
import org.sopt.service.ArticleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping
    public GlobalBaseResponse<ArticleResponseDto> createArticle(
            @Valid @RequestBody ArticleCreateRequestDto requestDto) {
        Long articleId = articleService.createArticle(requestDto);
        Article article = articleService.getArticleById(articleId);
        return GlobalBaseResponse.ok(ArticleResponseDto.from(article));
    }


    @GetMapping("/{id}")
    public GlobalBaseResponse<ArticleResponseDto> getArticleById(@PathVariable Long id) {
        Article article = articleService.getArticleById(id);
        return GlobalBaseResponse.ok(ArticleResponseDto.from(article));
    }

    @GetMapping
    public GlobalBaseResponse<List<ArticleResponseDto>> getAllArticles() {
        List<ArticleResponseDto> articles = articleService.getAllArticles()
                .stream()
                .map(ArticleResponseDto::from)
                .collect(Collectors.toList());
        return GlobalBaseResponse.ok(articles);
    }
}
