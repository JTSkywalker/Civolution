/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.game.action;

import com.jtskywalker.civolution.server.Coordinates;
import com.jtskywalker.civolution.game.Counter;
import com.jtskywalker.civolution.game.Game;
import com.jtskywalker.civolution.server.Action;
import com.jtskywalker.civolution.server.ActionNotAllowedException;
import java.util.Objects;

/**
 *
 * @author rincewind
 */
public class Attack implements Action {
    
    final Direction direction;

    public Attack(Direction direction) {
        this.direction = direction;
    }

    @Override
    public int execute(Game game, Counter counter)
            throws ActionNotAllowedException {
        if (!counter.canAttack()) {
            throw new ActionNotAllowedException();
        }
        
        Coordinates oldC = game.getCoordinates(counter);
        Coordinates newC = oldC.add(direction);
        if (!game.hasEnemy(newC, counter.getNation())) {
            throw new ActionNotAllowedException();
        }
        Counter enemy = game.getDefender(newC);
        int bES = enemy.getBaseStrength();
        int bMS = counter.getBaseStrength();
        int oldES = enemy.getEffectiveStrength();
        int oldMS = counter.getEffectiveStrength();
        double newES = oldES - 0.5 * oldMS - (Math.random() - 0.5) * bMS;
        double newMS = oldMS - 0.5 * oldES - (Math.random() - 0.5) * bES;
        if (newES > 0) {
            game.setFitness(enemy, newES / bES);
        } else {
            game.kill(enemy);
        }
        if (newMS > 0) {
            game.setFitness(counter, newMS / bMS);
        } else {
            game.kill(counter);
            return -1;
        }
        if (newES <= 0 && newMS > 0) {
            return (new Move(direction).execute(game, counter));
        }
        return 1;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.direction);
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
        final Attack other = (Attack) obj;
        if (this.direction != other.direction) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Attack{" + "direction=" + direction + '}';
    }
    
}
