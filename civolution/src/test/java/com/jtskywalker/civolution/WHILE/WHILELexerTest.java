/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.WHILE;

import com.jtskywalker.civolution.lang.Token;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import static org.junit.Assert.*;

/**
 *
 * @author rincewind
 */
public class WHILELexerTest {
    
    public WHILELexerTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of lex method, of class WHILELexer.
     */
    @org.junit.Test
    public void testLex() {
        System.out.println("lex");
        String input = "while a == 49 { xxaa3 = 42\n; y = 33 ; }";
        WHILELexer instance = new WHILELexer();
        List<Token> expResult = new ArrayList<>();
        expResult.add(Keyword.WHILE);
        expResult.add(new Identifier("a"));
        expResult.add(Keyword.EQ);
        expResult.add(new Number(49));
        expResult.add(Keyword.LBRACE);
        expResult.add(new Identifier("xxaa3"));
        expResult.add(Keyword.DEF);
        expResult.add(new Number(42));
        expResult.add(Keyword.SEMICOLON);
        expResult.add(new Identifier("y"));
        expResult.add(Keyword.DEF);
        expResult.add(new Number(33));
        expResult.add(Keyword.SEMICOLON);
        expResult.add(Keyword.RBRACE);
        List<Token> result = instance.lex(input);
        assertEquals(expResult, result);
    }
    
}
