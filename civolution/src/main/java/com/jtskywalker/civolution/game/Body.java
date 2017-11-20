/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.game;

import com.jtskywalker.civolution.util.JSONable;
import javafx.scene.image.Image;

/**
 *
 * @author jt
 */
public interface Body extends JSONable {
    
    public Image getImage();

    public int getEmblem();
    
}
