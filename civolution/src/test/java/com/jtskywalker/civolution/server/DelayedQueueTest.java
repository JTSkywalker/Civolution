/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtskywalker.civolution.server;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jt
 */
public class DelayedQueueTest {
    
    public DelayedQueueTest() {
    }

    /**
     * Test of getDelay method, of class DelayedQueue.
     */
    @Test
    public void testGetDelay() {
        System.out.println("getDelay");
        String str = "hallo";
        List<String> li = new ArrayList<>();
        li.add("Hallo");
        li.add("Test");
        li.add("test!");
        DelayedQueue instance = new DelayedQueue(li);
        instance.put("hallo", 3);
        int expResult = 3;
        int result = instance.getDelay(str);
        assertEquals(expResult, result);
    }

    /**
     * Test of getDelay method, of class DelayedQueue.
     */
    @Test
    public void testGetDelay2() {
        System.out.println("getDelay");
        DelayedQueue ins = new DelayedQueue();
        ins.put("fourteen", 14);
        ins.put("seven",7);
        ins.put("three-hundred", 300);
        ins.put("twenty-four", 24);
        ins.steps(10);
        ins.steps(4);
        assertEquals(0, ins.getDelay("fourteen"));
        assertEquals(-7,ins.getDelay("seven"));
        assertEquals(286,ins.getDelay("three-hundred"));
        assertEquals(10,ins.getDelay("twenty-four"));
    }

    /**
     * Test of pop method, of class DelayedQueue.
     */
    @Test
    public void testPop() {
        System.out.println("pop");
        DelayedQueue ins = new DelayedQueue();
        ins.put("fourteen", 14);
        ins.put("seven",7);
        ins.put("three-hundred", 300);
        ins.put("twenty-four", 24);
        ins.steps(23);
        ins.step();
        List<String> expRes = new ArrayList<>();
        expRes.add("seven");
        expRes.add("fourteen");
        expRes.add("twenty-four");
        assertEquals(expRes,ins.popAll());
    }
    /**
     * Test of pop method, of class DelayedQueue.
     */
    @Test
    public void testPop2() {
        System.out.println("pop");
        DelayedQueue ins = new DelayedQueue();  
        ins.put("fourteen", 14);
        ins.put("seven",7);
        ins.put("twenty-four", 5);
        ins.put("three-hundred", 300);
        ins.put("twenty-four", 24);
        ins.steps(23);
        ins.step();
        List<String> expRes = new ArrayList<>();
        expRes.add("seven");
        expRes.add("fourteen");
        expRes.add("twenty-four");
        assertEquals(expRes,ins.popAll());
    }
    
    @Test
    public void testPut() {
        System.out.println("put");
        DelayedQueue ins = new DelayedQueue();
        Integer r1 = ins.put("fourteen", 14);
        Integer r2 = ins.put("seven",7);
        Integer r3 = ins.put("twenty-four", 5);
        Integer r4 = ins.put("three-hundred", 300);
        Integer r5 = ins.put("twenty-four", 24);
        assertEquals(null,r1);
        assertEquals(null,r2);
        assertEquals(null,r3);
        assertEquals(null,r4);
        assertEquals((Integer) 5,r5);
    }
    
}
