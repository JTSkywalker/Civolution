/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.lang;

import java.util.List;

/**
 * The parser to parse WHILE.
 * @author jt
 * @param <T> type of the game-specific language
 */
public interface Parser<T> {

    public Statement<T> parse(List<Token> tokenlist) 
            throws ParserErrorException;
    
    
    public Expression<T> parseExp(List<Token> tokenlist) 
            throws ParserErrorException;
    
}
