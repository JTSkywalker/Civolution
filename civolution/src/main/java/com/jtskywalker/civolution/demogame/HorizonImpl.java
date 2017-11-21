/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.demogame;

import com.jtskywalker.civolution.controller.Actor;
import com.jtskywalker.civolution.game.Coordinates;
import com.jtskywalker.civolution.game.Horizon;
import com.jtskywalker.civolution.game.Tile;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import javafx.scene.image.Image;
import javafx.util.Pair;


public class HorizonImpl implements Horizon {
    
    final int width, height;
    final Map<Actor, Pair<BodyImpl,Coordinates>> visible = new HashMap<>();

    public HorizonImpl(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void putActor(Actor actor, BodyImpl body, Coordinates coord) {
        visible.put(actor, new Pair(body, coord));
    }

    private boolean isQueen(BodyImpl c) {
        return c.getBaseStrength() == 20
                && c.getBaseMobility() == 20;
    }
    
    private boolean isWarrior(BodyImpl c) {
        return c.getBaseStrength() == 20
                && c.getBaseMobility() == 10;
    }
    
    private boolean isScout(BodyImpl c) {
        return c.getBaseStrength() == 10
                && c.getBaseMobility() == 20;
    }
    
    private boolean isSettler(BodyImpl c) {
        return c.getBaseStrength() == 5
                && c.getBaseMobility() == 5;
    }

    @Override
    public Tile getTile(Coordinates coord) {
        Set<BodyImpl> localBodies = visible.values().stream()
                .filter((b) -> b.getValue().equals(coord))
                .map((b) -> b.getKey())
                .collect(Collectors.toSet());
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
            return new TileImpl(new Image(base + "queen.png"));
        if (queens == 0 && warriors == 1 && scouts == 0 && settlers == 0) 
            return new TileImpl(new Image(base + "warrior.png"));
        if (queens == 0 && warriors == 0 && scouts == 1 && settlers == 0) 
            return new TileImpl(new Image(base + "scout.png"));
        if (queens == 1 && warriors == 0 && scouts == 0 && settlers == 1) 
            return new TileImpl(new Image(base + "settler.png"));
        
        if (queens == 0 && warriors == 0 && scouts == 0 && settlers == 0) 
            return new TileImpl(new Image(base + "empty.png"));
        
        return new TileImpl(new Image(base + "more.png"));
    }
}
