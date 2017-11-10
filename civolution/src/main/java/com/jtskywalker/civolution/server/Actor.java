/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.server;

import com.jtskywalker.civolution.game.Pawn;
import com.jtskywalker.civolution.lang.Statement;

/**
 *
 * @author rincewind
 */
public abstract class Actor {
    
    Pawn pawn;

    public Pawn getPawn() {
        return pawn;
    }
    
    public void setPawn(Pawn pawn) {
        this.pawn = pawn;
    }
    
    public abstract Action nextAction(Horizon horizon);
    
    public boolean receiveOrders(Statement<Action> orders, int nation) {
        return false;
    }
    
}
