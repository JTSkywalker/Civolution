/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author jt
 * @param <T>
 */
public class DelayedQueue<T> {
    
    final Map<T,Integer> map = new HashMap<>();
    
    public DelayedQueue() {
        
    }
    
    public DelayedQueue (Collection<T> coll) {
        coll.forEach((a) -> {
            this.map.put(a, 0);
        });
    }
    
    public int getDelay(T obj) {
        return map.get(obj);
    }
    
    public Integer put(T obj, int delay) {
        Integer b = map.get(obj);
        map.put(obj, delay);
        return b;
    }
    
    public void steps(int steps) {
        map.forEach((T a,Integer b) -> {
            this.map.put(a, b - steps);
        });
    }
    
    public void step() {
        steps(1);
    }
    
    /**
     * find an element of the collection with the lowest delay
     * and step to this point
     * @return next element or null if empty
     */
    public T stepToNext() {
        int min = Integer.MAX_VALUE;
        T next = null;
        for (T t : map.keySet()) {
            if (map.get(t) < min) {
                min = map.get(t);
                next = t;
            }
        }
        steps(min);
        return next;
    }
    
    public List<T> popAll() {
        List<T> ret = new ArrayList<>();
        map.forEach((a,b) -> {
            if (b <= 0) {
                ret.add(a);
            }
        });
        ret.sort((T a, T b) -> Integer.compare(map.get(a),map.get(b))); 
        return ret;
    }
    
}
