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
enum Flora {
    
    /**
     * Forest.
     */
    FOREST(2),
    
    /**
     * Grassland.
     */
    GRASSLAND(1),
    
    /**
     * Desert.
     */
    DESERT(0);
    
    final int fertility;
    
    /**
     * Creates a {@code Flora} with the given {@code fertility}
     * @param fertility - fertility of the {@code Flora}
     */    
    Flora(int fertility) {
        this.fertility = fertility;
    }
    
}
