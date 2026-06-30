package Librería;

 // Clase que representa un libro dentro del sistema de prestamos.
 
public class Libro implements Comparable<Libro> {

    // Atributos principales del libro
    private int codigo;
    private String titulo;
    private String autor;
    private String categoria;
    private int anio;
    private String estado;

     // Constructor que inicializa los datos del libro.
     
    public Libro(int codigo, String titulo, String autor, String categoria, int anio, String estado) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.autor = autor;
        this.categoria = categoria;
        this.anio = anio;
        this.estado = estado;
    }

    // Métodos para obtener la información del libro
    public int getCodigo() {
        return codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getCategoria() {
        return categoria;
    }

    public int getAnio() {
        return anio;
    }

    public String getEstado() {
        return estado;
    }

    
     //Permite cambiar el estado del libro
      // disponible o prestado .
     
    public void setEstado(String estado) {
        this.estado = estado;
    }

    
     // Compara dos libros utilizando el codgio
     // Este método es utilizado por el arbol binario de busqeda
     
    @Override
    public int compareTo(Libro otro) {
        return Integer.compare(this.codigo, otro.codigo);
    }

    
     // devuelve la información del libro en formato de texto.
     
    
    public String toString() {
        return codigo + " | " + titulo + " | " + autor + " | "
                + categoria + " | " + anio + " | " + estado;
    }
}