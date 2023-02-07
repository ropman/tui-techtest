package com.ciklum.tuitechtest.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("config.rest.github-client")
public class GithubClientConfig {
    private String serviceUrl;
    private String fetchPublicRepositoriesUrl;
    private String fetchBranchesUrl;
    private String acceptHeader;
    private String versionHeader;
}
