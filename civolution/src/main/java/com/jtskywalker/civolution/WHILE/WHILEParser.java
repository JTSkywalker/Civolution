/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.WHILE;

import com.jtskywalker.civolution.lang.*;
import com.jtskywalker.civolution.lang.expression.*;
import com.jtskywalker.civolution.lang.statement.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rincewind
 */
public class WHILEParser<T> implements Parser<T> {
    
    final ExtParser<T> externalParser;

    public WHILEParser(ExtParser<T> externalParser) {
        this.externalParser = externalParser;
        externalParser.setIntParser(this);
    }
    
    @Override
    public Statement<T> parse(List<Token> tokenlist, int programpoint) 
        throws ParserErrorException {
        int pp = programpoint;
        List<Statement> stmts = new ArrayList<>();
        List<Token> block = new ArrayList<>();
        boolean inExt = false;
        boolean inBlock = false;
        for (Token t : tokenlist) {
            if (t == Keyword.EM) {
                inExt = !inExt;
            }
            if (t == Keyword.LBRACE) {
                inBlock = true;
            }
            if (t == Keyword.RBRACE) {
                inBlock = false;
            }
            if (!inExt && !inBlock && t == Keyword.SEMICOLON) {
                pp++;
                if (!block.isEmpty()) {
                    stmts.add(parseBlock(block, pp));
                }
                block.clear();
            } else {
                block.add(t);
            }
        }
        pp++;
        if (!block.isEmpty()) {
            stmts.add(parseBlock(block, pp));
        }
        return new ListStmt(stmts,programpoint);
    }

    private Statement parseBlock(List<Token> block, int pp) 
            throws ParserErrorException {
        Token first = block.get(0);
        if (first == Keyword.WHILE || first == Keyword.IF) {
            return parseWhileIf(block,pp);
        } else if (first == Keyword.EM) {
            return new ExternalStmt(
                    externalParser.parse(block.subList(1, block.size())),pp);
        } else {
            return parseBinding(block,pp);
        }
    }

    private Statement<T> parseWhileIf(List<Token> tokenlist, int pp)
            throws ParserErrorException {
        int i = 1;
        
        List<Token> exps = new ArrayList<>();
        while (tokenlist.get(i) != Keyword.LBRACE) {
            exps.add(tokenlist.get(i));
            i++;
        }
        Expression condition = parseExp(exps);
        
        i++;
        List<Token> stmts = new ArrayList<>();
        while (tokenlist.get(i) != Keyword.RBRACE) {
            stmts.add(tokenlist.get(i));
            i++;
        }
        Statement body = parse(stmts, pp + 1);
        
        if (tokenlist.get(0) == Keyword.IF) {
            return new IfStmt(condition, body, pp);
        } else {
            return new WhileStmt(condition, body, pp);
        }
    }

    private Statement parseBinding(List<Token> block, int pp)
            throws ParserErrorException {
        if (!(block.get(0) instanceof Identifier)
                || block.get(1) != Keyword.DEF) {
            throw new ParserErrorException();
        }
        Expression exp = parseExp(block.subList(2, block.size()));
        return new Binding(((Identifier) block.get(0)).getName(), exp, pp);
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
        if (t instanceof Number) {
            Number num = (Number) t;
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
