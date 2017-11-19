/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.controller;

import com.jtskywalker.civolution.game.Body;
import com.jtskywalker.civolution.lang.Statement;

/**
 *
 * @author rincewind
 */
public class Actor {
    
    private Body body;
    private Mind mind;
    final Controller controller;
    
    public Actor(Body body, Mind mind, Controller controller) {
        this.body = body;
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

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public Mind getMind() {
        return mind;
    }

    public void setMind(Mind mind) {
        this.mind = mind;
    }

    public int getEmblem() {
        return body.getEmblem();
    }
    
    public Controller getController() {
        return controller;
    }    
    
}
