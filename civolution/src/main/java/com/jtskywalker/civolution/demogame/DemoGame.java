/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.demogame;

import com.jtskywalker.civolution.controller.Coordinates;
import com.jtskywalker.civolution.controller.Actor;
import com.jtskywalker.civolution.controller.Horizon;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This class contains all information on the game 
 * @author rincewind
 */
public class DemoGame {
    
    final int width, height;
    final Map<Actor,Coordinates> actors = new HashMap<>();

    public DemoGame(int width, int height) {
        this.width = width;
        this.height= height;
    }
        
    public List<Actor> getFriends(Actor actor) {
        return actors.keySet()
                .stream()
                .filter((c) -> c.getEmblem() == actor.getEmblem())
                .collect(Collectors.toList());
    }
    
    
    public Horizon computeHorizon(Actor actor) {
        Horizon horizon = new HorizonImpl(width, height);
        for (Actor c : getFriends(actor)) {
            horizon.putActor(c, getCoordinates(c));
        }
        return horizon;
    }

    public Coordinates getCoordinates(Actor actor) {
        return actors.get(actor);
    }

    public boolean hasEnemy(Coordinates coord, int nation) {
        return getActors(coord)
                .stream()
                .anyMatch((c) -> c.getEmblem() != nation);
    }

    public List<Actor> getActors(Coordinates coord) {
        return actors.keySet()
                .stream()
                .filter((c) -> (actors.get(c).equals(coord)))
                .collect(Collectors.toList());
    }
    
    public void putActor(Actor actor, Coordinates newC) {
        actors.put(actor, newC);
    }

    public int getTileMobilityCost(Coordinates coord) {
        return 1;
    }

    public Actor getDefender(Coordinates coord) {
        return getActors(coord)
                .stream()
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException());
    }

    public void setFitness(Body body, double d) {
        body.setFitness(d);
    }

    public void kill(Actor actor) {
        actors.remove(actor);
    }

    public Actor getSubordinate(Actor actor) {
        Coordinates coord = getCoordinates(actor);
        return getActors(coord)
                .stream()
                .filter((c) -> !c.equals(actor))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException()); 
    }

    public Set<Actor> getActors() {
        return actors.keySet()
                .stream()
                .collect(Collectors.toSet());
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
    
    
}
