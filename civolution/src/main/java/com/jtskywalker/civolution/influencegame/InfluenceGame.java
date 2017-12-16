/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.influencegame;

import com.jtskywalker.civolution.controller.Actor;
import com.jtskywalker.civolution.game.Game;
import com.jtskywalker.civolution.game.GameMap;
import com.jtskywalker.civolution.game.Horizon;
import com.jtskywalker.civolution.game.SqTorusCoordinator;
import java.util.Collection;

/**
 * The InfluenceGame.
 * The players goal is to conquer the capital of the enemy.
 * At the start the enemy is way stronger and the player has to recruit 
 * mercenaries to achieve his goal.
 * The player will be constantly under attack, but both he and the enemy have
 * walls that help protecting them until someone invents explosives and uses
 * cannons to destroy these walls.
 * Player get money from the cities and can use it either to support research or
 * to pay their troops.
 * @author jt
 */
public class InfluenceGame implements Game {
    
    private final GameMap map;
    
    public InfluenceGame(int width, int height) {
        map = new GameMap(new SqTorusCoordinator(width,height));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Horizon computeHorizon(Actor actor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<Actor> getActors() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getWidth() {
        return map.getWidth();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getHeight() {
        return map.getHeight();
    }
    
}
