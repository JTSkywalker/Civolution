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

import java.util.concurrent.atomic.AtomicBoolean;

/**
 *
 * @author JTSkywalker <jtskywalker@t-online.de>
 */
public class Util {

    public static void print(Object out) {
        System.out.print(out);
    }
    public static void println(Object out) {
        System.out.println(out);
    }
    public static void println() {
        System.out.println();
    }
    
    public static void waitForCondition(AtomicBoolean condition) {
        synchronized(condition) {
            while (!condition.get())
                try {
                    condition.wait();
                } catch (InterruptedException ex) { }
        }
    }
    
    public static void signalAll(AtomicBoolean condition) {
        synchronized(condition) {
            condition.set(true);
            condition.notifyAll();
        }
    }
}
