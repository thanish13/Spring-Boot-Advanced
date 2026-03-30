package org.t13.app.meta;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.t13.app.config.CachingConfig.USER_CACHE;

@Slf4j
@Service
public class UserCacheLoader {
    HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1).build();

    @Cacheable(value = USER_CACHE)
    public String getUsers() throws IOException, InterruptedException {

        log.info("Loading User values");

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://fakestoreapi.com/users"))
                .build();

        HttpResponse<String> response = httpClient.send(request,HttpResponse.BodyHandlers.ofString());
        log.info("Got response from User URL");
        return response.body();
    }
}
