/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.WHILE;

import com.jtskywalker.civolution.lang.Token;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This is the lexer for the language WHILE.
 * @author jt
 */
public class WHILELexer {
    
    /**
     * The given String is expanded into a list of tokens.
     * @param input to be lexed
     * @return resulting list of tokens
     */
    public List<Token> lex(String input) {
        String prelexed = prelex(input);
        Scanner sc = new Scanner(new StringReader(prelexed));
        List<Token> tokenlist = new ArrayList<>();
        while (sc.hasNext()) {
            tokenlist.add(lexToken(sc.next()));
        }
        return tokenlist;
    }
    
    // support omitting whitespace
    // it's a pretty ugly solution though 
    String prelex(String input) {
        StringBuilder buf = new StringBuilder();
        for (int i=0; i < input.length(); i++) {
            char c = input.charAt(i);
            String spaced = " " + c + " ";
            switch (c) {
                case '{':
                case '}':
                case '[':
                case ']':
                case '+':
                case '-':
                case '?':
                case ';':
                    buf.append(spaced);
                    break;
                case '!':
                case '<':
                case '>':
                case '=':
                    if (i + 1 < input.length() && input.charAt(i+1) == '=') {
                        buf.append(" ").append(c).append("=");
                        i++;//don't visit this symbol again
                    } else
                        buf.append(spaced);
                    break;
                default:
                    buf.append(c);
            }
        }
        return buf.toString();
    }
    
    Token lexToken(String s) {
        switch (s) {
            case "while":
                return Keyword.WHILE;
                
            case "if":
                return Keyword.IF;
            
            case "{":
                return Keyword.LBRACE;
                
            case "}":
                return Keyword.RBRACE;
                
            case "[":
                return Keyword.LBRACKET;
                
            case "]":
                return Keyword.RBRACKET;
            
            case "+":
                return Keyword.PLUS;
              
            case "-":
                return Keyword.MINUS;
                
            case "=":
                return Keyword.DEF;
                
            case "==":
                return Keyword.EQ;
            
            case "!=":
                return Keyword.NEQ;
                
            case "<=":
                return Keyword.LEQ;
                
            case ">=":
                return Keyword.GEQ;
                
            case "<":
                return Keyword.LT;
                
            case ">":
                return Keyword.GT;
                
            case "?":
                return Keyword.QM;
                
            case "!":
                return Keyword.EM;
                
            case ";":
                return Keyword.SEMICOLON;
                
                
            default:
                if (Character.isDigit(s.charAt(0))) {
                    return new Number(Integer.parseInt(s));
                } else {
                    return new Identifier(s);
                }
    
        }
    }

}