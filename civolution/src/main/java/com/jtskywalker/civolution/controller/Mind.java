/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.controller;

import com.jtskywalker.civolution.lang.Statement;

/**
 *
 * @author jt
 */
public interface Mind {
    
    public Action nextAction(Horizon horizon);
    
    public boolean receiveOrders(Statement<Action> orders, int nation);

    public void setOrders(Statement<Action> orders);
    
}
