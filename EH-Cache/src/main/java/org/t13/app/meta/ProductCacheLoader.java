package org.t13.app.meta;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Slf4j
@Service
public class ProductCacheLoader {

    HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1).build();

    @Autowired
    private CacheManager cacheManager;

    @PostConstruct
    public void loadCache() throws IOException, InterruptedException {
        Cache cache = cacheManager.getCache("MY_CACHE");
        cache.put("response", getDetails());
        System.out.println("Cache preloaded with products!");
    }

    @Cacheable("MY_CACHE")
    public String getDetails() throws IOException, InterruptedException {

        log.info("Loading values");

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://fakestoreapi.com/products"))
                .build();

        HttpResponse<String> response = httpClient.send(request,HttpResponse.BodyHandlers.ofString());

        return response.body();
    }
}

