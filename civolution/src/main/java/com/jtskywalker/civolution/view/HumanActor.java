/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.view;

import com.jtskywalker.civolution.WHILE.WHILELexer;
import com.jtskywalker.civolution.game.ActionParser;
import com.jtskywalker.civolution.game.Pawn;
import com.jtskywalker.civolution.lang.ActionEvaluator;
import com.jtskywalker.civolution.lang.CivoLangException;
import com.jtskywalker.civolution.lang.Evaluator;
import com.jtskywalker.civolution.lang.Parser;
import com.jtskywalker.civolution.lang.ParserImpl;
import com.jtskywalker.civolution.lang.Scope;
import com.jtskywalker.civolution.lang.Statement;
import com.jtskywalker.civolution.lang.Token;
import com.jtskywalker.civolution.lang.statement.ExternalStmt;
import com.jtskywalker.civolution.server.Action;
import com.jtskywalker.civolution.server.Actor;
import com.jtskywalker.civolution.server.Controller;
import com.jtskywalker.civolution.server.Horizon;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author jt
 */
public class HumanActor extends BorderPane implements Actor {
    
    VBox infoPane;
    MapPane map;
    Button btn;
    TextField txt;
    Scene scene;
    
    Horizon horizon;
    
    final Stage stage;
    final Controller controller;
    final WHILELexer lexer = new WHILELexer();
    final Parser<Action> parser
            = new ParserImpl(new ActionParser());
    Statement<Action> program = null;
    final Scope<String,Integer> scope = new Scope(null);
    Pawn pawn;
    
    public HumanActor(Controller controller, Stage stage,
            int width, int height) {
        this.controller = controller;
        this.stage = stage;
        
        // setup command region
        BorderPane cmdPane = new BorderPane();
        txt = new TextField();
        btn = new Button();
        
        btn.setText("do it!");
        btn.setOnAction((ActionEvent event) -> {
            btn.setDisable(true);
            executeOrder(txt.getText());
        });
        
        cmdPane.setCenter(txt);
        cmdPane.setRight(btn);
        
        // setup info region
        infoPane = new VBox();
        
        // setup map region
        map = new MapPane(width, height);
                
        setCenter(map);
        setBottom(cmdPane);
        setRight(infoPane);
        scene = new Scene(this, 960, 1000);
    }

    @Override
    public Pawn getPawn() {
        return pawn;
    }

    @Override
    public void setPawn(Pawn pawn) {
        this.pawn = pawn;
    }

    @Override
    public void findNextAction(Horizon horizon) {
        this.horizon = horizon;
        updateView(horizon);
        btn.setDisable(false);
        stage.setScene(scene);
        
        Evaluator<Action> eval = new ActionEvaluator(horizon);
        if (program != null) {
            ExternalStmt<Action> result
                    = program.nextExternal(eval, scope);
            if (result == null) {
                program = null;
            } else {
                program = result.getNext();
                controller.executeAction(this, result.getExternal());
            }
        }
    }
    
    void updateView(Horizon horizon) {
        map.updateView(horizon);
    }
    
    void executeOrder(String input) {
        Evaluator<Action> eval = new ActionEvaluator(horizon);
        try {
            List<Token> tokenlist = lexer.lex(input);
            program = parser.parse(tokenlist);
            ExternalStmt<Action> order
                    = program.nextExternal(eval, scope);
            if (order == null) {
                program = null;
                btn.setDisable(false);
            } else {
                program = order.getNext();
                controller.executeAction(this, order.getExternal());
            }
            txt.setText("");
        } catch (CivoLangException ex) {
            ex.printStackTrace();
            btn.setDisable(false);
        }
    }

    @Override
    public boolean receiveOrders(Statement<Action> orders, int nation) {
        return false;
    }
    
}
