/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.WHILE;

import com.jtskywalker.civolution.lang.Token;
import java.util.Objects;

/**
 * Represents an identifier in the language WHILE
 * @author jt
 */
public class Identifier implements Token {
    
    private final String name;

    /**
     * Constructor.
     * @param s string representation of the identifier
     */
    public Identifier(String s) {
        this.name = s;
    }
    
    /**
     * Getter for the string representation.
     * @return the stringn representation of this identifier
     */
    public String getName() {
        return name;
    }   
        
    /**
     * Standard hashCode function.
     * @return hashCode of this identifier
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.name);
        return hash;
    }

    /**
     * Standard equals function.
     * @param obj the object to be compared
     * @return whether the two objects are equal
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
        final Identifier other = (Identifier) obj;
        return Objects.equals(this.name, other.name);
    }

    /**
     * Return the string representation in a JSON-like string
     * @return string representation of this object
     */
    @Override
    public String toString() {
        return "Identifier{" + "name=" + name + '}';
    }
    
}
