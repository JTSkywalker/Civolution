/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.game;

import com.jtskywalker.civolution.server.Coordinates;
import com.jtskywalker.civolution.server.Horizon;
import com.jtskywalker.civolution.server.util.HashMapSet;
import java.io.PrintStream;
import java.util.Set;


public class HorizonImpl implements Horizon {
    
    final int width, height;
    final HashMapSet<Coordinates, Counter> visible = new HashMapSet<>();

    public HorizonImpl(int width, int height) {
        this.width = width;
        this.height = height;
    }
    
    

    @Override
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
    }

    @Override
    public void putCounter(Counter counter, Coordinates coord) {
        visible.put(coord, counter);
    }

    private void printFigures(PrintStream printstream, Coordinates coord) {
        if (visible.containsKey(coord)) {
            Set<Counter> counters = visible.get(coord);
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
                    .getNation();
            
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

    private boolean isQueen(Counter c) {
        return c.getBaseStrength() == 20
                && c.getBaseMobility() == 20;
    }
    
    private boolean isWarrior(Counter c) {
        return c.getBaseStrength() == 20
                && c.getBaseMobility() == 10;
    }
    
    private boolean isScout(Counter c) {
        return c.getBaseStrength() == 10
                && c.getBaseMobility() == 20;
    }
    
    private boolean isSettler(Counter c) {
        return c.getBaseStrength() == 5
                && c.getBaseMobility() == 5;
    }
}
