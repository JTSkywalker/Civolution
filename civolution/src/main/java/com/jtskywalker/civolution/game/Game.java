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
 */
public interface Game {
    /*
    For the record: Horizon is not a subclass of Game since Horizon should only
    be able to gather information, while Game is also allowed to alter the model.
    Game is not a subclass of Horizon because Game contains only the current 
    state of the "game" - Horizon on the other hand can contain some information
    from the past.
    The only thing that would be possible is creating a new class "Knowledge"
    which contains the Horizon plus other information. In this case it might be 
    possible that Game inherits from Horizon.
    But I think the current solution is more flexible.
    */
    
    public Horizon computeHorizon(Actor actor);
    
    public Collection<Actor> getActors();
    
    public int getWidth();
    
    public int getHeight();
    
}
