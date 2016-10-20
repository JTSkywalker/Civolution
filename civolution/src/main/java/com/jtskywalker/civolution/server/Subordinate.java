/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.server;

import com.jtskywalker.civolution.game.action.Pause;
import com.jtskywalker.civolution.lang.Statement;

/**
 *
 * @author rincewind
 */
public class Subordinate extends Actor {
    
    final int nation;
    Statement orders;

    public Subordinate(int nation) {
        this.nation = nation;
    }
    
    @Override
    public Action nextAction(Horizon horizon) {
        return new Pause();
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
    
}
