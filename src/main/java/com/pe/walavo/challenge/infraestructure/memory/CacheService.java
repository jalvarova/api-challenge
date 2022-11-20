package com.pe.walavo.challenge.infraestructure.memory;

public interface CacheService<T> {

    void put(String key, T obj);

    T get(String key);
}
