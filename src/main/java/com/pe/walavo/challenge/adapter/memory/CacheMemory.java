package com.pe.walavo.challenge.adapter.memory;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Slf4j
@Component
public class CacheMemory implements CacheService<Object> {

    public final static Cache<String, Object> cache;

    static {
        cache = CacheBuilder
                .newBuilder()
                .maximumSize(1000)
                .expireAfterWrite(Duration.ofHours(1))
                .build();
    }


    public void put(String key, Object value) {
        log.info("Put key " + key + " in Cache");
        cache.put(key, value);
        cache.cleanUp();
    }

    public Object get(String key) {
        log.info("Get key " + key + " in Cache");
        Object value = cache.getIfPresent(key);
        cache.cleanUp();
        return value;
    }

}
