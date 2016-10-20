/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.lang.expression;

/**
 *
 * @author rincewind
 */
public enum Operator {
    
    PLUS, MINUS,
    EQ, NEQ,
    GT, LEQ,
    LT, GEQ;

    int evaluate(int vleft, int vright) {
        switch (this) {
            case PLUS:
                return vleft + vright;
            case MINUS:
                return vleft - vright;
            case EQ:
                return vleft == vright ? 1 : 0;
            case NEQ:
                return vleft != vright ? 1 : 0;
            case GT:
                return vleft > vright ? 1 : 0;
            case LEQ:
                return vleft <= vright ? 1 : 0;
            case LT:
                return vleft < vright ? 1 : 0;
            case GEQ:
            default:
                throw new AssertionError(this.name());
            
        }
    }
    
}
