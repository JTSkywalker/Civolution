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
import java.util.List;
import java.util.Objects;

/**
 *
 * @author rincewind
 */
public class ListStmt<T> implements Statement<T> {
    
    final List<Statement> stmts;
    final int pp;

    public ListStmt(List<Statement> stmts, int programpoint) {
        this.stmts = stmts;
        this.pp = programpoint;                 
    }

    @Override
    public Pair<Integer, T> nextExternal(Evaluator<T> externalEvaluator,
            Scope<String, Integer> scope, int startPP) {
        Pair<Integer, T> result;
        for (Statement s : stmts) {
            result = s.nextExternal(externalEvaluator, scope, startPP);
            if (result.getSecond() != null) {
                return result;
            }
        }
        return new Pair(pp,null);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.stmts);
        hash = 67 * hash + this.pp;
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
        final ListStmt<?> other = (ListStmt<?>) obj;
        if (this.pp != other.pp) {
            return false;
        }
        if (!Objects.equals(this.stmts, other.stmts)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ListStmt{" + "stmts=" + stmts + ", pp=" + pp + '}';
    }
    

}
