package UI_UX;

import Logica.Inmueble;
import Logica.JavaBNB;
import Logica.Reserva;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ListIterator;
import javax.swing.JFileChooser;

//La clase HostCheckReserves permite al anfitrión consultar y gestionar las reservas de sus inmuebles.
public class HostCheckReserves extends javax.swing.JPanel {

    private ArrayList<Reserva> reservas = new ArrayList<>(); //Referencia al ArrayList de reservas de la clase JavaBNB
    private ListIterator<Reserva> li; //Iterador para recorrer el ArrayList en ambas direcciones
    private Reserva objreserva; //Referencia a un objeto de tipo reserva del ArrayList

    public HostCheckReserves() {
        initComponents();
        errorNoSig.setVisible(false);
        errorNoAnt.setVisible(false);
        fechareserva.setEditable(false);
        fechallegada.setEditable(false);
        fechasalida.setEditable(false);
        name.setEditable(false);
        preciototal.setEditable(false);
        consultarTodo();
    }

    public void actualizar() {
        errorNoSig.setVisible(false);
        errorNoAnt.setVisible(false);
        consultarTodo();
    }

    //Consulta y carga todas las reservas de todos los inmuebles del anfitrión en la lista local, 
    // y se instancia un iterador para recorrerla.
    private void consultarTodo() {
        try {
            errorNoSig.setVisible(false);
            errorNoAnt.setVisible(false);

            if (JavaBNB.getClientes() != null) {
                if (App.hostcheckbuildings != null) {
                    // Obtener el inmueble actual desde HostCheckBuildings
                    Inmueble inmuebleActual = App.hostcheckbuildings.getInmuebleActual();
                    if (inmuebleActual != null) {
                        System.out.println("Inmueble actual: " + inmuebleActual.getTitulo());

                        // Filtrar las reservas para obtener solo las asociadas al inmueble actual
                        ArrayList<Reserva> reservasInmuebleActual = inmuebleActual.getReservas();
                        li = reservasInmuebleActual.listIterator();

                        if (reservasInmuebleActual.size() < 1) {
                            jButtonSig.setEnabled(false);
                            jButtonAnt.setEnabled(false);
                            limpiarCampos();
                            return;
                        } else {
                            jButtonSig.setEnabled(true);
                            jButtonAnt.setEnabled(true);
                        }

                        if (li.hasNext()) {
                            objreserva = li.next();
                        } else {
                            errorNoSig.setVisible(true);
                        }
                        if (objreserva != null) {
                            presenta();
                        } else {
                            limpiarCampos();
                            errorNoSig.setVisible(true);
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
        }
    }

    private void limpiarCampos() {
        fechallegada.setText("");
        fechareserva.setText("");
        fechasalida.setText("");
        name.setText("");
        preciototal.setText("");
        dnilabel.setText("");
        inmueblelabel.setText("");
    }

    private void presenta() {
        DateTimeFormatter formatoCorto = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        fechallegada.setText(objreserva.getFechaInicio().format(formatoCorto));
        fechareserva.setText(objreserva.getFechaReserva().format(formatoCorto));
        fechasalida.setText(objreserva.getFechaFin().format(formatoCorto));
        name.setText(objreserva.getParticular().getNombre());
        preciototal.setText(String.valueOf(objreserva.calcularPrecioTotal()) + "€");
        dnilabel.setText(objreserva.getParticular().getDni());
        inmueblelabel.setText(objreserva.getInmueble().getTitulo());
    }

    //Abre un cuadro de diálogo para seleccionar un directorio. Devuelve el directorio seleccionado, 
    //o null si no se selecciona nada
    public String seleccionarPath() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Seleccione una ruta para guardar su factura");
        fileChooser.setAcceptAllFileFilterUsed(false); // Deshabilitar la opción "Todos los archivos"
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);  // Permitir solo la selección de directorios, no archivos

        int result = fileChooser.showOpenDialog(null); // Mostrar el diálogo de seleccion y capturar la respuesta

        // Procesar la respuesta
        if (result == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile().getAbsolutePath();
        }
        return null; // No file was selected or the user cancelled

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
        logo1 = new javax.swing.JButton();
        titleLabel1 = new javax.swing.JLabel();
        mainscr = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        feLabel = new javax.swing.JLabel();
        frLabel = new javax.swing.JLabel();
        fechallegada = new javax.swing.JTextField();
        fechareserva = new javax.swing.JTextField();
        dnilabel = new javax.swing.JLabel();
        fsLabel = new javax.swing.JLabel();
        fechasalida = new javax.swing.JTextField();
        nLabel = new javax.swing.JLabel();
        name = new javax.swing.JTextField();
        pLabel = new javax.swing.JLabel();
        preciototal = new javax.swing.JTextField();
        recibo = new javax.swing.JButton();
        inmueblelabel = new javax.swing.JTextField();
        jButtonAnt = new javax.swing.JButton();
        jButtonSig = new javax.swing.JButton();
        errorNoSig = new javax.swing.JLabel();
        errorNoAnt = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(255, 250, 248));

        barraarriba1.setBackground(new java.awt.Color(255, 250, 248));
        barraarriba1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        barraarriba1.setLayout(new java.awt.GridBagLayout());

        logo1.setBackground(new java.awt.Color(255, 153, 153));
        logo1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/airbnb logo - 100x100.png"))); // NOI18N
        logo1.setBorderPainted(false);
        logo1.setContentAreaFilled(false);
        logo1.setDefaultCapable(false);
        logo1.setFocusPainted(false);
        logo1.setFocusable(false);
        logo1.setRequestFocusEnabled(false);
        logo1.setRolloverEnabled(false);
        logo1.setVerifyInputWhenFocusTarget(false);
        logo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logo1ActionPerformed(evt);
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
        barraarriba1.add(logo1, gridBagConstraints);

        titleLabel1.setFont(new java.awt.Font("Serif", 3, 24)); // NOI18N
        titleLabel1.setForeground(new java.awt.Color(255, 90, 95));
        titleLabel1.setText("JavaBNB");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(35, 18, 0, 0);
        barraarriba1.add(titleLabel1, gridBagConstraints);

        mainscr.setBackground(new java.awt.Color(255, 90, 95));
        mainscr.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        mainscr.setForeground(new java.awt.Color(255, 255, 255));
        mainscr.setText("Volver");
        mainscr.setBorderPainted(false);
        mainscr.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        mainscr.setPreferredSize(new java.awt.Dimension(80, 30));
        mainscr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mainscrActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 24;
        gridBagConstraints.ipady = 23;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(21, 479, 0, 175);
        barraarriba1.add(mainscr, gridBagConstraints);

        jPanel3.setBackground(new java.awt.Color(255, 250, 248));

        jPanel2.setBackground(new java.awt.Color(255, 250, 248));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel2.setMinimumSize(new java.awt.Dimension(325, 400));
        jPanel2.setPreferredSize(new java.awt.Dimension(325, 400));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        feLabel.setText("Fecha de llegada:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(21, 18, 0, 0);
        jPanel2.add(feLabel, gridBagConstraints);

        frLabel.setText("Fecha de reserva:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(15, 18, 0, 0);
        jPanel2.add(frLabel, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 8;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 104;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 23, 0, 0);
        jPanel2.add(fechallegada, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 8;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 104;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 23, 0, 0);
        jPanel2.add(fechareserva, gridBagConstraints);

        dnilabel.setFont(new java.awt.Font("Serif", 0, 24)); // NOI18N
        dnilabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dnilabel.setText("DNI");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 17;
        gridBagConstraints.ipadx = 252;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(17, 18, 0, 13);
        jPanel2.add(dnilabel, gridBagConstraints);

        fsLabel.setText("Fecha de salida:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(21, 18, 0, 0);
        jPanel2.add(fsLabel, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 8;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 104;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 23, 0, 0);
        jPanel2.add(fechasalida, gridBagConstraints);

        nLabel.setText("Nombre:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(21, 18, 0, 0);
        jPanel2.add(nLabel, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 8;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 104;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 23, 0, 0);
        jPanel2.add(name, gridBagConstraints);

        pLabel.setText("Precio total:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(21, 14, 0, 0);
        jPanel2.add(pLabel, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 8;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 104;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 23, 0, 0);
        jPanel2.add(preciototal, gridBagConstraints);

        recibo.setBackground(new java.awt.Color(255, 102, 102));
        recibo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        recibo.setForeground(new java.awt.Color(255, 255, 255));
        recibo.setText("Descargar recibo");
        recibo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reciboActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.ipadx = 44;
        gridBagConstraints.ipady = 16;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 12, 8, 0);
        jPanel2.add(recibo, gridBagConstraints);

        inmueblelabel.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        inmueblelabel.setText("Inmueble");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 17;
        gridBagConstraints.ipadx = 224;
        gridBagConstraints.ipady = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 24, 0, 13);
        jPanel2.add(inmueblelabel, gridBagConstraints);

        jButtonAnt.setBackground(new java.awt.Color(255, 153, 153));
        jButtonAnt.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jButtonAnt.setForeground(new java.awt.Color(255, 255, 255));
        jButtonAnt.setText("Anterior");
        jButtonAnt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAntActionPerformed(evt);
            }
        });

        jButtonSig.setBackground(new java.awt.Color(255, 153, 153));
        jButtonSig.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jButtonSig.setForeground(new java.awt.Color(255, 255, 255));
        jButtonSig.setText("Siguiente");
        jButtonSig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSigActionPerformed(evt);
            }
        });

        errorNoSig.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        errorNoSig.setForeground(new java.awt.Color(255, 102, 102));
        errorNoSig.setText("No hay una siguiente reserva");

        errorNoAnt.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        errorNoAnt.setForeground(new java.awt.Color(255, 102, 102));
        errorNoAnt.setText("No hay una reserva anterior");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(193, 193, 193)
                        .addComponent(jButtonAnt)
                        .addGap(418, 418, 418)
                        .addComponent(jButtonSig))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(411, 411, 411)
                        .addComponent(errorNoSig))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(422, 422, 422)
                        .addComponent(errorNoAnt))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(273, 273, 273)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 455, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(255, 255, 255))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonAnt)
                    .addComponent(jButtonSig))
                .addGap(6, 6, 6)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(errorNoSig)
                .addGap(6, 6, 6)
                .addComponent(errorNoAnt))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(barraarriba1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(barraarriba1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSigActionPerformed
        if (li.hasNext()) {
            objreserva = li.next();
            errorNoSig.setVisible(false);
            errorNoAnt.setVisible(false);
            presenta();

        } else {
            errorNoSig.setVisible(true);
        }
    }//GEN-LAST:event_jButtonSigActionPerformed

    private void jButtonAntActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAntActionPerformed
        if (li.hasPrevious()) {
            objreserva = li.previous();
            errorNoSig.setVisible(false);
            errorNoAnt.setVisible(false);
            presenta();

        } else {
            errorNoAnt.setVisible(true);
        }
    }//GEN-LAST:event_jButtonAntActionPerformed

    private void logo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logo1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_logo1ActionPerformed

    private void mainscrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mainscrActionPerformed
        HostCheckBuildings hcb = new HostCheckBuildings();
        hcb.setInmuebleActual(null);
        System.out.println("Estas observando el inmueble: " + hcb.getInmuebleActual());

        App.loadMainScreen();

    }//GEN-LAST:event_mainscrActionPerformed

    private void reciboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reciboActionPerformed
        try {
            String path = seleccionarPath();
            objreserva.generaFicha(path);
            System.out.println("GUARDADA FACTURA EN" + path);
        } catch (IOException ioe) {
            System.out.println("Error de IO: " + ioe.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }//GEN-LAST:event_reciboActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel barraarriba1;
    private javax.swing.JLabel dnilabel;
    private javax.swing.JLabel errorNoAnt;
    private javax.swing.JLabel errorNoSig;
    private javax.swing.JLabel feLabel;
    private javax.swing.JTextField fechallegada;
    private javax.swing.JTextField fechareserva;
    private javax.swing.JTextField fechasalida;
    private javax.swing.JLabel frLabel;
    private javax.swing.JLabel fsLabel;
    private javax.swing.JTextField inmueblelabel;
    private javax.swing.JButton jButtonAnt;
    private javax.swing.JButton jButtonSig;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JButton logo1;
    private javax.swing.JButton mainscr;
    private javax.swing.JLabel nLabel;
    private javax.swing.JTextField name;
    private javax.swing.JLabel pLabel;
    private javax.swing.JTextField preciototal;
    private javax.swing.JButton recibo;
    private javax.swing.JLabel titleLabel1;
    // End of variables declaration//GEN-END:variables
}
