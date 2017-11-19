/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.controller;

import com.jtskywalker.civolution.util.DelayedQueue;
import com.jtskywalker.civolution.game.Body;
import com.jtskywalker.civolution.game.DemoGame;

/**
 *
 * @author rincewind
 */
public class Controller {
    
    final DemoGame game;
    
    //Integer denotes turns the actor has to pause
    final DelayedQueue<Actor> actors;

    public Controller(DemoGame game) {
        this.game = game;
        actors = new DelayedQueue(game.getActors());
    }
    
    public void notifyNextActor() {
        Actor next = actors.stepToNext();
        next.findNextAction(game.computeHorizon(next));
    }
    
    public void executeAction(Actor actor, Action action) {
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
        actors.put(human, 0);
    }
    
    public int getWidth() {
        return game.getWidth();
    }
    
    public int getHeight() {
        return game.getHeight();
    }
    
}
