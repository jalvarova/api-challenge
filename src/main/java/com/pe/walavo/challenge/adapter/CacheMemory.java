package com.pe.walavo.challenge.adapter;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.time.Duration;

public class CacheMemory implements CacheService<Object>{

    public final static Cache<String, Object> cache;

    static {
        cache = CacheBuilder
                .newBuilder()
                .maximumSize(1000)
                .expireAfterWrite(Duration.ofHours(1))
                .build();
    }


    public void put(String key, Object value) {
        cache.put(key, value);
        cache.cleanUp();
    }

    public Object get(String key) {
        Object value = cache.getIfPresent(key);
        cache.cleanUp();
        return value;
    }

}
