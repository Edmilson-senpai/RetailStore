package PROYECTO;

import AppPackage.AnimationClass;
import static PROYECTO.P3_PRODUCTOS.rs;
import static PROYECTO.P3_PRODUCTOS.stmt;
import java.awt.Graphics;
import java.awt.Image;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.CLOSED_OPTION;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

public class TABLA_PROD extends javax.swing.JFrame {

    FondoPanel fondo = new FondoPanel();

    static Connection con = null;
    static Statement stmt = null;
    static ResultSet rs = null;

    DefaultTableModel tableModel;
    DefaultTableModel tabla = new DefaultTableModel();
    public Object select[] = new Object[20];
    public int filaSelec;

    //METODO PARA PERMITIR LA CONECTIVIDAD
    public static Connection conectar() {
        String cadena = "jdbc:sqlserver://localhost:1433;databaseName=PlazaVeaBruh3;user=sa;password=root;logingTimeout=10";
        try {
            con = DriverManager.getConnection(cadena);
            return con;
        } catch (SQLException e) {
            JOptionPane.showConfirmDialog(null, e, "No se pudo conectar", CLOSED_OPTION);
            return null;
        }
    }

    // Métodos para los botones
    public void consultarData() {
        try {;
            stmt = TABLA_PROD.conectar().createStatement();
            String consulta = "SELECT ID_PRODUCTO, NOMBRE, ID_PROVEEDOR, ID_CATEGORIA, ID_ALMACEN, ID_UBI_ALMACEN, DESCRIPCION, PRECIO, CANT_MINSTOCK, CANT_MAXSTOCK, STOCK, MARCA, PRECIO_COMPRA, FEC_CADUCIDAD, TAMAÑO, PESO "
                    + "FROM TB_PRODUCTO";

            rs = stmt.executeQuery(consulta);
            while (rs.next()) {
                //ESPECIFICAR LA COLUMNA
                Object fila[] = {rs.getString(1), rs.getString(2), rs.getString(3),
                    rs.getString(4), rs.getString(5), rs.getString(6),
                    rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13), rs.getString(14), rs.getString(15),
                    rs.getString(16)};
                tabla.addRow(fila);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e, "No se pudo recuperar", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void movement() {
        AnimationClass buton = new AnimationClass();
        //LOGO
        buton.jLabelYUp(410, 10, 3, 2, jLabel1);
        //IZQUIERDA
        buton.jButtonXRight(20, 1100, 3, 2, jButton7);
        buton.jButtonXRight(20, 1050, 3, 2, jButton4);
        buton.jButtonXRight(20, 1000, 3, 2, jButton6);
        //ABAJO
        buton.jLabelYUp(270, 20, 5, 2, jLabel23);
        buton.jButtonYUp(270, 20, 3, 2, jButton9);
        buton.jTextFieldYUp(270, 20, 3, 2, jTextField13);
    }

    public TABLA_PROD() {
        initComponents();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        rsscalelabel.RSScaleLabel.setScaleLabel(jLabel1, "src/ICONOS/logo-plazavea.png");
        movement();

       jTable1.setModel(tabla);
        tableModel = (DefaultTableModel) jTable1.getModel();
        //jTable1.setOpaque(false);

        tabla.addColumn("ID Producto");
        tabla.addColumn("Producto");
        tabla.addColumn("ID Proveedor");
        tabla.addColumn("ID Categoria");
        tabla.addColumn("ID Almacne");
        tabla.addColumn("ID Direcc alm.");
        tabla.addColumn("Descripcion");
        tabla.addColumn("Precio");
        tabla.addColumn("Stock min");
        tabla.addColumn("Stock max");
        tabla.addColumn("Stock");
        tabla.addColumn("Marca");
        tabla.addColumn("Precio Compra");
        tabla.addColumn("Fech. Caducidad");
        tabla.addColumn("Tamaño");
        tabla.addColumn("Peso");
        
        
        consultarData();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new FondoPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton7 = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jTextField13 = new javax.swing.JTextField();
        jButton9 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setLayout(null);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel2.add(jScrollPane1);
        jScrollPane1.setBounds(20, 50, 1110, 220);

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONOS/ocultarUNOx32.png"))); // NOI18N
        jButton7.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton7.setBorderPainted(false);
        jButton7.setContentAreaFilled(false);
        jButton7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton7.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONOS/ocultarDOSx32.png"))); // NOI18N
        jButton7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton7MouseClicked(evt);
            }
        });
        jPanel2.add(jButton7);
        jButton7.setBounds(20, 10, 32, 32);

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("TABLA DE PRODUCTOS");
        jPanel2.add(jLabel23);
        jLabel23.setBounds(500, 270, 170, 17);

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONOS/anadirUNOx32.png"))); // NOI18N
        jButton4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton4.setBorderPainted(false);
        jButton4.setContentAreaFilled(false);
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton4.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONOS/anadirDOSx32.png"))); // NOI18N
        jPanel2.add(jButton4);
        jButton4.setBounds(20, 10, 32, 32);

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONOS/eliminarUNOx32.png"))); // NOI18N
        jButton6.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton6.setBorderPainted(false);
        jButton6.setContentAreaFilled(false);
        jButton6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton6.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONOS/eliminarDOSx32.png"))); // NOI18N
        jPanel2.add(jButton6);
        jButton6.setBounds(20, 10, 32, 32);

        jTextField13.setBackground(new java.awt.Color(255, 235, 198));
        jTextField13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTextField13.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField13.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel2.add(jTextField13);
        jTextField13.setBounds(170, 270, 130, 16);

        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONOS/buscarx32.png"))); // NOI18N
        jButton9.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton9.setBorderPainted(false);
        jButton9.setContentAreaFilled(false);
        jButton9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel2.add(jButton9);
        jButton9.setBounds(310, 270, 32, 32);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setPreferredSize(new java.awt.Dimension(1200, 373));
        jPanel2.add(jLabel1);
        jLabel1.setBounds(10, 410, 90, 30);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1151, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1151, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 305, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton7MouseClicked
        // TODO add your handling code here:
        P3_PRODUCTOS prod = new P3_PRODUCTOS();
        prod.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton7MouseClicked

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_jTable1MouseClicked

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
            java.util.logging.Logger.getLogger(TABLA_PROD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TABLA_PROD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TABLA_PROD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TABLA_PROD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TABLA_PROD().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField13;
    // End of variables declaration//GEN-END:variables

    class FondoPanel extends JPanel {

        private Image imagen;

        @Override
        public void paint(Graphics g) {
            imagen = new ImageIcon(getClass().getResource("/FONDOS/fondo2.jpg")).getImage();
            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
            setOpaque(false);

            super.paint(g);
        }
    }

}
