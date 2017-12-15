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
 * The InfluenceGame. TODO: explain game here
 * @author jt
 */
public class InfluenceGame implements Game {
    
    private final GameMap map;
    
    public InfluenceGame(int width, int height) {
        map = new GameMap(new SqTorusCoordinator(width,height));
    }

    @Override
    public Horizon computeHorizon(Actor actor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<Actor> getActors() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getWidth() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getHeight() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
