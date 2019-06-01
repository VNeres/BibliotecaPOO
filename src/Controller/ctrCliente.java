/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Cliente;
import Model.ClienteDAO;
import View.ClienteTM;
import View.JFCliente;
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
public class ctrCliente implements ActionListener, ListSelectionListener {

    private JFCliente frmClientes;
    private ClienteTM tabModel;

    private ClienteDAO dao = new ClienteDAO();

    private char flagInsAltCons = 'C';

    public ctrCliente() throws SQLException {
    }

    public ctrCliente(JFCliente frmClientes) {
        this.frmClientes = frmClientes;
        inicializaTableModel();
        adicionarListener();
    }

    @Override
    public void actionPerformed(ActionEvent acao) {

        if (acao.getActionCommand().equals("Incluir")) {
            incluirCliente();
        } else if (acao.getActionCommand().equals("Alterar")) {
            alterarCliente();
        } else if (acao.getActionCommand().equals("Excluir")) {
            try {
                excluirCliente();
            } catch (SQLException ex) {
                Logger.getLogger(ctrCliente.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(ctrCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (acao.getActionCommand().equals("Salvar")) {
            try {
                salvarCliente();
            } catch (SQLException ex) {
                Logger.getLogger(ctrCliente.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(ctrCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (acao.getActionCommand().equals("Listar Todos")) {
            try {
                listarTodos();
            } catch (SQLException ex) {
                Logger.getLogger(ctrCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (acao.getActionCommand().equals("Pesquisar")) {
            try {
                pesquisarCliente();
            } catch (SQLException ex) {
                Logger.getLogger(ctrCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        Cliente cliente = tabModel.getCliente().get(frmClientes.getTbCliente().getSelectedRow());

        dadosClienteFrm(cliente);
    }

    private void inicializaTableModel() {
        tabModel = new ClienteTM();
        frmClientes.getTbCliente().setModel(tabModel);
    }

    private void adicionarListener() {
        frmClientes.getBtAdicionar().addActionListener(this);
        frmClientes.getBtAlterar().addActionListener(this);
        frmClientes.getBtnBuscar().addActionListener(this);
        frmClientes.getBtExcluir().addActionListener(this);
        frmClientes.getTbCliente().getSelectionModel().addListSelectionListener(this);
    }

    private void incluirCliente() {
        habilitaBotoesSalvar();

        limparCampos(frmClientes.getPaCliente());
        flagInsAltCons = 'I';

    }

    private void alterarCliente() {
        if ("".equals(frmClientes.getLblCodigo().getText())) {
            habilitaBotoesSalvar();
            flagInsAltCons = 'A';
        }
    }

    private void excluirCliente() throws SQLException, ParseException {

        if ("".equals(frmClientes.getLblCodigo().getText())) {
            int op = JOptionPane.showConfirmDialog(null, "Confirma a exclusão");

            if (op == 0) {
                dao.Excluir(dadosFrmCliente());
                JOptionPane.showMessageDialog(null, "Exclusão realizada com sucesso!!!", "Exclusão", JOptionPane.INFORMATION_MESSAGE);
                listarTodos();
                limparCampos(frmClientes.getPaCliente());
            }
        }
    }

    private void salvarCliente() throws SQLException, ParseException {
        try {
            if (flagInsAltCons == 'I') {
                int codLivro;
                codLivro = dao.Inserir(dadosFrmCliente());
                frmClientes.getLblCodigo().setText(Integer.toString(codLivro));
                tabModel.addCLiente(dadosFrmCliente());
            } else {
                dao.Alterar(dadosFrmCliente());
                tabModel.setValueAt(frmClientes.getTxtNome().getText(), frmClientes.getTbCliente().getSelectedRow(), 1);
                tabModel.setValueAt(frmClientes.getTxtRg().getText(), frmClientes.getTbCliente().getSelectedRow(), 2);
                tabModel.setValueAt(frmClientes.getTxtTelefone().getText(), frmClientes.getTbCliente().getSelectedRow(), 3);
                tabModel.setValueAt(frmClientes.getTxtNascimento().getText(), frmClientes.getTbCliente().getSelectedRow(), 4);
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    private void listarTodos() throws SQLException {
        tabModel.limpar();
        tabModel.setClientes(dao.ListaClientes());
        frmClientes.getBtAlterar().setEnabled(true);
        frmClientes.getBtExcluir().setEnabled(true);
    }

    private void pesquisarCliente() throws SQLException {

        Cliente cliente;

        cliente = dao.localizarCliente(frmClientes.getTxtPesquisar().getText());
        dadosClienteFrm(cliente);
    }

    private void habilitaBotoesSalvar() {
        habilitaDesailitaBotoes(true);
        habilitaDesabilitaPainel(frmClientes.getPaCliente(), true);
    }

    private void habilitaDesailitaBotoes(boolean habilitado) {
        frmClientes.getBtAdicionar().setEnabled(!habilitado);
        frmClientes.getBtAlterar().setEnabled(!habilitado);
        frmClientes.getBtExcluir().setEnabled(!habilitado);
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

    private void dadosClienteFrm(Cliente cliente) {
        frmClientes.getLblCodigo().setText("" + cliente.getId());
        frmClientes.getTxtNome().setText(cliente.getNome());
        frmClientes.getTxtRg().setText(cliente.getRg());
        frmClientes.getTxtTelefone().setText(cliente.getTelefone());
        frmClientes.getTxtNascimento().setText(new SimpleDateFormat("dd/MMMM/yyyy").format(cliente.getDataNascimento()));
    }

    private Cliente dadosFrmCliente() throws ParseException {
        Cliente cliente = new Cliente();
        if (flagInsAltCons == 'I') {
            cliente.setId(Integer.parseInt(frmClientes.getLblCodigo().getText()));
        }

        cliente.setNome(frmClientes.getTxtNome().getText());
        cliente.setDataNascimento(new SimpleDateFormat("dd/MM/yyyy").parse(frmClientes.getTxtNascimento().getText()));
        cliente.setRg(frmClientes.getTxtRg().getText());
        cliente.setTelefone(frmClientes.getTxtTelefone().getText());

        return cliente;
    }

}
