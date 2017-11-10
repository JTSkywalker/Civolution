/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.server;

import com.jtskywalker.civolution.game.Pawn;
import com.jtskywalker.civolution.game.Game;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author rincewind
 */
public class Server {
    
    final Game game;
    
    //Integer denotes turns the actor has to pause
    final Map<Actor,Integer> actors = new HashMap<>();

    public Server(Game game) {
        this.game = game;
        for (Actor a : game.getActors()) {
            actors.put(a, 0);
        }
    }
    
    public void run() {
        while (true) {
            // the actors are chosen in a cyclic way
            for (Actor actor : actors.keySet()) {
                Pawn pawn = actor.getPawn();
                // the next actions of the actor are executed 
                // until its turn is over
                while (actors.get(actor) == 0) {
                    Action action 
                            = actor.nextAction(game.computeHorizon(pawn));
                    try {
                        // pause saves the turns the actor has to pause
                        int pause = action.execute(game, pawn);
                        actors.put(actor, pause);
                    } catch (ActionNotAllowedException ex) {
                        // ignore
                    }
                }
                // the actors pause is decremented
                actors.put(actor, actors.get(actor) - 1);
            }
        }
    }
    
}
