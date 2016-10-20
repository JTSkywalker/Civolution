/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.lang;

import com.jtskywalker.civolution.lang.statement.ExternalStmt;



/**
 *
 * @author rincewind
 * @param <T>
 */
public interface Statement<T> {
    
    public ExternalStmt<T> nextExternal(Evaluator<T> externalEvaluator,
            Scope<String,Integer> scope);
    
}
