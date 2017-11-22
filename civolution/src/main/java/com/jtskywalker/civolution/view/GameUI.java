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
import com.jtskywalker.civolution.game.Horizon;
import com.jtskywalker.civolution.controller.Mind;
import com.jtskywalker.civolution.lang.ActionEvaluator;
import com.jtskywalker.civolution.lang.Evaluator;
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
public class GameUI<T extends Horizon> extends Actor<T> {
    
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
   
    
    public GameUI(Mind mind, Controller controller, ExtParser<Action> extParser,
            HorizonPaneWrapper<T> overviewWrapper, 
            HorizonPaneWrapper<T> detailWrapper) {
        super(mind, controller);
        //TODO: This is suspicious!!! I am handing around an uninitialised controller
        this.overviewWrapper= overviewWrapper;
        this.detailWrapper  = detailWrapper;
        this.parser  = new ParserImpl(extParser);
    }
    
    public void init() {
        
        //setup cmpPane
        BorderPane cmdPane = new BorderPane();
        btn.setText("do it !");
        btn.setOnAction((ActionEvent event) -> {
            executeOrder(txt.getText());
        });
        cmdPane.setCenter(txt);
        cmdPane.setRight(btn);
        
        //setup map
        
        //setup infoPane: this should be solved with a visitor
        //Label infoText = new Label();
        //infoPane.getChildren().add(infoText);
        //infoText.setText(body.getJSON().toJSONString() + "\n" + "test");
        
        //setup root
        root.setCenter(overviewWrapper.getPane());
        root.setBottom(cmdPane);
        root.setRight(detailWrapper.getPane());
    }
    
    public Pane getRoot() {
        return root;
    }
    
    private void executeOrder(String input) {
        btn.setDisable(true);
        try {
            Evaluator<Action> eval = new ActionEvaluator(horizon);
            List<Token> tokenlist = lexer.lex(input);
            Statement<Action> program = parser.parse(tokenlist);
            super.setOrders(program);
            super.findNextAction(horizon);
        } catch (ParserErrorException ex) {
            ex.printStackTrace();
            btn.setDisable(false);
            Logger.getLogger(GameUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void findNextAction(T horizon) {
        this.horizon = horizon;
        updateView();
        super.findNextAction(horizon);
        btn.setDisable(false);
    }
    
    //FIXME: I have broken my separation! here 
    private void updateView() {
        overviewWrapper.update(horizon);
        detailWrapper.update(horizon);
    }
    
}
