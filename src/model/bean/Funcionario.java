/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author User
 */
public class Funcionario {

    private String nome;
    private int codigo;
    private String cpf;
    private String pis;
    private String cargo;
    private Date admissao;
    private int ativo;

    public static final String SQL = "SQL";
    public static final String BR = "BR";

    public Funcionario() {
    }

    public Funcionario(String nome, int codigo) {
        this.nome = nome.toUpperCase();
        this.codigo = codigo;
        this.cpf = null;
        this.pis = null;
        this.cargo = null;
        this.admissao = null;
        this.ativo = 1;
    }

    public Funcionario(String nome, int codigo, String cpf, String pis, String cargo, Date admissao) {
        this.nome = nome.toUpperCase();
        this.codigo = codigo;
        this.cpf = cpf;
        this.pis = pis;
        this.cargo = cargo.toUpperCase();
        this.admissao = admissao;
        this.ativo =1;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome.toUpperCase();
    }

    /**
     * @return the codigo
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the cpf
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * @param cpf the cpf to set
     */
    public void setCpf(String cpf) {
        if (cpf.equals("   .   .   -  ")) {
            this.cpf = null;
        } else {
            this.cpf = cpf;
        }
    }

    /**
     * @return the pis
     */
    public String getPis() {
        return pis;
    }

    /**
     * @param pis the pis to set
     */
    public void setPis(String pis) {
        if (pis.equals("   .     .  - ")) {
            this.pis = null;
        } else {
            this.pis = pis;
        }
    }

    /**
     * @return the cargo
     */
    public String getCargo() {
        return cargo;
    }

    /**
     * @param cargo the cargo to set
     */
    public void setCargo(String cargo) {
        if (cargo.equals("")) {
            this.cargo = null;
        } else {
            this.cargo = cargo.toUpperCase();
        }
    }

    /**
     * @return the admissao
     */
    public Date getAdmissao() {
        return admissao;
    }

    /**
     * SQL ou BR
     *
     * @param tipo
     * @return
     * @throws ParseException
     */
    public String getAdmissao(String tipo) throws ParseException, Exception {
        switch (tipo) {
            case SQL:
                SimpleDateFormat formatoSQL = new SimpleDateFormat("yyyy-MM-dd");
                return formatoSQL.format(this.admissao);
            case BR:
                SimpleDateFormat formatoBR = new SimpleDateFormat("dd/MM/yyyy");
                return formatoBR.format(this.admissao);
            default:
                return null;
        }
    }

    /**
     * @param admissao the admissao to set
     */
    public void setAdmissao(Date admissao) {
        this.admissao = admissao;
    }

    public void setAdmissao(String data, String tipo) throws ParseException, Exception {
        Date admissao2 = null;
        DateFormat f;
        switch (tipo) {
            case SQL:
                f = new SimpleDateFormat("yyyy-MM-dd");
                admissao2 = (Date) f.parse(data);
                this.admissao = admissao2;
                break;
            case BR:
                f = new SimpleDateFormat("dd/MM/yyyy");
                admissao2 = (Date) f.parse(data);
                this.admissao = admissao2;
                break;
            default:
                this.admissao = null;
        }
    }

    public int getAtivo() {
        return ativo;
    }

    public void setAtivo(int ativo) {
        this.ativo = ativo;
    }
    
}
