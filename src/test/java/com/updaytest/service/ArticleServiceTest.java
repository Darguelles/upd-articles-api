package com.updaytest.service;

import com.updaytest.exception.NoExistingArticleException;
import com.updaytest.model.Article;
import com.updaytest.repo.ArticleRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static com.updaytest.mock.ArticleMock.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class ArticleServiceTest {

    private static final String NEW_ARTICLE_DESCRIPTION = "New description";
    private static final String EMPTY_PARAM = "";

    @Mock
    private ArticleRepository repository;

    private ArticleService service;

    @Before
    public void setUp() {
        service = new ArticleService(repository);
    }

    @Test
    public void shouldSaveNewArticleAndReturnGeneratedId() {
        Article articleToSave = mock();
        Article savedArticle = mockSaved();
        when(repository.save(articleToSave)).thenReturn(savedArticle);

        Article saved = service.save(articleToSave);

        assertThat(saved.getId(), is(DEFAULT_ID));
    }

    @Test
    public void shouldUpdateAnExistingArticle() throws NoExistingArticleException {
        Article savedArticle = mockSaved();
        Article modifiedArticle = savedArticle;
        modifiedArticle.setDescription(NEW_ARTICLE_DESCRIPTION);
        when(repository.findById(DEFAULT_ID)).thenReturn(savedArticle);
        when(repository.update(savedArticle)).thenReturn(modifiedArticle);

        savedArticle.setDescription(NEW_ARTICLE_DESCRIPTION);

        Article updated = service.update(savedArticle);

        assertThat(updated.getDescription(), is(NEW_ARTICLE_DESCRIPTION));
    }

    @Test(expected = NoExistingArticleException.class)
    public void shouldThrowNoExistingArticleExceptionWhenArticleDoesNotExists() throws NoExistingArticleException {
        Article noExistingArticle = mock();
        when(repository.findById(DEFAULT_ID)).thenReturn(null);

        service.update(noExistingArticle);
    }

    @Test
    public void shouldRetrieveAnExistingArticleById() throws NoExistingArticleException {
        Article savedArticle = mockSaved();
        when(repository.findById(DEFAULT_ID)).thenReturn(savedArticle);

        Article found = service.getById(DEFAULT_ID);

        assertThat(found.getId(), is(DEFAULT_ID));
        assertThat(found.getDescription(), is(DEFAULT_DESCRIPTION));
    }


    @Test(expected = NoExistingArticleException.class)
    public void shouldThrowNoExistingArticleExceptionWhenRetrievingAnUnknownArticle() throws NoExistingArticleException {
        Long unknownId = 2L;
        when(repository.findById(unknownId)).thenReturn(null);

        service.getById(unknownId);
    }


    @Test
    public void shouldRetrieveArticlesOfASingleAuthor() {
        when(repository.findByKeyword(EMPTY_PARAM)).thenReturn(mockList());

        List<Article> articlesByAuthor = service.searchByKeywordAndAuthorName(EMPTY_PARAM, "Erikson");

        assertThat(articlesByAuthor.size(), is(1));
    }

    @Test
    public void shouldRetrieveAnEmptyListIfKeywordAndAuthorAreEmpty() {
        when(repository.findByKeyword(EMPTY_PARAM)).thenReturn(new ArrayList<>());

        List<Article> articles = service.searchByKeywordAndAuthorName(EMPTY_PARAM, EMPTY_PARAM);

        assertThat(articles.size(), is(0));
    }
}
