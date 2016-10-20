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
 * @author rincewind
 * @param <T>
 */
public class ExternalExp<T> implements Expression<T> {
    
    final T external;

    public ExternalExp(T external) {
        this.external = external;
    }

    @Override
    public int evaluate(Evaluator<T> externalEvaluator,
            Scope<String, Integer> scope) {
        return externalEvaluator.evaluate(scope, external);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.external);
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
        final ExternalExp<?> other = (ExternalExp<?>) obj;
        if (!Objects.equals(this.external, other.external)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ExternalExp{" + "external=" + external + '}';
    }
    
    
}
