package Librería;

import busqueda.Solicitudes;
import Local_Data.ManejadorArchivo;
import estructuras.BinaryTree;
import estructuras.Cola;
import estructuras.Filtro;

public class GestorBiblioteca {

    private BinaryTree<Libro> catalogo;
    private Cola<Solicitudes> colaSolicitudes;
    
    private final String RUTA_CSV = "src/Local_Data/libros.csv";

    public GestorBiblioteca() {
        this.catalogo = new BinaryTree<>();
        this.colaSolicitudes = new Cola<>();
        cargarDatosIniciales(); // Lectura del archivo CSV
    }

    // ========================================================
    //  GESTIÓN DE LIBROS
    // ========================================================

    public boolean registrarLibro(Libro nuevoLibro) {
        // Validación: Verificar si el código ya existe para evitar duplicados
        if (catalogo.buscar(nuevoLibro) != null) {
            return false; 
        }
        catalogo.insertar(nuevoLibro);
        
        // Guardar cambios automáticamente en el archivo CSV
        ManejadorArchivo.guardarLibrosEnCSV(RUTA_CSV, this.catalogo);
        return true;
    }

    public void mostrarTodosLosLibros() {
        if (catalogo.estaVacio()) {
            System.out.println("El catálogo está vacío.");
        } else {
            catalogo.mostrarInOrden();
        }
    }

    public boolean modificarLibro(int codigo, String nuevoTitulo, String nuevoAutor, String nuevaCategoria, int nuevoAnio) {
        Libro molde = new Libro(codigo, "", "", "", 0, "");
        Libro libroEncontrado = catalogo.buscar(molde);
        
        if (libroEncontrado != null) {
            // Se modifican los atributos mediante los setters de la clase Libro
            libroEncontrado.setTitulo(nuevoTitulo);
            libroEncontrado.setAutor(nuevoAutor);
            libroEncontrado.setCategoria(nuevaCategoria);
            libroEncontrado.setAnio(nuevoAnio);
            
            // Guardar cambios automáticamente en el archivo CSV
            ManejadorArchivo.guardarLibrosEnCSV(RUTA_CSV, this.catalogo);
            return true;
        }
        return false;
    }

    public boolean eliminarLibro(int codigo) {
        Libro molde = new Libro(codigo, "", "", "", 0, "");
        if (catalogo.buscar(molde) != null) {
            catalogo.eliminar(molde);
            
            // Guardar cambios automáticamente en el archivo CSV
            ManejadorArchivo.guardarLibrosEnCSV(RUTA_CSV, this.catalogo);
            return true;
        }
        return false;
    }

    // Filtrar libros disponibles usando tu interfaz Filtro
    public void mostrarLibrosDisponibles() {
        Cola<Libro> disponibles = catalogo.buscarPorFiltro(libro -> libro.getEstado().equalsIgnoreCase("Disponible"));
        disponibles.mostrar();
    }

    // Filtrar libros prestados
    public void mostrarLibrosPrestados() {
        Cola<Libro> prestados = catalogo.buscarPorFiltro(libro -> libro.getEstado().equalsIgnoreCase("Prestado"));
        prestados.mostrar();
    }

    // ========================================================
    // RF02. BÚSQUEDA DE LIBROS
    // ========================================================

    public Libro buscarLibroPorCodigo(int codigo) {
        Libro molde = new Libro(codigo, "", "", "", 0, "");
        return catalogo.buscar(molde);
    }

    public Cola<Libro> buscarLibrosPorTitulo(String titulo) {
        return catalogo.buscarPorFiltro(libro -> libro.getTitulo().toLowerCase().contains(titulo.toLowerCase()));
    }

    public Cola<Libro> buscarLibrosPorCategoria(String categoria) {
        return catalogo.buscarPorFiltro(libro -> libro.getCategoria().equalsIgnoreCase(categoria));
    }

    // ========================================================
    // SOLICITUDES Y PRÉSTAMOS
    // ========================================================

    public void registrarSolicitudPrestamiento(Solicitudes sol) {
        colaSolicitudes.enqueue(sol);
    }

    public void mostrarColaSolicitudes() {
        System.out.println(colaSolicitudes.mostrar());
    }

    public Solicitudes verSiguienteSolicitud() {
        return colaSolicitudes.peek();
    }

    // Lógica principal de atención del préstamo (Puntos 1 al 5 del RF04)
    public String atenderSiguienteSolicitud() {
        if (colaSolicitudes.isEmpty()) {
            return "No hay solicitudes pendientes en la cola.";
        }

        // Miramos la solicitud al frente de la cola sin sacarla todavía
        Solicitudes solicitudActual = colaSolicitudes.peek();
        
        // 1. Verificar que el libro exista en el árbol
        Libro molde = new Libro(solicitudActual.getCodigoLibro(), "", "", "", 0, "");
        Libro libro = catalogo.buscar(molde);

        if (libro == null) {
            colaSolicitudes.dequeue(); // Se remueve por ser inválida (libro inexistente)
            return "Operación Fallida: El libro con código " + solicitudActual.getCodigoLibro() + " no existe en el catálogo.";
        }

        // 2. Comprobar que el libro esté disponible
        if (!libro.getEstado().equalsIgnoreCase("Disponible")) {
            colaSolicitudes.dequeue();
            return "Operación Fallida: El libro '" + libro.getTitulo() + "' ya se encuentra Prestado.";
        }

        // 3. Cambiar su estado a prestado
        libro.setEstado("Prestado");

        // 4. Retirar la solicitud de la cola
        colaSolicitudes.dequeue();

        // Guardar el estado modificado del libro ("Prestado") en el archivo CSV
        ManejadorArchivo.guardarLibrosEnCSV(RUTA_CSV, this.catalogo);

        // 5. Mostrar mensaje con el resultado
        return "¡Préstamo Exitoso!\n" +
               "Estudiante: " + solicitudActual.getNombreEstudiante() + "\n" +
               "Libro Entregado: " + libro.getTitulo();
    }

    // ========================================================
    // DEVOLUCIÓN DE LIBROS
    // ========================================================

    public boolean registrarDevolucion(int codigoLibro) {
        Libro molde = new Libro(codigoLibro, "", "", "", 0, "");
        Libro libro = catalogo.buscar(molde);

        if (libro != null && libro.getEstado().equalsIgnoreCase("Prestado")) {
            libro.setEstado("Disponible");
            
            // Guardar el estado modificado del libro ("Disponible") en el archivo CSV
            ManejadorArchivo.guardarLibrosEnCSV(RUTA_CSV, this.catalogo);
            return true;
        }
        return false;
    }

    // ========================================================
    // REPORTE BÁSICO (Contadores)
    // ========================================================

    public void mostrarReporteGeneral() {
        int totalLibros = catalogo.contar();
        
        // Contamos recorriendo con filtros personalizados
        int disponibles = catalogo.buscarPorFiltro(l -> l.getEstado().equalsIgnoreCase("Disponible")).size();
        int prestados = catalogo.buscarPorFiltro(l -> l.getEstado().equalsIgnoreCase("Prestado")).size();
        int pendientes = colaSolicitudes.size();

        System.out.println("=====================================");
        System.out.println("      REPORTE GENERAL QUICKLIBRARY   ");
        System.out.println("=====================================");
        System.out.println("Cantidad total de libros      : " + totalLibros);
        System.out.println("Cantidad de libros disponibles: " + disponibles);
        System.out.println("Cantidad de libros prestados  : " + prestados);
        System.out.println("Solicitudes pendientes en cola: " + pendientes);
        System.out.println("=====================================");
    }
    
    public String colaSolicitudesMostrarAuxiliar() {
        return this.colaSolicitudes.mostrar();
    }
    
    private void cargarDatosIniciales() {
        System.out.println("Iniciando la carga del catálogo...");
        ManejadorArchivo.cargarLibrosDesdeCSV(RUTA_CSV, this.catalogo);
    }
}