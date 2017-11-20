/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.demogame;

import com.jtskywalker.civolution.game.Tile;
import javafx.scene.image.Image;
import org.json.simple.JSONArray;

/**
 *
 * @author jt
 */
public class TileImpl implements Tile {
    
    private static final String MOREPATH = DemoGame.BASEPATH + "more.png";
    
    private final Image image;
    
    public TileImpl(Image image) {
        this.image = image;
    }

    @Override
    public Image getImage() {
        return image;
    }

    @Override
    public JSONArray getJSON() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
