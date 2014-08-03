/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lang;

import handle.CommandCluster;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 *
 * @author VAIO
 */
public interface Interpreter {

    public void read();

    public AtomicBoolean getRunning();

    public AtomicBoolean getAutoSimMode();

    public boolean interpret();

    public void interpretSim();

    public void reset();

    public CommandCluster getCommands();
    
}
