/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.demogame;

import com.jtskywalker.civolution.demogame.action.Move;
import com.jtskywalker.civolution.demogame.action.Attack;
import com.jtskywalker.civolution.demogame.action.Command;
import com.jtskywalker.civolution.game.SquareTileDirection;
import com.jtskywalker.civolution.WHILE.Identifier;
import com.jtskywalker.civolution.lang.ExtParser;
import com.jtskywalker.civolution.lang.Parser;
import com.jtskywalker.civolution.lang.ParserErrorException;
import com.jtskywalker.civolution.lang.Token;
import com.jtskywalker.civolution.controller.Action;
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
                if (internalParser != null) {
                    return new Command(internalParser
                        .parse(block.subList(2, block.size() - 1)));
                } else {
                    throw new ParserErrorException("internalParser not defined!");
                }
            default:
                throw new ParserErrorException();
        }
    }

    private SquareTileDirection parseDirection(Token token) throws ParserErrorException {
        if (!(token instanceof Identifier)) {
            throw new ParserErrorException();
        }
        String s = ((Identifier) token).getName();
        switch (s) {
            case "N": return SquareTileDirection.N;
            case "NE": return SquareTileDirection.NE;
            case "E": return SquareTileDirection.E;
            case "SE": return SquareTileDirection.SE;
            case "S": return SquareTileDirection.S;
            case "SW": return SquareTileDirection.SW;
            case "W": return SquareTileDirection.W;
            case "NW": return SquareTileDirection.NW;
            default : throw new ParserErrorException();
        }
    }

    @Override
    public void setIntParser(Parser<Action> internalParser) {
        this.internalParser = internalParser;
    }


}
