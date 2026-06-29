package estructuras;

// Nodo utilizado por la cola generic
public class Nodo<T> {

    T dato;
    Nodo<T> siguiente;
 // Constructor del nodo 
    public Nodo(T dato) {
        this.dato = dato;
        this.siguiente = null;
    }


}