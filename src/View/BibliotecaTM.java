package View;

import Model.Cliente;
import Model.Emprestimo;
import Model.Livro;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;


public class BibliotecaTM extends AbstractTableModel {
    
    private List<Livro> linhasLivros;
    private List<Cliente> linhasClientes;
    private List<Emprestimo> linhasEmprestimos;

    public BibliotecaTM() {
        linhasLivros = new ArrayList<Livro>();
        linhasClientes = new ArrayList<Cliente>();
        linhasEmprestimos = new ArrayList<Emprestimo>();
    }
    
    
    @Override
    public int getRowCount() {
        return linhasLivros.size();
        
    }

    @Override
    public int getColumnCount() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
