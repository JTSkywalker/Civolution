/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.WHILE;

import com.jtskywalker.civolution.game.ActionParser;
import com.jtskywalker.civolution.game.action.Attack;
import com.jtskywalker.civolution.game.action.Command;
import com.jtskywalker.civolution.game.action.Direction;
import com.jtskywalker.civolution.lang.Statement;
import com.jtskywalker.civolution.lang.Token;
import com.jtskywalker.civolution.lang.expression.BinaryExp;
import com.jtskywalker.civolution.lang.expression.ConstantExp;
import com.jtskywalker.civolution.lang.expression.IdentifierExp;
import com.jtskywalker.civolution.lang.expression.Operator;
import com.jtskywalker.civolution.lang.statement.Binding;
import com.jtskywalker.civolution.lang.statement.ExternalStmt;
import com.jtskywalker.civolution.lang.statement.ListStmt;
import com.jtskywalker.civolution.lang.statement.WhileStmt;
import com.jtskywalker.civolution.server.Action;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author rincewind
 */
public class WHILEParserTest {
    
    public WHILEParserTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of parse method, of class WHILEParser.
     */
    @Test
    public void testParse() throws Exception {
        System.out.println("parse");
        WHILELexer lexer = new WHILELexer();
        String input = "while a == 49 { xxaa3 = 42 ; y = 33 ; }";
        List<Token> tokenlist = lexer.lex(input);
        int programpoint = 0;
        WHILEParser instance = new WHILEParser(new ActionParser());
        List<Statement> bodyList = new ArrayList<>();
        bodyList.add(new Binding("xxaa3", new ConstantExp(42), 3));
        bodyList.add(new Binding("y", new ConstantExp(33), 4));
        Statement whilestmt 
                = new WhileStmt(
                        new BinaryExp(Operator.EQ, 
                                      new IdentifierExp("a"),
                                      new ConstantExp(49)),
                        new ListStmt(bodyList,2), 1);
        List<Statement> erli = new ArrayList<>();
        erli.add(whilestmt);
        Statement expResult = new ListStmt(erli,0);
        Statement result = instance.parse(tokenlist, programpoint);
        assertEquals(expResult, result);
    }
    
    
    @Test
    public void testParseExt() throws Exception {
        System.out.println("parse");
        WHILELexer lexer = new WHILELexer();
        String input = "while a == 49 { ! attack NW ! ; y = 33 ; }";
        List<Token> tokenlist = lexer.lex(input);
        int programpoint = 0;
        WHILEParser instance = new WHILEParser(new ActionParser());
        List<Statement> bodyList = new ArrayList<>();
        bodyList.add(new ExternalStmt(new Attack(Direction.NW),3));
        bodyList.add(new Binding("y", new ConstantExp(33), 4));
        Statement whilestmt 
                = new WhileStmt(
                        new BinaryExp(Operator.EQ, 
                                      new IdentifierExp("a"),
                                      new ConstantExp(49)),
                        new ListStmt(bodyList,2), 1);
        List<Statement> erli = new ArrayList<>();
        erli.add(whilestmt);
        Statement expResult = new ListStmt(erli,0);
        Statement result = instance.parse(tokenlist, programpoint);
        assertEquals(expResult, result);
    }
    
    
    @Test
    public void testParseExtCommand() throws Exception {
        System.out.println("parse");
        WHILELexer lexer = new WHILELexer();
        String input = "while a == 49 { ! command x = 2 ! ; y = 33 ; }";
        List<Token> tokenlist = lexer.lex(input);
        int programpoint = 0;
        WHILEParser instance = new WHILEParser(new ActionParser());
        List<Statement> bodyList = new ArrayList<>();
        List<Statement> orderList = new ArrayList<>();
        orderList.add(new Binding("x", new ConstantExp(2),1));
        Statement<Action> order = new ListStmt(orderList,0);
        bodyList.add(new ExternalStmt(new Command(order),3));
        bodyList.add(new Binding("y", new ConstantExp(33), 4));
        Statement whilestmt 
                = new WhileStmt(
                        new BinaryExp(Operator.EQ, 
                                      new IdentifierExp("a"),
                                      new ConstantExp(49)),
                        new ListStmt(bodyList,2), 1);
        List<Statement> erli = new ArrayList<>();
        erli.add(whilestmt);
        Statement expResult = new ListStmt(erli,0);
        Statement result = instance.parse(tokenlist, programpoint);
        assertEquals(expResult, result);
    }

    
    
}
