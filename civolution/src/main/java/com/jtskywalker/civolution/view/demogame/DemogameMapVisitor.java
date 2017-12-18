/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.view.demogame;

import com.jtskywalker.civolution.demogame.Body;
import com.jtskywalker.civolution.demogame.DemoGame;
import com.jtskywalker.civolution.demogame.DemogameVisitor;
import com.jtskywalker.civolution.demogame.HorizonImpl;
import com.jtskywalker.civolution.game.GameMap;
import com.jtskywalker.civolution.game.SqCoordinates;
import java.util.Set;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import com.jtskywalker.civolution.view.HorizonPaneWrapper;
import javafx.scene.layout.Pane;


public class DemogameMapVisitor 
        implements HorizonPaneWrapper<HorizonImpl>, DemogameVisitor {

    private final static int CELLSIZE = 50;
    
    private final ImageView[][] tile;
    private final int width, height;
    private final GridPane map;
    
    public DemogameMapVisitor(GridPane map, int width, int height) {
        this.map = map;
        this.width = width;
        this.height= height;
        tile = new ImageView[width][height];
        for (int x=0; x < width; x++) {
            for (int y=0; y < height; y++) {
                tile[x][y] = new ImageView();
                map.add(tile[x][y], x, height - y - 1);
            }
        }
        map.setGridLinesVisible(true);
        for (int x=0; x < width; x++) {
            map.getColumnConstraints().add(new ColumnConstraints(CELLSIZE));
        }
        for (int y=0; y < width; y++) {
            map.getRowConstraints().add(new RowConstraints(CELLSIZE));
        }
    }
    
    @Override
    public void visit(Body body) {
        // do nothing.
    }

    @Override
    public void visit(HorizonImpl horizon) {
        for (int x=0; x < width; x++) {
            for (int y=0; y < height; y++) {
                SqCoordinates coord = new SqCoordinates(x,y);
                tile[x][y].setImage(determineImage(horizon, coord));
            }
        }
    }
    
    private boolean isQueen(Body c) {
        return c.getBaseStrength() == 20
                && c.getBaseMobility() == 20;
    }
    
    private boolean isWarrior(Body c) {
        return c.getBaseStrength() == 20
                && c.getBaseMobility() == 10;
    }
    
    private boolean isScout(Body c) {
        return c.getBaseStrength() == 10
                && c.getBaseMobility() == 20;
    }
    
    private boolean isSettler(Body c) {
        return c.getBaseStrength() == 5
                && c.getBaseMobility() == 5;
    }

    public Image determineImage(HorizonImpl horizon, SqCoordinates coord) {
        Set<Body> localBodies = horizon.getBodies(coord);
        int queens = (int) localBodies.stream().filter((a) 
                -> isQueen(a)).count();
        int warriors = (int) localBodies.stream().filter((a) 
                -> isWarrior(a)).count();
        int scouts = (int) localBodies.stream().filter((a) 
                -> isScout(a)).count();
        int settlers = (int) localBodies.stream().filter((a) 
                -> isSettler(a)).count();
        String base = "file:" + DemoGame.BASEPATH;
               
        if (queens == 1 && warriors == 0 && scouts == 0 && settlers == 0) 
            return new Image(base + "queen.png");
        if (queens == 0 && warriors == 1 && scouts == 0 && settlers == 0) 
            return new Image(base + "warrior.png");
        if (queens == 0 && warriors == 0 && scouts == 1 && settlers == 0) 
            return new Image(base + "scout.png");
        if (queens == 1 && warriors == 0 && scouts == 0 && settlers == 1) 
            return new Image(base + "settler.png");
        
        if (queens == 0 && warriors == 0 && scouts == 0 && settlers == 0) 
            return new Image(base + "empty.png");
        
        return new Image(base + "more.png");
    }

    @Override
    public void update(HorizonImpl horizon) {
        this.visit(horizon);
    }

    @Override
    public Pane getPane() {
        return map;
    }

    @Override
    public void visit(GameMap gamemap) {
        
    }
    
}
