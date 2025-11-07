package org.sopt.service;

import org.sopt.domain.Article;
import org.sopt.domain.Member;
import org.sopt.dto.request.ArticleCreateRequestDto;
import org.sopt.global.constant.ArticleErrorCode;
import org.sopt.global.constant.MemberErrorCode;
import org.sopt.global.exception.ArticleException;
import org.sopt.global.exception.MemberException;
import org.sopt.repository.ArticleRepository;
import org.sopt.repository.MemoryMemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final MemoryMemberRepository memberRepository;

    public ArticleServiceImpl(ArticleRepository articleRepository, MemoryMemberRepository memberRepository) {
        this.articleRepository = articleRepository;
        this.memberRepository = memberRepository;
    }

    @Override
    public Long createArticle(ArticleCreateRequestDto request) {
        if (articleRepository.findByTitle(request.title()).isPresent()) {
            throw new ArticleException(ArticleErrorCode.DUPLICATE_TITLE);
        }

        Member member = memberRepository.findById(request.memberId())
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND_MEMBER));

        Article article = new Article(
                request.tag(),
                request.title(),
                request.content(),
                member
        );
        articleRepository.save(article);
        return article.getId();
    }


    @Override
    public Article getArticleById(Long id) {
        return articleRepository.findById(id)
                .orElseThrow(() -> new ArticleException(ArticleErrorCode.NOT_FOUND_ARTICLE));
    }

    @Override
    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }
}
