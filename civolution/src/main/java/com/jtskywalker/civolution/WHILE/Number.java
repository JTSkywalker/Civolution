/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.WHILE;

import com.jtskywalker.civolution.lang.Token;

/**
 *
 * @author rincewind
 */
public class Number implements Token {
    
    final int value;

    public Number(int value) {
        this.value = value;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.value;
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
        final Number other = (Number) obj;
        return this.value == other.value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Number{" + "value=" + value + '}';
    }
    
}
