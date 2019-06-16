/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Cliente;
import Model.Emprestimo;
import Model.EmprestimoDAO;
import Model.Livro;
import View.ClienteTM;
import View.EmprestimoTM;
import View.JFEmprestimo;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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

/*
 *Data de empréstimo é a do sistema(data atual)
 */
public class ctrEmprestimo extends ClienteTM implements ActionListener, ListSelectionListener {

    private JFEmprestimo frmEmprestimos;
    private EmprestimoTM tabModel;
    private Cliente cliente;
    private Livro livro;
    private int id;
    private LocalDate hoje = LocalDate.now();
    private Date date2 = java.sql.Date.valueOf(hoje);

    private SimpleDateFormat SimpleData = new SimpleDateFormat("dd/MM/yyyy");

    private EmprestimoDAO dao = new EmprestimoDAO();
    Emprestimo emprestimo = new Emprestimo();

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

    public ctrEmprestimo() throws SQLException {
        listarEmprestimos();
    }

    public ctrEmprestimo(JFEmprestimo frmEmprestimos) {
        this.frmEmprestimos = frmEmprestimos;
        inicializaTableModel();
        adicionarListener();
    }

    @Override
    public void actionPerformed(ActionEvent acao) {

        if (acao.getActionCommand().equals("Emprestar")) {
            try {
                incluirEmprestimo();
            } catch (SQLException ex) {
                Logger.getLogger(ctrEmprestimo.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(ctrEmprestimo.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (acao.getActionCommand().equals("Alterar")) {
            try {
                alterarLivro();
            } catch (SQLException ex) {
                Logger.getLogger(ctrEmprestimo.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(ctrEmprestimo.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (acao.getActionCommand().equals("Devolver")) {
            try {
                devolverEmprestimo();
            } catch (SQLException ex) {
                Logger.getLogger(ctrLivro.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(ctrEmprestimo.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (acao.getActionCommand().equals("Listar Empréstimos")) {
            try {
                listarEmprestimos();
            } catch (SQLException ex) {
                Logger.getLogger(ctrLivro.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (acao.getActionCommand().equals("Buscar")) {
            try {
                pesquisarEmprestimo();
            } catch (SQLException ex) {
                Logger.getLogger(ctrLivro.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (acao.getActionCommand().equals("Pesquisar Cliente")) {
            try {
                pesquisarCliente();
            } catch (SQLException ex) {
                Logger.getLogger(ctrLivro.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (acao.getActionCommand().equals("Pesquisar Livro")) {
            try {
                pesquisarLivro();
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
        frmEmprestimos.getBtnPesquisarLivro().addActionListener(this);
        frmEmprestimos.getBtnPesquisarCliente().addActionListener(this);
        frmEmprestimos.getBtnListarEmprestimos().addActionListener(this);
        frmEmprestimos.getTbEmprestimo().getSelectionModel().addListSelectionListener(this);
    }

    /*  private void alterarEmprestimo() {
     if ("".equals(frmEmprestimos.getLblCodigo().getText())) {
     habilitaBotoesSalvar();
     flagInsAltCons = 'A';
     }
     }*/
    private void devolverEmprestimo() throws SQLException, ParseException {

        operacao = 'E';

        if (Integer.toString(id).equals(frmEmprestimos.getLblCodigo().getText())) {
            int op = JOptionPane.showConfirmDialog(null, "Confirma a devolução");

            if (op == 0) {
                dao.Excluir(dadosFrmEmprestimo());
                JOptionPane.showMessageDialog(null, "Devolução realizada com sucesso!!!", "Devolução", JOptionPane.INFORMATION_MESSAGE);
                listarEmprestimos();
                limparCampos(frmEmprestimos.getPaEmprestimo());
            }
        }
    }

    private void incluirEmprestimo() throws SQLException, ParseException {
        operacao = 'A';

        try {
            int quantidade = Integer.parseInt(livro.getQuantidade());

            int codEmprestimo;

            if (operacao == 'A') {

                if (quantidade == 0) {
                    JOptionPane.showMessageDialog(null, "O livro " + livro.getNome() + " não está disponível para empréstimo");
                } else {
                    codEmprestimo = dao.Inserir(dadosFrmEmprestimo());
                    frmEmprestimos.getLblCodigo().setText(Integer.toString(codEmprestimo));
                    operacao = 'a';

                    listarEmprestimos();
                    pesquisarLivro();
                    pesquisarCliente();
                }
            }
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Os campos não podem ficar vazios");
        }
    }

    private void alterarLivro() throws SQLException, ParseException {
        operacao = 'M';
        if (Integer.toString(id).equals(frmEmprestimos.getLblCodigo().getText())) {
            dao.Alterar(dadosFrmEmprestimo());
            tabModel.setValueAt(frmEmprestimos.getTxtDataDevolucao().getText(), frmEmprestimos.getTbEmprestimo().getSelectedRow(), 1);
        }
        limparCampos(frmEmprestimos.getPaEmprestimo());
        listarEmprestimos();
    }

    public void listarEmprestimos() throws SQLException {
        frmEmprestimos.getTbEmprestimo().getSelectionModel().removeListSelectionListener(this);
        operacao = 'L';
        tabModel.limpar();
        tabModel.setEmprestimos(dao.ListaEmprestimos());
        frmEmprestimos.getTbEmprestimo().getSelectionModel().addListSelectionListener(this);
    }

    private void pesquisarEmprestimo() throws SQLException {
        try {
            Emprestimo emprestimo;
            emprestimo = dao.localizarEmprestimo(Integer.parseInt(frmEmprestimos.getTxtPesquisar().getText()));
            dadosEmprestimoFrm(emprestimo);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Por favor, digite apenas números");
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Empréstimo não encontrado, tente novamente!");
        }
    }

    private void pesquisarCliente() throws SQLException {
        try {
            cliente = dao.localizarCliente(frmEmprestimos.getTxtPesquisarCliente().getText());
            frmEmprestimos.getTxtNomeCliente().setText(cliente.getNome());
            emprestimo.setIdCliente(cliente.getId());
            //frmEmprestimos.getLblCodigo().setText(frmEmprestimos.getTxtPesquisar().getText());
            System.out.println(cliente);
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Cliente não encontrado, tente novamente!");
        }

    }

    private void pesquisarLivro() throws SQLException {
        try {
            livro = dao.localizarLivro(Integer.parseInt(frmEmprestimos.getTxtPesquisarLivro().getText()));
            frmEmprestimos.getTxtNomeLivro().setText(livro.getNome());
            frmEmprestimos.getTxtNomeEditora().setText(livro.getEditora());
            emprestimo.setIdLivro(livro.getId());
            //frmEmprestimos.getLblCodigo().setText(frmEmprestimos.getTxtPesquisar().getText());
            System.out.println(livro);
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Livro não encontrado, tente novamente!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Digite apenas números!!!");
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
        id = emprestimo.getId();
        frmEmprestimos.getLblCodigo().setText("" + emprestimo.getId());
        frmEmprestimos.getTxtNomeCliente().setText(emprestimo.getCliente());
        frmEmprestimos.getTxtNomeLivro().setText(emprestimo.getLivro());
        frmEmprestimos.getTxtDataDevolucao().setText(emprestimo.getDataDevolucao());
        frmEmprestimos.getTxtDataEmprestimo().setText(SimpleData.format(emprestimo.getDataEmprestimo()));
    }

    private Emprestimo dadosFrmEmprestimo() throws ParseException {
        if (operacao == 'a') {
            emprestimo.setId(Integer.parseInt(frmEmprestimos.getLblCodigo().getText()));
        } else if (operacao == 'E') {
            emprestimo.setId(Integer.parseInt(frmEmprestimos.getLblCodigo().getText()));
            frmEmprestimos.getTbEmprestimo().getSelectionModel().removeListSelectionListener(this);
        }

        emprestimo.setIdCliente(emprestimo.getIdCliente());
        emprestimo.setIdLivro(emprestimo.getIdLivro());
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String data = dateFormat.format(date2);
        emprestimo.setDataEmprestimo(SimpleData.parse(data));
        emprestimo.setDataDevolucao(frmEmprestimos.getTxtDataDevolucao().getText());

        return emprestimo;
    }

}
