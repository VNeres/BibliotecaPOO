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
        String sql = "INSERT INTO Emprestimo (emprestado para, livro emprestado, data emprestimo, data devoluçao) values (?,?,?,?)";
        
        stm = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        stm.setString(1, emprestimo.getEmprestadoPara());
        stm.setString(2, emprestimo.getLivroEmprestado());
        stm.setDate(3, (Date) emprestimo.getDataEmprestimo());
        stm.setDate(4, (Date) emprestimo.getDataDevolucao());
        
        stm.executeUpdate();
        
        rs = stm.getGeneratedKeys();
        
        //atualiza o atributo codigo (autoincremento) do objeto instanciado
        if (rs.next()){
            emprestimo.setID(rs.getInt(1));
        }
        
        return emprestimo.getID();
    }
    
    public void Alterar(Emprestimo emprestimo) throws SQLException{
        String sql = "UPDATE Emprestimo set emprestado para = ?, livro emprestado = ?, data emprestimo = ?, data devoluçao = ? WHERE id = ?";
        
        stm = con.prepareStatement(sql);
        stm.setString(1, emprestimo.getEmprestadoPara());
        stm.setString(2, emprestimo.getLivroEmprestado());
        stm.setDate(3, (Date) emprestimo.getDataEmprestimo());
        stm.setDate(4, (Date) emprestimo.getDataDevolucao());
        stm.setInt(5, emprestimo.getID());
        
        stm.executeUpdate();   
    }
    
    public void Excluir (Emprestimo emprestimo) throws SQLException{
        
        String sql = "DELETE FROM Emprestimo WHERE id = ?";
        
        stm = con.prepareStatement(sql);
        stm.setInt(1, emprestimo.getID());
        
         stm.executeUpdate();
    }
    
    
    public ArrayList<Emprestimo> ListaEmprestimos() throws SQLException{
        
        ArrayList<Emprestimo> emprestimos = new ArrayList<Emprestimo>();
        
        String sql = "SELECT * FROM Emprestimo";
        
        stm = con.prepareStatement(sql);
        rs = stm.executeQuery();
        
        while(rs.next()){
            emprestimos.add(new Emprestimo(rs.getInt(1),
                                rs.getString(2),
                                rs.getString(3),
                                rs.getDate(4), 
                                rs.getDate(5)));
        }
        return emprestimos;
    }
}
