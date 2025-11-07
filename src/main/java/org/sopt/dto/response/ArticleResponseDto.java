package org.sopt.dto.response;

import org.sopt.domain.Article;
import org.sopt.domain.Tag;
import java.time.LocalDateTime;

public record ArticleResponseDto(
        Long id,
        Long memberId,
        String memberName,
        Tag tag,
        String title,
        String content,
        LocalDateTime createdTime
) {
    public static ArticleResponseDto from(Article article) {
        return new ArticleResponseDto(
                article.getId(),
                article.getMember().getId(),
                article.getMember().getName(),
                article.getTag(),
                article.getTitle(),
                article.getContent(),
                article.getCreatedTime()
        );
    }
}
