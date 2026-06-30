package vista;

import Libreria.GestorBiblioteca;
import Libreria.Libro;
import Busqueda.Solicitudes;
import estructuras.Cola;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Menu extends JFrame {

    private GestorBiblioteca gestor;
    
    // Componentes principales de la interfaz
    private JTextArea areaConsola;
    private JPanel panelCentralCartas;
    private CardLayout navegadorCartas;

    public Menu(GestorBiblioteca gestor) {
        this.gestor = gestor;
        iniciarMusicaFondo();

        // Configuracion basica de la ventana principal
        setTitle("QuickLibrary - Sistema de Gestion");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Layout principal: Division por zonas
        setLayout(new BorderLayout(10, 10));
    //  ZONA SUPERIOR: Banner con imagen local escalada
        JLabel etiquetaBanner = new JLabel();
        etiquetaBanner.setHorizontalAlignment(SwingConstants.CENTER);
        
        URL rutaImagen = getClass().getResource("Banner.png");
        if (rutaImagen != null) {
            ImageIcon iconoOriginal = new ImageIcon(rutaImagen);
            
            Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(1000, 120, Image.SCALE_SMOOTH);
            etiquetaBanner.setIcon(new ImageIcon(imagenEscalada));
        } else {
            // Respaldar con texto plano si el archivo no se encuentra en el paquete
            etiquetaBanner.setText("QUICKLIBRARY - CATALOGO Y PRESTAMOS");
            etiquetaBanner.setFont(new Font("Arial", Font.BOLD, 22));
            etiquetaBanner.setOpaque(true);
            etiquetaBanner.setBackground(new Color(41, 128, 185));
            etiquetaBanner.setForeground(Color.WHITE);
        }
        
        // Fijamos la dimensión del contenedor del Banner para proteger el espacio de la pantalla
        etiquetaBanner.setPreferredSize(new Dimension(1000, 90));
        add(etiquetaBanner, BorderLayout.NORTH);

       