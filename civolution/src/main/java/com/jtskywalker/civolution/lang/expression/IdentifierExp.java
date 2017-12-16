/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.lang.expression;

import com.jtskywalker.civolution.lang.Evaluator;
import com.jtskywalker.civolution.lang.Expression;
import com.jtskywalker.civolution.lang.Scope;
import java.util.Objects;

/**
 *
 * @author jt
 * @param <T> type of the game-specific language
 */
public class IdentifierExp<T> implements Expression<T> {
    
    final String name;

    public IdentifierExp(String name) {
        this.name = name;
    }

    @Override
    public int evaluate(Evaluator<T> externalEvaluator, 
            Scope<String,Integer> scope) {
        try {
            return scope.lookup(name);
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
            return 666;
        }
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.name);
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
        final IdentifierExp<?> other = (IdentifierExp<?>) obj;
        return Objects.equals(this.name, other.name);
    }

    @Override
    public String toString() {
        return "IdentifierExp{" + "name=" + name + '}';
    }
    
    
}
