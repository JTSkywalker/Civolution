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
import com.jtskywalker.civolution.demogame.HorizonImpl;
import com.jtskywalker.civolution.lang.ActionEvaluator;
import com.jtskywalker.civolution.lang.Evaluator;
import com.jtskywalker.civolution.lang.ExtParser;
import com.jtskywalker.civolution.lang.Parser;
import com.jtskywalker.civolution.lang.ParserErrorException;
import com.jtskywalker.civolution.lang.ParserImpl;
import com.jtskywalker.civolution.lang.Statement;
import com.jtskywalker.civolution.lang.Token;
import com.jtskywalker.civolution.view.demogame.MapVisitor;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 *
 * @author jt
 */
public class GameUI extends Actor {
    
    private final BorderPane root = new BorderPane();
    
    private final VBox infoPane = new VBox();
    private final GridPane map = new GridPane();
    // and here
    private       MapVisitor mapVisi;
    private final Button btn = new Button();
    private final TextField txt = new TextField();

    private final WHILELexer lexer
            = new WHILELexer();
    private final Parser<Action> parser;
    
    private Horizon horizon;
   
    
    public GameUI(Mind mind, Controller controller, ExtParser<Action> extParser) {
        super(mind, controller);
        //TODO: This is suspicious!!! I am handing around an uninitialised controller
        this.parser = new ParserImpl(extParser);
    }
    
    public void init() {
        int width = super.getController().getWidth();
        int height= super.getController().getHeight();
        
        //setup cmpPane
        BorderPane cmdPane = new BorderPane();
        btn.setText("do it !");
        btn.setOnAction((ActionEvent event) -> {
            executeOrder(txt.getText());
        });
        cmdPane.setCenter(txt);
        cmdPane.setRight(btn);
        
        //setup map
        mapVisi = new MapVisitor(map, width, height);
        
        //setup infoPane: this should be solved with a visitor
        //Label infoText = new Label();
        //infoPane.getChildren().add(infoText);
        //infoText.setText(body.getJSON().toJSONString() + "\n" + "test");
        
        //setup root
        root.setCenter(map);
        root.setBottom(cmdPane);
        root.setRight(infoPane);
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
    public void findNextAction(Horizon horizon) {
        this.horizon = horizon;
        updateView();
        super.findNextAction(horizon);
        btn.setDisable(false);
    }
    
    //FIXME: I have broken my separation! here 
    private void updateView() {
        mapVisi.visit((HorizonImpl) horizon);
    }
    
}
