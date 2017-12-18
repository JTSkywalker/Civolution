/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.influencegame;

import com.jtskywalker.civolution.controller.Actor;
import com.jtskywalker.civolution.game.Game;
import com.jtskywalker.civolution.game.Horizon;
import com.jtskywalker.civolution.game.SqCoordinates;
import com.jtskywalker.civolution.game.SqDirection;
import com.jtskywalker.civolution.game.SqTorusCoordinator;
import java.util.Collection;
import java.util.Set;

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
public class InflGame implements Game {
    
    private final InflGameMap map;
    
    /*
    This class shouldn't leak actors.
    */
    
    public InflGame(int width, int height) {
        map = new InflGameMap(new SqTorusCoordinator(width,height));
    }

    Terrain getTerrain(SqCoordinates position) {
        return map.getTerrain(position);
    }

    boolean isSea(SqCoordinates position) {
        return map.isSea(position);
    }

    Flora getFlora(SqCoordinates position) {
        return map.getFlora(position);
    }
    
    Body getBody(Actor actor) {
        return map.getBody(actor);
    }

    SqCoordinates getPosition(Body body) {
        return map.getPosition(body);
    }

    Set<Body> getBodies(SqCoordinates position) {
        return map.getBodies(position);
    }

    void put(Body body, SqCoordinates position) {
        map.put(body, position);
    }

    void remove(Body body) {
        map.remove(body);
    }

    public SqCoordinates plusStep(SqCoordinates position, SqDirection direction) {
        return map.plusStep(position, direction);
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

    boolean hasEnemy(SqCoordinates newC, int team) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    boolean atWar(int team1, int team2) {
        return team1 != team2 && team1 != 0 && team2 != 0;
    }
    
}
