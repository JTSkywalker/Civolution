/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.game;

import com.jtskywalker.civolution.controller.Actor;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;
import javafx.util.Pair;

/**
 * Map to use for every map-based game.
 * @author jt
 * @param <C> type of the Coordinates
 * @param <D> type of directions
 * @param <B> type of the Body
 * @param <T> type of the visitor for this GameMap
 */
public class GameMap<C extends Coordinates,D,B extends Visitable<T>,
        T extends Visitor<GameMap<C,D,B,T>>>
        implements Visitable<T> {
    
    //TODO: maybe these maps should be body-focused and not actor-focused.
    final Map<Actor,B> bodies = new HashMap<>();
    final Map<Actor,C> positions = new HashMap<>();
    final Coordinator<C,D> coordinator;
    
    
    /**
     * Constructor.
     * @param coordinator coordinator for the kind of coordinates used
     */
    public GameMap(Coordinator<C,D> coordinator) {
        this.coordinator = coordinator;
    }
    
    /**
     * Constructor that adds all given actors with their positions and bodies
     * @param coordinator coordinator for the kind of coordinates used
     * @param actors to be added to the GameMap
     */
    public GameMap(Coordinator<C,D> coordinator, Map<Actor,Pair<B,C>> actors) {
        this(coordinator);
        putAll(actors);
    }
    
    /**
     * Getter for the body of the given actor.
     * @param actor actor whose body is looked up
     * @return body of the actor
     */
    public B getBody(Actor actor) {
        return bodies.get(actor);
    }
    
    /**
     * Getter for the position of the given actor.
     * @param actor actor whose position is looked up
     * @return position of the actor
     */
    public C getPosition(Actor actor) {
        return positions.get(actor);
    }
    
    /**
     * Getter for the position of the given body.
     * @param body body whose position is looked up
     * @return position of the body
     */
    public C getPosition(B body) {
        return positions.get(getActor(body));
    }
    
    /**
     * Getter of the set of actors at the given position.
     * @param position position to look for actors
     * @return set of actors who are at the given position
     */
    public Set<Actor> getActors(C position) {
        return positions.entrySet().stream().filter((entry) -> {
           return entry.getValue().equals(position); 
        }).map((entry) -> entry.getKey()).collect(Collectors.toSet());
    }
    
    /**
     * Getter of the set of bodies at the given position.
     * @param position position to look for bodies
     * @return set of bodies who are at the given position
     */
    public Set<B> getBodies(C position) {
        return positions.entrySet().stream().filter((entry) -> {
           return entry.getValue().equals(position); 
        }).map((entry) -> entry.getKey())
                .map((actor) -> getBody(actor)).collect(Collectors.toSet());
    }
    
    /**
     * Return all bodies that are on the map.
     * @return all bodies on the map
     */
    public Set<B> getBodies() {
        return new HashSet<>(bodies.values());
    }
    
    /**
     * Getter of the actor corresponding to a body.
     * @param body the body whose actor is looked up
     * @return the actor of the body
     * @throws GameInconsistencyException if there is more than one actor 
     * corresponding to the body
     * @throws IllegalArgumentException if there is no actor for this body
     */
    public Actor getActor(B body) {
        List<Actor> actors = bodies.entrySet().stream()
                .filter((entry) -> entry.getValue().equals(body))
                .map((entry) -> entry.getKey())
                .collect(Collectors.toList());
        if (actors.size() == 1) {
            return actors.get(0);
        } else if (actors.isEmpty())
            throw new NoSuchElementException(
                "There is no Actor for this Body: " + body.toString()
            );
        else 
            throw new GameInconsistencyException(
                "There is more than one Actor for this body." + body.toString()
            );
    }
    
    private void checkConsistentPut(Actor actor, B body) {
        if (bodies.entrySet().stream()
                .anyMatch((entry) 
                        -> !entry.getKey().equals(actor) 
                                && entry.getValue().equals(body)))
            throw new GameInconsistencyException(
                    "Map already contains this Body: " + body.toString()
            );
    }
    
    /**
     * Assign the body to the actor in this GameMap. This overrides previous
     * assignments.
     * @param actor the actor to put
     * @param body the body to put
     */
    public void put(Actor actor, B body) {
        checkConsistentPut(actor, body);
        bodies.put(actor, body);
    }
    
    /**
     * Put the actor at the position. This removes the old position of the actor.
     * @param actor actor to move
     * @param position new position of the actor.
     */
    public void put(Actor actor, C position) {
        positions.put(actor, position);
    }
    
    /**
     * Put the actor of {@code body} at {@code position}.
     * This removes the old position of the actor.
     * @param body to move
     * @param position new position of the actor.
     */
    public void put(B body, C position) {
        positions.put(getActor(body), position);
    }
    
    /**
     * Put the actor at the position and the assign the body. This overrides
     * previous assignments.
     * @param actor actor to put
     * @param body body to assign
     * @param position new position
     */
    public void put(Actor actor, B body, C position) {
        checkConsistentPut(actor, body);
        bodies.put(actor, body);
        positions.put(actor, position);
    }
    
    /**
     * Put all the actors at the assigned position and assign the corresponding
     * body. This overrides previous assignments.
     * @param actors mapping to be included to this GameMap
     */
    public final void putAll(Map<Actor,Pair<B,C>> actors) {
        actors.entrySet().stream().forEach((entry) -> {
            Actor actor = entry.getKey();
            B body = entry.getValue().getKey();
            C position = entry.getValue().getValue();
            checkConsistentPut(actor, body);
            bodies.put(actor,body);
            positions.put(actor,position);
        });
    }
    
    /**
     * Remove {@code actor} from this game.
     * @param actor to remove
     */
    public void remove(Actor actor) {
        bodies.remove(actor);
        positions.remove(actor);
    }
    
    /**
     * Remove {@code body} from this game.
     * @param body to remove
     */
    public void remove(B body) {
        Actor actor = getActor(body);
        bodies.remove(actor);
        positions.remove(actor);
    }
    
    /**
     * Add step in given direction to {@code position}.
     * @param position position to start
     * @param direction direction to go 
     * @return new Coordinates object where teh step is done.
     */
    public C plusStep(C position, D direction) {
        return coordinator.plusStep(position, direction);
    }

    /**
     * Return the set of actors.
     * @return set of {@code Actor} on this {@code GameMap}
     */
    public Set<Actor> getActors() {
        assert(bodies.keySet().equals(positions.keySet()));
        return bodies.keySet();
    }
    
    /**
     * Return width of this map.
     * @return width of this map 
     */
    public int getWidth() {
        return coordinator.getWidth();
    }
    
    /**
     * Return height of this map.
     * @return height of this map 
     */
    public int getHeight() {
        return coordinator.getHeight();
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void accept(T visitor) {
        bodies.values().stream()
                .forEach((body) -> body.accept(visitor));
        visitor.visit(this);
    }
    
}
