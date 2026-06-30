package estructuras;

public class ColaGenerica {
    private NodoCola<T> frente;
    private NodoCola<T> fin;
    private int tamanio;
    
    public void enqueue(T dato){
        NodoCola<T> nuevo=new NodoCola<>(dato);

        if(isEmpty()){
            frente=fin=nuevo;
        }else{
            fin.siguiente=nuevo;
            fin=nuevo;
        }
        tamanio++;
    }
 // Elimina el primer elemento.
    public T dequeue(){
        if(isEmpty()) return null;

        T dato=frente.dato;
        frente=frente.siguiente;

        if(frente==null){
            fin=null;
        }

        tamanio--;
        return dato;
    }
 // Devuelve el primer elemento.
    public T peek(){
        return isEmpty()?null:frente.dato;
    }
}
