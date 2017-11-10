/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.server;

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
    
    public List<T> pop() {
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
