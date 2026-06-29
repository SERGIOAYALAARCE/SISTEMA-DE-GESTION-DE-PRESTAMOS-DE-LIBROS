package estructuras;

public class Cola {
    private Nodo<T> frente;
    private Nodo<T> fin;
    private int tamanio;

    public void enqueue(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        if (isEmpty()) {
            frente = nuevo;
            fin = nuevo;
        } else {
            fin.siguiente = nuevo;
            fin = nuevo;
        }
        tamanio++;
    }
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

    public boolean isEmpty() {
        return tamanio == 0;
    }

    public int size() {
        return tamanio;
    }

    public void forEach(Consumer<T> accion) {
        Nodo<T> actual = frente;
        while (actual != null) {
            accion.accept(actual.dato);
            actual = actual.siguiente;
        }
    }

    public String mostrar() {
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
