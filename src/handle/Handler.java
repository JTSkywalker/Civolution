/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package handle;

import content.Civolution;
import handle.awesome.AIthought;
import static java.lang.Thread.sleep;
import java.util.Collection;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;
import lang.Interpreter;
import lang.imp.ImpInterpreter;

/**
 *
 * @author VAIO
 */
public class Handler {
         
    private CommandCluster commands;
    private final Civolution civolution;
    
    private final          Thinker thinker;
    private final      Interpreter interpreter;
    private final  CommandOperator coperator;
    
    private AtomicBoolean running;
    private AtomicBoolean autoSimMode;
    private AtomicBoolean commandsAccepted;
    private AtomicBoolean operationTerminated;

    public Handler(Thinker thinker, Interpreter interpreter,
            CommandOperator coperator,
            CommandCluster commands, Civolution civolution) {
        this.thinker     = thinker;
        this.interpreter = interpreter;
        this.coperator   = coperator;
        this.commands    = commands;
        this.civolution  = civolution;
        running          = interpreter.getRunning();
        autoSimMode      = interpreter.getAutoSimMode();
    }
    
    public void play() {
        new Thread(new InterpreterHandler()).start();
        
        while(running.get()) {
            thinker.think(commands, commandsAccepted);//thinking true <-> stop
            
            coperator.operate(commands);    //should start new Thread/s
            synchronized(this) {
                operationTerminated.set(true);
                notify();
            }
        }
    }
    
    class InterpreterHandler implements Runnable {

        @Override
        @SuppressWarnings("empty-statement")
        public void run() {
            while (running.get()) {                
                while (!interpreter.interpret());
                commandsAccepted.set(true);
                
                commands.addCC(interpreter.getCommands());
                interpreter.reset();
                
                if(autoSimMode.get())
                    while (!operationTerminated.get())
                        interpreter.interpretSim();
                else
                    while (!operationTerminated.get())
                        synchronized(this) {
                            try {
                                wait();//don't like that! not sure…
                            } catch (InterruptedException ex) { }
                        }
                    
            }
        }
        
    }
}
