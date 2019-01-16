package com.updaytest.rest;

import com.updaytest.exception.ArticleProcessingException;
import com.updaytest.exception.NoExistingArticleException;
import com.updaytest.model.Article;
import com.updaytest.service.ArticleService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import static java.time.LocalDate.*;
import static java.time.format.DateTimeFormatter.ofPattern;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    private ArticleService service;

    public ArticleController(ArticleService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Article> save(@RequestBody @Valid Article article) throws ArticleProcessingException {
        Article saved = service.save(article);
        return new ResponseEntity<>(saved, prepareHeader(saved.getId()), CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Article> update(@RequestParam Long id, @RequestBody Article article) throws NoExistingArticleException, ArticleProcessingException {
        article.setId(id);
        Article updated = service.update(article);
        return new ResponseEntity<>(updated, prepareHeader(updated.getId()), OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Article> findArticle(@PathVariable Long id) throws NoExistingArticleException {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Article>> findArticlesByKeyboardAndAuthor(@RequestParam(required = false) String keyword,
                                                                         @RequestParam(required = false) String author) throws NoExistingArticleException {
        if (keyword == null && author == null) {
            return ResponseEntity.ok(new ArrayList<>());
        }

        String keyboardParam = keyword != null ? keyword : "";
        String authorParam = author != null ? author : "";

        return ResponseEntity.ok(service.searchByKeywordAndAuthorName(keyboardParam, authorParam));
    }

    @GetMapping("/created")
    public ResponseEntity<List<Article>> findArticlesByCreationDate(@RequestParam(required = false) String after,
                                                                    @RequestParam(required = false) String before) throws NoExistingArticleException {
        if (after == null && before == null) {
            return new ResponseEntity<>(NO_CONTENT);
        }
        DateTimeFormatter dtf = ofPattern("dd-MM-yyyy");
        LocalDate start = after == null ? MIN : parse(after, dtf);
        LocalDate end = before == null ? now() : parse(before, dtf);

        return ResponseEntity.ok(service.searchBetweenDates(start, end));
    }


    private HttpHeaders prepareHeader(Long id) {
        HttpHeaders responseHeaders = new HttpHeaders();
        URI uri = fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
        responseHeaders.setLocation(uri);
        return responseHeaders;
    }

    @ExceptionHandler(NoExistingArticleException.class)
    public ResponseEntity handleException() {
        return new ResponseEntity<>(NOT_FOUND);
    }

    @ExceptionHandler({ArticleProcessingException.class, DateTimeParseException.class})
    public ResponseEntity handleProcessingException() {
        return new ResponseEntity<>(BAD_REQUEST);
    }

}
