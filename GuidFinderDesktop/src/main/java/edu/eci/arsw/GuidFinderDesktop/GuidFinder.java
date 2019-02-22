package edu.eci.arsw.GuidFinderDesktop;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;

public class GuidFinder extends JPanel{

    private static UUID[] guids;
    private static FinderThread[] hilos;
    private Boolean flag, bandera;
    private Timer tiempo;
    private TimerTask tk;
    private int tiempoTranscurrido;

    public GuidFinder() throws Exception {
        this.setVisible(false);
        flag = false;
        tiempoTranscurrido = 0;
        KeyListener a = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                if (flag) {
                    tiempoTranscurrido = 0;
                    tiempo.schedule(tk, 0, 1000);
                } else {
                    tiempo.cancel();
                }
            }
        };
        hilos = new FinderThread[4];
        getGuids();
        addKeyListener(a);
        bandera = false;
        tiempo = new Timer();
        tk = new TimerTask() {
            @Override
            public void run() {
                if (acabo()) {
                    tiempo.cancel();
                }
                if (tiempoTranscurrido == 10) {
                    System.err.println(flag);
                    if (flag) {
                        for (int i = 0; i < 4; i++) {
                            try {
                                hilos[i].dormir();
                            } catch (InterruptedException ex) {
                                Logger.getLogger(GuidFinder.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        flag = false;
                    } else {
                        for (int i = 0; i < 4; i++) {
                            hilos[i].correr();
                        }
                        flag = true;
                    }
                    tiempoTranscurrido = 0;
                } else {
                    tiempoTranscurrido++;
                }
            }
        };
    }

    public static UUID[] getGuids() throws Exception {

        if (guids == null) {
            System.out.println("es nulo");
            FileInputStream fi;

            fi = new FileInputStream(new File("guids.eci"));

            ObjectInputStream oi = new ObjectInputStream(fi);

            guids = (UUID[]) oi.readObject();

            oi.close();
            fi.close();
        }
        return guids;

    }

    public int countGuids(UUID guidToFind) {
        AtomicInteger count = new AtomicInteger(0);
        int tam = guids.length / 4;
        int inicio = 0;
        int fin = tam;
        for (int i = 0; i < 3; i++) {
            hilos[i] = new FinderThread(inicio, fin, guidToFind, guids, count);
            inicio = fin + 1;
            fin += tam;
        }
        hilos[3] = new FinderThread(fin, guids.length - 1, guidToFind, guids, count);
        for (int i = 0; i < 4; i++) {
            hilos[i].start();
        }
        return count.get();
    }

    public int timeCountGuids(UUID guidToFind) {
        AtomicInteger count = new AtomicInteger(0);
        int tam = guids.length / 4;
        int inicio = 0;
        int fin = tam;
        for (int i = 0; i < 3; i++) {
            hilos[i] = new FinderThread(inicio, fin, guidToFind, guids, count);
            inicio = fin + 1;
            fin += tam;
        }
        hilos[3] = new FinderThread(fin, guids.length - 1, guidToFind, guids, count);
        tiempo.schedule(tk, 0, 1000);
        for (int i = 0; i < 4; i++) {
            hilos[i].start();
        }
        while (!acabo()) {
        }
        return count.get();
    }

    private boolean acabo() {
        boolean f = true;
        for (int i = 0; i < 4 && f; i++) {
            if (!hilos[i].yaAcabo()) {
                f = false;
            }
        }
        return f;
    }

}
