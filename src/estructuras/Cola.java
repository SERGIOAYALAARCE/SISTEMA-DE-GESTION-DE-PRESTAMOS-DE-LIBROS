package estructuras;

import java.util.function.Consumer;

// Clase que implementa una cola genérica
public class Cola<T> {

    // Primer elemento de la cola
    private Nodo<T> frente;

    // Último elemento de la cola
    private Nodo<T> fin;

    // Cantidad de elementos almacenados
    private int tamanio;

    // Agrega un elemento al final de la cola.
    public void enqueue(T dato) {

        Nodo<T> nuevo = new Nodo<>(dato);

        // Si la cola está vacía, el nuevo nodo será el primero y el último.
        if (isEmpty()) {
            frente = nuevo;
            fin = nuevo;
        } else {

            fin.siguiente = nuevo;

            fin = nuevo;
        }

        tamanio++;
    }

    // Elimina el primer elemento de la cola.
    public T dequeue() {

        if (isEmpty()) {
            return null;
        }

        T dato = frente.dato;

        frente = frente.siguiente;

        tamanio--;

        if (frente == null) {
            fin = null;
        }

        return dato;
    }

    public T peek() {
        return isEmpty() ? null : frente.dato;
    }

    // Verifica si la cola está vacía.
    public boolean isEmpty() {
        return tamanio == 0;
    }

    // Devuelve la cantidad de elementos.
    public int size() {
        return tamanio;
    }

    // Recorre toda la cola ejecutando una acción.
    public void forEach(Consumer<T> accion) {

        Nodo<T> actual = frente;

        // Se recorre nodo por nodo.
        while (actual != null) {
            accion.accept(actual.dato);
            actual = actual.siguiente;
        }
    }

    public String mostrar() {

        // Si no hay datos se informa al usuario.
        if (isEmpty()) {
            return "No hay elementos para mostrar.";
        }

        StringBuilder sb = new StringBuilder();

        final int[] indice = {1};

        forEach(dato -> sb.append(indice[0]++)
                .append(". ")
                .append(dato)
                .append(System.lineSeparator()));

        return sb.toString();
    }

}