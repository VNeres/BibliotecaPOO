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
    private SimpleDateFormat SimpleData = new SimpleDateFormat("yyyy-MM-dd");
    private LocalDate hoje = LocalDate.now();
    private Date date = java.sql.Date.valueOf(hoje);

    public EmprestimoDAO() {
        dao = new DaoBasicoConexao();
        try {
            con = dao.getConexao();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public int Inserir(Emprestimo emprestimo) throws SQLException {

        String sql = "INSERT INTO emprestimo (idLivro, idCliente, dataEmprestimo, dataDevolucao) values (?,?,?,?)";

        stm = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        stm.setInt(1, emprestimo.getIdLivro());
        stm.setInt(2, emprestimo.getIdCliente());
        stm.setString(3, SimpleData.format(emprestimo.getDataEmprestimo()));
        stm.setString(4, emprestimo.getDataDevolucao());

        stm.executeUpdate();

        rs = stm.getGeneratedKeys();

        //atualiza o atributo codigo (autoincremento) do objeto instanciado
        if (rs.next()) {
            emprestimo.setId(rs.getInt(1));
        }

        decrementarLivro(emprestimo.getIdLivro());

        return emprestimo.getId();
    }

    public void Alterar(Emprestimo emprestimo) throws SQLException {
        String sql = "UPDATE emprestimo set dataDevolucao = ? WHERE idEmprestimo = ?";

        stm = con.prepareStatement(sql);
        stm.setString(1,emprestimo.getDataDevolucao());
        stm.setInt(2, emprestimo.getId());

        stm.executeUpdate();
    }

    public void incrementarLivro(int idLivro) throws SQLException {
        String sql = "UPDATE livros SET quantidade = quantidade + 1 where idLivro = ?";

        stm = con.prepareStatement(sql);
        stm.setInt(1, idLivro);

        stm.executeUpdate();
    }

    public void decrementarLivro(int idLivro) throws SQLException {
        String sql = "UPDATE livros SET quantidade = quantidade - 1 WHERE idLivro = ?";

        stm = con.prepareStatement(sql);
        stm.setInt(1, idLivro);

        stm.executeUpdate();
    }

    public void Excluir(Emprestimo emprestimo) throws SQLException {

        String sql = "DELETE FROM emprestimo WHERE idEmprestimo = ?;";

        stm = con.prepareStatement(sql);
        stm.setInt(1, emprestimo.getId());

        stm.executeUpdate();
        
        incrementarLivro(emprestimo.getIdLivro());
    }

    public ArrayList<Emprestimo> ListaEmprestimos() throws SQLException {

        ArrayList<Emprestimo> emprestimos = new ArrayList<Emprestimo>();

        String sql = "SELECT emprestimo.idEmprestimo, livros.nome, clientes.nome, emprestimo.dataEmprestimo, emprestimo.dataDevolucao "
                + "FROM emprestimo "
                + "INNER JOIN livros on emprestimo.idLivro = livros.idLivro "
                + "INNER JOIN clientes on emprestimo.idCliente = clientes.idCliente";

        stm = con.prepareStatement(sql);
        rs = stm.executeQuery();

        while (rs.next()) {
            emprestimos.add(new Emprestimo(rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getDate(4),
                    rs.getString(5)));
        }
        return emprestimos;
    }

    public Emprestimo localizarEmprestimo(int locEmprestimo) throws SQLException {

        String sql = "SELECT emprestimo.idEmprestimo, livros.nome, clientes.nome, emprestimo.dataEmprestimo, emprestimo.dataDevolucao \n"
                + "FROM emprestimo INNER JOIN livros on emprestimo.idLivro = livros.idLivro \n"
                + "INNER JOIN clientes on emprestimo.idCliente = clientes.idCliente \n"
                + "WHERE emprestimo.idEmprestimo = ?";
        stm = con.prepareStatement(sql);
        stm.setInt(1, locEmprestimo);
        //stm.setString(2, locEmprestimo);
        //stm.setString(3, locEmprestimo);
        rs = stm.executeQuery();
        Emprestimo emprestimo = null;

        if (rs.next()) {
            emprestimo = new Emprestimo(rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getDate(4),
                    rs.getString(5));
        }
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

        if (rs.next()) {
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
