/*
 * Autor: Jonathan Comin Ribeiro
 */
package model.DAO;

import JDBC.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author User
 */
public abstract class DAO {

    Connection con;
    PreparedStatement stmt;
    ResultSet rs;
    String sql;

    public DAO() {
        con = ConnectionFactory.getConnection();
    }

}
