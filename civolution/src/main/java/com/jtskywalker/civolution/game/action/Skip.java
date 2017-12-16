/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.game.action;

import com.jtskywalker.civolution.controller.Action;
import com.jtskywalker.civolution.controller.Actor;
import com.jtskywalker.civolution.game.Game;

/**
 * Action that makes the player skip one turn.
 * @author jt
 * @param <T> type of the game-specific language
 */
public class Skip<T extends Game> implements Action<T> {

    /**
     * Return 1.
     * The game is not modified at all.
     * @param game not relevant
     * @param actor not relevant
     * @return 1
     */
    @Override
    public int execute(T game, Actor actor) {
        return 1;
    }
    
}
