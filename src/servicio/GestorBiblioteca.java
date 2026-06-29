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
