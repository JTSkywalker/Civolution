/*
 * Copyright (C) 2014 JTSkywalker <jtskywalker@t-online.de>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package handle;

import content.Game;

/**
 *
 * @author JTSkywalker <jtskywalker@t-online.de>
 */
public class Handle {
    
    private final Game    game;
    private final Actions actions;
    private final Conditions conditions;
    
    private final ExitDialog exit;
    
    public Handle(Game game, Actions actions,
            Conditions conditions, ExitDialog exitDialog) {
        this.game = game;
        this.actions = actions;
        this.conditions = conditions;
        this.exit = exitDialog;
    }
            
    public void play() {
        Reaction next;
        Actors others;
        while(true) {
            next = game.nextReaction(actions, conditions);
            next.start();
            others = next.others();
            if(next.isHuman())
                others.think(actions, conditions);
            next.operate(actions, conditions);
            others.terminate();
            others.waitForTermination();
        }
    }
}
