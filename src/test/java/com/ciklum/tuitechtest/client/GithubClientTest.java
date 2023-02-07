package com.ciklum.tuitechtest.client;

import com.ciklum.tuitechtest.client.resource.BranchResource;
import com.ciklum.tuitechtest.client.resource.RepositoryResource;
import com.ciklum.tuitechtest.helper.DataProvider;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;

import static com.ciklum.tuitechtest.client.GithubClient.ACCEPT_HEADER;
import static com.ciklum.tuitechtest.helper.DataProvider.REPOSITORY_00_BRANCH_00_NAME;
import static com.ciklum.tuitechtest.helper.DataProvider.REPOSITORY_00_BRANCH_00_SHA;
import static com.ciklum.tuitechtest.helper.DataProvider.REPOSITORY_00_BRANCH_01_NAME;
import static com.ciklum.tuitechtest.helper.DataProvider.REPOSITORY_00_BRANCH_01_SHA;
import static com.ciklum.tuitechtest.helper.DataProvider.REPOSITORY_00_NAME;
import static com.ciklum.tuitechtest.helper.DataProvider.REPOSITORY_00_OWNER_NAME;
import static com.ciklum.tuitechtest.helper.DataProvider.REPOSITORY_01_NAME;
import static com.ciklum.tuitechtest.helper.DataProvider.REPOSITORY_01_OWNER_NAME;
import static com.ciklum.tuitechtest.helper.DataProvider.REPOSITORY_02_NAME;
import static com.ciklum.tuitechtest.helper.DataProvider.REPOSITORY_02_OWNER_NAME;
import static com.ciklum.tuitechtest.helper.DataProvider.USERNAME;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ExtendWith(OutputCaptureExtension.class)
@TestPropertySource(properties = "config.rest.github-client.service-url=http://localhost:8090")
class GithubClientTest {

    private static WireMockServer wireMockServer;

    @Autowired
    private GithubClient githubClient;

    @BeforeAll
    static void setup() {
        wireMockServer = new WireMockServer(8090);
        wireMockServer.start();
    }

    @AfterAll
    static void teardown() {
        wireMockServer.stop();
    }

    @BeforeEach
    void init() {
        wireMockServer.resetAll();
    }

    @Test
    void shouldReturnRepositoryListWhenClientReturns200() {
        wireMockServer.stubFor(WireMock.get(WireMock.urlEqualTo("/users/" + USERNAME + "/repos"))
                .withHeader("Accept", WireMock.equalTo(ACCEPT_HEADER))
                .willReturn(WireMock.aResponse().withHeader("Content-Type", ACCEPT_HEADER)
                        .withStatus(200)
                        .withBody(DataProvider.getGithubRepositoriesRequestBodyForUserRopman())));

        List<RepositoryResource> repositories = githubClient.getPublicRepositories(USERNAME);
        assertThat(repositories).hasSize(3);
        assertThat(repositories.get(0).getName()).isEqualTo(REPOSITORY_00_NAME);
        assertThat(repositories.get(0).getOwner().getLogin()).isEqualTo(REPOSITORY_00_OWNER_NAME);
        assertThat(repositories.get(0).getBranchesUrl()).isNotBlank();

        assertThat(repositories.get(1).getName()).isEqualTo(REPOSITORY_01_NAME);
        assertThat(repositories.get(1).getOwner().getLogin()).isEqualTo(REPOSITORY_01_OWNER_NAME);
        assertThat(repositories.get(1).getBranchesUrl()).isNotBlank();

        assertThat(repositories.get(2).getName()).isEqualTo(REPOSITORY_02_NAME);
        assertThat(repositories.get(2).getOwner().getLogin()).isEqualTo(REPOSITORY_02_OWNER_NAME);
        assertThat(repositories.get(2).getBranchesUrl()).isNotBlank();
    }

    @Test
    void shouldThrowsWebClientResponseExceptionWhenGetPublicRepositoriesCalledAndClientReturns404(CapturedOutput output) {
        wireMockServer.stubFor(WireMock.get(WireMock.urlEqualTo("/users/" + USERNAME + "/repos"))
                .withHeader("Accept", WireMock.equalTo(ACCEPT_HEADER))
                .willReturn(WireMock.aResponse().withHeader("Content-Type", ACCEPT_HEADER)
                        .withStatus(404)));

        WebClientResponseException webClientResponseException = assertThrows(WebClientResponseException.class, () -> githubClient.getPublicRepositories(USERNAME));
        assertThat(webClientResponseException.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(output.getAll()).contains("Error getting repositories from github");
    }

    @Test
    void shouldReturnBranchListWhenClientReturns200() {
        wireMockServer.stubFor(WireMock.get(WireMock.urlEqualTo("/repos/" + USERNAME + "/" + REPOSITORY_00_NAME + "/branches"))
                .withHeader("Accept", WireMock.equalTo(ACCEPT_HEADER))
                .willReturn(WireMock.aResponse().withHeader("Content-Type", ACCEPT_HEADER)
                        .withStatus(200)
                        .withBody(DataProvider.getGithubBranchesForDockerSshRepository())));

        List<BranchResource> branches = githubClient.getBranches(USERNAME, REPOSITORY_00_NAME);
        assertThat(branches).hasSize(2);

        assertThat(branches.get(0).getName()).isEqualTo(REPOSITORY_00_BRANCH_00_NAME);
        assertThat(branches.get(0).getCommit().getSha()).isEqualTo(REPOSITORY_00_BRANCH_00_SHA);
        assertThat(branches.get(1).getName()).isEqualTo(REPOSITORY_00_BRANCH_01_NAME);
        assertThat(branches.get(1).getCommit().getSha()).isEqualTo(REPOSITORY_00_BRANCH_01_SHA);
    }


    @Test
    void shouldThrowsWebClientResponseExceptionWhenGetBranchesCalledAndClientReturns404(CapturedOutput output) {
        wireMockServer.stubFor(WireMock.get(WireMock.urlEqualTo("/repos/" + USERNAME + "/" + REPOSITORY_00_NAME + "/branches"))
                .withHeader("Accept", WireMock.equalTo(ACCEPT_HEADER))
                .willReturn(WireMock.aResponse().withHeader("Content-Type", ACCEPT_HEADER)
                        .withStatus(404)));

        WebClientResponseException webClientResponseException = assertThrows(WebClientResponseException.class, () -> githubClient.getBranches(USERNAME, REPOSITORY_00_NAME));
        assertThat(webClientResponseException.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(output.getAll()).contains("Error getting branches from github client");
    }
}