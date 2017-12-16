/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.lang;

import java.util.List;

/**
 * The external parser used to parse the game-specific language.
 * @author jt
 * @param <T> type of external statements
 */
public interface ExtParser<T> {

    public T parse(List<Token> block) throws ParserErrorException;
    
    public void setIntParser(Parser<T> internalParser);
    
}
