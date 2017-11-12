/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.view;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;

/**
 *
 * @author jt
 */
public class Tile extends AnchorPane {
    
    int warriors;
    int settlers;
    int queens;
    int scout;
    
    final Label txtWarrior = new Label();
    final Label txtSettler = new Label();
    final Label txtQueen = new Label();
    final Label txtScout = new Label();

    public Tile(int warriors, int settlers, int queens, int scouts) {
        this.warriors = warriors;
        this.settlers = settlers;
        this.queens = queens;
        this.scout = scouts;
        txtWarrior.setText(stringify(warriors));
        txtSettler.setText(stringify(settlers));
        txtQueen.setText(stringify(queens));
        txtScout.setText(stringify(scouts));
        
        getChildren().add(txtQueen);
        AnchorPane.setTopAnchor(txtQueen, 5.0);
        AnchorPane.setRightAnchor(txtQueen, 5.0);
        
        getChildren().add(txtWarrior);
        AnchorPane.setTopAnchor(txtWarrior, 5.0);
        AnchorPane.setLeftAnchor(txtWarrior, 5.0);
        
        getChildren().add(txtScout);
        AnchorPane.setBottomAnchor(txtScout, 5.0);
        AnchorPane.setLeftAnchor(txtScout, 5.0);
        
        getChildren().add(txtSettler);
        AnchorPane.setBottomAnchor(txtSettler, 5.0);
        AnchorPane.setRightAnchor(txtSettler, 5.0);
    }
    
    private String stringify(int x) {
        return x != 0 ? Integer.toString(x) : " ";
    }

    public int getWarriors() {
        return warriors;
    }

    public int getSettlers() {
        return settlers;
    }

    public int getQueens() {
        return queens;
    }

    public int getScout() {
        return scout;
    }

    public void setWarriors(int warrior) {
        this.warriors = warrior;
        txtWarrior.setText(stringify(warrior));
    }

    public void setSettlers(int settler) {
        this.settlers = settler;
        txtSettler.setText(stringify(settler));
    }

    public void setQueens(int queen) {
        this.queens = queen;
        txtQueen.setText(stringify(queen));
    }

    public void setScouts(int scout) {
        this.scout = scout;
        txtScout.setText(stringify(scout));
    }
    
    
    
}
