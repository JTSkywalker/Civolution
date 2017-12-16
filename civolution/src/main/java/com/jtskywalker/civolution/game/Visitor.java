/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.game;

/**
 * Visitor interface of the visitor pattern.
 * @author jt
 * @param <T> type of the corresponding visitable
 */
public interface Visitor<T> {
    
    /**
     * Visit (access) the given {@code visitable}
     * @param visitable the object visited by this visitor 
     */
    public void visit(T visitable);
    
}
