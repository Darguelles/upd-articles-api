package com.updaytest.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

@Data
public class Article {

    private Long id;
    private String header;
    private String description;
    private String text;

    @JsonFormat(shape = STRING, pattern = "dd-MM-yyyy")
    private LocalDate publishDate;
    private List<Author> authors;
    private List<String> keywords;

}
