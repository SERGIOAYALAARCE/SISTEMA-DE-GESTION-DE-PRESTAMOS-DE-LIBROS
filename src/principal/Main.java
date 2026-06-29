package principal;
import java.util.Scanner;
import servicio.GestorBiblioteca;
import vista.Menu;

public class Main {
 public static void main(String[] args){

        Scanner sc=new Scanner(System.in);
        GestorBiblioteca gestor=new GestorBiblioteca();

        int opcion;
}
        do{
            Menu.mostrar();
            opcion=sc.nextInt();

            switch(opcion){

                case 1:
                    System.out.println("Registrar libro (se completa en integración).");
                    break;
                case 2:
                    gestor.mostrarLibros();
                    break;
                case 3:
                    System.out.println("Registrar solicitud (se completa en integración).");
                    break;
                              case 4:
                    gestor.mostrarSolicitudes();
                    break;

                case 5:
                    gestor.atenderSolicitud();
                    break;

                case 6:
                    gestor.mostrarReporte();
                    break;

                case 7:
                    System.out.println("Programa finalizado.");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }

        }while(opcion!=7);

        sc.close();
    }
}
