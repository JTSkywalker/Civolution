/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.controller;

import com.jtskywalker.civolution.game.Horizon;
import com.jtskywalker.civolution.lang.Statement;

/**
 * This is the general interface that is used for the every element of a game
 * that is possible to modify the state of the game by itself.
 * @author jt
 * @param <T> type of horizon
 */
public interface Actor<T extends Horizon> {
    
    default public void findNextAction(T horizon, Controller controller) {
        Action next = this.nextAction(horizon);
        // this is not very beautiful
        if (next != null) {
            controller.executeAction(this, next);
        }
    }
    
    public Action nextAction(T horizon);
    
    public boolean receiveOrders(Statement<Action> orders, int nation);
    
    public void setOrders(Statement<Action> orders);
    
}
