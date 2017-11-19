/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.controller;

/**
 *
 * @author rincewind
 */
public interface Horizon {
    
    //public void printOn(PrintStream OUTPUT);
    
    public void putActor(Actor actor, Coordinates coord);

    public int getSettlersAt(int i, int j);

    public int getWarriorsAt(int i, int j);

    public int getScoutsAt(int i, int j);

    public int getQueensAt(int i, int j);
    
}