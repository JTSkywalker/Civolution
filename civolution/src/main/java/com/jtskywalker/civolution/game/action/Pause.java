/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.game.action;

import com.jtskywalker.civolution.game.Pawn;
import com.jtskywalker.civolution.game.Game;
import com.jtskywalker.civolution.server.Action;
import com.jtskywalker.civolution.server.ActionNotAllowedException;

/**
 *
 * @author rincewind
 */
public class Pause implements Action {

    @Override
    public int execute(Game game, Pawn counter) throws ActionNotAllowedException {
        return 1;
    }
    
}
