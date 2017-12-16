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
import java.util.Objects;

/**
 * This class describes the binding of a variable to an integer.
 * @author jt
 * @param <T> type of the game-specific language
 */
public class Binding<T> implements Statement<T> {
    
    private final String name;
    private final Expression exp;
    private final Statement next;

    public Binding(String name, Expression exp, Statement next) {
        this.name = name;
        this.exp = exp;
        this.next = next;
    }

    @Override
    public ExternalStmt<T> nextExternal(Evaluator<T> externalEvaluator,
            Scope<String, Integer> scope) {
        scope.put(name, exp.evaluate(externalEvaluator, scope));
        if (next == null) {
            return null;
        }
        return next.nextExternal(externalEvaluator, scope);
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.name);
        hash = 59 * hash + Objects.hashCode(this.exp);
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
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return Objects.equals(this.exp, other.exp);
    }

    @Override
    public String toString() {
        return "Binding{" + "name=" + name + ", exp=" + exp + '}';
    }

    
}
