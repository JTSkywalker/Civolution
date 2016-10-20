/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.server;

import com.jtskywalker.civolution.game.Counter;
import com.jtskywalker.civolution.game.Game;
import com.jtskywalker.civolution.lang.CivoLangException;
import com.jtskywalker.civolution.lang.Interpreter;
import com.jtskywalker.civolution.lang.Statement;

/**
 *
 * @author rincewind
 */
public class GameInterpreter {
    
    final Statement<Action> program;
    
    public GameInterpreter (Statement<Action> program) {
        this.program = program;
    }
    
    public Action nextAction(Game game, Counter counter, String input) 
            throws CivoLangException, ActionNotAllowedException {
        return null;
    }
    
}
