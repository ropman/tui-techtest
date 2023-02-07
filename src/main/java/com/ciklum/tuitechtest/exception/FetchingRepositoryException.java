package com.ciklum.tuitechtest.exception;

public class FetchingRepositoryException extends RuntimeException {

    private static final long serialVersionUID = 2L;

    private static final String REPOSITORIES_NOT_FOUND_MESSAGE = "Repositories are not available for %s";

    public FetchingRepositoryException(String username) {
        super(String.format(REPOSITORIES_NOT_FOUND_MESSAGE, username));
    }
}
