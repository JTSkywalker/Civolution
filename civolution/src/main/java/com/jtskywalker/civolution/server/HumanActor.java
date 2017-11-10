/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.server;

import com.jtskywalker.civolution.WHILE.WHILELexer;
import com.jtskywalker.civolution.game.ActionParser;
import com.jtskywalker.civolution.lang.ActionEvaluator;
import com.jtskywalker.civolution.lang.CivoLangException;
import com.jtskywalker.civolution.lang.Evaluator;
import com.jtskywalker.civolution.lang.Parser;
import com.jtskywalker.civolution.lang.ParserImpl;
import com.jtskywalker.civolution.lang.Scope;
import com.jtskywalker.civolution.lang.Statement;
import com.jtskywalker.civolution.lang.Token;
import com.jtskywalker.civolution.lang.statement.ExternalStmt;
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
            = new ParserImpl(new ActionParser());
    
    Statement<Action> program = null;
    final Scope<String,Integer> scope = new Scope(null);

    @Override
    public Action nextAction(Horizon horizon) {
        Evaluator<Action> eval = new ActionEvaluator(horizon);
        if (program == null) {
            horizon.printOn(OUTPUT);
            Scanner sc = new Scanner(INPUT);
            String input = sc.nextLine();
            try {
                List<Token> tokenlist = lexer.lex(input);
                program = parser.parse(tokenlist);
            } catch (CivoLangException ex) {
                ex.printStackTrace();
                return nextAction(horizon);
            }
        }
        ExternalStmt<Action> result 
                = program.nextExternal(eval, scope);
        if (result == null) {
            program = null;
            return nextAction(horizon);
        }
        program = result.getNext();
        return result.getExternal();
    }
    
}
