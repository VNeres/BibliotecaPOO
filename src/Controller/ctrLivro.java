/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Livro;
import Model.LivroDAO;
import View.JFLivro;
import View.LivroTM;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
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
public class ctrLivro implements ActionListener, ListSelectionListener {

    private JFLivro frmLivros;
    private LivroTM tabModel;

    private LivroDAO dao = new LivroDAO();

    private char flagInsAltCons = 'C';

    public ctrLivro() throws SQLException {
        listarTodos();
    }

    public ctrLivro(JFLivro frmLivros) {
        this.frmLivros = frmLivros;
        inicializaTableModel();
        adicionarListener();
    }

    @Override
    public void actionPerformed(ActionEvent acao) {

        if (acao.getActionCommand().equals("Incluir")) {
            incluirLivro();
        } else if (acao.getActionCommand().equals("Alterar")) {
            alterarLivro();
        } else if (acao.getActionCommand().equals("Excluir")) {
            try {
                excluirlivro();
            } catch (SQLException ex) {
                Logger.getLogger(ctrLivro.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (acao.getActionCommand().equals("Salvar")) {
            try {
                salvarLivro();
            } catch (SQLException ex) {
                Logger.getLogger(ctrLivro.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (acao.getActionCommand().equals("Listar Todos")) {
            try {
                listarTodos();
            } catch (SQLException ex) {
                Logger.getLogger(ctrLivro.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (acao.getActionCommand().equals("Pesquisar")) {
            try {
                pesquisarLivro();
            } catch (SQLException ex) {
                Logger.getLogger(ctrLivro.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        Livro livro = tabModel.getLivros().get(frmLivros.getTbLivro().getSelectedRow());

        dadosLivroFrm(livro);
    }

    private void inicializaTableModel() {
        tabModel = new LivroTM();
        frmLivros.getTbLivro().setModel(tabModel);
    }

    private void adicionarListener() {
        frmLivros.getBtAdicionar().addActionListener(this);
        frmLivros.getBtAlterar().addActionListener(this);
        frmLivros.getBtBuscar().addActionListener(this);
        frmLivros.getBtExcluir().addActionListener(this);
        frmLivros.getTbLivro().getSelectionModel().addListSelectionListener(this);
    }

    private void incluirLivro() {
        habilitaBotoesSalvar();

        limparCampos(frmLivros.getPaLivro());
        flagInsAltCons = 'I';

    }

    private void alterarLivro() {
        if ("".equals(frmLivros.getLblCodigo().getText())) {
            habilitaBotoesSalvar();
            flagInsAltCons = 'A';
        }
    }

    private void excluirlivro() throws SQLException {

        if ("".equals(frmLivros.getLblCodigo().getText())) {
            int op = JOptionPane.showConfirmDialog(null, "Confirma a exclusão");

            if (op == 0) {
                dao.Excluir(dadosFrmLivro());
                JOptionPane.showMessageDialog(null, "Exclusão realizada com sucesso!!!", "Exclusão", JOptionPane.INFORMATION_MESSAGE);
                listarTodos();
                limparCampos(frmLivros.getPaLivro());
            }
        }
    }

    private void salvarLivro() throws SQLException{
        try{
            if(flagInsAltCons == 'I'){
                int codLivro;
                codLivro = dao.Inserir(dadosFrmLivro());
                frmLivros.getLblCodigo().setText(Integer.toString(codLivro));
                tabModel.addLivro(dadosFrmLivro());
            }else{
                dao.Alterar(dadosFrmLivro());
                tabModel.setValueAt(frmLivros.getTxtNomeLivro().getText(), frmLivros.getTbLivro().getSelectedRow(), 1);
                tabModel.setValueAt(frmLivros.getTxtAnoLivro().getText(), frmLivros.getTbLivro().getSelectedRow(), 2);
                tabModel.setValueAt(frmLivros.getTxtAutor().getText(), frmLivros.getTbLivro().getSelectedRow(), 3);
            }
        }catch(SQLException e){
            System.err.println(e);
        }
    }

    private void listarTodos() throws SQLException {
        tabModel.limpar();
        tabModel.setLivros(dao.ListaLivros());
        frmLivros.getBtAlterar().setEnabled(true);
        frmLivros.getBtExcluir().setEnabled(true);
    }

    private void pesquisarLivro() throws SQLException {
        
        Livro livro;
        
        livro = dao.localizarLivro(frmLivros.getTxtPesquisar().getText());
        dadosLivroFrm(livro);
    }

    private void habilitaBotoesSalvar() {
        habilitaDesailitaBotoes(true);
        habilitaDesabilitaPainel(frmLivros.getPaLivro(), true);
    }

    private void habilitaDesailitaBotoes(boolean habilitado) {
        frmLivros.getBtAdicionar().setEnabled(!habilitado);
        frmLivros.getBtAlterar().setEnabled(!habilitado);
        frmLivros.getBtExcluir().setEnabled(!habilitado);

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

    private void dadosLivroFrm(Livro livro) {
        frmLivros.getLblCodigo().setText("" + livro.getId());
        frmLivros.getTxtNomeLivro().setText(livro.getNome());
        frmLivros.getTxtAnoLivro().setText(Integer.toString(livro.getAno()));
    }

    private Livro dadosFrmLivro() {
        Livro livro = new Livro();
        if (flagInsAltCons == 'I'){
            livro.setId(Integer.parseInt(frmLivros.getLblCodigo().getText()));
        }
        
        livro.setNome(frmLivros.getTxtNomeLivro().getText());
        livro.setAno(Integer.parseInt(frmLivros.getTxtAnoLivro().getText()));
        livro.setAutor(frmLivros.getTxtAutor().getText());
        livro.setIsDisponivel(true);
        
        return livro;        
    }

}
