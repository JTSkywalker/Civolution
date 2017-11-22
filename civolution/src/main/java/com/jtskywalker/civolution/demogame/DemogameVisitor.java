/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.demogame;

import com.jtskywalker.civolution.game.Visitor;

/**
 *
 * @author jt
 */
public interface DemogameVisitor extends Visitor<HorizonImpl> {
    
    @Override
    public void visit(HorizonImpl horizon);
    
    public void visit(Body body);

}
