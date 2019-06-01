/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Emprestimo;
import Model.EmprestimoDAO;
import View.EmprestimoTM;
import View.JFEmprestimo;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author vande
 */
public class ctrEmprestimo implements ActionListener, ListSelectionListener {

    private JFEmprestimo frmEmprestimos;
    private EmprestimoTM tabModel;

    private EmprestimoDAO dao = new EmprestimoDAO();

    private char flagInsAltCons = 'C';

    public ctrEmprestimo() throws SQLException {
        listarTodos();
    }

    public ctrEmprestimo(JFEmprestimo frmEmprestimos) {
        this.frmEmprestimos = frmEmprestimos;
        inicializaTableModel();
        adicionarListener();
    }

    @Override
    public void actionPerformed(ActionEvent acao) {

        if (acao.getActionCommand().equals("Incluir")) {
            incluirEmprestimo();
        } else if (acao.getActionCommand().equals("Alterar")) {
            alterarEmprestimo();
        } else if (acao.getActionCommand().equals("Excluir")) {
            try {
                excluirEmprestimo();
            } catch (SQLException ex) {
                Logger.getLogger(ctrLivro.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(ctrEmprestimo.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (acao.getActionCommand().equals("Salvar")) {
            try {
                salvarEmprestimo();
            } catch (SQLException ex) {
                Logger.getLogger(ctrLivro.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(ctrEmprestimo.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (acao.getActionCommand().equals("Listar Todos")) {
            try {
                listarTodos();
            } catch (SQLException ex) {
                Logger.getLogger(ctrLivro.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (acao.getActionCommand().equals("Pesquisar")) {
            try {
                pesquisarEmprestimo();
            } catch (SQLException ex) {
                Logger.getLogger(ctrLivro.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        Emprestimo emprestimo = tabModel.getEmprestimos().get(frmEmprestimos.getTbEmprestimo().getSelectedRow());

        dadosEmprestimoFrm(emprestimo);
    }

    private void inicializaTableModel() {
        tabModel = new EmprestimoTM();
        frmEmprestimos.getTbEmprestimo().setModel(tabModel);
    }

    private void adicionarListener() {
        frmEmprestimos.getBtEmprestar().addActionListener(this);
        frmEmprestimos.getBtAlterar().addActionListener(this);
        frmEmprestimos.getBtnBuscar().addActionListener(this);
        frmEmprestimos.getBtDevolver().addActionListener(this);
        frmEmprestimos.getTbEmprestimo().getSelectionModel().addListSelectionListener(this);
    }

    private void incluirEmprestimo() {
        habilitaBotoesSalvar();

        limparCampos(frmEmprestimos.getPaEmprestimo());
        flagInsAltCons = 'I';

    }

    private void alterarEmprestimo() {
        if ("".equals(frmEmprestimos.getLblCodigo().getText())) {
            habilitaBotoesSalvar();
            flagInsAltCons = 'A';
        }
    }

    private void excluirEmprestimo() throws SQLException, ParseException {

        if ("".equals(frmEmprestimos.getLblCodigo().getText())) {
            int op = JOptionPane.showConfirmDialog(null, "Confirma a exclusão");

            if (op == 0) {
                dao.Excluir(dadosFrmEmprestimo());
                JOptionPane.showMessageDialog(null, "Exclusão realizada com sucesso!!!", "Exclusão", JOptionPane.INFORMATION_MESSAGE);
                listarTodos();
                limparCampos(frmEmprestimos.getPaEmprestimo());
            }
        }
    }

    private void salvarEmprestimo() throws SQLException, ParseException{
        if(flagInsAltCons == 'I'){
            int codLivro;
            codLivro = dao.Inserir(dadosFrmEmprestimo());
            frmEmprestimos.getLblCodigo().setText(Integer.toString(codLivro));
            tabModel.addEmprestimo(dadosFrmEmprestimo());
        }else{
            dao.Alterar(dadosFrmEmprestimo());
            tabModel.setValueAt(frmEmprestimos.getComboCliente().getSelectedItem().toString(),frmEmprestimos.getTbEmprestimo().getSelectedRow(), 1);
            tabModel.setValueAt(frmEmprestimos.getComboLivro().getSelectedItem().toString(), frmEmprestimos.getTbEmprestimo().getSelectedRow(), 2);
            tabModel.setValueAt(frmEmprestimos.getTxtDatavDevolucao().getText(), frmEmprestimos.getTbEmprestimo().getSelectedRow(), 3);
        }
    }

    private void listarTodos() throws SQLException {
        tabModel.limpar();
        tabModel.setEmprestimos(dao.ListaEmprestimos());
        frmEmprestimos.getBtAlterar().setEnabled(true);
        frmEmprestimos.getBtDevolver().setEnabled(true);
    }

    private void pesquisarEmprestimo() throws SQLException {
        
        Emprestimo livro;
        
        livro = dao.localizarEmprestimo(frmEmprestimos.getTxtPesquisar().getText());
        dadosEmprestimoFrm(livro);
    }

    private void habilitaBotoesSalvar() {
        habilitaDesailitaBotoes(true);
        habilitaDesabilitaPainel(frmEmprestimos.getPaEmprestimo(), true);
    }

    private void habilitaDesailitaBotoes(boolean habilitado) {
        frmEmprestimos.getBtEmprestar().setEnabled(!habilitado);
        frmEmprestimos.getBtAlterar().setEnabled(!habilitado);
        frmEmprestimos.getBtDevolver().setEnabled(!habilitado);

    }

    private void habilitaDesabilitaPainel(JPanel panel, boolean habilitado) {
        Component[] componente = panel.getComponents();
        for (Component comp : componente) {
            if (comp instanceof JTextField) {
                JTextField textField = (JTextField) comp;
                textField.setEnabled(habilitado);
            }
        }
    }

    private void limparCampos(JPanel panel) {
        Component[] componente = panel.getComponents();
        for (Component comp : componente) {
            if (comp instanceof JTextField) {
                JTextField textField = (JTextField) comp;
                textField.setText("");
            }
        }
    }

    private void dadosEmprestimoFrm(Emprestimo emprestimo) {
        frmEmprestimos.getLblCodigo().setText("" + emprestimo.getId());
        //frmEmprestimos.getComboLivro().setText(emprestimo.getIdCliente());
        //frmEmprestimos.getTxtAnoLivro().setText(Integer.toString(livro.getAno()));
    }

    private Emprestimo dadosFrmEmprestimo() throws ParseException {
        Emprestimo emprestimo = new Emprestimo();
        if (flagInsAltCons == 'I'){
            emprestimo.setId(Integer.parseInt(frmEmprestimos.getLblCodigo().getText()));
        }
        
        emprestimo.setIdCliente((int) frmEmprestimos.getComboCliente().getSelectedItem());
        emprestimo.setIdLivro((int)(frmEmprestimos.getComboLivro().getSelectedItem()));
        emprestimo.setDataDevolucao(new SimpleDateFormat("dd/MM/yyyy").parse(frmEmprestimos.getTxtDatavDevolucao().getText()));
        
        return emprestimo;        
    }
    
}
