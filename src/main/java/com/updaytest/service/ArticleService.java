package com.updaytest.service;

import com.updaytest.exception.NoExistingArticleException;
import com.updaytest.model.Article;
import com.updaytest.model.Author;
import com.updaytest.repo.ArticleRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleService {

    private ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Article save(Article article){
        return articleRepository.save(article);
    }

    public Article update(Article article) throws NoExistingArticleException {
        if(getById(article.getId())!=null){
            return articleRepository.update(article);
        }
        throw new NoExistingArticleException();
    }

    public Article getById(Long id) throws NoExistingArticleException {
        Article found = articleRepository.findById(id);
        if(found!=null){
            return found;
        }
        throw new NoExistingArticleException();
    }

    public List<Article> searchByKeywordAndAuthorName(String keyword, String author){
        if(author.equals("")){
            return articleRepository.findByKeyword(keyword);
        }
        List<Article> filteredByAuthors = new ArrayList<>();
        for(Article article : articleRepository.findByKeyword(keyword)){
            for(Author aut : article.getAuthors()){
                if(aut.getName().contains(author)){
                    filteredByAuthors.add(article);
                }
            }
        }
        return filteredByAuthors;
    }

    public List<Article> searchBetweenDates(LocalDate start, LocalDate end){
        return articleRepository.findBetweenDates(start, end);
    }

}
