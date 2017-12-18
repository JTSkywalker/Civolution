/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.influencegame;

import com.jtskywalker.civolution.game.GameMap;
import com.jtskywalker.civolution.game.SqCoordinates;
import com.jtskywalker.civolution.game.SqDirection;
import com.jtskywalker.civolution.game.Visitor;

/**
 *
 * @author jt
 */
public interface InflGameVisitor 
        extends Visitor<GameMap<SqCoordinates, SqDirection, Body, InflGameVisitor>> {
    
}
