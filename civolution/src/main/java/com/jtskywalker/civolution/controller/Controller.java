/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.controller;

import com.jtskywalker.civolution.util.DelayedQueue;
import com.jtskywalker.civolution.game.Game;

/**
 *
 * @author rincewind
 */
public class Controller {
    
    private Game game;
    
    //Integer denotes turns the actor has to pause
    final DelayedQueue<Actor> actors = new DelayedQueue();
    
    public Controller() {
        
    }

    public void init(Game game) {
        this.game = game;
        this.actors.addAll(game.getActors());
    }
    
    public void notifyNextActor() {
        checkInitialised();
        Actor next = actors.stepToNext();
        next.findNextAction(game.computeHorizon(next));
    }
    
    public void executeAction(Actor actor, Action action) {
        checkInitialised();
        if (actors.getDelay(actor) == 0) {
            try { 
                int pause = action.execute(game, actor);
                actors.put(actor, pause);
            } catch (ActionNotAllowedException ex) {
                actor.findNextAction(game.computeHorizon(actor));
            }
        }
        notifyNextActor();
    }
    
    public void addActor(Actor human) {
        checkInitialised();
        actors.put(human, 0);
    }

    private void checkInitialised() throws IllegalStateException {
        if (game == null || actors == null) {
            throw new IllegalStateException("Controller not initialised.");
        }
    }
    
    public int getWidth() {
        checkInitialised();
        return game.getWidth();
    }
    
    public int getHeight() {
        checkInitialised();
        return game.getHeight();
    }
    
}
