package com.example.tboard.domain.article.article;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    public Article create(String title, String body) {
        Article article = new Article();
        article.setTitle(title);
        article.setBody(body);
        return articleRepository.save(article);
    }

    public List<Article> getArticles() {
        return articleRepository.findAll();
    }
}

