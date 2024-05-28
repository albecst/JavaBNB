package UI_UX;

import Logica.Particular;
import Logica.Sesion;
import Logica.Tarjeta;
import Logica.Validacion;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class ClientProfile extends javax.swing.JPanel {

    static String fotografia; // La fotografía de usuario mostrada
    public Particular particular; // El particular actual de la sesión

    public ClientProfile() {
        initComponents();
        errorLabel1.setVisible(false);
        requirementsLabel.setVisible(false);
        emailTextField.setEditable(false);
        passPasswordField.setEditable(false);
        tlfTextField.setEditable(false);
        promocodeTextField.setEditable(false);
        CCTextField.setEditable(false);
        dayTextField.setEditable(false);
        monthTextField.setEditable(false);
        yearTextField.setEditable(false);
        moneyTextField.setEditable(false);
        cvvTextField.setEditable(false);
    }

    public void actualizar() {
        if (Sesion.user != null) {
            if (Sesion.user.getFotoperfil() != null) {
                fotografia = Sesion.user.getFotoperfil();
                photoButton.setIcon(resizeIMG(fotografia));
            } else {
                fotografia = null;
                photoButton.setIcon(resizeIMG("/images/user (2).jpg"));
            }
        }
        // Establecer los campos de texto con la información del usuario actual
        nameTextField.setText(Sesion.user.getNombre());
        dniTextField.setText(Sesion.user.getDni());
        usernameLabel.setText(Sesion.user.getNombre().toUpperCase());
        emailTextField.setText(Sesion.user.getCorreo());
        passPasswordField.setText(Sesion.user.getClave());
        tlfTextField.setText(Sesion.user.getTelefono());
        if (Sesion.esUsuarioVip()) {
            promocodeTextField.setText("JAVABNB2024");
        } else {
            promocodeTextField.setText("                    ");
        }

        Tarjeta tarjeta = ((Particular) Sesion.user).getTarjetaCredito();
        if (tarjeta != null) {
            CCTextField.setText(tarjeta.getNumeroTarjeta());
            LocalDate fechaCaducidad = tarjeta.getFechaCaducidad();
            dayTextField.setText(String.valueOf(fechaCaducidad.getDayOfMonth()));
            monthTextField.setText(String.valueOf(fechaCaducidad.getMonthValue()));
            yearTextField.setText(String.valueOf(fechaCaducidad.getYear()));
            cvvTextField.setText(tarjeta.getCvv());
            moneyTextField.setText(String.valueOf(tarjeta.getSaldo()));
        }
    }

    /**
     * Método para poder cambiar la foto de perfil
     *
     * @return
     */
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

    /**
     * Método para guardar la imagen introducida en los archivos del proyecto,
     * en la carpeta "fotosperfil".
     *
     * @param archivofoto el File de la imagen a guardar.
     * @return el String con la ruta a la imagen guardada.
     */
    public String saveImage(File archivofoto) {
        String directoriodestino = "./src/main/resources/fotosperfil"; // Directorio de destino fijo
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
            int width = 306;
            int height = 307;
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
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel2 = new javax.swing.JPanel();
        uppermenu = new javax.swing.JPanel();
        logoLabel = new javax.swing.JLabel();
        logo = new javax.swing.JButton();
        logoButton = new javax.swing.JButton();
        returnButton = new javax.swing.JButton();
        signOutButton = new javax.swing.JButton();
        addMoneyButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        data = new javax.swing.JPanel();
        emailLabel = new javax.swing.JLabel();
        dniLabel = new javax.swing.JLabel();
        tlfLabel = new javax.swing.JLabel();
        passLabel = new javax.swing.JLabel();
        passPasswordField = new javax.swing.JPasswordField();
        editButton = new javax.swing.JButton();
        emailTextField = new javax.swing.JTextField();
        dniTextField = new javax.swing.JTextField();
        tlfTextField = new javax.swing.JTextField();
        errorLabel1 = new javax.swing.JLabel();
        requirementsLabel = new javax.swing.JLabel();
        promocodeLabel = new javax.swing.JLabel();
        promocodeTextField = new javax.swing.JTextField();
        CCLabel = new javax.swing.JLabel();
        CCTextField = new javax.swing.JTextField();
        dayLabel = new javax.swing.JLabel();
        monthLabel = new javax.swing.JLabel();
        yearLabel = new javax.swing.JLabel();
        dayTextField = new javax.swing.JTextField();
        monthTextField = new javax.swing.JTextField();
        cvvTextField = new javax.swing.JTextField();
        cvvLabel = new javax.swing.JLabel();
        yearTextField = new javax.swing.JTextField();
        moneyLabel = new javax.swing.JLabel();
        moneyTextField = new javax.swing.JTextField();
        nameTextField = new javax.swing.JTextField();
        nameLabel = new javax.swing.JLabel();
        photoButton = new javax.swing.JButton();
        usernameLabel = new javax.swing.JLabel();
        typeLabel = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 250, 248));
        setLayout(new java.awt.BorderLayout());

        jPanel2.setBackground(new java.awt.Color(255, 250, 248));

        uppermenu.setBackground(new java.awt.Color(255, 250, 248));
        uppermenu.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        uppermenu.setLayout(new java.awt.GridBagLayout());

        logoLabel.setFont(new java.awt.Font("Serif", 0, 24)); // NOI18N
        logoLabel.setForeground(new java.awt.Color(255, 90, 95));
        logoLabel.setText("JavaBNB");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(30, 18, 0, 0);
        uppermenu.add(logoLabel, gridBagConstraints);

        logo.setBackground(new java.awt.Color(255, 153, 153));
        logo.setBorderPainted(false);
        logo.setContentAreaFilled(false);
        logo.setDefaultCapable(false);
        logo.setFocusPainted(false);
        logo.setFocusable(false);
        logo.setRequestFocusEnabled(false);
        logo.setRolloverEnabled(false);
        logo.setVerifyInputWhenFocusTarget(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 0, 0);
        uppermenu.add(logo, gridBagConstraints);

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
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = -31;
        gridBagConstraints.ipady = -24;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(7, 101, 3, 0);
        uppermenu.add(logoButton, gridBagConstraints);

        returnButton.setBackground(new java.awt.Color(255, 90, 95));
        returnButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
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
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 16;
        gridBagConstraints.ipady = 16;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(23, 68, 0, 50);
        uppermenu.add(returnButton, gridBagConstraints);

        signOutButton.setBackground(new java.awt.Color(255, 90, 95));
        signOutButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        signOutButton.setForeground(new java.awt.Color(255, 255, 255));
        signOutButton.setText("Cerrar sesión");
        signOutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signOutButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 16;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(23, 611, 0, 0);
        uppermenu.add(signOutButton, gridBagConstraints);

        addMoneyButton.setBackground(new java.awt.Color(255, 90, 95));
        addMoneyButton.setForeground(new java.awt.Color(255, 255, 255));
        addMoneyButton.setText("Añadir dinero");
        addMoneyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addMoneyButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 16;
        gridBagConstraints.ipady = 16;
        gridBagConstraints.insets = new java.awt.Insets(23, 268, 0, 0);
        uppermenu.add(addMoneyButton, gridBagConstraints);

        jPanel1.setBackground(new java.awt.Color(255, 250, 248));

        data.setBackground(new java.awt.Color(255, 250, 248));
        data.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        data.setMaximumSize(new java.awt.Dimension(306, 307));
        data.setPreferredSize(new java.awt.Dimension(306, 400));
        data.setRequestFocusEnabled(false);
        data.setLayout(new java.awt.GridBagLayout());

        emailLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        emailLabel.setText("Correo electrónico:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 14;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 21, 0, 0);
        data.add(emailLabel, gridBagConstraints);

        dniLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        dniLabel.setText("DNI:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 21, 0, 0);
        data.add(dniLabel, gridBagConstraints);

        tlfLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tlfLabel.setText("Teléfono:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 21, 0, 0);
        data.add(tlfLabel, gridBagConstraints);

        passLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        passLabel.setText("Contraseña:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 20;
        gridBagConstraints.gridwidth = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 21, 0, 0);
        data.add(passLabel, gridBagConstraints);

        passPasswordField.setText("contraseña1");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 20;
        gridBagConstraints.gridwidth = 191;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 79;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 125, 0, 0);
        data.add(passPasswordField, gridBagConstraints);

        editButton.setBackground(new java.awt.Color(255, 90, 95));
        editButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        editButton.setForeground(new java.awt.Color(255, 255, 255));
        editButton.setText("Editar datos");
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 23;
        gridBagConstraints.ipadx = 18;
        gridBagConstraints.ipady = 11;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(40, 67, 0, 0);
        data.add(editButton, gridBagConstraints);

        emailTextField.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 422;
        gridBagConstraints.ipadx = 40;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 144, 0, 0);
        data.add(emailTextField, gridBagConstraints);

        dniTextField.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 44;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 19;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 60, 0, 0);
        data.add(dniTextField, gridBagConstraints);

        tlfTextField.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 96;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 32;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 95, 0, 0);
        data.add(tlfTextField, gridBagConstraints);

        errorLabel1.setBackground(new java.awt.Color(255, 90, 95));
        errorLabel1.setForeground(new java.awt.Color(255, 90, 95));
        errorLabel1.setText("Alguno de los datos no cumple el formato requerido, o no es válido");
        errorLabel1.setEnabled(false);
        errorLabel1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                errorLabel1PropertyChange(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 24;
        gridBagConstraints.gridwidth = 478;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(16, 33, 62, 53);
        data.add(errorLabel1, gridBagConstraints);

        requirementsLabel.setFont(new java.awt.Font("Segoe UI", 2, 8)); // NOI18N
        requirementsLabel.setForeground(new java.awt.Color(102, 102, 102));
        requirementsLabel.setText("Al menos una letra mayúscula, minúscula, dígitos y 8 caracteres de longitud");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 22;
        gridBagConstraints.gridwidth = 477;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 38, 0, 0);
        data.add(requirementsLabel, gridBagConstraints);

        promocodeLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        promocodeLabel.setText("Código promocional:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 57;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 21, 0, 0);
        data.add(promocodeLabel, gridBagConstraints);

        promocodeTextField.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 20;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 81, 0, 0);
        data.add(promocodeTextField, gridBagConstraints);

        CCLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        CCLabel.setText("Tarjeta de crédito:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 19;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 21, 0, 0);
        data.add(CCLabel, gridBagConstraints);

        CCTextField.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 433;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 152;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 70, 0, 0);
        data.add(CCTextField, gridBagConstraints);

        dayLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        dayLabel.setText("Día:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 21, 0, 0);
        data.add(dayLabel, gridBagConstraints);

        monthLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        monthLabel.setText("Mes:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 21, 0, 0);
        data.add(monthLabel, gridBagConstraints);

        yearLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        yearLabel.setText("Año:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 21, 0, 0);
        data.add(yearLabel, gridBagConstraints);

        dayTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dayTextFieldActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 10;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 9, 0, 0);
        data.add(dayTextField, gridBagConstraints);

        monthTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                monthTextFieldActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.gridwidth = 10;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 9, 0, 0);
        data.add(monthTextField, gridBagConstraints);

        cvvTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cvvTextFieldActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 16;
        gridBagConstraints.gridwidth = 25;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 12, 0, 0);
        data.add(cvvTextField, gridBagConstraints);

        cvvLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cvvLabel.setText("CVV:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 16;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 21, 0, 0);
        data.add(cvvLabel, gridBagConstraints);

        yearTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yearTextFieldActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.gridwidth = 25;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 12, 0, 0);
        data.add(yearTextField, gridBagConstraints);

        moneyLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        moneyLabel.setText("Saldo:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 18;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 21, 0, 0);
        data.add(moneyLabel, gridBagConstraints);

        moneyTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moneyTextFieldActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 17;
        gridBagConstraints.gridwidth = 25;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 12, 0, 0);
        data.add(moneyTextField, gridBagConstraints);

        nameTextField.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 43;
        gridBagConstraints.insets = new java.awt.Insets(43, 10, 0, 0);
        data.add(nameTextField, gridBagConstraints);

        nameLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        nameLabel.setText("Nombre: ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(43, 21, 0, 0);
        data.add(nameLabel, gridBagConstraints);

        photoButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/user (2).jpg"))); // NOI18N
        photoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                photoButtonActionPerformed(evt);
            }
        });

        usernameLabel.setFont(new java.awt.Font("Serif", 0, 30)); // NOI18N
        usernameLabel.setForeground(new java.awt.Color(255, 90, 95));
        usernameLabel.setText("XXXXXX");

        typeLabel.setFont(new java.awt.Font("Serif", 0, 24)); // NOI18N
        typeLabel.setForeground(new java.awt.Color(102, 102, 102));
        typeLabel.setText("Cliente");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(photoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addComponent(usernameLabel))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(102, 102, 102)
                        .addComponent(typeLabel)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                .addComponent(data, javax.swing.GroupLayout.PREFERRED_SIZE, 446, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(216, 216, 216))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(photoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(usernameLabel)
                        .addGap(14, 14, 14)
                        .addComponent(typeLabel))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(data, javax.swing.GroupLayout.PREFERRED_SIZE, 561, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(131, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(uppermenu, javax.swing.GroupLayout.PREFERRED_SIZE, 1231, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(uppermenu, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(jPanel2, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void photoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_photoButtonActionPerformed
        File f = openImage();
        if (f != null) {
            fotografia = saveImage(f);
            Sesion.user.setFotoperfil(fotografia);
            actualizar();
            System.out.println(fotografia);
        } else {
            System.out.println("no existe la ruta");
        }
    }//GEN-LAST:event_photoButtonActionPerformed

    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
        if (editButton.getText().equals("Editar datos")) {
            // Si el botón está en modo "Editar datos", establece los campos de texto como editables
            emailTextField.setEditable(true);
            passPasswordField.setEditable(true);
            tlfTextField.setEditable(true);
            nameTextField.setEditable(true);

            if (Sesion.esUsuarioVip()) {

                promocodeTextField.setEditable(false);
            } else {
                promocodeTextField.setEditable(true);
            }
            // Cambiar el texto del botón a "Aceptar"
            editButton.setText("Aceptar");
        } else {
            // Si el botón está en modo "Aceptar", establece los campos de texto como no editables
            emailTextField.setEditable(false);
            passPasswordField.setEditable(false);
            tlfTextField.setEditable(false);
            promocodeTextField.setEditable(false);
            nameTextField.setEditable(false);

            editButton.setText("Editar datos");

            String nombre = nameTextField.getText();
            String email = emailTextField.getText();
            char[] passwordCharArray = passPasswordField.getPassword();
            String password = new String(passwordCharArray);
            String telefono = tlfTextField.getText();
            String promocode = promocodeTextField.getText();

            boolean valido = true;

            // Verificar el nombre
            if (!Validacion.validarNombre(nombre)) {
                errorLabel1.setVisible(true);
                nameTextField.setText("");
                valido = false;
            } // Verificar el correo electrónico
            else if (!Validacion.validarEmail(email)) {
                errorLabel1.setVisible(true);
                emailTextField.setText("");
                valido = false;
            } // Verificar la contraseña            
            else if (!Validacion.validarContraseña(password)) {
                errorLabel1.setVisible(true);
                passPasswordField.setText("");
                valido = false;
            } // Verificar el teléfono
            else if (!Validacion.validarTelefono(telefono)) {
                errorLabel1.setVisible(true);
                tlfTextField.setText("");
                valido = false;
            }

            // Verificar el código promocional
            if (!Validacion.validarPromocode(promocode)) {
                errorLabel1.setVisible(true);
                promocodeTextField.setText("");
                valido = false;
            } else {
                errorLabel1.setVisible(false);
            }

            if (valido) {
                errorLabel1.setVisible(false);

                Sesion.user.setNombre(nombre);
                usernameLabel.setText(nombre);

                Sesion.user.setCorreo(email);
                Sesion.user.setTelefono(telefono);
                Sesion.user.setClave(password);

                // Verificar y actualizar el estado VIP
                if (Sesion.hacerVipSiPromocodeValido(promocode)) {
                    System.out.println("El usuario ha sido actualizado a VIP.");
                }

                System.out.println(Sesion.user.toString());
            } else {
                errorLabel1.setVisible(true);
            }
        }


    }//GEN-LAST:event_editButtonActionPerformed

    private void errorLabel1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_errorLabel1PropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_errorLabel1PropertyChange

    private void dayTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dayTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dayTextFieldActionPerformed

    private void monthTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_monthTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_monthTextFieldActionPerformed

    private void cvvTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cvvTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cvvTextFieldActionPerformed

    private void yearTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yearTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_yearTextFieldActionPerformed

    private void moneyTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moneyTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_moneyTextFieldActionPerformed

    private void returnButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_returnButtonActionPerformed
        App.loadMainScreen();
    }//GEN-LAST:event_returnButtonActionPerformed

    private void signOutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signOutButtonActionPerformed
        Sesion.cerrarSesion();
        App.cardLayout.show(App.cards, "Pantalla login");
    }//GEN-LAST:event_signOutButtonActionPerformed

    private void addMoneyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addMoneyButtonActionPerformed
        // Verifica si el usuario actual es un Particular
        if (Sesion.user instanceof Particular) {
            Particular particular = (Particular) Sesion.user;
            Tarjeta tarjeta = particular.getTarjetaCredito();
            try {
                // Verifica si el Particular tiene una tarjeta de crédito asociada 
                if (tarjeta != null) {
                    double saldoañadido;
                    String notaIntroducida = JOptionPane.showInputDialog(this, "Introduzca la cantidad de dinero a añadir:");
                    saldoañadido = Double.parseDouble(notaIntroducida);

                    // Incrementa el saldo de la tarjeta
                    tarjeta.incrementarSaldo(saldoañadido);
                    moneyTextField.setText(String.valueOf(tarjeta.getSaldo()));
                    // Actualiza la interfaz de usuario para mostrar el nuevo saldo (si tienes una etiqueta o campo de texto para el saldo)

                    System.out.println("Saldo añadido. Nuevo saldo: " + tarjeta.getSaldo());
                } else {
                    System.out.println("El usuario no tiene una tarjeta de crédito asociada.");
                }

            } catch (NumberFormatException nfe) {
                System.out.println("Error del formato: " + nfe.getMessage());
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());

            }
        } else {
            System.out.println("El usuario actual no es un cliente particular.");
        }


    }//GEN-LAST:event_addMoneyButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel CCLabel;
    private javax.swing.JTextField CCTextField;
    private javax.swing.JButton addMoneyButton;
    private javax.swing.JLabel cvvLabel;
    private javax.swing.JTextField cvvTextField;
    private javax.swing.JPanel data;
    private javax.swing.JLabel dayLabel;
    private javax.swing.JTextField dayTextField;
    private javax.swing.JLabel dniLabel;
    private javax.swing.JTextField dniTextField;
    private javax.swing.JButton editButton;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JTextField emailTextField;
    private javax.swing.JLabel errorLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton logo;
    private javax.swing.JButton logoButton;
    private javax.swing.JLabel logoLabel;
    private javax.swing.JLabel moneyLabel;
    private javax.swing.JTextField moneyTextField;
    private javax.swing.JLabel monthLabel;
    private javax.swing.JTextField monthTextField;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JLabel passLabel;
    private javax.swing.JPasswordField passPasswordField;
    private javax.swing.JButton photoButton;
    private javax.swing.JLabel promocodeLabel;
    private javax.swing.JTextField promocodeTextField;
    private javax.swing.JLabel requirementsLabel;
    private javax.swing.JButton returnButton;
    private javax.swing.JButton signOutButton;
    private javax.swing.JLabel tlfLabel;
    private javax.swing.JTextField tlfTextField;
    private javax.swing.JLabel typeLabel;
    private javax.swing.JPanel uppermenu;
    private javax.swing.JLabel usernameLabel;
    private javax.swing.JLabel yearLabel;
    private javax.swing.JTextField yearTextField;
    // End of variables declaration//GEN-END:variables
}
