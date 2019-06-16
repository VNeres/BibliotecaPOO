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
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
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
    private int id;
    private LivroDAO dao = new LivroDAO();

    /*
     *  Operações:
     *  N = nenhuma(padrão)
     *  A = adicionar
     *  M = muda(alterar)
     *  E = excluir
     *  B = buscar
     *  L = Listar
     * Will.
     */
    private char operacao = 'N';

    public ctrLivro() throws SQLException {
    }

    public ctrLivro(JFLivro frmLivros) {
        this.frmLivros = frmLivros;
        inicializaTableModel();
        adicionarListener();
        /*
         * Demonstra o status da conexão
         * Will.
         */
        imprimeStatus();
    }

    /*
     * Imprime o status da conexão na label, conforme a mesma seja:
     * true = conexão OK
     * false = falha na conexão
     * também foram colocadas as cores na mesagem de conexão:
     * verde = conectado;
     * vermelho = não conectado.
     * Will
     */
    private void imprimeStatus() {
        if (dao.isConStatus()) {
            frmLivros.getLblMensagemConexao().setForeground(new Color(0, 210, 0));
            frmLivros.getLblMensagemConexao().setText("Conectado");
        } else if (!dao.isConStatus()) {
            frmLivros.getLblMensagemConexao().setForeground(new Color(210, 0, 0));
            frmLivros.getLblMensagemConexao().setText("Não Conectado");

        }
    }

    private void adicionarListener() {
        frmLivros.getBtAdicionar().addActionListener(this);
        frmLivros.getBtAlterar().addActionListener(this);
        frmLivros.getBtExcluir().addActionListener(this);
        frmLivros.getBtnBuscar().addActionListener(this);
        frmLivros.getBtnListar().addActionListener(this);
        frmLivros.getBtnLimpar().addActionListener(this);
        frmLivros.getTbLivro().getSelectionModel().addListSelectionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent acao) {

        if (acao.getActionCommand().equals("Adicionar")) {
            incluirLivro();
        } else if (acao.getActionCommand().equals("Alterar")) {
            try {
                alterarLivro();
            } catch (SQLException ex) {
                Logger.getLogger(ctrLivro.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (acao.getActionCommand().equals("Listar Livros")) {
            try {
                listarTodos();
            } catch (SQLException ex) {
                Logger.getLogger(ctrLivro.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (acao.getActionCommand().equals("Excluir")) {
            try {
                excluirlivro();
            } catch (SQLException ex) {
            }
        } else if (acao.getActionCommand().equals("Buscar")) {
            try {
                pesquisarLivro();
            } catch (SQLException ex) {
            }
        } else if (acao.getActionCommand().equals("Limpar")) {
            limparTabela();
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

    private void incluirLivro() {
        operacao = 'A';

        try {
            if (operacao == 'A') {
                int idCliente;
                idCliente = dao.Inserir(dadosFrmLivro());
                frmLivros.getLblCodigo().setText(Integer.toString(idCliente));
                operacao = 'a';
                tabModel.addLivro(dadosFrmLivro());

            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        limparCampos(frmLivros.getPaLivro());

    }

    private void alterarLivro() throws SQLException {
        operacao = 'M';
        if (Integer.toString(id).equals(frmLivros.getLblCodigo().getText())) {
            dao.Alterar(dadosFrmLivro());
            tabModel.setValueAt(frmLivros.getTxtNomeLivro().getText(), frmLivros.getTbLivro().getSelectedRow(), 2);
            tabModel.setValueAt(frmLivros.getTxtAutor().getText(), frmLivros.getTbLivro().getSelectedRow(), 3);
            tabModel.setValueAt(frmLivros.getTxtAnoLivro().getText(), frmLivros.getTbLivro().getSelectedRow(), 4);
            tabModel.setValueAt(frmLivros.getTxtEditora().getText(), frmLivros.getTbLivro().getSelectedRow(), 5);
            tabModel.setValueAt(frmLivros.getTxtQuantidade().getText(), frmLivros.getTbLivro().getSelectedRow(), 6);
        }
        limparCampos(frmLivros.getPaLivro());
        listarTodos();
    }

    private void listarTodos() throws SQLException {
        frmLivros.getTbLivro().getSelectionModel().removeListSelectionListener(this);
        operacao = 'L';
        tabModel.limpar();
        tabModel.setLivros(dao.ListaLivros());
        frmLivros.getTbLivro().getSelectionModel().addListSelectionListener(this);
    }

    private void excluirlivro() throws SQLException {

        operacao = 'E';

        if (Integer.toString(id).equals(frmLivros.getLblCodigo().getText())) {
            int op = JOptionPane.showConfirmDialog(null, "Confirma a exclusão");

            if (op == 0) {
                dao.Excluir(dadosFrmLivro());
                JOptionPane.showMessageDialog(null, "Exclusão realizada com sucesso!!!", "Exclusão", JOptionPane.INFORMATION_MESSAGE);
                limparCampos(frmLivros.getPaLivro());
                tabModel.setLivros(dao.ListaLivros());
                tabModel.limpar();
                listarTodos();
            }
        }
    }

    private void pesquisarLivro() throws SQLException {
        Livro livro;
        livro = dao.localizarLivro(Integer.parseInt(frmLivros.getTxtPesquisar().getText()));
        dadosLivroFrm(livro);
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
        id = livro.getId();
        frmLivros.getLblCodigo().setText("" + livro.getId());
        frmLivros.getTxtNomeLivro().setText(livro.getNome());
        frmLivros.getTxtAutor().setText(livro.getAutor());
        frmLivros.getTxtAnoLivro().setText(livro.getAno());
        frmLivros.getTxtEditora().setText(livro.getEditora());
        frmLivros.getTxtQuantidade().setText(livro.getQuantidade());
    }

    private Livro dadosFrmLivro() {
        Livro livro = new Livro();
        if (operacao == 'a') {
            System.out.println("oi");
            livro.setId(Integer.parseInt(frmLivros.getLblCodigo().getText()));

        } else if (operacao == 'E') {
            livro.setId(Integer.parseInt(frmLivros.getLblCodigo().getText()));
            frmLivros.getTbLivro().getSelectionModel().removeListSelectionListener(this);

        } else if (operacao == 'M') {
            livro.setId(Integer.parseInt(frmLivros.getLblCodigo().getText()));
        }

        livro.setNome(frmLivros.getTxtNomeLivro().getText());
        livro.setAutor(frmLivros.getTxtAutor().getText());
        livro.setAno(frmLivros.getTxtAnoLivro().getText());
        livro.setEditora(frmLivros.getTxtEditora().getText());
        livro.setQuantidade(frmLivros.getTxtQuantidade().getText());
        return livro;
    }

    private void limparTabela() {
        frmLivros.getTbLivro().getSelectionModel().removeListSelectionListener(this);
        tabModel.limpar();
        limparCampos(frmLivros.getPaLivro());
        frmLivros.getTbLivro().getSelectionModel().addListSelectionListener(this);
    }

}
