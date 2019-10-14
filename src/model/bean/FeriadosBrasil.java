/*
 * Autor: Jonathan Comin Ribeiro
 */
package model.bean;

/**
 *
 * @author jothbc classe criada com o intuito de ter uma lista contendo todos os
 * feriados brasileiros em formato Calendar lembrando que janeiro é representado
 * como 0 no calendar...
 *
 */
public class FeriadosBrasil {

    private int id;
    private int dia;
    private int mes;
    private int ano;
    private String descricao;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the dia
     */
    public int getDia() {
        return dia;
    }

    /**
     * @param dia the dia to set
     */
    public void setDia(int dia) {
        this.dia = dia;
    }

    /**
     * @return the mes
     */
    public int getMes() {
        return mes;
    }

    /**
     * @param mes the mes to set
     */
    public void setMes(int mes) {
        this.mes = mes;
    }

    /**
     * @return the ano
     */
    public int getAno() {
        return ano;
    }

    /**
     * @param ano the ano to set
     */
    public void setAno(int ano) {
        this.ano = ano;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String toString() {
        String d, m, a;
        d = Integer.toString(this.dia);
        if (this.dia < 10) {
            d = "0" + d;
        }
        m = Integer.toString(this.mes);
        if (this.mes < 10) {
            m = "0" + m;
        }
        a = Integer.toString(this.ano);
        return (d + "/" + m + "/" + a + "   " + this.descricao);

    }

}
