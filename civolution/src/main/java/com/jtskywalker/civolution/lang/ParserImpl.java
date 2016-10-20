/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.lang;

import com.jtskywalker.civolution.WHILE.Identifier;
import com.jtskywalker.civolution.WHILE.Keyword;
import com.jtskywalker.civolution.lang.expression.BinaryExp;
import com.jtskywalker.civolution.lang.expression.ConstantExp;
import com.jtskywalker.civolution.lang.expression.ExternalExp;
import com.jtskywalker.civolution.lang.expression.IdentifierExp;
import com.jtskywalker.civolution.lang.expression.Operator;
import com.jtskywalker.civolution.lang.statement.Binding;
import com.jtskywalker.civolution.lang.statement.Branch;
import com.jtskywalker.civolution.lang.statement.ExternalStmt;
import java.util.List;

/**
 *
 * @author rincewind
 */
public class ParserImpl<T> implements Parser<T> {
    
    final ExtParser<T> externalParser;

    public ParserImpl(ExtParser<T> externalParser) {
        this.externalParser = externalParser;
    }    
    
    @Override
    public Statement<T> parse(List<Token> tokens) throws ParserErrorException {
        return parse(tokens, null);
    }
    
    public Statement<T> parse(List<Token> tokens, Statement<T> successor) 
                throws ParserErrorException {
        if (tokens.isEmpty()) {
            return successor;
        }
        
        Token first = tokens.get(0);
        if (first == Keyword.EM) {
            return parseExternal(tokens, successor);
        } else if (first == Keyword.WHILE) {
            return parseWhile(tokens, successor);
        } else if (first == Keyword.IF) {
            return parseIf(tokens, successor);
        } else if (first instanceof Identifier) {
            return parseBinding(tokens, successor);
        } else {
            throw new ParserErrorException();
        }
    }
    

    private Statement<T> parseExternal(List<Token> tokens, Statement<T> successor)
            throws ParserErrorException {
        int split = findSemicolon(tokens);
        if (split == -1) {
            throw new ParserErrorException();
        }
        T t = externalParser.parse(tokens.subList(1, split));
        Statement succ = parse(tokens.subList(split + 1, tokens.size()),successor);
        return new ExternalStmt(t,succ);
    }

    private Statement<T> parseWhile(List<Token> tokens, Statement<T> successor)
            throws ParserErrorException {
        if (tokens.size() <= 4) {
            throw new ParserErrorException();
        }
        int i_lbr = tokens.indexOf(Keyword.LBRACE);
        if (i_lbr < 2) {
            throw new ParserErrorException();
        }
        Expression cond = parseExp(tokens.subList(1, i_lbr));
        int i_rbr = findMatchingRBR(i_lbr,tokens);
        Statement succ = parse(tokens.subList(i_rbr + 1, tokens.size()),successor);
        Branch<T> branch = new Branch<>(cond, null, succ);
        Statement body = parse(tokens.subList(i_lbr + 1, i_rbr),branch);
        branch.setSuccessor(body);
        return branch;
    }

    private Statement<T> parseIf(List<Token> tokens, Statement<T> successor) throws ParserErrorException {
        if (tokens.size() <= 4) {
            throw new ParserErrorException();
        }
        int i_lbr = tokens.indexOf(Keyword.LBRACE);
        if (i_lbr < 2) {
            throw new ParserErrorException();
        }
        Expression cond = parseExp(tokens.subList(1, i_lbr));
        int i_rbr = findMatchingRBR(i_lbr,tokens);
        Statement succ = parse(tokens.subList(i_rbr + 1, tokens.size()),successor);
        Statement body = parse(tokens.subList(i_lbr + 1, i_rbr), succ);
        return new Branch<>(cond, body, succ);
    }

    private Statement<T> parseBinding(List<Token> tokens, Statement<T> successor)
                throws ParserErrorException {
        if (tokens.size() < 3 || tokens.get(1) != Keyword.DEF) {
            throw new ParserErrorException();
        }
        String name = ((Identifier) tokens.get(0)).getName();
        int split = tokens.indexOf(Keyword.SEMICOLON);
        if (split == -1) {
            throw new ParserErrorException();
        }
        Expression exp = parseExp(tokens.subList(2, split));
        Statement succ = parse(tokens.subList(split +1, tokens.size()),successor);
        return new Binding(name,exp,succ);
    }

    private int findMatchingRBR(int i_lbr, List<Token> tokens) {
        int k = 0; //denotes how many lbraces have been passed
        for (int i=i_lbr; i < tokens.size(); i++) {
            if (tokens.get(i) == Keyword.LBRACE) {
                k++;
            }
            if (tokens.get(i) == Keyword.RBRACE) {
                k--;
            }
            if (k == 0) {
                return i;
            }
        }
        return -1;
    }

    private int findSemicolon(List<Token> tokens) {
        int k = 0;
        for (int i=0; i< tokens.size(); i++) {
            Token t = tokens.get(i);
            if (t == Keyword.LBRACE) {
                k++;
            }
            if (t == Keyword.RBRACE) {
                k--;
            }
            if (t == Keyword.SEMICOLON && k == 0) {
                return i;
            }
        }
        return -1;
    }
   
    @Override
    public Expression parseExp(List<Token> exps)
            throws ParserErrorException {
        if (exps.get(0) == Keyword.QM) {
            return new ExternalExp(externalParser
                    .parse(exps.subList(1, exps.size())));
        }
        if (exps.size() == 1) {
            return parseIdNum(exps.get(0));
        }
        if (exps.size() == 3) {
            Expression first = parseIdNum(exps.get(0));
            Expression second = parseIdNum(exps.get(2));
            Operator op = tokenToOperator(exps.get(1));
            
            return new BinaryExp(op, first, second);
        }        
        throw new ParserErrorException();
    }

    private Expression parseIdNum(Token t) throws ParserErrorException {
        if (t instanceof Identifier) {
            Identifier id = (Identifier) t;
            return new IdentifierExp(id.getName());
        } 
        if (t instanceof com.jtskywalker.civolution.WHILE.Number) {
            com.jtskywalker.civolution.WHILE.Number num = (com.jtskywalker.civolution.WHILE.Number) t;
            return new ConstantExp(num.getValue());
        }
        throw new ParserErrorException();
    }
    
    private Operator tokenToOperator(Token t) throws ParserErrorException {
        if (t == Keyword.PLUS)
            return Operator.PLUS;
        if (t == Keyword.MINUS)
            return Operator.MINUS;
        if (t == Keyword.LT)
            return Operator.LT;
        if (t == Keyword.GEQ)
            return Operator.GEQ;
        if (t == Keyword.GT)
            return Operator.GT;
        if (t == Keyword.LEQ)
            return Operator.LEQ;
        if (t == Keyword.NEQ)
            return Operator.NEQ;
        if (t == Keyword.EQ)
            return Operator.EQ;
        throw new ParserErrorException();
    }
}