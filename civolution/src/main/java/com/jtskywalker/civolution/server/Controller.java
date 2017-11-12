/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.server;

import com.jtskywalker.civolution.game.Pawn;
import com.jtskywalker.civolution.game.Game;

/**
 *
 * @author rincewind
 */
public class Controller {
    
    final Game game;
    
    //Integer denotes turns the actor has to pause
    final DelayedQueue<Actor> actors;

    public Controller(Game game) {
        this.game = game;
        actors = new DelayedQueue(game.getActors());
    }
    
    public void notifyNextActor() {
        Actor next = actors.stepToNext();
        next.findNextAction(game.computeHorizon(next.getPawn()));
    }
    
    public void executeAction(Actor actor, Action action) {
        if (actors.getDelay(actor) == 0) {
            Pawn pawn = actor.getPawn();
            try { 
                int pause = action.execute(game, pawn);
                actors.put(actor, pause);
            } catch (ActionNotAllowedException ex) {
                actor.findNextAction(game.computeHorizon(pawn));
            }
        }
        notifyNextActor();
    }
    
    public void addActor(Actor human) {
        actors.put(human, 0);
    }
    
}
