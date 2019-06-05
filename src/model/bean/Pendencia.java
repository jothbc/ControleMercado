/*
 * Autor: Jonathan Comin Ribeiro
 */
package model.bean;

/**
 *
 * @author User
 */
public class Pendencia {
    private int id;
    private boolean situacao;
    private Fornecedor fornecedor;
    private String motivo;
    private double valor;
    private String resposta;
    private boolean visto;
    private String obs;

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
     * @return the situacao
     */
    public boolean isSituacao() {
        return situacao;
    }

    /**
     * @param situacao the situacao to set
     */
    public void setSituacao(boolean situacao) {
        this.situacao = situacao;
    }

    /**
     * @return the fornecedor
     */
    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    /**
     * @param fornecedor the fornecedor to set
     */
    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    /**
     * @return the motivo
     */
    public String getMotivo() {
        return motivo;
    }

    /**
     * @param motivo the motivo to set
     */
    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    /**
     * @return the valor
     */
    public double getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(double valor) {
        this.valor = valor;
    }

    /**
     * @return the resposta
     */
    public String getResposta() {
        return resposta;
    }

    /**
     * @param resposta the resposta to set
     */
    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

    /**
     * @return the visto
     */
    public boolean isVisto() {
        return visto;
    }

    /**
     * @param visto the visto to set
     */
    public void setVisto(boolean visto) {
        this.visto = visto;
    }

    /**
     * @return the obs
     */
    public String getObs() {
        return obs;
    }

    /**
     * @param obs the obs to set
     */
    public void setObs(String obs) {
        this.obs = obs;
    }
    
}
