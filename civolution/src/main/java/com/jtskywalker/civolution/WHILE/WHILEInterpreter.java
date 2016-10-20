/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.WHILE;

import com.jtskywalker.civolution.lang.CivoLangException;
import com.jtskywalker.civolution.lang.Interpreter;
import com.jtskywalker.civolution.lang.Statement;

/**
 *
 * @author rincewind
 * @param <T>
 */
public class WHILEInterpreter<T> implements Interpreter<T> {
    
    Interpreter<T> externalInterpreter;
    
    public WHILEInterpreter(Interpreter<T> externalInterpreter) {
        this.externalInterpreter = externalInterpreter;
    }

    @Override
    public Statement<T> nextExternal(String input) throws CivoLangException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
