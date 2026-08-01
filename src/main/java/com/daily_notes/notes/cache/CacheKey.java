package com.daily_notes.notes.cache;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
final public class CacheKey<K, V> {

    private final K key;
    private final V value;

    public CacheKey(K key, V value) {
        this.key = key;
        this.value = value;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        CacheKey<K, V> cacheKey = (CacheKey<K, V>) o;

        return Objects.equals(cacheKey.value, value);
    }

}
