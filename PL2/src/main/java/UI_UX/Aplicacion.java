package UI_UX;

import java.awt.CardLayout;
import javax.swing.*;


public class Aplicacion {
    static CardLayout cardLayout = new CardLayout();
    static JPanel cards = new JPanel(cardLayout); //Cards: Contenedor donde van las pantallas (el cardLayout las controla).
    static JFrame frame = new JFrame(); //Ventana normal y corriente.
    static MenuPrincipal menuPrincipal = new MenuPrincipal();
    
    public static void main(String[] args){
        cards.add(menuPrincipal, "Pantalla principal"); //Llamo a toda la lista de pantallas disponibles
        frame.setContentPane(cards); //Muestra por pantalla las cards
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Si le doy a cerrar, se quita la APP
        frame.setVisible(true); //Lo muestro
        cardLayout.show(cards, "Pantalla principal"); //Enseño por pantalla la pantalla principal
    }
}

//Si quiero cambiar de pantalla, Aplicacion.cardLayout.show(cards, "Reservas")
