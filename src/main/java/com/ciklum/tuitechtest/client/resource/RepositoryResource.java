package com.ciklum.tuitechtest.client.resource;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RepositoryResource {
    private String name;
    @JsonAlias("branches_url")
    private String branchesUrl;
    private boolean fork;
    private OwnerResource owner;

    public boolean isNotFork() {
        return !isFork();
    }
}
