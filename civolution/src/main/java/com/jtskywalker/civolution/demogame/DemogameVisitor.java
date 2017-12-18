/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.demogame;

import com.jtskywalker.civolution.game.GameMap;
import com.jtskywalker.civolution.game.SqCoordinates;
import com.jtskywalker.civolution.game.SqDirection;
import com.jtskywalker.civolution.game.Visitor;

/**
 *
 * @author jt
 */
public interface DemogameVisitor 
        extends Visitor<GameMap<SqCoordinates,SqDirection,Body,DemogameVisitor>> {
    // because of type erasure I need this class and can't do something like that:
    // ... extends Visitor<HorizonImpl>, Visitor<GameMap>, Visitor<Body> ...
    // so don't delete it again.
    
    public void visit(HorizonImpl horizon);
    
    public void visit(Body body);
    
    @Override
    public void visit(GameMap gamemap);

}
