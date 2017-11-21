/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.lang.statement;

import com.jtskywalker.civolution.lang.Evaluator;
import com.jtskywalker.civolution.lang.Scope;
import com.jtskywalker.civolution.lang.Statement;
import java.util.Objects;

/**
 *
 * @author rincewind
 */
public class ExternalStmt<T> implements Statement<T> {
    
    final T external;
    final Statement<T> next;

    public ExternalStmt(T external, Statement<T> next) {
        this.external = external;
        this.next = next;
    }

    @Override
    public ExternalStmt<T> nextExternal(Evaluator<T> externalEvaluator,
            Scope<String, Integer> scope) {
        return this;
    }

    public T getExternal() {
        return external;
    }

    public Statement<T> getNext() {
        return next;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.external);
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
        if (!Objects.equals(this.external, other.external)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ExternalStmt{" + "external=" + external + '}';
    }
        
}
