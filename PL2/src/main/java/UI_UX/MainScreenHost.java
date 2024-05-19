/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package UI_UX;

import Logica.Inmueble;
import Logica.JavaBNB;
import java.awt.Desktop;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class MainScreenHost extends javax.swing.JPanel {

    public ArrayList<IconoInmueble> buildingsicon;
    private ArrayList<Inmueble> buildings;
    private ArrayList<Inmueble> allBuildings;
    //private ArrayList<Inmueble> preBuildings;
    int estado;

    /**
     * Creates new form MainScreen
     */
    public MainScreenHost() {
        initComponents();
        buildingsLabel.setVisible(false);

        // Agrega un FocusListener al campo de texto de la fecha de inicio
        startDateTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                JTextField textField = (JTextField) e.getSource();
                String text = textField.getText();
                if (!text.isEmpty() && text.length() == 6 && !text.contains("/")) {
                    textField.setText(text.substring(0, 2) + "/" + text.substring(2, 4) + "/" + text.substring(4));
                }
            }
        });

        // Agrega un FocusListener al campo de texto de la fecha de fin
        endDateTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                JTextField textField = (JTextField) e.getSource();
                String text = textField.getText();
                if (!text.isEmpty() && text.length() == 6 && !text.contains("/")) {
                    textField.setText(text.substring(0, 2) + "/" + text.substring(2, 4) + "/" + text.substring(4));
                }
            }
        });
    }

    public void actualizar() {
        buildings = JavaBNB.getInmuebles();
        allBuildings = JavaBNB.getInmuebles();

        buildingsicon = new ArrayList<>();
    }

    public void insertAllBuildings() {
        buildingsLabel.setVisible(true);
        if (allBuildings == null | allBuildings.isEmpty()) {
            System.err.println("La lista de edificios está vacía. No se pueden insertar inmuebles.");
            return;
        }
        System.out.println("La lista de edificios no está vacía:");
        deleteBuildings(); // Borra cualquier widget de edificio existente antes de insertar nuevos

        int fila = 0;
        //tamaño ventana widget= [295, 400] 
        int x = 50; //valor de prueba
        for (Inmueble inmueble : allBuildings) {
            if (x >= 800) { //1920=tamaño de la ventana
                fila += 400;
                x = 50;
            }
            IconoInmueble iconoinm = new IconoInmueble();
            iconoinm.init(inmueble);
            //AbsoluteConstraints constr = new org.netbeans.lib.awtextra.AbsoluteConstraints(295*x, fila, -1, -1);  //-1 en altura y anchura para que nos de la predeterminada del widget añadido
            buildingsicon.add(iconoinm);
            buildingsContainer.add(iconoinm, new org.netbeans.lib.awtextra.AbsoluteConstraints(x, fila, -1, -1));  //método addLayoutComponent(java.awt.Component comp, java.lang.Object constr);  Adds the specified component to the layout, using the specified constraint object.
            System.out.println(inmueble);
            x += 350; //valor de prueba
        }
        buildingsContainer.revalidate(); // Actualiza el contenedor para mostrar los cambios
        buildingsContainer.repaint();   // Repinta el contenedor para asegurar que los cambios sean visibles
        buildings = allBuildings;
    }

    public void insertBuildings() {
        buildingsLabel.setVisible(true);
        if (buildings == null | buildings.isEmpty()) {
            System.err.println("La lista de edificios está vacía. No se pueden insertar inmuebles.");
            return;
        }
        System.out.println("La lista de edificios no está vacía:");
        deleteBuildings(); // Borra cualquier widget de edificio existente antes de insertar nuevos

        int fila = 0;
        //tamaño ventana widget= [295, 400] 
        int x = 50; //valor de prueba
        for (Inmueble inmueble : buildings) {
            if (x >= 800) { //1920=tamaño de la ventana
                fila += 400;
                x = 50;
            }
            IconoInmueble iconoinm = new IconoInmueble();
            iconoinm.init(inmueble);
            //AbsoluteConstraints constr = new org.netbeans.lib.awtextra.AbsoluteConstraints(295*x, fila, -1, -1);  //-1 en altura y anchura para que nos de la predeterminada del widget añadido
            buildingsicon.add(iconoinm);
            buildingsContainer.add(iconoinm, new org.netbeans.lib.awtextra.AbsoluteConstraints(x, fila, -1, -1));  //método addLayoutComponent(java.awt.Component comp, java.lang.Object constr);  Adds the specified component to the layout, using the specified constraint object.
            System.out.println(inmueble);
            x += 350; //valor de prueba
        }
        buildingsContainer.revalidate(); // Actualiza el contenedor para mostrar los cambios
        buildingsContainer.repaint();   // Repinta el contenedor para asegurar que los cambios sean visibles
    }

    public void deleteBuildings() {
        for (IconoInmueble ii : buildingsicon) {
            this.buildingsContainer.remove(ii);
        }
        buildingsicon.clear(); // Limpia la lista de widgets de edificios después de eliminarlos
        buildingsContainer.revalidate(); // Actualiza el contenedor para mostrar los cambios
        buildingsContainer.repaint();   // Repinta el contenedor para asegurar que los cambios sean visibles
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        barraarriba = new javax.swing.JPanel();
        titleLabel = new javax.swing.JLabel();
        logo = new javax.swing.JButton();
        hostProfile = new javax.swing.JButton();
        addBuildingsButton = new javax.swing.JButton();
        myBuildingsButton = new javax.swing.JButton();
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
        cityTextField2 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        startDateTextField = new javax.swing.JFormattedTextField();
        endDateTextField = new javax.swing.JFormattedTextField();
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

        setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(255, 250, 248));

        barraarriba.setBackground(new java.awt.Color(255, 250, 248));
        barraarriba.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        barraarriba.setLayout(new java.awt.GridBagLayout());

        titleLabel.setFont(new java.awt.Font("Serif", 3, 24)); // NOI18N
        titleLabel.setForeground(new java.awt.Color(255, 90, 95));
        titleLabel.setText("JavaBNB");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(36, 18, 0, 0);
        barraarriba.add(titleLabel, gridBagConstraints);

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
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.ipadx = -22;
        gridBagConstraints.ipady = -32;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 72, 9, 0);
        barraarriba.add(logo, gridBagConstraints);

        hostProfile.setBackground(new java.awt.Color(153, 153, 153));
        hostProfile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/user (1).jpg"))); // NOI18N
        hostProfile.setBorderPainted(false);
        hostProfile.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        hostProfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hostProfileActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(24, 71, 0, 153);
        barraarriba.add(hostProfile, gridBagConstraints);

        addBuildingsButton.setBackground(new java.awt.Color(255, 90, 95));
        addBuildingsButton.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        addBuildingsButton.setText("Añadir inmueble");
        addBuildingsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBuildingsButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 32;
        gridBagConstraints.ipady = 26;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(32, 600, 0, 0);
        barraarriba.add(addBuildingsButton, gridBagConstraints);

        myBuildingsButton.setBackground(new java.awt.Color(255, 90, 95));
        myBuildingsButton.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        myBuildingsButton.setText("Mis inmuebles");
        myBuildingsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myBuildingsButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 32;
        gridBagConstraints.ipady = 26;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(32, 26, 0, 0);
        barraarriba.add(myBuildingsButton, gridBagConstraints);

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

        jLabel7.setText("Destino:");

        cityTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cityTextField2ActionPerformed(evt);
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
                    .addComponent(cityTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        destinopanel2Layout.setVerticalGroup(
            destinopanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(destinopanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cityTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 250, 248));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jSeparator1.setForeground(new java.awt.Color(255, 153, 153));
        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel2.setText("Fecha llegada");

        jLabel3.setText("Fecha salida");

        startDateTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getDateInstance(java.text.DateFormat.SHORT))));
        startDateTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startDateTextFieldActionPerformed(evt);
            }
        });

        endDateTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getDateInstance(java.text.DateFormat.SHORT))));
        endDateTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                endDateTextFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jLabel2)
                        .addGap(46, 46, 46))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(startDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)))
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jLabel3))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(endDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(startDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(endDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 17, Short.MAX_VALUE)))
                .addContainerGap())
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
                .addGroup(buscaalojamiento2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(destinopanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(1215, 1215, 1215))
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

        jButton1.setBackground(new java.awt.Color(255, 204, 204));
        jButton1.setText("jButton1");
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
                .addGap(40, 40, 40)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        barraabajoLayout.setVerticalGroup(
            barraabajoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(barraabajoLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(barraabajoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
            .addComponent(barraarriba, javax.swing.GroupLayout.DEFAULT_SIZE, 1492, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(barraabajo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(barraarriba, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addComponent(barraabajo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void hostProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hostProfileActionPerformed

    Aplicacion.loadHostProfile();
    }//GEN-LAST:event_hostProfileActionPerformed

    private void logoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_logoActionPerformed

    private void addBuildingsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBuildingsButtonActionPerformed
        Aplicacion.cardLayout.show(Aplicacion.cards, "Pantalla addbuildings");

    }//GEN-LAST:event_addBuildingsButtonActionPerformed

    private void myBuildingsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myBuildingsButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_myBuildingsButtonActionPerformed

    private void cityTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cityTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cityTextField2ActionPerformed

    private void startDateTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startDateTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_startDateTextFieldActionPerformed

    private void endDateTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_endDateTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_endDateTextFieldActionPerformed

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        filterComboBox.setSelectedItem("Filtrar por:");
        this.estado = 1;
        // Obtener los valores de los campos de texto
        String ciudad = cityTextField.getText().trim();
        String startDateStr = startDateTextField.getText().trim();
        String endDateStr = endDateTextField.getText().trim();

        // Formateador de fechas
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[dd/MM/yyyy]['ddMMyy']");

        // Variables para almacenar las fechas convertidas
        LocalDate startDate = null;
        LocalDate endDate = null;

        // Intentar convertir las fechas de texto a LocalDate
        try {
            if (!startDateStr.isEmpty()) {
                startDate = LocalDate.parse(startDateStr, formatter);
            }
            if (!endDateStr.isEmpty()) {
                endDate = LocalDate.parse(endDateStr, formatter);
            }
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(this, "Por favor, introduce las fechas en el formato DD/MM/YYYY", "Formato de fecha inválido", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Filtrar los inmuebles en función de los criterios introducidos
        ArrayList<Inmueble> inmueblesFiltrados = JavaBNB.buscarInmuebles(ciudad, startDate, endDate);

        // Actualizar la lista de buildings con los inmuebles filtrados
        buildings = inmueblesFiltrados;
        //preBuildings = inmueblesFiltrados;

        // Llama al método para insertar los inmuebles en el panel
        insertBuildings();

    }//GEN-LAST:event_searchButtonActionPerformed

    private void showAllBuildingsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showAllBuildingsButtonActionPerformed
        filterComboBox.setSelectedItem("Filtrar por:");

        this.estado = 0;
        deleteBuildings();
        insertAllBuildings();
    }//GEN-LAST:event_showAllBuildingsButtonActionPerformed

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        deleteBuildings();
        Aplicacion.cardLayout.show(Aplicacion.cards, "Pantalla privacypolicy");
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            Desktop.getDesktop().browse(new URI("https://www.instagram.com/javabnb/"));
        } catch (IOException | URISyntaxException e1) {
            e1.printStackTrace();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void filterComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filterComboBoxActionPerformed

    }//GEN-LAST:event_filterComboBoxActionPerformed

    private void applyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_applyButtonActionPerformed
        System.out.println(estado);

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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBuildingsButton;
    private javax.swing.JButton applyButton;
    private javax.swing.JPanel barraabajo;
    private javax.swing.JPanel barraarriba;
    private javax.swing.JPanel buildingsContainer;
    private javax.swing.JLabel buildingsLabel;
    private javax.swing.JPanel buscaalojamiento;
    private javax.swing.JPanel buscaalojamiento1;
    private javax.swing.JPanel buscaalojamiento2;
    private javax.swing.JTextField cityTextField;
    private javax.swing.JTextField cityTextField1;
    private javax.swing.JTextField cityTextField2;
    private javax.swing.JPanel destinopanel;
    private javax.swing.JPanel destinopanel1;
    private javax.swing.JPanel destinopanel2;
    private javax.swing.JScrollPane disfrutade;
    private javax.swing.JFormattedTextField endDateTextField;
    private javax.swing.JComboBox<String> filterComboBox;
    private javax.swing.JScrollPane filtratubusqueda;
    private javax.swing.JLabel filtrospara;
    private javax.swing.JButton fotocasa;
    private javax.swing.JButton hostProfile;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton logo;
    private javax.swing.JScrollPane masespacio;
    private javax.swing.JLabel maspormenos;
    private javax.swing.JLabel mastiempopara;
    private javax.swing.JButton myBuildingsButton;
    private javax.swing.JScrollPane regalateun;
    private javax.swing.JButton searchButton;
    private javax.swing.JButton showAllBuildingsButton;
    private javax.swing.JLabel sientetecomoencasa;
    private javax.swing.JFormattedTextField startDateTextField;
    private javax.swing.JTextArea textbuscaaloj;
    private javax.swing.JTextArea textbuscaaloj1;
    private javax.swing.JTextArea textbuscaaloj2;
    private javax.swing.JTextArea textteesperan;
    private javax.swing.JTextArea textteesperan1;
    private javax.swing.JTextArea textteesperan2;
    private javax.swing.JTextArea textteesperan3;
    private javax.swing.JTextArea textteesperan4;
    private javax.swing.JTextArea textteesperan5;
    private javax.swing.JTextArea textteesperan6;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration//GEN-END:variables
}
