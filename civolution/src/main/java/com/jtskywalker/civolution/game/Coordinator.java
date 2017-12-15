/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.game;

/**
 * Interface to make computations on Coordinates.
 * @author jt
 * @param <C> Coordinate type
 * @param <D> Direction type
 */
public interface Coordinator<C extends Coordinates,D> {
    
    /**
     * Function to move one step in the given direction from the given position.
     * @param start - starting position
     * @param direction - direction to go
     * @return a new position object
     */
    public default C plusStep(C start, D direction) {
        return plusSteps(start, direction, 1);
    }
    
    /**
     * Function to move the given number of steps in the given direction from
     * the starting position.
     * @param start - starting position
     * @param direction - direction to go
     * @param steps - steps to go
     * @return a new position object
     */
    public C plusSteps(C start, D direction, int steps);

    public int getWidth();

    public int getHeight();
    
}
