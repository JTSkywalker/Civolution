/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.lang.statement;

import com.jtskywalker.civolution.lang.Evaluator;
import com.jtskywalker.civolution.lang.Expression;
import com.jtskywalker.civolution.lang.Scope;
import com.jtskywalker.civolution.lang.Statement;
import com.jtskywalker.civolution.server.util.Pair;
import java.util.Objects;

/**
 *
 * @author rincewind
 */
public class Binding<T> implements Statement<T> {
    
    final String name;
    final Expression exp;
    final int pp;

    public Binding(String name, Expression exp, int programpoint) {
        this.name = name;
        this.exp = exp;
        this.pp = programpoint;
    }

    @Override
    public Pair<Integer, T> nextExternal(Evaluator<T> externalEvaluator,
            Scope<String, Integer> scope, int startPP) {
        if (startPP > pp) {
            return new Pair(pp,null);
        }
        scope.put(name, exp.evaluate(externalEvaluator, scope));
        return new Pair(pp,null);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.name);
        hash = 29 * hash + Objects.hashCode(this.exp);
        hash = 29 * hash + this.pp;
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
        final Binding<?> other = (Binding<?>) obj;
        if (this.pp != other.pp) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.exp, other.exp)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Binding{" + "name=" + name + ", exp=" + exp + ", pp=" + pp + '}';
    }
    
    
}
