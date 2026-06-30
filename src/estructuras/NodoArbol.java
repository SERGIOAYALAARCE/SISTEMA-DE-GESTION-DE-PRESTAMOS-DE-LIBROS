package estructuras;

// Nodo utilizado por el árbol binario.
public class NodoArbol<T> {

    // Dato almacenado en el nodo
    T dato;

    // Referencia al hijo izquierd
    NodoArbol<T> izquierdo;

    // Referencia al hijo derecho
    NodoArbol<T> derecho;

    // Constructor del nodo
    public NodoArbol(T dato) {
        this.dato = dato;
        this.izquierdo = null;
        this.derecho = null;
    }

}