/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.server;

import com.jtskywalker.civolution.game.Coordinates;
import com.jtskywalker.civolution.game.Counter;
import java.io.PrintStream;

/**
 *
 * @author rincewind
 */
public interface Horizon {
    
    public void printOn(PrintStream OUTPUT);
    
    public void putCounter(Counter counter, Coordinates coord);
    
}
