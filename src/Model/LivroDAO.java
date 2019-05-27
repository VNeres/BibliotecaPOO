package Model;

import framework.DaoBasicoConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LivroDAO {
    private Connection con = null;
    private PreparedStatement stm;
    private ResultSet rs;
    private DaoBasicoConexao dao;
    
    public LivroDAO()  {
        dao = new DaoBasicoConexao();
        try{
            con = dao.getConexao();
        }
        catch (SQLException e){
            System.out.println(e);
        }
    }
    
    public int Inserir (Livro livro) throws SQLException{
        String sql = "INSERT INTO Livro (nome, autor, ano, disponivel) values (?,?,?,?)";
        
        stm = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        stm.setString(1, livro.getNome());
        stm.setString(2, livro.getAutor());
        stm.setInt(3, livro.getAno());
        stm.setBoolean(4, true);
        
        stm.executeUpdate();
        
        rs = stm.getGeneratedKeys();
        
        //atualiza o atributo codigo (autoincremento) do objeto instanciado
        if (rs.next()){
            livro.setId(rs.getInt(1));
        }
        
        return livro.getId();
    }
    
    public void Alterar(Livro livro) throws SQLException{
        String sql = "UPDATE Livro set nome = ?, autor = ?, ano = ?, disponivel = ? WHERE id = ?";
        
        stm = con.prepareStatement(sql);
        stm.setString(1, livro.getNome());
        stm.setString(2, livro.getAutor());
        stm.setInt(3, livro.getAno());
        stm.setBoolean(4, true);
        stm.setInt(5, livro.getId());
        
        stm.executeUpdate();   
    }
    
    public void Excluir (Livro livro) throws SQLException{
        
        String sql = "DELETE FROM Livro WHERE id = ?";
        
        stm = con.prepareStatement(sql);
        stm.setInt(1, livro.getId());
        
         stm.executeUpdate();
    }
    
    public Livro localizarLivro(String locLivro) throws SQLException{
        
        String sql = "SELECT * FROM Livro WHERE nome = ? ";
        stm = con.prepareStatement(sql);
        stm.setString(1, locLivro);
        
        rs = stm.executeQuery();
        Livro livro = new Livro(rs.getInt(1),
                                rs.getString(2),
                                rs.getString(3),
                                rs.getInt(4), 
                                rs.getBoolean(5));
        return livro;
    }
    
    public ArrayList<Livro> ListaLivros() throws SQLException{
        
        ArrayList<Livro> livros = new ArrayList<Livro>();
        
        String sql = "SELECT * FROM Livro";
        
        stm = con.prepareStatement(sql);
        rs = stm.executeQuery();
        
        while(rs.next()){
            livros.add(new Livro(rs.getInt(1),
                                rs.getString(2),
                                rs.getString(3),
                                rs.getInt(4), 
                                rs.getBoolean(5)));
        }
        return livros;
    }
}
