package View;

import Model.Cliente;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author vande
 */
public class ClienteTM extends AbstractTableModel {

    private List<Cliente> linhas;
    private String[] colunas = new String[]{"Código", "Nome", "Telefone", "RG", "Data de Nascimento"};

    public ClienteTM() {
        linhas = new ArrayList<Cliente>();
    }

    public ClienteTM(ArrayList<Cliente> clientes) {
        linhas = new ArrayList<Cliente>(clientes);
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
                return linhas.get(linha).getTelefone();
            case 3:
                return linhas.get(linha).getRg();
            case 4:
                return linhas.get(linha).getDataNascimento();
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
                linhas.get(linha).setNome((String) valor);
                break;
            case 2:
                linhas.get(linha).setTelefone((String) valor);
                break;
            case 3:
                linhas.get(linha).setRg((String) valor);
                break;
            case 4:
                try {
                    linhas.get(linha).setDataNascimento(new SimpleDateFormat("dd/MM/yyyy").parse((String) valor));
                } catch (ParseException ex) {
                    Logger.getLogger(ClienteTM.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
        }
    }
    
    public void addCLiente(Cliente c) {
        linhas.add(c);
        fireTableDataChanged();
    }

    public void deleteCliente(int indiceLinha) {
        linhas.remove(indiceLinha);
        fireTableRowsDeleted(indiceLinha, indiceLinha);
    }

    public Cliente getCLiente(int indiceLinha) {
        return linhas.get(indiceLinha);
    }

    public List<Cliente> getCliente() {
        return linhas;
    }

    public void setLivros(List<Cliente> clientes) {
        int tamanhoAntigo = this.getRowCount();

        linhas.addAll(clientes);
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
