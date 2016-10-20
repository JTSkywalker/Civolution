/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.lang;

/**
 *
 * @author rincewind
 * @param <T>
 */
@FunctionalInterface
public interface Evaluator<T> {
    
    public int evaluate(Scope<String,Integer> scope, T expression);
    
    
}
