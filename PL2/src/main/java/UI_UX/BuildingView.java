/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package UI_UX;

import Logica.Anfitrion;
import Logica.Inmueble;
import Logica.Particular;
import Logica.Resenia;
import Logica.Reserva;
import Logica.Sesion;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.ListIterator;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class BuildingView extends javax.swing.JPanel {

    Inmueble i;
    private ArrayList<Resenia> resenias; // Referencia al ArrayList de reseñas del inmueble
    private ListIterator<Resenia> li; // Iterador para recorrer el ArrayList en ambas direcciones
    private Resenia resenia; // Referencia a un objeto de tipo inmueble del ArrayList

    public BuildingView() {
        initComponents();
        errorLabel1.setVisible(false);
    }

    public void actualizar() {
        //Solo muestra los botones de calificar y reservar si el usuario activo de la sesión es un particular.
        if (Sesion.esAnfitrion) {
            panelreservas.setVisible(false);
            gradeButton.setVisible(false);
        } else {
            panelreservas.setVisible(true);
            gradeButton.setVisible(true);
        }
        titleLabel.setText(i.getTitulo());
        typeLabel.setText(i.getTipo());
        descriptionTextArea.setText(i.getDescripcion());
        priceLabel.setText(Double.toString(i.getPrecioNoche()) + "€ por noche");

        hostLabel.setText("Anfitrion: " + i.getAnfitrion().getNombre());
        if (i.getAnfitrion().isSuperAnfitrion()) {
            superhostLabel.setVisible(true);
        } else {
            superhostLabel.setVisible(false);
        }

        guestsLabel.setText(Integer.toString(i.getDatosInmueble().getMaxHuespedes()));
        roomsLabel.setText(Integer.toString(i.getDatosInmueble().getHabitaciones()));
        bathsLabel.setText(Integer.toString(i.getDatosInmueble().getBaños()));
        bedsLabel.setText(Integer.toString(i.getDatosInmueble().getCamas()));

        directionTextArea.setText(i.getDireccion().toString());
        servicesTextArea.setText("los servicios de este inmueble son " + i.getServicios());
        markLabel.setText("Calificación: " + Double.toString(i.getCalificacion()));
        numbermarksLabel.setText("Calificado por " + Integer.toString(i.getValoraciones()) + " personas");

        fotoboton.setIcon(resizeIMG(i.getFotografia()));
        estrella1.setIcon(i.getCalificacion() >= 1 ? imagenIcon("./src/main/resources/images/estrella50roja.PNG") : (imagenIcon("./src/main/resources/images/estrella50.PNG")));
        estrella2.setIcon(i.getCalificacion() >= 2 ? imagenIcon("./src/main/resources/images/estrella50roja.PNG") : (imagenIcon("./src/main/resources/images/estrella50.PNG")));
        estrella3.setIcon(i.getCalificacion() >= 3 ? imagenIcon("./src/main/resources/images/estrella50roja.PNG") : (imagenIcon("./src/main/resources/images/estrella50.PNG")));
        estrella4.setIcon(i.getCalificacion() >= 4 ? imagenIcon("./src/main/resources/images/estrella50roja.PNG") : (imagenIcon("./src/main/resources/images/estrella50.PNG")));
        estrella5.setIcon(i.getCalificacion() >= 5 ? imagenIcon("./src/main/resources/images/estrella50roja.PNG") : (imagenIcon("./src/main/resources/images/estrella50.PNG")));

        consultarTodo();
    }

    public void setInmueble(Inmueble inmueble) {
        this.i = inmueble;
        this.actualizar(); // Llamar al método actualizar() para actualizar la vista con el nuevo inmueble
    }

// Convierte el String de la ruta a una imagen en un "ImageIcon" para utilizarlo como imagen en la interfaz.
    public ImageIcon imagenIcon(String img) {
        try {
            Image image = ImageIO.read(new File(img));
            ImageIcon icon = new ImageIcon(image);
            return icon;
        } catch (IOException ioe) {
            System.out.println("Error de IO: " + ioe.getMessage());
            return null;
        }
    }

    //Redimensiona una imagen al tamaño del "botón" donde se mostrará en la ventana.
    public ImageIcon resizeIMG(String img) {
        try {
            File imagePath = new File(img);
            BufferedImage originalImage = ImageIO.read(imagePath);
            int width = 560;
            int height = 331;
            Image resizedImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            ImageIcon imageIcon = new ImageIcon(resizedImage);
            return imageIcon;
        } catch (IOException ioe) {
            System.out.println("Error de IO: " + ioe.getMessage());
            return null;
        }
    }

    // Convierte un objeto de tipo "Date" en LocalDate.
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

    private void consultarTodo() {
        try {
            errorNoMas.setVisible(false);
            siguiente.setEnabled(false);
            anterior.setEnabled(false);

            if (i.getResenias() != null && !i.getResenias().isEmpty()) {
                siguiente.setEnabled(true);
                anterior.setEnabled(true);

                resenias = i.getResenias();

                li = resenias.listIterator();
                if (resenias.isEmpty()) {
                    errorNoMas.setEnabled(false);
                    limpiarCampos();
                    return;
                } else {
                    errorNoMas.setEnabled(true);
                }

                if (li.hasNext()) {
                    resenia = li.next();
                } else {
                    errorNoMas.setVisible(true);
                }
                if (resenia != null) {
                    presenta();
                } else {
                    limpiarCampos();
                    errorNoMas.setVisible(true);
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
        }
    }

    private void limpiarCampos() {
        frasecomentario.setText("");
        notacomentario.setText("");
    }

    private void presenta() {
        frasecomentario.setText(resenia.getComentario());
        notacomentario.setText("Nota: " + Double.toString(resenia.getNota()));
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
        fotoboton = new javax.swing.JButton();
        starsPanel = new javax.swing.JPanel();
        estrella1 = new javax.swing.JButton();
        estrella2 = new javax.swing.JButton();
        estrella3 = new javax.swing.JButton();
        estrella4 = new javax.swing.JButton();
        estrella5 = new javax.swing.JButton();
        titleLabel = new javax.swing.JLabel();
        priceLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        descriptionTextArea = new javax.swing.JTextArea();
        barraarriba = new javax.swing.JPanel();
        returnButton = new javax.swing.JButton();
        logoButton = new javax.swing.JButton();
        logoLabel = new javax.swing.JLabel();
        typeLabel = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        servicesTextArea = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        directionTextArea = new javax.swing.JTextArea();
        guestLabel = new javax.swing.JLabel();
        bathLabel = new javax.swing.JLabel();
        roomLabel = new javax.swing.JLabel();
        bedLabel = new javax.swing.JLabel();
        guestsLabel = new javax.swing.JLabel();
        roomsLabel = new javax.swing.JLabel();
        bathsLabel = new javax.swing.JLabel();
        bedsLabel = new javax.swing.JLabel();
        panelreservas = new javax.swing.JPanel();
        reservenowLabel = new javax.swing.JLabel();
        reserveButton = new javax.swing.JButton();
        startDateLabel = new javax.swing.JLabel();
        endDateLabel = new javax.swing.JLabel();
        endDateFormattedField = new javax.swing.JFormattedTextField();
        startDateFormattedField = new javax.swing.JFormattedTextField();
        daymonthyearLabel2 = new javax.swing.JLabel();
        daymonthyearLabel1 = new javax.swing.JLabel();
        errorLabel1 = new javax.swing.JLabel();
        markLabel = new javax.swing.JLabel();
        gradeButton = new javax.swing.JButton();
        numbermarksLabel = new javax.swing.JLabel();
        hostLabel = new javax.swing.JLabel();
        superhostLabel = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        notacomentario = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        frasecomentario = new javax.swing.JTextArea();
        siguiente = new javax.swing.JButton();
        anterior = new javax.swing.JButton();
        errorNoMas = new javax.swing.JLabel();

        setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(255, 250, 248));
        jPanel1.setMaximumSize(new java.awt.Dimension(1310, 711));
        jPanel1.setPreferredSize(new java.awt.Dimension(1265, 711));

        fotoboton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/casa1.jpg"))); // NOI18N

        starsPanel.setBackground(new java.awt.Color(255, 250, 248));
        starsPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        estrella1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/estrella50roja.png"))); // NOI18N
        estrella1.setToolTipText("");
        estrella1.setBorderPainted(false);
        estrella1.setContentAreaFilled(false);

        estrella2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/estrella50roja.png"))); // NOI18N
        estrella2.setToolTipText("");
        estrella2.setBorderPainted(false);
        estrella2.setContentAreaFilled(false);

        estrella3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/estrella50.png"))); // NOI18N
        estrella3.setToolTipText("");
        estrella3.setBorderPainted(false);
        estrella3.setContentAreaFilled(false);

        estrella4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/estrella50.png"))); // NOI18N
        estrella4.setToolTipText("");
        estrella4.setBorderPainted(false);
        estrella4.setContentAreaFilled(false);
        estrella4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                estrella4ActionPerformed(evt);
            }
        });

        estrella5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/estrella50.png"))); // NOI18N
        estrella5.setToolTipText("");
        estrella5.setBorderPainted(false);
        estrella5.setContentAreaFilled(false);

        javax.swing.GroupLayout starsPanelLayout = new javax.swing.GroupLayout(starsPanel);
        starsPanel.setLayout(starsPanelLayout);
        starsPanelLayout.setHorizontalGroup(
            starsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(starsPanelLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(estrella1)
                .addGap(6, 6, 6)
                .addComponent(estrella2)
                .addGap(6, 6, 6)
                .addComponent(estrella3)
                .addGap(6, 6, 6)
                .addComponent(estrella4)
                .addGap(6, 6, 6)
                .addComponent(estrella5)
                .addContainerGap(8, Short.MAX_VALUE))
        );
        starsPanelLayout.setVerticalGroup(
            starsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(starsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(starsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(estrella1)
                    .addComponent(estrella2)
                    .addComponent(estrella3)
                    .addComponent(estrella4)
                    .addComponent(estrella5))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        titleLabel.setFont(new java.awt.Font("Serif", 1, 36)); // NOI18N
        titleLabel.setText("Titulo");

        priceLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        priceLabel.setText("precio $/noche");

        descriptionTextArea.setEditable(false);
        descriptionTextArea.setBackground(new java.awt.Color(255, 250, 248));
        descriptionTextArea.setColumns(20);
        descriptionTextArea.setRows(5);
        descriptionTextArea.setText("Descripcion : Casa preciosa en Gandía");
        descriptionTextArea.setAutoscrolls(false);
        descriptionTextArea.setBorder(null);
        descriptionTextArea.setCaretPosition(0);
        descriptionTextArea.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane1.setViewportView(descriptionTextArea);

        barraarriba.setBackground(new java.awt.Color(255, 250, 248));
        barraarriba.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        returnButton.setBackground(new java.awt.Color(255, 153, 153));
        returnButton.setText("Volver");
        returnButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                returnButtonActionPerformed(evt);
            }
        });

        logoButton.setBackground(new java.awt.Color(255, 153, 153));
        logoButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/airbnb logo - 100x100.png"))); // NOI18N
        logoButton.setBorderPainted(false);
        logoButton.setContentAreaFilled(false);
        logoButton.setDefaultCapable(false);
        logoButton.setFocusPainted(false);
        logoButton.setFocusable(false);
        logoButton.setRequestFocusEnabled(false);
        logoButton.setRolloverEnabled(false);
        logoButton.setVerifyInputWhenFocusTarget(false);
        logoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoButtonActionPerformed(evt);
            }
        });

        logoLabel.setFont(new java.awt.Font("Serif", 3, 24)); // NOI18N
        logoLabel.setForeground(new java.awt.Color(255, 90, 95));
        logoLabel.setText("JavaBNB");

        javax.swing.GroupLayout barraarribaLayout = new javax.swing.GroupLayout(barraarriba);
        barraarriba.setLayout(barraarribaLayout);
        barraarribaLayout.setHorizontalGroup(
            barraarribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, barraarribaLayout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(logoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(logoLabel)
                .addGap(1500, 1500, 1500)
                .addComponent(returnButton)
                .addGap(64, 64, 64))
        );
        barraarribaLayout.setVerticalGroup(
            barraarribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(barraarribaLayout.createSequentialGroup()
                .addGroup(barraarribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(barraarribaLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(returnButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(barraarribaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(barraarribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(logoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, barraarribaLayout.createSequentialGroup()
                                .addComponent(logoLabel)
                                .addGap(21, 21, 21)))))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        typeLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        typeLabel.setText("tipo");

        servicesTextArea.setEditable(false);
        servicesTextArea.setBackground(new java.awt.Color(255, 250, 248));
        servicesTextArea.setColumns(20);
        servicesTextArea.setRows(5);
        servicesTextArea.setText("Los servicios de este inmueble son blablabla");
        servicesTextArea.setBorder(null);
        jScrollPane3.setViewportView(servicesTextArea);

        directionTextArea.setEditable(false);
        directionTextArea.setBackground(new java.awt.Color(255, 250, 248));
        directionTextArea.setColumns(20);
        directionTextArea.setRows(5);
        directionTextArea.setText("Calle Ponferrada,  nº1, Alcalá de henares. CP:28804");
        directionTextArea.setBorder(null);
        jScrollPane4.setViewportView(directionTextArea);

        guestLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        guestLabel.setText("Nº máximo de huéspedes:");

        bathLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        bathLabel.setText("Baños:");

        roomLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        roomLabel.setText("Habitaciones:");

        bedLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        bedLabel.setText("Camas:");

        guestsLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        guestsLabel.setText("1");

        roomsLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        roomsLabel.setText("1");

        bathsLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        bathsLabel.setText("1");

        bedsLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        bedsLabel.setText("1");

        panelreservas.setBackground(new java.awt.Color(255, 250, 248));
        panelreservas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        reservenowLabel.setFont(new java.awt.Font("Serif", 1, 24)); // NOI18N
        reservenowLabel.setText("Reserva ya");

        reserveButton.setText("Reservar");
        reserveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reserveButtonActionPerformed(evt);
            }
        });

        startDateLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        startDateLabel.setText("Fecha de llegada");

        endDateLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        endDateLabel.setText("Fecha de salida");

        endDateFormattedField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd/MM/yyyy"))));

        startDateFormattedField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd/MM/yyyy"))));

        daymonthyearLabel2.setText("dd/MM/yyyy");

        daymonthyearLabel1.setText("dd/MM/yyyy");

        errorLabel1.setForeground(new java.awt.Color(255, 102, 102));
        errorLabel1.setText("El inmueble no está disponible para estas fechas");

        javax.swing.GroupLayout panelreservasLayout = new javax.swing.GroupLayout(panelreservas);
        panelreservas.setLayout(panelreservasLayout);
        panelreservasLayout.setHorizontalGroup(
            panelreservasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelreservasLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(reservenowLabel)
                .addGap(116, 116, 116))
            .addGroup(panelreservasLayout.createSequentialGroup()
                .addGroup(panelreservasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelreservasLayout.createSequentialGroup()
                        .addGap(135, 135, 135)
                        .addComponent(reserveButton))
                    .addGroup(panelreservasLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(panelreservasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelreservasLayout.createSequentialGroup()
                                .addComponent(daymonthyearLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(86, 86, 86)
                                .addComponent(daymonthyearLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(9, 9, 9))
                            .addComponent(errorLabel1)))
                    .addGroup(panelreservasLayout.createSequentialGroup()
                        .addGroup(panelreservasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(panelreservasLayout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addComponent(startDateFormattedField, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(53, 53, 53))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelreservasLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(startDateLabel)
                                .addGap(67, 67, 67)))
                        .addGroup(panelreservasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(endDateLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(endDateFormattedField))))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        panelreservasLayout.setVerticalGroup(
            panelreservasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelreservasLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(reservenowLabel)
                .addGap(18, 18, 18)
                .addGroup(panelreservasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(startDateLabel)
                    .addComponent(endDateLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelreservasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(daymonthyearLabel2)
                    .addComponent(daymonthyearLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelreservasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(startDateFormattedField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(endDateFormattedField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(errorLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(reserveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        markLabel.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        markLabel.setText("Calificación: 2.5");

        gradeButton.setBackground(new java.awt.Color(255, 102, 102));
        gradeButton.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        gradeButton.setForeground(new java.awt.Color(255, 255, 255));
        gradeButton.setText("Calificar");
        gradeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gradeButtonActionPerformed(evt);
            }
        });

        numbermarksLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        numbermarksLabel.setText("Calificado por x personas");

        hostLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        hostLabel.setText("Anfitrion:");

        superhostLabel.setBackground(new java.awt.Color(255, 153, 153));
        superhostLabel.setFont(new java.awt.Font("Serif", 1, 24)); // NOI18N
        superhostLabel.setForeground(new java.awt.Color(255, 102, 102));
        superhostLabel.setText("Superanfitrión");

        jPanel2.setBackground(new java.awt.Color(255, 250, 248));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        notacomentario.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        notacomentario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        notacomentario.setText("Nota:");

        frasecomentario.setBackground(new java.awt.Color(255, 250, 248));
        frasecomentario.setColumns(20);
        frasecomentario.setRows(5);
        jScrollPane2.setViewportView(frasecomentario);

        siguiente.setBackground(new java.awt.Color(255, 102, 102));
        siguiente.setFont(new java.awt.Font("Serif", 1, 12)); // NOI18N
        siguiente.setForeground(new java.awt.Color(255, 255, 255));
        siguiente.setText("Siguiente");
        siguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siguienteActionPerformed(evt);
            }
        });

        anterior.setBackground(new java.awt.Color(255, 102, 102));
        anterior.setFont(new java.awt.Font("Serif", 1, 12)); // NOI18N
        anterior.setForeground(new java.awt.Color(255, 255, 255));
        anterior.setText("Anterior");
        anterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                anteriorActionPerformed(evt);
            }
        });

        errorNoMas.setForeground(new java.awt.Color(255, 102, 102));
        errorNoMas.setText("No hay más comentarios");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(errorNoMas)
                        .addGap(100, 100, 100))))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(anterior)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(siguiente))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(notacomentario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(siguiente)
                    .addComponent(anterior))
                .addGap(9, 9, 9)
                .addComponent(notacomentario, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(errorNoMas)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(barraarriba, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fotoboton, javax.swing.GroupLayout.PREFERRED_SIZE, 560, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(markLabel)
                            .addComponent(gradeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(starsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(numbermarksLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(175, 175, 175)
                                .addComponent(priceLabel))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 614, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
                                    .addComponent(jScrollPane3))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(bedLabel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(bedsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(guestLabel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(guestsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(bathLabel)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(bathsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addComponent(roomLabel))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(roomsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(hostLabel)
                                    .addComponent(superhostLabel))
                                .addGap(40, 40, 40)
                                .addComponent(panelreservas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(titleLabel)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(217, 217, 217)
                                .addComponent(typeLabel)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(barraarriba, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(218, 218, 218)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(guestLabel)
                                    .addComponent(guestsLabel))
                                .addGap(10, 10, 10)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(bathLabel)
                                    .addComponent(bathsLabel)))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(roomLabel)
                                    .addComponent(roomsLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(bedLabel)
                                    .addComponent(bedsLabel)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fotoboton, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(titleLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(typeLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(priceLabel)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(hostLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(superhostLabel))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(starsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(markLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(numbermarksLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(gradeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(panelreservas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0))
        );

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void estrella4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_estrella4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_estrella4ActionPerformed

    private void returnButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_returnButtonActionPerformed
        if (Sesion.user != null) {
            App.loadMainScreen();
        }
    }//GEN-LAST:event_returnButtonActionPerformed

    private void logoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_logoButtonActionPerformed

    private void reserveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reserveButtonActionPerformed
        errorLabel1.setVisible(false);
        //Las fechas de los formatted field se transforman en LocalDates
        LocalDate llegada = convertToLocalDate(startDateFormattedField.getValue());
        LocalDate salida = convertToLocalDate(endDateFormattedField.getValue());

        Reserva reserva = new Reserva((Particular) Sesion.user, i, llegada, salida);
        System.out.println("El inmueble está disponible: " + i.estaDisponible(llegada, salida));
        System.out.println(((Particular) Sesion.user).getSaldo() > reserva.calcularPrecioTotal());

        if (((Particular) Sesion.user).getSaldo() <= reserva.calcularPrecioTotal()) {
            JOptionPane.showMessageDialog(this, "No tienes suficiente dinero para hacer esta reserva", "Dinero insuficiente", JOptionPane.WARNING_MESSAGE);

        }

        if ((i.estaDisponible(llegada, salida)) && (((Particular) Sesion.user).getSaldo() > reserva.calcularPrecioTotal())
                && reserva.calcularPrecioTotal() > 0.0) {

            String textoconfirmacion = "¿Quieres confirmar la reserva de este inmueble del " + llegada + " al " + salida + " por un coste total de " + reserva.calcularPrecioTotal() + " euros?";
            int n = JOptionPane.showConfirmDialog(this, textoconfirmacion, "ConfirmDialog", JOptionPane.YES_NO_CANCEL_OPTION);
            if (n == JOptionPane.YES_OPTION) {
                System.out.println("SI. Haciendo reserva ");
                ArrayList<Reserva> reservass = i.getReservas();

                //Añade la reserva a la lista de reservas y se realiza el "pago"
                i.agregarReserva(reserva);
                ((Particular) Sesion.user).disminuirSaldo(reserva.calcularPrecioTotal());

            } else if (n == JOptionPane.NO_OPTION) {
                System.out.println("NO");
            } else if (n == JOptionPane.CANCEL_OPTION) {
                System.out.println("CANCEL");
            } else {
                System.out.println("Ninguna Opción");
            }
        } else {
            errorLabel1.setVisible(true);
            System.out.println("ERROR");
        }


    }//GEN-LAST:event_reserveButtonActionPerformed

    private void gradeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gradeButtonActionPerformed
        double nota = 0;
        boolean reservaHecha = false;
        // Obtener todas las reservas asociadas al inmueble
        ArrayList<Reserva> reservas = i.getReservas();

        // Iterar sobre todas las reservas
        for (Reserva reserva : reservas) {
            // Verificar si el cliente de la reserva es el mismo que el usuario actual de la sesión
            if (reserva.getParticular().getDni().equals((((Particular) Sesion.user).getDni()))) {
                // El usuario ha realizado una reserva en este inmueble
                reservaHecha = true;
                break;
            }
        }

        // Verificar si el usuario ha realizado alguna reserva en este inmueble
        if (reservaHecha) {
            try {
                do {
                    String notaIntroducida = JOptionPane.showInputDialog(this, "Introduzca la calificación (entre 0 y 5):");
                    nota = Double.parseDouble(notaIntroducida);
                } while (nota < 0 || nota > 5);
                String comentario = JOptionPane.showInputDialog(this, "Introduzca el comentario:");
                try {
                    Resenia resenianueva = new Resenia(comentario, nota);
                    if (resenianueva != null) {
                        i.addResenias(resenianueva);
                    }
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }

                // Modificar la calificación del inmueble
                i.setCalificacion(nota);

                // Obtener el anfitrión del inmueble y actualizar su estado de "Superanfitrión".
                Anfitrion anfitrion = i.getAnfitrion();
                anfitrion.setSuperAnfitrion();

                actualizar();

                System.out.println("calificacion=" + nota);
                System.out.println("nota del inmueble= " + i.getCalificacion());
            } catch (NumberFormatException nfe) {
                System.out.println("Error del formato: " + nfe.getMessage());
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());

            }
        } else {
            JOptionPane.showMessageDialog(this, "Solo los usuarios que han realizado al menos una reserva en este inmueble pueden calificarlo.");
        }


    }//GEN-LAST:event_gradeButtonActionPerformed

    private void siguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siguienteActionPerformed
        if (li.hasNext()) {
            resenia = li.next();
            errorNoMas.setVisible(false);
            presenta();

        } else {
            errorNoMas.setVisible(true);
        }
    }//GEN-LAST:event_siguienteActionPerformed

    private void anteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_anteriorActionPerformed
        if (li.hasPrevious()) {
            resenia = li.previous();
            errorNoMas.setVisible(false);
            presenta();

        } else {
            errorNoMas.setVisible(true);
        }
    }//GEN-LAST:event_anteriorActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton anterior;
    private javax.swing.JPanel barraarriba;
    private javax.swing.JLabel bathLabel;
    private javax.swing.JLabel bathsLabel;
    private javax.swing.JLabel bedLabel;
    private javax.swing.JLabel bedsLabel;
    private javax.swing.JLabel daymonthyearLabel1;
    private javax.swing.JLabel daymonthyearLabel2;
    private javax.swing.JTextArea descriptionTextArea;
    private javax.swing.JTextArea directionTextArea;
    private javax.swing.JFormattedTextField endDateFormattedField;
    private javax.swing.JLabel endDateLabel;
    private javax.swing.JLabel errorLabel1;
    private javax.swing.JLabel errorNoMas;
    private javax.swing.JButton estrella1;
    private javax.swing.JButton estrella2;
    private javax.swing.JButton estrella3;
    private javax.swing.JButton estrella4;
    private javax.swing.JButton estrella5;
    private javax.swing.JButton fotoboton;
    private javax.swing.JTextArea frasecomentario;
    private javax.swing.JButton gradeButton;
    private javax.swing.JLabel guestLabel;
    private javax.swing.JLabel guestsLabel;
    private javax.swing.JLabel hostLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JButton logoButton;
    private javax.swing.JLabel logoLabel;
    private javax.swing.JLabel markLabel;
    private javax.swing.JLabel notacomentario;
    private javax.swing.JLabel numbermarksLabel;
    private javax.swing.JPanel panelreservas;
    private javax.swing.JLabel priceLabel;
    private javax.swing.JButton reserveButton;
    private javax.swing.JLabel reservenowLabel;
    private javax.swing.JButton returnButton;
    private javax.swing.JLabel roomLabel;
    private javax.swing.JLabel roomsLabel;
    private javax.swing.JTextArea servicesTextArea;
    private javax.swing.JButton siguiente;
    private javax.swing.JPanel starsPanel;
    private javax.swing.JFormattedTextField startDateFormattedField;
    private javax.swing.JLabel startDateLabel;
    private javax.swing.JLabel superhostLabel;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JLabel typeLabel;
    // End of variables declaration//GEN-END:variables
}
