/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.view;

import com.jtskywalker.civolution.game.CounterFactory;
import com.jtskywalker.civolution.game.Game;
import com.jtskywalker.civolution.game.Pawn;
import com.jtskywalker.civolution.lang.Statement;
import com.jtskywalker.civolution.server.Action;
import com.jtskywalker.civolution.server.Actor;
import com.jtskywalker.civolution.server.Coordinates;
import com.jtskywalker.civolution.server.Horizon;
import com.jtskywalker.civolution.server.Controller;
import com.jtskywalker.civolution.server.Subordinate;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author jt
 */
public class StartUI extends Application implements Actor {
    
    Pawn pawn;
    StackPane pane;
    HumanActor huac;
    
    
    @Override
    public void start(Stage primaryStage) {
        
        StackPane root = new StackPane();
        Button btn = new Button();
        btn.setText("Start demo");
        btn.setOnAction((ActionEvent event) -> {
            demoSetup(primaryStage);
        });
        root.getChildren().add(btn);
        
        Scene scene = new Scene(root, 960, 1000);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    void demoSetup(Stage stage) {
        
        int width = 10;
        int height = 15;
        Game game = new Game(width,height);
        
        Controller controller = new Controller(game);
        Actor human = new HumanActor(controller, stage, width, height);
        Pawn queen = CounterFactory.createQueen(0, human);
        game.putCounter(queen, new Coordinates(1,1,width,height));
        
        Actor sub1 = new Subordinate(0, controller);
        Actor sub2 = new Subordinate(0, controller);
        game.putCounter(CounterFactory.createWarrior(0,sub1),
                new Coordinates(3, 3, width, height));
        game.putCounter(CounterFactory.createScout(0, sub2),
                new Coordinates(0, 5, width, height));
        
        // this shouldn't be that ugly...
        controller.addActor(human);
        controller.addActor(sub1);
        controller.addActor(sub2);
        controller.notifyNextActor();
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean receiveOrders(Statement<Action> orders, int nation) {
        return false;
    }
    
}
