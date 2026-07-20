package org.t13.app.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.MemoryUnit;
import org.ehcache.core.config.DefaultConfiguration;
import org.ehcache.expiry.ExpiryPolicy;
import org.ehcache.jsr107.EhcacheCachingProvider;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.jcache.JCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static javax.cache.Caching.getCachingProvider;

@Configuration
@EnableCaching
public class CachingConfig {

    private static final Logger logger = LogManager.getLogger(CachingConfig.class);

    public static final String PRODUCT_CACHE = "PRODUCT_CACHE";
    public static final String USER_CACHE = "USER_CACHE";


    @Bean
    public CacheManager jCacheCacheManager() {

        Map<String, CacheConfiguration<?, ?>> cacheMap = new HashMap<>();

        ResourcePoolsBuilder resourcePoolsBuilder = ResourcePoolsBuilder
                .heap(3)
                .offheap(1, MemoryUnit.MB)
                //min value is 1MB
                ;

        ExpiryPolicy<Object, Object> expiryPolicy = createExpiryPolicy(Duration.ofMinutes(1), Duration.ofMinutes(1));

        CacheConfiguration<Object, Object> cacheConfiguration = CacheConfigurationBuilder
                .newCacheConfigurationBuilder(Object.class, Object.class, resourcePoolsBuilder)
                .withExpiry(expiryPolicy)
                .build();

        cacheMap.put(PRODUCT_CACHE, cacheConfiguration);
        cacheMap.put(USER_CACHE, cacheConfiguration);
        EhcacheCachingProvider ehcacheCachingProvider = (EhcacheCachingProvider) getCachingProvider(EhcacheCachingProvider.class.getName());
        DefaultConfiguration defaultConfiguration = new DefaultConfiguration(cacheMap, ehcacheCachingProvider.getDefaultClassLoader());
        javax.cache.CacheManager cacheManager = ehcacheCachingProvider.getCacheManager(ehcacheCachingProvider.getDefaultURI(), defaultConfiguration);

        return new JCacheCacheManager(cacheManager);
    }

    private static ExpiryPolicy<Object, Object> createExpiryPolicy(Duration timeToLive, Duration timeToIdle) {
        return ExpiryPolicyBuilder
                .expiry()
                .create(timeToLive)
                .access(timeToIdle)
                .build();
    }

}
