/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.controller;

import com.jtskywalker.civolution.game.Game;

/**
 * The game-specific language should implement this interface with all its 
 * actions that modify the game.
 * @author jt
 * @param <T> type of game
 */
public interface Action<T extends Game> {
    
    public int execute(T game, Actor actor) throws ActionNotAllowedException;
    
}
