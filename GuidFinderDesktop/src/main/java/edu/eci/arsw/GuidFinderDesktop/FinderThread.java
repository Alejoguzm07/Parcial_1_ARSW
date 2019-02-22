/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.GuidFinderDesktop;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.midi.Soundbank;

/**
 *
 * @author 2132219
 */
public class FinderThread extends Thread {

    private int ini, fin;
    private boolean running, finished;
    private UUID guidToFind;
    private UUID[] guids;
    private AtomicInteger count;

    public FinderThread(int ini, int fin, UUID guidToFind, UUID[] guids, AtomicInteger count) {
        this.ini = ini;
        this.fin = fin;
        this.guidToFind = guidToFind;
        this.guids = guids;
        this.count = count;
        running = false;
        finished = false;
    }

    @Override
    public void run() {        
        if (running) {
            for (int i = ini; i <= fin; i++) {
                UUID uuid = guids[i];
                if (uuid.equals(guidToFind)) {
                    count.addAndGet(1);
                }
            }
            finished = true;
        }else{
            try {
                synchronized(this){
                    this.wait();
                }
                
            } catch (InterruptedException ex) {
                Logger.getLogger(FinderThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    void dormir() throws InterruptedException {
        running = false;
    }

    void correr() {
        running = true;
        synchronized(this){
            notifyAll();
            run();
        }
    }

    boolean yaAcabo() {
        return finished;
    }

}
