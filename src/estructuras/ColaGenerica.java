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
}
