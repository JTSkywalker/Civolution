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
import content.Situation;
import lang.Interpreter;
import lang.imp.ImpInterpreter;

/**
 *
 * @author JTSkywalker <jtskywalker@t-online.de>
 */
public class HandleFactory implements handle.HandleFactory {

    @Override
    public Thinker createThinker(Civolution civolution) {
        return new Thinker(civolution);
    }

    @Override
    public Interpreter createInterpreter(Situation mySituation) {
        return new ImpInterpreter(mySituation);
    }

    @Override
    public CommandOperator createCommandOperator(Civolution civolution) {
        return new CommandOperator(civolution);
    }
    
}
