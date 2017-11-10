/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.game.action;

import com.jtskywalker.civolution.server.Coordinates;
import com.jtskywalker.civolution.game.Pawn;
import com.jtskywalker.civolution.game.Game;
import com.jtskywalker.civolution.server.Action;
import com.jtskywalker.civolution.server.ActionNotAllowedException;
import java.util.Objects;

/**
 *
 * @author rincewind
 */
public class Move implements Action {
    
    final Direction direction;

    public Move(Direction direction) {
        this.direction = direction;
    }

    @Override
    public int execute(Game game, Pawn counter) 
            throws ActionNotAllowedException {
        Coordinates oldC = game.getCoordinates(counter);
        Coordinates newC = oldC.add(direction);
        if (game.hasEnemy(newC,counter.getEmblem())) {
            if (counter.canAttack()) {
                return (new Attack(direction)).execute(game, counter);
            } else {
                throw new ActionNotAllowedException();
            }
        }
        game.putCounter(counter,newC);
        return game.getTileMobilityCost(newC);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.direction);
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
        final Move other = (Move) obj;
        if (this.direction != other.direction) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Move{" + "direction=" + direction + '}';
    }
       
    
}

