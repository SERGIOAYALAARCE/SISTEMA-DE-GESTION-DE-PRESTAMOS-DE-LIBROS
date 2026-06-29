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

}
