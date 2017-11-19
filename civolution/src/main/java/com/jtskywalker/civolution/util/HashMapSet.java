/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author rincewind
 */
public class HashMapSet<K,V> {
    
    private final Map<K,Set<V>> mapset = new HashMap<>();

    public Set<V> get(K key) {
        Set<V> result = mapset.get(key);
        if (result == null) {
            return new HashSet<>();
        }
        return result;    
    }

    public int size() {
        return mapset.values()
                .stream()
                .map((set) -> set.size())
                .reduce(0, Integer::sum);
    }

    public boolean isEmpty() {
        return mapset.values()
                .stream()
                .allMatch((set) -> set.isEmpty());
    }
    
    public boolean containsKey(K key) {
        return mapset.containsKey(key) && !mapset.get(key).isEmpty();
    }

    public boolean containsValue(V value) {
        return mapset.values()
                .stream()
                .anyMatch((set) -> set.contains(value));
    }

    public void put(K key, V value) {
        if (!mapset.containsKey(key)) {
            mapset.put(key, new HashSet<>());
        }
        mapset.get(key).add(value);
    }

    public void removeValue(V value) {
        mapset.values()
                .stream()
                .forEach((set) -> set.remove(value));
        mapset.keySet()
                .stream()
                .filter((key) -> (mapset.get(key).isEmpty()))
                .forEach((key) -> mapset.remove(key));
    }

    public void clear() {
        mapset.clear();
    }

    public Set<K> keySet() {
        return mapset.keySet()
                .stream()
                .filter((key) -> (!mapset.get(key).isEmpty()))
                .collect(Collectors.toSet());
    }
    
    
    
}
