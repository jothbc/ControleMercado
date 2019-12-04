/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DAO.funcionario;

import JDBC.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.bean.CartaoPonto;
import model.bean.Funcionario;

/**
 *
 * @author User
 */
public class CartaoPontoDAO {

    Connection con = null;

    public CartaoPontoDAO() {
        con = ConnectionFactory.getConnection();
    }

    public int nextReg() throws Exception {
        String sql = "select max(reg) as reg from cartao_ponto";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            rs.first();
            return rs.getInt("reg") + 1;
        } catch (SQLException ex) {
            Logger.getLogger(CartaoPontoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        throw new Exception("Não foi possível retornar um número de registro");
    }

    public boolean salvar(CartaoPonto cartao, int registro, List<String[]> list) {
        int mes, ano;
        mes = cartao.getMes();
        ano = cartao.getAno();
        String sql = "INSERT INTO cartao_ponto"
                + "(codigo,nome,dia,mes,ano,situacao,entrada,saida_intervalo,entrada_intervalo,saida,entrada2,saida2,horas_trabalhadas,reg)"
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            int count_dia = 1;
            for (String[] s : list) {
                stmt.setInt(1, cartao.getFuncionario().getCodigo()); //codigo
                stmt.setString(2, cartao.getFuncionario().getNome()); //nome
                stmt.setInt(3, count_dia++); //dia
                stmt.setInt(4, mes); //mes
                stmt.setInt(5, ano); //ano
                if (s[0].equals("S") //situação
                        || s[0].equals("D")
                        || s[0].equals("R")
                        || s[0].equals("H")
                        || s[0].equals("A")
                        || s[0].equals("F")) {
                    stmt.setString(6, s[0]);
                } else {
                    stmt.setString(6, "P");
                }
                //strings vindo com tipo null em String
                if (s[1] != null && !s[1].equals("null")) {
                    stmt.setString(7, s[1]); //entrada
                } else {
                    stmt.setNull(7, Types.TIME);
                }
                if (s[2] != null && !s[2].equals("null")) {
                    stmt.setString(8, s[2]); //saida_intervalo
                } else {
                    stmt.setNull(8, Types.TIME);
                }
                if (s[3] != null && !s[3].equals("null")) {
                    stmt.setString(9, s[3]); //entrada_intervalo
                } else {
                    stmt.setNull(9, Types.TIME);
                }
                if (s[4] != null && !s[4].equals("null")) {
                    stmt.setString(10, s[4]); //saida
                } else {
                    stmt.setNull(10, Types.TIME);
                }
                if (s[5] != null && !s[5].equals("null")) {
                    stmt.setString(11, s[5]); //entrada2
                } else {
                    stmt.setNull(11, Types.TIME);
                }
                if (s[6] != null && !s[6].equals("null")) {
                    stmt.setString(12, s[6]); //saida2
                } else {
                    stmt.setNull(12, Types.TIME);
                }
                if (s[7] != null && !s[7].equals("null")) {
                    stmt.setString(13, s[7]); //horas_trabalhadas
                } else {
                    stmt.setNull(13, Types.TIME);
                }
                stmt.setInt(14, registro); //reg
                stmt.execute();
            }
            sql = "INSERT INTO cartao_ponto_sub"
                    + "(codigo,nome,mes,ano,hora_extra,hora_falta,hora_noturna,reducao_noturna,jornada,reg)"
                    + "VALUES (?,?,?,?,?,?,?,?,?,?)";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, cartao.getFuncionario().getCodigo()); //codigo
            stmt.setString(2, cartao.getFuncionario().getNome()); //nome
            stmt.setInt(3, mes); //mes
            stmt.setInt(4, ano); //ano
            stmt.setString(5, cartao.getExtra()); //hora_extra
            stmt.setString(6, cartao.getFalta()); //hora_falta
            stmt.setString(7, cartao.getNoturna()); //hora_noturna
            stmt.setDouble(8, cartao.getReducao()); //reducao_noturna
            stmt.setString(9, cartao.getJornada()); //jornada
            stmt.setInt(10, registro); //reg
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CartaoPontoDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex, "ERRO AO SALVAR O CARTAO", JOptionPane.ERROR_MESSAGE);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public int lancado(Funcionario fun, int mes, int ano) throws Exception {
        String sql = "SELECT * FROM cartao_ponto_sub where mes = ? and ano = ? and codigo = ?";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, mes);
            stmt.setInt(2, ano);
            stmt.setInt(3, fun.getCodigo());
            rs = stmt.executeQuery();
            while (rs.next()) {
                return rs.getInt("reg");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CartaoPontoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        throw new Exception("Não tem cartão lançado esse mês. (" + mes + ")");
    }

    public CartaoPonto getLancado(int reg, JTable table) {
        CartaoPonto cartao = new CartaoPonto();
        String sql = "SELECT * FROM cartao_ponto_sub WHERE reg = ?";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, reg);
            rs = stmt.executeQuery();
            rs.first();

            cartao.setFuncionario(new Funcionario(rs.getString("nome"), rs.getInt("codigo")));
            cartao.setAno(rs.getInt("ano"));
            cartao.setMes(rs.getInt("mes"));
            cartao.setExtra(rs.getString("hora_extra"));
            cartao.setFalta(rs.getString("hora_falta"));
            cartao.setNoturna(rs.getString("hora_noturna"));
            cartao.setReducao(rs.getDouble("reducao_noturna"));
            cartao.setJornada(rs.getString("jornada"));

            cartao.setReg_sub(reg);
            cartao.setReg(reg);

            sql = "SELECT * FROM cartao_ponto WHERE reg = ?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, reg);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int index = rs.getInt("dia") - 1;
                String situacao = null, entrada = null, sintervalo = null, eintervalo = null, saida = null, e2 = null, s2 = null, ht = null;
                if (rs.getString("situacao").equals("P")) {
                    situacao = Integer.toString(rs.getInt("dia"));
                } else {
                    situacao = rs.getString("situacao");
                }
                if (rs.getString("entrada") != null) {
                    entrada = rs.getString("entrada");
                    entrada = entrada.substring(0, 5);
                }
                if (rs.getString("saida_intervalo") != null) {
                    sintervalo = rs.getString("saida_intervalo");
                    sintervalo = sintervalo.substring(0, 5);
                }
                if (rs.getString("entrada_intervalo") != null) {
                    eintervalo = rs.getString("entrada_intervalo");
                    eintervalo = eintervalo.substring(0, 5);
                }
                if (rs.getString("saida") != null) {
                    saida = rs.getString("saida");
                    saida = saida.substring(0, 5);
                }
                if (rs.getString("entrada2") != null) {
                    e2 = rs.getString("entrada2");
                    e2 = e2.substring(0, 5);
                }
                if (rs.getString("saida2") != null) {
                    s2 = rs.getString("saida2");
                    s2 = s2.substring(0, 5);
                }
                if (rs.getString("horas_trabalhadas") != null) {
                    ht = rs.getString("horas_trabalhadas");
                    ht = ht.substring(0, 5);
                }
                table.setValueAt(situacao, index, 0);
                table.setValueAt(entrada, index, 1);
                table.setValueAt(sintervalo, index, 2);
                table.setValueAt(eintervalo, index, 3);
                table.setValueAt(saida, index, 4);
                table.setValueAt(e2, index, 5);
                table.setValueAt(s2, index, 6);
                table.setValueAt(ht, index, 7);
            }
            cartao.setTabela((DefaultTableModel) table.getModel());
            return cartao;
        } catch (SQLException ex) {
            Logger.getLogger(CartaoPontoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return cartao;
    }

    public boolean atualizar(CartaoPonto cartao, List<String[]> list) {
        int mes, ano;
        mes = cartao.getMes();
        ano = cartao.getAno();
        String sql = "UPDATE cartao_ponto "
                + "SET codigo = ?,"
                + "nome= ?,"
                + "situacao=?,"
                + "entrada=?,"
                + "saida_intervalo=?,"
                + "entrada_intervalo=?,"
                + "saida=?,"
                + "entrada2=?,"
                + "saida2=?,"
                + "horas_trabalhadas=? "
                + "WHERE reg = ? AND "
                + "dia = ? AND "
                + "mes = ? AND "
                + "ano = ?";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            int count_dia = 1;
            for (String[] s : list) {
                stmt.setInt(1, cartao.getFuncionario().getCodigo()); //codigo
                stmt.setString(2, cartao.getFuncionario().getNome()); //nome
                if (s[0].equals("S") //situação
                        || s[0].equals("D")
                        || s[0].equals("R")
                        || s[0].equals("H")
                        || s[0].equals("A")
                        || s[0].equals("F")) {
                    stmt.setString(3, s[0]);
                } else {
                    stmt.setString(3, "P");
                }
                //strings vindo com tipo null em String
                if (s[1] != null && !s[1].equals("null")) {
                    stmt.setString(4, s[1]); //entrada
                } else {
                    stmt.setNull(4, Types.TIME);
                }
                if (s[2] != null && !s[2].equals("null")) {
                    stmt.setString(5, s[2]); //saida_intervalo
                } else {
                    stmt.setNull(5, Types.TIME);
                }
                if (s[3] != null && !s[3].equals("null")) {
                    stmt.setString(6, s[3]); //entrada_intervalo
                } else {
                    stmt.setNull(6, Types.TIME);
                }
                if (s[4] != null && !s[4].equals("null")) {
                    stmt.setString(7, s[4]); //saida
                } else {
                    stmt.setNull(7, Types.TIME);
                }
                if (s[5] != null && !s[5].equals("null")) {
                    stmt.setString(8, s[5]); //entrada2
                } else {
                    stmt.setNull(8, Types.TIME);
                }
                if (s[6] != null && !s[6].equals("null")) {
                    stmt.setString(9, s[6]); //saida2
                } else {
                    stmt.setNull(9, Types.TIME);
                }
                if (s[7] != null && !s[7].equals("null")) {
                    stmt.setString(10, s[7]); //horas_trabalhadas
                } else {
                    stmt.setNull(10, Types.TIME);
                }
                stmt.setInt(11, cartao.getReg()); //reg
                stmt.setInt(12, count_dia++); //dia
                stmt.setInt(13, mes); //mes
                stmt.setInt(14, ano); //ano
                stmt.executeUpdate();
            }
            sql = "UPDATE cartao_ponto_sub "
                    + "SET codigo=?,"
                    + "nome=?,"
                    + "mes=?,"
                    + "ano=?,"
                    + "hora_extra=?,"
                    + "hora_falta=?,"
                    + "hora_noturna=?,"
                    + "reducao_noturna=?,"
                    + "jornada=? "
                    + "WHERE reg = ?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, cartao.getFuncionario().getCodigo()); //codigo
            stmt.setString(2, cartao.getFuncionario().getNome()); //nome
            stmt.setInt(3, mes); //mes
            stmt.setInt(4, ano); //ano
            stmt.setString(5, cartao.getExtra()); //hora_extra
            stmt.setString(6, cartao.getFalta()); //hora_falta
            stmt.setString(7, cartao.getNoturna()); //hora_noturna
            stmt.setDouble(8, cartao.getReducao()); //reducao_noturna
            stmt.setString(9, cartao.getJornada()); //jornada
            stmt.setInt(10, cartao.getReg_sub()); //reg
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CartaoPontoDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex, "ERRO AO ATUALIZAR CARTAO", JOptionPane.WARNING_MESSAGE);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public boolean removerAlteracoes(CartaoPonto cartao) {
        String sql = "DELETE FROM cartao_ponto WHERE codigo = ? and mes = ? and ano = ?";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, cartao.getFuncionario().getCodigo());
            stmt.setInt(2, cartao.getMes());
            stmt.setInt(3, cartao.getAno());
            stmt.execute();
            sql = "DELETE FROM cartao_ponto_sub WHERE codigo = ? and mes = ? and ano = ?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, cartao.getFuncionario().getCodigo());
            stmt.setInt(2, cartao.getMes());
            stmt.setInt(3, cartao.getAno());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CartaoPontoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public boolean folhaPago(int mes, int ano) {
        String sql = "SELECT * FROM controle_folha WHERE mes = ? and ano = ?";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, mes);
            stmt.setInt(2, ano);
            rs = stmt.executeQuery();
            while (rs.next()) {
                if (rs.getBoolean("pago")) {
                    return true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CartaoPontoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return false;
    }

    public boolean alterarStatusFolhaPago(boolean status, int mes, int ano) {
        String sql = "UPDATE controle_folha SET pago = ? WHERE mes = ? and ano = ?";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setBoolean(1, status);
            stmt.setInt(2, mes);
            stmt.setInt(3, ano);
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CartaoPontoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        return false;
    }

}
