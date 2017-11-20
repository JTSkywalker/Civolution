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
import com.jtskywalker.civolution.util.HashMapSet;
import java.util.Set;
import javafx.scene.image.Image;


public class HorizonImpl implements Horizon {
    
    final int width, height;
    final HashMapSet<Coordinates, Actor<BodyImpl>> visible = new HashMapSet<>();

    public HorizonImpl(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void putActor(Actor actor, Coordinates coord) {
        visible.put(coord, actor);
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
        Set<Actor<BodyImpl>> localActors = visible.get(coord);
        int queens = (int) localActors.stream().filter((a) -> isQueen(a.getBody())).count();
        int warriors = (int) localActors.stream().filter((a) -> isWarrior(a.getBody())).count();
        int scouts = (int) localActors.stream().filter((a) -> isScout(a.getBody())).count();
        int settlers = (int) localActors.stream().filter((a) -> isSettler(a.getBody())).count();
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
