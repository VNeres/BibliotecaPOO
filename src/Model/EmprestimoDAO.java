package Model;

import framework.DaoBasicoConexao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    //private SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
    private LocalDate hoje = LocalDate.now();
    private Date date = java.sql.Date.valueOf(hoje);
    
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
        String sql = "INSERT INTO emprestimo (idLivro, idCliente, dataEmprestimo, dataDevolucao) values (?,?,?,?)";
        
        stm = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        stm.setInt(1, emprestimo.getIdLivro());
        stm.setInt(2, emprestimo.getIdCliente());
        stm.setDate(3, date);
        stm.setString(4, emprestimo.getDataDevolucao());
        System.out.println();
        stm.executeUpdate();
        
        rs = stm.getGeneratedKeys();
        
        //atualiza o atributo codigo (autoincremento) do objeto instanciado
        if (rs.next()){
            emprestimo.setId(rs.getInt(1));
        }
        
        return emprestimo.getId();
    }
    
    public void Alterar(Emprestimo emprestimo) throws SQLException{
        String sql = "UPDATE emprestimo set idCliente = ?, idLivro = ?, dataDevolucao = ? WHERE id = ?";
        
        stm = con.prepareStatement(sql);
        stm.setString(1, Integer.toString(emprestimo.getIdCliente()));
        stm.setString(2, Integer.toString(emprestimo.getIdLivro()));
        stm.setString(3, emprestimo.getDataDevolucao());
        stm.setInt(4, emprestimo.getId());
        
        stm.executeUpdate();   
    }
    
    public void Excluir (Emprestimo emprestimo) throws SQLException{
        
        String sql = "DELETE FROM emprestimo WHERE id = ?";
        
        stm = con.prepareStatement(sql);
        stm.setInt(1, emprestimo.getId());
        
         stm.executeUpdate();
    }
    
    
    public ArrayList<Emprestimo> ListaEmprestimos() throws SQLException{
        
        ArrayList<Emprestimo> emprestimos = new ArrayList<Emprestimo>();
        
        String sql = "SELECT * FROM emprestimo E INNER JOIN Cliente C ON E.idCliente = C.Id LEFT JOIN Livro L ON E.idLivro = L.Id";
        
        stm = con.prepareStatement(sql);
        rs = stm.executeQuery();
        
        while(rs.next()){
            emprestimos.add(new Emprestimo(rs.getInt(1),
                                rs.getInt(2),
                                rs.getInt(3),
                                rs.getDate(4),
                                rs.getString(5)));
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
                                rs.getString(5));
        return emprestimo;
    }
        
    public Cliente localizarCliente(String locCliente) throws SQLException {
        String sql = "SELECT * FROM clientes WHERE rg = ? ";
        stm = con.prepareStatement(sql);
        stm.setString(1, locCliente);
        rs = stm.executeQuery();
        Cliente cliente = null;
        if (rs.next()) {
            cliente = new Cliente(rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5));
        }
        System.out.println(cliente);
        return cliente;
    }
    
    public Livro localizarLivro(int locLivro) throws SQLException {

        String sql = "SELECT * FROM livros WHERE idLivro = ? ";
        stm = con.prepareStatement(sql);
        stm.setInt(1, locLivro);

        rs = stm.executeQuery();
        Livro livro = null;
        
        if(rs.next()) {
        livro = new Livro(rs.getInt(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4),
                rs.getString(5),
                rs.getString(6));
        
        }
        return livro;
    }
}
