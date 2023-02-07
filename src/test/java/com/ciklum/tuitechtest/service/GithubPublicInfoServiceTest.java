package com.ciklum.tuitechtest.service;

import com.ciklum.tuitechtest.client.GithubClient;
import com.ciklum.tuitechtest.client.resource.BranchResource;
import com.ciklum.tuitechtest.client.resource.RepositoryResource;
import com.ciklum.tuitechtest.config.ObjectMapperCustomizer;
import com.ciklum.tuitechtest.dto.BranchInfoDto;
import com.ciklum.tuitechtest.dto.RepositoryInfoDto;
import com.ciklum.tuitechtest.exception.FetchingRepositoryException;
import com.ciklum.tuitechtest.helper.DataProvider;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;
import java.util.stream.Collectors;

import static com.ciklum.tuitechtest.helper.DataProvider.REPOSITORY_02_NAME;
import static com.ciklum.tuitechtest.helper.DataProvider.REPOSITORY_00_OWNER_NAME;
import static com.ciklum.tuitechtest.helper.DataProvider.USERNAME;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.NOT_FOUND;


@ExtendWith(MockitoExtension.class)
@Log4j2
class GithubPublicInfoServiceTest {

    @Mock
    private GithubClient mockGithubClient;

    private GithubPublicInfoService githubPublicInfoService;

    private ObjectMapper objectMapper = new ObjectMapperCustomizer().objectMapper();

    @BeforeEach
    void setUp() {
        this.githubPublicInfoService = new GithubPublicInfoService(mockGithubClient);
    }

    @Test
    void shouldFilterOutRepositoriesWhichAreForked() throws JsonProcessingException {
        List<RepositoryResource> repositoryResources = objectMapper.readValue(DataProvider.getGithubRepositoriesRequestBodyForUserRopman(), new TypeReference<>() { });
        List<BranchResource> testBranchResources_02 = objectMapper.readValue(DataProvider.getGithubBranchesForTestRepository(), new TypeReference<>() { });

        when(mockGithubClient.getPublicRepositories(USERNAME)).thenReturn(repositoryResources);
        when(mockGithubClient.getBranches(USERNAME, DataProvider.REPOSITORY_02_NAME)).thenReturn(testBranchResources_02);

        List<RepositoryInfoDto> repositoriesWithBranchesFor = githubPublicInfoService.getRepositoriesWithBranchesFor(USERNAME).stream().toList();
        assertAll("filtered response by is forked",
                () -> assertThat(repositoriesWithBranchesFor).hasSize(1),
                () -> assertThat(repositoriesWithBranchesFor.get(0).repositoryName()).isEqualTo(REPOSITORY_02_NAME),
                () -> assertThat(repositoriesWithBranchesFor.get(0).ownerLogin()).isEqualTo(REPOSITORY_00_OWNER_NAME),
                () -> assertThat(repositoriesWithBranchesFor.get(0).branchInfo()).hasSize(testBranchResources_02.size()),
                () -> assertThat(repositoriesWithBranchesFor.get(0).branchInfo()).hasSameElementsAs(testBranchResources_02.stream().map(br -> new BranchInfoDto(br.getName(),br.getCommit().getSha())).collect(Collectors.toSet()))
        );
    }

    @Test
    void shouldReturnEmptyCollectionIfUserDoesNotHaveRepositories() {

        when(mockGithubClient.getPublicRepositories(USERNAME)).thenReturn(List.of());

        List<RepositoryInfoDto> repositoriesWithBranchesFor = githubPublicInfoService.getRepositoriesWithBranchesFor(USERNAME).stream().toList();
        assertAll("should return empty list",
                () -> assertThat(repositoriesWithBranchesFor).isEmpty()
        );
    }

    @Test
    void shouldThrowFetchingRepositoryExceptionWhenUserNorExist() {
        WebClientResponseException expectedWebClientExceptionWhenUserIsNonExistWithForgedMessagePart = WebClientResponseException.create(NOT_FOUND.value(), "Not Found" + "/api/../repos", null, null, null);
        when(mockGithubClient.getPublicRepositories(USERNAME)).thenThrow(expectedWebClientExceptionWhenUserIsNonExistWithForgedMessagePart);
        FetchingRepositoryException fetchingRepositoryException = assertThrows(FetchingRepositoryException.class, () -> githubPublicInfoService.getRepositoriesWithBranchesFor(USERNAME));
        assertThat(fetchingRepositoryException.getMessage()).isEqualTo("Repositories are not available for " + USERNAME);
    }

    // Not sure if this is a possible situation, so won't cover with test at the moment. - the code should handle it and add an empty collection there.
    // void shouldReturnEmptyBranchInfoCollectionIfUserRepositoryDoesNotHaveAnyBranch() {}
}