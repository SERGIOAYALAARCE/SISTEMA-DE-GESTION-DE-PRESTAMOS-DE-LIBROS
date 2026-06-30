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