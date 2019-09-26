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
import model.bean.Cheque;
import model.bean.Fornecedor;

/**
 *
 * @author User
 */
public class ChequeDAO {
    private Connection con = null;

    public ChequeDAO() {
        con = ConnectionFactory.getConnection();
    }
    public void reconect(){
        con = ConnectionFactory.getConnection();
    }
    
    public boolean save(Cheque cheque){
        String sql = "INSERT INTO cheques (seq,emissao,vencimento,fornecedor_id,valor) VALUES (?,?,?,?,?)";
        PreparedStatement stmt = null;
        String emissao_ = CDate.PTBRtoMYSQL(cheque.getEmissao());
        String predatado_ = CDate.PTBRtoMYSQL(cheque.getPredatado());
        try{
            stmt = con.prepareStatement(sql);
            stmt.setInt(1,cheque.getSeq());
            stmt.setString(2,emissao_);
            stmt.setString(3, predatado_);
            stmt.setInt(4, cheque.getFornecedor().getId());
            stmt.setDouble(5, cheque.getValor());
            stmt.executeUpdate();
            return true;
        }catch (SQLException ex){
            System.err.println("Erro ao salvar o cheque!");
            return false;
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    public boolean anular(Cheque cheque){
        String sql = "INSERT INTO cheques (seq,fornecedor_id) VALUES (?,?)";
        PreparedStatement stmt = null;
        try{
            stmt = con.prepareStatement(sql);
            stmt.setInt(1,cheque.getSeq());
            stmt.setInt(2, cheque.getFornecedor().getId());
            stmt.executeUpdate();
            return true;
        }catch (SQLException ex){
            System.err.println("Erro ao salvar o cheque!");
            return false;
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    public boolean pagar(Cheque cheque){
        String sql = "UPDATE cheques SET saque = ?  WHERE seq = ?";
        PreparedStatement stmt = null;
        String saque_ = CDate.PTBRtoMYSQL(cheque.getSaque());
        try{
            stmt = con.prepareStatement(sql);
            stmt.setString(1,saque_);
            stmt.setInt(2, cheque.getSeq());
            stmt.executeUpdate();
            return true;
        }catch (SQLException ex){
            System.err.println("Erro ao tentar salvar como 'pago' no banco de dados!");
            return false;
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    public List<Cheque> findAll(){
        String sql = "SELECT * FROM vw_fornecedor_cheque";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Cheque>cheques = new ArrayList<>();
        try{
            stmt=con.prepareStatement(sql);
            rs = stmt.executeQuery();
            while(rs.next()){
                Cheque ch = new Cheque();
                Fornecedor forn = new Fornecedor();
                forn.setId(rs.getInt("fornecedor_id"));
                forn.setNome(rs.getString("nome"));
                ch.setSeq(rs.getInt("seq"));
                ch.setEmissao(rs.getString("emissao"));
                ch.setPredatado(rs.getString("vencimento"));
                ch.setFornecedor(forn);
                ch.setValor(rs.getDouble("valor"));
                ch.setSaque(rs.getString("saque"));
                cheques.add(ch);
            }
        }catch (SQLException ex){
            System.err.println("Erro ao tentar fazer a busca no banco de dados!");
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return cheques;
    }
    public boolean update(Cheque cheque){
        String sql = "UPDATE cheques SET emissao = ?,vencimento = ?,fornecedor_id = ?, valor = ? WHERE seq = ?";
        PreparedStatement stmt = null;
        String emissao_ = CDate.PTBRtoMYSQL(cheque.getEmissao());
        String predatado_ = CDate.PTBRtoMYSQL(cheque.getPredatado());
        try{
            stmt = con.prepareStatement(sql);
            stmt.setString(1,emissao_);
            stmt.setString(2, predatado_);
            stmt.setInt(3, cheque.getFornecedor().getId());
            stmt.setDouble(4, cheque.getValor());
            stmt.setInt(5, cheque.getSeq());
            stmt.executeUpdate();
            return true;
        }catch (SQLException ex){
            System.err.println("Erro ao salvar o cheque!");
            return false;
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public double getValorEmAberto(){
        String sql = "SELECT valor FROM cheques where saque is null and valor != 0";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        double valor = 0;
        try{
            stmt=con.prepareStatement(sql);
            rs = stmt.executeQuery();
            while(rs.next()){
                valor +=(rs.getDouble("valor"));
            }
        }catch (SQLException ex){
            System.err.println("Erro ao tentar fazer a busca no banco de dados!");
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return valor;
    }
}
