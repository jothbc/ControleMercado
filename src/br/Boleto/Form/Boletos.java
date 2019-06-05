/*
 * Autor: Jonathan Comin Ribeiro
 */
package br.Boleto.Form;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author User
 */
@Entity
@Table(name = "boletos", catalog = "financeiro", schema = "")
@NamedQueries({
    @NamedQuery(name = "Boletos.findAll", query = "SELECT b FROM Boletos b")
    , @NamedQuery(name = "Boletos.findBySeq", query = "SELECT b FROM Boletos b WHERE b.seq = :seq")
    , @NamedQuery(name = "Boletos.findByFornecedorId", query = "SELECT b FROM Boletos b WHERE b.fornecedorId = :fornecedorId")
    , @NamedQuery(name = "Boletos.findByCdBarras", query = "SELECT b FROM Boletos b WHERE b.cdBarras = :cdBarras")
    , @NamedQuery(name = "Boletos.findByValor", query = "SELECT b FROM Boletos b WHERE b.valor = :valor")
    , @NamedQuery(name = "Boletos.findByVencimento", query = "SELECT b FROM Boletos b WHERE b.vencimento = :vencimento")
    , @NamedQuery(name = "Boletos.findByPago", query = "SELECT b FROM Boletos b WHERE b.pago = :pago")})
public class Boletos implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "seq")
    private Integer seq;
    @Column(name = "fornecedor_id")
    private Integer fornecedorId;
    @Basic(optional = false)
    @Column(name = "cd_barras")
    private String cdBarras;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor")
    private Double valor;
    @Column(name = "vencimento")
    @Temporal(TemporalType.DATE)
    private Date vencimento;
    @Column(name = "pago")
    @Temporal(TemporalType.DATE)
    private Date pago;

    public Boletos() {
    }

    public Boletos(Integer seq) {
        this.seq = seq;
    }

    public Boletos(Integer seq, String cdBarras) {
        this.seq = seq;
        this.cdBarras = cdBarras;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        Integer oldSeq = this.seq;
        this.seq = seq;
        changeSupport.firePropertyChange("seq", oldSeq, seq);
    }

    public Integer getFornecedorId() {
        return fornecedorId;
    }

    public void setFornecedorId(Integer fornecedorId) {
        Integer oldFornecedorId = this.fornecedorId;
        this.fornecedorId = fornecedorId;
        changeSupport.firePropertyChange("fornecedorId", oldFornecedorId, fornecedorId);
    }

    public String getCdBarras() {
        return cdBarras;
    }

    public void setCdBarras(String cdBarras) {
        String oldCdBarras = this.cdBarras;
        this.cdBarras = cdBarras;
        changeSupport.firePropertyChange("cdBarras", oldCdBarras, cdBarras);
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        Double oldValor = this.valor;
        this.valor = valor;
        changeSupport.firePropertyChange("valor", oldValor, valor);
    }

    public Date getVencimento() {
        return vencimento;
    }

    public void setVencimento(Date vencimento) {
        Date oldVencimento = this.vencimento;
        this.vencimento = vencimento;
        changeSupport.firePropertyChange("vencimento", oldVencimento, vencimento);
    }

    public Date getPago() {
        return pago;
    }

    public void setPago(Date pago) {
        Date oldPago = this.pago;
        this.pago = pago;
        changeSupport.firePropertyChange("pago", oldPago, pago);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (seq != null ? seq.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Boletos)) {
            return false;
        }
        Boletos other = (Boletos) object;
        if ((this.seq == null && other.seq != null) || (this.seq != null && !this.seq.equals(other.seq))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.Boleto.Form.Boletos[ seq=" + seq + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
