/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.server;

import com.jtskywalker.civolution.game.Counter;
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
            for (Actor actor : actors.keySet()) {
                Counter counter = actor.getCounter();
                while (actors.get(actor) == 0) {
                    Action action 
                            = actor.nextAction(game.computeHorizon(counter));
                    try {
                        int pause = action.execute(game, counter);
                        actors.put(actor, pause);
                    } catch (ActionNotAllowedException ex) {
                        // ignore
                    }
                }
                actors.put(actor, actors.get(actor) - 1);
            }
        }
    }
    
}
