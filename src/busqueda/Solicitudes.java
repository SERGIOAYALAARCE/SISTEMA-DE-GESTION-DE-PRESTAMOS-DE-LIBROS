package busqueda;

public class Solicitudes {

    // Atributos obligatorios según el documento de requerimientos
    private String codigoEstudiante;
    private String nombreEstudiante;
    private int codigoLibro; // Se usa int porque en tu clase Libro el código es int
    private String fechaSolicitud;

    // Constructor para inicializar todos los datos de la solicitud
    public Solicitudes(String codigoEstudiante, String nombreEstudiante, int codigoLibro, String fechaSolicitud) {
        this.codigoEstudiante = codigoEstudiante;
        this.nombreEstudiante = nombreEstudiante;
        this.codigoLibro = codigoLibro;
        this.fechaSolicitud = fechaSolicitud;
    }

    // Metodos Getters para obtener la información (necesarios para la lógica de préstamos)
    public String getCodigoEstudiante() {
        return codigoEstudiante;
    }

    public String getNombreEstudiante() {
        return nombreEstudiante;
    }

    public int getCodigoLibro() {
        return codigoLibro;
    }

    public String getFechaSolicitud() {
        return fechaSolicitud;
    }

    // Formato de texto para que se muestre ordenadamente en la Cola
    @Override
    public String toString() {
        return "Estudiante: " + nombreEstudiante + " (" + codigoEstudiante + ") " +
               "| Libro Solicitado (Código): " + codigoLibro + " " +
               "| Fecha: " + fechaSolicitud;
    }
}