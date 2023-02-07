package com.ciklum.tuitechtest.exception;

public class FetchingBranchException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private static final String BRANCHES_NOT_FOUND_MESSAGE = "Branches are not available based on username:%s and repositoryName: %s";

    public FetchingBranchException(String username, String repositoryName) {
        super(String.format(BRANCHES_NOT_FOUND_MESSAGE, username, repositoryName));
    }
}
