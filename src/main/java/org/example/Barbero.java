package org.example;

public class Barbero implements Runnable {
    private final Barberia barberia;

    public Barbero(Barberia barberia) {
        this.barberia = barberia;
    }

    @Override
    public void run() {
        try {
            barberia.atenderClientes();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
