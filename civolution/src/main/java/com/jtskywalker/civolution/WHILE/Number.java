/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.WHILE;

import com.jtskywalker.civolution.lang.Token;

/**
 * Represents integers in WHILE, which are the only supported numbers.
 * @author jt
 */
public class Number implements Token {
    
    private final int value;

    /**
     * Constructor
     * @param value - the value of the integer
     */
    public Number(int value) {
        this.value = value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.value;
        return hash;
    }

    /**
     * {@inheritDoc}
     */
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
        final Number other = (Number) obj;
        return this.value == other.value;
    }

    /**
     * Getter for the value.
     * @return - value of this integer
     */
    public int getValue() {
        return value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Number{" + "value=" + value + '}';
    }
    
}
