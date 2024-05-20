package UI_UX;

import Logica.*;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;

/**
 * Clase principal que representa la aplicación de usuario.
 */
class WindowEventHandler extends WindowAdapter {

    @Override
    public void windowClosing(WindowEvent evt) {
        JavaBNB.guardarDatos();
    }
}

public class Aplicacion {

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
    static MainScreenClient mainscreenclient = new MainScreenClient();
    static MainScreenHost mainscreenhost = new MainScreenHost();
    static HostProfile hostprofile = new HostProfile();
    static ClientProfile clientprofile = new ClientProfile();
    static AdminScreen adminscreen = new AdminScreen();
    static AdminConsultarUser adminconsultaruser = new AdminConsultarUser();
    static AddBuildings addbuildings = new AddBuildings();
    static AdminCheckBuildings admincheckbuildings = new AdminCheckBuildings();
    static AnfitrionCheckBuildings anfitrioncheckbuildings = new AnfitrionCheckBuildings();
    static BuildingView buildingview = new BuildingView();
    static AdminConsultarReservas adminconsultarreservas = new AdminConsultarReservas();

    public static Sesion sesion = null;  //iniciamos sesion como null

    /**
     * Método principal que inicia la aplicación.
     *
     * @param args Argumentos de la línea de comandos (no se utilizan en este
     * caso).
     */
    public static void main(String[] args) {
        JavaBNB.inicializadorJavaBNB();
        JavaBNB.cargarDatos();

        // Establecer el tamaño mínimo de la ventana.
        frame.setMinimumSize(new Dimension(1000, 1000));
        frame.setPreferredSize(new Dimension(1000, 1000));
        frame.setLocationRelativeTo(null);

        // Crear un JScrollPane y agregar el panel cards para permitir el desplazamiento.
        JScrollPane scrollPane = new JScrollPane(cards);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Agregar los paneles a cards.
        cards.add(login, "Pantalla login");
        cards.add(register, "Pantalla register");
        cards.add(privacypolicy, "Pantalla privacypolicy");
        loadMainScreenClient();
        loadMainScreenHost();
        loadAdminCheckBuildings();
        loadAnfitrionCheckBuildings();
        cards.add(adminscreen, "Pantalla adminscreen");
        cards.add(addbuildings, "Pantalla addbuildings");

        loadBuildingView();
        loadHostProfile();
        loadClientProfile();

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

    public static void loadHostProfile() {
        cards.add(hostprofile, "Pantalla hostprofile");
        hostprofile.actualizar();
        cardLayout.show(cards, "Pantalla hostprofile");
    }

    public static void loadClientProfile() {
        cards.add(clientprofile, "Pantalla clientprofile");
        clientprofile.actualizar();
        cardLayout.show(cards, "Pantalla clientprofile");
    }

    public static void loadAdminConsultarUser() {
        cards.add(adminconsultaruser, "Pantalla adminconsultaruser");
        adminconsultaruser.actualizar();
        cardLayout.show(cards, "Pantalla adminconsultaruser");
    }
    public static void loadAdminConsultarReservas() {
        cards.add(adminconsultarreservas, "Pantalla adminconsultarreservas");
        adminconsultarreservas.actualizar();
        cardLayout.show(cards, "Pantalla adminconsultarreservas");
    }

    public static void loadAdminCheckBuildings() {
        cards.add(admincheckbuildings, "Pantalla admincheckbuildings");
        admincheckbuildings.actualizar();
        cardLayout.show(cards, "Pantalla admincheckbuildings");
    }

    public static void loadAnfitrionCheckBuildings() {
        cards.add(anfitrioncheckbuildings, "Pantalla anfitrioncheckbuildings");
        anfitrioncheckbuildings.actualizar();   
        cardLayout.show(cards, "Pantalla anfitrioncheckbuildings");
    }

    public static void loadMainScreenClient() {
        cards.add(mainscreenclient, "Pantalla mainscreenclient");
        mainscreenclient.actualizar();
        cardLayout.show(cards, "Pantalla mainscreenclient");
    }

    public static void loadMainScreenHost() {
        cards.add(mainscreenhost, "Pantalla mainscreenhost");
        mainscreenhost.actualizar();
        cardLayout.show(cards, "Pantalla mainscreenhost");
    }

    public static void loadBuildingView(Inmueble inmueble) {
        cards.add(buildingview, "Pantalla buildingview");
        buildingview.setInmueble(inmueble);
        cardLayout.show(cards, "Pantalla buildingview");
    }

    public static void loadBuildingView() {
        cards.add(buildingview, "Pantalla buildingview");
        cardLayout.show(cards, "Pantalla buildingview");
    }
}
