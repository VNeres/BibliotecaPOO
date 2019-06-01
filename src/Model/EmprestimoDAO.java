package Model;

import framework.DaoBasicoConexao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author aluno
 */
public class EmprestimoDAO {
    private Connection con = null;
    private PreparedStatement stm;
    private ResultSet rs;
    private DaoBasicoConexao dao;
    
    public EmprestimoDAO()  {
        dao = new DaoBasicoConexao();
        try{
            con = dao.getConexao();
        }
        catch (SQLException e){
            System.out.println(e);
        }
    }
    
    public int Inserir (Emprestimo emprestimo) throws SQLException{
        String sql = "INSERT INTO Emprestimo (idLivro, idCliente, dataEmprestimo, dataDevolucao) values (?,?,?,?)";
        
        stm = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        stm.setString(1, Integer.toString(emprestimo.getIdCliente()));
        stm.setString(2, Integer.toString(emprestimo.getIdLivro()));
        stm.setDate(3, (Date) emprestimo.getDataEmprestimo());
        stm.setDate(4, (Date) emprestimo.getDataDevolucao());
        
        stm.executeUpdate();
        
        rs = stm.getGeneratedKeys();
        
        //atualiza o atributo codigo (autoincremento) do objeto instanciado
        if (rs.next()){
            emprestimo.setId(rs.getInt(1));
        }
        
        return emprestimo.getId();
    }
    
    public void Alterar(Emprestimo emprestimo) throws SQLException{
        String sql = "UPDATE Emprestimo set idCliente = ?, idLivro = ?, dataDevolucao = ? WHERE id = ?";
        
        stm = con.prepareStatement(sql);
        stm.setString(1, Integer.toString(emprestimo.getIdCliente()));
        stm.setString(2, Integer.toString(emprestimo.getIdLivro()));
        stm.setDate(3, (Date) emprestimo.getDataDevolucao());
        stm.setInt(4, emprestimo.getId());
        
        stm.executeUpdate();   
    }
    
    public void Excluir (Emprestimo emprestimo) throws SQLException{
        
        String sql = "DELETE FROM Emprestimo WHERE id = ?";
        
        stm = con.prepareStatement(sql);
        stm.setInt(1, emprestimo.getId());
        
         stm.executeUpdate();
    }
    
    
    public ArrayList<Emprestimo> ListaEmprestimos() throws SQLException{
        
        ArrayList<Emprestimo> emprestimos = new ArrayList<Emprestimo>();
        
        String sql = "SELECT * FROM Emprestimo E INNER JOIN Cliente C ON E.idCliente = C.Id LEFT JOIN Livro L ON E.idLivro = L.Id";
        
        stm = con.prepareStatement(sql);
        rs = stm.executeQuery();
        
        while(rs.next()){
            emprestimos.add(new Emprestimo(rs.getInt(1),
                                rs.getInt(2),
                                rs.getInt(3),
                                rs.getDate(4),
                                rs.getDate(5)));
        }
        return emprestimos;
    }
    
        public Emprestimo localizarEmprestimo(String locEmprestimo) throws SQLException{
        
        String sql = "SELECT * FROM emprestimo WHERE  id like %?% or idLivro like %?% or idCliente like %?% or dataEmprestimo like %?% or dataDevolucao like %?% ";
        stm = con.prepareStatement(sql);
        stm.setString(1, locEmprestimo);
        stm.setString(2, locEmprestimo);
        stm.setString(3, locEmprestimo);
        stm.setString(4, locEmprestimo);
        stm.setString(5, locEmprestimo);
        
        rs = stm.executeQuery();
        Emprestimo emprestimo = new Emprestimo(rs.getInt(1),
                                rs.getInt(2),
                                rs.getInt(3),
                                rs.getDate(4),
                                rs.getDate(5));
        return emprestimo;
    }
}
