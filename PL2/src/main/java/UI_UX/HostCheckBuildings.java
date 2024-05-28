package UI_UX;

import Logica.Anfitrion;
import Logica.Inmueble;
import Logica.JavaBNB;
import Logica.Sesion;
import Logica.Validacion;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.ListIterator;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import UI_UX.App;

// Panel para que el anfitrión revise sus inmuebles y realice acciones relacionadas.
public class HostCheckBuildings extends javax.swing.JPanel {

    private ArrayList<Inmueble> buildings; // Referencia al ArrayList de inmuebles del anfitrión de la sesión
    private ListIterator<Inmueble> li; // Iterador para recorrer el ArrayList en ambas direcciones
    private Inmueble objInm; // Referencia a un objeto de tipo inmueble del ArrayList
    private Inmueble inmuebleActual;
    private String fotografia = "";

    public HostCheckBuildings() {
        initComponents();
        errorNextLabel.setVisible(false);
        errorPreviousLabel.setVisible(false);
        setEditableFields(false);
        consultarTodo();
    }

    public void actualizar() {
        errorNextLabel.setVisible(false);
        errorPreviousLabel.setVisible(false);

        consultarTodo();
    }

    public Inmueble getInmuebleActual() {
        return inmuebleActual;
    }

    public void setInmuebleActual(Inmueble inmuebleActual) {
        this.inmuebleActual = inmuebleActual;
    }

    private void consultarTodo() {
        if (Sesion.user != null) {
            try {
                errorNextLabel.setVisible(false);
                errorPreviousLabel.setVisible(false);

                // Verificar si el usuario actual es un anfitrión
                if (Sesion.esAnfitrion) {
                    Anfitrion anfitrion = (Anfitrion) Sesion.user;
                    // Copia de la lista de inmuebles del anfitrión para evitar problemas de concurrencia
                    buildings = new ArrayList<>(JavaBNB.filtrarInmueblesPorAnfitrion(anfitrion)); // Obtener los inmuebles del anfitrión utilizando el método filtrarInmueblesPorAnfitrion

                    // Verificar si hay inmuebles asociados al anfitrión
                    if (buildings.isEmpty()) {
                        setButtonsEnabled(false);
                        limpiarCampos();
                        return;
                    } else {
                        setButtonsEnabled(true);
                    }

                    // Configurar un iterador para los inmuebles
                    li = buildings.listIterator();
                    if (li.hasNext()) {
                        objInm = li.next();
                        presenta(objInm);
                    } else {
                        errorNextLabel.setVisible(true);
                    }

                } else {
                    // Si el usuario no es un anfitrión, mostrar un mensaje de error
                    System.out.println("El usuario actual no es un anfitrión.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.toString());
            }
        }
    }

    private void setEditableFields(boolean editable) {
        titleTextPanel.setEditable(editable);
        descriptionTextPanel.setEditable(editable);
        serviceTextField.setEditable(editable);
        streetTextField.setEditable(editable);
        cityTextField.setEditable(editable);
        numberTextField.setEditable(editable);
        cpTextField.setEditable(editable);
        priceTextField.setEditable(editable);
        guestTextField.setEditable(editable);
        roomTextField.setEditable(editable);
        bedTextField.setEditable(editable);
        bathTextField.setEditable(editable);
        serviceTextField.setEditable(editable);
    }

    private void setButtonsEnabled(boolean enabled) {
        nextButton.setEnabled(enabled);
        previousButton.setEnabled(enabled);
        deleteBuildingButton.setEnabled(enabled);
        checkReservesButton.setEnabled(enabled);
        editBuildingButton.setEnabled(enabled);
        editphotoButton.setEnabled(enabled);
    }

    private void presenta(Inmueble inmueble) {
        typeLabel.setText(inmueble.getTipo());
        titleTextPanel.setText(inmueble.getTitulo());
        descriptionTextPanel.setText(inmueble.getDescripcion());
        streetTextField.setText(inmueble.getDireccion().getCalle());
        cityTextField.setText(inmueble.getDireccion().getCiudad());
        numberTextField.setText(inmueble.getDireccion().getNumero());
        cpTextField.setText(inmueble.getDireccion().getCp());
        priceTextField.setText(Double.toString(inmueble.getPrecioNoche()));
        guestTextField.setText(Integer.toString(inmueble.getDatosInmueble().getMaxHuespedes()));
        roomTextField.setText(Integer.toString(inmueble.getDatosInmueble().getHabitaciones()));
        bedTextField.setText(Integer.toString(inmueble.getDatosInmueble().getCamas()));
        bathTextField.setText(Integer.toString(inmueble.getDatosInmueble().getBaños()));
        serviceTextField.setText(inmueble.getServicios());
        markTextField.setText(String.valueOf(inmueble.getCalificacion()));
    }

    // Abre un cuadro de diálogo para seleccionar una imagen. Devuelve el archivo de imagen seleccionado, 
    //o null si no se selecciona ningún archivo.
    public File openImage() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Selecciona una imagen");
        fileChooser.setAcceptAllFileFilterUsed(false); // Deshabilitar la opción "Todos los archivos"
        fileChooser.addChoosableFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Image files", "jpg", "jpeg", "png", "gif", "bmp"));

        int result = fileChooser.showOpenDialog(null); // Mostrar el diálogo de selección y capturar la respuesta

        // Procesar la respuesta
        if (result == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile();
        }
        return null; // No se selecciona ningún archivo
    }

    //Método para guardar la imagen introducida en los archivos del proyecto, en la carpeta "fotosinmuebles".
    public String saveImage(File archivofoto) {
        String directoriodestino = "./src/main/resources/fotosinmuebles"; // Directorio de destino fijo
        Path pathdestino = Paths.get(directoriodestino, archivofoto.getName());

        try {
            // Asegúrate de que el directorio exista
            if (!Files.exists(Paths.get(directoriodestino))) {
                Files.createDirectories(Paths.get(directoriodestino));
            }

            // Copia el archivo al directorio especificado
            Files.copy(archivofoto.toPath(), pathdestino, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Imagen guardada en: " + pathdestino);
            return pathdestino.toString(); // Devuelve la ruta de la imagen como String
        } catch (IOException ex) {
            System.out.println("Error al guardar la imagen: " + ex.getMessage());
            return null; // Devuelve null si hay un error
        }
    }

    private void limpiarCampos() {
        typeLabel.setText("");
        titleTextPanel.setText("");
        descriptionTextPanel.setText("");
        streetTextField.setText("");
        cityTextField.setText("");
        numberTextField.setText("");
        cpTextField.setText("");
        priceTextField.setText("");
        guestTextField.setText("");
        roomTextField.setText("");
        bedTextField.setText("");
        bathTextField.setText("");
        serviceTextField.setText("");
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
        barraarriba1 = new javax.swing.JPanel();
        logoButton = new javax.swing.JButton();
        logoLabel = new javax.swing.JLabel();
        returnButton = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        titleLabel = new javax.swing.JLabel();
        typeLabel = new javax.swing.JLabel();
        descriptionLabel = new javax.swing.JLabel();
        streetLabel = new javax.swing.JLabel();
        cityLabel = new javax.swing.JLabel();
        cityTextField = new javax.swing.JTextField();
        deleteBuildingButton = new javax.swing.JButton();
        cpLabel = new javax.swing.JLabel();
        numberLabel = new javax.swing.JLabel();
        numberTextField = new javax.swing.JTextField();
        cpTextField = new javax.swing.JTextField();
        priceLabel = new javax.swing.JLabel();
        priceTextField = new javax.swing.JTextField();
        guestLabel = new javax.swing.JLabel();
        guestTextField = new javax.swing.JTextField();
        roomLabel = new javax.swing.JLabel();
        roomTextField = new javax.swing.JTextField();
        bedLabel = new javax.swing.JLabel();
        bathLabel = new javax.swing.JLabel();
        bedTextField = new javax.swing.JTextField();
        bathTextField = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        titleTextPanel = new javax.swing.JTextPane();
        streetTextField = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        serviceTextField = new javax.swing.JTextPane();
        editBuildingButton = new javax.swing.JButton();
        serviceLabel = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        descriptionTextPanel = new javax.swing.JTextPane();
        markLabel = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        markTextField = new javax.swing.JTextPane();
        checkReservesButton = new javax.swing.JButton();
        editphotoButton = new javax.swing.JButton();
        previousButton = new javax.swing.JButton();
        nextButton = new javax.swing.JButton();
        errorNextLabel = new javax.swing.JLabel();
        errorPreviousLabel = new javax.swing.JLabel();

        setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(255, 250, 248));

        barraarriba1.setBackground(new java.awt.Color(255, 250, 248));
        barraarriba1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        barraarriba1.setLayout(new java.awt.GridBagLayout());

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
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = -35;
        gridBagConstraints.ipady = -32;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 69, 7, 0);
        barraarriba1.add(logoButton, gridBagConstraints);

        logoLabel.setFont(new java.awt.Font("Serif", 3, 24)); // NOI18N
        logoLabel.setForeground(new java.awt.Color(255, 90, 95));
        logoLabel.setText("JavaBNB");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(35, 18, 0, 0);
        barraarriba1.add(logoLabel, gridBagConstraints);

        returnButton.setBackground(new java.awt.Color(255, 90, 95));
        returnButton.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        returnButton.setForeground(new java.awt.Color(255, 255, 255));
        returnButton.setText("Volver");
        returnButton.setBorderPainted(false);
        returnButton.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        returnButton.setPreferredSize(new java.awt.Dimension(80, 30));
        returnButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                returnButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 24;
        gridBagConstraints.ipady = 23;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(21, 479, 0, 175);
        barraarriba1.add(returnButton, gridBagConstraints);

        jPanel3.setBackground(new java.awt.Color(255, 250, 248));

        jPanel2.setBackground(new java.awt.Color(255, 250, 248));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel2.setMinimumSize(new java.awt.Dimension(580, 499));
        jPanel2.setPreferredSize(new java.awt.Dimension(370, 640));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        titleLabel.setText("Título:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipady = -1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(26, 37, 0, 0);
        jPanel2.add(titleLabel, gridBagConstraints);

        typeLabel.setFont(new java.awt.Font("Serif", 0, 24)); // NOI18N
        typeLabel.setText("var1");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 118;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 49, 0, 0);
        jPanel2.add(typeLabel, gridBagConstraints);

        descriptionLabel.setText("Descripción:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(22, 21, 0, 0);
        jPanel2.add(descriptionLabel, gridBagConstraints);

        streetLabel.setText("Calle:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(21, 39, 0, 0);
        jPanel2.add(streetLabel, gridBagConstraints);

        cityLabel.setText("Ciudad:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(21, 32, 0, 0);
        jPanel2.add(cityLabel, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 140;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 24, 0, 54);
        jPanel2.add(cityTextField, gridBagConstraints);

        deleteBuildingButton.setBackground(new java.awt.Color(255, 153, 153));
        deleteBuildingButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        deleteBuildingButton.setForeground(new java.awt.Color(255, 255, 255));
        deleteBuildingButton.setText("Eliminar inmueble");
        deleteBuildingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBuildingButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 31;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 30, 17, 0);
        jPanel2.add(deleteBuildingButton, gridBagConstraints);

        cpLabel.setText("C. Postal:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(22, 28, 0, 0);
        jPanel2.add(cpLabel, gridBagConstraints);

        numberLabel.setText("Número:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(22, 29, 0, 0);
        jPanel2.add(numberLabel, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 140;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 24, 0, 54);
        jPanel2.add(numberTextField, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 140;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 24, 0, 54);
        jPanel2.add(cpTextField, gridBagConstraints);

        priceLabel.setText("P.Noche:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(22, 29, 0, 0);
        jPanel2.add(priceLabel, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 140;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 24, 0, 54);
        jPanel2.add(priceTextField, gridBagConstraints);

        guestLabel.setText("Huéspedes:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 16;
        gridBagConstraints.gridwidth = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(22, 21, 0, 0);
        jPanel2.add(guestLabel, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 16;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 140;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 24, 0, 54);
        jPanel2.add(guestTextField, gridBagConstraints);

        roomLabel.setText("Habitaciones:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 18;
        gridBagConstraints.gridwidth = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(22, 16, 0, 0);
        jPanel2.add(roomLabel, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 18;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 140;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 24, 0, 54);
        jPanel2.add(roomTextField, gridBagConstraints);

        bedLabel.setText("Camas:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 20;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(22, 33, 0, 0);
        jPanel2.add(bedLabel, gridBagConstraints);

        bathLabel.setText("Baños:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 22;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(22, 35, 0, 0);
        jPanel2.add(bathLabel, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 20;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 140;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 24, 0, 54);
        jPanel2.add(bedTextField, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 22;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 140;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 24, 0, 54);
        jPanel2.add(bathTextField, gridBagConstraints);

        jScrollPane3.setMinimumSize(new java.awt.Dimension(300, 50));
        jScrollPane3.setPreferredSize(new java.awt.Dimension(64, 22));

        titleTextPanel.setMinimumSize(new java.awt.Dimension(64, 22));
        titleTextPanel.setPreferredSize(new java.awt.Dimension(64, 22));
        jScrollPane3.setViewportView(titleTextPanel);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(18, 24, 0, 54);
        jPanel2.add(jScrollPane3, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 140;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 24, 0, 54);
        jPanel2.add(streetTextField, gridBagConstraints);

        jScrollPane4.setMinimumSize(new java.awt.Dimension(300, 50));
        jScrollPane4.setPreferredSize(new java.awt.Dimension(64, 22));

        serviceTextField.setMinimumSize(new java.awt.Dimension(64, 22));
        serviceTextField.setPreferredSize(new java.awt.Dimension(64, 22));
        jScrollPane4.setViewportView(serviceTextField);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 24;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(18, 24, 0, 54);
        jPanel2.add(jScrollPane4, gridBagConstraints);

        editBuildingButton.setBackground(new java.awt.Color(255, 90, 95));
        editBuildingButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        editBuildingButton.setForeground(new java.awt.Color(255, 255, 255));
        editBuildingButton.setText("Editar inmueble");
        editBuildingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBuildingButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 30;
        gridBagConstraints.ipadx = 14;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 30, 0, 0);
        jPanel2.add(editBuildingButton, gridBagConstraints);

        serviceLabel.setText("Servicios:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 24;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(22, 27, 0, 0);
        jPanel2.add(serviceLabel, gridBagConstraints);

        jScrollPane5.setMinimumSize(new java.awt.Dimension(300, 50));
        jScrollPane5.setPreferredSize(new java.awt.Dimension(64, 22));

        descriptionTextPanel.setMinimumSize(new java.awt.Dimension(64, 22));
        descriptionTextPanel.setPreferredSize(new java.awt.Dimension(64, 22));
        jScrollPane5.setViewportView(descriptionTextPanel);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(18, 24, 0, 54);
        jPanel2.add(jScrollPane5, gridBagConstraints);

        markLabel.setText("Calificación:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 22, 0, 0);
        jPanel2.add(markLabel, gridBagConstraints);

        jScrollPane6.setMinimumSize(new java.awt.Dimension(300, 50));
        jScrollPane6.setPreferredSize(new java.awt.Dimension(64, 22));

        markTextField.setMinimumSize(new java.awt.Dimension(64, 22));
        markTextField.setPreferredSize(new java.awt.Dimension(64, 22));
        jScrollPane6.setViewportView(markTextField);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(11, 24, 0, 54);
        jPanel2.add(jScrollPane6, gridBagConstraints);

        checkReservesButton.setBackground(new java.awt.Color(255, 90, 95));
        checkReservesButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        checkReservesButton.setForeground(new java.awt.Color(255, 255, 255));
        checkReservesButton.setText("Ver reservas");
        checkReservesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkReservesButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 28;
        gridBagConstraints.ipadx = 28;
        gridBagConstraints.insets = new java.awt.Insets(17, 30, 0, 0);
        jPanel2.add(checkReservesButton, gridBagConstraints);

        editphotoButton.setBackground(new java.awt.Color(255, 90, 95));
        editphotoButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        editphotoButton.setForeground(new java.awt.Color(255, 255, 255));
        editphotoButton.setText("Cambiar foto");
        editphotoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editphotoButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 29;
        gridBagConstraints.ipadx = 28;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(17, 30, 0, 0);
        jPanel2.add(editphotoButton, gridBagConstraints);

        previousButton.setBackground(new java.awt.Color(255, 153, 153));
        previousButton.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        previousButton.setForeground(new java.awt.Color(255, 255, 255));
        previousButton.setText("Anterior");
        previousButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                previousButtonActionPerformed(evt);
            }
        });

        nextButton.setBackground(new java.awt.Color(255, 153, 153));
        nextButton.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        nextButton.setForeground(new java.awt.Color(255, 255, 255));
        nextButton.setText("Siguiente");
        nextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextButtonActionPerformed(evt);
            }
        });

        errorNextLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        errorNextLabel.setForeground(new java.awt.Color(255, 102, 102));
        errorNextLabel.setText("No hay un inmueble posterior");

        errorPreviousLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        errorPreviousLabel.setForeground(new java.awt.Color(255, 102, 102));
        errorPreviousLabel.setText("No hay un inmueble anterior");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(418, 418, 418)
                        .addComponent(previousButton)
                        .addGap(463, 463, 463)
                        .addComponent(nextButton))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(561, 561, 561)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(620, 620, 620)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(errorPreviousLabel)
                            .addComponent(errorNextLabel))))
                .addGap(481, 481, 481))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(previousButton)
                    .addComponent(nextButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 785, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(errorNextLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(errorPreviousLabel)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(barraarriba1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(barraarriba1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void nextButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextButtonActionPerformed
        if (li.hasNext()) {
            objInm = li.next();
            errorNextLabel.setVisible(false);
            errorPreviousLabel.setVisible(false);
            presenta(objInm);

        } else {
            errorNextLabel.setVisible(true);
        }
    }//GEN-LAST:event_nextButtonActionPerformed

    private void previousButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previousButtonActionPerformed
        if (li.hasPrevious()) {
            objInm = li.previous();
            errorNextLabel.setVisible(false);
            errorPreviousLabel.setVisible(false);
            presenta(objInm);

        } else {
            errorPreviousLabel.setVisible(true);
        }
    }//GEN-LAST:event_previousButtonActionPerformed

    private void logoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_logoButtonActionPerformed

    private void deleteBuildingButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBuildingButtonActionPerformed
        if (objInm != null) {
            JavaBNB.eliminarInmueble(objInm); // Llamar al método para eliminar el inmueble

            li.remove();

            if (li.hasNext()) {
                objInm = li.next();
                if (objInm != null) {
                    presenta(objInm);
                }
            } else if (li.hasPrevious()) {
                objInm = li.previous();
                if (objInm != null) {
                    presenta(objInm);
                }
            } else {
                limpiarCampos();
            }
        }


    }//GEN-LAST:event_deleteBuildingButtonActionPerformed

    private void returnButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_returnButtonActionPerformed
        App.loadMainScreen();
    }//GEN-LAST:event_returnButtonActionPerformed

    private void editBuildingButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBuildingButtonActionPerformed
// ActionListener para el botón
        //Verificar el texto actual del botón
        if (editBuildingButton.getText().equals("Editar inmueble")) {
            // Si el botón está en modo "Editar datos"
            // Establecer los campos de texto como editables
            titleTextPanel.setEditable(true);
            descriptionTextPanel.setEditable(true);
            streetTextField.setEditable(true);
            cityTextField.setEditable(true);
            numberTextField.setEditable(true);
            cpTextField.setEditable(true);
            priceTextField.setEditable(true);
            guestTextField.setEditable(true);
            roomTextField.setEditable(true);
            bedTextField.setEditable(true);
            bathTextField.setEditable(true);
            serviceTextField.setEditable(true);

            // Cambiar el texto del botón a "Aceptar"
            editBuildingButton.setText("Aceptar");
        } else {

            // Si el botón está en modo "Aceptar"
            // Establecer los campos de texto como no editables
            titleTextPanel.setEditable(false);
            descriptionTextPanel.setEditable(false);
            streetTextField.setEditable(false);
            cityTextField.setEditable(false);
            numberTextField.setEditable(false);
            cpTextField.setEditable(false);
            priceTextField.setEditable(false);
            guestTextField.setEditable(false);
            roomTextField.setEditable(false);
            bedTextField.setEditable(false);
            bathTextField.setEditable(false);
            serviceTextField.setEditable(false);

            editBuildingButton.setText("Editar datos");

            String titulo = titleTextPanel.getText();
            String descripcion = descriptionTextPanel.getText();
            String calle = streetTextField.getText();
            String numero = numberTextField.getText();
            String ciudad = cityTextField.getText();
            String cp = cpTextField.getText();
            String tipo = typeLabel.getText();
            double precio = Double.parseDouble(priceTextField.getText());
            int huespedes = Integer.parseInt(guestTextField.getText());
            int habitaciones = Integer.parseInt(roomTextField.getText());
            int camas = Integer.parseInt(bedTextField.getText());
            int baños = Integer.parseInt(bathTextField.getText());
            String servicios = serviceTextField.getText();

            boolean valido = true;

            if (titulo.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Cada inmueble necesita un título", "Sin título", JOptionPane.WARNING_MESSAGE);
                valido = false;

            } else if (descripcion.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Cada inmueble necesita una descripción.", "Sin descripción", JOptionPane.WARNING_MESSAGE);
                valido = false;
            } else if (!Validacion.validarNombre(ciudad)) {
                JOptionPane.showMessageDialog(this, "Existe algún error con la ciudad, puede que esté vacía o que el formato no sea válido.", "Error con la ciudad", JOptionPane.WARNING_MESSAGE);
                valido = false;
            } else if (!Validacion.validarNombre(calle)) {
                JOptionPane.showMessageDialog(this, "La casilla de la calle del inmueble es necesaria.", "Falta la calle", JOptionPane.WARNING_MESSAGE);
                valido = false;
            }

            //TODO: este numeroo tb me sobra######################################################################################
            boolean numeroo = false;
            int numeroInt = 0;
            if (!numero.isEmpty()) {
                try {
                    numeroInt = Integer.parseInt(numero);
                    numeroo = true;
                } catch (NumberFormatException e) {
                    valido = false;
                    JOptionPane.showMessageDialog(this, "El número del inmueble debe ser un número entero.", "Error de número", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "La casilla del número del inmueble es necesaria.", "Falta el número", JOptionPane.WARNING_MESSAGE);
                valido = false;

            }
            boolean cpp = false;
            int cpInt = 0;
            if (!cp.isEmpty() && cp.length() == 5) {
                try {
                    cpInt = Integer.parseInt(cpTextField.getText());
                    cpp = true;
                } catch (NumberFormatException e) {
                    valido = false;
                    JOptionPane.showMessageDialog(this, "El código postal tiene que ser un número entero.", "Error del código postal", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                valido = false;
                JOptionPane.showMessageDialog(this, "El código postal debe tener 5 carácteres exactamente.", "Error del código postal", JOptionPane.WARNING_MESSAGE);
            }

            if (priceLabel.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "El campo del precio no puede estar vacío.", "Campo de precio vacío", JOptionPane.WARNING_MESSAGE);
                valido = false;
            } else {
                if (precio <= 0) {
                    JOptionPane.showMessageDialog(this, "El precio debe ser mayor a 0.", "Precio menor o igual a 0", JOptionPane.WARNING_MESSAGE);
                    valido = false;
                }
            }

            if (guestTextField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "El campo de huéspedes no puede estar vacío.", "Campo de huéspedes vacío", JOptionPane.WARNING_MESSAGE);
                valido = false;
            } else {
                if (huespedes <= 0) {
                    JOptionPane.showMessageDialog(this, "El máximo de huéspedes debe ser mayor a 0.", "Número de huéspedes menor o igual a 0", JOptionPane.WARNING_MESSAGE);
                    valido = false;
                }
            }

            if (roomTextField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "El campo de habitaciones no puede estar vacío.", "Campo de habitaciones vacío", JOptionPane.WARNING_MESSAGE);
                valido = false;
            } else {
                if (habitaciones <= 0) {
                    JOptionPane.showMessageDialog(this, "El número de habitaciones debe ser mayor a 0.", "Número de habitaciones menor o igual a 0", JOptionPane.WARNING_MESSAGE);
                    valido = false;
                }
            }

            if (bedTextField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "El campo de camas no puede estar vacío.", "Campo de camas vacío", JOptionPane.WARNING_MESSAGE);
                valido = false;
            } else {
                if (camas <= 0) {
                    JOptionPane.showMessageDialog(this, "El número de camas debe ser mayor a 0.", "Número de camas menor o igual a 0", JOptionPane.WARNING_MESSAGE);
                    valido = false;
                }
            }

            if (bathTextField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "El campo de baños no puede estar vacío.", "Campo de baños vacío", JOptionPane.WARNING_MESSAGE);
                valido = false;
            } else {
                if (baños <= 0) {
                    JOptionPane.showMessageDialog(this, "El número de baños debe ser mayor a 0.", "Número de baños menor o igual a 0", JOptionPane.WARNING_MESSAGE);
                    valido = false;
                }
            }

            if (servicios.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Cada inmueble necesita un conjunto de servicios.", "Sin servicios", JOptionPane.WARNING_MESSAGE);
                valido = false;
            } else {
            }

            if (!tipo.equals("Casa") && !tipo.equals("Apartamento")) {
                JOptionPane.showMessageDialog(this, "El tipo solo puede ser 'Casa' o 'Apartamento'.", "Tipo inválido", JOptionPane.WARNING_MESSAGE);
                valido = false;
            }

            if (valido) {
                objInm.setTitulo(titulo);
                objInm.setTipo(tipo);
                objInm.setDescripcion(descripcion);
                objInm.getDireccion().setCalle(calle);
                objInm.getDireccion().setCiudad(ciudad);
                objInm.getDireccion().setNumero(numero);
                objInm.getDireccion().setCp(cp);
                objInm.setPrecioNoche(precio);
                objInm.getDatosInmueble().setMaxHuespedes(huespedes);
                objInm.getDatosInmueble().setHabitaciones(habitaciones);
                objInm.getDatosInmueble().setCamas(camas);
                objInm.getDatosInmueble().setBaños(baños);
                objInm.setServicios(servicios);

                editBuildingButton.setText("Editar inmueble");

                System.out.println(objInm.toString());
            }
        }
    }//GEN-LAST:event_editBuildingButtonActionPerformed

    private void checkReservesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkReservesButtonActionPerformed
        // Establecer el inmueble actual antes de cambiar a la pantalla HostCheckReserves
        setInmuebleActual(objInm);

        System.out.println("Inmueble actual:" + objInm.toString());

        App.loadHostCheckReserves();

     }//GEN-LAST:event_checkReservesButtonActionPerformed

    private void editphotoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editphotoButtonActionPerformed
        File f = openImage();
        if (f != null) {
            fotografia = saveImage(f);
            objInm.setFotografia(fotografia);

        } else {
            System.out.println("no existe la ruta");
        }    }//GEN-LAST:event_editphotoButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel barraarriba1;
    private javax.swing.JLabel bathLabel;
    private javax.swing.JTextField bathTextField;
    private javax.swing.JLabel bedLabel;
    private javax.swing.JTextField bedTextField;
    private javax.swing.JButton checkReservesButton;
    private javax.swing.JLabel cityLabel;
    private javax.swing.JTextField cityTextField;
    private javax.swing.JLabel cpLabel;
    private javax.swing.JTextField cpTextField;
    private javax.swing.JButton deleteBuildingButton;
    private javax.swing.JLabel descriptionLabel;
    private javax.swing.JTextPane descriptionTextPanel;
    private javax.swing.JButton editBuildingButton;
    private javax.swing.JButton editphotoButton;
    private javax.swing.JLabel errorNextLabel;
    private javax.swing.JLabel errorPreviousLabel;
    private javax.swing.JLabel guestLabel;
    private javax.swing.JTextField guestTextField;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JButton logoButton;
    private javax.swing.JLabel logoLabel;
    private javax.swing.JLabel markLabel;
    private javax.swing.JTextPane markTextField;
    private javax.swing.JButton nextButton;
    private javax.swing.JLabel numberLabel;
    private javax.swing.JTextField numberTextField;
    private javax.swing.JButton previousButton;
    private javax.swing.JLabel priceLabel;
    private javax.swing.JTextField priceTextField;
    private javax.swing.JButton returnButton;
    private javax.swing.JLabel roomLabel;
    private javax.swing.JTextField roomTextField;
    private javax.swing.JLabel serviceLabel;
    private javax.swing.JTextPane serviceTextField;
    private javax.swing.JLabel streetLabel;
    private javax.swing.JTextField streetTextField;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JTextPane titleTextPanel;
    private javax.swing.JLabel typeLabel;
    // End of variables declaration//GEN-END:variables
}
