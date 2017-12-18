/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.influencegame;

import com.jtskywalker.civolution.game.Visitable;

/**
 *
 * @author jt
 */
class Body implements Visitable<InflGameVisitor> {
    
    private int team;

    public int getTeam() {
        return team;
    }

    boolean canMove(Terrain terrain) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void accept(InflGameVisitor visitor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
       
}
