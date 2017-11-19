/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.game;

import com.jtskywalker.civolution.controller.Actor;

/**
 *
 * @author rincewind
 */
public class BodyFactory {
    
    public static Body createWarrior(int nation) {
        return new Body(nation, 20, 10, 3, true);
    }
    
    public static Body createScout(int nation) {
        return new Body(nation, 10, 20, 2, true);
    }
    
    public static Body createQueen(int nation) {
        return new Body(nation, 20, 20, 1, true);
    }
    
    public static Body createSettler(int nation) {
        return new Body(nation, 5, 5, 10, false);
    }
     
}
