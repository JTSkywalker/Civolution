/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.game;

import com.jtskywalker.civolution.WHILE.Identifier;
import com.jtskywalker.civolution.game.action.*;
import com.jtskywalker.civolution.lang.ExtParser;
import com.jtskywalker.civolution.lang.Parser;
import com.jtskywalker.civolution.lang.ParserErrorException;
import com.jtskywalker.civolution.lang.Token;
import com.jtskywalker.civolution.server.Action;
import java.util.List;

/**
 *
 * @author rincewind
 */
public class ActionParser implements ExtParser<Action> {
    
    Parser<Action> internalParser;

    @Override
    public Action parse(List<Token> block) throws ParserErrorException {
        if (!(block.get(0) instanceof Identifier)) {
            throw new ParserErrorException();
        }
        String name = ((Identifier) block.get(0)).getName();
        switch (name) {
            case "attack":
                return new Attack(parseDirection(block.get(1)));
            case "move":
                return new Move(parseDirection(block.get(1)));
            case "command":
                return new Command(internalParser
                        .parse(block.subList(2, block.size() - 1)));
            default:
                throw new ParserErrorException();
        }
    }

    private Direction parseDirection(Token token) throws ParserErrorException {
        if (!(token instanceof Identifier)) {
            throw new ParserErrorException();
        }
        String s = ((Identifier) token).getName();
        switch (s) {
            case "N": return Direction.N;
            case "NE": return Direction.NE;
            case "E": return Direction.E;
            case "SE": return Direction.SE;
            case "S": return Direction.S;
            case "SW": return Direction.SW;
            case "W": return Direction.W;
            case "NW": return Direction.NW;
            default : throw new ParserErrorException();
        }
    }

    @Override
    public void setIntParser(Parser<Action> internalParser) {
        this.internalParser = internalParser;
    }


}
