/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framework;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author aluno
 */
public class DaoBasicoConexao {
    
    //Dados para a Conexão
    //nome do driver fornecido pela documentação do fabricante
    private String driver = "com.mysql.jdbc.Driver";
    
    /*
     * Observe que foi retirada a porta, devido ao banco funcionar diferente
     * em casa, porém, caso houver problemas, adicionar após o "localhost":
     * localhost:8080 ou localhost:3306
     * Will.
     */
    private String url = "jdbc:mysql://localhost/biblioteca";
    private String usuario = "root";
    private String senha = "";
    
    public Statement stmt;
    
    public Connection getConexao()throws SQLException{
        
        Connection con = null;
        
        try {
            Class.forName(driver);
            con = (Connection) DriverManager.getConnection(url, usuario, senha);
        }
        catch (ClassNotFoundException e){
            JOptionPane.showMessageDialog(null, "Problemas na conexão com a fonte de dados! " +e, "Banco de Dados", JOptionPane.ERROR_MESSAGE);
        }
        return con;
    }
    
    public void fecharConexao(Connection con, Statement stmt, ResultSet rs) throws SQLException{
        try{
            if (stmt != null){
                stmt.close();
            }
        }
        catch (SQLException e){
            
        }
        
        try{
            if (rs != null){
                rs.close();
            }
        }
        catch (SQLException e){
        }
        
        try{
            con.close();
        }
        catch (SQLException e){
            
        }
        System.out.println("Tudo desconectado!");
    }
    
}

