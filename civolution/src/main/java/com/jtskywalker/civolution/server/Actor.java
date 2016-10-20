/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.server;

import com.jtskywalker.civolution.game.Counter;
import com.jtskywalker.civolution.lang.Statement;

/**
 *
 * @author rincewind
 */
public abstract class Actor {
    
    Counter counter;

    public Counter getCounter() {
        return counter;
    }
    
    public void setCounter(Counter counter) {
        this.counter = counter;
    }
    
    public abstract Action nextAction(Horizon horizon);
    
    public boolean receiveOrders(Statement<Action> orders, int nation) {
        return false;
    }
    
}
