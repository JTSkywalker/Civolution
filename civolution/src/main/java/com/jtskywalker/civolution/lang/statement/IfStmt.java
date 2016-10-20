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
public class IfStmt<T> implements Statement<T> {
    
    final Expression condition;
    final Statement body;
    final int pp;

    public IfStmt(Expression condition, Statement body, int programpoint) {
        this.condition = condition;
        this.body = body;
        this.pp = programpoint;
    }

    @Override
    public Pair<Integer, T> nextExternal(Evaluator<T> externalEvaluator, 
            Scope<String, Integer> scope, int startPP) {
        if (startPP > pp) {
            return body.nextExternal(externalEvaluator, scope, startPP);                    
        } else if (condition.evaluate(externalEvaluator, scope) != 0) {
            return body.nextExternal(externalEvaluator, scope, startPP);
        } else {
            return new Pair(pp, null);
        }
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.condition);
        hash = 97 * hash + Objects.hashCode(this.body);
        hash = 97 * hash + this.pp;
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
        final IfStmt<?> other = (IfStmt<?>) obj;
        if (this.pp != other.pp) {
            return false;
        }
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
        return "IfStmt{" + "condition=" + condition + 
                ", body=" + body + ", pp=" + pp + '}';
    }    
}
