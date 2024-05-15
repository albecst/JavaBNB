/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package UI_UX;

import Logica.Inmueble;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class IconoInmueble extends javax.swing.JPanel {

    private Inmueble i;

    /**
     * Creates new form IconoInmueble
     */
    public IconoInmueble() {
        initComponents();
    }

    public void init(Inmueble i) {
        this.i = i;
        nombre.setText(this.i.getTitulo());
        precio.setText(String.valueOf(this.i.getPrecioNoche()) + "€/noche");
        fotoboton.setIcon(resizeIMG(this.i.getFotografia()));

        estrella1.setIcon(this.i.getCalificacion() >= 1 ? imagenIcon("./src/main/resources/images/estrella50roja.PNG") : (imagenIcon("./src/main/resources/images/estrella50.PNG")));
        estrella2.setIcon(this.i.getCalificacion() >= 2 ? imagenIcon("./src/main/resources/images/estrella50roja.PNG") : (imagenIcon("./src/main/resources/images/estrella50.PNG")));
        estrella3.setIcon(this.i.getCalificacion() >= 3 ? imagenIcon("./src/main/resources/images/estrella50roja.PNG") : (imagenIcon("./src/main/resources/images/estrella50.PNG")));
        estrella4.setIcon(this.i.getCalificacion() >= 4 ? imagenIcon("./src/main/resources/images/estrella50roja.PNG") : (imagenIcon("./src/main/resources/images/estrella50.PNG")));
        estrella5.setIcon(this.i.getCalificacion() >= 5 ? imagenIcon("./src/main/resources/images/estrella50roja.PNG") : (imagenIcon("./src/main/resources/images/estrella50.PNG")));
    }

    public ImageIcon imagenIcon(String img) {
        try {
            Image image = ImageIO.read(new File(img));
            ImageIcon icon = new ImageIcon(image);
            return icon;
        } catch (IOException e) {
            //JOptionPane.showMessageDialog(null, "No se pudo cargar el icono: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public ImageIcon resizeIMG(String img) {
        try {
            File imagePath = new File(img);
            BufferedImage originalImage = ImageIO.read(imagePath);
            int width = 264; //fotoboton.getWidth();
            int height = 173; //fotoboton.getHeight();
            Image resizedImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            ImageIcon imageIcon = new ImageIcon(resizedImage);
            return imageIcon;
        } catch (IOException e) {
            //JOptionPane.showMessageDialog(this, "No se pudo cargar la imagen: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
        fotoboton = new javax.swing.JButton();
        nombre = new javax.swing.JLabel();
        precio = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        estrella1 = new javax.swing.JButton();
        estrella2 = new javax.swing.JButton();
        estrella3 = new javax.swing.JButton();
        estrella4 = new javax.swing.JButton();
        estrella5 = new javax.swing.JButton();

        setMaximumSize(new java.awt.Dimension(295, 400));
        setPreferredSize(new java.awt.Dimension(295, 400));

        jPanel1.setBackground(new java.awt.Color(255, 250, 248));
        jPanel1.setMaximumSize(new java.awt.Dimension(295, 400));
        jPanel1.setMinimumSize(new java.awt.Dimension(295, 400));

        fotoboton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/casa1.jpg"))); // NOI18N
        fotoboton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fotobotonMouseClicked(evt);
            }
        });
        fotoboton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fotobotonActionPerformed(evt);
            }
        });

        nombre.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        nombre.setText("Titulo");

        precio.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        precio.setText("Precio €/noche");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setMaximumSize(new java.awt.Dimension(220, 49));

        estrella1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/estrella50roja.png"))); // NOI18N
        estrella1.setToolTipText("");
        estrella1.setBorderPainted(false);
        estrella1.setFocusable(false);

        estrella2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/estrella50roja.png"))); // NOI18N
        estrella2.setToolTipText("");
        estrella2.setBorderPainted(false);
        estrella2.setFocusable(false);

        estrella3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/estrella50.png"))); // NOI18N
        estrella3.setToolTipText("");
        estrella3.setBorderPainted(false);
        estrella3.setFocusable(false);

        estrella4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/estrella50.png"))); // NOI18N
        estrella4.setToolTipText("");
        estrella4.setBorderPainted(false);
        estrella4.setFocusable(false);
        estrella4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                estrella4ActionPerformed(evt);
            }
        });

        estrella5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/estrella50.png"))); // NOI18N
        estrella5.setToolTipText("");
        estrella5.setBorderPainted(false);
        estrella5.setFocusable(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(estrella1)
                .addGap(6, 6, 6)
                .addComponent(estrella2)
                .addGap(6, 6, 6)
                .addComponent(estrella3)
                .addGap(6, 6, 6)
                .addComponent(estrella4)
                .addGap(6, 6, 6)
                .addComponent(estrella5))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(estrella1)
                    .addComponent(estrella2)
                    .addComponent(estrella3)
                    .addComponent(estrella4)
                    .addComponent(estrella5)))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(fotoboton, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(119, 119, 119)
                .addComponent(nombre))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(92, 92, 92)
                .addComponent(precio))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(fotoboton, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(nombre)
                .addGap(6, 6, 6)
                .addComponent(precio)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void fotobotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fotobotonActionPerformed
        Aplicacion.loadBuildingView(this.i);
        
    }//GEN-LAST:event_fotobotonActionPerformed

    private void estrella4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_estrella4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_estrella4ActionPerformed

    private void fotobotonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fotobotonMouseClicked

    }//GEN-LAST:event_fotobotonMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton estrella1;
    private javax.swing.JButton estrella2;
    private javax.swing.JButton estrella3;
    private javax.swing.JButton estrella4;
    private javax.swing.JButton estrella5;
    private javax.swing.JButton fotoboton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel nombre;
    private javax.swing.JLabel precio;
    // End of variables declaration//GEN-END:variables
}
