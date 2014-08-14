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
package lang.imp;

import content.Situation;
import handle.CommCluster;
import handle.Stopped;
import lang.Interpreter;

/**
 *
 * @author JTSkywalker <jtskywalker@t-online.de>
 */
public class ImpInterpreter implements Interpreter {

    public ImpInterpreter(Situation mySituation) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean interpret() throws Stopped {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void interpretSim() throws Stopped {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void reset() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public CommCluster getCommands() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
