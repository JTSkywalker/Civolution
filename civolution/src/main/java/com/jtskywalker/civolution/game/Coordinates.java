/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.game;

/**
 *
 * @author rincewind
 */
public class Coordinates {
    
    final int x, y;
    final int width, height;

    public Coordinates(int x, int y, int width, int height) {
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
    
    public Coordinates add(Direction direction) {
        switch (direction) {
            case N:
                return new Coordinates( x, mod(y + 1, height),
                                        width, height);
            case NE:
                return new Coordinates(mod(x + 1, width), mod(y + 1, height),
                                        width, height);
            case E:
                return new Coordinates(mod(x + 1, width), y,
                                        width, height);
            case SE:
                return new Coordinates(mod(x + 1, width), mod(y - 1, height),
                                        width, height);
            case S:
                return new Coordinates( x, mod(y - 1, height),
                                        width, height);
            case SW:
                return new Coordinates(mod(x - 1, width), mod(y - 1, height),
                                        width, height);
            case W:
                return new Coordinates(mod(x - 1, width), y,
                                        width, height);
            case NW:
                return new Coordinates(mod(x - 1, width), mod(y + 1, height),
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
        final Coordinates other = (Coordinates) obj;
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
