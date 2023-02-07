package com.ciklum.tuitechtest.controller;

import com.ciklum.tuitechtest.dto.BranchInfoDto;
import com.ciklum.tuitechtest.dto.RepositoryInfoDto;
import com.ciklum.tuitechtest.exception.FetchingRepositoryException;
import com.ciklum.tuitechtest.service.GithubPublicInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.Set;

import static com.ciklum.tuitechtest.helper.DataProvider.REPOSITORY_00_BRANCH_00_NAME;
import static com.ciklum.tuitechtest.helper.DataProvider.REPOSITORY_00_BRANCH_00_SHA;
import static com.ciklum.tuitechtest.helper.DataProvider.REPOSITORY_00_NAME;
import static com.ciklum.tuitechtest.helper.DataProvider.REPOSITORY_00_OWNER_NAME;
import static com.ciklum.tuitechtest.helper.DataProvider.USERNAME;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_XML;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class GithubPublicInfoControllerTest {

    private static final String REQUEST_URL = "/api/repositories/%s";

    @MockBean
    private GithubPublicInfoService githubPublicInfoService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnOkAndInformationWhenAcceptHeaderIsJsonType() throws Exception {
        BranchInfoDto branchInfoDto = new BranchInfoDto(REPOSITORY_00_BRANCH_00_NAME, REPOSITORY_00_BRANCH_00_SHA);
        List<RepositoryInfoDto> repositoryInfoDtos = List.of(new RepositoryInfoDto(REPOSITORY_00_NAME, REPOSITORY_00_OWNER_NAME, Set.of(branchInfoDto)));
        when(githubPublicInfoService.getRepositoriesWithBranchesFor(USERNAME))
                .thenReturn(repositoryInfoDtos);

        mockMvc.perform(MockMvcRequestBuilders.get(String.format(REQUEST_URL, USERNAME))
                .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(repositoryInfoDtos.size())))
                .andExpect(jsonPath("$[0].repositoryName").value(repositoryInfoDtos.get(0).repositoryName()))
                .andExpect(jsonPath("$[0].ownerLogin").value(repositoryInfoDtos.get(0).ownerLogin()))
                .andExpect(jsonPath("$[0].branchInfo").isArray())
                .andExpect(jsonPath("$[0].branchInfo[0].name").value(branchInfoDto.name()))
                .andExpect(jsonPath("$[0].branchInfo[0].lastCommitSha").value(branchInfoDto.lastCommitSha()))         ;
    }

    @Test
    void shouldReturnNotAcceptableWithErrorResponseWhenAcceptHeaderIsXmlType() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(String.format(REQUEST_URL, USERNAME))
                        .accept(APPLICATION_XML))
                .andExpect(status().isNotAcceptable())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value(NOT_ACCEPTABLE.value()))
                .andExpect(jsonPath("$.Message").value("No acceptable representation"));
    }

    @Test
    void shouldReturnNotFoundWithErrorResponseWhenFetchingRepositoryExceptionThrown() throws Exception {
        when(githubPublicInfoService.getRepositoriesWithBranchesFor(USERNAME))
                .thenThrow(new FetchingRepositoryException(USERNAME));

        mockMvc.perform(MockMvcRequestBuilders.get(String.format(REQUEST_URL, USERNAME))
                        .accept(APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value(NOT_FOUND.value()))
                .andExpect(jsonPath("$.Message").value(String.format("Repositories are not available for %s", USERNAME)));
    }
}