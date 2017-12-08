/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.controller;

import com.jtskywalker.civolution.game.Horizon;
import com.jtskywalker.civolution.lang.Statement;

/**
 *
 * @author rincewind
 * @param <T>
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
