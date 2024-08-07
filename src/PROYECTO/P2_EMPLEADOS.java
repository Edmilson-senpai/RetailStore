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
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

public class P2_EMPLEADOS extends javax.swing.JFrame {

    FondoPanel fondo = new FondoPanel();
    String origID;
    boolean edition = false;
    int clmbus = 0;

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
        all.jLabelYUp(400, 10, 5, 2, jLabel1);
        //ARRIBA
        all.jLabelYDown(-25, 50, 5, 2, jLabel2);
        //ID
        all.jLabelYDown(-25, 80, 5, 2, jLabel4);
        all.jTextFieldYDown(-25, 80, 5, 2, jtxt_Codigo);
        //NOMBRE
        all.jLabelYDown(-25, 110, 5, 2, jLabel3);
        all.jTextFieldYDown(-25, 130, 5, 2, jtxt_Nombre);
        //APELLIDO
        all.jLabelYDown(-25, 160, 5, 2, jLabel5);
        all.jTextFieldYDown(-25, 180, 5, 2, jtxt_Apellido);
        //DNI
        all.jLabelYDown(-25, 210, 5, 2, jLabel7);
        all.jTextFieldYDown(-25, 230, 5, 2, jtxt_DNI);
        //GENERO
        all.jLabelYDown(-25, 260, 5, 2, jLabel6);
        //CARGO
        all.jLabelYDown(-25, 290, 5, 2, jLabel8);
        //DIRECCION
        all.jLabelYDown(-25, 320, 5, 2, jLabel9);
        all.jTextFieldYDown(-25, 340, 5, 2, jtxt_Direccion);
        //BUSQUEDA
        all.jTextFieldYDown(-25, 80, 5, 2, jtxt_busqueda);

        //ABAJO
        all.jButtonYUp(400, 70, 5, 2, btn_Buscar);
        all.jButtonYUp(400, 300, 5, 2, btn_Editar);
        all.jButtonYUp(400, 300, 5, 2, btn_Agregar);
        all.jButtonYUp(400, 300, 5, 2, btn_Eliminar);
        all.jButtonYUp(400, 10, 5, 2, btn_Regrsar);
        all.jButtonYUp(400, 280, 5, 2, btn_Limpiar);
    }

    public P2_EMPLEADOS() {
        initComponents();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        rsscalelabel.RSScaleLabel.setScaleLabel(jLabel1, "src/ICONOS/logo-plazavea.png");
        movement();
        try {
            GetEmpleado();
        } catch (SQLException ex) {
            Logger.getLogger(P2_EMPLEADOS.class.getName()).log(Level.SEVERE, null, ex);
        }

//        jTable1.setModel(tabla);
//        tableModel = (DefaultTableModel) jTable1.getModel();
//        //jTable1.setOpaque(false);
//        
//        tabla.addColumn("ID");
//        tabla.addColumn("Nombre");
//        tabla.addColumn("Apellidos");
//        tabla.addColumn("DNI");
//        tabla.addColumn("Género");
//        tabla.addColumn("Cargo");
//        tabla.addColumn("Dirección");
    }

    private void GetEmpleado() throws SQLException {
        stmt = P2_EMPLEADOS.conectar().createStatement();
        ResultSet counter = stmt.executeQuery("SELECT * FROM TB_EMPLEADO ;");

        int count = 0;
        while (counter.next()) {
            count++;
        }

        String list[][] = new String[count][7];
        int i = 0;
        rs = stmt.executeQuery("SELECT TB_EMPLEADO.*, TB_CARGO.cargo FROM TB_EMPLEADO JOIN TB_CARGO ON TB_EMPLEADO.ID_CARGO = TB_CARGO.ID_CARGO;");
        while (rs.next()) {
            list[i][0] = rs.getString("ID_EMPLEADO");
            list[i][1] = rs.getString("NOMBRE");
            list[i][2] = rs.getString("APELLIDO");
            list[i][3] = rs.getString("SEXO");
            list[i][4] = rs.getString("DNI");
            list[i][5] = rs.getString("DIRECCION");
            list[i][6] = rs.getString("cargo");
            i++;
        }

        tb_empleado.setModel(new javax.swing.table.DefaultTableModel(
                list,
                new String[]{
                    "Codigo", "Nombre", "Apellido", "Genero", "DNI", "Direccion", "Cargo"
                }));
    }

    private void lookEmpleado() throws SQLException {
        String buscar = jtxt_busqueda.getText();
        stmt = P2_EMPLEADOS.conectar().createStatement();
        ResultSet counter = stmt.executeQuery("SELECT TB_EMPLEADO.*, TB_CARGO.cargo FROM TB_EMPLEADO JOIN TB_CARGO ON TB_EMPLEADO.ID_CARGO = TB_CARGO.ID_CARGO WHERE  NOMBRE LIKE '" + buscar + "%';");

        int count = 0;
        while (counter.next()) {
            count++;
        }

        String list[][] = new String[count][7];
        int i = 0;
        rs = stmt.executeQuery("SELECT TB_EMPLEADO.*, TB_CARGO.cargo FROM TB_EMPLEADO JOIN TB_CARGO ON TB_EMPLEADO.ID_CARGO = TB_CARGO.ID_CARGO WHERE  NOMBRE LIKE '" + buscar + "%';");
        if (rs.next()) {
            list[i][0] = rs.getString("ID_EMPLEADO");
            list[i][1] = rs.getString("NOMBRE");
            list[i][2] = rs.getString("APELLIDO");
            list[i][3] = rs.getString("SEXO");
            list[i][4] = rs.getString("DNI");
            list[i][5] = rs.getString("DIRECCION");
            list[i][6] = rs.getString("cargo");
            i++;

            jtxt_busqueda.setText("");
        } else {
            JOptionPane.showMessageDialog(null, "Dato no encontrado", "Datos incorrectos", JOptionPane.WARNING_MESSAGE);
            jtxt_busqueda.setText("");
        }

        tb_empleado.setModel(new javax.swing.table.DefaultTableModel(
                list,
                new String[]{
                    "Codigo", "Nombre", "Apellido", "Genero", "DNI", "Direccion", "Cargo"
                }));
    }

    private void inserEmpleado(String codigo, String nombre, String apellido, int dni, String genero, String cargo, String direccion) throws SQLException {
        stmt = P2_EMPLEADOS.conectar().createStatement();
        stmt.executeUpdate("INSERT INTO TB_EMPLEADO(`ID_EMPLEADO`, `NOMBRE`, `APELLIDO`, `SEXO`, `DNI`, `DIRECCION`, `cargo`)"
                + " VALUES ('" + codigo + "', '" + nombre + "', '" + apellido + "', '" + genero + "','" + dni + "','" + direccion + "','" + cargo + "');");
        JOptionPane.showMessageDialog(this, "¡Empleado registrado correctamente! \n", "HECHO", javax.swing.JOptionPane.INFORMATION_MESSAGE);
        try {
            GetEmpleado();
        } catch (SQLException ex) {
            Logger.getLogger(P2_EMPLEADOS.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void UpdateEmpleado(String codigo, String nombre, String apellido, String genero, String direccion, String cargo, int dni) throws SQLException {
        stmt = P2_EMPLEADOS.conectar().createStatement();
        stmt.executeUpdate("UPDATE TB_EMPLEADO SET `ID_EMPLEADO` = '" + codigo + "', `NOMBRE` = '" + nombre + "', `APELLIDO` = '" + apellido + "',"
                + "`SEXO` = '" + genero + "',`DIRECCION` = '" + direccion + "',`ID_CARGO` = '" + cargo + "',`DNI` = '" + dni + "' WHERE (`ID_EMPLEADO` = '" + origID + "');");
        javax.swing.JOptionPane.showMessageDialog(this, "¡Empleado editado correctamente! \n", "HECHO", javax.swing.JOptionPane.INFORMATION_MESSAGE);
        try {
            GetEmpleado();
        } catch (SQLException ex) {
            Logger.getLogger(P2_EMPLEADOS.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void DeleteEmpleado() throws SQLException {
        try {
            int idcell = tb_empleado.getSelectedRow();
            if (idcell <= -1) {
                javax.swing.JOptionPane.showMessageDialog(this, "Debe seleccionar un Empleado a eliminar. \n", "AVISO", javax.swing.JOptionPane.WARNING_MESSAGE);
            } else {
                stmt = P2_EMPLEADOS.conectar().createStatement();
                ResultSet counter = stmt.executeQuery("SELECT TB_EMPLEADO.*, TB_CARGO.cargo FROM TB_EMPLEADO JOIN TB_CARGO ON TB_EMPLEADO.ID_CARGO = TB_CARGO.ID_CARGO;");

                int count = 0;
                while (counter.next()) {
                    count++;
                }

                String list[][] = new String[count][7];
                int i = 0;
                rs = stmt.executeQuery("SELECT TB_EMPLEADO.*, TB_CARGO.cargo FROM TB_EMPLEADO JOIN TB_CARGO ON TB_EMPLEADO.ID_CARGO = TB_CARGO.ID_CARGO;");
                while (rs.next()) {
                    list[i][0] = rs.getString("ID_EMPLEADO");
                    list[i][1] = rs.getString("NOMBRE");
                    list[i][2] = rs.getString("APELLIDO");
                    list[i][3] = rs.getString("SEXO");
                    list[i][4] = rs.getString("DNI");
                    list[i][5] = rs.getString("DIRECCION");
                    list[i][6] = rs.getString("cargo");
                    i++;

                }
                //int id = Integer.parseInt(list[idcell][0]);
                String ID = (String) list[idcell][0];
                if (ID.equals(" ")) {
                    javax.swing.JOptionPane.showMessageDialog(this, "Debe seleccionar el almacen a borrar. \n", "AVISO", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                } else {
                    Statement stm2 = null;
                    try {
                        stm2 = P2_EMPLEADOS.conectar().createStatement();
                    } catch (SQLException ex) {
                        Logger.getLogger(P2_EMPLEADOS.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        stm2.executeUpdate("DELETE FROM TB_EMPLEADO WHERE ID_EMPLEADO = '" + ID + "'");
                        javax.swing.JOptionPane.showMessageDialog(this, "¡empleado borrado! \n", "HECHO", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                        GetEmpleado();
                    } catch (SQLException ex) {
                        Logger.getLogger(P2_EMPLEADOS.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(P2_EMPLEADOS.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void Limpiar() {
        jtbox_cargo.setSelectedItem(null);
        jtbox_genero.setSelectedItem(null);
        jtxt_Nombre.setText("");
        jtxt_Apellido.setText("");
        jtxt_Codigo.setText("");
        jtxt_Direccion.setText("");
        jtxt_DNI.setText("");
        jtxt_busqueda.setText("");
    }

    public static String PrimerasPalabras(String palabra) {
        String[] palabras = palabra.split(" ");

        if (palabras.length == 1) {
            // Si es una sola palabra, elegir la primera letra seguida de las dos últimas
            String primeradosLetras = palabra.substring(0, 2);
            return (primeradosLetras).toUpperCase();
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

    public static String UltimosDigitos(String numero) {

        String ultimasDosLetras = numero.substring(numero.length() - 2);
        return (ultimasDosLetras).toUpperCase();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new FondoPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jtxt_busqueda = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jtxt_Codigo = new javax.swing.JTextField();
        jtxt_Apellido = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jtxt_DNI = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jtbox_genero = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jtbox_cargo = new javax.swing.JComboBox<>();
        jtxt_Direccion = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_empleado = new javax.swing.JTable();
        jtxt_Nombre = new javax.swing.JTextField();
        btn_Buscar = new javax.swing.JButton();
        btn_Editar = new javax.swing.JButton();
        btn_Agregar = new javax.swing.JButton();
        btn_Eliminar = new javax.swing.JButton();
        btn_Regrsar = new javax.swing.JButton();
        btn_Limpiar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(null);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setPreferredSize(new java.awt.Dimension(1200, 373));
        jPanel1.add(jLabel1);
        jLabel1.setBounds(10, 400, 90, 30);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("EMPLEADOS");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(20, -25, 90, 17);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Nombre:");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(30, -25, 60, 15);

        jtxt_busqueda.setBackground(new java.awt.Color(255, 235, 198));
        jtxt_busqueda.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel1.add(jtxt_busqueda);
        jtxt_busqueda.setBounds(440, -25, 170, 18);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("ID Empleado:");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(30, -25, 80, 15);

        jtxt_Codigo.setEditable(false);
        jtxt_Codigo.setBackground(new java.awt.Color(255, 235, 198));
        jtxt_Codigo.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel1.add(jtxt_Codigo);
        jtxt_Codigo.setBounds(120, -25, 80, 18);

        jtxt_Apellido.setBackground(new java.awt.Color(255, 235, 198));
        jtxt_Apellido.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel1.add(jtxt_Apellido);
        jtxt_Apellido.setBounds(30, -25, 170, 18);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Apellidos:");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(30, -25, 60, 15);

        jtxt_DNI.setBackground(new java.awt.Color(255, 235, 198));
        jtxt_DNI.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel1.add(jtxt_DNI);
        jtxt_DNI.setBounds(30, -25, 170, 18);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Género:");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(30, -25, 50, 15);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("DNI:");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(30, -25, 30, 15);

        jtbox_genero.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jtbox_genero.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "" }));
        jtbox_genero.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel1.add(jtbox_genero);
        jtbox_genero.setBounds(90, 260, 110, 20);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Cargo:");
        jPanel1.add(jLabel8);
        jLabel8.setBounds(30, -25, 40, 15);

        jtbox_cargo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jtbox_cargo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "" }));
        jtbox_cargo.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel1.add(jtbox_cargo);
        jtbox_cargo.setBounds(90, 290, 110, 20);

        jtxt_Direccion.setBackground(new java.awt.Color(255, 235, 198));
        jtxt_Direccion.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel1.add(jtxt_Direccion);
        jtxt_Direccion.setBounds(30, -25, 170, 18);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("Dirección:");
        jPanel1.add(jLabel9);
        jLabel9.setBounds(30, -25, 70, 15);

        tb_empleado.setModel(new javax.swing.table.DefaultTableModel(
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
        tb_empleado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_empleadoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb_empleado);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(230, 110, 452, 160);

        jtxt_Nombre.setBackground(new java.awt.Color(255, 235, 198));
        jtxt_Nombre.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel1.add(jtxt_Nombre);
        jtxt_Nombre.setBounds(30, -25, 170, 18);

        btn_Buscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONOS/buscarx32.png"))); // NOI18N
        btn_Buscar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn_Buscar.setBorderPainted(false);
        btn_Buscar.setContentAreaFilled(false);
        btn_Buscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_Buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_BuscarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_Buscar);
        btn_Buscar.setBounds(620, 400, 32, 32);

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
        btn_Editar.setBounds(470, 400, 32, 32);

        btn_Agregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONOS/empleAgrex32.png"))); // NOI18N
        btn_Agregar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn_Agregar.setBorderPainted(false);
        btn_Agregar.setContentAreaFilled(false);
        btn_Agregar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_Agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_AgregarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_Agregar);
        btn_Agregar.setBounds(530, 400, 32, 32);

        btn_Eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONOS/empleElimx32.png"))); // NOI18N
        btn_Eliminar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn_Eliminar.setBorderPainted(false);
        btn_Eliminar.setContentAreaFilled(false);
        btn_Eliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_Eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_EliminarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_Eliminar);
        btn_Eliminar.setBounds(590, 400, 32, 32);

        btn_Regrsar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONOS/atrasUNOx32.png"))); // NOI18N
        btn_Regrsar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn_Regrsar.setBorderPainted(false);
        btn_Regrsar.setContentAreaFilled(false);
        btn_Regrsar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_Regrsar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONOS/atrasDOSx32.png"))); // NOI18N
        btn_Regrsar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_RegrsarMouseClicked(evt);
            }
        });
        jPanel1.add(btn_Regrsar);
        btn_Regrsar.setBounds(660, 400, 32, 32);

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
        btn_Limpiar.setBounds(240, 400, 32, 32);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 709, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_RegrsarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_RegrsarMouseClicked
        // TODO add your handling code here:
        MAIN main = new MAIN();
        main.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_RegrsarMouseClicked

    private void btn_BuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_BuscarActionPerformed
        try {
            // TODO add your handling code here:
            lookEmpleado();
        } catch (SQLException ex) {
            Logger.getLogger(P2_EMPLEADOS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_BuscarActionPerformed

    private void btn_AgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AgregarActionPerformed
        if (jtxt_Nombre.getText().equals("") || jtxt_Apellido.getText().equals("") || jtxt_DNI.getText().equals("") || jtbox_genero.getSelectedItem().equals("")
                || jtbox_cargo.getSelectedItem().equals("") || jtxt_Direccion.getText().equals("")) {
            javax.swing.JOptionPane.showMessageDialog(this, "Debe llenar todos los campos \n", "AVISO", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            jtxt_Nombre.requestFocus();
        } else {
            String nombre = jtxt_Nombre.getText();
            String apellido = jtxt_Apellido.getText();
            String Dni = jtxt_DNI.getText();
            String direccion = jtxt_Direccion.getText();
            String genero = (String) jtbox_genero.getSelectedItem();
            String cargo = (String) jtbox_cargo.getSelectedItem();
            String codigo = PrimerasPalabras(nombre) + "0" + UltimosDigitos(Dni);

            int dni = 0;

            if (nombre == null || "".equals(nombre) || apellido == null || "".equals(apellido) || Dni == null || "".equals(Dni) || direccion == null || "".equals(direccion)
                    || genero == null || "".equals(genero) || cargo == null || "".equals(cargo)) {
                javax.swing.JOptionPane.showMessageDialog(this, "Debe llenar todos los campos \n", "AVISO", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                jtxt_Nombre.requestFocus();
            } else {

                try {
                    dni = Integer.parseInt(Dni);
                    try {
                        for (int i = 0; i < tb_empleado.getRowCount(); i++) {
                            String valor = tb_empleado.getValueAt(i, clmbus).toString();
                            if (codigo.equals(valor)) {
                                UpdateEmpleado(codigo, nombre, apellido, genero, direccion, cargo, dni);
                                //`id_empleado`, `nombre`, `apellido`, `sexo`, `direccion`, `id_cargo`, `num_DNI`
                                edition = true; // Marcar que se encontró una coincidencia
                                break; // Salir del bucle
                            }
                        }
                        if (!edition) {
                            inserEmpleado(codigo, nombre, apellido, dni, genero, cargo, direccion);
                        }

                        Limpiar();

                    } catch (SQLException ex) {
                        Logger.getLogger(P2_EMPLEADOS.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (NumberFormatException ex) {
                    javax.swing.JOptionPane.showMessageDialog(this, "El DNI , deben ser un número entero. \n", "AVISO", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                    jtxt_Nombre.requestFocus();
                }
            }
        }
    }//GEN-LAST:event_btn_AgregarActionPerformed

    private void btn_EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_EliminarActionPerformed

        try {
            DeleteEmpleado();
        } catch (SQLException ex) {
            Logger.getLogger(P2_EMPLEADOS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_EliminarActionPerformed

    private void btn_LimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LimpiarActionPerformed
        // TODO add your handling code here:
        Limpiar();
    }//GEN-LAST:event_btn_LimpiarActionPerformed

    private void btn_EditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_EditarActionPerformed
        try {
            int idcell = tb_empleado.getSelectedRow();
            if (idcell <= -1) {
                javax.swing.JOptionPane.showMessageDialog(this, "Debe seleccionar el empleado a editar. \n", "AVISO", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            } else {
                stmt = P2_EMPLEADOS.conectar().createStatement();
                ResultSet counter = stmt.executeQuery("SELECT TB_EMPLEADO.*, TB_CARGO.cargo FROM TB_EMPLEADO JOIN TB_CARGO ON TB_EMPLEADO.ID_CARGO = TB_CARGO.ID_CARGO;");

                int count = 0;
                while (counter.next()) {
                    count++;
                }

                String list[][] = new String[count][7];
                int i = 0;
                rs = stmt.executeQuery("SELECT TB_EMPLEADO.*, TB_CARGO.cargo FROM TB_EMPLEADO JOIN TB_CARGO ON TB_EMPLEADO.ID_CARGO = TB_CARGO.ID_CARGO;");
                while (rs.next()) {
                    list[i][0] = rs.getString("ID_EMPLEADO");
                    list[i][1] = rs.getString("NOMBRE");
                    list[i][2] = rs.getString("APELLIDO");
                    list[i][3] = rs.getString("SEXO");
                    list[i][4] = rs.getString("DNI");
                    list[i][5] = rs.getString("DIRECCION");
                    list[i][6] = rs.getString("cargo");
                    i++;
                }
                //int id = Integer.parseInt(list[idcell][0]);
                String ID = (String) list[idcell][0];
                if (ID.equals("")) {
                    javax.swing.JOptionPane.showMessageDialog(this, "Debe seleccionar el Empleado a editar. \n", "AVISO", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                } else {
                    
                   
                    String COD_EMP = "" +ID;
                    String NOM_EMP =  list[idcell][1];
                    String APEL_EMP = list[idcell][2];
                    String Gen_EMP =  list[idcell][3];
                    String DNI_EMP = list[idcell][4];
                    String DIR_EMP = list[idcell][5];
                    String cargo = list[idcell][6];
                   
                    jtxt_Codigo.setText(COD_EMP);
                    jtxt_Nombre.setText(NOM_EMP);
                    jtxt_Apellido.setText(APEL_EMP);
                    jtbox_genero.setSelectedItem(Gen_EMP);
                    jtbox_cargo.setSelectedItem(cargo);
                    jtxt_DNI.setText(DNI_EMP);
                    jtxt_Direccion.setText(DIR_EMP);

                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(P2_EMPLEADOS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_EditarActionPerformed

    private void tb_empleadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_empleadoMouseClicked
        // TODO add your handling code here:
        filaSelec = tb_empleado.getSelectedRow();
        select[0] = tb_empleado.getValueAt(filaSelec, 0);
        select[1] = tb_empleado.getValueAt(filaSelec, 1);
        select[2] = tb_empleado.getValueAt(filaSelec, 2);
        select[3] = tb_empleado.getValueAt(filaSelec, 3);
        select[4] = tb_empleado.getValueAt(filaSelec, 4);
        select[5] = tb_empleado.getValueAt(filaSelec, 5);
        select[6] = tb_empleado.getValueAt(filaSelec, 6);

        jtxt_Codigo.setText(select[0].toString());
        jtxt_Nombre.setText(select[1].toString());
        jtxt_Apellido.setText(select[2].toString());
        jtxt_DNI.setText(select[3].toString());
        jtbox_genero.setSelectedItem(select[4]);
        jtbox_cargo.setSelectedItem(select[5]);
        jtxt_Direccion.setText(select[6].toString());
    }//GEN-LAST:event_tb_empleadoMouseClicked

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
            java.util.logging.Logger.getLogger(P2_EMPLEADOS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(P2_EMPLEADOS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(P2_EMPLEADOS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(P2_EMPLEADOS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new P2_EMPLEADOS().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Agregar;
    private javax.swing.JButton btn_Buscar;
    private javax.swing.JButton btn_Editar;
    private javax.swing.JButton btn_Eliminar;
    private javax.swing.JButton btn_Limpiar;
    private javax.swing.JButton btn_Regrsar;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JComboBox<String> jtbox_cargo;
    private javax.swing.JComboBox<String> jtbox_genero;
    private javax.swing.JTextField jtxt_Apellido;
    private javax.swing.JTextField jtxt_Codigo;
    private javax.swing.JTextField jtxt_DNI;
    private javax.swing.JTextField jtxt_Direccion;
    private javax.swing.JTextField jtxt_Nombre;
    private javax.swing.JTextField jtxt_busqueda;
    private javax.swing.JTable tb_empleado;
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
