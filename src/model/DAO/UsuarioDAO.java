/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DAO;

import JDBC.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class UsuarioDAO {
    private Connection con = null;

    public UsuarioDAO() {
        con = ConnectionFactory.getConnection();
    }
    public boolean addUser(String user,int senha){
        String sql = "INSERT INTO usuarios (id,senha) VALUES (?,?)";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, user);
            stmt.setInt(2, senha);
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("ERRO: " + ex);
            return false;
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    public boolean checkUser(String user,int senha){
        String sql = "SELECT * FROM usuarios";
        PreparedStatement stmt = null;
        ResultSet rs= null;
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()){
                if (rs.getString("id").equals(user) && rs.getInt("senha")==senha){
                    return true;
                }
            }
            return false;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    
}
