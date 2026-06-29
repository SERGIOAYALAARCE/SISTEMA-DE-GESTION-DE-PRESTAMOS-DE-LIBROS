package estructuras;

// Nodo utilizado por la cola generic
public class Nodo<T> {

    // Dato que almacena el nodo

    T dato;
    
    // siguiente nodo

    Nodo<T> siguiente;
 // Constructor del nodo 
    public Nodo(T dato) {
        this.dato = dato;
        this.siguiente = null;
    }

}