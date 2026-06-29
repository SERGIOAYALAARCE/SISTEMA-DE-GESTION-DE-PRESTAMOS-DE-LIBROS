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


