/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.view;

import com.jtskywalker.civolution.controller.Coordinates;
import com.jtskywalker.civolution.game.Horizon;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

/**
 *
 * @author jt
 */
public class MapPane extends GridPane {
    
    final static int CELLSIZE = 50;
    
    final int height, width;
    final TileImage[][] tile;

    public MapPane(int width, int height) {
        this.height = height;
        this.width = width;
        tile = new TileImage[width][height];
        for (int x=0; x < width; x++) {
            for (int y=0; y < height; y++) {
                tile[x][y] = new TileImage();
                this.add(tile[x][y], x, height - y - 1);
            }
        }
        this.setGridLinesVisible(true);
        for (int x=0; x < width; x++) {
            this.getColumnConstraints().add(new ColumnConstraints(CELLSIZE));
        }
        for (int y=0; y < height; y++) {
            this.getRowConstraints().add(new RowConstraints(CELLSIZE));
        }
    }
    
    public void updateView(Horizon horizon) {
        for (int x=0; x < width; x++) {
            for (int y=0; y < height; y++) {
                Coordinates coord = new Coordinates(x,y,width,height);
                tile[x][y].update(horizon.getTile(coord));
            }
        }
    }
    
}
