/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.controller;

/**
 *
 * @author jt
 */
public class ActionNotAllowedException extends Exception {
    
    public ActionNotAllowedException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public ActionNotAllowedException(String msg) {
        super(msg);
    }
    
}
