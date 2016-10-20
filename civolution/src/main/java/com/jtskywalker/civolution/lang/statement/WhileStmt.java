/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.lang.statement;

import com.jtskywalker.civolution.lang.*;
import com.jtskywalker.civolution.server.util.Pair;
import java.util.Objects;


/**
 *
 * @author rincewind
 * @param <T>
 */
public class WhileStmt<T> implements Statement<T> {
    
    final Expression condition;
    final Statement body;
    final int pp;

    public WhileStmt(Expression condition, Statement body, int programpoint) {
        this.condition = condition;
        this.body = body;
        this.pp = programpoint;
    }


    @Override
    public Pair<Integer,T> nextExternal(Evaluator<T> externalEvaluator, 
            Scope<String,Integer> scope, int startPP) {
        Pair<Integer,T> result = new Pair(pp,null);
        if (startPP > pp) {
            result = body.nextExternal(externalEvaluator, scope, startPP);
        }
        while(condition.evaluate(externalEvaluator, scope) != 0) {
            result = body.nextExternal(externalEvaluator, scope, startPP);
        }
        return result;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.condition);
        hash = 37 * hash + Objects.hashCode(this.body);
        hash = 37 * hash + this.pp;
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
        final WhileStmt<?> other = (WhileStmt<?>) obj;
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
        return "WhileStmt{" + "condition=" + condition + 
                ", body=" + body + ", pp=" + pp + '}';
    }
    
}
