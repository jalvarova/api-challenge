package com.pe.walavo.challenge.adapter;

public interface CacheService<T> {

    void put(String key, T obj);

    T get(String key);
}
