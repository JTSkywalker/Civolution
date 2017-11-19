/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.controller;

import com.jtskywalker.civolution.game.Body;
import com.jtskywalker.civolution.game.DemoGame;

/**
 *
 * @author rincewind
 */
public interface Action {
    
    public int execute(DemoGame game, Actor actor) throws ActionNotAllowedException;
    
}
