/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.demogame;

import com.jtskywalker.civolution.controller.Actor;
import com.jtskywalker.civolution.game.Coordinates;
import com.jtskywalker.civolution.game.Horizon;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import javafx.util.Pair;


public class HorizonImpl implements Horizon<DemogameVisitor> {
    
    final int width, height;
    final Map<Actor, Pair<Body,Coordinates>> visible = new HashMap<>();

    public HorizonImpl(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void putActor(Actor actor, Body body, Coordinates coord) {
        visible.put(actor, new Pair(body, coord));
    }

    @Override
    public Horizon computeHorizon(Actor actor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<Actor> getActors() {
        return visible.keySet();
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void accept(DemogameVisitor t) {
        for (Pair<Body,Coordinates> pair : visible.values()) {
            pair.getKey().accept(t);
        }
        t.visit(this);
    }

    public Set<Body> getBodies(Coordinates coord) {
        return visible.values().stream()
                .filter((b) -> b.getValue().equals(coord))
                .map((b) -> b.getKey())
                .collect(Collectors.toSet());
    }
}
