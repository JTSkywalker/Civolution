/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.game;

import com.jtskywalker.civolution.server.Coordinates;
import com.jtskywalker.civolution.server.Actor;
import com.jtskywalker.civolution.server.Horizon;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This class contains all information on the game 
 * @author rincewind
 */
public class Game {
    
    final int width, height;
    final Map<Pawn,Coordinates> counters = new HashMap<>();

    public Game(int width, int height) {
        this.width = width;
        this.height= height;
    }
        
    public List<Pawn> getFriends(Pawn counter) {
        return counters.keySet()
                .stream()
                .filter((c) -> c.getEmblem() == counter.getEmblem())
                .collect(Collectors.toList());
    }
    
    
    public Horizon computeHorizon(Pawn counter) {
        Horizon horizon = new HorizonImpl(width, height);
        for (Pawn c : getFriends(counter)) {
            horizon.putCounter(c, getCoordinates(c));
        }
        return horizon;
    }

    public Coordinates getCoordinates(Pawn counter) {
        return counters.get(counter);
    }

    public boolean hasEnemy(Coordinates coord, int nation) {
        return getCounters(coord)
                .stream()
                .anyMatch((c) -> c.getEmblem() != nation);
    }

    public List<Pawn> getCounters(Coordinates coord) {
        return counters.keySet()
                .stream()
                .filter((c) -> (counters.get(c).equals(coord)))
                .collect(Collectors.toList());
    }
    
    public void putCounter(Pawn counter, Coordinates newC) {
        counters.put(counter, newC);
    }

    public int getTileMobilityCost(Coordinates coord) {
        return 1;
    }

    public Pawn getDefender(Coordinates coord) {
        return getCounters(coord)
                .stream()
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException());
    }

    public void setFitness(Pawn counter, double d) {
        counter.setFitness(d);
    }

    public void kill(Pawn counter) {
        counters.remove(counter);
    }

    public Pawn getSubordinate(Pawn counter) {
        Coordinates coord = getCoordinates(counter);
        return getCounters(coord)
                .stream()
                .filter((c) -> !c.equals(counter))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException()); 
    }

    public Set<Actor> getActors() {
        return counters.keySet()
                .stream()
                .map((c) -> c.getActor())
                .collect(Collectors.toSet());
    }
    
    
}
