package estructuras;

public class ArbolBinarioBusqueda<T extends Comparable<T>> {
	
	private NodoArbol<T> raiz;

    public boolean estaVacio(){
        return raiz == null;
    }
    
    public void insertar(T dato){
        raiz = insertar(raiz,dato);
    }

    private NodoArbol<T> insertar(NodoArbol<T> nodo,T dato){
        if(nodo==null){
            return new NodoArbol<>(dato);
        }

        if(dato.compareTo(nodo.dato)<0){
            nodo.izquierdo = insertar(nodo.izquierdo,dato);
        }else if(dato.compareTo(nodo.dato)>0){
            nodo.derecho = insertar(nodo.derecho,dato);
        }

        return nodo;
    }
    
    public T buscar(T dato){
        return buscar(raiz,dato);
    }

    private T buscar(NodoArbol<T> nodo,T dato){

        if(nodo==null){
            return null;
        }

        int comparacion = dato.compareTo(nodo.dato);

        if(comparacion==0){
            return nodo.dato;
        }

        if(comparacion<0){
            return buscar(nodo.izquierdo,dato);
        }

        return buscar(nodo.derecho,dato);
    }
    
    public void mostrarInOrden(){
        inOrden(raiz);
    }

    private void inOrden(NodoArbol<T> nodo){

        if(nodo!=null){
            inOrden(nodo.izquierdo);
            System.out.println(nodo.dato);
            inOrden(nodo.derecho);
        }

    }
    
    public int contar(){
        return contar(raiz);
    }

    private int contar(NodoArbol<T> nodo){

        if(nodo==null){
            return 0;
        }

        return 1 + contar(nodo.izquierdo) + contar(nodo.derecho);
    }


}
