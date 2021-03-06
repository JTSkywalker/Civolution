/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.demogame;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author jt
 */
public class BodyFactory {
    
    private JSONObject bodies;
    
    public BodyFactory() {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(
                    new FileReader(DemoGame.BASEPATH + "bodies.json"));
            bodies = (JSONObject) obj;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(BodyFactory.class.getName())
                    .log(Level.SEVERE, null, ex);
        } catch (IOException | ParseException ex) {
            Logger.getLogger(BodyFactory.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }
    
    public Body create(String type, int nation) {
        JSONObject jsonObj = (JSONObject) bodies.get(type);
        if (jsonObj == null) {
            throw new IllegalArgumentException(
                    "The body was not found in the Body file!");
        }
        Image image = new Image("file:" + DemoGame.BASEPATH + type + ".png");
        return new Body(nation,
                        Math.toIntExact((Long) jsonObj.get("strength")),
                        Math.toIntExact((Long) jsonObj.get("mobility")),
                        Math.toIntExact((Long) jsonObj.get("population")),
                        (boolean) jsonObj.get("canAttack"),
                        image);
    }
     
}
