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
    int dias;
    int mes;
    int ano;
    String extra;
    String falta;
    String noturna;
    double reducao;
    String jornada;
    int reg_sub;
    int reg;
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
        this.dias = tabela.getRowCount();
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

   
    
}
