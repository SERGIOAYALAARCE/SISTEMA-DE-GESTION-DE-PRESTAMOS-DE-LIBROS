package modelo;

// Clase que representa una solicitud de prestamo
public class Solicitud {

    private String codigoEstudiante;
    private String nombreEstudiante;
    private int codigoLibro;
    private String fecha;

    // Constructor de la clase
    public Solicitud(String codigoEstudiante, String nombreEstudiante,
                     int codigoLibro, String fecha) {

        this.codigoEstudiante = codigoEstudiante;
        this.nombreEstudiante = nombreEstudiante;
        this.codigoLibro = codigoLibro;
        this.fecha = fecha;
    }

 // Devuelve el codigo del libro 
    public int getCodigoLibro() {
        return codigoLibro;
    }
}
