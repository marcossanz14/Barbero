# Barbero dormilón

### Preguntas

#### 1. Decisión del Cliente: Considera cómo un cliente decide si esperará o se irá cuando llegue a la barbería. ¿Qué factores influyen en esta decisión y cómo se puede implementar esta lógica de manera que sea coherente con el comportamiento esperado?

El cliente decide si esperará o se irá dependiendo el numero de clientes que hayan esperando en ese momento. Si todas las sillas están llenas se irá, si queda alguna silla libre entrará. Si no hay nadie despertará al barbero y le atenderán. Si hay algun otro cleinte simplemente esperará en la cola sentado. 

#### 2. Manejo de la Cola de Espera: Reflexiona sobre la estructura de datos que podría representar mejor la cola de espera. ¿Cómo garantizarías que los clientes sean atendidos en el orden correcto, especialmente cuando el barbero se desocupa y está listo para atender al siguiente cliente?

Para esto usamos un Queue de clientes con LinkedList. Cuando llega un cliente nuevo y le queremos añadir a la cola, lo que debemos hacer es hacer un .offer(cliente) a nuestra cola, lo que hace que se añada al final de la cola. Luego cuando un cliente se va porque ha sido atendido usamos el .poll() que sirve para eliminar el primer elemento de la cola, es decir, el cliente que estaba siendo atendido y se adelantán una posición todos los demás clientes, con lo cual el orden sigue siendo el mismo pero una posición mas adelantada.

#### 3. Concurrencia y Sincronización: Piensa en cómo gestionarías la concurrencia en este escenario. ¿Cómo asegurarías que el barbero no sea despertado por un cliente cuando ya está atendiendo a otro? ¿Cómo manejarías las situaciones en las que múltiples clientes llegan al mismo tiempo cuando solo queda una silla de espera disponible?

Para eso podemos usar un booleando de que si el barbero está ocupado. Mientras está cortando el pelo se pone como true, entonces a la hora de despertar el barbero comprobamos si la lista esta vacía y además el barbero no esta ocupado entonces si que podríamos despertarlo. Se podría usar un semáforo, yo en este caso no sabría como implementarlo y como lo tengo se coge el que primero llegue.

#### 4. Justicia y Eficiencia: Considera el equilibrio entre la justicia (asegurando que todos los clientes tengan una oportunidad justa de ser atendidos) y la eficiencia (minimizando el tiempo de espera para los clientes y el tiempo inactivo para el barbero). ¿Cómo impactan tus decisiones de diseño en este equilibrio?

Si ponemos mas tiempo de espera al corte de pelo que a la llegada de nuevos clientes el resultado será que llegarán clientes que se tendrán que ir porque estará ocupada la cola. En cambio si hay mas tiempo de espera de la llegada de clientes que del corte de pelo, prácticamente siempre habrá espacio para todos porque se cortan el pelo más rápido. Entonces dependiendo como queremos que funcione la barbería podemos jugar con los sleep. Yo tengo que el corte de pelo sea entre 4-7s y la llegada de 2-3s, con lo cual algún cliente se quedará fuera normalmente. (Hago que intenten entrar 10 y haya una cola de 5).

Link al repo: https://github.com/marcossanz14/Barbero

### Esquema eventos y lógico

![1](https://github.com/marcossanz14/Barbero/assets/65198517/c1350200-3fb1-432e-8fe8-ebd3a94e0ca6)

![2](https://github.com/marcossanz14/Barbero/assets/65198517/b21a5942-1f67-4155-b947-f0f915b1de49)
