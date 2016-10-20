/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution;

import com.jtskywalker.civolution.game.Coordinates;
import com.jtskywalker.civolution.game.Counter;
import com.jtskywalker.civolution.game.CounterFactory;
import com.jtskywalker.civolution.game.Game;
import com.jtskywalker.civolution.server.Actor;
import com.jtskywalker.civolution.server.HumanActor;
import com.jtskywalker.civolution.server.Server;
import com.jtskywalker.civolution.server.Subordinate;

/**
 *
 * @author rincewind
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Game game = new Game(80,40);
        
        Actor human = new HumanActor();
        Counter queen = CounterFactory.createQueen(0, human);
        game.putCounter(queen, new Coordinates(10,10,80,40));
        
        Actor sub1 = new Subordinate(0);
        Actor sub2 = new Subordinate(0);
        game.putCounter(CounterFactory.createWarrior(0,sub1),
                new Coordinates(10, 12, 80, 40));
        game.putCounter(CounterFactory.createScout(0, sub2),
                new Coordinates(12, 10, 80, 40));
        
        Server server = new Server(game);
        
        server.run();
                
    }
    
}
