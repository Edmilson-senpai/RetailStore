
package PROYECTO;

import AppPackage.AnimationClass;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class PERFIL extends javax.swing.JFrame {

    FondoPanel fondo=new FondoPanel();
    
    public void movement(){
        AnimationClass all = new AnimationClass();
        //LOGO
        all.jLabelYUp(310, 10, 5, 2, jLabel1);
        //ARRIBA
        all.jLabelYDown(-25, 52, 5, 2, jLabel2);
        //ID EMPLEADO
        all.jLabelYDown(-25, 230, 5, 2, jLabel3);
        all.jTextFieldYDown(-25, 250, 5, 2, jTextField1);
        //NOMBRE
        all.jLabelYDown(-25, 80, 5, 2, jLabel4);
        all.jTextFieldYDown(-25, 100, 5, 2, jTextField2);
        //APELLIDO
        all.jLabelYDown(-25, 130, 5, 2, jLabel5);
        all.jTextFieldYDown(-25, 150, 5, 2, jTextField3);
        //DNI
        all.jLabelYDown(-25, 180, 5, 2, jLabel6);
        all.jTextFieldYDown(-25, 200, 5, 2, jTextField4);
        //CARGO
        all.jLabelYDown(-25, 230, 5, 2, jLabel7);
        all.jTextFieldYDown(-25, 250, 5, 2, jTextField5);
        //ABAJO
        all.jButtonYUp(310, 90, 5, 2, jButton1);
        all.jButtonYUp(310, 10, 5, 2, jButton5);
    }
    
    public PERFIL() {
        initComponents();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        rsscalelabel.RSScaleLabel.setScaleLabel(jLabel1, "src/ICONOS/logo-plazavea.png");
        movement();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new FondoPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(null);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setPreferredSize(new java.awt.Dimension(1200, 373));
        jPanel1.add(jLabel1);
        jLabel1.setBounds(10, 310, 90, 30);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("PERFIL");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(20, -25, 60, 17);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONOS/perfilx128.png"))); // NOI18N
        jButton1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jPanel1.add(jButton1);
        jButton1.setBounds(30, 310, 128, 128);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("ID Empleado:");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(30, -25, 80, 15);

        jTextField1.setBackground(new java.awt.Color(255, 235, 198));
        jTextField1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel1.add(jTextField1);
        jTextField1.setBounds(30, -25, 130, 16);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Nombre:");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(190, -25, 60, 15);

        jTextField2.setBackground(new java.awt.Color(255, 235, 198));
        jTextField2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel1.add(jTextField2);
        jTextField2.setBounds(190, -25, 160, 16);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Apellidos:");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(190, -25, 60, 15);

        jTextField3.setBackground(new java.awt.Color(255, 235, 198));
        jTextField3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel1.add(jTextField3);
        jTextField3.setBounds(190, -25, 160, 16);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("DNI:");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(190, -25, 30, 15);

        jTextField4.setBackground(new java.awt.Color(255, 235, 198));
        jTextField4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel1.add(jTextField4);
        jTextField4.setBounds(190, -25, 160, 16);

        jTextField5.setBackground(new java.awt.Color(255, 235, 198));
        jTextField5.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel1.add(jTextField5);
        jTextField5.setBounds(190, -25, 160, 16);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Cargo:");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(190, -25, 50, 15);

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONOS/atrasUNOx32.png"))); // NOI18N
        jButton5.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton5.setBorderPainted(false);
        jButton5.setContentAreaFilled(false);
        jButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton5.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONOS/atrasDOSx32.png"))); // NOI18N
        jButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton5MouseClicked(evt);
            }
        });
        jPanel1.add(jButton5);
        jButton5.setBounds(340, 310, 32, 32);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseClicked
        // TODO add your handling code here:
        MAIN main=new MAIN();
        main.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton5MouseClicked

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PERFIL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PERFIL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PERFIL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PERFIL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PERFIL().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    // End of variables declaration//GEN-END:variables

    class FondoPanel extends JPanel{
        private Image imagen;
        
        @Override
        public void paint(Graphics g){
            imagen=new ImageIcon(getClass().getResource("/FONDOS/fondo2.jpg")).getImage();
            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
            setOpaque(false);
            
            super.paint(g);
        }
    }
    
}
