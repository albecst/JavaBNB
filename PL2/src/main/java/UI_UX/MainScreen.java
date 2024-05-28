/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package UI_UX;

import Logica.Anfitrion;
import Logica.Inmueble;
import Logica.JavaBNB;
import Logica.Sesion;
import static UI_UX.ClientProfile.fotografia;
import java.awt.Desktop;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

//La clase MainScreen constituye la interfaz principal de la aplicación.
// Contiene los botones para "redirigir" al usuario al resto de ventanas, y
// utiliza contenido dinámico para mostrar, según los parámetros de búsqueda
// introducidos por el usuario, los distintos inmuebles mostrados en forma de
// widget.
//
public class MainScreen extends javax.swing.JPanel {

    public ArrayList<BuildingIcon> buildingsicon; //ArrayList para contener los distintos widgets
    private ArrayList<Inmueble> buildings; //ArrayList que se actualizará para contener los inmuebles según los parámetros de búsqueda
    private ArrayList<Inmueble> allBuildings; //ArrayList con todos los inmuebles
    int estado;
    String fotografia;

    public MainScreen() {
        initComponents();
        buildingsLabel.setVisible(false);
    }

    public void actualizar() {
        buildings = JavaBNB.getInmuebles();
        allBuildings = JavaBNB.getInmuebles();

        buildingsicon = new ArrayList<>();

        fotografia = Sesion.user.getFotoperfil();
        hostProfile.setIcon(resizeIMG(fotografia));

        if (Sesion.esAnfitrion) {
            addBuildingsButton.setVisible(true);
            myBuildingsButton.setVisible(true);
            misReservasButton.setVisible(false);
        } else {
            addBuildingsButton.setVisible(false);
            myBuildingsButton.setVisible(false);
            misReservasButton.setVisible(true);
        }
    }

    // Se utiliza contenido dinámico para mostrar todos los inmuebles de la
    // aplicación. Una vez "limpiado" de búsquedas anteriores el panel que
    // contendrá los widgets, se muestra el contenido de la lista "allBuildings"
    // mediante paneles del tipo BuildingIcon con cada uno de estos inmuebles.
    public void insertAllBuildings() {
        buildingsLabel.setVisible(true);
        deleteBuildings(); // Borra cualquier widget de edificio existente antes de insertar nuevos si es posible
        if (allBuildings == null | allBuildings.isEmpty()) {
            System.err.println("La lista de edificios está vacía. No se pueden insertar inmuebles.");
            return;
        }
        System.out.println("La lista de edificios no está vacía:");

        int fila = 0;
        int x = 50; //valor de prueba
        for (Inmueble inmueble : allBuildings) {
            if (x >= 800) {
                fila += 400;//el tamaño de la ventana  del widget= [295, 400] 
                x = 50;
            }
            BuildingIcon iconoinm = new BuildingIcon();
            iconoinm.init(inmueble);
            //AbsoluteConstraints constr = new org.netbeans.lib.awtextra.AbsoluteConstraints(295*x, fila, -1, -1);  //-1 en altura y anchura para que nos de la predeterminada del widget añadido
            buildingsicon.add(iconoinm);
            buildingsContainer.add(iconoinm, new org.netbeans.lib.awtextra.AbsoluteConstraints(x, fila, -1, -1));  //método addLayoutComponent(java.awt.Component comp, java.lang.Object constr);  Adds the specified component to the layout, using the specified constraint object.
            //System.out.println(inmueble);
            x += 350; //valor de prueba
        }
        buildingsContainer.revalidate(); // Actualiza el contenedor para mostrar los cambios
        buildingsContainer.repaint();   // Repinta el contenedor para asegurar que los cambios sean visibles
        buildings = allBuildings;
    }

    // Se utiliza contenido dinámico para mostrar los inmuebles de la lista
    // "buildings". Una vez "limpiado" de búsquedas anteriores el panel que
    // contendrá los widgets, se muestra el contenido de "buildings" mediante
    // paneles del tipo BuildingIcon con cada uno de estos inmuebles.
    public void insertBuildings() {
        buildingsLabel.setVisible(true);
        if (buildings == null | buildings.isEmpty()) {
            System.err.println("La lista de edificios está vacía. No se pueden insertar inmuebles.");
            return;
        }
        System.out.println("La lista de edificios no está vacía:");
        deleteBuildings(); // Borra cualquier widget de edificio existente antes de insertar nuevos

        int fila = 0;
        int x = 50; //valor de prueba
        for (Inmueble inmueble : buildings) {
            if (x >= 800) {
                fila += 400; //tamaño ventana widget= [295, 400]
                x = 50;
            }
            BuildingIcon iconoinm = new BuildingIcon();
            iconoinm.init(inmueble);
            //AbsoluteConstraints constr = new org.netbeans.lib.awtextra.AbsoluteConstraints(295*x, fila, -1, -1);  //-1 en altura y anchura para que nos de la predeterminada del widget añadido
            buildingsicon.add(iconoinm);
            buildingsContainer.add(iconoinm, new org.netbeans.lib.awtextra.AbsoluteConstraints(x, fila, -1, -1));  //método addLayoutComponent(java.awt.Component comp, java.lang.Object constr);  Adds the specified component to the layout, using the specified constraint object.

            x += 350; //valor de prueba
        }
        buildingsContainer.revalidate(); // Actualiza el contenedor para mostrar los cambios
        buildingsContainer.repaint();   // Repinta el contenedor para asegurar que los cambios sean visibles
    }

    public void deleteBuildings() {
        for (BuildingIcon ii : buildingsicon) {
            this.buildingsContainer.remove(ii);
        }
        buildingsicon.clear(); // Limpia la lista de widgets de edificios después de eliminarlos
        buildingsContainer.revalidate(); // Actualiza el contenedor para mostrar los cambios
        buildingsContainer.repaint();   // Repinta el contenedor para asegurar que los cambios sean visibles
    }

    // Conversión de un valor "Date" a "LocalDate". 
    public LocalDate convertToLocalDate(Object dateObject) {
        if (dateObject instanceof Date) {
            //Se hace un cast del objeto a Date. 
            //Entonces, se transforma en "Instant" (punto en la línea de tiempo, en milisegundos).
            //Después se convierte en ZonedDateTime, usando la zona horaria por defecto del sistema donde se ejecute el código.
            //Por último, esto se convierte en LocalDate.
            return ((Date) dateObject).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        } else {
            return null;
        }
    }

    /**
     * Redimensiona la imagen, para que se puede ver en ese cuadrado.
     *
     * @param img
     * @return
     */
    public static ImageIcon resizeIMG(String img) {
        try {
            File imagePath = new File(img);
            BufferedImage originalImage = ImageIO.read(imagePath);
            int width = 56;
            int height = 57;
            Image resizedImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            ImageIcon imageIcon = new ImageIcon(resizedImage);
            return imageIcon;
        } catch (IOException ioe) {
            System.out.println("Error de IO: " + ioe.getMessage());
            return null;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        barraarriba = new javax.swing.JPanel();
        titleLabel = new javax.swing.JLabel();
        logo = new javax.swing.JButton();
        hostProfile = new javax.swing.JButton();
        addBuildingsButton = new javax.swing.JButton();
        myBuildingsButton = new javax.swing.JButton();
        misReservasButton = new javax.swing.JButton();
        filtratubusqueda = new javax.swing.JScrollPane();
        textteesperan1 = new javax.swing.JTextArea();
        sientetecomoencasa = new javax.swing.JLabel();
        buscaalojamiento2 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        textteesperan6 = new javax.swing.JTextArea();
        jScrollPane7 = new javax.swing.JScrollPane();
        textbuscaaloj2 = new javax.swing.JTextArea();
        destinopanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        cityTextField = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        endDateTextField = new javax.swing.JFormattedTextField();
        startDateTextField = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        searchButton = new javax.swing.JButton();
        showAllBuildingsButton = new javax.swing.JButton();
        fotocasa = new javax.swing.JButton();
        filtrospara = new javax.swing.JLabel();
        mastiempopara = new javax.swing.JLabel();
        disfrutade = new javax.swing.JScrollPane();
        textteesperan2 = new javax.swing.JTextArea();
        regalateun = new javax.swing.JScrollPane();
        textteesperan3 = new javax.swing.JTextArea();
        maspormenos = new javax.swing.JLabel();
        masespacio = new javax.swing.JScrollPane();
        textteesperan4 = new javax.swing.JTextArea();
        barraabajo = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        buildingsContainer = new javax.swing.JPanel();
        filterComboBox = new javax.swing.JComboBox<>();
        applyButton = new javax.swing.JButton();
        buildingsLabel = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(1817, 2551));
        setPreferredSize(new java.awt.Dimension(1817, 2551));
        setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(255, 250, 248));
        jPanel1.setMaximumSize(new java.awt.Dimension(1817, 2551));
        jPanel1.setMinimumSize(new java.awt.Dimension(1817, 2551));

        barraarriba.setBackground(new java.awt.Color(255, 250, 248));
        barraarriba.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        titleLabel.setFont(new java.awt.Font("Serif", 3, 24)); // NOI18N
        titleLabel.setForeground(new java.awt.Color(255, 90, 95));
        titleLabel.setText("JavaBNB");

        logo.setBackground(new java.awt.Color(255, 153, 153));
        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/airbnb logo - 100x100.png"))); // NOI18N
        logo.setBorderPainted(false);
        logo.setContentAreaFilled(false);
        logo.setDefaultCapable(false);
        logo.setFocusPainted(false);
        logo.setFocusable(false);
        logo.setRequestFocusEnabled(false);
        logo.setRolloverEnabled(false);
        logo.setVerifyInputWhenFocusTarget(false);
        logo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoActionPerformed(evt);
            }
        });

        hostProfile.setBackground(new java.awt.Color(153, 153, 153));
        hostProfile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/user (1).jpg"))); // NOI18N
        hostProfile.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        hostProfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hostProfileActionPerformed(evt);
            }
        });

        addBuildingsButton.setBackground(new java.awt.Color(255, 90, 95));
        addBuildingsButton.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        addBuildingsButton.setText("Añadir inmueble");
        addBuildingsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBuildingsButtonActionPerformed(evt);
            }
        });

        myBuildingsButton.setBackground(new java.awt.Color(255, 90, 95));
        myBuildingsButton.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        myBuildingsButton.setText("Mis inmuebles");
        myBuildingsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myBuildingsButtonActionPerformed(evt);
            }
        });

        misReservasButton.setBackground(new java.awt.Color(255, 90, 95));
        misReservasButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        misReservasButton.setText("Mis reservas");
        misReservasButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                misReservasButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout barraarribaLayout = new javax.swing.GroupLayout(barraarriba);
        barraarriba.setLayout(barraarribaLayout);
        barraarribaLayout.setHorizontalGroup(
            barraarribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(barraarribaLayout.createSequentialGroup()
                .addGap(116, 116, 116)
                .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(titleLabel)
                .addGap(700, 700, 700)
                .addComponent(misReservasButton, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(addBuildingsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(myBuildingsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71)
                .addComponent(hostProfile, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        barraarribaLayout.setVerticalGroup(
            barraarribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(barraarribaLayout.createSequentialGroup()
                .addGroup(barraarribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(barraarribaLayout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(misReservasButton, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(barraarribaLayout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(addBuildingsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(barraarribaLayout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(myBuildingsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(barraarribaLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(hostProfile))
                    .addGroup(barraarribaLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(barraarribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, barraarribaLayout.createSequentialGroup()
                                .addComponent(titleLabel)
                                .addGap(21, 21, 21)))))
                .addGap(5, 5, 5))
        );

        filtratubusqueda.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        filtratubusqueda.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        textteesperan1.setEditable(false);
        textteesperan1.setBackground(new java.awt.Color(255, 250, 248));
        textteesperan1.setColumns(20);
        textteesperan1.setForeground(new java.awt.Color(102, 102, 102));
        textteesperan1.setRows(5);
        textteesperan1.setText("Filtra tu búsqueda según el rango de precios,el \ntipo de alojamiento que buscas y otros servicios \nclave para encontrar el alojamiento que mejor \nse adapte a tus necesidades.");
        textteesperan1.setAutoscrolls(false);
        textteesperan1.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        textteesperan1.setRequestFocusEnabled(false);
        textteesperan1.setVerifyInputWhenFocusTarget(false);
        filtratubusqueda.setViewportView(textteesperan1);

        sientetecomoencasa.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        sientetecomoencasa.setText("Siéntete como en casa");

        buscaalojamiento2.setBackground(new java.awt.Color(255, 250, 248));
        buscaalojamiento2.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        jScrollPane6.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane6.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        textteesperan6.setEditable(false);
        textteesperan6.setBackground(new java.awt.Color(255, 250, 248));
        textteesperan6.setColumns(20);
        textteesperan6.setForeground(new java.awt.Color(102, 102, 102));
        textteesperan6.setRows(5);
        textteesperan6.setText("Te esperan alojamientos enteros y \nhabitaciones ideales para cualquier tipo de viaje.");
        textteesperan6.setAutoscrolls(false);
        textteesperan6.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jScrollPane6.setViewportView(textteesperan6);

        jScrollPane7.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane7.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        textbuscaaloj2.setEditable(false);
        textbuscaaloj2.setBackground(new java.awt.Color(255, 250, 248));
        textbuscaaloj2.setColumns(20);
        textbuscaaloj2.setFont(new java.awt.Font("Serif", 0, 24)); // NOI18N
        textbuscaaloj2.setRows(5);
        textbuscaaloj2.setText("Busca alojamiento en \nJavaBNB");
        textbuscaaloj2.setAutoscrolls(false);
        textbuscaaloj2.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jScrollPane7.setViewportView(textbuscaaloj2);

        destinopanel2.setBackground(new java.awt.Color(255, 250, 248));
        destinopanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel7.setText("Ciudad de destino:");

        cityTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cityTextFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout destinopanel2Layout = new javax.swing.GroupLayout(destinopanel2);
        destinopanel2.setLayout(destinopanel2Layout);
        destinopanel2Layout.setHorizontalGroup(
            destinopanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(destinopanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(destinopanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(cityTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        destinopanel2Layout.setVerticalGroup(
            destinopanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(destinopanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cityTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 250, 248));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jSeparator1.setForeground(new java.awt.Color(255, 153, 153));
        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel2.setText("Fecha de llegada");

        jLabel3.setText("Fecha de salida");

        endDateTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd/MM/yyyy"))));
        endDateTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                endDateTextFieldActionPerformed(evt);
            }
        });

        startDateTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd/MM/yyyy"))));
        startDateTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startDateTextFieldActionPerformed(evt);
            }
        });
        startDateTextField.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                startDateTextFieldPropertyChange(evt);
            }
        });

        jLabel6.setForeground(new java.awt.Color(153, 153, 153));
        jLabel6.setText("dd/MM/yyyy");

        jLabel8.setForeground(new java.awt.Color(153, 153, 153));
        jLabel8.setText("dd/MM/yyyy");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jLabel2)
                        .addGap(38, 38, 38))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(49, 49, 49))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(startDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)))))
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(endDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel8))))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(endDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(startDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
            .addComponent(jSeparator1)
        );

        searchButton.setBackground(new java.awt.Color(255, 153, 153));
        searchButton.setForeground(new java.awt.Color(255, 255, 255));
        searchButton.setText("Buscar");
        searchButton.setPreferredSize(new java.awt.Dimension(129, 36));
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        showAllBuildingsButton.setBackground(new java.awt.Color(255, 153, 153));
        showAllBuildingsButton.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        showAllBuildingsButton.setForeground(new java.awt.Color(255, 255, 255));
        showAllBuildingsButton.setText("Mostrar todos los inmuebles");
        showAllBuildingsButton.setMaximumSize(new java.awt.Dimension(143, 58));
        showAllBuildingsButton.setMinimumSize(new java.awt.Dimension(143, 58));
        showAllBuildingsButton.setPreferredSize(new java.awt.Dimension(143, 58));
        showAllBuildingsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showAllBuildingsButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout buscaalojamiento2Layout = new javax.swing.GroupLayout(buscaalojamiento2);
        buscaalojamiento2.setLayout(buscaalojamiento2Layout);
        buscaalojamiento2Layout.setHorizontalGroup(
            buscaalojamiento2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, buscaalojamiento2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(buscaalojamiento2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane6)
                    .addComponent(destinopanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(1204, 1204, 1204))
            .addGroup(buscaalojamiento2Layout.createSequentialGroup()
                .addGroup(buscaalojamiento2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(buscaalojamiento2Layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(buscaalojamiento2Layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(showAllBuildingsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        buscaalojamiento2Layout.setVerticalGroup(
            buscaalojamiento2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buscaalojamiento2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(destinopanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(showAllBuildingsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        fotocasa.setBackground(new java.awt.Color(255, 250, 248));
        fotocasa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/casa1.jpg"))); // NOI18N
        fotocasa.setBorderPainted(false);
        fotocasa.setDefaultCapable(false);
        fotocasa.setFocusPainted(false);
        fotocasa.setFocusable(false);
        fotocasa.setRequestFocusEnabled(false);
        fotocasa.setRolloverEnabled(false);
        fotocasa.setVerifyInputWhenFocusTarget(false);

        filtrospara.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        filtrospara.setText("Filtros para una estancia a tu medida");

        mastiempopara.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        mastiempopara.setText("Más tiempo para disfrutar");

        disfrutade.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        disfrutade.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        textteesperan2.setEditable(false);
        textteesperan2.setBackground(new java.awt.Color(255, 250, 248));
        textteesperan2.setColumns(20);
        textteesperan2.setForeground(new java.awt.Color(102, 102, 102));
        textteesperan2.setRows(5);
        textteesperan2.setText("Disfruta de cocinas totalmente \nequipadas, piscinas, grandes \njardines, terrazas y ¡mucho más!");
        textteesperan2.setAutoscrolls(false);
        textteesperan2.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        disfrutade.setViewportView(textteesperan2);

        regalateun.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        regalateun.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        textteesperan3.setEditable(false);
        textteesperan3.setBackground(new java.awt.Color(255, 250, 248));
        textteesperan3.setColumns(20);
        textteesperan3.setForeground(new java.awt.Color(102, 102, 102));
        textteesperan3.setRows(5);
        textteesperan3.setText("Regálate un viaje sin complicaciones: \ndesde que reservas hasta que \nllegas a tu destino.");
        textteesperan3.setAutoscrolls(false);
        textteesperan3.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        regalateun.setViewportView(textteesperan3);

        maspormenos.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        maspormenos.setText("Más por menos");

        masespacio.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        masespacio.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        textteesperan4.setEditable(false);
        textteesperan4.setBackground(new java.awt.Color(255, 250, 248));
        textteesperan4.setColumns(20);
        textteesperan4.setForeground(new java.awt.Color(102, 102, 102));
        textteesperan4.setRows(5);
        textteesperan4.setText("Más espacio, más privacidad, \nmás servicios y ¡una mejor \nrelación calidad-precio!\n");
        textteesperan4.setAutoscrolls(false);
        textteesperan4.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        masespacio.setViewportView(textteesperan4);

        barraabajo.setBackground(new java.awt.Color(255, 250, 248));
        barraabajo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel4.setText("© 2024 JavaBNB, Inc.");

        jLabel5.setText("Privacidad");
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(255, 250, 248));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/2227.jpg"))); // NOI18N
        jButton1.setBorder(null);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout barraabajoLayout = new javax.swing.GroupLayout(barraabajo);
        barraabajo.setLayout(barraabajoLayout);
        barraabajoLayout.setHorizontalGroup(
            barraabajoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(barraabajoLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel4)
                .addGap(40, 40, 40)
                .addComponent(jLabel5)
                .addGap(28, 28, 28)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        barraabajoLayout.setVerticalGroup(
            barraabajoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(barraabajoLayout.createSequentialGroup()
                .addGroup(barraabajoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(barraabajoLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(barraabajoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)))
                    .addGroup(barraabajoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(55, Short.MAX_VALUE))
        );

        buildingsContainer.setBackground(new java.awt.Color(255, 250, 248));
        buildingsContainer.setMinimumSize(new java.awt.Dimension(1242, 416));
        buildingsContainer.setPreferredSize(new java.awt.Dimension(1242, 426));
        buildingsContainer.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        filterComboBox.setBackground(new java.awt.Color(255, 90, 95));
        filterComboBox.setForeground(new java.awt.Color(255, 255, 255));
        filterComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Filtrar por:", "Precio mayor a menor", "Precio menor a mayor", "Casas", "Apartamentos", "Calificación de mayor a menor", "Calificación de menor a mayor" }));
        filterComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterComboBoxActionPerformed(evt);
            }
        });
        buildingsContainer.add(filterComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(1260, 10, 180, 40));

        applyButton.setBackground(new java.awt.Color(215, 90, 95));
        applyButton.setForeground(new java.awt.Color(255, 255, 255));
        applyButton.setText("Aplicar");
        applyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                applyButtonActionPerformed(evt);
            }
        });
        buildingsContainer.add(applyButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(1300, 60, 100, 40));

        buildingsLabel.setFont(new java.awt.Font("Serif", 3, 48)); // NOI18N
        buildingsLabel.setForeground(new java.awt.Color(255, 90, 95));
        buildingsLabel.setText("Lista de inmuebles");
        buildingsLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(barraarriba, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(filtratubusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(filtrospara))
                            .addComponent(buscaalojamiento2, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(125, 125, 125)
                                .addComponent(fotocasa, javax.swing.GroupLayout.PREFERRED_SIZE, 812, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(buildingsLabel)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(disfrutade, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(sientetecomoencasa, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(161, 161, 161)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(regalateun, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(mastiempopara, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(125, 125, 125)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(masespacio, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(maspormenos, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(buildingsContainer, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(barraabajo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(barraarriba, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buscaalojamiento2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fotocasa, javax.swing.GroupLayout.PREFERRED_SIZE, 497, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(81, 81, 81)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(filtrospara)
                    .addComponent(sientetecomoencasa)
                    .addComponent(mastiempopara)
                    .addComponent(maspormenos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(filtratubusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(disfrutade, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(regalateun, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(masespacio, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(buildingsLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buildingsContainer, javax.swing.GroupLayout.PREFERRED_SIZE, 1515, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(barraabajo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void hostProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hostProfileActionPerformed
        deleteBuildings();

        if (Sesion.esAnfitrion) {
            App.loadHostProfile();
            actualizar();
        } else {
            App.loadClientProfile();
            actualizar();

        }


    }//GEN-LAST:event_hostProfileActionPerformed

    private void logoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_logoActionPerformed

    private void addBuildingsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBuildingsButtonActionPerformed
        deleteBuildings();
        App.cardLayout.show(App.cards, "Pantalla addbuildings");

    }//GEN-LAST:event_addBuildingsButtonActionPerformed

    private void myBuildingsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myBuildingsButtonActionPerformed
        deleteBuildings();
        App.loadHostCheckBuildings();
    }//GEN-LAST:event_myBuildingsButtonActionPerformed

    private void cityTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cityTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cityTextFieldActionPerformed

    private void endDateTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_endDateTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_endDateTextFieldActionPerformed

    // Al pulsar el botón de buscar, se actualizará la lista "buildings" con los
    // inmuebles que cumplan los requisitos establecidos y se llamará al método para mostrarlos. 
    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        deleteBuildings();
        filterComboBox.setSelectedItem("Filtrar por:");
        this.estado = 1;
        // Obtener los valores de los campos de texto y eliminar espacios en blanco de inicio y final
        String ciudad = cityTextField.getText().trim();
        // Variables para almacenar las fechas convertidas a LocalDate
        LocalDate fechaEntrada = null;
        LocalDate fechaSalida = null;

        // Verificar si los campos de fecha no están vacíos antes de intentar convertir
        if (!startDateTextField.getText().isEmpty() && !endDateTextField.getText().isEmpty()) {
            try {
                fechaEntrada = convertToLocalDate(startDateTextField.getValue());
                fechaSalida = convertToLocalDate(endDateTextField.getValue());
            } catch (DateTimeParseException e) {
                JOptionPane.showMessageDialog(this, "Por favor, introduce la fecha de inicio en el formato DD/MM/YYYY", "Formato de fecha inválido", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        // Filtrar los inmuebles en función de los criterios introducidos
        ArrayList<Inmueble> inmueblesFiltrados = JavaBNB.buscarInmuebles(ciudad, fechaEntrada, fechaSalida);

        // Actualizar la lista de buildings con los inmuebles filtrados
        buildings = inmueblesFiltrados;

        // Llama al método para insertar los inmuebles en el panel
        insertBuildings();


    }//GEN-LAST:event_searchButtonActionPerformed

    private void showAllBuildingsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showAllBuildingsButtonActionPerformed
        filterComboBox.setSelectedItem("Filtrar por:");

        this.estado = 0;
        insertAllBuildings();
    }//GEN-LAST:event_showAllBuildingsButtonActionPerformed

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        deleteBuildings();
        App.cardLayout.show(App.cards, "Pantalla privacypolicy");
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        deleteBuildings();
        try {
            //Abrir un enlace externo en el navegador al pulsar un botón 
            Desktop.getDesktop().browse(new URI("https://www.instagram.com/javabnb/"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void filterComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filterComboBoxActionPerformed

    }//GEN-LAST:event_filterComboBoxActionPerformed
    /**
     * Con este método se aplicará el filtro introducido a la lista de inmuebles
     * que corresponda.
     */
    private void applyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_applyButtonActionPerformed
        deleteBuildings();
        String selectedOption = (String) filterComboBox.getSelectedItem();

        ArrayList<Inmueble> inmueblesFiltrados;

        if (estado == 0) {
            inmueblesFiltrados = new ArrayList<>(allBuildings); // Usar todos los inmuebles disponibles
        } else {
            inmueblesFiltrados = new ArrayList<>(buildings); // Usar los inmuebles filtrados por la búsqueda
        }

        // Llama al método de filtrado correspondiente según la opción seleccionada
        switch (selectedOption) {
            case "Precio mayor a menor":
                JavaBNB.ordenarPorPrecioDescCF(inmueblesFiltrados);
                break;
            case "Precio menor a mayor":
                JavaBNB.ordenarPorPrecioAscCF(inmueblesFiltrados);
                break;
            case "Casas":
                // Filtra los inmuebles disponibles por tipo "Casa"
                inmueblesFiltrados = JavaBNB.filtrarCasas(inmueblesFiltrados);
                break;
            case "Apartamentos":
                // Filtra los inmuebles disponibles por tipo "Apartamento"
                inmueblesFiltrados = JavaBNB.filtrarApartamentos(inmueblesFiltrados);
                break;
            case "Calificación de mayor a menor":
                JavaBNB.ordenarPorCalificacionDescCF(inmueblesFiltrados);
                break;
            case "Calificación de menor a mayor":
                JavaBNB.ordenarPorCalificacionAscCF(inmueblesFiltrados);
                break;
        }

        // Actualiza la lista de buildings con los inmuebles filtrados y ordenados
        buildings = inmueblesFiltrados;

        // Llama al método para insertar los inmuebles en el panel
        insertBuildings();


    }//GEN-LAST:event_applyButtonActionPerformed

    private void startDateTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startDateTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_startDateTextFieldActionPerformed

    private void startDateTextFieldPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_startDateTextFieldPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_startDateTextFieldPropertyChange

    private void misReservasButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_misReservasButtonActionPerformed
        deleteBuildings();
        App.loadGuestCheckReserves();
    }//GEN-LAST:event_misReservasButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBuildingsButton;
    private javax.swing.JButton applyButton;
    private javax.swing.JPanel barraabajo;
    private javax.swing.JPanel barraarriba;
    private javax.swing.JPanel buildingsContainer;
    private javax.swing.JLabel buildingsLabel;
    private javax.swing.JPanel buscaalojamiento2;
    private javax.swing.JTextField cityTextField;
    private javax.swing.JPanel destinopanel2;
    private javax.swing.JScrollPane disfrutade;
    private javax.swing.JFormattedTextField endDateTextField;
    private javax.swing.JComboBox<String> filterComboBox;
    private javax.swing.JScrollPane filtratubusqueda;
    private javax.swing.JLabel filtrospara;
    private javax.swing.JButton fotocasa;
    private javax.swing.JButton hostProfile;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton logo;
    private javax.swing.JScrollPane masespacio;
    private javax.swing.JLabel maspormenos;
    private javax.swing.JLabel mastiempopara;
    private javax.swing.JButton misReservasButton;
    private javax.swing.JButton myBuildingsButton;
    private javax.swing.JScrollPane regalateun;
    private javax.swing.JButton searchButton;
    private javax.swing.JButton showAllBuildingsButton;
    private javax.swing.JLabel sientetecomoencasa;
    private javax.swing.JFormattedTextField startDateTextField;
    private javax.swing.JTextArea textbuscaaloj2;
    private javax.swing.JTextArea textteesperan1;
    private javax.swing.JTextArea textteesperan2;
    private javax.swing.JTextArea textteesperan3;
    private javax.swing.JTextArea textteesperan4;
    private javax.swing.JTextArea textteesperan6;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration//GEN-END:variables
}
