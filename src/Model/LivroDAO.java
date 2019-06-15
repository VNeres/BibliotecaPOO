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
    /*
     * Variável usada para retornar o status da conexão:
     *  true = sucesso e false = erro.
     */
    private boolean conStatus = false;

    public LivroDAO() {
        dao = new DaoBasicoConexao();
        try {
            con = dao.getConexao();
            this.setConStatus(true);
        } catch (SQLException e) {
            System.out.println(e);
            this.setConStatus(false);
        }
    }

    /*
     * Adicionados de modo a pegar o status da conexão no ctrCliente e add ao labelMensagemConexao.
     */
    public boolean isConStatus() {
        return conStatus;
    }

    public void setConStatus(boolean conStatus) {
        this.conStatus = conStatus;
    }

    public int Inserir(Livro livro) throws SQLException {
        String sql = "INSERT INTO livros (nome, autor, ano, editora, quantidade) values (?,?,?,?,?)";

        stm = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        stm.setString(1, livro.getNome());
        stm.setString(2, livro.getAutor());
        stm.setString(3, livro.getAno());
        stm.setString(4, livro.getEditora());
        stm.setString(5, livro.getQuantidade());

        stm.executeUpdate();

        rs = stm.getGeneratedKeys();

        //atualiza o atributo codigo (autoincremento) do objeto instanciado
        if (rs.next()) {
            livro.setId(rs.getInt(1));
        }

        return livro.getId();
    }

    public void Alterar(Livro livro) throws SQLException {
        String sql = "UPDATE livros set nome = ?, autor = ?, ano = ?, editora = ?, quantidade = ? WHERE idLivro = ?";

        stm = con.prepareStatement(sql);
        stm.setString(1, livro.getNome());
        stm.setString(2, livro.getAutor());
        stm.setString(3, livro.getAno());
        stm.setString(4, livro.getEditora());
        stm.setString(5, livro.getQuantidade());
        stm.setInt(6, livro.getId());
        stm.executeUpdate();
    }

    public void Excluir(Livro livro) throws SQLException {

        String sql = "DELETE FROM livros WHERE idLivro = ?";

        stm = con.prepareStatement(sql);
        stm.setInt(1, livro.getId());

        stm.executeUpdate();
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

    public ArrayList<Livro> ListaLivros() throws SQLException {

        ArrayList<Livro> livros = new ArrayList<Livro>();

        String sql = "SELECT * FROM livros";

        stm = con.prepareStatement(sql);
        rs = stm.executeQuery();

        while (rs.next()) {
            livros.add(new Livro(rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6)));
        }
        return livros;
    }
}
