package com.updaytest.mock;

import com.updaytest.model.Article;
import com.updaytest.model.Author;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static java.time.LocalDate.parse;
import static java.util.Arrays.asList;

public class ArticleMock {

    public static final Long DEFAULT_ID = 1L;
    public static final String DEFAULT_HEADER = "Title";
    public static final String DEFAULT_DESCRIPTION = "Descriptiopn....";
    public static final String DEFAULT_TEXT = "default content";
    static DateTimeFormatter dft = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    public static final LocalDate DEFAULT_PUBLISHED_DATE = parse("15-01-2019", dft);
    public static final List<Author> DEFAULT_AUTHOR_LIST = asList(Author.builder().name("Diego").genre("Male").build());
    public static final List<String> DEFAULT_KEYWORDS_LIST = asList("Terror", "SciFi", "Horror");

    public static Article mock() {
        return Article.builder()
                .header(DEFAULT_HEADER)
                .description(DEFAULT_DESCRIPTION)
                .text(DEFAULT_TEXT)
                .publishDate(DEFAULT_PUBLISHED_DATE)
                .authors(DEFAULT_AUTHOR_LIST)
                .keywords(DEFAULT_KEYWORDS_LIST)
                .build();
    }

    public static List<Article> mockList() {
        Article article1 = Article.builder()
                .id(DEFAULT_ID)
                .header(DEFAULT_HEADER + " II")
                .description(DEFAULT_DESCRIPTION)
                .text(DEFAULT_TEXT)
                .publishDate(DEFAULT_PUBLISHED_DATE)
                .authors(DEFAULT_AUTHOR_LIST)
                .keywords(DEFAULT_KEYWORDS_LIST)
                .build();
        Article article2 = Article.builder()
                .id(DEFAULT_ID + 1)
                .header(DEFAULT_HEADER + " III")
                .description(DEFAULT_DESCRIPTION)
                .text(DEFAULT_TEXT)
                .publishDate(DEFAULT_PUBLISHED_DATE.plusMonths(2))
                .authors(DEFAULT_AUTHOR_LIST)
                .keywords(DEFAULT_KEYWORDS_LIST)
                .build();
        Article article3 = Article.builder()
                .id(DEFAULT_ID + 2)
                .header(DEFAULT_HEADER + " IV")
                .description(DEFAULT_DESCRIPTION)
                .text(DEFAULT_TEXT)
                .publishDate(DEFAULT_PUBLISHED_DATE.plusMonths(3))
                .authors(DEFAULT_AUTHOR_LIST)
                .keywords(DEFAULT_KEYWORDS_LIST)
                .build();
        Article article4 = Article.builder()
                .id(DEFAULT_ID + 3)
                .header(DEFAULT_HEADER + " V")
                .description(DEFAULT_DESCRIPTION)
                .text(DEFAULT_TEXT)
                .publishDate(DEFAULT_PUBLISHED_DATE.plusMonths(4))
                .authors(DEFAULT_AUTHOR_LIST)
                .keywords(DEFAULT_KEYWORDS_LIST)
                .build();
        Article article5 = Article.builder()
                .id(DEFAULT_ID + 4)
                .header(DEFAULT_HEADER + " XX")
                .description(DEFAULT_DESCRIPTION)
                .text(DEFAULT_TEXT)
                .publishDate(DEFAULT_PUBLISHED_DATE.plusMonths(5))
                .authors(asList(Author.builder().name("Erikson").build()))
                .keywords(DEFAULT_KEYWORDS_LIST)
                .build();

        return asList(article1, article2, article3, article4, article5);
    }

    public static Article mockSaved() {
        Article saved = mock();
        saved.setId(DEFAULT_ID);
        return saved;
    }

}
