/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.view;

import com.jtskywalker.civolution.controller.ExternalMind;
import com.jtskywalker.civolution.demogame.BodyFactory;
import com.jtskywalker.civolution.demogame.DemoGame;
import com.jtskywalker.civolution.demogame.BodyImpl;
import com.jtskywalker.civolution.controller.Actor;
import com.jtskywalker.civolution.game.Coordinates;
import com.jtskywalker.civolution.controller.Controller;
import com.jtskywalker.civolution.controller.Mind;
import com.jtskywalker.civolution.controller.Subordinate;
import com.jtskywalker.civolution.demogame.ActionParser;
import java.util.HashMap;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Pair;

/**
 *
 * @author jt
 */
public class StartUI extends Application {
    
    
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
        HashMap<Actor,Pair<BodyImpl,Coordinates>> actors = new HashMap();
        BodyFactory bf = new BodyFactory();
        Controller controller = new Controller();
        
        BodyImpl queen = bf.create("queen", 0);
        BodyImpl warrior = bf.create("warrior", 0);
        BodyImpl scout = bf.create("scout", 0);
        
        Mind humanMind = new ExternalMind(0);
        GameUI human 
                = new GameUI(humanMind, controller, new ActionParser());
        Mind subM1 = new Subordinate(0);
        Actor sub1 = new Actor(subM1, controller);
        Mind subM2 = new Subordinate(0);
        Actor sub2 = new Actor(subM2, controller);
        
        actors.put(human, new Pair(queen, new Coordinates(1,1,width,height)));
        actors.put(sub1,  new Pair(warrior, new Coordinates(3, 3, width, height)));
        actors.put(sub2,  new Pair(scout, new Coordinates(0, 5, width, height)));
        
        DemoGame game = new DemoGame(width, height, actors);
        
        //FIXME: this is kind of ugly
        controller.init(game);
        human.init();
        
        stage.setScene(new Scene(human.getRoot(), 960, 1000));
        stage.show();
        
        controller.notifyNextActor();
    }
    
}
