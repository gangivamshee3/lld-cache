package com.gvk.cache.policies;


import com.gvk.algoritms.DoublyLinkedList;
import com.gvk.algoritms.DoublyLinkedListNode;

import java.util.HashMap;
import java.util.Map;

/**
 * Eviction policy based on LRU algorithm.
 *
 * @param <Key> Key type.
 */
public class LRUEvictionPolicy<Key> implements EvictionPolicy<Key> {

    private DoublyLinkedList<Key> dll;
    private Map<Key, DoublyLinkedListNode<Key>> mapper;

    public LRUEvictionPolicy() {
        this.dll = new DoublyLinkedList<Key>();
        this.mapper = new HashMap<Key, DoublyLinkedListNode<Key>>();
    }

    public void keyAccessed(Key key) {
        if (mapper.containsKey(key)) {
            dll.detachNode(mapper.get(key));
            dll.addNodeAtLast(mapper.get(key));
        } else {
            DoublyLinkedListNode<Key> newNode = dll.addElementAtLast(key);
            mapper.put(key, newNode);
        }
    }

    public Key evictKey() {
        DoublyLinkedListNode<Key> first = dll.getFirstNode();
        if(first == null) {
            return null;
        }
        dll.detachNode(first);
        return first.getElement();
    }
}
