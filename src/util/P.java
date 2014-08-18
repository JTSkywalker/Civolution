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

package util;

/**
 *
 * @author JTSkywalker <jtskywalker@t-online.de>
 * @param <t1>
 * @param <t2>
 */
public class P <t1, t2> {
    
    private final t1 first;
    private final t2 second;
    
    public P(t1 first, t2 second) {
        this.first  = first;
        this.second = second;
    }
    
    public t1 first() {
        return first;
    }
    
    public t2 second() {
        return second;
    }
}
