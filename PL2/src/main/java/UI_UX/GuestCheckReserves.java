package UI_UX;

import Logica.Particular;
import Logica.Reserva;
import Logica.Sesion;
import java.util.ArrayList;

public class GuestCheckReserves extends javax.swing.JPanel {

    public ArrayList<ReservasView> reservasicon;
    public Particular particular;

    /**
     * Creates new form UserProfile
     */
    public GuestCheckReserves() {
        initComponents();
    }

    public void actualizar() {
        if (Sesion.user != null && !Sesion.esAnfitrion) {
            reservasicon = new ArrayList<>();
            insertarReservas();
        }
    }

    public void insertarReservas() {
        deleteReservas(); // Borra cualquier widget de reserva existente antes de insertar nuevos

        int fila = 20; // Ajusta el valor inicial de fila
        int x = 20; // Ajusta el valor inicial de x
        int separacionHorizontal = 300; // Ajusta la separación horizontal entre reservas
        int separacionVertical = 300; // Ajusta la separación vertical entre filas de reservas
        int reservaWidth = 225;
        int reservaHeight = 229;

        for (Reserva reserva : (((Particular) Sesion.user).getReservas())) {
            if (x + reservaWidth >= reservasContainer.getWidth()) { // Ajusta el valor de reservaWidth según tus necesidades
                fila += reservaHeight + separacionVertical; // Ajusta el valor de reservaHeight según tus necesidades
                x = 20; // Reinicia x al inicio de la fila
            }

            ReservasView iconoreserva = new ReservasView();
            iconoreserva.init(reserva);

            reservasicon.add(iconoreserva);
            reservasContainer.add(iconoreserva, new org.netbeans.lib.awtextra.AbsoluteConstraints(x, fila, -1, -1));

            x += reservaWidth + separacionHorizontal; // Ajusta el valor de reservaWidth según tus necesidades
        }

        reservasContainer.revalidate(); // Actualiza el contenedor para mostrar los cambios
        reservasContainer.repaint();   // Repinta el contenedor para asegurar que los cambios sean visibles
    }

    public void deleteReservas() {
        for (ReservasView rv : reservasicon) {
            this.reservasicon.remove(rv);
        }
        reservasicon.clear(); // Limpia la lista de widgets de edificios después de eliminarlos
        reservasContainer.revalidate(); // Actualiza el contenedor para mostrar los cambios
        reservasContainer.repaint();   // Repinta el contenedor para asegurar que los cambios sean visibles
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        barraarriba1 = new javax.swing.JPanel();
        logoButton = new javax.swing.JButton();
        logoLabel = new javax.swing.JLabel();
        returnButton = new javax.swing.JButton();
        reservasContainer = new javax.swing.JPanel();
        reservasLabel1 = new javax.swing.JLabel();
        reservasLabel = new javax.swing.JLabel();

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

        reservasContainer.setBackground(new java.awt.Color(255, 250, 248));
        reservasContainer.setForeground(new java.awt.Color(255, 250, 248));
        reservasContainer.setMaximumSize(new java.awt.Dimension(941, 2147483647));
        reservasContainer.setMinimumSize(new java.awt.Dimension(941, 0));
        reservasContainer.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        reservasLabel1.setFont(new java.awt.Font("Serif", 3, 48)); // NOI18N
        reservasLabel1.setForeground(new java.awt.Color(255, 90, 95));
        reservasLabel1.setText("Bienvenido a tu lista de reservas");
        reservasLabel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        reservasLabel.setFont(new java.awt.Font("Serif", 3, 36)); // NOI18N
        reservasLabel.setForeground(new java.awt.Color(255, 90, 95));
        reservasLabel.setText("Aquí podrás consultar donde pasarás tus próximas aventuras");
        reservasLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(barraarriba1, javax.swing.GroupLayout.DEFAULT_SIZE, 2573, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(reservasContainer, javax.swing.GroupLayout.PREFERRED_SIZE, 1484, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(597, 597, 597)
                        .addComponent(reservasLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(458, 458, 458)
                        .addComponent(reservasLabel)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(barraarriba1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(reservasLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(reservasLabel)
                .addGap(10, 10, 10)
                .addComponent(reservasContainer, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void logoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_logoButtonActionPerformed

    private void returnButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_returnButtonActionPerformed
        Aplicacion.loadMainScreen();
    }//GEN-LAST:event_returnButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel barraarriba1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton logoButton;
    private javax.swing.JLabel logoLabel;
    private javax.swing.JPanel reservasContainer;
    private javax.swing.JLabel reservasLabel;
    private javax.swing.JLabel reservasLabel1;
    private javax.swing.JButton returnButton;
    // End of variables declaration//GEN-END:variables
}
