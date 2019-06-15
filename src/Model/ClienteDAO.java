/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import framework.DaoBasicoConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author aluno
 */
public class ClienteDAO {

    private Connection con = null;
    private PreparedStatement stm, key;
    private ResultSet rs;
    private DaoBasicoConexao dao;
    /*
     * Variável usada para retornar o status da conexão:
     *  true = sucesso e false = erro.
     */
    private boolean conStatus = false;

    public ClienteDAO() {
        dao = new DaoBasicoConexao();
        try {
            con = dao.getConexao();
            this.setConStatus(true);
        } catch (SQLException e) {
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

    public int Inserir(Cliente cliente) throws SQLException {

        String sql = "INSERT INTO clientes (nome, telefone, rg, dataNascimento) values (?,?,?,?)";

        stm = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        stm.setString(1, cliente.getNome());
        stm.setString(2, cliente.getTelefone());
        stm.setString(3, cliente.getRg());
        stm.setString(4, cliente.getDataNascimento());

        stm.executeUpdate();

        rs = stm.getGeneratedKeys();

        //atualiza o atributo codigo (autoincremento) do objeto instanciado
        if (rs.next()) {
            cliente.setId(rs.getInt(1));
        }

        return cliente.getId();
    }

    public void Alterar(Cliente cliente) throws SQLException {
        String sql = "UPDATE clientes set nome = ?, telefone = ?, rg = ?, dataNascimento = ? WHERE idCliente = ?";

        stm = con.prepareStatement(sql);
        System.out.println(sql);
        stm.setString(1, cliente.getNome());
        stm.setString(2, cliente.getTelefone());
        stm.setString(3, cliente.getRg());
        stm.setString(4, cliente.getDataNascimento());
        stm.setInt(5, cliente.getId());
        stm.executeUpdate();

    }

    public void Excluir(Cliente cliente) throws SQLException {
        String sql = "DELETE FROM clientes WHERE idCliente = ?";

        stm = con.prepareStatement(sql);
        stm.setInt(1, cliente.getId());

        stm.executeUpdate();

    }

    public Cliente localizarCliente(int locCliente) throws SQLException {
        String sql = "SELECT * FROM clientes WHERE idCliente = ? ";
        stm = con.prepareStatement(sql);
        stm.setInt(1, locCliente);
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

    public ArrayList<Cliente> ListaClientes() throws SQLException {
        ArrayList<Cliente> clientes = new ArrayList<Cliente>();
        String sql = "SELECT * FROM clientes";
        stm = con.prepareStatement(sql);
        rs = stm.executeQuery();

        while (rs.next()) {
            clientes.add(new Cliente(rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5)));
        }
        return clientes;
    }
}
