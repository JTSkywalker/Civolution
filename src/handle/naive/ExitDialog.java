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

package handle.naive;

import content.Civolution;
import handle.CommCluster;
import static handle.Util.println;
import java.util.Scanner;

/**
 *
 * @author JTSkywalker <jtskywalker@t-online.de>
 */
public class ExitDialog implements handle.ExitDialog {

    private Scanner scanner;
    
    private Civolution civolution;
    private CommCluster commands;
    
    @Override
    public void run(Civolution civolution, CommCluster commands) {
        
        this.civolution = civolution;
        this.commands   = commands;
        whatToDo();
    }

    private void whatToDo() {
        println("Do you want to save the game? (y/n)");
        switch (scanner.nextLine()) {
            case "y":
                saveGame();
                break;
            case "Y":
                saveGame();
                break;
            case "n":
                discardGame();
                break;
            case "N":
                discardGame();
                break;
            default:
                println("You have submitted illegal input.\n");
                whatToDo();
        }
    }

    private void saveGame() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void discardGame() {
        println("Are you sure to discard the Game? (y/n)");
        switch (scanner.nextLine()) {
            case "y":
                break;
            case "n":
                whatToDo();
                break;
            default:
                println("You have submitted illegal input.\n");
                discardGame();
        }
    }
}
