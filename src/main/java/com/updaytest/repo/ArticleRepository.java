package com.updaytest.repo;

import com.updaytest.model.Article;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

@Repository
public class ArticleRepository {

    private Map<Long, Article> repository = new HashMap<>();
    private static long counter;

    public Article save(Article article) {
        counter++;
        article.setId(counter);
        repository.put(counter, article);
        return article;
    }

    public Article update(Article article) {
        repository.put(article.getId(), article);
        return article;
    }

    public Article findById(Long id) {
        return repository.getOrDefault(id, null);
    }

    public List<Article> findByKeyword(String keyword) {
        if (keyword.equals("")) {
            return new ArrayList<>();
        }
        List<Article> articles = repository.values().parallelStream()
                .filter(article -> article.getKeywords().contains(keyword))
                .collect(toList());

        return articles;
    }

    public List<Article> findBetweenDates(LocalDate start, LocalDate end) {
        return repository.values().parallelStream()
                .filter(article -> article.getPublishDate().isAfter(start) || article.getPublishDate().isEqual(start))
                .filter(article -> article.getPublishDate().isBefore(end) || article.getPublishDate().isEqual(end))
                .collect(toList());
    }
}
