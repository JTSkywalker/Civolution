/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.view;

import com.jtskywalker.civolution.game.BodyFactory;
import com.jtskywalker.civolution.game.DemoGame;
import com.jtskywalker.civolution.game.Body;
import com.jtskywalker.civolution.controller.Actor;
import com.jtskywalker.civolution.controller.Coordinates;
import com.jtskywalker.civolution.controller.Controller;
import com.jtskywalker.civolution.controller.Mind;
import com.jtskywalker.civolution.controller.Subordinate;
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
public class StartUI extends Application {
    
    Body pawn;
    StackPane pane;    
    
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
        
        primaryStage.setTitle("Civolution");
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
        DemoGame game = new DemoGame(width,height);
        
        Controller controller = new Controller(game);
        Mind humanMind = new ExternalMind(0);
        Body queen = BodyFactory.createQueen(0);
        GameUI human = new GameUI(queen, humanMind, controller);
        game.putActor(human, new Coordinates(1,1,width,height));
        
        Body warrior = BodyFactory.createWarrior(0);
        Mind subM1 = new Subordinate(0);
        Actor sub1 = new Actor(warrior ,subM1, controller);
        game.putActor(sub1, new Coordinates(3, 3, width, height));
        
        Body scout = BodyFactory.createScout(0);
        Mind subM2 = new Subordinate(0);
        Actor sub2 = new Actor(scout, subM2, controller);
        game.putActor(sub2, new Coordinates(0, 5, width, height));
        
        // this shouldn't be that ugly...
        controller.addActor(human);
        controller.addActor(sub1);
        controller.addActor(sub2);
        
        stage.setScene(new Scene(human.getRoot(), 960, 1000));
        stage.show();
        
        controller.notifyNextActor();
    }
    
}
