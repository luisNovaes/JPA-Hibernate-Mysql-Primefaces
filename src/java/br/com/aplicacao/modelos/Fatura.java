/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aplicacao.modelos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author luis.silva
 */
@Entity
@Table(name = "fatura")
public class Fatura implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long codDocumento;
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal ValorTotDoc;
    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private StatusDoc StatusDoc;

    @OneToOne
    private Agendamento agendamento;

    public Long getCodDocumento() {
        return codDocumento;
    }

    public void setCodDocumento(Long codDocumento) {
        this.codDocumento = codDocumento;
    }

    public BigDecimal getValorTotDoc() {
        return ValorTotDoc;
    }

    public void setValorTotDoc(BigDecimal ValorTotDoc) {
        this.ValorTotDoc = ValorTotDoc;
    }

    public StatusDoc getStatusDoc() {
        return StatusDoc;
    }

    public void setStatusDoc(StatusDoc StatusDoc) {
        this.StatusDoc = StatusDoc;
    }

    public Agendamento getAgendamento() {
        return agendamento;
    }

    public void setAgendamento(Agendamento agendamento) {
        this.agendamento = agendamento;
    }

    @Override
    public String toString() {
        return "Fatura{" + "codDocumento=" + codDocumento + ", ValorTotDoc=" + ValorTotDoc + ", StatusDoc=" + StatusDoc + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.codDocumento);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Fatura other = (Fatura) obj;
        if (!Objects.equals(this.codDocumento, other.codDocumento)) {
            return false;
        }
        return true;
    }

}
