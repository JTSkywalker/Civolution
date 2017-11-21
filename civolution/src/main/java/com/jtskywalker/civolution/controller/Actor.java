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
 */
public class Actor {
    
    private Mind mind;
    final Controller controller;
    
    public Actor(Mind mind, Controller controller) {
        this.mind = mind;
        this.controller = controller;
    }
    
    public void findNextAction(Horizon horizon) {
        Action next = mind.nextAction(horizon);
        // this is not very beautiful
        if (next != null) {
            controller.executeAction(this, next);
        }
    }
    
    public boolean receiveOrders(Statement<Action> orders, int nation) {
        return mind.receiveOrders(orders, nation);
    }
    
    public void setOrders(Statement<Action> orders) {
        mind.setOrders(orders);
    }

    public Mind getMind() {
        return mind;
    }

    public void setMind(Mind mind) {
        this.mind = mind;
    }
    
    public Controller getController() {
        return controller;
    }    
    
}
