
package gui;

import exceptions.NoEstaEntidadException;

import java.awt.Dimension;
import java.awt.Toolkit;

import java.util.HashMap;
import java.util.Iterator;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import pga.Asignatura;
import pga.Controlador;
import pga.Profesor;

public class ProfesorConsulta extends javax.swing.JFrame
{
    private Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    private Controlador controlador;
    private DefaultListModel listModel1, listModel2;
    private HashMap<String, Profesor> hash = null;

    public ProfesorConsulta(Controlador controlador)
    {
        this.controlador = controlador;
        initComponents();
        this.setTitle("Consulta Profesor");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocation(d.width / 2 - this.getWidth() / 2, d.height / 2 - this.getHeight() / 2);
        this.setVisible(true);
    }

    public boolean camposVacios()
    {
        return !(this.jTextFieldNombre.getText().length() != 0 && this.jTextFieldApellido.getText().length() != 0);
    }
    
    public void listar1(HashMap<String, Profesor> hash)
    {
        Iterator <Profesor> iP = hash.values().iterator();
        
        this.listModel1.clear();
        while(iP.hasNext())
            this.listModel1.addElement(iP.next());
    }
    
    public void listar2(HashMap<String, Asignatura> hash)
    {
        Iterator <Asignatura> iA = hash.values().iterator();
        
        this.listModel2.clear();
        while(iA.hasNext())
            this.listModel2.addElement(iA.next());
    }
    
    public void vaciaCamposConsulta()
    {
        this.jLabelNombreC2.setText("...............");
        this.jLabelApellidoC2.setText("...............");
        this.jLabelDomicilioC2.setText("...............");
        this.jLabelTelefonoC2.setText("...............");
        this.jLabelMailC2.setText("...............");
        this.listModel2.clear();
    }
    
    public void consultaDatos(Profesor profesor)
    {
        this.jLabelNombreC2.setText(profesor.getNombre());
        this.jLabelApellidoC2.setText(profesor.getApellido());
        this.jLabelDomicilioC2.setText(profesor.getDomicilio());
        this.jLabelTelefonoC2.setText(profesor.getTelefono());
        this.jLabelMailC2.setText(profesor.getMail());
        this.jLabelLegajoC2.setText(profesor.getLegajo());
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    private void initComponents()//GEN-BEGIN:initComponents
    {

        jPanel1 = new javax.swing.JPanel();
        jLabelNombre = new javax.swing.JLabel();
        jLabelApellido = new javax.swing.JLabel();
        jTextFieldNombre = new javax.swing.JTextField();
        jTextFieldApellido = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        listModel1 = new DefaultListModel();
        jList1 = new javax.swing.JList<>(listModel1);
        jButtonElegir = new javax.swing.JButton();
        jButtonBuscar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jButtonCancelar = new javax.swing.JButton();
        jLabelNombreC1 = new javax.swing.JLabel();
        jLabelApellidoC1 = new javax.swing.JLabel();
        jLabelDomicilioC1 = new javax.swing.JLabel();
        jLabelTelefonoC1 = new javax.swing.JLabel();
        jLabelMailC1 = new javax.swing.JLabel();
        jLabelLegajoC1 = new javax.swing.JLabel();
        jLabelNombreC2 = new javax.swing.JLabel();
        jButtonAceptar = new javax.swing.JButton();
        jLabelApellidoC2 = new javax.swing.JLabel();
        jLabelDomicilioC2 = new javax.swing.JLabel();
        jLabelMailC2 = new javax.swing.JLabel();
        jLabelLegajoC2 = new javax.swing.JLabel();
        jLabelTelefonoC2 = new javax.swing.JLabel();
        jLabelCompetencias = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listModel2 = new DefaultListModel();
        jListCompetencias = new javax.swing.JList<>(listModel2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabelNombre.setText("Nombre");

        jLabelApellido.setText("Apellido");

        jScrollPane1.setViewportView(jList1);

        jButtonElegir.setText("Elegir");
        jButtonElegir.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButtonElegirActionPerformed(evt);
            }
        });

        jButtonBuscar.setText("Buscar");
        jButtonBuscar.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButtonBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelNombre)
                    .addComponent(jLabelApellido))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jTextFieldNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
                        .addComponent(jTextFieldApellido))
                    .addComponent(jButtonBuscar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonElegir)
                .addContainerGap(49, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonElegir)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabelNombre)
                                .addComponent(jTextFieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(21, 21, 21)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabelApellido)
                                .addComponent(jTextFieldApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addComponent(jButtonBuscar))))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButtonCancelarActionPerformed(evt);
            }
        });

        jLabelNombreC1.setText("Nombre");

        jLabelApellidoC1.setText("Apellido");

        jLabelDomicilioC1.setText("Domicilio");

        jLabelTelefonoC1.setText("Telefono");

        jLabelMailC1.setText("Mail");

        jLabelLegajoC1.setText("Legajo");

        jLabelNombreC2.setText("..............");

        jButtonAceptar.setText("Aceptar");
        jButtonAceptar.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButtonAceptarActionPerformed(evt);
            }
        });

        jLabelApellidoC2.setText("..............");

        jLabelDomicilioC2.setText("..............");

        jLabelMailC2.setText("..............");

        jLabelLegajoC2.setText("..............");

        jLabelTelefonoC2.setText("..............");

        jLabelCompetencias.setText("Competencias");

        jScrollPane2.setViewportView(jListCompetencias);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabelLegajoC1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabelLegajoC2, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelNombreC1)
                            .addComponent(jLabelApellidoC1)
                            .addComponent(jLabelMailC1)
                            .addComponent(jLabelTelefonoC1)
                            .addComponent(jLabelDomicilioC1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelNombreC2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelMailC2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelTelefonoC2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelDomicilioC2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelApellidoC2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jButtonCancelar)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonAceptar)
                        .addGap(29, 29, 29))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelCompetencias))
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNombreC1)
                    .addComponent(jLabelNombreC2)
                    .addComponent(jLabelCompetencias))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelApellidoC1)
                            .addComponent(jLabelApellidoC2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelDomicilioC1)
                            .addComponent(jLabelDomicilioC2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelTelefonoC1)
                            .addComponent(jLabelTelefonoC2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelMailC1)
                            .addComponent(jLabelMailC2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelLegajoC1)
                            .addComponent(jLabelLegajoC2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAceptar)
                    .addComponent(jButtonCancelar))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }//GEN-END:initComponents

    private void jButtonBuscarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonBuscarActionPerformed
    {//GEN-HEADEREND:event_jButtonBuscarActionPerformed
        this.vaciaCamposConsulta();
        if (this.camposVacios()) 
            JOptionPane.showMessageDialog(rootPane, "Faltan completar campos", "Error de B�squeda", JOptionPane.WARNING_MESSAGE);
        else
            try
            {
                this.hash = controlador.ubicarProfesor(this.jTextFieldNombre.getText(), this.jTextFieldApellido.getText());
                this.listar1(hash);
            } catch (NoEstaEntidadException e)
            {
                JOptionPane.showMessageDialog(rootPane, e.getMessage(), "Error de B�squeda", JOptionPane.WARNING_MESSAGE);
            }
    }//GEN-LAST:event_jButtonBuscarActionPerformed

    private void jButtonElegirActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonElegirActionPerformed
    {//GEN-HEADEREND:event_jButtonElegirActionPerformed
        int index;
        Profesor profesor = null;
        
        if (this.camposVacios())
            JOptionPane.showMessageDialog(rootPane, "Faltan completar campos", "Error de Modificaci�n", JOptionPane.WARNING_MESSAGE);
        else
            if (this.jList1.getSelectedValue() != null)
            {
                index = this.jList1.getSelectedIndex();
                profesor = (Profesor) this.listModel1.getElementAt(index);
                this.consultaDatos(profesor);
                this.listar2(profesor.getCompetencias());
            }
            else
                JOptionPane.showMessageDialog(rootPane, "Seleccione un elemento de la lista", "Error de Consulta", JOptionPane.WARNING_MESSAGE);
    }//GEN-LAST:event_jButtonElegirActionPerformed

    private void jButtonAceptarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonAceptarActionPerformed
    {//GEN-HEADEREND:event_jButtonAceptarActionPerformed
        if (this.hash != null)
        {
            JOptionPane.showMessageDialog(rootPane, "Consulta del Profesor Exitosa");
            this.dispose();   
        }
        else
            JOptionPane.showMessageDialog(rootPane, "Realice una consulta o presione \"Cancelar\" para salir", "Error de Consulta", JOptionPane.WARNING_MESSAGE);
    }//GEN-LAST:event_jButtonAceptarActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonCancelarActionPerformed
    {//GEN-HEADEREND:event_jButtonCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing
                                                                   .UIManager
                                                                   .getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing
                         .UIManager
                         .setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex)
        {
            java.util
                .logging
                .Logger
                .getLogger(ProfesorConsulta.class.getName())
                .log(java.util
                         .logging
                         .Level
                         .SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util
                .logging
                .Logger
                .getLogger(ProfesorConsulta.class.getName())
                .log(java.util
                         .logging
                         .Level
                         .SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util
                .logging
                .Logger
                .getLogger(ProfesorConsulta.class.getName())
                .log(java.util
                         .logging
                         .Level
                         .SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util
                .logging
                .Logger
                .getLogger(ProfesorConsulta.class.getName())
                .log(java.util
                         .logging
                         .Level
                         .SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt
            .EventQueue
            .invokeLater(new Runnable()
            {
                public void run()
                {
                    new ProfesorConsulta(null).setVisible(true);
                }
            });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAceptar;
    private javax.swing.JButton jButtonBuscar;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonElegir;
    private javax.swing.JLabel jLabelApellido;
    private javax.swing.JLabel jLabelApellidoC1;
    private javax.swing.JLabel jLabelApellidoC2;
    private javax.swing.JLabel jLabelCompetencias;
    private javax.swing.JLabel jLabelDomicilioC1;
    private javax.swing.JLabel jLabelDomicilioC2;
    private javax.swing.JLabel jLabelLegajoC1;
    private javax.swing.JLabel jLabelLegajoC2;
    private javax.swing.JLabel jLabelMailC1;
    private javax.swing.JLabel jLabelMailC2;
    private javax.swing.JLabel jLabelNombre;
    private javax.swing.JLabel jLabelNombreC1;
    private javax.swing.JLabel jLabelNombreC2;
    private javax.swing.JLabel jLabelTelefonoC1;
    private javax.swing.JLabel jLabelTelefonoC2;
    private javax.swing.JList<String> jList1;
    private javax.swing.JList<String> jListCompetencias;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextFieldApellido;
    private javax.swing.JTextField jTextFieldNombre;
    // End of variables declaration//GEN-END:variables

}
