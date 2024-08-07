
package PROYECTO;

import AppPackage.AnimationClass;
import java.awt.Graphics;
import java.awt.Image;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.CLOSED_OPTION;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

public class P5_ENTRADA extends javax.swing.JFrame {

    FondoPanel fondo=new FondoPanel();
    
    static Connection con=null;
    static Statement stmt=null;
    static ResultSet rs=null;
    
    DefaultTableModel tableModel;
    DefaultTableModel tabla = new DefaultTableModel();
    public Object select[] = new Object[12];
    public int filaSelec;
    
    //METODO PARA PERMITIR LA CONECTIVIDAD
    public static Connection conectar(){
        String cadena = "jdbc:sqlserver://localhost:1433;databaseName=PlazaVeaBruh3;user=sa;password=root;logingTimeout=10";
        try{
            con=DriverManager.getConnection(cadena);
            return con;
        }catch(SQLException e){
            JOptionPane.showConfirmDialog(null,e, "No se pudo conectar",CLOSED_OPTION);
            return null;
        }
    }
    
    //METODOS PARA LOS BOTONES
    public void consultarData(){
        try{
            stmt = P5_ENTRADA.conectar().createStatement();
            String consulta="SELECT TB_ENTRADA.ID_ENTRADA, TB_ENTRADA.ID_PROVEEDOR, TB_ENTRADA.ID_ALMACEN, TB_ENTRADA.ID_EMPLEADO, TB_ENTRADA.FEC_ENTRADA, TB_ENTRADA.NUM_FACTURA, TB_ENTRADA.FEC_FACTURA, TB_ENTRADA.FACTURA, TB_DETA_ENTRADA.ID_PRODUCTO, TB_DETA_ENTRADA.ID_MOTIVO, TB_DETA_ENTRADA.CANTIDAD, TB_DETA_ENTRADA.PRECIO_COMPRA\n" +
                            "FROM TB_ENTRADA\n" +
                            "INNER JOIN TB_DETA_ENTRADA ON TB_ENTRADA.ID_ENTRADA = TB_DETA_ENTRADA.ID_ENTRADA;";
            rs = stmt.executeQuery(consulta);
            while (rs.next()){
                //ESPECIFICAR LA COLUMNA
                Object fila[] = {rs.getString(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getString(6), 
                        rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12)};
                tabla.addRow(fila);
            }            
        }catch(SQLException e){
            JOptionPane.showConfirmDialog(null, e, "No se pudo recuperar", CLOSED_OPTION);
        }
    }
    
    public void limpiarTabla(){
        int rowCount = tableModel.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            tableModel.removeRow(i);
        }
    }
    
    public void limpiar(){
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
        jTextField5.setText("");
        jTextField6.setText("");
        jTextField7.setText("");
        jComboBox1.setSelectedIndex(0);
        jComboBox2.setSelectedIndex(0);
        jComboBox3.setSelectedIndex(0);
        jComboBox4.setSelectedIndex(0);
        jComboBox5.setSelectedIndex(0);
        jDateChooser1.setDate(null);
        jDateChooser2.setDate(null);
        jSpinner1.setValue(0);
    }
    
    public void idAlma(){
        try{
            stmt = P5_ENTRADA.conectar().createStatement();
            String almacen="select * from TB_ALMACEN";
            rs = stmt.executeQuery(almacen);
            while (rs.next()){
                //PARA ESTE NO HAY NECESIDAD DE ESPECIFICAR LA COLUMNA             
                jComboBox2.addItem(rs.getString(1));
            }            
        }catch(SQLException e){
            JOptionPane.showConfirmDialog(null, e, "No se pudo recuperar", CLOSED_OPTION);
        }
    }
    
    public void idEmpl(){
        try{
            stmt = P5_ENTRADA.conectar().createStatement();
            String emple="select * from TB_EMPLEADO";
            rs = stmt.executeQuery(emple);
            while (rs.next()){
                //PARA ESTE NO HAY NECESIDAD DE ESPECIFICAR LA COLUMNA             
                jComboBox3.addItem(rs.getString(1));
            }            
        }catch(SQLException e){
            JOptionPane.showConfirmDialog(null, e, "No se pudo recuperar", CLOSED_OPTION);
        }
    }
    public void idProduc(){
        try{
            stmt = P5_ENTRADA.conectar().createStatement();
            String prod="select * from TB_PRODUCTO";
            rs = stmt.executeQuery(prod);
            while (rs.next()){
                //PARA ESTE NO HAY NECESIDAD DE ESPECIFICAR LA COLUMNA             
                jComboBox4.addItem(rs.getString(1));
                
            }            
        }catch(SQLException e){
            JOptionPane.showConfirmDialog(null, e, "No se pudo recuperar", CLOSED_OPTION);
        }
    }
    public void idMotiv(){
        try{
            stmt = P5_ENTRADA.conectar().createStatement();
            String motiv="select * from TB_MOTIVO";
            rs = stmt.executeQuery(motiv);
            while (rs.next()){
                //PARA ESTE NO HAY NECESIDAD DE ESPECIFICAR LA COLUMNA             
                jComboBox5.addItem(rs.getString(1));
            }            
        }catch(SQLException e){
            JOptionPane.showConfirmDialog(null, e, "No se pudo recuperar", CLOSED_OPTION);
        }
    }
    public void idProv(){
        try{
            stmt = P5_ENTRADA.conectar().createStatement();
            String prov="select * from TB_PROVEEDOR";
            rs = stmt.executeQuery(prov);
            while (rs.next()){
                //PARA ESTE NO HAY NECESIDAD DE ESPECIFICAR LA COLUMNA             
                jComboBox1.addItem(rs.getString(1));                
            }            
        }catch(SQLException e){
            JOptionPane.showConfirmDialog(null, e, "No se pudo recuperar", CLOSED_OPTION);
        }
    }
    
    //METODOS PARA LA ANIMACION
    public void movement(){
        AnimationClass all = new AnimationClass();
        //LOGO
        all.jLabelYUp(460, 10, 5, 2, jLabel1);
        //ARRIBA
        all.jLabelYDown(-25, 50, 5, 2, jLabel2);
        //ID ENTRADA
        all.jLabelYDown(-25, 80, 5, 2, jLabel3);
        all.jTextFieldYDown(-25, 100, 5, 2, jTextField1);
        //ID PROVEEDOR
        all.jLabelYDown(-25, 130, 5, 2, jLabel4);
        //ID ALMACEN
        all.jLabelYDown(-25, 180, 5, 2, jLabel5);
        //ID EMPLEADO
        all.jLabelYDown(-25, 230, 5, 2, jLabel7);
        //FECHA ENTRADA
        all.jLabelYDown(-25, 280, 5, 2, jLabel6);
        //NUM FACTURA
        all.jLabelYDown(-25, 330, 5, 2, jLabel8);
        all.jTextFieldYDown(-25, 350, 5, 2, jTextField2);
        //FECHA FACTURA
        all.jLabelYDown(-25, 380, 5, 2, jLabel9);
        //FACTURA
        all.jLabelYDown(-25, 100, 5, 2, jLabel13);
        all.jTextFieldYDown(-25, 120, 5, 2, jTextField7);
        //ID PRODUCTO
        all.jLabelYDown(-25, 150, 5, 2, jLabel11);
        all.jTextFieldYDown(-25, 190, 5, 2, jTextField3);
        //ID MOTIVO
        all.jLabelYDown(-25, 220, 5, 2, jLabel12);
        all.jTextFieldYDown(-25, 260, 5, 2, jTextField5);
        //CANTIDAD
        all.jLabelYDown(-25, 290, 5, 2, jLabel10);
        //PRECIO COMPRA
        all.jLabelYDown(-25, 340, 5, 2, jLabel14);  
        all.jTextFieldYDown(-25, 360, 5, 2, jTextField6);
        //BUSQUEDA
        all.jTextFieldYDown(-25, 90, 5, 2, jTextField4);
        
        //ABAJO
        all.jButtonYUp(460, 370, 5, 2, jButton6);
        all.jButtonYUp(460, 370, 5, 2, jButton4);
        all.jButtonYUp(460, 340, 5, 2, jButton3);
        all.jButtonYUp(460, 10, 5, 2, jButton5);
        all.jButtonYUp(460, 80, 5, 2, jButton1);
        all.jButtonYUp(460, 370, 5, 2, jButton7);
        all.jButtonYUp(460, 340, 5, 2, jButton8);
    }
    
    public P5_ENTRADA() {
        initComponents();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        rsscalelabel.RSScaleLabel.setScaleLabel(jLabel1, "src/ICONOS/logo-plazavea.png");
        jDateChooser1.setOpaque(false);
        jDateChooser2.setOpaque(false);
        movement();
        
        jTable1.setModel(tabla);
        tableModel = (DefaultTableModel) jTable1.getModel();
        //jTable1.setOpaque(false);
        
        tabla.addColumn("ID Entrada");
        tabla.addColumn("ID Proveedor");
        tabla.addColumn("ID Almacen");
        tabla.addColumn("ID Empleado");
        tabla.addColumn("Fec. Entrada");
        tabla.addColumn("Num. Factura");
        tabla.addColumn("Fec. Factura");
        tabla.addColumn("Factura");
        tabla.addColumn("ID Producto");
        tabla.addColumn("ID Motivo");
        tabla.addColumn("Cantidad");
        tabla.addColumn("Precio Compra");
        
        consultarData();
        idAlma();
        idEmpl();
        idMotiv();
        idProduc();
        idProv();
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
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jTextField2 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jLabel10 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox<>();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jComboBox5 = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jSpinner1 = new javax.swing.JSpinner();
        jTextField6 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTextField7 = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(null);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setPreferredSize(new java.awt.Dimension(1200, 373));
        jPanel1.add(jLabel1);
        jLabel1.setBounds(10, 460, 90, 30);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("ENTRADA DE PRODUCTOS");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(20, -25, 190, 17);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("ID Entrada:");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(30, -25, 70, 15);

        jTextField1.setBackground(new java.awt.Color(255, 235, 198));
        jTextField1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTextField1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel1.add(jTextField1);
        jTextField1.setBounds(30, -25, 130, 16);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("ID Proveedor:");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(30, -25, 90, 15);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "" }));
        jPanel1.add(jComboBox1);
        jComboBox1.setBounds(30, 150, 130, 22);

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "" }));
        jPanel1.add(jComboBox2);
        jComboBox2.setBounds(30, 200, 130, 22);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("ID Almacén:");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(30, -25, 80, 15);

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "" }));
        jPanel1.add(jComboBox3);
        jComboBox3.setBounds(30, 250, 130, 22);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Fecha de entrada:");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(30, -25, 110, 15);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("ID Empleado:");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(30, -25, 80, 15);

        jDateChooser1.setBackground(new java.awt.Color(255, 255, 255));
        jDateChooser1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jDateChooser1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jPanel1.add(jDateChooser1);
        jDateChooser1.setBounds(30, 300, 130, 24);

        jTextField2.setBackground(new java.awt.Color(255, 235, 198));
        jTextField2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTextField2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel1.add(jTextField2);
        jTextField2.setBounds(30, -25, 130, 16);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Num. Factura:");
        jPanel1.add(jLabel8);
        jLabel8.setBounds(30, -25, 84, 15);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("Fecha de factura:");
        jPanel1.add(jLabel9);
        jLabel9.setBounds(30, -25, 110, 15);

        jDateChooser2.setBackground(new java.awt.Color(255, 255, 255));
        jDateChooser2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jDateChooser2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jPanel1.add(jDateChooser2);
        jDateChooser2.setBounds(30, 400, 130, 24);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("Cantidad:");
        jPanel1.add(jLabel10);
        jLabel10.setBounds(190, -25, 60, 15);

        jTextField3.setBackground(new java.awt.Color(255, 235, 198));
        jTextField3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTextField3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel1.add(jTextField3);
        jTextField3.setBounds(190, -25, 130, 16);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setText("ID Producto:");
        jPanel1.add(jLabel11);
        jLabel11.setBounds(190, -25, 80, 15);

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "" }));
        jPanel1.add(jComboBox4);
        jComboBox4.setBounds(190, 170, 130, 22);

        jTextField4.setBackground(new java.awt.Color(255, 235, 198));
        jTextField4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTextField4.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel1.add(jTextField4);
        jTextField4.setBounds(600, -25, 130, 16);

        jTextField5.setBackground(new java.awt.Color(255, 235, 198));
        jTextField5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTextField5.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel1.add(jTextField5);
        jTextField5.setBounds(190, -25, 130, 16);

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "" }));
        jPanel1.add(jComboBox5);
        jComboBox5.setBounds(190, 240, 130, 22);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("ID Motivo:");
        jPanel1.add(jLabel12);
        jLabel12.setBounds(190, -25, 70, 15);

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setText("Factura:");
        jPanel1.add(jLabel13);
        jLabel13.setBounds(190, -25, 50, 15);

        jSpinner1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jPanel1.add(jSpinner1);
        jSpinner1.setBounds(190, 310, 130, 20);

        jTextField6.setBackground(new java.awt.Color(255, 235, 198));
        jTextField6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTextField6.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel1.add(jTextField6);
        jTextField6.setBounds(190, -25, 130, 16);

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setText("Precio compra:");
        jPanel1.add(jLabel14);
        jLabel14.setBounds(190, -25, 90, 15);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONOS/buscarx32.png"))); // NOI18N
        jButton1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel1.add(jButton1);
        jButton1.setBounds(730, 460, 32, 32);

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONOS/eliminarUNOx32.png"))); // NOI18N
        jButton6.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton6.setBorderPainted(false);
        jButton6.setContentAreaFilled(false);
        jButton6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton6.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONOS/eliminarDOSx32.png"))); // NOI18N
        jButton6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton6MouseClicked(evt);
            }
        });
        jPanel1.add(jButton6);
        jButton6.setBounds(690, 460, 32, 32);

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
        jButton5.setBounds(790, 460, 32, 32);

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONOS/anadirUNOx32.png"))); // NOI18N
        jButton4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton4.setBorderPainted(false);
        jButton4.setContentAreaFilled(false);
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton4.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONOS/anadirDOSx32.png"))); // NOI18N
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton4MouseClicked(evt);
            }
        });
        jPanel1.add(jButton4);
        jButton4.setBounds(620, 460, 32, 32);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONOS/limpiarUNOx32.png"))); // NOI18N
        jButton3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton3.setBorderPainted(false);
        jButton3.setContentAreaFilled(false);
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONOS/limpiarDOSx32.png"))); // NOI18N
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });
        jPanel1.add(jButton3);
        jButton3.setBounds(380, 460, 32, 32);

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
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(360, 120, 452, 210);

        jTextField7.setBackground(new java.awt.Color(255, 235, 198));
        jTextField7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTextField7.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel1.add(jTextField7);
        jTextField7.setBounds(190, -25, 130, 16);

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONOS/editarUNOx32.png"))); // NOI18N
        jButton7.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton7.setBorderPainted(false);
        jButton7.setContentAreaFilled(false);
        jButton7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton7.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONOS/editarDOSx32.png"))); // NOI18N
        jButton7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton7MouseClicked(evt);
            }
        });
        jPanel1.add(jButton7);
        jButton7.setBounds(550, 460, 32, 32);

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONOS/reporte-de-negocios.png"))); // NOI18N
        jButton8.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton8.setBorderPainted(false);
        jButton8.setContentAreaFilled(false);
        jButton8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton8MouseClicked(evt);
            }
        });
        jPanel1.add(jButton8);
        jButton8.setBounds(420, 460, 32, 32);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 834, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 442, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
//ATRAS
    private void jButton5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseClicked
        // TODO add your handling code here:
        MAIN main=new MAIN();
        main.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton5MouseClicked
    //LIMPIAR CAJAS DE TEXTO
    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
        // TODO add your handling code here:
        limpiar();
    }//GEN-LAST:event_jButton3MouseClicked
    //EDITAR
    private void jButton7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton7MouseClicked
        // TODO add your handling code here:
        int idEntrada = Integer.valueOf(jTextField1.getText());
        String idProveedor = jComboBox1.getSelectedItem().toString();
        String idAlmacen = jComboBox2.getSelectedItem().toString();
        String idEmlpleado = jComboBox3.getSelectedItem().toString();
        String fecEntr = jDateChooser1.getDateFormatString();
        String fecFact = jDateChooser2.getDateFormatString();
        int numFactura = Integer.valueOf(jTextField2.getText());
        String factura = jTextField7.getText();
        
        String acSA = "UPDATE TB_ENTRADA SET ID_ENTRADA='" + idEntrada + "', ID_PROVEEDOR='" + idProveedor + "', ID_ALMACEN='" + idAlmacen + "',\n"
            + "FEC_ENTRADA='" + fecEntr + "', NUM_FACTURA='" + numFactura + "', FEC_FACTURA='" + fecFact + "', ID_EMPLEADO='" + idEmlpleado + "', FACTURA='" + factura + "'\n"
            + "WHERE ID_ENTRADA='" + idEntrada + "'";
        PreparedStatement pre1 = null;
            try {
                pre1 = con.prepareStatement(acSA);
                pre1.executeUpdate();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        
        //--------------------------------------------------------------
        String idProduc = jComboBox4.getSelectedItem().toString();
        String idMotivo = jComboBox5.getSelectedItem().toString();
        String Cantidad = jSpinner1.getValue().toString();
        String preComp = jTextField6.getText();
        String IGV = 18+"%";
        
        String acDESA = "UPDATE TB_DETA_ENTRADA SET ID_ENTRADA='" + idEntrada + "',ID_PRODUCTO='" + idProduc + "',ID_MOTIVO='" + idMotivo + "',\n"
                    + "CANTIDAD='" + Cantidad + "',PRECIO_COMPRA='" + preComp + "',IGV='" + IGV + "'\n"
                    + "WHERE ID_ENTRADA='" + idEntrada + "'";
        PreparedStatement pre2 = null;
            try {
                pre2 = con.prepareStatement(acDESA);
                pre2.executeUpdate();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        
        limpiar();
        limpiarTabla();
        consultarData();
    }//GEN-LAST:event_jButton7MouseClicked
    //AGREGAR
    private void jButton4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseClicked
        // TODO add your handling code here:
        int idEntrada = Integer.valueOf(jTextField1.getText());
        String idProveedor = jComboBox1.getSelectedItem().toString();
        String idAlmacen = jComboBox2.getSelectedItem().toString();
        String idEmlpleado = jComboBox3.getSelectedItem().toString();
        //----INICIO FECHAS
        //1
        String dia = Integer.toString(jDateChooser1.getCalendar().get(Calendar.DAY_OF_MONTH));
        String mes = Integer.toString(jDateChooser1.getCalendar().get(Calendar.MONTH)+1);
        String año = Integer.toString(jDateChooser1.getCalendar().get(Calendar.YEAR));
        String fecEntr = (dia+"/"+mes+"/"+año);
        //2
        String dia2 = Integer.toString(jDateChooser2.getCalendar().get(Calendar.DAY_OF_MONTH));
        String mes2 = Integer.toString(jDateChooser2.getCalendar().get(Calendar.MONTH)+1);
        String año2 = Integer.toString(jDateChooser2.getCalendar().get(Calendar.YEAR));
        String fecFact = (dia2+"/"+mes2+"/"+año2);
        //----FIN FECHAS
        int numFactura = Integer.valueOf(jTextField2.getText());
        String factura = jTextField7.getText();
        
        String ingSALI = "INSERT INTO TB_ENTRADA VALUES ('" + idEntrada + "','" + idProveedor + "','" + idAlmacen + "','" + fecEntr + "','" + numFactura + "','" + fecFact + "','" + idEmlpleado + "','" + factura + "')";
        PreparedStatement pre1 = null;
        try {
            pre1 = con.prepareStatement(ingSALI);
            pre1.executeUpdate();
        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(null, ex);
        }
        
        //--------------------------------------------------------------
        String idProduc = jComboBox4.getSelectedItem().toString();
        String idMotivo = jComboBox5.getSelectedItem().toString();
        String Cantidad = jSpinner1.getValue().toString();
        String preComp = jTextField6.getText();
        String IGV = 18+"%";
        
        String ingDeSa = "INSERT INTO TB_DETA_ENTRADA VALUES ('" + idEntrada + "','" + idProduc + "','" + idMotivo + "','" + Cantidad + "','" + preComp + "','" + IGV + "')";
        PreparedStatement pre2 = null;
        try {
            pre2 = con.prepareStatement(ingDeSa);
            pre2.executeUpdate();
        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(null, ex);
        }
        
        limpiar();
        limpiarTabla();
        consultarData();
    }//GEN-LAST:event_jButton4MouseClicked
    //ELIMINAR
    private void jButton6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton6MouseClicked
        // TODO add your handling code here:
        String idEntrada = jTextField1.getText();
        
        String del = "DELETE FROM TB_DETA_ENTRADA WHERE ID_ENTRADA ='" + idEntrada + "'";
        PreparedStatement pre2 = null;
        try {
            pre2 = con.prepareStatement(del);
            pre2.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        
        String ing = "DELETE FROM TB_ENTRADA WHERE ID_ENTRADA ='" + idEntrada + "'";
        PreparedStatement pre = null;
        try {
            pre = con.prepareStatement(ing);
            pre.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        
        limpiar();
        limpiarTabla();
        consultarData();
    }//GEN-LAST:event_jButton6MouseClicked
    //MOSTRAR DATOS EN LAS CAJAS DE TEXTO
    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        filaSelec = jTable1.getSelectedRow();
        select[0] = jTable1.getValueAt(filaSelec, 0);
        select[1] = jTable1.getValueAt(filaSelec, 1);
        select[2] = jTable1.getValueAt(filaSelec, 2);
        select[3] = jTable1.getValueAt(filaSelec, 3);
        select[4] = jTable1.getValueAt(filaSelec, 4);
        select[5] = jTable1.getValueAt(filaSelec, 5);
        select[6] = jTable1.getValueAt(filaSelec, 6);
        select[7] = jTable1.getValueAt(filaSelec, 7);
        select[8] = jTable1.getValueAt(filaSelec, 8);
        select[9] = jTable1.getValueAt(filaSelec, 9);
        select[10] = jTable1.getValueAt(filaSelec, 10);
        select[11] = jTable1.getValueAt(filaSelec, 11);
        
        jTextField1.setText(select[0].toString());
        jComboBox1.setSelectedItem(select[1]);
        jComboBox2.setSelectedItem(select[2]);
        jComboBox3.setSelectedItem(select[3]);
        //jDateChooser2.getDateFormatString(select[4]);
        jTextField2.setText(select[5].toString());
        //jDateChooser2.getDateFormatString(select[6]);
        jTextField7.setText(select[7].toString());
        jComboBox4.setSelectedItem(select[8]);
        jComboBox5.setSelectedItem(select[9]);
        jSpinner1.setValue(Integer.valueOf(select[10].toString()));
        jTextField6.setText(select[11].toString());
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton8MouseClicked
        // TODO add your handling code here:
            P7_Reporte report = new P7_Reporte();
            report.setVisible(true);
            this.dispose();
    }//GEN-LAST:event_jButton8MouseClicked

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
            java.util.logging.Logger.getLogger(P5_ENTRADA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(P5_ENTRADA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(P5_ENTRADA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(P5_ENTRADA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new P5_ENTRADA().setVisible(true);
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
    private javax.swing.JButton jButton8;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner jSpinner1;
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
