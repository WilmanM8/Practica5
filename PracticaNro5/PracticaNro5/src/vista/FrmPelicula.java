/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista;

import controlador.dao.GeneroDao;
import controlador.dao.PeliculaDao;
import controlador.ed.lista.ListaEnlazada;
import javax.swing.JOptionPane;
import vista.modeloTabla.ModeloTablaPelicula;
import vista.utilidades.Utilidades;

/**
 *
 * @author Usuario Asus
 */
public class FrmPelicula extends javax.swing.JFrame {

    private ModeloTablaPelicula modelo = new ModeloTablaPelicula();
    private GeneroDao generoD = new GeneroDao();
    private PeliculaDao peliculaD = new PeliculaDao();
    private Integer fila = -1;

    /**
     * Creates new form Pelicula
     */
    private FrmPelicula(java.awt.Frame parent, boolean modal) {
        //super(parent, modal);

    }

    private FrmPelicula() {
        initComponents();
        cargarCombo();
        cargarTabla();
    }

    public void cargarCombo() {
        try {
            Utilidades.cargarGenero(cbxGenero, generoD);
        } catch (Exception e) {
        }
    }

    public void cargarTabla() {
        modelo.setDato(peliculaD.listar());
        tblTabla.setModel(modelo);
        tblTabla.updateUI();
    }

    public void limpiar() {
        txtTitulo.setText("");
        txtRating.setText("");
        cargarTabla();
        cargarCombo();
        peliculaD.setPelicula(null);
        fila = -1;
        cbxBusqueda.setSelectedIndex(0);
        txtBuscar.setText("");
    }

    public void guardar() {
        if (txtTitulo.getText().trim().isEmpty() || txtRating.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Campos incompletos", "ERROR", JOptionPane.ERROR_MESSAGE);

        } else {
            try {
                peliculaD.getPelicula().setNombre(txtTitulo.getText());
                peliculaD.getPelicula().setRating(txtRating.getText());
                peliculaD.getPelicula().setId_genero(generoD.buscarPorTitulo(cbxGenero.getSelectedItem().toString()).getId());
                if (peliculaD.getPelicula().getId() != null) {
                    peliculaD.modificar(fila);
                    limpiar();
                } else {
                    peliculaD.guardar();
                    limpiar();
                }
                JOptionPane.showMessageDialog(null, "Se Guardo", "OK", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void seleccionar() {
        fila = tblTabla.getSelectedRow();
        if (fila >= 0) {
            try {
                Integer id = modelo.getDato().get(fila).getId();
                peliculaD.setPelicula(peliculaD.obtener(id));
                txtTitulo.setText(peliculaD.getPelicula().getNombre());
                txtRating.setText(peliculaD.getPelicula().getRating());
                cbxGenero.setSelectedItem(generoD.obtener(peliculaD.getPelicula().getId_genero()).getClaseGenero().toUpperCase());
            } catch (Exception e) {
            }
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione algo", "ERROR", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void busquedaLinealBinaria() {
        String por = cbxBusqueda.getSelectedItem().toString();
        String texto = txtBuscar.getText();
        try {
            if (por.equalsIgnoreCase("Titulo")) {
                modelo.setDato(peliculaD.busquedaLinealTitulo(texto));
            }
            if (por.equalsIgnoreCase("Rating")) {
                modelo.setDato(peliculaD.busquedaLinealRating(texto));
            }
            if (por.equalsIgnoreCase("Genero")) {
                modelo.setDato(peliculaD.busquedaLinealGenero(texto));
            }
            tblTabla.setModel(modelo);
            tblTabla.updateUI();

        } catch (Exception e) {
        }
    }

    public void busquedaBinaria() {
        ListaEnlazada<modelo.Pelicula> lista = new ListaEnlazada<>();
        String criterio = cbxBusqueda.getSelectedItem().toString();
        String texto = txtBuscar.getText();

        try {
            if (criterio.equalsIgnoreCase("Titulo")) {
                modelo.Pelicula p = peliculaD.buscarBinariaTitulo(texto);
                if (p != null) {
                    lista.insertar(p);
                    modelo.setDato(lista);
                } else {
                    modelo.setDato(new ListaEnlazada<>()); 
                }
            }
            if (criterio.equalsIgnoreCase("Rating")) {
                modelo.Pelicula p = peliculaD.buscarBinariaRating(texto);
                if (p != null) {
                    lista.insertar(p);
                    modelo.setDato(lista);
                } else {
                    modelo.setDato(new ListaEnlazada<>());
                }
            }
            if (criterio.equalsIgnoreCase("Genero")) {
                modelo.Pelicula p = peliculaD.buscarBinariaGenero(texto);
                if (p != null) {
                    lista.insertar(p);
                    modelo.setDato(lista);
                } else {
                    modelo.setDato(new ListaEnlazada<>()); 
                }
            }
            tblTabla.setModel(modelo);
            tblTabla.updateUI();

        } catch (Exception e) {
            System.out.println(e);
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

        jComboBox1 = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtTitulo = new javax.swing.JTextField();
        txtRating = new javax.swing.JTextField();
        cbxGenero = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cbxBusqueda = new javax.swing.JComboBox<>();
        txtBuscar = new javax.swing.JTextField();
        btnBusquedaLineal = new javax.swing.JButton();
        btnBusquedaBinaria = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTabla = new javax.swing.JTable();

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Titulo:");

        jLabel2.setText("Rating:");

        jLabel3.setText("Genero:");

        cbxGenero.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel4.setText("Buscar por:");

        jLabel5.setText("Buscar:");

        cbxBusqueda.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Titulo", "Rating", "Genero" }));

        txtBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarActionPerformed(evt);
            }
        });

        btnBusquedaLineal.setText("lineal");
        btnBusquedaLineal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBusquedaLinealActionPerformed(evt);
            }
        });

        btnBusquedaBinaria.setText("binaria");
        btnBusquedaBinaria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBusquedaBinariaActionPerformed(evt);
            }
        });

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        jLabel6.setText("Seleccione el metodo de busqueda");

        tblTabla.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblTabla);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cbxBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnGuardar, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cbxGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtRating))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtTitulo)))
                        .addGap(51, 51, 51)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel6)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnBusquedaLineal)
                        .addGap(18, 18, 18)
                        .addComponent(btnBusquedaBinaria)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(txtRating, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(cbxGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(16, 16, 16)
                        .addComponent(btnGuardar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(cbxBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBusquedaLineal)
                    .addComponent(btnBusquedaBinaria))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        guardar();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnBusquedaLinealActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBusquedaLinealActionPerformed
        // TODO add your handling code here:
        busquedaLinealBinaria();
    }//GEN-LAST:event_btnBusquedaLinealActionPerformed

    private void btnBusquedaBinariaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBusquedaBinariaActionPerformed
        // TODO add your handling code here:
        busquedaBinaria();
    }//GEN-LAST:event_btnBusquedaBinariaActionPerformed

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(FrmPelicula.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmPelicula.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmPelicula.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmPelicula.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmPelicula().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBusquedaBinaria;
    private javax.swing.JButton btnBusquedaLineal;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<String> cbxBusqueda;
    private javax.swing.JComboBox<String> cbxGenero;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblTabla;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtRating;
    private javax.swing.JTextField txtTitulo;
    // End of variables declaration//GEN-END:variables
}
