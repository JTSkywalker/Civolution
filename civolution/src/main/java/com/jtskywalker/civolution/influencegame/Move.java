/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.influencegame;

import com.jtskywalker.civolution.game.SqDirection;
import com.jtskywalker.civolution.game.SqCoordinates;
import com.jtskywalker.civolution.controller.Action;
import com.jtskywalker.civolution.controller.ActionNotAllowedException;
import com.jtskywalker.civolution.controller.Actor;
import java.util.Objects;

/**
 *
 * @author jt
 */
public class Move implements Action<InflGame> {
    
    final SqDirection direction;

    public Move(SqDirection direction) {
        this.direction = direction;
    }
    
    /**
     * The {@code Body} of {@code actor} moves one step in {@code direction} of
     * this object if there is no enemy on the new position and it is accessible
     * for the body.
     * If their is an enemy it will be attacked.
     * @param game
     * @param actor
     * @return
     * @throws ActionNotAllowedException 
     */
    @Override
    public int execute(InflGame game, Actor actor) 
            throws ActionNotAllowedException {
        Body body = game.getBody(actor);
        SqCoordinates oldC = game.getPosition(body);
        SqCoordinates newC = game.plusStep(oldC, direction);
        
        if (body.canMove(game.getTerrain(newC))) {
            if (game.hasEnemy(newC, body.getTeam())) {
                return attack(game, actor, oldC, newC);
            } else {
                game.put(body, newC);
            }
        }
         
        game.put(body,newC);
        return game.getTerrain(newC).exertion;
    }
    
    private int attack(InflGame game, Actor actor, 
            SqCoordinates oldC,SqCoordinates newC) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.direction);
        return hash;
    }

    /**
     * {@inheritDoc}
     */
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
        return this.direction == other.direction;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Move{" + "direction=" + direction + '}';
    }
       
    
}

