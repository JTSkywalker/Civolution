/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.view;

import com.jtskywalker.civolution.controller.Horizon;
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
    final Tile[][] tile;

    public MapPane(int width, int height) {
        this.height = height;
        this.width = width;
        tile = new Tile[height][width];
        for (int i=0; i < height; i++) {
            for (int j=0; j < width; j++) {
                tile[i][j] = new Tile(0,0,0,0);
                this.add(tile[i][j], i, width - j - 1);
            }
        }
        this.setGridLinesVisible(true);
        for (int i=0; i < height; i++) {
            this.getColumnConstraints().add(new ColumnConstraints(CELLSIZE));
        }
        for (int j=0; j < width; j++) {
            this.getRowConstraints().add(new RowConstraints(CELLSIZE));
        }
    }
    
    public void updateView(Horizon horizon) {
        for (int i=0; i < height; i++) {
            for (int j=0; j < width; j++) {
                tile[i][j].setSettlers(horizon.getSettlersAt(i,j));
                tile[i][j].setWarriors(horizon.getWarriorsAt(i,j));
                tile[i][j].setScouts(horizon.getScoutsAt(i,j));
                tile[i][j].setQueens(horizon.getQueensAt(i,j));
            }
        }
    }
    
}
