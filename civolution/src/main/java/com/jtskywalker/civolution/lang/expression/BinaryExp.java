/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.lang.expression;

import com.jtskywalker.civolution.lang.*;
import java.util.Objects;

/**
 *
 * @author rincewind
 * @param <T>
 */
public class BinaryExp<T> implements Expression<T> {
    
    final Operator operator;
    final Expression left, right;

    public BinaryExp(Operator operator, Expression left, Expression right) {
        this.operator = operator;
        this.left = left;
        this.right = right;
    }
    
    @Override
    public int evaluate(Evaluator eval, Scope scope) {
        int vleft = left.evaluate(eval, scope);
        int vright = right.evaluate(eval, scope);
        return operator.evaluate(vleft,vright);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.operator);
        hash = 23 * hash + Objects.hashCode(this.left);
        hash = 23 * hash + Objects.hashCode(this.right);
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
        final BinaryExp other = (BinaryExp) obj;
        if (this.operator != other.operator) {
            return false;
        }
        if (!Objects.equals(this.left, other.left)) {
            return false;
        }
        return Objects.equals(this.right, other.right);
    }

    @Override
    public String toString() {
        return "BinaryExp{" + "operator=" + operator + 
                ", left=" + left + ", right=" + right + '}';
    }
    
}
