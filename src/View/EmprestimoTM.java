package View;

import Model.Emprestimo;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

public class EmprestimoTM extends AbstractTableModel {

    private List<Emprestimo> linhas;
    private String[] colunas = new String[]{"Código", "Livro Emprestado", "Emprestado Para", "Data Empréstimo", "Data Devolução"};

    public EmprestimoTM() {
        linhas = new ArrayList<Emprestimo>();
    }

    public EmprestimoTM(ArrayList<Emprestimo> emprestimos) {
        linhas = new ArrayList<Emprestimo>(emprestimos);
    }

    @Override
    public int getRowCount() {
        return linhas.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {

        switch (coluna) {
            case 0:
                return linhas.get(linha).getId();
            case 1:
                return linhas.get(linha).getIdCliente();
            case 2:
                return linhas.get(linha).getIdLivro();
            case 3:
                return linhas.get(linha).getDataEmprestimo();
            case 4:
                return linhas.get(linha).getDataDevolucao();
        }
        return null;
    }

    @Override
    public void setValueAt(Object valor, int linha, int coluna) {

        switch (coluna) {
            case 0:
                linhas.get(linha).setId(Integer.parseInt((String) valor));
                break;
            case 1:
                linhas.get(linha).setIdCliente(Integer.parseInt((String)valor));
                break;
            case 2:
                linhas.get(linha).setIdLivro(Integer.parseInt((String) valor));
                break;
            case 3:
                linhas.get(linha).setDataEmprestimo(new Date());
                break;
            case 4:
                try {
                    linhas.get(linha).setDataDevolucao(new SimpleDateFormat("dd/MM/yyyy").parse((String) valor));
                } catch (ParseException ex) {
                    Logger.getLogger(EmprestimoTM.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
        }
    }
    
    public void addEmprestimo(Emprestimo e) {
        linhas.add(e);
        fireTableDataChanged();
    }

    public void devolveEmprestimo(int indiceLinha) {
        linhas.remove(indiceLinha);
        fireTableRowsDeleted(indiceLinha, indiceLinha);
    }

    public Emprestimo getEmprestimo(int indiceLinha) {
        return linhas.get(indiceLinha);
    }

    public List<Emprestimo> getEmprestimos() {
        return linhas;
    }

    public void setEmprestimos(List<Emprestimo> emprestimos) {
        int tamanhoAntigo = this.getRowCount();

        linhas.addAll(emprestimos);
        fireTableRowsInserted(tamanhoAntigo, this.getRowCount() - 1);
    }

    public void limpar() {
        linhas.clear();
        fireTableDataChanged();
    }
    
    public boolean isEmpty(){
        return linhas.isEmpty();
    }

}
