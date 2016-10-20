/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.game;

import com.jtskywalker.civolution.game.Coordinates;
import com.jtskywalker.civolution.game.Counter;
import com.jtskywalker.civolution.server.Horizon;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;


public class HorizonImpl implements Horizon {
    
    final int width, height;
    final Map<Coordinates, Counter> visible = new HashMap<>();

    public HorizonImpl(int width, int height) {
        this.width = width;
        this.height = height;
    }
    
    

    @Override
    public void printOn(PrintStream printstream) {
        for (int y=height-1; y >= 0; y--) {
            for (int x=0; x < width; x++) {
                Coordinates coord = new Coordinates(x,y,width,height);
                if (visible.containsKey(coord)) {
                    Counter c = visible.get(coord);
                    if (c == null) {
                        printstream.print(' ');
                    } else {
                       printstream.print(c.getNation());
                    }
                } else {
                    printstream.print('#');
                }
            }
            printstream.print('\n');
        }
    }

    @Override
    public void putCounter(Counter counter, Coordinates coord) {
        visible.put(coord, counter);
    }
    
}
