package com.updaytest.service;

import com.updaytest.exception.ArticleProcessingException;
import com.updaytest.exception.NoExistingArticleException;
import com.updaytest.model.Article;
import com.updaytest.model.Author;
import com.updaytest.repo.ArticleRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.ofNullable;

@Service
public class ArticleService {

    private ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Article save(Article article) throws ArticleProcessingException {
        Optional<Article> saved = ofNullable(articleRepository.save(article));
        if(saved.isPresent()){
            return saved.get();
        }
        throw new ArticleProcessingException();
    }

    public Article update(Article article) throws NoExistingArticleException, ArticleProcessingException {
        if (getById(article.getId()) != null) {
            Optional<Article> updated = ofNullable(articleRepository.update(article));
            if(updated.isPresent()){
                return updated.get();
            }
            throw new ArticleProcessingException();
        }
        throw new NoExistingArticleException();
    }

    public Article getById(Long id) throws NoExistingArticleException {
        Optional<Article> found = ofNullable(articleRepository.findById(id));

        if (found.isPresent()) {
            return found.get();
        }
        throw new NoExistingArticleException();
    }

    public List<Article> searchByKeywordAndAuthorName(String keyword, String author) throws NoExistingArticleException {

        if (keyword.isEmpty() && author.isEmpty()) {
            return new ArrayList<>();
        }
        if (author.isEmpty()) { //Search by keyword
            Optional<List<Article>> foundArticles = ofNullable(articleRepository.findByKeyword(keyword));
            if (foundArticles.isPresent()) {
                return foundArticles.get();
            }
            throw new NoExistingArticleException();
        }
        //Search by both values
        Optional<List<Article>> foundArticles = ofNullable(articleRepository.findByKeyword(keyword));
        if (foundArticles.isPresent()) {
            return filterByAuthorName(foundArticles.get(), author);
        }
        throw new NoExistingArticleException();
    }

    private List<Article> filterByAuthorName(List<Article> articles, String author){
        List<Article> result = new ArrayList<>();
        for (Article article : articles) {
            for (Author aut : article.getAuthors()) {
                if (aut.getName().contains(author)) {
                    result.add(article);
                }
            }
        }
        return result;
    }

    public List<Article> searchBetweenDates(LocalDate start, LocalDate end) throws NoExistingArticleException {

        Optional<List<Article>> foundArticles = ofNullable(articleRepository.findBetweenDates(start, end));
        if (foundArticles.isPresent()) {
            return foundArticles.get();
        }
        throw new NoExistingArticleException();
    }

}
