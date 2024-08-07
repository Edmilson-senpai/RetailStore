package PROYECTO;

import AppPackage.AnimationClass;
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

public class P3_PRODUCTOS extends javax.swing.JFrame {

    FondoPanel fondo = new FondoPanel();

    static Connection con = null;
    static Statement stmt = null;
    static ResultSet rs = null;

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

    //METODOS PARA LOS BOTONES
    public void consultarData() {
        try {
            stmt = P3_PRODUCTOS.conectar().createStatement();
            String consulta = "SELECT ID_PRODUCTO, NOMBRE,ID_PROVEEDOR,ID_CATEGORIA,ID_ALMACEN,ID_UBI_ALMACEN,\n"
                    + "DESCRIPCION,PRECIO,CANT_MINSTOCK,CANT_MAXSTOCK,STOCK,MARCA,PRECIO_COMPRA,FEC_CADUCIDAD,MARCA,\n"
                    + "TAMAÑO,PESO\n"
                    + "FROM TB_PRODUCTO";
            rs = stmt.executeQuery(consulta);
            while (rs.next()) {
                //ESPECIFICAR LA COLUMNA
                Object fila[] = {rs.getString(1), rs.getString(2), rs.getString(3),
                    rs.getString(4), rs.getString(5), rs.getString(6),
                    rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), 
                    rs.getString(11), rs.getString(12),rs.getString(13), rs.getString(14), rs.getString(15)};
                tabla.addRow(fila);
            }
        } catch (SQLException e) {
            JOptionPane.showConfirmDialog(null, e, "No se pudo recuperar", CLOSED_OPTION);
        }
    }

    public void limpiarTabla() {
        int rowCount = tableModel.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            tableModel.removeRow(i);
        }
    }

    DefaultTableModel tableModel;
    DefaultTableModel tabla = new DefaultTableModel();
    public Object select[] = new Object[15];
    public int filaSelec;

    public void movement() {
        AnimationClass all = new AnimationClass();
        //LOGO
        all.jLabelYUp(410, 10, 5, 2, jLabel1);
        //ARRIBA
        all.jLabelYDown(-25, 50, 5, 2, jLabel2);
        //ID PRODUCTO
        all.jLabelYDown(-25, 80, 5, 2, jLabel3);
        all.jTextFieldYDown(-25, 100, 5, 2, jTextField1);
        //PRODUCTO
        all.jLabelYDown(-25, 130, 5, 2, jLabel4);
        all.jTextFieldYDown(-25, 150, 5, 2, jTextField2);
        //CATEGORIA
        all.jLabelYDown(-25, 180, 5, 2, jLabel5);
        //PROVEEDOR
        all.jLabelYDown(-25, 230, 5, 2, jLabel6);
        //ALMACEN
        all.jLabelYDown(-25, 280, 5, 2, jLabel7);
        //DIRECC ALMACEN
        all.jLabelYDown(-25, 330, 5, 2, jLabel8);
        //DESCRIPCION
        all.jLabelYDown(-25, 80, 5, 2, jLabel9);
        all.jTextFieldYDown(-25, 100, 5, 2, jTextField3);
        //PRECIO
        all.jLabelYDown(-25, 130, 5, 2, jLabel10);
        all.jTextFieldYDown(-25, 150, 5, 2, jTextField4);
        //STOCK MIN
        all.jLabelYDown(-25, 180, 5, 2, jLabel11);
        all.jTextFieldYDown(-25, 200, 5, 2, jTextField5);
        //STOCK MAX
        all.jLabelYDown(-25, 230, 5, 2, jLabel12);
        all.jTextFieldYDown(-25, 250, 5, 2, jTextField6);
        //STOCK
        all.jLabelYDown(-25, 280, 5, 2, jLabel13);
        all.jTextFieldYDown(-25, 300, 5, 2, jTextField7);
        //MARCA
        all.jLabelYDown(-25, 330, 5, 2, jLabel14);
        all.jTextFieldYDown(-25, 350, 5, 2, jTextField8);
        //PRECIO COMPRA
        all.jLabelYDown(-25, 80, 5, 2, jLabel15);
        all.jTextFieldYDown(-25, 100, 5, 2, jTextField9);
        //FECHA VENCIMECINTO
        all.jLabelYDown(-25, 130, 5, 2, jLabel16);
        //MARCA
        all.jLabelYDown(-25, 180, 5, 2, jLabel17);
        all.jTextFieldYDown(-25, 200, 5, 2, jTextField10);
        //TAMAÑO
        all.jLabelYDown(-25, 230, 5, 2, jLabel18);
        all.jTextFieldYDown(-25, 250, 5, 2, jTextField11);
        //PESO
        all.jLabelYDown(-25, 280, 5, 2, jLabel19);
        all.jTextFieldYDown(-25, 300, 5, 2, jTextField12);
        //COD BARRAS
        all.jLabelYDown(-75, 100, 5, 2, jLabel20);
        all.jLabelYDown(-25, 80, 5, 2, jLabel21);
        all.jLabelYDown(-25, 170, 5, 2, jLabel22);

        //ABAJO
        all.jButtonYUp(410, 10, 5, 2, Regresar);
        all.jButtonYUp(410, 340, 5, 2, btn_tabla);
        all.jButtonYUp(410, 340, 5, 2, btn_editar);
        all.jButtonYUp(410, 340, 5, 2, btn_guardar);
    }

    public P3_PRODUCTOS() {
        initComponents();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        rsscalelabel.RSScaleLabel.setScaleLabel(jLabel1, "src/ICONOS/logo-plazavea.png");
        jDateChooser1.setOpaque(false);
        jLabel22.setOpaque(false);
        jPanel2.setVisible(false);
        consultarData();
        movement();

        jTable1.setModel(tabla);
        tableModel = (DefaultTableModel) jTable1.getModel();
        //jTable1.setOpaque(false);

        tabla.addColumn("ID Producto");
        tabla.addColumn("Producto");
        tabla.addColumn("ID Cargo");
        tabla.addColumn("ID Proveedor");
        tabla.addColumn("ID Almacne");
        tabla.addColumn("ID Direcc alm.");
        tabla.addColumn("Subcategoria");
        tabla.addColumn("Descripcion");
        tabla.addColumn("Vencimiento");
        tabla.addColumn("Moneda");
        tabla.addColumn("Cod. barras");
        tabla.addColumn("Stock");
        tabla.addColumn("Stock min");
        tabla.addColumn("Stock max");
        tabla.addColumn("Precio Compra");
        tabla.addColumn("Precio");
        tabla.addColumn("Marca");
        tabla.addColumn("Estado");
        tabla.addColumn("Tamaño");
        tabla.addColumn("Peso");
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
        jLabel6 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel17 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jTextField12 = new javax.swing.JTextField();
        jComboBox5 = new javax.swing.JComboBox<>();
        btn_editar = new javax.swing.JButton();
        btn_tabla = new javax.swing.JButton();
        Regresar = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jPanel2 = new FondoPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton7 = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jTextField13 = new javax.swing.JTextField();
        jButton9 = new javax.swing.JButton();
        btn_guardar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(null);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setPreferredSize(new java.awt.Dimension(1200, 373));
        jPanel1.add(jLabel1);
        jLabel1.setBounds(10, 410, 90, 30);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("PRODUCTOS");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(20, -25, 100, 17);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("ID Producto:");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(30, -25, 80, 15);

        jTextField1.setEditable(false);
        jTextField1.setBackground(new java.awt.Color(255, 235, 198));
        jTextField1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTextField1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel1.add(jTextField1);
        jTextField1.setBounds(30, -25, 130, 16);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Producto:");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(30, -25, 70, 15);

        jTextField2.setBackground(new java.awt.Color(255, 235, 198));
        jTextField2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTextField2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel1.add(jTextField2);
        jTextField2.setBounds(30, -25, 130, 16);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Categoría:");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(30, -25, 70, 15);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Proveedor:");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(30, -25, 70, 15);

        jComboBox1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "" }));
        jComboBox1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel1.add(jComboBox1);
        jComboBox1.setBounds(30, 200, 130, 20);

        jComboBox2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "" }));
        jComboBox2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel1.add(jComboBox2);
        jComboBox2.setBounds(30, 250, 130, 20);

        jComboBox3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "" }));
        jComboBox3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel1.add(jComboBox3);
        jComboBox3.setBounds(30, 300, 130, 20);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Almacén:");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(30, -25, 60, 15);

        jComboBox4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "" }));
        jComboBox4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel1.add(jComboBox4);
        jComboBox4.setBounds(30, 350, 130, 20);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Dirección alm:");
        jPanel1.add(jLabel8);
        jLabel8.setBounds(30, -25, 90, 15);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("Descripción:");
        jPanel1.add(jLabel9);
        jLabel9.setBounds(250, -25, 80, 15);

        jTextField3.setBackground(new java.awt.Color(255, 235, 198));
        jTextField3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTextField3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel1.add(jTextField3);
        jTextField3.setBounds(250, -25, 130, 16);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("Precio:");
        jPanel1.add(jLabel10);
        jLabel10.setBounds(250, -25, 50, 15);

        jTextField4.setBackground(new java.awt.Color(255, 235, 198));
        jTextField4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTextField4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel1.add(jTextField4);
        jTextField4.setBounds(250, -25, 130, 16);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setText("Stock mínimo:");
        jPanel1.add(jLabel11);
        jLabel11.setBounds(250, -25, 90, 15);

        jTextField5.setBackground(new java.awt.Color(255, 235, 198));
        jTextField5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTextField5.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel1.add(jTextField5);
        jTextField5.setBounds(250, -25, 130, 16);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("Stock máximo:");
        jPanel1.add(jLabel12);
        jLabel12.setBounds(250, -25, 90, 15);

        jTextField6.setBackground(new java.awt.Color(255, 235, 198));
        jTextField6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTextField6.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel1.add(jTextField6);
        jTextField6.setBounds(250, -25, 130, 16);

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setText("Stock:");
        jPanel1.add(jLabel13);
        jLabel13.setBounds(250, -25, 40, 15);

        jTextField7.setBackground(new java.awt.Color(255, 235, 198));
        jTextField7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTextField7.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel1.add(jTextField7);
        jTextField7.setBounds(250, -25, 130, 16);

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setText("Marca:");
        jPanel1.add(jLabel14);
        jLabel14.setBounds(250, -25, 50, 15);

        jTextField8.setBackground(new java.awt.Color(255, 235, 198));
        jTextField8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTextField8.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel1.add(jTextField8);
        jTextField8.setBounds(250, -25, 130, 16);

        jTextField9.setBackground(new java.awt.Color(255, 235, 198));
        jTextField9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTextField9.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel1.add(jTextField9);
        jTextField9.setBounds(480, -25, 130, 16);

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setText("Precio compra:");
        jPanel1.add(jLabel15);
        jLabel15.setBounds(480, -25, 100, 15);

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel16.setText("Fecha vencimiento:");
        jPanel1.add(jLabel16);
        jLabel16.setBounds(480, -25, 120, 15);

        jDateChooser1.setBackground(new java.awt.Color(255, 255, 255));
        jDateChooser1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jDateChooser1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jPanel1.add(jDateChooser1);
        jDateChooser1.setBounds(480, 150, 130, 24);

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel17.setText("Marca:");
        jPanel1.add(jLabel17);
        jLabel17.setBounds(480, -25, 50, 15);

        jTextField10.setBackground(new java.awt.Color(255, 235, 198));
        jTextField10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTextField10.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel1.add(jTextField10);
        jTextField10.setBounds(480, -25, 130, 16);

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel18.setText("Tamaño:");
        jPanel1.add(jLabel18);
        jLabel18.setBounds(480, -25, 60, 15);

        jTextField11.setBackground(new java.awt.Color(255, 235, 198));
        jTextField11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTextField11.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel1.add(jTextField11);
        jTextField11.setBounds(480, -25, 130, 16);

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel19.setText("Peso:");
        jPanel1.add(jLabel19);
        jLabel19.setBounds(480, -25, 40, 15);

        jTextField12.setBackground(new java.awt.Color(255, 235, 198));
        jTextField12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTextField12.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel1.add(jTextField12);
        jTextField12.setBounds(480, -25, 60, 16);

        jComboBox5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "" }));
        jPanel1.add(jComboBox5);
        jComboBox5.setBounds(550, 300, 60, 20);

        btn_editar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONOS/editarUNOx32.png"))); // NOI18N
        btn_editar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn_editar.setBorderPainted(false);
        btn_editar.setContentAreaFilled(false);
        btn_editar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_editar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONOS/editarDOSx32.png"))); // NOI18N
        jPanel1.add(btn_editar);
        btn_editar.setBounds(520, 410, 32, 32);

        btn_tabla.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONOS/tablaUNOx32.png"))); // NOI18N
        btn_tabla.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn_tabla.setBorderPainted(false);
        btn_tabla.setContentAreaFilled(false);
        btn_tabla.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_tabla.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONOS/tablaDOSx32.png"))); // NOI18N
        btn_tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_tablaMouseClicked(evt);
            }
        });
        jPanel1.add(btn_tabla);
        btn_tabla.setBounds(700, 410, 32, 32);

        Regresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONOS/atrasUNOx32.png"))); // NOI18N
        Regresar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Regresar.setBorderPainted(false);
        Regresar.setContentAreaFilled(false);
        Regresar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Regresar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONOS/atrasDOSx32.png"))); // NOI18N
        Regresar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RegresarMouseClicked(evt);
            }
        });
        jPanel1.add(Regresar);
        Regresar.setBounds(760, 410, 32, 32);

        jLabel20.setText("jLabel20");
        jPanel1.add(jLabel20);
        jLabel20.setBounds(660, -75, 110, 70);

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel21.setText("Código de barras:");
        jPanel1.add(jLabel21);
        jLabel21.setBounds(660, -25, 110, 15);

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("987456532");
        jLabel22.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel1.add(jLabel22);
        jLabel22.setBounds(660, -25, 110, 16);

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
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane1.setViewportView(jTable1);

        jPanel2.add(jScrollPane1);
        jScrollPane1.setBounds(20, 50, 760, 220);

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
        jLabel23.setBounds(320, 270, 170, 17);

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
        jTextField13.setBounds(130, 270, 130, 16);

        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONOS/buscarx32.png"))); // NOI18N
        jButton9.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton9.setBorderPainted(false);
        jButton9.setContentAreaFilled(false);
        jButton9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel2.add(jButton9);
        jButton9.setBounds(270, 270, 32, 32);

        jPanel1.add(jPanel2);
        jPanel2.setBounds(0, 60, 800, 290);

        btn_guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONOS/limpiarUNOx32.png"))); // NOI18N
        btn_guardar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn_guardar.setBorderPainted(false);
        btn_guardar.setContentAreaFilled(false);
        btn_guardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_guardar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONOS/limpiarDOSx32.png"))); // NOI18N
        btn_guardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_guardarMouseClicked(evt);
            }
        });
        jPanel1.add(btn_guardar);
        btn_guardar.setBounds(580, 410, 32, 32);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void RegresarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RegresarMouseClicked
        // TODO add your handling code here:
        MAIN main = new MAIN();
        main.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_RegresarMouseClicked

    //VER TABLA
    private void btn_tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_tablaMouseClicked
        // TODO add your handling code here:
        TABLA_PROD tabla = new TABLA_PROD();
        tabla.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_tablaMouseClicked
    //OCULTAR TABLA
    private void jButton7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton7MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7MouseClicked

    private void btn_guardarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_guardarMouseClicked
        // TODO add your handling code here:


    }//GEN-LAST:event_btn_guardarMouseClicked

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
            java.util.logging.Logger.getLogger(P3_PRODUCTOS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(P3_PRODUCTOS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(P3_PRODUCTOS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(P3_PRODUCTOS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new P3_PRODUCTOS().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Regresar;
    private javax.swing.JButton btn_editar;
    private javax.swing.JButton btn_guardar;
    private javax.swing.JButton btn_tabla;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
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
