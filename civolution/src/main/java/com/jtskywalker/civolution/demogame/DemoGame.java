/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.demogame;

import com.jtskywalker.civolution.game.Coordinates;
import com.jtskywalker.civolution.controller.Actor;
import com.jtskywalker.civolution.game.Game;
import com.jtskywalker.civolution.game.Horizon;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This class contains all information on the game 
 * @author rincewind
 */
public class DemoGame implements Game<BodyImpl> {
    
    public static final String BASEPATH = "src/main/resources/demogame/";
    
    final int width, height;
    final Map<Actor<BodyImpl>,Coordinates> actors = new HashMap<>();

    public DemoGame(int width, int height) {
        this.width = width;
        this.height= height;
    }
        
    public List<Actor<BodyImpl>> getFriends(Actor<BodyImpl> actor) {
        return actors.keySet()
                .stream()
                .filter((c) -> c.getEmblem() == actor.getEmblem())
                .collect(Collectors.toList());
    }
    
    
    @Override
    public Horizon computeHorizon(Actor<BodyImpl> actor) {
        HorizonImpl horizon = new HorizonImpl(width, height);
        getFriends(actor).forEach((c) -> {
            horizon.putActor(c, getCoordinates(c));
        });
        return horizon;
    }

    public Coordinates getCoordinates(Actor<BodyImpl> actor) {
        return actors.get(actor);
    }

    public boolean hasEnemy(Coordinates coord, int nation) {
        return getActors(coord)
                .stream()
                .anyMatch((c) -> c.getEmblem() != nation);
    }

    public List<Actor<BodyImpl>> getActors(Coordinates coord) {
        return actors.keySet()
                .stream()
                .filter((c) -> (actors.get(c).equals(coord)))
                .collect(Collectors.toList());
    }
    
    public void putActor(Actor<BodyImpl> actor, Coordinates newC) {
        actors.put(actor, newC);
    }

    public int getTileMobilityCost(Coordinates coord) {
        return 1;
    }

    public Actor<BodyImpl> getDefender(Coordinates coord) {
        return getActors(coord)
                .stream()
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException());
    }

    public void setFitness(BodyImpl body, double d) {
        body.setFitness(d);
    }

    public void kill(Actor<BodyImpl> actor) {
        actors.remove(actor);
    }

    public Actor<BodyImpl> getSubordinate(Actor actor) {
        Coordinates coord = getCoordinates(actor);
        return getActors(coord)
                .stream()
                .filter((c) -> !c.equals(actor))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException()); 
    }

    @Override
    public Set<Actor<BodyImpl>> getActors() {
        return actors.keySet()
                .stream()
                .collect(Collectors.toSet());
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getWidth() {
        return width;
    }
    
    
}
