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

import content.Civolution;
import static handle.Util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;
import lang.Interpreter;

/**
 *
 * @author JTSkywalker <jtskywalker@t-online.de>
 */
public class Handler {
         
    private final CommCluster commands;
    private final Civolution civolution;
    
    private final      Thinker thinker;
    private final CommOperator coperator;
    private final  Interpreter interpreter;
    private final   ExitDialog exitDialog;
    
    private final AtomicBoolean running;
    private final AtomicBoolean autoSimMode;
    private final AtomicBoolean commandsAccepted;
    private final AtomicBoolean operationTerminated;

    public Handler(Civolution civolution, CommCluster commands,
            HandleFactory hFactory, ExitDialog exitDialog) {
        running             = new AtomicBoolean(true);
        autoSimMode         = new AtomicBoolean();
        commandsAccepted    = new AtomicBoolean();
        operationTerminated = new AtomicBoolean();
        this.thinker        = hFactory.createThinker(civolution);
        this.interpreter    = hFactory.createInterpreter(
                                        civolution.getMySituation());
        this.coperator      = hFactory.createCommandOperator(civolution);
        this.commands       = commands;
        this.civolution     = civolution;
        this.exitDialog     = exitDialog;
    }
    
    public void play() {
        Thread I = new Thread(new InterpreterHandler());
        I.start();
        
        while(running.get()) {
            //thinker.think(commands, commandsAccepted);//thinking true <-> stop
            thinker.startThinking(commands);
            waitForCondition(commandsAccepted);
            thinker.stopThinking();
            thinker.waitForTermination();
            commandsAccepted.set(false);
            
            if(running.get())  {
                coperator.operate(commands);
                signalAll(operationTerminated);
            }
        }
        try {
            I.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Handler.class.getName())
                            .log(Level.SEVERE, null, ex);
        }
        exitDialog.run(civolution, commands);
    }
    
    class InterpreterHandler implements Runnable {

        @Override
        @SuppressWarnings("empty-statement")
        public void run() {
            try {
                while(true) {
                    while (!interpreter.interpret());
                    signalAll(operationTerminated);

                    commands.addCC(interpreter.getCommands());
                    interpreter.reset();

                    if(autoSimMode.get())
                        while (!operationTerminated.get())
                            interpreter.interpretSim();
                    else
                        waitForCondition(operationTerminated);
                    operationTerminated.set(false);
                }
            } catch (Stopped st) {
                signalAll(commandsAccepted);
                running.set(false);
            }
        }
    }
}
