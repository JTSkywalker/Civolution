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
 */
public class Skip<T extends Game> implements Action<T> {

    @Override
    public int execute(T game, Actor actor) throws ActionNotAllowedException {
        return 1;
    }
    
}
