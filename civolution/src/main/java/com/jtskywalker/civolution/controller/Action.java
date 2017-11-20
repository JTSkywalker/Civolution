/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.controller;

import com.jtskywalker.civolution.game.Body;
import com.jtskywalker.civolution.game.Game;

/**
 *
 * @author rincewind
 * @param <T>
 * @param <S>
 */
public interface Action<T extends Game, S extends Body> {
    
    public int execute(T game, Actor<S> actor) throws ActionNotAllowedException;
    
}
