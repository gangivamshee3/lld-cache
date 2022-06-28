package com.gvk.cache.factories;

import com.gvk.cache.Cache;
import com.gvk.cache.policies.LRUEvictionPolicy;
import com.gvk.cache.storage.HashMapBasedStorage;

public class CacheFactory<Key, Value> {

    public Cache<Key, Value> defaultCache(final int capacity) {
        return new Cache<Key, Value>(new LRUEvictionPolicy<Key>(),
                new HashMapBasedStorage<Key, Value>(capacity));
    }
}
