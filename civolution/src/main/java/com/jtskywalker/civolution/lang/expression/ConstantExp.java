/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.lang.expression;

import com.jtskywalker.civolution.lang.Evaluator;
import com.jtskywalker.civolution.lang.Expression;
import com.jtskywalker.civolution.lang.Scope;

/**
 *
 * @author rincewind
 */
public class ConstantExp implements Expression {
    
    final int value;

    public ConstantExp(int value) {
        this.value = value;
    }

    @Override
    public int evaluate(Evaluator externalEvaluator, Scope scope) {
        return value;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.value;
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
        final ConstantExp other = (ConstantExp) obj;
        return this.value == other.value;
    }

    @Override
    public String toString() {
        return "ConstantExp{" + "value=" + value + '}';
    }    
    
}
