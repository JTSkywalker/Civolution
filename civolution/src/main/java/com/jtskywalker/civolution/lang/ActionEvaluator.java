/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.lang;

import com.jtskywalker.civolution.controller.Action;
import com.jtskywalker.civolution.controller.Horizon;


public class ActionEvaluator implements Evaluator<Action> {

    final Horizon horizon;

    public ActionEvaluator(Horizon horizon) {
        this.horizon = horizon;
    }
    
    @Override
    public int evaluate(Scope<String, Integer> scope, Action expression) {
        return 0;
    }
    
}
