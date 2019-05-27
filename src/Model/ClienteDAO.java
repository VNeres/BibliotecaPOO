/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class ClienteDAO {
    private Connection con = null;
    private PreparedStatement stm;
    private ResultSet rs;
    private DaoBasicoConexao dao;
    
    public ClienteDAO()  {
        dao = new DaoBasicoConexao();
        try{
            con = dao.getConexao();
        }
        catch (SQLException e){
            System.out.println(e);
        }
    }
    
    public int Inserir (Cliente cliente) throws SQLException{
        String sql = "INSERT INTO VINHO (nome, telefone, RG, Data Nascimento) values (?,?,?,?)";
        
        stm = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        stm.setString(1, cliente.getNome());
        stm.setString(2, cliente.getTelefone());
        stm.setString(3, cliente.getRg());
        stm.setDate(4, (Date) cliente.getDataNascimento());
        
        stm.executeUpdate();
        
        rs = stm.getGeneratedKeys();
        
        //atualiza o atributo codigo (autoincremento) do objeto instanciado
        if (rs.next()){
            cliente.setId(rs.getInt(1));
        }
        
        return cliente.getId();
    }
    
    public void Alterar(Cliente cliente) throws SQLException{
        String sql = "UPDATE VINHO set nome = ?, telefone = ?, rg = ?, data nascimento = ? WHERE id = ?";
        
        stm = con.prepareStatement(sql);
        stm.setString(1, cliente.getNome());
        stm.setString(2, cliente.getTelefone());
        stm.setString(3, cliente.getRg());
        stm.setDate(4, (Date) cliente.getDataNascimento());
        stm.setInt(5, cliente.getId());
        
        stm.executeUpdate();   
    }
    
    public void Excluir (Cliente cliente) throws SQLException{
        
        String sql = "DELETE FROM Cliente WHERE id = ?";
        
        stm = con.prepareStatement(sql);
        stm.setInt(1, cliente.getId());
        
         stm.executeUpdate();
    }
    
    public Cliente localizarCliente(String locCliente) throws SQLException{
        
        String sql = "SELECT * FROM Cliente WHERE nome = ? ";
        stm = con.prepareStatement(sql);
        stm.setString(1, locCliente);
        
        rs = stm.executeQuery();
        Cliente cliente = new Cliente(rs.getInt(1),
                                rs.getString(2),
                                rs.getString(3),
                                rs.getString(4), 
                                rs.getDate(5));
        return cliente;
    }
    
    public ArrayList<Cliente> ListaClientes() throws SQLException{
        
        ArrayList<Cliente> clientes = new ArrayList<Cliente>();
        
        String sql = "SELECT * FROM Cliente";
        
        stm = con.prepareStatement(sql);
        rs = stm.executeQuery();
        
        while(rs.next()){
            clientes.add(new Cliente(rs.getInt(1),
                                rs.getString(2),
                                rs.getString(3),
                                rs.getString(4), 
                                rs.getDate(5)));
        }
        return clientes;
    }
}
