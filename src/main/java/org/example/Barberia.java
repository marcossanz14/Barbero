package org.example;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Barberia {
    private final Lock lock = new ReentrantLock();
    private final Condition barberoDormido = lock.newCondition();
    private final Condition clientesEsperando = lock.newCondition();
    private final Queue<Cliente> colaClientes = new LinkedList<>();
    private final Random random = new Random();

    public void llegaCliente(Cliente cliente) {
        lock.lock();
        try {
            if (colaClientes.size() == 5) {
                System.out.println("Cliente: "+cliente.num+" se va, todas las sillas ocupadas");
                return;
            }
            // Despertamos al barbero si está dormido
            if (colaClientes.isEmpty()) {
                barberoDormido.signal();
            }
            // Añadimos el cliente a la cola y lo notificamos
            colaClientes.offer(cliente);
            System.out.println("Llega cliente: " + cliente.num);
            clientesEsperando.signal();
        } finally {
            lock.unlock();
        }
    }

    public void atenderClientes() throws InterruptedException {
        lock.lock();
        try {
            while (true) {
                // Si no hay clientes, el barbero se quedará dormido
                while (colaClientes.isEmpty()) {
                    System.out.println("Barbero duerme");
                    barberoDormido.await();
                }
                // Si hay clientes, el barbero le etiende (le eliminamos de la cola)
                Cliente cliente = colaClientes.poll();
                System.out.println("Atendiendo cliente: "+cliente.num);
                // Permitimos que lleguen más clientes y simulamos el corte de pelo con el sleep
                lock.unlock();
                Thread.sleep(random.nextInt(4000) + 3000);
                // Una vez finaliza de cortar el pelo, vuelve a hacerse el lock y se notifica al siguiente cliente
                lock.lock();
                System.out.println("Cliente: "+cliente.num +" atendido");
                clientesEsperando.signal();
            }
        } finally {
            lock.unlock();
        }
    }

}