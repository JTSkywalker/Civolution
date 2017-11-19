/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.demogame.action;

import com.jtskywalker.civolution.demogame.Body;
import com.jtskywalker.civolution.demogame.DemoGame;
import com.jtskywalker.civolution.controller.Action;
import com.jtskywalker.civolution.controller.ActionNotAllowedException;
import com.jtskywalker.civolution.controller.Actor;

/**
 *
 * @author rincewind
 */
public class Pause implements Action {

    @Override
    public int execute(DemoGame game, Actor actor) throws ActionNotAllowedException {
        return 1;
    }
    
}
