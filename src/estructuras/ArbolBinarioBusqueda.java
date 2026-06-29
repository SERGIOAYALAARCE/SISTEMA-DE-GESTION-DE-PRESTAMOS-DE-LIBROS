package estructuras;

public class ArbolBinarioBusqueda<T extends Comparable<T>> {
	
	private NodoArbol<T> raiz;

    public boolean estaVacio(){
        return raiz == null;
    }

}
