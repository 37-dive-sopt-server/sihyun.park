package org.sopt.repository;

import org.sopt.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    Optional<Article> findByTitle(String title);
}
