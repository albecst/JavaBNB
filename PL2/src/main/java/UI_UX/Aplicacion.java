package UI_UX;

import java.awt.CardLayout;
import java.awt.Dimension;
import javax.swing.*;


public class Aplicacion {
    static CardLayout cardLayout = new CardLayout();
    static JPanel cards = new JPanel(cardLayout); //Cards: Contenedor donde van las pantallas (el cardLayout las controla).
    static JFrame frame = new JFrame(); //Ventana normal y corriente.
    
    static Login login = new Login();
    static Register register = new Register();
    static PrivacyPolicy privacypolicy = new PrivacyPolicy();
    static MainScreen mainscreen = new MainScreen();
    static UserProfile userprofile = new UserProfile();
    
    public static void main(String[] args){
        frame.setMinimumSize(new Dimension(900, 777));
        
        cards.add(login, "Pantalla login"); //Llamo a toda la lista de pantallas disponibles
        cards.add(register, "Pantalla register");
        cards.add(privacypolicy, "Pantalla privacypolicy");
        cards.add(mainscreen, "Pantalla mainscreen");
        cards.add(userprofile, "Pantalla userprofile");
        
        frame.setContentPane(cards); //Muestra por pantalla las cards
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Si le doy a cerrar, se quita la APP
        frame.setVisible(true); //Lo muestro
        cardLayout.show(cards, "Pantalla login"); //Enseño por pantalla la pantalla de login
    }
}

//Si quiero cambiar de pantalla, Aplicacion.cardLayout.show(cards, "Reservas")
