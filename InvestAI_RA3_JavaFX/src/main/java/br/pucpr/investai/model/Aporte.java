package br.pucpr.investai.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Aporte implements Identificavel, Validavel {

    private int id;
    private int usuarioId;
    private int metaId;
    private BigDecimal valor = BigDecimal.ZERO;
    private LocalDate dataAporte;
    private String observacao;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public int getMetaId() {
        return metaId;
    }

    public void setMetaId(int metaId) {
        this.metaId = metaId;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LocalDate getDataAporte() {
        return dataAporte;
    }

    public void setDataAporte(LocalDate dataAporte) {
        this.dataAporte = dataAporte;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public void validar() {
        if (usuarioId <= 0) {
            throw new IllegalArgumentException("ID do usuário deve ser maior que zero.");
        }

        if (metaId <= 0) {
            throw new IllegalArgumentException("ID da meta deve ser maior que zero.");
        }

        if (valor == null || valor.signum() <= 0) {
            throw new IllegalArgumentException("Valor do aporte deve ser maior que zero.");
        }

        if (dataAporte == null) {
            throw new IllegalArgumentException("Data do aporte é obrigatória.");
        }
    }
}