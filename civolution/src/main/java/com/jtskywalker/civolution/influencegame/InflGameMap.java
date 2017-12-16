/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.influencegame;

import com.jtskywalker.civolution.game.Coordinator;
import com.jtskywalker.civolution.game.GameMap;
import com.jtskywalker.civolution.game.SqCoordinates;
import com.jtskywalker.civolution.game.SqDirection;

/**
 *
 * @author jt
 */
public class InflGameMap extends GameMap<SqCoordinates,SqDirection,Body> {
    
    private final Terrain[][] terrain;
    private final Flora[][] flora;
    
    /*
    Constraints:
        1. terrain, flora and super.coordinator have all the same width & height
        2. there is no null pointer in either terrain & flora
    */
    
    /**
     * Creates a new map for the influence game.
     * Every tile is set to be plain grassland.
     * @param coordinator - to compute coordinate changes
     */
    public InflGameMap(Coordinator<SqCoordinates, SqDirection> coordinator) {
        super(coordinator);
        final int width = coordinator.getWidth();
        final int height = coordinator.getHeight();
        terrain = new Terrain[width][height];
        flora = new Flora[width][height];
        for (int x=0; x<width; x++)
            for (int y=0; y<height; y++) {
                terrain[x][y] = Terrain.PLAIN;
                flora[x][y] = Flora.GRASSLAND;
            }
    }
    
    Terrain getTerrain(SqCoordinates position) {
        return terrain[position.getX()][position.getY()];
    }
    
    boolean isSea(SqCoordinates position) {
        switch (getTerrain(position)) {
            case SEA:
                return true;
            default:
                return false;
        }
    }
    
    Flora getFlora(SqCoordinates position) {
        return flora[position.getX()][position.getY()];
    }
    
}
