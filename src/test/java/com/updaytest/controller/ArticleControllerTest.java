package com.updaytest.controller;

import com.updaytest.exception.ArticleProcessingException;
import com.updaytest.exception.NoExistingArticleException;
import com.updaytest.model.Article;
import com.updaytest.rest.ArticleController;
import com.updaytest.service.ArticleService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import static com.updaytest.mock.ArticleMock.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.CREATED;

@RunWith(SpringJUnit4ClassRunner.class)
public class ArticleControllerTest {

    private static final String EMPTY_PARAM = "";

    @Mock
    private ArticleService service;

    private MockHttpServletRequest request;
    private ArticleController controller;

    @Before
    public void setUp() {
        controller = new ArticleController(service);
        request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
    }

    @Test
    public void shouldReturn201StatusForSavingNewArticle() throws ArticleProcessingException {
        Article articleToSave = mock();
        Article savedArticle = mockSaved();
        when(service.save(articleToSave)).thenReturn(savedArticle);

        ResponseEntity<Article> saved = controller.save(articleToSave);

        assertThat(saved.getStatusCode(), is(CREATED));
        assertThat(saved.getBody().getId(), is(DEFAULT_ID));
    }

    @Test(expected = ArticleProcessingException.class)
    public void shouldRaiseArticleProcessingExceptionWhenThereWasAnErrorSavingANewArticle() throws ArticleProcessingException {
        Article articleToSave = mock();
        when(service.save(articleToSave)).thenThrow(ArticleProcessingException.class);

        controller.save(articleToSave);
    }

    @Test
    public void shouldReturnAnEmptyListWhenPerformKeywordSearchWithoutAnyParams() throws NoExistingArticleException {

        ResponseEntity<List<Article>> found = controller.findArticlesByKeyboardAndAuthor(EMPTY_PARAM, EMPTY_PARAM);

        assertThat(found.getBody().size(), is(0));
    }

    @Test
    public void shouldReturnEmptyListWhenTryToSearchByUnknownAuthor() throws NoExistingArticleException {
        when(service.searchByKeywordAndAuthorName(EMPTY_PARAM, "Unknown")).thenReturn(new ArrayList<>());

        ResponseEntity<List<Article>> articles = controller.findArticlesByKeyboardAndAuthor(EMPTY_PARAM, "Unknown");

        assertThat(articles.getBody().size(), is(0));
    }


    @Test(expected = DateTimeParseException.class)
    public void shouldThrowDateTimeParseExceptionWhenDatesAreSentInWrongFormat() throws NoExistingArticleException {
        controller.findArticlesByCreationDate("2018/01/01","2018/01/01");
    }
}
