/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DAO;

import JDBC.ConnectionFactory;
import funcoes.CDate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.bean.Imposto;

/**
 *
 * @author User
 */
public class ImpostoDAO {
    private Connection con = null;

    public ImpostoDAO() {
        con = ConnectionFactory.getConnection();
    }
    public void reconectar(){
        con = ConnectionFactory.getConnection();
    }
    public boolean save(Imposto imposto){
        String sql = "INSERT INTO impostos (nome,vencimento,valor) VALUES (?,?,?)";
        PreparedStatement stmt = null;
        CDate conv= new CDate();
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, imposto.getDescricao());
            stmt.setString(2, conv.DataPTBRtoDataMySQL(imposto.getVencimento()));
            stmt.setDouble(3, imposto.getValor());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("ERRO: " + ex);
            return false;
        }finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public boolean pago(Imposto imposto) { 
        String sql = "UPDATE impostos SET pago = ? WHERE seq = ?";
        PreparedStatement stmt = null;
        CDate conv = new CDate();
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1,conv.DataPTBRtoDataMySQL(imposto.getPago()));
            stmt.setInt(2, imposto.getSeq());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("ERRO: " + ex);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public boolean delete(Imposto imposto) {
        String sql = "DELETE FROM impostos WHERE seq = ?";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(2, imposto.getSeq());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("ERRO: " + ex);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public List<Imposto> findAll() {
        String sql = "SELECT * FROM impostos";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Imposto> impostos = new ArrayList<>();
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            while(rs.next()){
                Imposto imposto = new Imposto();
                imposto.setDescricao(rs.getString("nome"));
                imposto.setVencimento(rs.getString("vencimento"));
                imposto.setValor(rs.getDouble("valor"));
                imposto.setSeq(rs.getInt("seq"));
                imposto.setPago(rs.getString("pago"));
                impostos.add(imposto);
            }
        } catch (SQLException ex) {
            System.err.println("ERRO: " + ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return impostos;
    }
    
     public double findAllAberto() {
        String sql = "SELECT valor FROM impostos WHERE pago is null";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        double valor=0;
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            while(rs.next()){
                valor+=(rs.getDouble("valor"));
            }
        } catch (SQLException ex) {
            System.err.println("ERRO: " + ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return valor;
    }
}
