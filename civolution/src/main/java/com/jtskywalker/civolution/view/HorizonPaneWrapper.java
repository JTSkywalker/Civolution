/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.view;

import javafx.scene.layout.Pane;

/**
 *
 * @author jt
 * @param <T>
 */
public interface HorizonPaneWrapper<T> {
    
    public void update(T t);
    
    public Pane getPane();
    
}
