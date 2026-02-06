package org.t13.app.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.t13.app.meta.ProductCacheLoader;

import java.io.IOException;

@RestController
@Slf4j
public class EHCacheController {

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private ProductCacheLoader productCacheLoader;

    // GET endpoint at: http://localhost:8080/hello
    @GetMapping("/hello")
    public String sayHello() throws IOException, InterruptedException {

        Cache cache = cacheManager.getCache("MY_CACHE");
        String value = cache.get("response") == null ? productCacheLoader.getDetails() : cache.get("response").toString();
        System.out.println("Cached value: " + value);
        return value;

    }
}