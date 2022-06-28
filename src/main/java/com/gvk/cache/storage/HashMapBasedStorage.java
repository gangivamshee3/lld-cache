package com.gvk.cache.storage;

import com.gvk.cache.exceptions.NotFoundException;
import com.gvk.cache.exceptions.StorageFullException;

import java.util.HashMap;
import java.util.Map;

public class HashMapBasedStorage<Key, Value> implements Storage<Key, Value> {

    Map<Key, Value> storage;
    private final Integer capacity;

    public HashMapBasedStorage(Integer capacity) {
        this.capacity = capacity;
        storage = new HashMap<Key, Value>();
    }

    public void add(Key key, Value value) throws StorageFullException {
        if (isStorageFull()) throw new StorageFullException("Capacity Full.....");
        storage.put(key, value);
    }

    public void remove(Key key) throws NotFoundException {
        if (!storage.containsKey(key)) throw new NotFoundException(key + "doesn't exist in cache.");
        storage.remove(key);
    }

    public Value get(Key key) throws NotFoundException {
        if (!storage.containsKey(key)) throw new NotFoundException(key + "doesn't exist in cache.");
        return storage.get(key);
    }

    private boolean isStorageFull() {
        return storage.size() == capacity;
    }
}
