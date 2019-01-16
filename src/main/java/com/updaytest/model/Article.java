package com.updaytest.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Article {

    private Long id;
    @NotNull
    @Size(min = 1)
    private String header;
    @NotNull
    @Size(min = 1)
    private String description;
    @NotNull
    @Size(min = 1)
    private String text;
    @NotNull
    @JsonFormat(shape = STRING, pattern = "dd-MM-yyyy")
    private LocalDate publishDate;
    @NotNull
    private List<Author> authors;
    @NotNull
    private List<String> keywords;

}
