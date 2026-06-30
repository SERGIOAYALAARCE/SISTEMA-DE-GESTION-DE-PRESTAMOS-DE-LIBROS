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
        // 2. ZONA LATERAL IZQUIERDA: Menu de navegacion Grid Layout
        
        JPanel panelMenuIzquierdo = new JPanel(new GridLayout(11, 1, 5, 5));
        panelMenuIzquierdo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton btnNav1 = new JButton("1. Registrar Libro");
        JButton btnNav2 = new JButton("2. Mostrar Inventario");
        JButton btnNav3 = new JButton("3. Buscar por Codigo");
        JButton btnNav4 = new JButton("4. Buscar por Categoria");
        JButton btnNav5 = new JButton("5. Modificar Libro");
        JButton btnNav6 = new JButton("6. Eliminar Libro");
        JButton btnNav7 = new JButton("7. Registrar Solicitud");
        JButton btnNav8 = new JButton("8. Mostrar Cola Solicitudes");
        JButton btnNav9 = new JButton("9. Atender Solicitud (FIFO)");
        JButton btnNav10 = new JButton("10. Registrar Devolucion");
        JButton btnNav11 = new JButton("11. Mostrar Reporte");

        panelMenuIzquierdo.add(btnNav1);
        panelMenuIzquierdo.add(btnNav2);
        panelMenuIzquierdo.add(btnNav3);
        panelMenuIzquierdo.add(btnNav4);
        panelMenuIzquierdo.add(btnNav5);
        panelMenuIzquierdo.add(btnNav6);
        panelMenuIzquierdo.add(btnNav7);
        panelMenuIzquierdo.add(btnNav8);
        panelMenuIzquierdo.add(btnNav9);
        panelMenuIzquierdo.add(btnNav10);
        panelMenuIzquierdo.add(btnNav11);

        add(panelMenuIzquierdo, BorderLayout.WEST);
        
        
        // 3. ZONA INFERIOR: Consola para mensajes y reportes
	    areaConsola = new JTextArea();
	    areaConsola.setEditable(false);
	    areaConsola.setFont(new Font("Monospaced", Font.PLAIN, 12));
	    areaConsola.setBackground(new Color(245, 245, 245));
	
	    JScrollPane scrollConsola = new JScrollPane(areaConsola);
	    scrollConsola.setPreferredSize(new Dimension(1000, 180));
	
	    add(scrollConsola, BorderLayout.SOUTH);
	    
		// 4. ZONA CENTRAL: Contenedor de paneles dinamicos (CardLayout)
		navegadorCartas = new CardLayout();
		panelCentralCartas = new JPanel(navegadorCartas);
		panelCentralCartas.setBorder(BorderFactory.createTitledBorder("Panel de Operaciones"));
	
		// Creamos y añadimos las "cartas" de formularios al contenedor central
		crearPanelBienvenida();
		crearPanelRegistrarLibro();
		crearPanelBuscarCodigo();
		crearPanelBuscarCategoria();
		crearPanelModificarLibro();
		crearPanelEliminarLibro();
		crearPanelRegistrarSolicitud();
		crearPanelDevolucion();
	
		add(panelCentralCartas, BorderLayout.CENTER);

       