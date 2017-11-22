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
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import javafx.util.Pair;

/**
 * This class contains all information on the game 
 * @author rincewind
 */
public class DemoGame implements Game {
    
    public static final String BASEPATH = "src/main/resources/demogame/";
    
    final int width, height;
    final Map<Actor,Pair<Body,Coordinates>> actors;

    public DemoGame(int width, int height,
            Map<Actor,Pair<Body,Coordinates>> actors) {
        this.width = width;
        this.height= height;
        this.actors= actors;
    }
        
    public List<Actor> getFriends(Actor actor) {
        return actors.keySet()
                .stream()
                .filter((c) 
                         -> getBody(c).getEmblem() == getBody(actor).getEmblem())
                .collect(Collectors.toList());
    }
    
    
    @Override
    public Horizon computeHorizon(Actor actor) {
        HorizonImpl horizon = new HorizonImpl(width, height);
        getFriends(actor).forEach((c) -> {
            horizon.putActor(c, getBody(c), getCoordinates(c));
        });
        return horizon;
    }

    public Coordinates getCoordinates(Actor actor) {
        return actors.get(actor).getValue();
    }

    public boolean hasEnemy(Coordinates coord, int nation) {
        return getActors(coord)
                .stream()
                .anyMatch((c) -> actors.get(c).getKey().getEmblem() != nation);
    }

    public List<Actor> getActors(Coordinates coord) {
        return actors.keySet()
                .stream()
                .filter((c) -> (actors.get(c).equals(coord)))
                .collect(Collectors.toList());
    }
    
    public void putActor(Actor actor, Coordinates newC) {
        actors.put(actor, new Pair(actors.get(actor).getKey(),newC));
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

    @Override
    public Set<Actor> getActors() {
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

    public Body getBody(Actor actor) {
        return actors.get(actor).getKey();
    }
    
}
