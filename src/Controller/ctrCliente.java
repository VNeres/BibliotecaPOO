package Controller;

import Model.Cliente;
import Model.ClienteDAO;
import View.ClienteTM;
import View.JFCliente;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
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

    private int id;

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

    public ctrCliente() throws SQLException {
    }

    public ctrCliente(JFCliente frmClientes) {
        this.frmClientes = frmClientes;
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
            frmClientes.getLblMensagemConexao().setForeground(new Color(0, 210, 0));
            frmClientes.getLblMensagemConexao().setText("Conectado");
        } else if (!dao.isConStatus()) {
            frmClientes.getLblMensagemConexao().setForeground(new Color(210, 0, 0));
            frmClientes.getLblMensagemConexao().setText("Não Conectado");
        }
    }

    private void adicionarListener() {
        frmClientes.getBtAdicionar().addActionListener(this);
        frmClientes.getBtAlterar().addActionListener(this);
        frmClientes.getBtExcluir().addActionListener(this);
        frmClientes.getBtnBuscar().addActionListener(this);
        frmClientes.getBtnListar().addActionListener(this);
        frmClientes.getBtnLimpar().addActionListener(this);
        frmClientes.getTbCliente().getSelectionModel().addListSelectionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent acao) {

        if (acao.getActionCommand().equals("Adicionar")) {
            try {
                incluirCliente();
            } catch (ParseException ex) {
                System.err.println("Erro ao Incluir");
            } catch (SQLException ex) {
            }
        } else if (acao.getActionCommand().equals("Alterar")) {
            try {
                alterarCliente();
            } catch (ParseException | SQLException ex) {
            }
        } else if (acao.getActionCommand().equals("Listar Clientes")) {
            try {
                listarTodos();
            } catch (SQLException ex) {
            }
        } else if (acao.getActionCommand().equals("Excluir")) {
            try {
                excluirCliente();
            } catch (SQLException | ParseException ex) {
            }
        } else if (acao.getActionCommand().equals("Buscar")) {
            try {
                pesquisarCliente();
            } catch (SQLException ex) {
            }
        } else if (acao.getActionCommand().equals("Limpar")) {
            limparTabela();
        }
    }

    private void inicializaTableModel() {
        tabModel = new ClienteTM();
        frmClientes.getTbCliente().setModel(tabModel);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        Cliente cliente = tabModel.getClientes().get(frmClientes.getTbCliente().getSelectedRow());
        dadosClienteFrm(cliente);

    }

    private void incluirCliente() throws ParseException, SQLException {
        operacao = 'A';

        try {
            if (operacao == 'A') {
                int idCliente;
                idCliente = dao.Inserir(dadosFrmCliente());
                frmClientes.getLblCodigo().setText(Integer.toString(idCliente));
                operacao = 'a';
                tabModel.addCLiente(dadosFrmCliente());

            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        limparCampos(frmClientes.getPaCliente());

    }

    private void alterarCliente() throws ParseException, SQLException {
        operacao = 'M';
        if (Integer.toString(id).equals(frmClientes.getLblCodigo().getText())) {
            dao.Alterar(dadosFrmCliente());
            tabModel.setValueAt(frmClientes.getTxtNome().getText(), frmClientes.getTbCliente().getSelectedRow(), 2);
            tabModel.setValueAt(frmClientes.getTxtRg().getText(), frmClientes.getTbCliente().getSelectedRow(), 3);
            tabModel.setValueAt(frmClientes.getTxtTelefone().getText(), frmClientes.getTbCliente().getSelectedRow(), 4);
            tabModel.setValueAt(frmClientes.getTxtNascimento().getText(), frmClientes.getTbCliente().getSelectedRow(), 5);
        }
        limparCampos(frmClientes.getPaCliente());
        listarTodos();
    }

    private void excluirCliente() throws SQLException, ParseException {
        operacao = 'E';

        if (Integer.toString(id).equals(frmClientes.getLblCodigo().getText())) {
            int op = JOptionPane.showConfirmDialog(null, "Confirma a exclusão");

            if (op == 0) {
                dao.Excluir(dadosFrmCliente());
                JOptionPane.showMessageDialog(null, "Exclusão realizada com sucesso!!!", "Exclusão", JOptionPane.INFORMATION_MESSAGE);
                limparCampos(frmClientes.getPaCliente());
                tabModel.setClientes(dao.ListaClientes());
                tabModel.limpar();
                listarTodos();
            }
        }
    }

    private void limparTabela() {
        frmClientes.getTbCliente().getSelectionModel().removeListSelectionListener(this);
        tabModel.limpar();
        limparCampos(frmClientes.getPaCliente());
        frmClientes.getTbCliente().getSelectionModel().addListSelectionListener(this);
    }

    public void listarTodos() throws SQLException {
        frmClientes.getTbCliente().getSelectionModel().removeListSelectionListener(this);
        operacao = 'L';
        tabModel.limpar();
        tabModel.setClientes(dao.ListaClientes());
        frmClientes.getTbCliente().getSelectionModel().addListSelectionListener(this);
    }

    private void pesquisarCliente() throws SQLException {
        Cliente cliente;
        cliente = dao.localizarCliente(Integer.parseInt(frmClientes.getTxtPesquisar().getText()));
        dadosClienteFrm(cliente);
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
        id = cliente.getId();
        frmClientes.getLblCodigo().setText("" + cliente.getId());
        frmClientes.getTxtNome().setText(cliente.getNome());
        frmClientes.getTxtTelefone().setText(cliente.getTelefone());
        frmClientes.getTxtRg().setText(cliente.getRg());
        frmClientes.getTxtNascimento().setText(cliente.getDataNascimento());
    }

    private Cliente dadosFrmCliente() throws ParseException, SQLException {
        Cliente cliente = new Cliente();
        if (operacao == 'a') {
            cliente.setId(Integer.parseInt(frmClientes.getLblCodigo().getText()));

        } else if (operacao == 'E') {
            cliente.setId(Integer.parseInt(frmClientes.getLblCodigo().getText()));
            frmClientes.getTbCliente().getSelectionModel().removeListSelectionListener(this);

        } else if (operacao == 'M') {
            cliente.setId(Integer.parseInt(frmClientes.getLblCodigo().getText()));
        }

        cliente.setNome(frmClientes.getTxtNome().getText());
        cliente.setTelefone(frmClientes.getTxtTelefone().getText());
        cliente.setRg(frmClientes.getTxtRg().getText());
        cliente.setDataNascimento(frmClientes.getTxtNascimento().getText());

        return cliente;

    }

}
