/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.demogame;

import com.jtskywalker.civolution.controller.Actor;
import com.jtskywalker.civolution.controller.Coordinates;
import com.jtskywalker.civolution.game.Horizon;
import com.jtskywalker.civolution.game.Tile;
import com.jtskywalker.civolution.util.HashMapSet;
import java.util.Set;
import javafx.scene.image.Image;


public class HorizonImpl implements Horizon {
    
    final int width, height;
    final HashMapSet<Coordinates, Actor> visible = new HashMapSet<>();

    public HorizonImpl(int width, int height) {
        this.width = width;
        this.height = height;
    }
    
    /*@Override
    public void printOn(PrintStream printstream) {
        printstream.print("\n\n");
        for (int y=height-1; y >= 0; y--) {
            
            for (int x=0; x < width; x++) {
                printstream.print("+-----");
            }
            printstream.print("+\n");
            
            for (int x=0; x < width; x++) {
                Coordinates coord = new Coordinates(x,y,width,height);
                printstream.print('|');
                printFigures(printstream, coord);
            }
            printstream.print("|\n");
            
            for (int x=0; x < width; x++) {
                printstream.print("|     ");
            }
            printstream.print("|\n");
        }
        
        for (int x=0; x < width; x++) {
            printstream.print("+-----");
        }
        printstream.print("+\n\n");
    }*/

    public void putActor(Actor actor, Coordinates coord) {
        visible.put(coord, actor);
    }

    /*
    private void printFigures(PrintStream printstream, Coordinates coord) {
        if (visible.containsKey(coord)) {
            Set<Body> counters = visible.get(coord);
            if (counters.isEmpty()) {
                printstream.print("     ");
                return;
            }
            int queens , warriors, scouts, settlers;
            queens = counters
                    .stream()
                    .filter((c) -> isQueen(c))
                    .map((c) -> 1)
                    .reduce(0, Integer::sum);
            warriors = counters
                    .stream()
                    .filter((c) -> isWarrior(c))
                    .map((c) -> 1)
                    .reduce(0, Integer::sum);
            scouts = counters
                    .stream()
                    .filter((c) -> isScout(c))
                    .map((c) -> 1)
                    .reduce(0, Integer::sum);
            settlers = counters
                    .stream()
                    .filter((c) -> isSettler(c))
                    .map((c) -> 1)
                    .reduce(0, Integer::sum);
            
            int nation = counters
                    .stream()
                    .findAny()
                    .orElseThrow(() -> new IllegalArgumentException())
                    .getEmblem();
            
            printstream.print(nation);
            printstream.print(queens == 0 ? " " : 
                    queens == 1 ? "Q" : queens);
            printstream.print(warriors==0 ? " " :
                    warriors==1 ? "W" : warriors);
            printstream.print(scouts == 0 ? " " : 
                    scouts == 1 ? "S" : scouts);
            printstream.print(settlers==0 ? " " :
                    settlers==1 ? "C" : settlers);            
        } else {
            printstream.print("     ");
        }
    }
    */

    private boolean isQueen(Body c) {
        return c.getBaseStrength() == 20
                && c.getBaseMobility() == 20;
    }
    
    private boolean isWarrior(Body c) {
        return c.getBaseStrength() == 20
                && c.getBaseMobility() == 10;
    }
    
    private boolean isScout(Body c) {
        return c.getBaseStrength() == 10
                && c.getBaseMobility() == 20;
    }
    
    private boolean isSettler(Body c) {
        return c.getBaseStrength() == 5
                && c.getBaseMobility() == 5;
    }

    public int getSettlersAt(int i, int j) {
        Coordinates coord = new Coordinates(i,j,width,height);
        Set<Actor> counters = visible.get(coord);
        return counters.stream().filter((c) -> isSettler(c.getBody()))
                .map((c) -> 1).reduce(0, Integer::sum);
    }

    public int getWarriorsAt(int i, int j) {
        Coordinates coord = new Coordinates(i,j,width,height);
        Set<Actor> counters = visible.get(coord);
        return counters.stream().filter((c) -> isWarrior(c.getBody()))
                .map((c) -> 1).reduce(0, Integer::sum);
    }

    public int getScoutsAt(int i, int j) {
        Coordinates coord = new Coordinates(i,j,width,height);
        Set<Actor> counters = visible.get(coord);
        return counters.stream().filter((c) -> isScout(c.getBody()))
                .map((c) -> 1).reduce(0, Integer::sum);
    }

    public int getQueensAt(int i, int j) {
        Coordinates coord = new Coordinates(i,j,width,height);
        Set<Actor> counters = visible.get(coord);
        return counters.stream().filter((c) -> isQueen(c.getBody()))
                .map((c) -> 1).reduce(0, Integer::sum);
    }

    @Override
    public Tile getTile(Coordinates coord) {
        Set<Actor> localActors = visible.get(coord);
        int queens = (int) localActors.stream().filter((a) -> isQueen(a.getBody())).count();
        int warriors = (int) localActors.stream().filter((a) -> isWarrior(a.getBody())).count();
        int scouts = (int) localActors.stream().filter((a) -> isScout(a.getBody())).count();
        int settlers = (int) localActors.stream().filter((a) -> isSettler(a.getBody())).count();
        String base = "file:" + DemoGame.BASEPATH;
        if (queens == 1 && warriors == 0 && scouts == 0 && settlers == 0) 
            return new TileImpl(new Image(base + "queen.png"));
        if (queens == 0 && warriors == 1 && scouts == 0 && settlers == 0) 
            return new TileImpl(new Image(base + "warrior.png"));
        if (queens == 0 && warriors == 0 && scouts == 1 && settlers == 0) 
            return new TileImpl(new Image(base + "scout.png"));
        if (queens == 1 && warriors == 0 && scouts == 0 && settlers == 1) 
            return new TileImpl(new Image(base + "settler.png"));
        
        if (queens == 0 && warriors == 0 && scouts == 0 && settlers == 0) 
            return new TileImpl(new Image(base + "empty.png"));
        
        return new TileImpl(new Image(base + "more.png"));
    }
}
