/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.demogame;

/**
 *
 * @author jt
 */
public interface DemogameVisitor {
    
    public void visit(DemoGame game);
    
    public void visit(Body body);

    public void visit(HorizonImpl aThis);

    
}
