package servicio;
import estructuras.ArbolBinarioBusqueda;
import estructuras.ColaGenerica;
import modelo.Libro;
import modelo.Solicitud;

public class GestorBiblioteca {
  private ArbolBinarioBusqueda<Libro> arbol = new ArbolBinarioBusqueda<>();
    private ColaGenerica<Solicitud> cola = new ColaGenerica<>();
  
    public void registrarLibro(Libro libro){
        arbol.insertar(libro);
        System.out.println("Libro registrado correctamente.");
}
      public void mostrarLibros(){
        arbol.mostrarInOrden();
}
      public void registrarSolicitud(Solicitud solicitud){
        cola.enqueue(solicitud);
        System.out.println("Solicitud registrada.");
    }
      public void mostrarSolicitudes(){
        cola.mostrar();
    }
    public void atenderSolicitud(){
        Solicitud s=cola.dequeue();
        if(s==null){
            System.out.println("No existen solicitudes.");
        }else{
            System.out.println("Solicitud atendida: "+s);
        }
    }
