/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.WHILE;

import com.jtskywalker.civolution.lang.Token;

/**
 *
 * @author rincewind
 */
public enum Keyword implements Token {
    
    WHILE, IF,
    PLUS, MINUS,
    LBRACE, RBRACE,
    DEF,
    NEQ, GT, LT, GEQ, LEQ, EQ, 
    QM, EM, 
    SEMICOLON;
}
