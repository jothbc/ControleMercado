/*
 * Autor: Jonathan Comin Ribeiro
 */
package model.DAO;

import JDBC.ConnectionFactory;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.bean.VariavelFolha;

/**
 *
 * @author User
 */
public class VariavelFolhaDAO extends DAO {

    public boolean addVariavel(int code, String desc) {
        sql = "INSERT INTO variaveis_folha(?,?) VALUES (?,?)";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, code);
            stmt.setString(2, desc);
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(VariavelFolhaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public List<VariavelFolha> getVariaveis() {
        List<VariavelFolha> folhas = new ArrayList<>();
        sql = "SELECT * FROM variaveis_folha";
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                VariavelFolha v = new VariavelFolha();
                v.setId(rs.getInt("id"));
                v.setCodigo(rs.getInt("codigo"));
                v.setDescricao(rs.getString("descricao"));
                folhas.add(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(VariavelFolhaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return folhas;
    }
    
    
}
