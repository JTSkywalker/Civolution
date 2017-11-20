/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.game;

import javafx.scene.image.Image;
import org.json.simple.JSONArray;

/**
 *
 * @author jt
 */
public interface Tile {
    
    public Image getImage();
    
    public JSONArray getJSON();
    
}
