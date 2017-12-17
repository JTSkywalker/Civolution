/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.demogame.action;

import com.jtskywalker.civolution.demogame.DemoGame;
import com.jtskywalker.civolution.lang.Statement;
import com.jtskywalker.civolution.controller.Action;
import com.jtskywalker.civolution.controller.ActionNotAllowedException;
import com.jtskywalker.civolution.controller.Actor;
import java.util.Objects;

/**
 *
 * @author jt
 */
public class Command implements Action<DemoGame> {
    
    final Statement<Action> order;

    public Command(Statement<Action> order) {
        this.order = order;
    }
    

    @Override
    public int execute(DemoGame game, Actor actor) 
            throws ActionNotAllowedException {
        try {
            Actor sub = game.getSubordinate(actor);
            sub.receiveOrders(order, game.getBody(actor).getEmblem());
            return 0;
        } catch (IllegalArgumentException ex) {
            throw new ActionNotAllowedException("No Subordinate found.",ex);
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(this.order);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Command other = (Command) obj;
        return Objects.equals(this.order, other.order);
    }

    @Override
    public String toString() {
        return "Command{" + "order=" + order + '}';
    }
    
}
