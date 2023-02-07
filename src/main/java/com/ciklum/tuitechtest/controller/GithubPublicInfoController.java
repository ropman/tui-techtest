package com.ciklum.tuitechtest.controller;

import com.ciklum.tuitechtest.dto.RepositoryInfoDto;
import com.ciklum.tuitechtest.service.GithubPublicInfoService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/repositories", produces = MediaType.APPLICATION_JSON_VALUE)
public class GithubPublicInfoController {

    private GithubPublicInfoService publicInfoService;

    @GetMapping(value = "/{username}")
    public Collection<RepositoryInfoDto> getRepositoriesInfo(@PathVariable("username") String username) {
        return publicInfoService.getRepositoriesWithBranchesFor(username);
    }
}
