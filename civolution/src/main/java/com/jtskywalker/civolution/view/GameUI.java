/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.view;

import com.jtskywalker.civolution.WHILE.WHILELexer;
import com.jtskywalker.civolution.controller.Action;
import com.jtskywalker.civolution.controller.Actor;
import com.jtskywalker.civolution.controller.Controller;
import com.jtskywalker.civolution.controller.ExternalMind;
import com.jtskywalker.civolution.game.Horizon;
import com.jtskywalker.civolution.lang.ExtParser;
import com.jtskywalker.civolution.lang.Parser;
import com.jtskywalker.civolution.lang.ParserErrorException;
import com.jtskywalker.civolution.lang.ParserImpl;
import com.jtskywalker.civolution.lang.Statement;
import com.jtskywalker.civolution.lang.Token;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/**
 *
 * @author jt
 * @param <T>
 */
public class GameUI<T extends Horizon> implements Actor<T> {
    
    private final BorderPane root = new BorderPane();
    
    // and here
    private final HorizonPaneWrapper<T> overviewWrapper;
    private final HorizonPaneWrapper<T> detailWrapper;
    
    private final Button btn = new Button();
    private final TextField txt = new TextField();

    private final WHILELexer lexer
            = new WHILELexer();
    private final Parser<Action> parser;
    
    private T horizon;
   
    private final ExternalMind mind;
    private Controller controller;
    
    public GameUI(ExternalMind mind,
            ExtParser<Action> extParser,
            HorizonPaneWrapper<T> overviewWrapper, 
            HorizonPaneWrapper<T> detailWrapper) { 
        this.mind = mind;
        this.overviewWrapper= overviewWrapper;
        this.detailWrapper  = detailWrapper;
        this.parser  = new ParserImpl(extParser);
        init();
    }
    
    private void init() {
        
        //setup cmpPane
        BorderPane cmdPane = new BorderPane();
        btn.setText("do it !");
        btn.setOnAction((ActionEvent event) -> {
            executeOrder(txt.getText());
        });
        cmdPane.setCenter(txt);
        cmdPane.setRight(btn);
        
        
        //setup root
        root.setCenter(overviewWrapper.getPane());
        root.setBottom(cmdPane);
        root.setRight(detailWrapper.getPane());
        btn.setDisable(true);
    }
    
    public Pane getRoot() {
        return root;
    }
    
    private void executeOrder(String input) {
        btn.setDisable(true);
        try {
            List<Token> tokenlist = lexer.lex(input);
            Statement<Action> program = parser.parse(tokenlist);
            this.setOrders(program);
            this.findNextAction(horizon, controller);
        } catch (ParserErrorException ex) {
            ex.printStackTrace();
            btn.setDisable(false);
            Logger.getLogger(GameUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void findNextAction(T horizon, Controller controller) {
        this.controller = controller;
        this.horizon = horizon;
        updateView();
        Action next = this.nextAction(horizon);
        if (next != null) {
            controller.executeAction(this, next);
        }
        btn.setDisable(false);
    }
    
    private void updateView() {
        overviewWrapper.update(horizon);
        detailWrapper.update(horizon);
    }

    @Override
    public Action nextAction(Horizon horizon) {
        return mind.nextAction(horizon);
    }

    @Override
    public boolean receiveOrders(Statement orders, int nation) {
        return mind.receiveOrders(orders, nation);
    }

    @Override
    public void setOrders(Statement orders) {
        mind.setOrders(orders);
    }
    
}
