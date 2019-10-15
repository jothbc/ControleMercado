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
import model.bean.FeriadosBrasil;

/**
 *
 * @author User
 */
public class FeriadosBrasilDAO{
    Connection con = null;
    PreparedStatement stmt;
    ResultSet rs;
    String sql;

    public FeriadosBrasilDAO() {
        con = ConnectionFactory.getConnection();
    }
    
    public boolean addData(int dia,int mes,int ano,String desc){
        sql = "INSERT INTO log_feriados(dia,mes,ano,descricao) VALUES (?,?,?,?)";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, dia);
            stmt.setInt(2, mes);
            stmt.setInt(3, ano);
            stmt.setString(4, desc);
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(FeriadosBrasilDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public List<FeriadosBrasil> getFeriadosDoMes(int mes,int ano){
        List<FeriadosBrasil> feriados = new ArrayList<>();
        sql = "SELECT * FROM log_feriados WHERE mes = ? and ano = ?";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, mes);
            stmt.setInt(2, ano);
            rs = stmt.executeQuery();
            while(rs.next()){
                FeriadosBrasil f = new FeriadosBrasil();
                f.setId(rs.getInt("id"));
                f.setDia(rs.getInt("dia"));
                f.setMes(mes);
                f.setAno(ano);
                f.setDescricao(rs.getString("descricao"));
                feriados.add(f);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(FeriadosBrasilDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return feriados;
    }
    
    public List<FeriadosBrasil> findAll(){
        List<FeriadosBrasil> feriados = new ArrayList<>();
        sql = "SELECT * FROM log_feriados ORDER BY ano,mes,dia";
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            while(rs.next()){
                FeriadosBrasil f = new FeriadosBrasil();
                f.setId(rs.getInt("id"));
                f.setDia(rs.getInt("dia"));
                f.setMes(rs.getInt("mes"));
                f.setAno(rs.getInt("ano"));
                f.setDescricao(rs.getString("descricao"));
                feriados.add(f);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(FeriadosBrasilDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return feriados;
    }
    
    public boolean removeData(int id){
        sql = "DELETE FROM log_feriados WHERE id = ?";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(FeriadosBrasilDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
   
}
