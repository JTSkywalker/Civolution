/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.controller;

import com.jtskywalker.civolution.game.Horizon;
import com.jtskywalker.civolution.game.action.Skip;
import com.jtskywalker.civolution.lang.ActionEvaluator;
import com.jtskywalker.civolution.lang.Evaluator;
import com.jtskywalker.civolution.lang.Scope;
import com.jtskywalker.civolution.lang.Statement;
import com.jtskywalker.civolution.lang.statement.ExternalStmt;

/**
 *
 * @author jt
 */
public class Subordinate implements Actor {
    
    final int nation;
    Statement orders;
    final Scope<String,Integer> scope = new Scope(null);

    public Subordinate(int nation) {
        this.nation = nation;
    }
    
    @Override
    public Action nextAction(Horizon horizon) {
        Evaluator<Action> eval = new ActionEvaluator(horizon);
        if (orders == null) {
            return new Skip();
        }
        ExternalStmt<Action> result 
                = orders.nextExternal(eval, scope);
        if (result == null) {
            orders = null;
            return new Skip();
        }
        orders = result.getNext();
        return result.getExternal();
    }
    
    @Override
    public boolean receiveOrders(Statement orders, int nation) {
        if (this.nation == nation) {
            this.orders = orders;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void setOrders(Statement orders) {
        this.orders = orders;
    }
    
}
