package UI_UX;

import Logica.Anfitrion;
import Logica.Cliente;
import Logica.Inmueble;
import Logica.JavaBNB;
import Logica.Particular;
import Logica.Reserva;
import Logica.Validacion;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import javax.swing.JOptionPane;

//La clase AdminCheckUser permite al administrador consultar y gestionar los usuarios registrados en la aplicación.
public class AdminCheckUser extends javax.swing.JPanel {

    private ArrayList<Cliente> clientesaux; // Referencia al ArrayList de personas de la clase JavaBNB
    private ListIterator<Cliente> li; // Iterador para recorrer el ArrayList en ambas direcciones
    private Cliente objcli; // Referencia a un objeto de tipo cliente del ArrayList

    public AdminCheckUser() {
        initComponents();
        errorNextLabel.setVisible(false);
        errorPreviousLabel.setVisible(false);
        nameTextField.setEditable(false);
        dniTextField.setEditable(false);
        emailTextField.setEditable(false);
        passTextField.setEditable(false);
        tlfTextField.setEditable(false);
        consultarTodo();
    }

    public void actualizar() {
        errorNextLabel.setVisible(false);
        errorPreviousLabel.setVisible(false);
        consultarTodo();
    }

    //Consulta y carga todos los usuarios de JavaBNB en la lista local.
    private void consultarTodo() {
        try {
            errorNextLabel.setVisible(false);
            errorPreviousLabel.setVisible(false);

            if (JavaBNB.getClientes() != null) {
                clientesaux = JavaBNB.getClientes();

                li = clientesaux.listIterator();
                if (clientesaux.size() < 1) {
                    nextButton.setEnabled(false);
                    previousButton.setEnabled(false);
                    deleteUserButton.setEnabled(false);
                    editUserButton.setEnabled(false);
                    return;
                } else {
                    nextButton.setEnabled(true);
                    previousButton.setEnabled(true);
                    deleteUserButton.setEnabled(true);
                    editUserButton.setEnabled(true);
                }

                if (li.hasNext()) {
                    objcli = li.next();
                } else {
                    errorNextLabel.setVisible(true);
                }
                if (objcli != null) {
                    presenta(objcli);
                } else {
                    errorNextLabel.setVisible(true);
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
        }
    }

    private void presenta(Cliente per) {
        String tipo = per.getClass().getSimpleName();
        dniTextField.setText(objcli.getDni());
        nameTextField.setText(objcli.getNombre());
        emailTextField.setText(objcli.getCorreo());
        passTextField.setText(objcli.getClave());
        tlfTextField.setText(objcli.getTelefono());
        if (tipo.equals("Anfitrion")) {
            typeLabel.setText(tipo);
        } else {
            typeLabel.setText(tipo);
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

        jPanel1 = new javax.swing.JPanel();
        barraarriba1 = new javax.swing.JPanel();
        logoButton = new javax.swing.JButton();
        logoLabel = new javax.swing.JLabel();
        returnButton = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        dniLabel = new javax.swing.JLabel();
        nameLabel = new javax.swing.JLabel();
        dniTextField = new javax.swing.JTextField();
        nameTextField = new javax.swing.JTextField();
        typeLabel = new javax.swing.JLabel();
        emailLabel = new javax.swing.JLabel();
        emailTextField = new javax.swing.JTextField();
        passLabel = new javax.swing.JLabel();
        passTextField = new javax.swing.JTextField();
        tlfLabel = new javax.swing.JLabel();
        tlfTextField = new javax.swing.JTextField();
        deleteUserButton = new javax.swing.JButton();
        editUserButton = new javax.swing.JButton();
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
        jPanel2.setMinimumSize(new java.awt.Dimension(325, 400));
        jPanel2.setPreferredSize(new java.awt.Dimension(325, 400));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        dniLabel.setText("DNI:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(21, 16, 0, 0);
        jPanel2.add(dniLabel, gridBagConstraints);

        nameLabel.setText("Nombre:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(28, 16, 0, 0);
        jPanel2.add(nameLabel, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 144;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 12, 0, 38);
        jPanel2.add(dniTextField, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 144;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(25, 12, 0, 38);
        jPanel2.add(nameTextField, gridBagConstraints);

        typeLabel.setFont(new java.awt.Font("Serif", 0, 24)); // NOI18N
        typeLabel.setText("var1");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 118;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 27, 0, 0);
        jPanel2.add(typeLabel, gridBagConstraints);

        emailLabel.setText("Correo:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(21, 16, 0, 0);
        jPanel2.add(emailLabel, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 144;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 12, 0, 38);
        jPanel2.add(emailTextField, gridBagConstraints);

        passLabel.setText("Clave:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(21, 16, 0, 0);
        jPanel2.add(passLabel, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 144;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 12, 0, 38);
        jPanel2.add(passTextField, gridBagConstraints);

        tlfLabel.setText("Telefono:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(21, 12, 0, 0);
        jPanel2.add(tlfLabel, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 144;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 12, 0, 38);
        jPanel2.add(tlfTextField, gridBagConstraints);

        deleteUserButton.setBackground(new java.awt.Color(255, 153, 153));
        deleteUserButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        deleteUserButton.setForeground(new java.awt.Color(255, 255, 255));
        deleteUserButton.setText("Eliminar usuario");
        deleteUserButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteUserButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.insets = new java.awt.Insets(17, 25, 0, 0);
        jPanel2.add(deleteUserButton, gridBagConstraints);

        editUserButton.setBackground(new java.awt.Color(255, 90, 95));
        editUserButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        editUserButton.setForeground(new java.awt.Color(255, 255, 255));
        editUserButton.setText("Editar usuario");
        editUserButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editUserButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.insets = new java.awt.Insets(17, 25, 0, 0);
        jPanel2.add(editUserButton, gridBagConstraints);

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
        errorNextLabel.setText("No hay un siguiente usuario");

        errorPreviousLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        errorPreviousLabel.setForeground(new java.awt.Color(255, 102, 102));
        errorPreviousLabel.setText("No hay un usuario anterior");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(193, 193, 193)
                        .addComponent(previousButton)
                        .addGap(54, 54, 54)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(nextButton))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(395, 395, 395)
                        .addComponent(errorNextLabel))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(401, 401, 401)
                        .addComponent(errorPreviousLabel)))
                .addGap(255, 255, 255))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(previousButton)
                            .addComponent(nextButton)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(38, 38, 38)
                .addComponent(errorNextLabel)
                .addGap(6, 6, 6)
                .addComponent(errorPreviousLabel))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(barraarriba1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(barraarriba1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void nextButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextButtonActionPerformed
        if (li.hasNext()) {
            objcli = li.next();
            errorNextLabel.setVisible(false);
            errorPreviousLabel.setVisible(false);
            presenta(objcli);

        } else {
            errorNextLabel.setVisible(true);
        }
    }//GEN-LAST:event_nextButtonActionPerformed

    private void previousButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previousButtonActionPerformed
        if (li.hasPrevious()) {
            objcli = li.previous();
            errorNextLabel.setVisible(false);
            errorPreviousLabel.setVisible(false);
            presenta(objcli);

        } else {
            errorPreviousLabel.setVisible(true);
        }
    }//GEN-LAST:event_previousButtonActionPerformed

    private void logoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_logoButtonActionPerformed

    private void deleteUserButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteUserButtonActionPerformed
        if (objcli != null) {
            if (objcli instanceof Particular) {
                Particular particular = (Particular) objcli;
                JavaBNB.eliminarParticular(objcli);

                // Tengo que eliminar las reservas asociadas a ese particular
                ArrayList<Inmueble> inmuebles = JavaBNB.getInmuebles();
                for (Inmueble i : inmuebles) {
                    ArrayList<Reserva> reservas = i.getReservas();
                    Iterator<Reserva> iterator = reservas.iterator();
                    while (iterator.hasNext()) {
                        Reserva reserva = iterator.next();
                        if (reserva.getParticular().getDni().equals(objcli.getDni())) {
                            iterator.remove();
                        }
                    }
                }

            } else if (objcli instanceof Anfitrion) {
                Anfitrion anfitrion = (Anfitrion) objcli;
                ArrayList<Inmueble> inmuebles = JavaBNB.getInmuebles();

                JavaBNB.eliminarAnfitrion(objcli);

                // Aquí se deberían eliminar solo los inmuebles del anfitrión, no todos
                Iterator<Inmueble> iterator = inmuebles.iterator();
                while (iterator.hasNext()) {
                    Inmueble inmueble = iterator.next();
                    if (inmueble.getAnfitrion().equals(anfitrion)) {
                        iterator.remove();
                    }
                }
            }

            try {
                // Eliminar el cliente de la lista
                li.remove();

                // Guardar los datos actualizados
                JavaBNB.guardarDatos();

                // Actualizar las vistas
                App.adminconsultarreservas.actualizar();
                App.admincheckbuildings.actualizar();
                App.adminconsultaruser.actualizar();
            } catch (Exception e) {
                System.out.println("Está vacío, no se puede eliminar más");
            }
        }


    }//GEN-LAST:event_deleteUserButtonActionPerformed

    private void returnButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_returnButtonActionPerformed
        App.cardLayout.show(App.cards, "Pantalla adminscreen");
    }//GEN-LAST:event_returnButtonActionPerformed

    private void editUserButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editUserButtonActionPerformed
        // Verificar el texto actual del botón
        if (editUserButton.getText().equals("Editar datos")) {
            // Si el botón está en modo "Editar datos"
            // Establecer los campos de texto como editables
            emailTextField.setEditable(true);
            passTextField.setEditable(true);
            tlfTextField.setEditable(true);

            // Cambiar el texto del botón a "Aceptar"
            editUserButton.setText("Aceptar");
        } else {
            // Si el botón está en modo "Aceptar"
            // Establecer los campos de texto como no editables
            emailTextField.setEditable(false);
            passTextField.setEditable(false);
            tlfTextField.setEditable(false);

            // Cambiar el texto del botón a "Editar datos"
            editUserButton.setText("Editar datos");

            // Verificar la validez de los datos ingresados
            String email = emailTextField.getText();
            String password = passTextField.getText();
            String telefono = tlfTextField.getText();

            boolean datosValidos = true;

            // Verificar el correo electrónico
            if (!Validacion.validarEmail(email)) {
                JOptionPane.showMessageDialog(this, "El email introducido no es válido.", "Email inválido", JOptionPane.WARNING_MESSAGE);
                emailTextField.setText("");
                datosValidos = false;
            } // Verificar la contraseña
            else if (!Validacion.validarContraseña(password)) {
                JOptionPane.showMessageDialog(this, "La contraseña introducida no es válida. \n Revise los requisitos.", "Contraseña inválida", JOptionPane.WARNING_MESSAGE);
                passTextField.setText("");
                datosValidos = false;
            } // Verificar el teléfono
            else if (!Validacion.validarTelefono(telefono)) {
                JOptionPane.showMessageDialog(this, "El teléfono introducido no es válido. \n Debe empezar por 6/7/9 y contener 9 números.", "Teléfono inválido", JOptionPane.WARNING_MESSAGE);
                tlfTextField.setText("");
                datosValidos = false;

            }

            // Si todos los datos son válidos, guardar los cambios
            if (datosValidos) {
                objcli.setCorreo(email);
                objcli.setTelefono(telefono);
                objcli.setClave(password);

                editUserButton.setText("Editar datos");

                System.out.println(objcli);
            } else {
            }
        }

    }//GEN-LAST:event_editUserButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel barraarriba1;
    private javax.swing.JButton deleteUserButton;
    private javax.swing.JLabel dniLabel;
    private javax.swing.JTextField dniTextField;
    private javax.swing.JButton editUserButton;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JTextField emailTextField;
    private javax.swing.JLabel errorNextLabel;
    private javax.swing.JLabel errorPreviousLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JButton logoButton;
    private javax.swing.JLabel logoLabel;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JButton nextButton;
    private javax.swing.JLabel passLabel;
    private javax.swing.JTextField passTextField;
    private javax.swing.JButton previousButton;
    private javax.swing.JButton returnButton;
    private javax.swing.JLabel tlfLabel;
    private javax.swing.JTextField tlfTextField;
    private javax.swing.JLabel typeLabel;
    // End of variables declaration//GEN-END:variables
}
