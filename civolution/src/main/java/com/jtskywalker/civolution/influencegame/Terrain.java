/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.influencegame;

/**
 *
 * @author jt
 */
enum Terrain {
    
    /**
     * Hills are a little more difficult to pass, but not dangerous at all.
     */
    HILLS(2,0),
    
    /**
     * Mountains are very difficult and dangerous to pass.
     * Furthermore they are not passable at all for some units.
     */
    MOUNTAINS(4,10),
    
    /**
     * Plains are easy to pass and not dangerous at all.
     */
    PLAIN(1,0),
    
    /**
     * Swampland is difficult to pass and also quite dangerous.
     * Furthermore it is not passable at all for some units.
     */
    SWAMP(3,10),
    
    /**
     * The sea is only passable for nautical units.
     * Sometimes they fall victim to a storm.
     */
    SEA(1,2);
    
    final int exertion, mortality;
    
    /**
     * Creates a terrain with the given parameters.
     * @param exertion - how much effort it is to travel through these lands
     * @param danger - how dangerous it is to travel through these lands
     */
    Terrain (int exertion, int danger) {
        this.exertion = exertion;
        this.mortality= danger;
    }
    
}
