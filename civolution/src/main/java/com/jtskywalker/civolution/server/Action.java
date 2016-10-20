/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.server;

import com.jtskywalker.civolution.game.Counter;
import com.jtskywalker.civolution.game.Game;

/**
 *
 * @author rincewind
 */
public interface Action {
    
    public int execute(Game game, Counter counter) throws ActionNotAllowedException;
    
}
