/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package handle;

import content.Civolution;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author VAIO
 */
public class Handler {
    
    private final ThreadPoolExecutor aiExecutor;
    private final BlockingQueue<Runnable> aiThoughts;
    private final Civolution civolution;
    private boolean run;

    public Handler() {
        aiThoughts = new LinkedBlockingQueue<>();
        aiExecutor = new ThreadPoolExecutor( 0,
                Runtime.getRuntime().availableProcessors(),
                10l, TimeUnit.SECONDS, aiThoughts);
        civolution = new Civolution();
    }
    
    public void play() {
        while(run) {
            
        }
    }
    
    void addThought(AIthought thought) {
        aiThoughts.add(thought);
    }
}
