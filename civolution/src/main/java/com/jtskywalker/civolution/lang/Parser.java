/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.lang;

import java.util.List;

/**
 *
 * @author rincewind
 */
public interface Parser<T> {

    public Statement<T> parse(List<Token> tokenlist) 
            throws ParserErrorException;
    
    
    public Expression<T> parseExp(List<Token> tokenlist) 
            throws ParserErrorException;
    
}
