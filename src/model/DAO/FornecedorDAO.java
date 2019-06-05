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
import java.util.ArrayList;
import java.util.List;
import model.bean.Fornecedor;

/**
 *
 * @author User
 */
public class FornecedorDAO {
    private Connection con = null;

    public FornecedorDAO() {
        con = ConnectionFactory.getConnection();
    }
    public void reconectar(){
        con = ConnectionFactory.getConnection();
    }
    public boolean save(Fornecedor fornecedor){
        String sql = "INSERT INTO fornecedor (nome,banco,numero) VALUES (?,?,?)";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, fornecedor.getNome());
            stmt.setInt(2, fornecedor.getBanco());
            stmt.setString(3,fornecedor.getNumero());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("ERRO: " + ex);
            return false;
        }finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public boolean update(Fornecedor fornecedor) { 
        String sql = "UPDATE fornecedor SET nome= ?,banco = ?,numero = ? WHERE id = ?";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1,fornecedor.getNome());
            stmt.setInt(2, fornecedor.getBanco());
            stmt.setString(3,fornecedor.getNumero());
            stmt.setInt(4, fornecedor.getId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("ERRO: " + ex);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public boolean updateNumero(Fornecedor fornecedor) { 
        String sql = "UPDATE fornecedor SET numero = ? WHERE id = ?";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1,fornecedor.getNumero());
            stmt.setInt(2, fornecedor.getId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("ERRO: " + ex);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public boolean delete(Fornecedor fornecedor) {
        String sql = "DELETE FROM fornecedor WHERE id = ?";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(2, fornecedor.getId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("ERRO: " + ex);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public List<Fornecedor> findAll() {
        String sql = "SELECT * FROM fornecedor ORDER BY nome";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Fornecedor> fornecedores = new ArrayList<>();
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            while(rs.next()){
                Fornecedor fornecedor = new Fornecedor();
                fornecedor.setNome(rs.getString("nome"));
                fornecedor.setId(rs.getInt("id"));
                fornecedor.setBanco(rs.getInt("banco"));
                fornecedor.setNumero(rs.getString("numero"));
                fornecedores.add(fornecedor);
            }
        } catch (SQLException ex) {
            System.err.println("ERRO: " + ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return fornecedores;
    }
}
