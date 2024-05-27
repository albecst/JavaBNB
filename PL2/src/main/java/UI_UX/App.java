package UI_UX;

import Logica.*;
import java.awt.CardLayout;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;


class WindowEventHandler extends WindowAdapter {

    /**
     * Método que se ejecuta cuando se cierra la ventana. Guarda los datos
     * utilizando la clase JavaBNB.
     *
     * @param evt Evento de cierre de ventana.
     */
    @Override
    public void windowClosing(WindowEvent evt) {
        JavaBNB.guardarDatos();
    }
}

/**
 * Clase principal de la aplicación para crear y gestionar las pantallas y paneles necesarios.
 */
public class App {

    // Se define un CardLayout para gestionar los diferentes paneles de la aplicación.
    static CardLayout cardLayout = new CardLayout();
    // JPanel que actúa como contenedor de los paneles que forman la aplicación.
    static JPanel cards = new JPanel(cardLayout);
    // JFrame principal de la aplicación.
    static JFrame frame = new JFrame();

    // Instancias de los paneles de la aplicación.
    static Login login = new Login();
    static Register register = new Register();
    static PrivacyPolicy privacypolicy = new PrivacyPolicy();
    static HostProfile hostprofile = new HostProfile();
    static ClientProfile clientprofile = new ClientProfile();
    static AdminScreen adminscreen = new AdminScreen();
    static AdminCheckUser adminconsultaruser = new AdminCheckUser();
    static AddBuildings addbuildings = new AddBuildings();
    static AdminCheckBuildings admincheckbuildings = new AdminCheckBuildings();
    static HostCheckBuildings hostcheckbuildings = new HostCheckBuildings();
    static BuildingView buildingview = new BuildingView();
    static AdminCheckReserves adminconsultarreservas = new AdminCheckReserves();
    static GuestCheckReserves guestcheckreserves = new GuestCheckReserves();
    static MainScreen mainscreen = new MainScreen();
    static HostCheckReserves hostcheckreserves = new HostCheckReserves();

    public static Sesion sesion = null;  // Iniciamos sesión como null

    /**
     * Método principal que inicia la aplicación.
     *
     */
    public static void main(String[] args) {
        JavaBNB.inicializadorJavaBNB();
        JavaBNB.cargarDatos();

        // Establecer el tamaño mínimo de la ventana.
        frame.setMinimumSize(Toolkit.getDefaultToolkit().getScreenSize());
        frame.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
        frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        frame.setLocationRelativeTo(null);

        // Crear un JScrollPane y agregar el panel cards para permitir el desplazamiento.
        JScrollPane scrollPane = new JScrollPane(cards);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Agregar los paneles que no tienen "load" a cards.
        cards.add(login, "Pantalla login");
        cards.add(register, "Pantalla register");
        cards.add(privacypolicy, "Pantalla privacypolicy");
        cards.add(adminscreen, "Pantalla adminscreen");
        cards.add(addbuildings, "Pantalla addbuildings");
        cards.add(hostcheckreserves, "Pantalla hostcheckreserves");

        // Establecer el contenido del JFrame como el JScrollPane.
        frame.setContentPane(scrollPane);
        // Configurar el comportamiento de cierre del JFrame.
        frame.addWindowListener(new WindowEventHandler());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Hacer visible el JFrame.
        frame.setVisible(true);
        // Mostrar inicialmente la pantalla de login.
        cardLayout.show(cards, "Pantalla login");
    }

    // Métodos para cargar diferentes paneles en la aplicación.

    /**
     * Carga el perfil del anfitrión.
     */
    public static void loadHostProfile() {
        cards.add(hostprofile, "Pantalla hostprofile");
        hostprofile.actualizar();
        cardLayout.show(cards, "Pantalla hostprofile");
    }

    /**
     * Carga el perfil del cliente.
     */
    public static void loadClientProfile() {
        cards.add(clientprofile, "Pantalla clientprofile");
        clientprofile.actualizar();
        cardLayout.show(cards, "Pantalla clientprofile");
    }

    /**
     * Carga la pantalla de consulta de usuarios del administrador y actualiza sus datos.
     */
    public static void loadAdminConsultarUser() {
        cards.add(adminconsultaruser, "Pantalla adminconsultaruser");
        adminconsultaruser.actualizar();
        cardLayout.show(cards, "Pantalla adminconsultaruser");
    }

    /**
     * Carga la pantalla de consulta de reservas del administrador y actualiza sus datos.
     */
    public static void loadAdminConsultarReservas() {
        cards.add(adminconsultarreservas, "Pantalla adminconsultarreservas");
        adminconsultarreservas.actualizar();
        cardLayout.show(cards, "Pantalla adminconsultarreservas");
    }

    /**
     * Carga la pantalla de consulta de reservas de particulares y actualiza sus datos.
     */
    public static void loadGuestCheckReserves() {
        cards.add(guestcheckreserves, "Pantalla guestcheckreserves");
        guestcheckreserves.actualizar();
        cardLayout.show(cards, "Pantalla guestcheckreserves");
    }

    /**
     * Carga la pantalla de consulta de edificios del administrador, y actualiza sus datos.
     */
    public static void loadAdminCheckBuildings() {
        cards.add(admincheckbuildings, "Pantalla admincheckbuildings");
        admincheckbuildings.actualizar();
        cardLayout.show(cards, "Pantalla admincheckbuildings");
    }

    /**
     * Carga la pantalla de consulta de edificios de anfitriones y actualiza sus datos.
     */
    public static void loadHostCheckBuildings() {
        cards.add(hostcheckbuildings, "Pantalla hostcheckbuildings");
        hostcheckbuildings.actualizar();
        cardLayout.show(cards, "Pantalla hostcheckbuildings");
    }

    /**
     * Carga la pantalla de consulta de reservas de anfitriones y actualiza sus datos.
     */
    public static void loadHostCheckReserves() {
        cards.add(hostcheckreserves, "Pantalla hostcheckreserves");
        hostcheckreserves.actualizar();
        cardLayout.show(cards, "Pantalla hostcheckreserves");
    }

    /**
     * Carga la pantalla de consulta de cada uno de los inmuebles.
     * @param inmueble . El inmueble a mostrar.
     */
    public static void loadBuildingView(Inmueble inmueble) {
        cards.add(buildingview, "Pantalla buildingview");
        buildingview.setInmueble(inmueble);
        cardLayout.show(cards, "Pantalla buildingview");
    }

    /**
     * Carga la pantalla de consulta de los inmuebles
     */
    public static void loadBuildingView() {
        cards.add(buildingview, "Pantalla buildingview");
        cardLayout.show(cards, "Pantalla buildingview");
    }

    /**
     * Carga la pantalla principal de la aplicación y actualiza sus datos.
     */
    public static void loadMainScreen() {
        cards.add(mainscreen, "Pantalla mainscreen");
        mainscreen.actualizar();
        cardLayout.show(cards, "Pantalla mainscreen");
    }
}
