/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author vande
 */
public class JFEmprestimo extends javax.swing.JFrame {

    private JFCliente jfcliente;
    private JFLivro jflivro;

    /**
     * Creates new form Biblioteca
     */
    public JFEmprestimo() throws SQLException {
        initComponents();
        this.jfcliente = new JFCliente();
        this.jflivro = new JFLivro();

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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btEmprestar = new javax.swing.JButton();
        btAlterar = new javax.swing.JButton();
        btDevolver = new javax.swing.JButton();
        paEmprestimo = new javax.swing.JPanel();
        lblNomeCliente = new javax.swing.JLabel();
        comboCliente = new javax.swing.JComboBox();
        lblNomeLivro = new javax.swing.JLabel();
        comboLivro = new javax.swing.JComboBox();
        lblDataDevolucao = new javax.swing.JLabel();
        txtDatavDevolucao = new javax.swing.JTextField();
        lblCodigo = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbEmprestimo = new javax.swing.JTable();
        lblPesquisar = new javax.swing.JLabel();
        txtPesquisar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        btJfCliente = new javax.swing.JButton();
        btJfLivro = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));

        jLabel1.setFont(new java.awt.Font("Segoe UI Semilight", 1, 48)); // NOI18N
        jLabel1.setText("EMPRÉSTIMOS");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/emprestimoBG.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btEmprestar.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btEmprestar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/book.png"))); // NOI18N
        btEmprestar.setText("Emprestar");

        btAlterar.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/edit24.png"))); // NOI18N
        btAlterar.setText("Alterar");

        btDevolver.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btDevolver.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/4476867-32.png"))); // NOI18N
        btDevolver.setText("Devolver");

        paEmprestimo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        lblNomeCliente.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblNomeCliente.setText("Nome do Cliente:");

        comboCliente.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        comboCliente.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lblNomeLivro.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblNomeLivro.setText("Nome do Livro:");

        comboLivro.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        comboLivro.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lblDataDevolucao.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblDataDevolucao.setText("Data para devolução:");

        txtDatavDevolucao.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        lblCodigo.setText("Código:");

        javax.swing.GroupLayout paEmprestimoLayout = new javax.swing.GroupLayout(paEmprestimo);
        paEmprestimo.setLayout(paEmprestimoLayout);
        paEmprestimoLayout.setHorizontalGroup(
            paEmprestimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paEmprestimoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(paEmprestimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(paEmprestimoLayout.createSequentialGroup()
                        .addComponent(comboCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(comboLivro, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(paEmprestimoLayout.createSequentialGroup()
                        .addComponent(lblCodigo)
                        .addGap(16, 16, 16)
                        .addComponent(lblNomeCliente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblNomeLivro)
                        .addGap(96, 96, 96))
                    .addGroup(paEmprestimoLayout.createSequentialGroup()
                        .addComponent(lblDataDevolucao)
                        .addGap(18, 18, 18)
                        .addComponent(txtDatavDevolucao, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        paEmprestimoLayout.setVerticalGroup(
            paEmprestimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, paEmprestimoLayout.createSequentialGroup()
                .addGroup(paEmprestimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(paEmprestimoLayout.createSequentialGroup()
                        .addContainerGap(25, Short.MAX_VALUE)
                        .addGroup(paEmprestimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNomeLivro))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(paEmprestimoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblCodigo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(paEmprestimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboLivro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(paEmprestimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDataDevolucao)
                    .addComponent(txtDatavDevolucao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        tbEmprestimo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tbEmprestimo);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
        );

        lblPesquisar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblPesquisar.setText("Pesquisar:");

        txtPesquisar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        btnBuscar.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/iconfinder_Search_27877.png"))); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btJfCliente.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btJfCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/book-with-add-button.png"))); // NOI18N
        btJfCliente.setText("Adicionar novo Cliente");
        btJfCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btJfClienteActionPerformed(evt);
            }
        });

        btJfLivro.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btJfLivro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/new-user.png"))); // NOI18N
        btJfLivro.setText("Adicionar novo Livro");
        btJfLivro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btJfLivroActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(paEmprestimo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btEmprestar, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(135, 135, 135)
                        .addComponent(btAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 135, Short.MAX_VALUE)
                        .addComponent(btDevolver, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblPesquisar)
                        .addGap(18, 18, 18)
                        .addComponent(txtPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBuscar)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(141, 141, 141)
                .addComponent(btJfCliente)
                .addGap(56, 56, 56)
                .addComponent(btJfLivro, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btEmprestar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btAlterar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btDevolver, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btJfCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btJfLivro, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addComponent(paEmprestimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPesquisar)
                    .addComponent(txtPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar))
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
       
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btJfClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btJfClienteActionPerformed
        jfcliente.setVisible(true);
    }//GEN-LAST:event_btJfClienteActionPerformed

    private void btJfLivroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btJfLivroActionPerformed
        jflivro.setVisible(true);
    }//GEN-LAST:event_btJfLivroActionPerformed

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFEmprestimo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFEmprestimo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFEmprestimo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFEmprestimo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new JFEmprestimo().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(JFEmprestimo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAlterar;
    private javax.swing.JButton btDevolver;
    private javax.swing.JButton btEmprestar;
    private javax.swing.JButton btJfCliente;
    private javax.swing.JButton btJfLivro;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JComboBox comboCliente;
    private javax.swing.JComboBox comboLivro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblDataDevolucao;
    private javax.swing.JLabel lblNomeCliente;
    private javax.swing.JLabel lblNomeLivro;
    private javax.swing.JLabel lblPesquisar;
    private javax.swing.JPanel paEmprestimo;
    private javax.swing.JTable tbEmprestimo;
    private javax.swing.JTextField txtDatavDevolucao;
    private javax.swing.JTextField txtPesquisar;
    // End of variables declaration//GEN-END:variables

    public JLabel getLblCodigo() {
        return lblCodigo;
    }

    public void setLblCodigo(JLabel lblCodigo) {
        this.lblCodigo = lblCodigo;
    }

    public JButton getBtAlterar() {
        return btAlterar;
    }

    public void setBtAlterar(JButton btAlterar) {
        this.btAlterar = btAlterar;
    }

    public JButton getBtDevolver() {
        return btDevolver;
    }

    public void setBtDevolver(JButton btDevolver) {
        this.btDevolver = btDevolver;
    }

    public JButton getBtEmprestar() {
        return btEmprestar;
    }

    public void setBtEmprestar(JButton btEmprestar) {
        this.btEmprestar = btEmprestar;
    }

    public JButton getBtJfCliente() {
        return btJfCliente;
    }

    public void setBtJfCliente(JButton btJfCliente) {
        this.btJfCliente = btJfCliente;
    }

    public JButton getBtJfLivro() {
        return btJfLivro;
    }

    public void setBtJfLivro(JButton btJfLivro) {
        this.btJfLivro = btJfLivro;
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public void setBtnBuscar(JButton btnBuscar) {
        this.btnBuscar = btnBuscar;
    }

    public JComboBox getComboCliente() {
        return comboCliente;
    }

    public void setComboCliente(JComboBox comboCliente) {
        this.comboCliente = comboCliente;
    }

    public JComboBox getComboLivro() {
        return comboLivro;
    }

    public void setComboLivro(JComboBox comboLivro) {
        this.comboLivro = comboLivro;
    }

    public JLabel getjLabel1() {
        return jLabel1;
    }

    public void setjLabel1(JLabel jLabel1) {
        this.jLabel1 = jLabel1;
    }

    public JLabel getjLabel2() {
        return jLabel2;
    }

    public void setjLabel2(JLabel jLabel2) {
        this.jLabel2 = jLabel2;
    }

    public JPanel getjPanel1() {
        return jPanel1;
    }

    public void setjPanel1(JPanel jPanel1) {
        this.jPanel1 = jPanel1;
    }

    public JPanel getjPanel3() {
        return jPanel3;
    }

    public void setjPanel3(JPanel jPanel3) {
        this.jPanel3 = jPanel3;
    }

    public JScrollPane getjScrollPane1() {
        return jScrollPane1;
    }

    public void setjScrollPane1(JScrollPane jScrollPane1) {
        this.jScrollPane1 = jScrollPane1;
    }

    public JLabel getLblDataDevolucao() {
        return lblDataDevolucao;
    }

    public void setLblDataDevolucao(JLabel lblDataDevolucao) {
        this.lblDataDevolucao = lblDataDevolucao;
    }

    public JLabel getLblNomeCliente() {
        return lblNomeCliente;
    }

    public void setLblNomeCliente(JLabel lblNomeCliente) {
        this.lblNomeCliente = lblNomeCliente;
    }

    public JLabel getLblNomeLivro() {
        return lblNomeLivro;
    }

    public void setLblNomeLivro(JLabel lblNomeLivro) {
        this.lblNomeLivro = lblNomeLivro;
    }

    public JLabel getLblPesquisar() {
        return lblPesquisar;
    }

    public void setLblPesquisar(JLabel lblPesquisar) {
        this.lblPesquisar = lblPesquisar;
    }

    public JPanel getPaEmprestimo() {
        return paEmprestimo;
    }

    public void setPaEmprestimo(JPanel paEmprestimo) {
        this.paEmprestimo = paEmprestimo;
    }

    public JTable getTbEmprestimo() {
        return tbEmprestimo;
    }

    public void setTbEmprestimo(JTable tbEmprestimo) {
        this.tbEmprestimo = tbEmprestimo;
    }

    public JTextField getTxtDatavDevolucao() {
        return txtDatavDevolucao;
    }

    public void setTxtDatavDevolucao(JTextField txtDatavDevolucao) {
        this.txtDatavDevolucao = txtDatavDevolucao;
    }

    public JTextField getTxtPesquisar() {
        return txtPesquisar;
    }

    public void setTxtPesquisar(JTextField txtPesquisar) {
        this.txtPesquisar = txtPesquisar;
    }

}
