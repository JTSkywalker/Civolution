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
public class WhileIfStmt<T> implements Statement<T> {
    
    final Expression condition;
    final Statement body;
    final Statement next;

    public WhileIfStmt(Expression condition, Statement body, Statement next) {
        this.condition = condition;
        this.body = body;
        this.next = next;
    }
    
    
    @Override
    public ExternalStmt<T> nextExternal(Evaluator<T> externalEvaluator, 
            Scope<String, Integer> scope) {
        if (condition.evaluate(externalEvaluator, scope) != 0) {
            return body.nextExternal(externalEvaluator, scope);
        } else {
            return next.nextExternal(externalEvaluator, scope);
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.condition);
        hash = 53 * hash + Objects.hashCode(this.body);
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
        final WhileIfStmt<?> other = (WhileIfStmt<?>) obj;
        if (!Objects.equals(this.condition, other.condition)) {
            return false;
        }
        if (!Objects.equals(this.body, other.body)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "IfStmt{" + "condition=" + condition + ", body=" + body + '}';
    }    
    
}
