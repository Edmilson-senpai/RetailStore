
package PROYECTO;

import AppPackage.AnimationClass;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.CLOSED_OPTION;

public class P4_PROVEEDOR extends javax.swing.JFrame {

    FondoPanel fondo=new FondoPanel();
    
    static Connection con = null;
    static Statement stmt = null;
    static ResultSet rs = null;
    
    DefaultTableModel tableModel;
    DefaultTableModel tabla = new DefaultTableModel();
    public Object select[] = new Object[20];
    public int filaSelec;

    //METODO PARA PERMITIR LA CONECTIVIDAD
    public static Connection conectar(){
        String cadena = "jdbc:sqlserver://localhost:1433;databaseName=PlazaVeaBruh3;user=sa;password=utpuser;logingTimeout=10";
        try{
            con=DriverManager.getConnection(cadena);
            return con;
        }catch(SQLException e){
            JOptionPane.showConfirmDialog(null,e, "No se pudo conectar",CLOSED_OPTION);
            return null;
        }
    }

    // Métodos para los botones
    public void consultarData() {
        try {;
            stmt = TABLA_PROD.conectar().createStatement();
            String consulta="SELECT ID_PROVEEDOR,NOMBRE,RUC,NUMERO,CORREO,DIRECCION FROM TB_PROVEEDOR";        
        
        rs = stmt.executeQuery(consulta);
            while (rs.next()){
                //ESPECIFICAR LA COLUMNA
                Object fila[] = {rs.getString(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getString(6)};
                tabla.addRow(fila);
            } 
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e, "No se pudo recuperar", JOptionPane.ERROR_MESSAGE);
        }
    }
   
    
    public void movement(){
        AnimationClass all = new AnimationClass();
        //LOGO
        all.jLabelYUp(390, 10, 5, 2, jLabel1);
        //ARRIBA
        all.jLabelYDown(-25, 47, 5, 2, jLabel2);
        //CODIGO
        all.jLabelYDown(-25, 80, 5, 2, jLabel4);
        all.jTextFieldYDown(-25, 80, 5, 2, jTextField2);
        //NOMBRE
        all.jLabelYDown(-25, 110, 5, 2, jLabel3);
        all.jTextFieldYDown(-25, 130, 5, 2, jTextField7);
        //RUC
        all.jLabelYDown(-25, 160, 5, 2, jLabel5);
        all.jTextFieldYDown(-25, 180, 5, 2, jTextField3);
        //NUMERO
        all.jLabelYDown(-25, 210, 5, 2, jLabel6);
        all.jTextFieldYDown(-25, 230, 5, 2, jTextField4);
        //CORREO
        all.jLabelYDown(-25, 260, 5, 2, jLabel7);
        all.jTextFieldYDown(-25, 280, 5, 2, jTextField5);
        //DIRECCION
        all.jLabelYDown(-25, 310, 5, 2, jLabel8);
        all.jTextFieldYDown(-25, 330, 5, 2, jTextField6);
        //BUSQUEDA
        all.jTextFieldYDown(-25, 80, 5, 2, jTextField1);
        
        //ABAJO
        all.jButtonYUp(390, 300, 5, 2, jButton6);
        all.jButtonYUp(390, 300, 5, 2, jButton4);
        all.jButtonYUp(390, 300, 5, 2, jButton3);
        all.jButtonYUp(390, 10, 5, 2, jButton5);
        all.jButtonYUp(390, 70, 5, 2, jButton1);
        all.jButtonYUp(390, 290, 5, 2, jButton7);
    }
    
    public P4_PROVEEDOR() {
        initComponents();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        rsscalelabel.RSScaleLabel.setScaleLabel(jLabel1, "src/ICONOS/logo-plazavea.png");
        movement();
        
        jTable1.setModel(tabla);
        tableModel = (DefaultTableModel) jTable1.getModel();
        //jTable1.setOpaque(false);
        
        tabla.addColumn("Códico");
        tabla.addColumn("Nombre");
        tabla.addColumn("RUC");
        tabla.addColumn("Número");
        tabla.addColumn("Correo");
        tabla.addColumn("Dirección");
        consultarData();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new FondoPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jTextField7 = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(null);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setPreferredSize(new java.awt.Dimension(1200, 373));
        jPanel1.add(jLabel1);
        jLabel1.setBounds(10, 390, 90, 30);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("PROVEEDORES");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(20, -25, 110, 17);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Nombre:");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(30, -25, 60, 15);

        jTextField1.setBackground(new java.awt.Color(255, 235, 198));
        jTextField1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel1.add(jTextField1);
        jTextField1.setBounds(460, -25, 150, 16);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Código:");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(30, -25, 50, 15);

        jTextField2.setEditable(false);
        jTextField2.setBackground(new java.awt.Color(255, 235, 198));
        jTextField2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTextField2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel1.add(jTextField2);
        jTextField2.setBounds(80, -25, 100, 16);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("RUC:");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(30, -25, 40, 15);

        jTextField3.setBackground(new java.awt.Color(255, 235, 198));
        jTextField3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTextField3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel1.add(jTextField3);
        jTextField3.setBounds(30, -25, 150, 16);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Número:");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(30, -25, 60, 15);

        jTextField4.setBackground(new java.awt.Color(255, 235, 198));
        jTextField4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTextField4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel1.add(jTextField4);
        jTextField4.setBounds(30, -25, 150, 16);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Correo:");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(30, -25, 50, 15);

        jTextField5.setBackground(new java.awt.Color(255, 235, 198));
        jTextField5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTextField5.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel1.add(jTextField5);
        jTextField5.setBounds(30, -25, 150, 16);

        jTextField6.setBackground(new java.awt.Color(255, 235, 198));
        jTextField6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTextField6.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel1.add(jTextField6);
        jTextField6.setBounds(30, -25, 150, 16);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Dirección:");
        jPanel1.add(jLabel8);
        jLabel8.setBounds(30, -25, 70, 15);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONOS/editarUNOx32.png"))); // NOI18N
        jButton3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton3.setBorderPainted(false);
        jButton3.setContentAreaFilled(false);
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONOS/editarDOSx32.png"))); // NOI18N
        jPanel1.add(jButton3);
        jButton3.setBounds(470, 390, 32, 32);

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONOS/proveeAgrex32.png"))); // NOI18N
        jButton4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton4.setBorderPainted(false);
        jButton4.setContentAreaFilled(false);
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel1.add(jButton4);
        jButton4.setBounds(530, 390, 32, 32);

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
        jButton5.setBounds(670, 390, 32, 32);

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONOS/proveeElimx32.png"))); // NOI18N
        jButton6.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton6.setBorderPainted(false);
        jButton6.setContentAreaFilled(false);
        jButton6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel1.add(jButton6);
        jButton6.setBounds(590, 390, 32, 32);

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
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(220, 110, 452, 170);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONOS/buscarx32.png"))); // NOI18N
        jButton1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel1.add(jButton1);
        jButton1.setBounds(620, 390, 32, 32);

        jTextField7.setBackground(new java.awt.Color(255, 235, 198));
        jTextField7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTextField7.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel1.add(jTextField7);
        jTextField7.setBounds(30, -25, 150, 16);

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONOS/limpiarUNOx32.png"))); // NOI18N
        jButton7.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton7.setBorderPainted(false);
        jButton7.setContentAreaFilled(false);
        jButton7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton7.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONOS/limpiarDOSx32.png"))); // NOI18N
        jPanel1.add(jButton7);
        jButton7.setBounds(230, 390, 32, 32);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 709, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            java.util.logging.Logger.getLogger(P4_PROVEEDOR.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(P4_PROVEEDOR.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(P4_PROVEEDOR.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(P4_PROVEEDOR.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new P4_PROVEEDOR().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
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
