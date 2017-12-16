/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.lang;

import java.util.HashMap;
import java.util.Map;

/**
 * A class to keep track of the current scope.
 * @author jt
 * @param <K> key type
 * @param <V> value type
 */
public class Scope<K,V> {
    
    final Scope<K,V> parent;
    final Map<K,V> table = new HashMap<>();
    
    public Scope(Scope<K,V> parent) {
        this.parent = parent;
    }
    
    
    public V lookup(K key) {
        if (table.containsKey(key)) {
            return table.get(key);
        }
        if (parent != null) {
            return parent.lookup(key);
        }
        throw new IllegalArgumentException(key + " is out of scope");
    }
    
    public void put(K key, V value) {
        if (table.containsKey(key)
                || parent == null
                || !parent.contains(key)) {
            table.put(key, value);
            
        } else {
            parent.put(key, value);
        }
    }
    
    public boolean contains(K key) {
        if (table.containsKey(key)) {
            return true;
        }
        if (parent == null) {
            return false;
        }
        return parent.contains(key);
    }
    
}
