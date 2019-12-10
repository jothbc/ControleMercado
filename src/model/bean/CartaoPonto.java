/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author User
 */
public class CartaoPonto {

    private Funcionario funcionario;
    private DefaultTableModel tabela;
    private int dias;
    private int mes;
    private int ano;
    private String extra;
    private String extra_100;
    private String falta;
    private String falta_dsr;
    private String noturna;
    private double reducao;
    private String jornada;
    private int reg_sub;
    private int reg;

    public CartaoPonto() {
    }

    public CartaoPonto(Funcionario funcionario, DefaultTableModel tabela) {
        this.funcionario = funcionario;
        this.tabela = tabela;
        this.dias = tabela.getRowCount();
    }

    /**
     * @return the funcionario
     */
    public Funcionario getFuncionario() {
        return funcionario;
    }

    /**
     * @param funcionario the funcionario to set
     */
    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    /**
     * @return the tabela
     */
    public DefaultTableModel getTabela() {
        return tabela;
    }

    /**
     * @param tabela the tabela to set
     */
    public void setTabela(DefaultTableModel tabela) {
        this.tabela = tabela;
        this.setDias(tabela.getRowCount());
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public void setFalta(String falta) {
        this.falta = falta;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public void setNoturna(String noturna) {
        this.noturna = noturna;
    }

    public int getAno() {
        return ano;
    }

    public int getDias() {
        return dias;
    }

    public String getExtra() {
        return extra;
    }

    public String getFalta() {
        return falta;
    }

    public int getMes() {
        return mes;
    }

    public String getNoturna() {
        return noturna;
    }

    public double getReducao() {
        return reducao;
    }

    public void setReducao(double reducao) {
        this.reducao = reducao;
    }

    public String getJornada() {
        return jornada;
    }

    public void setJornada(String jornada) {
        this.jornada = jornada;
    }

    public int getReg() {
        return reg;
    }

    public int getReg_sub() {
        return reg_sub;
    }

    public void setReg(int reg) {
        this.reg = reg;
    }

    public void setReg_sub(int reg_sub) {
        this.reg_sub = reg_sub;
    }

    /**
     * @return the extra_100
     */
    public String getExtra_100() {
        return extra_100;
    }

    /**
     * @param extra_100 the extra_100 to set
     */
    public void setExtra_100(String extra_100) {
        this.extra_100 = extra_100;
    }

    /**
     * @return the falta_dsr
     */
    public String getFalta_dsr() {
        return falta_dsr;
    }

    /**
     * @param falta_dsr the falta_dsr to set
     */
    public void setFalta_dsr(String falta_dsr) {
        this.falta_dsr = falta_dsr;
    }

}
