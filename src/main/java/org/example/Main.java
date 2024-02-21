package org.example;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Barberia barberia = new Barberia();

        // Creamos un barbero y lo iniciamos
        Barbero barbero = new Barbero(barberia);
        Thread tb = new Thread(barbero);
        tb.start();

        for (int i = 1; i < 11; i++) {
            // Creamos un cliente y lo iniciamos
            Cliente cliente = new Cliente(barberia, i);
            Thread tc = new Thread(cliente);
            tc.start();

            // Simulamos la llegada con tiempos random de los clientes
            try {
                Thread.sleep(new Random().nextInt(2000) + 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}