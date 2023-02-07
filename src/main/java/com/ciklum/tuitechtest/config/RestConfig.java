package com.ciklum.tuitechtest.config;

import io.netty.resolver.DefaultAddressResolverGroup;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

@Data
@Configuration
@ConfigurationProperties("config.rest")
public class RestConfig {
    private int readTimeout;

    @Bean
    HttpClient nettyHttpClient() {
        return HttpClient.create()
                .resolver(DefaultAddressResolverGroup.INSTANCE)
                .responseTimeout(Duration.of(readTimeout, ChronoUnit.MILLIS));
    }
}
