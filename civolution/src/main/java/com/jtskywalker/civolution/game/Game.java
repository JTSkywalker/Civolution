/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.game;

import com.jtskywalker.civolution.controller.Actor;
import java.util.Collection;

/**
 *
 * @author jt
 * @param <T>
 */
public interface Game<T extends Body> {
    
    public Horizon computeHorizon(Actor<T> actor);
    
    public Collection<Actor<T>> getActors();
    
    public int getWidth();
    
    public int getHeight();
    
}
