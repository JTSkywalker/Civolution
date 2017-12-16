/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.game;

/**
 * Interface of the visitable from the visitor pattern.
 * @author jt
 * @param <T> type of the visitor corresponding to the respective game
 */
public interface Visitable<T> {
    
    /**
     * Accept a visitor to access the object.
     * @param visitor gets access to visitable object 
     */
    public void accept(T visitor);
    
}
