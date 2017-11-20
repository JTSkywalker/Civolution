/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.demogame.action;

import com.jtskywalker.civolution.game.Direction;
import com.jtskywalker.civolution.game.Coordinates;
import com.jtskywalker.civolution.demogame.DemoGame;
import com.jtskywalker.civolution.controller.Action;
import com.jtskywalker.civolution.controller.ActionNotAllowedException;
import com.jtskywalker.civolution.controller.Actor;
import com.jtskywalker.civolution.demogame.BodyImpl;
import java.util.Objects;

/**
 *
 * @author rincewind
 */
public class Move implements Action<DemoGame,BodyImpl> {
    
    final Direction direction;

    public Move(Direction direction) {
        this.direction = direction;
    }

    @Override
    public int execute(DemoGame game, Actor<BodyImpl> actor) 
            throws ActionNotAllowedException {
        Coordinates oldC = game.getCoordinates(actor);
        Coordinates newC = oldC.add(direction);
        if (game.hasEnemy(newC,actor.getEmblem())) {
            if (actor.getBody().canAttack()) {
                return (new Attack(direction)).execute(game, actor);
            } else {
                throw new ActionNotAllowedException();
            }
        }
        game.putActor(actor,newC);
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

