package com.ciklum.tuitechtest.dto;

import java.util.Set;

public record RepositoryInfoDto(String repositoryName, String ownerLogin, Set<BranchInfoDto> branchInfo) { }
