/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package handle;

import handle.awesome.AIthought;
import content.Situation;

/**
 *
 * @author VAIO
 */
public interface AI extends Runnable {
    
    AIthought think(Situation situation);
    
}