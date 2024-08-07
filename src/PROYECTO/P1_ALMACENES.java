package PROYECTO;

import AppPackage.AnimationClass;
import java.awt.Graphics;
import java.awt.Image;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.CLOSED_OPTION;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import java.sql.PreparedStatement;

public class P1_ALMACENES extends javax.swing.JFrame {

    FondoPanel fondo = new FondoPanel();
    int clmbus = 0;
    String origID;
    boolean edition;

    static private Connection con = null;
    static private Statement stmt = null;
    static private ResultSet rs = null;

    public static Connection conectar() {
        String cadena = "jdbc:sqlserver://localhost:1433;databaseName=PlazaVeaBruh3;user=sa;password=root;logingTimeout=10";
        try {
            con = DriverManager.getConnection(cadena);
            return con;
        } catch (SQLException e) {
            JOptionPane.showConfirmDialog(null, "No se pudo conectar" + e);
            return null;
        }
    }

    DefaultTableModel tableModel;
    DefaultTableModel tabla = new DefaultTableModel();
    public Object select[] = new Object[12];
    public int filaSelec;

    public void movement() {
        AnimationClass all = new AnimationClass();
        //LOGO
        all.jLabelYUp(320, 10, 5, 2, jLabel1);
        //ARRIBA
        all.jLabelYDown(-250, 50, 5, 2, jLabel2);
        //codigo
        all.jLabelYDown(-210, 90, 5, 2, jLabel7);
        all.jTextFieldYDown(-210, 90, 5, 2, txt_codigo);
        //nom almacen
        all.jLabelYDown(-170, 130, 5, 2, jLabel4);
        all.jTextFieldYDown(-150, 150, 5, 2, txt_almacen);
        //direccion
        all.jLabelYDown(-120, 180, 5, 2, jLabel5);
        all.jTextFieldYDown(-100, 200, 5, 2, txt_direccion);
        //numero almacen
        all.jLabelYDown(-70, 230, 5, 2, jLabel6);
        all.jTextFieldYDown(-50, 250, 5, 2, txt_num_Alm);
        //encargado
        all.jLabelYDown(-20, 280, 5, 2, jLabel3);
        //busqueda
        all.jTextFieldYDown(-50, 70, 5, 2, txt_busqueda);

        //ABAJO
        all.jButtonYUp(380, 60, 5, 2, Buscar);
        all.jButtonYUp(340, 280, 5, 2, btn_Editar);
        all.jButtonYUp(340, 280, 5, 2, btn_Guardar);
        all.jButtonYUp(340, 280, 5, 2, btn_Eliminar);
        all.jButtonYUp(340, 10, 5, 2, jButton3);
        all.jButtonYUp(340, 260, 5, 2, btn_Limpiar);
    }

    public void idEmpl() {
        try {
            stmt = P1_ALMACENES.conectar().createStatement();
            String emple = "SELECT NOMBRE FROM TB_EMPLEADO ;";
            rs = stmt.executeQuery(emple);
            while (rs.next()) {
                //PARA ESTE NO HAY NECESIDAD DE ESPECIFICAR LA COLUMNA             
                BOX_encargado.addItem(rs.getString(1));
            }
        } catch (SQLException e) {
            JOptionPane.showConfirmDialog(null, e, "No se pudo recuperar", CLOSED_OPTION);
        }
    }

    public P1_ALMACENES() {
        initComponents();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        rsscalelabel.RSScaleLabel.setScaleLabel(jLabel1, "src/ICONOS/logo-plazavea.png");
        movement();
        idEmpl();

        try {
            GetAlmacen();
        } catch (SQLException ex) {
            Logger.getLogger(P1_ALMACENES.class.getName()).log(Level.SEVERE, null, ex);
        }

//        tb_almacen.setModel(tabla);
//        tableModel = (DefaultTableModel) tb_almacen.getModel();
//        //jTable1.setOpaque(false);
//
//        tabla.addColumn("ID");
//        tabla.addColumn("Nombre");
//        tabla.addColumn("Dirección");
//        tabla.addColumn("Número");
//        tabla.addColumn("Encargado");
    }

    private void GetAlmacen() throws SQLException {
        stmt = P1_ALMACENES.conectar().createStatement();
        ResultSet counter = stmt.executeQuery("SELECT * FROM TB_ALMACEN;");

        int count = 0;
        while (counter.next()) {
            count++;
        }

        String list[][] = new String[count][5];
        int i = 0;
        rs = stmt.executeQuery("SELECT * FROM TB_ALMACEN;");
        while (rs.next()) {
            list[i][0] = rs.getString("ID_ALMACEN");
            list[i][1] = rs.getString("NOM_ALMACEN");
            list[i][2] = rs.getString("DIRECCION");
            list[i][3] = rs.getString("NUM_ALMACEN");
            list[i][4] = rs.getString("SUPERVISOR_ALMACEN");
            i++;
        }

        tb_almacen.setModel(new javax.swing.table.DefaultTableModel(
                list,
                new String[]{
                    "Codigo", "Nombre", "Dirección", "Numero", "Encargado"
                }));
    }

    private void lookAlmacen() throws SQLException {
        String buscar = txt_busqueda.getText();
        stmt = P1_ALMACENES.conectar().createStatement();
        ResultSet counter = stmt.executeQuery("SELECT * FROM TB_ALMACEN WHERE  NOM_ALMACEN LIKE '" + buscar + "%';");

        int count = 0;
        while (counter.next()) {
            count++;
        }

        String list[][] = new String[count][5];
        int i = 0;
        rs = stmt.executeQuery("SELECT * FROM TB_ALMACEN WHERE  NOM_ALMACEN LIKE '" + buscar + "%';");
        if (rs.next()) {
            list[i][0] = rs.getString("ID_ALMACEN");
            list[i][1] = rs.getString("NOM_ALMACEN");
            list[i][2] = rs.getString("DIRECCION");
            list[i][3] = rs.getString("NUM_ALMACEN");
            list[i][4] = rs.getString("SUPERVISOR_ALMACEN");
            i++;
            txt_busqueda.setText("");
        } else {
            JOptionPane.showMessageDialog(null, "Dato no encontrado", "Datos incorrectos", JOptionPane.WARNING_MESSAGE);
            txt_busqueda.setText("");
        }

        tb_almacen.setModel(new javax.swing.table.DefaultTableModel(
                list,
                new String[]{
                    "Codigo", "Nombre", "Dirección", "Numero", "Encargado"
                }));
    }

    private void inserAlmacen(String codigo, String nombre, String direccion, String numero, String encargado) throws SQLException {
        stmt = P1_ALMACENES.conectar().createStatement();
        stmt.executeUpdate("INSERT INTO  TB_ALMACEN (ID_ALMACEN, NOM_ALMACEN, DIRECCION, NUM_ALMACEN, SUPERVISOR_ALMACEN)"
                + "VALUES ('" + codigo + "', '" + nombre + "', '" + direccion + "', '" + numero + "', '" + encargado + "');");
        JOptionPane.showMessageDialog(this, "¡Almacen registrado correctamente! \n", "HECHO", javax.swing.JOptionPane.INFORMATION_MESSAGE);
        try {
            GetAlmacen();
        } catch (SQLException ex) {
            Logger.getLogger(P1_ALMACENES.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void UpdateAlmacen(String codigo, String nombre, String direccion, String numero, String encargado) throws SQLException {
        stmt = P1_ALMACENES.conectar().createStatement();
        stmt.executeUpdate("UPDATE TB_ALMACEN SET ID_ALMACEN = '" + codigo + "', NOM_ALMACEN = '" + nombre + ", DIRECCION = '" + direccion + "',"
                + "NUM_ALMACEN = '" + numero + "',SUPERVISOR_ALMACEN = '" + encargado + "' WHERE ID_ALMACEN = '" + codigo + "';");
        javax.swing.JOptionPane.showMessageDialog(this, "¡Almacen editado correctamente! \n", "HECHO", javax.swing.JOptionPane.INFORMATION_MESSAGE);
        try {
            GetAlmacen();
        } catch (SQLException ex) {
            Logger.getLogger(P1_ALMACENES.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void DeleteAlmacen() throws SQLException {
        try {
            int idcell = tb_almacen.getSelectedRow();
            if (idcell <= -1) {
                javax.swing.JOptionPane.showMessageDialog(this, "Debe seleccionar un almacen a eliminar. \n", "AVISO", javax.swing.JOptionPane.WARNING_MESSAGE);
            } else {
                stmt = P1_ALMACENES.conectar().createStatement();
                ResultSet counter = stmt.executeQuery("SELECT * FROM TB_ALMACEN;");

                int count = 0;
                while (counter.next()) {
                    count++;
                }

                String list[][] = new String[count][7];
                int i = 0;
                ResultSet rs = stmt.executeQuery("SELECT * FROM TB_ALMACEN;");
                while (rs.next()) {
                    list[i][0] = rs.getString("ID_ALMACEN");
                    list[i][1] = rs.getString("NOM_ALMACEN");
                    list[i][2] = rs.getString("DIRECCION");
                    list[i][3] = rs.getString("NUM_ALMACEN");
                    list[i][4] = rs.getString("SUPERVISOR_ALMACEN");
                    i++;
                }
                //int id = Integer.parseInt(list[idcell][0]);
                String ID = (String) list[idcell][0];
                if (ID.equals(" ")) {
                    javax.swing.JOptionPane.showMessageDialog(this, "Debe seleccionar el almacen a borrar. \n", "AVISO", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                } else {
                    Statement stm2 = null;
                    try {
                        stm2 = P1_ALMACENES.conectar().createStatement();
                    } catch (SQLException ex) {
                        Logger.getLogger(P1_ALMACENES.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        stm2.executeUpdate("DELETE FROM TB_ALMACEN WHERE ID_ALMACEN = '" + ID + "';");
                        javax.swing.JOptionPane.showMessageDialog(this, "¡Almacen borrado! \n", "HECHO", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                        GetAlmacen();
                    } catch (SQLException ex) {
                        Logger.getLogger(P1_ALMACENES.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(P1_ALMACENES.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void Limpiar() {
        txt_almacen.setText("");
        txt_codigo.setText("");
        txt_num_Alm.setText("");
        txt_busqueda.setText("");
        txt_direccion.setText("");
        BOX_encargado.setSelectedItem(null);
    }

    public static String GenerarCodigo(String palabra) {
        String[] palabras = palabra.split(" ");

        if (palabras.length == 1) {
            // Si es una sola palabra, elegir la primera letra seguida de las dos últimas
            String primeraLetra = palabra.substring(0, 2);
            String ultimasDosLetras = palabra.substring(palabra.length() - 2);
            return (primeraLetra).toUpperCase();
        } else {
            // Obtener las primeras letras de cada palabra en mayúscula
            StringBuilder abreviacion = new StringBuilder();

            for (String p : palabras) {
                if (!p.isEmpty()) {
                    abreviacion.append(p.charAt(0));
                }
            }

            return abreviacion.toString().toUpperCase();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new FondoPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_codigo = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txt_almacen = new javax.swing.JTextField();
        txt_direccion = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txt_num_Alm = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        BOX_encargado = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_almacen = new javax.swing.JTable();
        txt_busqueda = new javax.swing.JTextField();
        btn_Eliminar = new javax.swing.JButton();
        Buscar = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        btn_Guardar = new javax.swing.JButton();
        btn_Editar = new javax.swing.JButton();
        btn_Limpiar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(null);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setPreferredSize(new java.awt.Dimension(1200, 373));
        jPanel1.add(jLabel1);
        jLabel1.setBounds(10, 320, 90, 30);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("ALMACEN");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(20, -250, 70, 17);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Encargado:");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(20, -20, 70, 15);

        txt_codigo.setEditable(false);
        txt_codigo.setBackground(new java.awt.Color(255, 235, 198));
        txt_codigo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txt_codigo.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel1.add(txt_codigo);
        txt_codigo.setBounds(70, -210, 110, 16);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Nombre del almacén:");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(20, -170, 130, 15);

        txt_almacen.setBackground(new java.awt.Color(255, 235, 198));
        txt_almacen.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txt_almacen.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel1.add(txt_almacen);
        txt_almacen.setBounds(20, -150, 160, 16);

        txt_direccion.setBackground(new java.awt.Color(255, 235, 198));
        txt_direccion.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txt_direccion.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel1.add(txt_direccion);
        txt_direccion.setBounds(20, -100, 160, 16);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Dirección:");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(20, -120, 70, 15);

        txt_num_Alm.setBackground(new java.awt.Color(255, 235, 198));
        txt_num_Alm.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txt_num_Alm.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel1.add(txt_num_Alm);
        txt_num_Alm.setBounds(20, -50, 160, 16);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Numero de almacén:");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(20, -70, 130, 15);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Código:");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(20, -210, 50, 15);

        BOX_encargado.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BOX_encargado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "" }));
        jPanel1.add(BOX_encargado);
        BOX_encargado.setBounds(90, 280, 90, 20);

        tb_almacen.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tb_almacen.setModel(new javax.swing.table.DefaultTableModel(
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
        tb_almacen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_almacenMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb_almacen);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(220, 110, 452, 140);

        txt_busqueda.setBackground(new java.awt.Color(255, 235, 198));
        txt_busqueda.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txt_busqueda.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_busqueda.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel1.add(txt_busqueda);
        txt_busqueda.setBounds(470, -50, 120, 16);

        btn_Eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONOS/eliminarUNOx32.png"))); // NOI18N
        btn_Eliminar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn_Eliminar.setBorderPainted(false);
        btn_Eliminar.setContentAreaFilled(false);
        btn_Eliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_Eliminar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONOS/eliminarDOSx32.png"))); // NOI18N
        btn_Eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_EliminarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_Eliminar);
        btn_Eliminar.setBounds(600, 340, 32, 32);

        Buscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONOS/buscarx32.png"))); // NOI18N
        Buscar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Buscar.setBorderPainted(false);
        Buscar.setContentAreaFilled(false);
        Buscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuscarActionPerformed(evt);
            }
        });
        jPanel1.add(Buscar);
        Buscar.setBounds(600, 380, 32, 32);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONOS/atrasUNOx32.png"))); // NOI18N
        jButton3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton3.setBorderPainted(false);
        jButton3.setContentAreaFilled(false);
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONOS/atrasDOSx32.png"))); // NOI18N
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });
        jPanel1.add(jButton3);
        jButton3.setBounds(640, 340, 32, 32);

        btn_Guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONOS/guardarUNOx32.png"))); // NOI18N
        btn_Guardar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn_Guardar.setBorderPainted(false);
        btn_Guardar.setContentAreaFilled(false);
        btn_Guardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_Guardar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONOS/guardarDOSx32.png"))); // NOI18N
        btn_Guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_GuardarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_Guardar);
        btn_Guardar.setBounds(540, 340, 32, 32);

        btn_Editar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONOS/editarUNOx32.png"))); // NOI18N
        btn_Editar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn_Editar.setBorderPainted(false);
        btn_Editar.setContentAreaFilled(false);
        btn_Editar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_Editar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONOS/editarDOSx32.png"))); // NOI18N
        btn_Editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_EditarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_Editar);
        btn_Editar.setBounds(480, 340, 32, 32);

        btn_Limpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONOS/limpiarUNOx32.png"))); // NOI18N
        btn_Limpiar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn_Limpiar.setBorderPainted(false);
        btn_Limpiar.setContentAreaFilled(false);
        btn_Limpiar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_Limpiar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONOS/limpiarDOSx32.png"))); // NOI18N
        btn_Limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LimpiarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_Limpiar);
        btn_Limpiar.setBounds(230, 340, 32, 32);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 686, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
        // TODO add your handling code here:
        MAIN main = new MAIN();
        main.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton3MouseClicked

    private void BuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscarActionPerformed

        try {
            // Buscar datos
            lookAlmacen();
        } catch (SQLException ex) {
            Logger.getLogger(P1_ALMACENES.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BuscarActionPerformed

    private void btn_GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_GuardarActionPerformed

        if (txt_almacen.getText().equals("") || txt_direccion.getText().equals("") || txt_num_Alm.getText().equals("") || BOX_encargado.getSelectedItem().equals("")) {
            javax.swing.JOptionPane.showMessageDialog(this, "Debe llenar todos los campos \n", "AVISO", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            txt_almacen.requestFocus();
        } else {
            String nombre = txt_almacen.getText();
            String direccion = txt_direccion.getText();
            String numero = txt_num_Alm.getText();
            String encargado = (String) BOX_encargado.getSelectedItem();
            String codigo = GenerarCodigo(nombre) + "0" + GenerarCodigo(numero);

            if (nombre == null || "".equals(nombre) || direccion == null || "".equals(direccion) || numero == null || "".equals(numero) || encargado == null || "".equals(encargado)) {
                javax.swing.JOptionPane.showMessageDialog(this, "Debe llenar todos los campos \n", "AVISO", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                txt_almacen.requestFocus();
            } else {

                try {
                    for (int i = 0; i < tb_almacen.getRowCount(); i++) {
                        String valor = tb_almacen.getValueAt(i, clmbus).toString();
                        if (codigo.equals(valor)) {
                            UpdateAlmacen(codigo, nombre, direccion, numero, encargado);
                            edition = true; // Marcar que se encontró una coincidencia
                            break; // Salir del bucle
                        }
                    }
                    if (!edition) {
                        inserAlmacen(codigo, nombre, direccion, numero, encargado);
                    }

                    Limpiar();

                } catch (SQLException ex) {
                    Logger.getLogger(P1_ALMACENES.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_btn_GuardarActionPerformed

    private void btn_EditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_EditarActionPerformed
//       
        String codigo = txt_codigo.getText();
        String nom = txt_almacen.getText();
        String direcc = txt_direccion.getText();
        String num = txt_num_Alm.getText();
        String cargo = BOX_encargado.getSelectedItem().toString();

        String acSA = "UPDATE TB_ALMACEN SET ID_ALMACEN='" + codigo + "', NOM_ALMACEN='" + nom + "', DIRECCION='" + direcc + "',\n"
                + "NUM_ALMACEN='" + num + "', SUPERVISOR_ALMACEN='" + cargo + "'\n"
                + "WHERE ID_ALMACEN='" + codigo + "'";
        PreparedStatement pre1 = null;
        try {
            pre1 = con.prepareStatement(acSA);
            pre1.executeUpdate();
            JOptionPane.showMessageDialog(null, "Dato editado");
            GetAlmacen();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

//try {
//            int idcell = tb_almacen.getSelectedRow();
//            if (idcell <= -1) {
//                javax.swing.JOptionPane.showMessageDialog(this, "Debe seleccionar el almacen a editar. \n", "AVISO", javax.swing.JOptionPane.INFORMATION_MESSAGE);
//            } else {
//                stmt = P1_ALMACENES.conectar().createStatement();
//                ResultSet counter = stmt.executeQuery("SELECT * FROM TB_ALMACEN;");
//
//                int count = 0;
//                while (counter.next()) {
//                    count++;
//                }
//
//                String list[][] = new String[count][6];
//                int i = 0;
//                rs = stmt.executeQuery("SELECT * FROM TB_ALMACEN;");
//                while (rs.next()) {
//                    list[i][0] = rs.getString("ID_ALMACEN");
//                    list[i][1] = rs.getString("NOM_ALMACEN");
//                    list[i][2] = rs.getString("DIRECCION");
//                    list[i][3] = rs.getString("NUM_ALMACEN");
//                    list[i][4] = rs.getString("SUPERVISOR_ALMACEN");
//                    i++;
//                }
//                //int id = Integer.parseInt(list[idcell][0]);
//                String ID = (String) list[idcell][0];
//                if (ID.equals("")) {
//                    javax.swing.JOptionPane.showMessageDialog(this, "Debe seleccionar el Almacen a editar. \n", "AVISO", javax.swing.JOptionPane.INFORMATION_MESSAGE);
//                } else {
//                    String COD_ALM = "" + ID;
//                    String NOM_ALM = list[idcell][1];
//                    String DIR_ALM = list[idcell][2];
//                    String NUM_ALM = list[idcell][3];
//                    String COD_EMPL = list[idcell][5];
//
//                    txt_codigo.setText(COD_ALM);
//                    txt_almacen.setText(NOM_ALM);
//                    txt_direccion.setText(DIR_ALM);
//                    txt_num_Alm.setText(NUM_ALM);
//                    BOX_encargado.setSelectedItem(COD_EMPL);
//
//                }
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(P1_ALMACENES.class.getName()).log(Level.SEVERE, null, ex);
//        }

    }//GEN-LAST:event_btn_EditarActionPerformed

    private void btn_LimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LimpiarActionPerformed
        // TODO add your handling code here:
        Limpiar();
    }//GEN-LAST:event_btn_LimpiarActionPerformed

    private void btn_EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_EliminarActionPerformed
        try {
            DeleteAlmacen();
        } catch (SQLException ex) {
            Logger.getLogger(P1_ALMACENES.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_EliminarActionPerformed

    private void tb_almacenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_almacenMouseClicked
        // TODO add your handling code here:
        filaSelec = tb_almacen.getSelectedRow();
        select[0] = tb_almacen.getValueAt(filaSelec, 0);
        select[1] = tb_almacen.getValueAt(filaSelec, 1);
        select[2] = tb_almacen.getValueAt(filaSelec, 2);
        select[3] = tb_almacen.getValueAt(filaSelec, 3);
        select[4] = tb_almacen.getValueAt(filaSelec, 4);

        txt_codigo.setText(select[0].toString());
        txt_almacen.setText(select[1].toString());
        txt_direccion.setText(select[2].toString());
        txt_num_Alm.setText(select[3].toString());
        BOX_encargado.setSelectedItem(select[4]);
    }//GEN-LAST:event_tb_almacenMouseClicked

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
            java.util.logging.Logger.getLogger(P1_ALMACENES.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(P1_ALMACENES.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(P1_ALMACENES.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(P1_ALMACENES.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new P1_ALMACENES().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> BOX_encargado;
    private javax.swing.JButton Buscar;
    private javax.swing.JButton btn_Editar;
    private javax.swing.JButton btn_Eliminar;
    private javax.swing.JButton btn_Guardar;
    private javax.swing.JButton btn_Limpiar;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tb_almacen;
    private javax.swing.JTextField txt_almacen;
    private javax.swing.JTextField txt_busqueda;
    private javax.swing.JTextField txt_codigo;
    private javax.swing.JTextField txt_direccion;
    private javax.swing.JTextField txt_num_Alm;
    // End of variables declaration//GEN-END:variables

    class FondoPanel extends JPanel {

        private Image imagen;

        @Override
        public void paint(Graphics g) {
            imagen = new ImageIcon(getClass().getResource("/FONDOS/LOCAL2.png")).getImage();
            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
            setOpaque(false);

            super.paint(g);
        }
    }

}
