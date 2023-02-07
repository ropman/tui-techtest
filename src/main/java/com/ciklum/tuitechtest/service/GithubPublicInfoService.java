package com.ciklum.tuitechtest.service;

import com.ciklum.tuitechtest.client.GithubClient;
import com.ciklum.tuitechtest.client.resource.BranchResource;
import com.ciklum.tuitechtest.client.resource.RepositoryResource;
import com.ciklum.tuitechtest.dto.BranchInfoDto;
import com.ciklum.tuitechtest.dto.RepositoryInfoDto;
import com.ciklum.tuitechtest.exception.FetchingRepositoryException;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@AllArgsConstructor
@Log4j2
public class GithubPublicInfoService {

    private static final String FETCH_REPOSITORY_ENDPOINT_SUFFIX = "/repos";
    private GithubClient githubClient;

    public Collection<RepositoryInfoDto> getRepositoriesWithBranchesFor(String username) {
        List<RepositoryInfoDto> repositoryInfoDtos = new ArrayList<>();
        try {
            List<RepositoryResource> publicRepositories = githubClient.getPublicRepositories(username);
            publicRepositories.stream()
                    .peek(repositoryResource -> log.info("repository name:{}, isFork:{}",
                            repositoryResource.getName(), repositoryResource.isFork()))
                    .filter(RepositoryResource::isNotFork)
                    .forEach(repo -> {
                        List<BranchResource> branches = githubClient.getBranches(username, repo.getName());
                        repositoryInfoDtos.add(GithubPublicInfoService.mapFrom(repo, branches));
                    });
        } catch (WebClientResponseException ex) {
            if(ex.getStatusCode().equals(NOT_FOUND) && ex.getMessage().endsWith(FETCH_REPOSITORY_ENDPOINT_SUFFIX)) {
                throw new FetchingRepositoryException(username);
            }
        }

        return repositoryInfoDtos;
    }

    private static RepositoryInfoDto mapFrom(RepositoryResource repository, List<BranchResource> branchesForRepository) {
        return new RepositoryInfoDto(
                repository.getName(),
                repository.getOwner().getLogin(),
                branchesForRepository.stream()
                        .map(b -> new BranchInfoDto(b.getName(), b.getCommit().getSha()))
                        .collect(Collectors.toSet())
        );
    }

}
