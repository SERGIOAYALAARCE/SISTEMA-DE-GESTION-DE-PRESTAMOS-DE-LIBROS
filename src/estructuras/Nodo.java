package estructuras;

public class Nodo {
    T dato;
    Nodo<T> siguiente;

    Nodo(T dato) {
        this.dato = dato;
        this.siguiente = null;
}
