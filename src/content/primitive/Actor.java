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

package content.primitive;

import lang.ast.comm.Comm;


/**
 *
 * @author JTSkywalker <jtskywalker@t-online.de>
 */
public abstract class Actor implements content.Actor {

    protected Coordinates pos;
    protected boolean moveable;
    
    @Override
    public void go(int rx, int ry) {
        pos = pos.add(rx, ry);
    }

    @Override
    public void fortify() {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public void attack(content.Actor enemy) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public Comm comm(Comm comm, content.Actor commander) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
