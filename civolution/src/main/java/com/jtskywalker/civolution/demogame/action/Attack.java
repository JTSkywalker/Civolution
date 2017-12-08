/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.demogame.action;

import com.jtskywalker.civolution.game.Direction;
import com.jtskywalker.civolution.game.Coordinates;
import com.jtskywalker.civolution.demogame.Body;
import com.jtskywalker.civolution.demogame.DemoGame;
import com.jtskywalker.civolution.controller.Action;
import com.jtskywalker.civolution.controller.ActionNotAllowedException;
import com.jtskywalker.civolution.controller.Actor;
import java.util.Objects;

/**
 *
 * @author rincewind
 */
public class Attack implements Action<DemoGame> {
    
    final Direction direction;

    public Attack(Direction direction) {
        this.direction = direction;
    }

    @Override
    public int execute(DemoGame game, Actor actor)
            throws ActionNotAllowedException {
        
        Body body = game.getBody(actor);
        if (!body.canAttack()) {
            throw new ActionNotAllowedException();
        }
        
        Coordinates oldC = game.getCoordinates(actor);
        Coordinates newC = oldC.add(direction);
        if (!game.hasEnemy(newC, body.getEmblem())) {
            throw new ActionNotAllowedException();
        }
        Actor enemyActor = game.getDefender(newC);
        Body enemyBody = game.getBody(enemyActor);
        int bES = enemyBody.getBaseStrength();
        int bMS = body.getBaseStrength();
        int oldES = enemyBody.getEffectiveStrength();
        int oldMS = body.getEffectiveStrength();
        double newES = oldES - 0.5 * oldMS - (Math.random() - 0.5) * bMS;
        double newMS = oldMS - 0.5 * oldES - (Math.random() - 0.5) * bES;
        if (newES > 0) {
            game.setFitness(enemyBody, newES / bES);
        } else {
            game.kill(enemyActor);
        }
        if (newMS > 0) {
            game.setFitness(body, newMS / bMS);
        } else {
            game.kill(actor);
            return -1;
        }
        if (newES <= 0 && newMS > 0) {
            return (new Move(direction).execute(game, actor));
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
        return this.direction == other.direction;
    }

    @Override
    public String toString() {
        return "Attack{" + "direction=" + direction + '}';
    }
    
}
