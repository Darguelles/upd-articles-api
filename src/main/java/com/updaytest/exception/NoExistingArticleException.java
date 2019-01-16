package com.updaytest.exception;

public class NoExistingArticleException extends Exception {

    public NoExistingArticleException() {
        super("You are trying to get an article that does not match with your search criteria");
    }
}
