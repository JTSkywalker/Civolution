/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.view;

import com.jtskywalker.civolution.controller.Action;
import com.jtskywalker.civolution.game.Horizon;
import com.jtskywalker.civolution.controller.Mind;
import com.jtskywalker.civolution.lang.ActionEvaluator;
import com.jtskywalker.civolution.lang.Evaluator;
import com.jtskywalker.civolution.lang.Scope;
import com.jtskywalker.civolution.lang.Statement;
import com.jtskywalker.civolution.lang.statement.ExternalStmt;

/**
 *
 * @author jt
 */
public class ExternalMind implements Mind {
    
    private Statement orders = null;
    final Scope<String, Integer> scope = new Scope(null);
    private final int nation;
    
    public ExternalMind(int nation) {
        this.nation = nation;
    }

    @Override
    public Action nextAction(Horizon horizon) {
        Evaluator<Action> eval = new ActionEvaluator(horizon);
        if (orders == null) {
            return null;
        }
        ExternalStmt<Action> result 
                = orders.nextExternal(eval, scope);
        if (result == null) {
            orders = null;
            return null;
        }
        orders = result.getNext();
        return result.getExternal();
    }

    @Override
    public boolean receiveOrders(Statement<Action> orders, int nation) {
        return false;
    }

    @Override
    public void setOrders(Statement<Action> orders) {
        this.orders = orders;
    }
    
}
