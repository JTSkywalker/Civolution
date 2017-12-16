/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.view;

import com.jtskywalker.civolution.controller.ExternalMind;
import com.jtskywalker.civolution.demogame.BodyFactory;
import com.jtskywalker.civolution.demogame.DemoGame;
import com.jtskywalker.civolution.demogame.Body;
import com.jtskywalker.civolution.controller.Actor;
import com.jtskywalker.civolution.game.SqCoordinates;
import com.jtskywalker.civolution.controller.Controller;
import com.jtskywalker.civolution.controller.Subordinate;
import com.jtskywalker.civolution.demogame.ActionParser;
import com.jtskywalker.civolution.game.GameMap;
import com.jtskywalker.civolution.game.SqTorusCoordinator;
import com.jtskywalker.civolution.view.demogame.DemogameMapVisitor;
import java.util.HashMap;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
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
        
        int width = 10 , height = 15;
        GameUI gameUI 
                = new GameUI(new ExternalMind(0), new ActionParser(),
            new DemogameMapVisitor(new GridPane(), width, height),
            new HorizonPaneWrapper() {

                VBox pane = new VBox();

                @Override
                public void update(Object t) {
                    // do nothing
                }

                @Override
                public Pane getPane() {
                    return pane;
                }

        });
        DemoGame game = DemoGame.createDemo(width,height,gameUI);

        Controller controller = new Controller(game);
        
        stage.setScene(new Scene(gameUI.getRoot(), 960, 1000));
        stage.show();
        
        controller.notifyNextActor();
    }
    
}
