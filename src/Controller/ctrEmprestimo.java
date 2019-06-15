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
    
    

    private EmprestimoDAO dao = new EmprestimoDAO();
    
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
        listarTodos();
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
            
        } else if (acao.getActionCommand().equals("Excluir")) {
            try {
                excluirEmprestimo();
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
        } else if (acao.getActionCommand().equals("Buscar")) {
            try {
                pesquisarLivro();
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
        frmEmprestimos.getTbEmprestimo().getSelectionModel().addListSelectionListener(this);
    }

  /*  private void alterarEmprestimo() {
        if ("".equals(frmEmprestimos.getLblCodigo().getText())) {
            habilitaBotoesSalvar();
            flagInsAltCons = 'A';
        }
    }*/

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

    private void incluirEmprestimo() throws SQLException, ParseException{
        operacao = 'A';
        
        if(operacao == 'A'){
            int codLivro;
            codLivro = dao.Inserir(dadosFrmEmprestimo());
            frmEmprestimos.getLblCodigo().setText(Integer.toString(codLivro));
            operacao = 'a';
            tabModel.addEmprestimo(dadosFrmEmprestimo());
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
    
    private void pesquisarCliente() throws SQLException {
        cliente = dao.localizarCliente(frmEmprestimos.getTxtPesquisarCliente().getText());
        frmEmprestimos.getTxtNomeCliente().setText(cliente.getNome());
        //frmEmprestimos.getLblCodigo().setText(frmEmprestimos.getTxtPesquisar().getText());
        System.out.println(cliente);
        
        
    }
    
    private void pesquisarLivro() throws SQLException {
        livro = dao.localizarLivro(Integer.parseInt(frmEmprestimos.getTxtPesquisarLivro().getText()));
        frmEmprestimos.getTxtNomeLivro().setText(livro.getNome());
        frmEmprestimos.getTxtNomeEditora().setText(livro.getEditora());
        //frmEmprestimos.getLblCodigo().setText(frmEmprestimos.getTxtPesquisar().getText());
        System.out.println(livro);
        
        
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
        frmEmprestimos.getTxtNomeCliente().setText(cliente.getNome());
        frmEmprestimos.getTxtNomeLivro().setText(livro.getNome());
    }

    private Emprestimo dadosFrmEmprestimo() throws ParseException {
        Emprestimo emprestimo = new Emprestimo();
        if (operacao == 'a') {
            emprestimo.setId(Integer.parseInt(frmEmprestimos.getLblCodigo().getText()));
            emprestimo.setDataEmprestimo(date2);
        }
        
        emprestimo.setIdCliente(cliente.getId());
        emprestimo.setIdLivro(livro.getId());
        emprestimo.setDataDevolucao(frmEmprestimos.getTxtDataDevolucao().getText());
        
        return emprestimo;        
    }
    
}
