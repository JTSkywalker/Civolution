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
 *
 * @author rincewind
 */
public class Branch<T> implements Statement<T>{

    private final Expression condition;
    private Statement consequence;
    private final Statement alternative;

    public Branch(Expression condition, Statement consequence,
                    Statement alternative) {
        this.condition = condition;
        this.consequence = consequence;
        this.alternative = alternative;
    }
    
    @Override
    public ExternalStmt<T> nextExternal(Evaluator<T> externalEvaluator,
                                        Scope<String, Integer> scope) {
        if (condition.evaluate(externalEvaluator, scope) != 0) {
            return consequence.nextExternal(externalEvaluator, scope);
        } else {
            return alternative.nextExternal(externalEvaluator, scope);
        }
    }

    public void setSuccessor(Statement<T> s) {
        consequence = s;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 73 * hash + Objects.hashCode(this.condition);
        hash = 73 * hash + Objects.hashCode(this.consequence);
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
        final Branch<?> other = (Branch<?>) obj;
        if (!Objects.equals(this.condition, other.condition)) {
            return false;
        }
        if (!Objects.equals(this.consequence, other.consequence)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Branch{" + "condition=" + condition + ", consequence=" + consequence + '}';
    }
    
}