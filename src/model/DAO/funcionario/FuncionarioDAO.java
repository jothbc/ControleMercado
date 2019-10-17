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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.bean.Funcionario;

/**
 *
 * @author User
 */
public class FuncionarioDAO {

    Connection con = null;

    public FuncionarioDAO() {
        con = ConnectionFactory.getConnection();
    }

    public boolean save(Funcionario funcionario) {
        String sql = "INSERT INTO funcionarios(codigo,nome,cpf,pis,cargo,admissao,ativo, salario) VALUES (?,?,?,?,?,?,?,?)";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, funcionario.getCodigo());
            stmt.setString(2, funcionario.getNome());
            stmt.setString(3, funcionario.getCpf());
            stmt.setString(4, funcionario.getPis());
            stmt.setString(5, funcionario.getCargo());
            stmt.setInt(7, 1);
            stmt.setDouble(8, funcionario.getSalario());
            if (funcionario.getAdmissao() != null) {
                stmt.setString(6, funcionario.getAdmissao("SQL"));
            } else {
                stmt.setString(6, null);
            }
            stmt.executeUpdate();
            return true;
        } catch (Exception ex) {
            if (ex.getMessage().contains("Duplicate entry")) {
                JOptionPane.showMessageDialog(null, "Código já informado em outro funcionário cadastrado.\nVerifique se digitou o código correto.");
            } else {
                JOptionPane.showMessageDialog(null, "ERRO: " + ex.getMessage());
            }
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public boolean remove(int codigo) {
        String sql = "DELETE FROM funcionarios WHERE codigo = ?";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, codigo);
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public boolean inativar(int codigo) {
        String sql = "UPDATE funcionarios SET ativo = 0 WHERE codigo = ?";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, codigo);
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public boolean ativar(int codigo) {
        String sql = "UPDATE funcionarios SET ativo = 1 WHERE codigo = ?";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, codigo);
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public List<Funcionario> findAll() {
        String sql = "SELECT * FROM funcionarios ORDER BY codigo";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Funcionario> array_retorno = new ArrayList<>();
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setNome(rs.getString("nome"));
                funcionario.setCodigo(rs.getInt("codigo"));
                if (rs.getString("cpf") != null) {
                    funcionario.setCpf(rs.getString("cpf"));
                }
                if (rs.getString("pis") != null) {
                    funcionario.setPis(rs.getString("pis"));
                }
                if (rs.getString("cargo") != null) {
                    funcionario.setCargo(rs.getString("cargo"));
                }
                if (rs.getString("admissao") != null) {
                    funcionario.setAdmissao(rs.getString("admissao"), "SQL");
                }
                funcionario.setAtivo(rs.getInt("ativo"));
                funcionario.setSalario(rs.getDouble("salario"));
                array_retorno.add(funcionario);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return array_retorno;
    }

    public boolean atualizar(Funcionario funcionario) {
        return atualizarFuncionario_tabelaFuncionarios(funcionario)
                && atualizarFuncionario_tabelaCartao(funcionario)
                && atualizarFuncionario_tabelaCartaoSub(funcionario);
    }

    public Funcionario getFuncionario(int i) {
        String sql = "SELECT * FROM funcionarios WHERE codigo = ?";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, i);
            rs = stmt.executeQuery();
            rs.first();
            Funcionario funcionario = new Funcionario();
            funcionario.setNome(rs.getString("nome"));
            funcionario.setCodigo(rs.getInt("codigo"));
            if (rs.getString("cpf") != null) {
                funcionario.setCpf(rs.getString("cpf"));
            }
            if (rs.getString("pis") != null) {
                funcionario.setPis(rs.getString("pis"));
            }
            if (rs.getString("cargo") != null) {
                funcionario.setCargo(rs.getString("cargo"));
            }
            if (rs.getString("admissao") != null) {
                try {
                    funcionario.setAdmissao(rs.getString("admissao"), "SQL");
                } catch (Exception ex) {
                    Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            funcionario.setAtivo(rs.getInt("ativo"));
            funcionario.setSalario(rs.getDouble("salario"));
            return funcionario;
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

    private boolean atualizarFuncionario_tabelaFuncionarios(Funcionario funcionario) {
        String sql = "UPDATE funcionarios SET nome=?, cpf=?, pis=?, cargo=?, admissao=?, salario = ? WHERE codigo = ?";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getCpf());
            stmt.setString(3, funcionario.getPis());
            stmt.setString(4, funcionario.getCargo());
            if (funcionario.getAdmissao() != null) {
                stmt.setString(5, funcionario.getAdmissao("SQL"));
            } else {
                stmt.setString(5, null);
            }
            stmt.setDouble(6, funcionario.getSalario());
            stmt.setInt(7, funcionario.getCodigo());
            stmt.executeUpdate();
            return true;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "ERRO: " + ex.getMessage());
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    private boolean atualizarFuncionario_tabelaCartao(Funcionario funcionario) {
        String sql = "UPDATE cartao_ponto SET nome = ? WHERE codigo = ?";
        PreparedStatement stmt = null;
        con = ConnectionFactory.getConnection();
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, funcionario.getNome());
            stmt.setInt(2, funcionario.getCodigo());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    private boolean atualizarFuncionario_tabelaCartaoSub(Funcionario funcionario) {
        String sql = "UPDATE cartao_ponto_sub SET nome = ? WHERE codigo = ?";
        PreparedStatement stmt = null;
        con = ConnectionFactory.getConnection();
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, funcionario.getNome());
            stmt.setInt(2, funcionario.getCodigo());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
}
