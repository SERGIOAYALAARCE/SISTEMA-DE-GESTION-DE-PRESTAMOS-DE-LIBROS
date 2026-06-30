package estructuras;

public class ColaGenerica<T> {

    
    private Nodo<T> primero; // Frente de la cola
    private Nodo<T> ultimo;  // Final de la cola
    private int contador;    // Para llevar el tamanio de forma eficiente

    public ColaGenerica() {
        this.primero = null;
        this.ultimo = null;
        this.contador = 0;
    }

    // Agrega un elemento al final de la cola (ahora usa la T genérica)
    public void enqueue(T elemento) {
        Nodo<T> nuevoNodo = new Nodo<>(elemento);
        
        if (primero == null) {
            primero = nuevoNodo;
            ultimo = nuevoNodo;
        } else {
            ultimo.siguiente = nuevoNodo;
            ultimo = nuevoNodo;
        }
        contador++;
    }

    // Retira y devuelve el elemento al frente de la cola
    public T dequeue() {
        if (primero == null) {
            return null; // Si está vacía, devuelve null
        }
        
        T valor = primero.dato;
        primero = primero.siguiente; // Avanzamos al siguiente nodo
        
        if (primero == null) {
            ultimo = null; // Si se vació la cola, el último también es null
        }
        
        contador--;
        return valor;
    }

    // Devuelve el tamaño actual convertido a String
    public String size() {
        return String.valueOf(contador);
    }

    public void mostrar() {
        if (primero == null) {
            System.out.println("La cola está vacía.");
            return;
        }
        
        Nodo<T> actual = primero;
        System.out.print("Frente -> ");
        while (actual != null) {
            System.out.print("[" + actual.dato + "] ");
            actual = actual.siguiente;
        }
        System.out.println("<- Final");
    }
}