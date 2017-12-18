/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.demogame;

import com.jtskywalker.civolution.controller.Actor;
import com.jtskywalker.civolution.game.GameMap;
import com.jtskywalker.civolution.game.SqCoordinates;
import com.jtskywalker.civolution.game.Horizon;
import com.jtskywalker.civolution.game.SqDirection;
import java.util.Set;


public class HorizonImpl<T extends DemogameVisitor> implements Horizon<T> {
    
    final GameMap<SqCoordinates,SqDirection,Body,DemogameVisitor> visible;

    public HorizonImpl(GameMap<SqCoordinates,SqDirection,Body,DemogameVisitor> map) {
        this.visible = map;
    }

    public Set<Actor> getActors() {
        return visible.getActors();
    }

    public int getWidth() {
        return visible.getWidth();
    }

    public int getHeight() {
        return visible.getHeight();
    }

    @Override
    public void accept(T visitor) {
        visible.accept(visitor);
        visitor.visit(this);
    }

    public Set<Body> getBodies(SqCoordinates coord) {
        return visible.getBodies(coord);
    }
    
}
