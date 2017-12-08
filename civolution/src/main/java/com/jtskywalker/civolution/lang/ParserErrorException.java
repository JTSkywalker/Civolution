/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.lang;

/**
 *
 * @author rincewind
 */
public class ParserErrorException extends CivoLangException {

    public ParserErrorException(String msg) {
        super(msg);
    }

    public ParserErrorException() {
        super();
    }
    
}
