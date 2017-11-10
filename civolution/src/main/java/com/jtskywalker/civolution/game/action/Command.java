/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.game.action;

import com.jtskywalker.civolution.game.Pawn;
import com.jtskywalker.civolution.game.Game;
import com.jtskywalker.civolution.lang.Statement;
import com.jtskywalker.civolution.server.Action;
import com.jtskywalker.civolution.server.ActionNotAllowedException;
import com.jtskywalker.civolution.server.Actor;
import java.util.Objects;

/**
 *
 * @author rincewind
 */
public class Command implements Action {
    
    final Statement<Action> order;

    public Command(Statement<Action> order) {
        this.order = order;
    }


    @Override
    public int execute(Game game, Pawn counter) throws ActionNotAllowedException {
        Pawn sub = game.getSubordinate(counter);
        if (sub == null) {
            return 0;
        }
        Actor subordinate = sub.getActor();
        subordinate.receiveOrders(order, counter.getEmblem());
        return 0;
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
        if (!Objects.equals(this.order, other.order)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Command{" + "order=" + order + '}';
    }
    
}
