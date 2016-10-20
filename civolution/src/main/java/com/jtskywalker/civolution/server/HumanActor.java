/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.server;

import com.jtskywalker.civolution.WHILE.WHILELexer;
import com.jtskywalker.civolution.WHILE.WHILEParser;
import com.jtskywalker.civolution.game.ActionParser;
import com.jtskywalker.civolution.lang.ActionEvaluator;
import com.jtskywalker.civolution.lang.CivoLangException;
import com.jtskywalker.civolution.lang.Evaluator;
import com.jtskywalker.civolution.lang.Parser;
import com.jtskywalker.civolution.lang.Scope;
import com.jtskywalker.civolution.lang.Statement;
import com.jtskywalker.civolution.lang.Token;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author rincewind
 */
public class HumanActor extends Actor {
    
    public final static  InputStream INPUT  = System.in;
    public final static  PrintStream OUTPUT = System.out;
    
    final WHILELexer lexer = new WHILELexer();
    final Parser<Action> parser
            = new WHILEParser(new ActionParser());

    @Override
    public Action nextAction(Horizon horizon) {
        horizon.printOn(OUTPUT);
        Evaluator<Action> eval = new ActionEvaluator(horizon);
        Scanner sc = new Scanner(INPUT);
        String input = sc.nextLine();
        try {
            List<Token> tokenlist = lexer.lex(input);
            Statement<Action> program = parser.parse(tokenlist, 0);
            program.nextExternal(eval, new Scope(null), 0);
        } catch (CivoLangException ex) {
            ex.printStackTrace();
            return nextAction(horizon);
        }
        return null;
    }
    
}
