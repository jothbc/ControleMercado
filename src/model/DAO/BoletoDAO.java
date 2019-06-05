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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.bean.Boleto;
import model.bean.Fornecedor;

/**
 *
 * @author User
 */
public class BoletoDAO {

    private Connection con = null;

    public BoletoDAO() {
        con = ConnectionFactory.getConnection();
    }

    public void reconect() {
        con = ConnectionFactory.getConnection();
    }

    public String Cdate(String temp) {
        DateFormat f = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date data = null;
        try {
            data = (java.util.Date) f.parse(temp);
        } catch (ParseException ex) {
            System.err.println("Erro ao converter data :" + ex);
        }
        String temp2 = sdf.format(data);
        return temp2;
    }

    public boolean save(Boleto boleto) {
        String sql = "INSERT INTO boletos (fornecedor_id,cd_barras,valor,vencimento) VALUES (?,?,?,?)";
        PreparedStatement stmt = null;
        String datatemp = Cdate(boleto.getVencimento());
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, boleto.getFornecedor_id().getId());
            stmt.setString(2, boleto.getCd_barras());
            stmt.setDouble(3, boleto.getValor());
            stmt.setString(4, datatemp);
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("ERRO: " + ex);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    /*
    *   Funcao para lançar um boleto como pago definindo o dia em que foi pago.
    *
     */
    public boolean update(Boleto boleto) {
        String sql = "UPDATE boletos SET pago = ? WHERE seq = ?";
        PreparedStatement stmt = null;
        String datatemp = Cdate(boleto.getPago());
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, datatemp);
            stmt.setInt(2, boleto.getId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro ao salvar: " + ex);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public boolean atualizar(Boleto boleto) {
        String sql = "UPDATE boletos SET fornecedor_id = ?, cd_barras = ?, valor = ?, vencimento = ? WHERE seq = ?";
        PreparedStatement stmt = null;
        String datatemp = Cdate(boleto.getVencimento());
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, boleto.getFornecedor_id().getId());
            stmt.setString(2, boleto.getCd_barras());
            stmt.setDouble(3, boleto.getValor());
            stmt.setString(4, datatemp);
            stmt.setInt(5, boleto.getId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro ao salvar: " + ex);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public boolean delete(int seq) {
        String sql = "DELETE FROM boletos WHERE seq = ?";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, seq);
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro ao deletar :" + ex);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public List<Boleto> findAll() {
        String sql = "SELECT * FROM vw_fornecedor_boleto ORDER BY vencimento";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Boleto> boletos = new ArrayList<>();
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Boleto boleto = new Boleto();
                Fornecedor fornecedor = new Fornecedor();
                fornecedor.setId(rs.getInt("bfornecedor_id"));
                fornecedor.setNome(rs.getString("nome_fornecedor"));
                boleto.setId(rs.getInt("bseq"));
                boleto.setFornecedor_id(fornecedor);
                boleto.setCd_barras(rs.getString("bcd_barras"));
                boleto.setValor(rs.getDouble("valor"));
                boleto.setVencimento(rs.getString("vencimento"));
                boleto.setPago(rs.getString("pago"));
                boletos.add(boleto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BoletoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return boletos;
    }
    public double findAllAberto() {
        String sql = "SELECT valor FROM boletos WHERE pago is null ORDER BY vencimento";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        double valor = 0;
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                valor+=(rs.getDouble("valor"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BoletoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return valor;
    }

}
