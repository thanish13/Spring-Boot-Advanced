package org.t13.app.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.t13.app.meta.ProductCacheLoader;

import java.io.IOException;
import java.util.Objects;

@RestController
@Slf4j
public class JCacheController {

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private ProductCacheLoader productCacheLoader;

    // GET endpoint at: http://localhost:8080/hello
    @GetMapping("/products")
    public String getProducts() throws IOException, InterruptedException {

        var products = cacheManager.getCache("MY_CACHE").get("products");

        if (products == null) {
            log.info("Cache not found!");
            productCacheLoader.loadCache();
        }

        return cacheManager.getCache("MY_CACHE").get("products").toString();

    }

    @GetMapping("/getItems")
    public String getItems() throws IOException, InterruptedException {

        return cacheManager.getCache("MY_CACHE").get("products").toString();

    }
}