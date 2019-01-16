package com.updaytest.exception;

public class ArticleProcessingException  extends Exception{

    public ArticleProcessingException() {
        super("Error processing the incoming article.");
    }
}
