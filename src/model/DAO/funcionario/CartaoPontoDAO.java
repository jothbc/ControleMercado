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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
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

    public boolean salvar(CartaoPonto cartao) {
        int dias, mes, ano;
        dias = cartao.getDias();
        mes = cartao.getMes();
        ano = cartao.getAno();

        String sql = "INSERT INTO cartao_ponto"
                + "(codigo,nome,dia,mes,ano,situacao,entrada,saida_intervalo,entrada_intervalo,saida,entrada2,saida2,horas_trabalhadas)"
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        String sql2 = "INSERT INTO cartao_ponto_sub"
                + "(codigo,nome,mes,ano,hora_extra,hora_falta,hora_noturna,reducao_noturna,jornada)"
                + "VALUES (?,?,?,?,?,?,?,?,?)";
        PreparedStatement stmt = null;
        try {
            for (int x = 0; x < dias; x++) {
                stmt = null;
                stmt = con.prepareStatement(sql);
                //defini funcionario
                stmt.setInt(1, cartao.getFuncionario().getCodigo());
                stmt.setString(2, cartao.getFuncionario().getNome());
                //define data
                stmt.setInt(3, x + 1);
                stmt.setInt(4, mes);
                stmt.setInt(5, ano);
                //defini situação (p = presença, d = domingo, f=falta... etc)
                if (cartao.getTabela().getValueAt(x, 0) == "S" || cartao.getTabela().getValueAt(x, 0) == "D" || cartao.getTabela().getValueAt(x, 0) == "R" || cartao.getTabela().getValueAt(x, 0) == "H" || cartao.getTabela().getValueAt(x, 0) == "A" || cartao.getTabela().getValueAt(x, 0) == "F") {
                    stmt.setString(6, (String) cartao.getTabela().getValueAt(x, 0));
                } else {
                    stmt.setString(6, "P");
                }
                //defini horas
                String e, si, ei, s, e1, s1, horas;
                e = (String) cartao.getTabela().getValueAt(x, 1);
                si = (String) cartao.getTabela().getValueAt(x, 2);
                ei = (String) cartao.getTabela().getValueAt(x, 3);
                s = (String) cartao.getTabela().getValueAt(x, 4);
                e1 = (String) cartao.getTabela().getValueAt(x, 5);
                s1 = (String) cartao.getTabela().getValueAt(x, 6);
                horas = (String) cartao.getTabela().getValueAt(x, 7);
                stmt.setString(7, e);
                stmt.setString(8, si);
                stmt.setString(9, ei);
                stmt.setString(10, s);
                stmt.setString(11, e1);
                stmt.setString(12, s1);
                stmt.setString(13, horas);
                stmt.executeUpdate();
            }
            stmt = null;
            stmt = con.prepareStatement(sql2);
            stmt.setInt(1, cartao.getFuncionario().getCodigo());
            stmt.setString(2, cartao.getFuncionario().getNome());
            stmt.setInt(3, mes);
            stmt.setInt(4, ano);
            stmt.setString(5, cartao.getExtra());
            stmt.setString(6, cartao.getFalta());
            stmt.setString(7, cartao.getNoturna());
            stmt.setDouble(8, cartao.getReducao());
            stmt.setString(9, cartao.getJornada());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CartaoPontoDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex, "ERRO", JOptionPane.ERROR_MESSAGE);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }

    public boolean lancado(Funcionario fun, int mes, int ano) {
        String sql = "SELECT * FROM cartao_ponto_sub";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                if (rs.getInt("codigo") == fun.getCodigo() && rs.getInt("ano") == ano && rs.getInt("mes") == mes) {
                    return true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CartaoPontoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return false;
    }

    public CartaoPonto getLancado(Funcionario fun, int mes, int ano) {
        CartaoPonto cartao = new CartaoPonto();
        cartao.setFuncionario(fun);
        cartao.setAno(ano);
        cartao.setMes(mes);
        String sql = "SELECT * FROM cartao_ponto_sub";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                if (rs.getInt("codigo") == fun.getCodigo() && rs.getInt("ano") == ano && rs.getInt("mes") == mes) {
                    cartao.setExtra(rs.getString("hora_extra"));
                    cartao.setFalta(rs.getString("hora_falta"));
                    cartao.setNoturna(rs.getString("hora_noturna"));
                    cartao.setReducao(rs.getDouble("reducao_noturna"));
                    cartao.setJornada(rs.getString("jornada"));
                    cartao.setReg_sub(rs.getInt("reg"));
                    break;
                }
            }
            stmt = null;
            rs = null;
            sql = "SELECT * FROM cartao_ponto";
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            boolean test = false, setreg = true;
            DefaultTableModel t = new DefaultTableModel();
            t.setColumnCount(8);
            while (rs.next()) {
                int diatemp = rs.getInt("dia");
                int mestemp = rs.getInt("mes");
                int anotemp = rs.getInt("ano");
                int codigofun = rs.getInt("codigo");
                if ((codigofun == fun.getCodigo() && diatemp == 1 && mestemp == mes && anotemp == ano) || test) {
                    if (mes != mestemp || ano != anotemp || codigofun != fun.getCodigo()) {
                        //System.out.println("mudou");
                        break;
                    } else {
                        test = true;
                        if (setreg) {
                            cartao.setReg(rs.getInt("reg"));
                            setreg = false;
                        }
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
                        Object[] dado = {situacao, entrada, sintervalo, eintervalo, saida, e2, s2, ht};
                        //System.out.println(situacao+"\t"+entrada+"\t"+sintervalo+"\t"+eintervalo+"\t"+saida+"\t"+e2+"\t"+s2+"\t"+ht);
                        t.addRow(dado);
                    }
                }
            }
            cartao.setTabela(t);
            return cartao;
        } catch (SQLException ex) {
            Logger.getLogger(CartaoPontoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return cartao;
    }

    public boolean atualizar(CartaoPonto cartao) {
        //só lança situação P, tem q ver por que nao ta lançando outras situações
        int dias, mes, ano;
        dias = cartao.getDias();
        mes = cartao.getMes();
        ano = cartao.getAno();

        String sql = "UPDATE cartao_ponto "
                + "SET codigo = ?,nome= ?,"
                + "dia=?,mes=?,ano=?,"
                + "situacao=?,"
                + "entrada=?,saida_intervalo=?,entrada_intervalo=?,saida=?,"
                + "entrada2=?,saida2=?,"
                + "horas_trabalhadas=? "
                + "WHERE reg = ?";
        String sql2 = "UPDATE cartao_ponto_sub "
                + "SET codigo=?,nome=?,"
                + "mes=?,ano=?,"
                + "hora_extra=?,hora_falta=?,hora_noturna=?,reducao_noturna=?,jornada=? "
                + "WHERE reg = ?";
        PreparedStatement stmt = null;
        try {
            for (int x = 0; x < dias; x++) {
                stmt = null;
                stmt = con.prepareStatement(sql);
                //defini funcionario
                stmt.setInt(1, cartao.getFuncionario().getCodigo());
                stmt.setString(2, cartao.getFuncionario().getNome());
                //define data
                stmt.setInt(3, x + 1);
                stmt.setInt(4, mes);
                stmt.setInt(5, ano);
                //defini situação (p = presença, d = domingo, f=falta... etc)
                //System.out.println(cartao.getTabela().getValueAt(x, 0));
                try {
                    if ("S".equals((String)cartao.getTabela().getValueAt(x, 0))
                            || "D".equals((String)cartao.getTabela().getValueAt(x, 0))
                            || "R".equals((String)cartao.getTabela().getValueAt(x, 0))
                            || "H".equals((String)cartao.getTabela().getValueAt(x, 0))
                            || "A".equals((String)cartao.getTabela().getValueAt(x, 0))
                            || "F".equals((String)cartao.getTabela().getValueAt(x, 0))) {
                        stmt.setString(6, (String) cartao.getTabela().getValueAt(x, 0));
                    }else{
                        stmt.setString(6, "P");
                    }
                } catch (NumberFormatException ex) {
                    stmt.setString(6, "P");
                }
                //defini horas
                String e, si, ei, s, e1, s1, horas;
                e = (String) cartao.getTabela().getValueAt(x, 1);
                si = (String) cartao.getTabela().getValueAt(x, 2);
                ei = (String) cartao.getTabela().getValueAt(x, 3);
                s = (String) cartao.getTabela().getValueAt(x, 4);
                e1 = (String) cartao.getTabela().getValueAt(x, 5);
                s1 = (String) cartao.getTabela().getValueAt(x, 6);
                horas = (String) cartao.getTabela().getValueAt(x, 7);
                stmt.setString(7, e);
                stmt.setString(8, si);
                stmt.setString(9, ei);
                stmt.setString(10, s);
                stmt.setString(11, e1);
                stmt.setString(12, s1);
                stmt.setString(13, horas);
                stmt.setInt(14, cartao.getReg() + x);
                //System.out.println("reg:"+cartao.getReg()+"\tdia: "+ (x+1));
                stmt.executeUpdate();
            }
            stmt = null;
            stmt = con.prepareStatement(sql2);
            stmt.setInt(1, cartao.getFuncionario().getCodigo());
            stmt.setString(2, cartao.getFuncionario().getNome());
            stmt.setInt(3, mes);
            stmt.setInt(4, ano);
            stmt.setString(5, cartao.getExtra());
            stmt.setString(6, cartao.getFalta());
            stmt.setString(7, cartao.getNoturna());
            stmt.setDouble(8, cartao.getReducao());
            stmt.setString(9, cartao.getJornada());
            stmt.setInt(10, cartao.getReg_sub());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CartaoPontoDAO.class.getName()).log(Level.SEVERE, null, ex);
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

}
