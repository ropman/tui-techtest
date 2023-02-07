package com.ciklum.tuitechtest.client;

import com.ciklum.tuitechtest.client.resource.BranchResource;
import com.ciklum.tuitechtest.client.resource.RepositoryResource;
import com.ciklum.tuitechtest.config.GithubClientConfig;
import com.ciklum.tuitechtest.config.RestConfig;
import com.ciklum.tuitechtest.exception.FetchingBranchException;
import com.ciklum.tuitechtest.exception.FetchingRepositoryException;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

@Component
@Log4j2
public class GithubClient {
    public static final String ACCEPT_HEADER = "application/vnd.github+json";
    private static final Long TIME_OUT_MULTIPLIER = 2L;

    private final WebClient webClient;
    private final RestConfig restConfig;
    private final GithubClientConfig githubClientConfig;

    public GithubClient(GithubClientConfig githubClientConfig,
                        RestConfig restConfig,
                        WebClient webClient) {
        this.githubClientConfig = githubClientConfig;
        this.restConfig = restConfig;
        this.webClient = webClient;
    }

    public List<RepositoryResource> getPublicRepositories(String username) {
        log.debug("getPublicRepositories called with: {}", username);
        return Optional.ofNullable(webClient
                        .get()
                        .uri(builder -> builder.path(githubClientConfig.getFetchPublicRepositoriesUrl()).build(username))
                        .headers(h -> h.setAccept(List.of(MediaType.valueOf(ACCEPT_HEADER))))
                        .retrieve()
                        .bodyToMono(new ParameterizedTypeReference<List<RepositoryResource>>() {})
                        .timeout(Duration.ofMillis(restConfig.getReadTimeout() * TIME_OUT_MULTIPLIER))
                        .doOnError(error -> log.error("Error getting repositories from github", error))
                        .block())
                .orElseThrow(() -> new FetchingRepositoryException(username));
    }

    public List<BranchResource> getBranches(String username, String repositoryName) {
        log.debug("getBranches called with: {}, {}", username, repositoryName);
        return Optional.ofNullable(webClient
                        .get()
                        .uri(builder -> builder.path(githubClientConfig.getFetchBranchesUrl()).build(username, repositoryName))
                        .headers(h -> h.setAccept(List.of(MediaType.valueOf(ACCEPT_HEADER))))
                        .retrieve()
                        .bodyToMono(new ParameterizedTypeReference<List<BranchResource>>() {})
                        .timeout(Duration.ofMillis(restConfig.getReadTimeout() * TIME_OUT_MULTIPLIER))
                        .doOnError(error -> log.error("Error getting branches from github client", error))
                        .block())
                .orElseThrow(() -> new FetchingBranchException(username, repositoryName));
    }
}
