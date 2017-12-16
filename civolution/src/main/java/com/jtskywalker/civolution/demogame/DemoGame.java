/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.demogame;

import com.jtskywalker.civolution.game.SqCoordinates;
import com.jtskywalker.civolution.controller.Actor;
import com.jtskywalker.civolution.controller.ExternalMind;
import com.jtskywalker.civolution.controller.Subordinate;
import com.jtskywalker.civolution.game.Game;
import com.jtskywalker.civolution.game.GameMap;
import com.jtskywalker.civolution.game.Horizon;
import com.jtskywalker.civolution.game.SqDirection;
import com.jtskywalker.civolution.game.SqTorusCoordinator;
import com.jtskywalker.civolution.view.GameUI;
import com.jtskywalker.civolution.view.HorizonPaneWrapper;
import com.jtskywalker.civolution.view.demogame.DemogameMapVisitor;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Pair;

/**
 * This class contains all information on the game.
 * @author jt
 */
public class DemoGame implements Game {
    
    /**
     * Base path of the resources for this game.
     */
    public static final String BASEPATH = "src/main/resources/demogame/";
    
    final GameMap<SqCoordinates,SqDirection,Body> map;

    /**
     * Constructor.
     * @param map - the map with the positions and properties of the actors
     */
    public DemoGame(GameMap map) {
        this.map = map;
    }
        
    /**
     * Returns all the actors of the map in the same team of {@code actor}.
     * @param actor - the {@code Actor} whose friends are looked for
     * @return {@code List} of {@code Actor} who are in the same team as 
     * {@code actor}
     */
    public List<Actor> getFriends(Actor actor) {
        return map.getActors()
                .stream()
                .filter((c) 
                         -> getBody(c).getEmblem() == getBody(actor).getEmblem())
                .collect(Collectors.toList());
    }
    
    /**
     * {@inheritDoc }
     */
    @Override
    public Horizon computeHorizon(Actor actor) {
        HorizonImpl horizon = new HorizonImpl(map.getWidth(), map.getHeight());
        getFriends(actor).forEach((c) -> {
            horizon.putActor(c, getBody(c), getCoordinates(c));
        });
        return horizon;
    }

    /**
     * Return the position of {@code actor}
     * @param actor - the actor whose position is looked for
     * @return the position of {@code actor}
     */
    public SqCoordinates getCoordinates(Actor actor) {
        return map.getPosition(actor);
    }

    /**
     * Returns {@code true} iff there is a {@code Body} on {@code position}
     * of nation that differs from {@code nation}.
     * @param position - the position to consider
     * @param nation - the nation of the asker
     * @return {@code true} iff {@code there is a {@code Body} on {@code position}
     * of nation that differs from {@code nation}.
     */
    public boolean hasEnemy(SqCoordinates position, int nation) {
        return getActors(position)
                .stream()
                .anyMatch((c) -> map.getBody(c).getEmblem() != nation);
    }

    /**
     * Return {@code Set} of {@code Actor} that are present on {@code position}
     * @param position - the position to look for
     * @return {@code Set} of {@code Actor} that are present on {@code position}
     */
    public Set<Actor> getActors(SqCoordinates position) {
        return map.getActors()
                .stream()
                .filter((c) -> (map.getPosition(c).equals(position)))
                .collect(Collectors.toSet());
    }
    
    /**
     * Puts {@code actor} at {@code newC}.
     * This can either introduce a new actor 
     * or move one that is already present.
     * @param actor - actor to move.
     * @param newC - new position
     */
    public void putActor(Actor actor, SqCoordinates newC) {
        map.put(actor, newC);
    }

    /**
     * Return tile mobility cost of {@code position}
     * @param position
     * @return tile mobility cost of {@code position} 
     */
    public int getTileMobilityCost(SqCoordinates position) {
        return 1;
    }

    /**
     * Return the defending {@code Actor} on {@code position}.
     * @param position - the position that is attacked.
     * @return the defending {@code Actor} on {@code position}.
     */
    public Actor getDefender(SqCoordinates position) {
        return getActors(position)
                .stream()
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException());
    }

    /**
     * Set the fitness of {@code body} to {@code fitness}.
     * @param body - body to modify
     * @param fitness - new fitness value
     */
    public void setFitness(Body body, double fitness) {
        body.setFitness(fitness);
    }

    /**
     * Remove {@code actor} from the game.
     * @param actor - {@code Actor} to kill
     */
    public void kill(Actor actor) {
        map.remove(actor);
    }

    public Actor getSubordinate(Actor actor) {
        SqCoordinates coord = getCoordinates(actor);
        return getActors(coord)
                .stream()
                .filter((c) -> !c.equals(actor))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException()); 
    }

    @Override
    public Set<Actor> getActors() {
        return map.getActors()
                .stream()
                .collect(Collectors.toSet());
    }

    @Override
    public int getHeight() {
        return map.getHeight();
    }

    @Override
    public int getWidth() {
        return map.getWidth();
    }

    public Body getBody(Actor actor) {
        return map.getBody(actor);
    }
     
    /**
     * Add step in given direction to {@code position}.
     * @param position - position to start
     * @param direction - direction to go 
     * @return new Coordinates object where the step is done.
     */
    public SqCoordinates plusStep(
            SqCoordinates position,SqDirection direction) {
        return map.plusStep(position, direction);
    }
    
    public static DemoGame createDemo(int width, int height, GameUI gameUI) {
        HashMap<Actor,Pair<Body,SqCoordinates>> actors = new HashMap();
        BodyFactory bf = new BodyFactory();
        
        Body queen = bf.create("queen", 0);
        Body warrior = bf.create("warrior", 0);
        Body scout = bf.create("scout", 0);

        Actor sub1 = new Subordinate(0);
        Actor sub2 = new Subordinate(0);
        
        actors.put(gameUI, new Pair(queen, new SqCoordinates(1,1)));
        actors.put(sub1,  new Pair(warrior, new SqCoordinates(3, 3)));
        actors.put(sub2,  new Pair(scout, new SqCoordinates(0, 5)));
        
        return new DemoGame(new GameMap(
                new SqTorusCoordinator(width,height), actors));
    }
    
}
