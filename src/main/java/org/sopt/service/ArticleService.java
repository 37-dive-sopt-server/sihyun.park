package org.sopt.service;

import org.sopt.domain.Article;
import org.sopt.dto.request.ArticleCreateRequestDto;

import java.util.List;

public interface ArticleService {
    Long createArticle(ArticleCreateRequestDto request);
    Article getArticleById(Long id);
    List<Article> getAllArticles();
}
