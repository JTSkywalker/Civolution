/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.game;

import com.jtskywalker.civolution.controller.Action;
import com.jtskywalker.civolution.controller.ActionNotAllowedException;
import com.jtskywalker.civolution.controller.Actor;

/**
 *
 * @author rincewind
 * @param <T>
 * @param <S>
 */
public class Skip<T extends Game, S extends Body> implements Action<T,S> {

    @Override
    public int execute(T game, Actor<S> actor) throws ActionNotAllowedException {
        return 1;
    }
    
}
