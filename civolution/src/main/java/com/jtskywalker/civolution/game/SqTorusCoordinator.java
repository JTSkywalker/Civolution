/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.game;

/**
 * This is a class that supports operations on square tile coordinates.
 * @author jt
 */
public class SqTorusCoordinator 
        implements Coordinator<SqCoordinates,SqDirection> {
    
    private final int width, height;
    
    /**
     * Constructor.
     * @param width width of the torus
     * @param height height of the torus
     */
    public SqTorusCoordinator(int width, int height) {
        this.width = width;
        this.height= height;
    }
        
    /**
     * Function to move the given number of steps in the given direction from
     * the starting position.
     * @param start starting position
     * @param direction direction to go
     * @param steps steps to go
     * @return the new position
     */
    @Override
    public SqCoordinates plusSteps(SqCoordinates start, 
            SqDirection direction, int steps) {
        int x = start.getX();
        int y = start.getY();
        switch (direction) {
            case N:
                return new SqCoordinates( 
                        x, mod(y + steps, height));
            case NE:
                return new SqCoordinates(
                        mod(x + steps, width), mod(y + steps, height));
            case E:
                return new SqCoordinates(
                        mod(x + steps, width), y);
            case SE:
                return new SqCoordinates(
                        mod(x + steps, width), mod(y - steps, height));
            case S:
                return new SqCoordinates(
                        x, mod(y - steps, height));
            case SW:
                return new SqCoordinates(
                        mod(x - steps, width), mod(y - steps, height));
            case W:
                return new SqCoordinates(
                        mod(x - steps, width), y);
            case NW:
                return new SqCoordinates(
                        mod(x - steps, width), mod(y + steps, height));
            default:
                throw new AssertionError(direction.name());   
        }
    }
    
    private int mod(int a, int b) {
        return (a % b + b) % b;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }
    
}
