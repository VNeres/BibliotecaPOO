/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import View.JFLivro;
import View.LivroTM;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author vande
 */
public class ctrLivro implements ActionListener, ListSelectionListener {

    private JFLivro frmLivros;
    private LivroTM tabModel;

    public ctrLivro() {
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
            excluirlivro();
        }else if(acao.getActionCommand().equals("Salvar")){
            salvarVinho();
        }else if (acao.getActionCommand().equals("Cancelar")){
            cancelarLivro();
        }else if (acao.getActionCommand().equals("Listar Todos")){
            listarTodos();
        }else if(acao.getActionCommand().equals("Pesquisar")){
            pesquisarLivro();
        }

    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
    }

}
