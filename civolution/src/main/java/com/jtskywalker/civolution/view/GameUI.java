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
 * This is the standard GameUI. It includes a TextField to enter commands and a
 * Button to send them, as well as two HorizonPaneWrapper to show the current 
 * information to the player. For these HorizonPaneWrapper either a suitable 
 * general implementation should be used or they have to be customly implemented
 * for any specific game.
 * @author jt
 * @param <T> - specifies the specific kind of Horizon used in this GameUI
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
    
    /**
     * The only constructor. 
     * @param mind - the ExternalMind used for this GameUI
     * @param extParser - the parser to parse external statements, i.e. Actions
     * @param overviewWrapper - recommended to use to give an overview on the horizon
     * @param detailWrapper - recommended to use to show some specific details 
     */
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
    
    /**
     * Getter for the root pane.
     * @return - returns the root pane
     */
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
    
    /**
     * Called by the controller to notify the UI, that an action is admissible.
     * @param horizon - the current Horizon of the player
     * @param controller - the Controller to deliver the next Action to
     */
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

    /**
     * Calls the mind to compute the next Action.
     * @param horizon - the current horizon
     * @return - returns the next Action
     */
    @Override
    public Action nextAction(Horizon horizon) {
        return mind.nextAction(horizon);
    }

    /**
     * Calls the mind to decide what to do with the receivedOrders
     * @param orders - received orders
     * @param nation - the nation of the instructor
     * @return - returns true iff the orders are accepted
     */
    @Override
    public boolean receiveOrders(Statement orders, int nation) {
        return mind.receiveOrders(orders, nation);
    }

    /**
     * Set the orders in the mind.
     * @param orders - new orders to set
     */
    @Override
    public void setOrders(Statement orders) {
        mind.setOrders(orders);
    }
    
}
