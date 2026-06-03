package br.pucpr.investai.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class MetaFinanceira implements Identificavel, Validavel {

    private int id;
    private int usuarioId;
    private String nome;
    private BigDecimal valorTotal = BigDecimal.ZERO;
    private BigDecimal valorGuardado = BigDecimal.ZERO;
    private LocalDate prazo;
    private boolean ativo = true;

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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public BigDecimal getValorGuardado() {
        return valorGuardado;
    }

    public void setValorGuardado(BigDecimal valorGuardado) {
        this.valorGuardado = valorGuardado;
    }

    public LocalDate getPrazo() {
        return prazo;
    }

    public void setPrazo(LocalDate prazo) {
        this.prazo = prazo;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public void validar() {
        if (usuarioId <= 0) {
            throw new IllegalArgumentException("ID do usuário deve ser maior que zero.");
        }

        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome da meta é obrigatório.");
        }

        if (valorTotal == null || valorTotal.signum() <= 0) {
            throw new IllegalArgumentException("Valor total deve ser maior que zero.");
        }

        if (valorGuardado == null || valorGuardado.signum() < 0) {
            throw new IllegalArgumentException("Valor guardado não pode ser negativo.");
        }

        if (prazo == null) {
            throw new IllegalArgumentException("Prazo é obrigatório.");
        }
    }
}