/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.lang;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author rincewind
 * @param <K>
 * @param <V>
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
        table.put(key, value);
    }
    
}
