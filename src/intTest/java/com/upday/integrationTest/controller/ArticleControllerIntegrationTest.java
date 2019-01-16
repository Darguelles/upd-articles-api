package com.upday.integrationTest.controller;

import com.updaytest.UpdArticlesApiApplication;
import com.updaytest.mock.ArticleMock;
import com.updaytest.model.Article;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.Arrays;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = UpdArticlesApiApplication.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class ArticleControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Before
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    public void shouldReturnOKWhenNewArticleIsCreated() {
        Article article = ArticleMock.mock();

        given()
                .contentType(ContentType.JSON)
                .body(article)
        .when()
                .post("/articles")
        .then()
                .assertThat()
                .statusCode(is(CREATED.value()));
    }

    @Test
    public void shouldReturnBadRequestWhenTryingToPostAnUncompletedArticle() {
        Article incompleteArticle = Article.builder()
                .description("Small description")
                .keywords(Arrays.asList("terror", "comedy"))
                .publishDate(LocalDate.now())
                .build();

        given()
                .contentType(ContentType.JSON)
                .body(incompleteArticle)
        .when()
                .post("/articles")
        .then()
                .assertThat()
                .statusCode(is(BAD_REQUEST.value()));
    }
}
