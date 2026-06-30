package Local_Data;

import Librería.Libro;
import estructuras.BinaryTree;
import estructuras.Cola;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ManejadorArchivo {

    // Método estático para que pueda ser llamado directamente sin instanciar la clase
    public static void cargarLibrosDesdeCSV(String rutaArchivo, BinaryTree<Libro> catalogo) {
        String linea;
        String separador = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            // Leer la primera línea (cabecera: codigo,titulo,autor...) y descartarla
            String cabecera = br.readLine();
            
            if (cabecera == null) {
                System.out.println("El archivo CSV está vacío.");
                return;
            }

            int cont = 0;
            while ((linea = br.readLine()) != null) {
               
                String[] datos = linea.split(separador);

              
                if (datos.length == 6) {
                    try {
                        int codigo = Integer.parseInt(datos[0].trim());
                        String titulo = datos[1].trim();
                        String autor = datos[2].trim();
                        String categoria = datos[3].trim();
                        int anio = Integer.parseInt(datos[4].trim());
                        String estado = datos[5].trim();

                        Libro libro = new Libro(codigo, titulo, autor, categoria, anio, estado);
                        catalogo.insertar(libro);
                        cont++;
                    } catch (NumberFormatException e) {
                        System.out.println("Error de formato en número de línea omitida: " + linea);
                    }
                }
            }
            System.out.println(" [OK] Se cargaron con éxito " + cont + " libros desde el archivo CSV.");

        } catch (IOException e) {
            System.out.println(" No se pudo leer el archivo CSV (" + rutaArchivo + "). El sistema iniciará vacío.");
        }
    }
    
    public static void guardarLibrosEnCSV(String rutaArchivo, BinaryTree<Libro> catalogo) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo))) {
            // 1. Escribir la cabecera obligatoria del CSV
            bw.write("codigo,titulo,autor,categoria,anio,estado");
            bw.newLine();

            // 2. Obtener todos los libros en orden usando tu propio árbol.
            // Para no romper el encapsulamiento, podemos usar el filtro genérico 
            // con una condición que siempre sea verdadera (así nos trae todos los libros).
            Cola<Libro> todosLosLibros = catalogo.buscarPorFiltro(libro -> true);
            
            // 3. Recorrer la cola de libros y escribir línea por línea en el archivo
            int tamaño = todosLosLibros.size();
            for (int i = 0; i < tamaño; i++) {
                // Sacamos el libro de la cola (asumiendo que dequeue devuelve el elemento)
                // Si tu dequeue no retorna el objeto, asegúrate de tener un método para obtenerlo.
                Libro libro = todosLosLibros.dequeue(); 
                
                if (libro != null) {
                    String linea = libro.getCodigo() + ","
                            + libro.getTitulo() + ","
                            + libro.getAutor() + ","
                            + libro.getCategoria() + ","
                            + libro.getAnio() + ","
                            + libro.getEstado();
                    bw.write(linea);
                    bw.newLine();
                }
            }
            System.out.println("Confirmacion: Base de datos guardada con éxito en " + rutaArchivo);

        } catch (IOException e) {
            System.out.println("Error al intentar escribir en el archivo CSV: " + e.getMessage());
        }
    }
}