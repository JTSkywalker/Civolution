/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.game;

import com.jtskywalker.civolution.server.Actor;

/**
 *
 * @author rincewind
 */
public class CounterFactory {
    
    public static Pawn createWarrior(int nation, Actor actor) {
        Pawn c = new Pawn(nation, 20, 10, 3, true, actor);
        c.getActor().setPawn(c);
        return c;
    }
    
    public static Pawn createScout(int nation, Actor actor) {
        Pawn c = new Pawn(nation, 10, 20, 2, true, actor);
        c.getActor().setPawn(c);
        return c;
    }
    
    public static Pawn createQueen(int nation, Actor actor) {
        Pawn c = new Pawn(nation, 20, 20, 1, true, actor);
        c.getActor().setPawn(c);
        return c;
    }
    
    public static Pawn createSettler(int nation, Actor actor) {
        Pawn c = new Pawn(nation, 5, 5, 10, false, actor);
        c.getActor().setPawn(c);
        return c;
    }
     
}
