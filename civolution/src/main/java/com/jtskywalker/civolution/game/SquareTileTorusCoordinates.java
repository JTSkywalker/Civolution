/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.game;

/**
 *
 * @author jt
 */
public class SquareTileTorusCoordinates implements Coordinates {
    
    final int x, y;
    final int width, height;

    public SquareTileTorusCoordinates(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    
    public SquareTileTorusCoordinates add(SquareTileDirection direction) {
        switch (direction) {
            case N:
                return new SquareTileTorusCoordinates( x, mod(y + 1, height),
                                        width, height);
            case NE:
                return new SquareTileTorusCoordinates(mod(x + 1, width), mod(y + 1, height),
                                        width, height);
            case E:
                return new SquareTileTorusCoordinates(mod(x + 1, width), y,
                                        width, height);
            case SE:
                return new SquareTileTorusCoordinates(mod(x + 1, width), mod(y - 1, height),
                                        width, height);
            case S:
                return new SquareTileTorusCoordinates( x, mod(y - 1, height),
                                        width, height);
            case SW:
                return new SquareTileTorusCoordinates(mod(x - 1, width), mod(y - 1, height),
                                        width, height);
            case W:
                return new SquareTileTorusCoordinates(mod(x - 1, width), y,
                                        width, height);
            case NW:
                return new SquareTileTorusCoordinates(mod(x - 1, width), mod(y + 1, height),
                                        width, height);
            default:
                throw new AssertionError(direction.name());
            
        }
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 43 * hash + this.x;
        hash = 43 * hash + this.y;
        hash = 43 * hash + this.width;
        hash = 43 * hash + this.height;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SquareTileTorusCoordinates other = (SquareTileTorusCoordinates) obj;
        if (this.x != other.x) {
            return false;
        }
        if (this.y != other.y) {
            return false;
        }
        if (this.width != other.width) {
            return false;
        }
        return this.height == other.height;
    }

    @Override
    public String toString() {
        return "Coordinates{" + "x=" + x + ", y=" + y +
                ", width=" + width + ", height=" + height + '}';
    }
    
    private int mod(int a, int b) {
        return (a % b + b) % b;
    }
    
}
