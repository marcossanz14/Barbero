package org.example;

class Cliente implements Runnable {
    private final Barberia barberia;
    int num;

    public Cliente(Barberia barberia, int num) {
        this.barberia = barberia;
        this.num = num;
    }

    @Override
    public void run() {
        barberia.llegaCliente(this);
    }
}
