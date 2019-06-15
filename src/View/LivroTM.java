package View;

import Model.Livro;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class LivroTM extends AbstractTableModel {

    private List<Livro> linhas;
    private String[] colunas = new String[]{"Código", "Nome", "Autor", "Ano", "Editora", "Quantidade"};

    public LivroTM() {
        linhas = new ArrayList<Livro>();
    }

    public LivroTM(ArrayList<Livro> livros) {
        linhas = new ArrayList<Livro>(livros);
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
                return linhas.get(linha).getNome();
            case 2:
                return linhas.get(linha).getAutor();
            case 3:
                return linhas.get(linha).getAno();
            case 4:
                return linhas.get(linha).getEditora();
            case 5:
                return linhas.get(linha).getQuantidade();
        }
        return null;
    }

    @Override
    public void setValueAt(Object valor, int linha, int coluna) {

        switch (coluna) {
            case 0:
                linhas.get(linha).setId((int) valor);
                break;
            case 1:
                linhas.get(linha).setNome((String) valor);
                break;
            case 2:
                linhas.get(linha).setAutor((String) valor);
                break;
            case 3:
                linhas.get(linha).setAno((String) valor);
                break;
            case 4:
                linhas.get(linha).setEditora((String) valor);
                break;
            case 5:
                linhas.get(linha).setQuantidade((String) valor);
                break;
        }
    }
    
    public String getColumnName( int coluna ) {
        return colunas[coluna];
    }

    public void addLivro(Livro c) {
        linhas.add(c);
        fireTableDataChanged();
    }

    public void deleteLivro(int indiceLinha) {
        linhas.remove(indiceLinha);
        fireTableRowsDeleted(indiceLinha, indiceLinha);
    }

    public Livro getLivro(int indiceLinha) {
        return linhas.get(indiceLinha);
    }

    public List<Livro> getLivros() {
        return linhas;
    }

    public void setLivros(List<Livro> livros) {
        int tamanhoAntigo = this.getRowCount();

        linhas.addAll(livros);
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
