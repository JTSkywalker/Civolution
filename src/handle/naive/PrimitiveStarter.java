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

import static handle.Util.*;
import java.util.Scanner;

/**
 *
 * @author JTSkywalker <jtskywalker@t-online.de>
 */
public class PrimitiveStarter {
    
    private static Scanner scanner;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if(args.length == 0) {
            scanner = new Scanner(System.in);
            whatToDo();
        } else if("n".equals(args[0])) {
            startNewGame();
        } else if("l".equals(args[0])) {
            loadStoredGame();
        }
    }
    
    private static void whatToDo() {
        println("Do you want to\n\tstart a new game (n)\n"
                              + "\tor load a stored (l) one?");
        switch (scanner.next()) {
            case "n":
                startNewGame();
                break;
            case "N":
                startNewGame();
                break;
            case "l":
                loadStoredGame();
                break;
            case "L":
                loadStoredGame();
                break;
            default:
                println("You have submitted illegal input.\n");
                whatToDo();
        }
    }
    
    private static void startNewGame() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private static void loadStoredGame() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    private static void setPreferences() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
