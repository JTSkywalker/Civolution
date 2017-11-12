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
public interface Actor {
    
    public Pawn getPawn();
    
    public void setPawn(Pawn pawn);
    
    public abstract void findNextAction(Horizon horizon);
    
    public boolean receiveOrders(Statement<Action> orders, int nation);
    
}
