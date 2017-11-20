/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.view;

import com.jtskywalker.civolution.game.Tile;
import javafx.scene.image.ImageView;

/**
 *
 * @author jt
 */
public class TileImage extends ImageView {
        
    public TileImage() {
        
    }
    
    public void update(Tile tile) {
        setImage(tile.getImage());
    }
    
}
