/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.lang.statement;

import com.jtskywalker.civolution.lang.Evaluator;
import com.jtskywalker.civolution.lang.Scope;
import com.jtskywalker.civolution.lang.Statement;
import com.jtskywalker.civolution.server.util.Pair;
import java.util.Objects;

/**
 *
 * @author rincewind
 */
public class ExternalStmt<T> implements Statement<T> {
    
    final T external;
    final int pp;

    public ExternalStmt(T external, int pp) {
        this.external = external;
        this.pp = pp;
    }

    @Override
    public Pair<Integer, T> nextExternal(Evaluator<T> externalEvaluator, 
            Scope<String, Integer> scope, int startPP) {
        if (startPP > pp) {
            return new Pair(pp, null);
        } else {
            return new Pair(pp, external);
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.external);
        hash = 59 * hash + this.pp;
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
        final ExternalStmt<?> other = (ExternalStmt<?>) obj;
        if (this.pp != other.pp) {
            return false;
        }
        if (!Objects.equals(this.external, other.external)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ExternalStmt{" + "external=" + external + ", pp=" + pp + '}';
    }
        
}
