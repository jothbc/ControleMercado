/*
 * Autor: Jonathan Comin Ribeiro
 */
package model.DAO;

import JDBC.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.bean.Fornecedor;
import model.bean.Pendencia;

/**
 *
 * @author User
 */
public class PendenciaDAO {

    Connection con = null;

    public PendenciaDAO() {
        con = ConnectionFactory.getConnection();
    }

    public boolean addPendencia(Pendencia p) {
        String sql = "INSERT INTO pendencia (situacao,fornecedor,motivo,obs,valor) VALUES (?,?,?,?,?)";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setBoolean(1, false);
            stmt.setString(2, p.getFornecedor().getNome());
            stmt.setString(3, p.getMotivo());
            stmt.setString(4, p.getObs());
            stmt.setDouble(5, p.getValor());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PendenciaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public boolean responder(int id,String resposta){
        String sql = "UPDATE pendencia SET resposta = ? WHERE id = ?";
        PreparedStatement stmt = null;
        try {
            stmt= con.prepareStatement(sql);
            stmt.setString(1, resposta);
            stmt.setInt(2, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PendenciaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public boolean setSituacao(boolean situacao,int id){
        String sql = "UPDATE pendencia SET situacao = ? WHERE id = ?";
        PreparedStatement stmt = null;
        try {
            stmt= con.prepareStatement(sql);
            stmt.setBoolean(1, situacao);
            stmt.setInt(2, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PendenciaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    public boolean setVisto(boolean situacao,int id){
        String sql = "UPDATE pendencia SET visto = ? WHERE id = ?";
        PreparedStatement stmt = null;
        try {
            stmt= con.prepareStatement(sql);
            stmt.setBoolean(1, situacao);
            stmt.setInt(2, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PendenciaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public List<Pendencia> findAll() {
        List<Pendencia> pendencias = new ArrayList<>();
        String sql = "SELECT * FROM pendencia";
        ResultSet rs = null;
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()){
                Pendencia p = new Pendencia();
                p.setId(rs.getInt("id"));
                p.setSituacao(rs.getBoolean("situacao"));
                p.setMotivo(rs.getString("motivo"));
                p.setValor(rs.getDouble("valor"));
                p.setObs(rs.getString("obs"));
                p.setResposta(rs.getString("resposta"));
                p.setVisto(rs.getBoolean("visto"));
                Fornecedor f = new Fornecedor();
                f.setNome(rs.getString("fornecedor"));
                p.setFornecedor(f);
                pendencias.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PendenciaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return pendencias;
    }

    public boolean deletar(int id) {
        String sql = "DELETE FROM pendencia WHERE id = ?";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PendenciaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

}
