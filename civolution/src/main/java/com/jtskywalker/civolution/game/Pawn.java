/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.game;

import com.jtskywalker.civolution.server.Actor;
import java.util.Objects;

/**
 *
 * @author rincewind
 */
public class Pawn {
    
    final int emblem, baseStrength, baseMobility, population;
    double fitness;
    final boolean canAttack;
    final Actor actor;

    public Pawn(int nation, int baseStrength, int baseMobility, int population, boolean canAttack, Actor actor) {
        this.emblem = nation;
        this.baseStrength = baseStrength;
        this.baseMobility = baseMobility;
        this.population = population;
        this.canAttack = canAttack;
        this.actor = actor;
        this.fitness = 1;
    }

    public int getEmblem() {
        return emblem;
    }

    public int getBaseStrength() {
        return baseStrength;
    }

    public int getBaseMobility() {
        return baseMobility;
    }

    public int getPopulation() {
        return population;
    }

    public double getFitness() {
        return fitness;
    }

    public boolean isCanAttack() {
        return canAttack;
    }

    public Actor getActor() {
        return actor;
    }  
    
    
    public boolean canAttack() {
        return canAttack;
    }
    
    public int getEffectiveStrength() {
        return (int) (getFitness() * getBaseStrength());
    }
    
    public int getEffectiveMobility() {
        if (getFitness() < 0.5) {
            return (int) (2 * getFitness() * getBaseMobility());
        } else {
            return getBaseMobility();
        }
    }

    void setFitness(double d) {
        fitness = d;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.emblem;
        hash = 53 * hash + this.baseStrength;
        hash = 53 * hash + this.baseMobility;
        hash = 53 * hash + this.population;
        hash = 53 * hash + (int) (Double.doubleToLongBits(this.fitness) ^ (Double.doubleToLongBits(this.fitness) >>> 32));
        hash = 53 * hash + (this.canAttack ? 1 : 0);
        hash = 53 * hash + Objects.hashCode(this.actor);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pawn other = (Pawn) obj;
        if (this.emblem != other.emblem) {
            return false;
        }
        if (this.baseStrength != other.baseStrength) {
            return false;
        }
        if (this.baseMobility != other.baseMobility) {
            return false;
        }
        if (this.population != other.population) {
            return false;
        }
        if (Double.doubleToLongBits(this.fitness) != Double.doubleToLongBits(other.fitness)) {
            return false;
        }
        if (this.canAttack != other.canAttack) {
            return false;
        }
        if (!Objects.equals(this.actor, other.actor)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Counter{" + "nation=" + emblem + ", baseStrength=" + baseStrength + ", baseMobility=" + baseMobility + ", population=" + population + ", fitness=" + fitness + ", canAttack=" + canAttack + ", actor=" + actor + '}';
    }
    
    
    
  
}
