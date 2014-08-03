/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package handle;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 *
 * @author VAIO
 */
public interface Thinker {

    public void think(CommandCluster commands, AtomicBoolean commandsAccepted);
    
}
