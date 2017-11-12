/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.server;

import com.jtskywalker.civolution.game.Pawn;
import com.jtskywalker.civolution.game.action.Pause;
import com.jtskywalker.civolution.lang.ActionEvaluator;
import com.jtskywalker.civolution.lang.Evaluator;
import com.jtskywalker.civolution.lang.Scope;
import com.jtskywalker.civolution.lang.Statement;
import com.jtskywalker.civolution.lang.statement.ExternalStmt;

/**
 *
 * @author rincewind
 */
public class Subordinate implements Actor {
    
    final int nation;
    Statement orders;
    Pawn pawn;
    final Scope<String,Integer> scope = new Scope(null);
    final Controller controller;

    public Subordinate(int nation, Controller controller) {
        this.nation = nation;
        this.controller = controller;
    }
    
    Action nextAction(Horizon horizon) {
        Evaluator<Action> eval = new ActionEvaluator(horizon);
        if (orders == null) {
            return new Pause();
        }
        ExternalStmt<Action> result 
                = orders.nextExternal(eval, scope);
        if (result == null) {
            orders = null;
            return new Pause();
        }
        orders = result.getNext();
        return result.getExternal();
    }
    
    @Override
    public void findNextAction(Horizon horizon) {
        Action next = nextAction(horizon);
        controller.executeAction(this, next);
    }
    
    @Override
    public boolean receiveOrders(Statement<Action> orders, int nation) {
        if (this.nation == nation) {
            this.orders = orders;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Pawn getPawn() {
        return pawn;
    }

    @Override
    public void setPawn(Pawn pawn) {
        this.pawn = pawn;
    }
    
}
