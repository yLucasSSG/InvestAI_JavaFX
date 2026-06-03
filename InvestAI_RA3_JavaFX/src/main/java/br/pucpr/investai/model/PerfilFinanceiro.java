package br.pucpr.investai.model;

import java.math.BigDecimal;

public class PerfilFinanceiro implements Identificavel, Validavel {

    private int id;
    private int usuarioId;
    private BigDecimal rendaMensal = BigDecimal.ZERO;
    private BigDecimal saldoInicial = BigDecimal.ZERO;
    private String objetivoFinanceiro;
    private String perfilComportamento;

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

    public BigDecimal getRendaMensal() {
        return rendaMensal;
    }

    public void setRendaMensal(BigDecimal rendaMensal) {
        this.rendaMensal = rendaMensal;
    }

    public BigDecimal getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(BigDecimal saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public String getObjetivoFinanceiro() {
        return objetivoFinanceiro;
    }

    public void setObjetivoFinanceiro(String objetivoFinanceiro) {
        this.objetivoFinanceiro = objetivoFinanceiro;
    }

    public String getPerfilComportamento() {
        return perfilComportamento;
    }

    public void setPerfilComportamento(String perfilComportamento) {
        this.perfilComportamento = perfilComportamento;
    }

    public void validar() {
        if (usuarioId <= 0) {
            throw new IllegalArgumentException("ID do usuário deve ser maior que zero.");
        }

        if (rendaMensal == null || rendaMensal.signum() < 0) {
            throw new IllegalArgumentException("Renda mensal inválida.");
        }

        if (saldoInicial == null || saldoInicial.signum() < 0) {
            throw new IllegalArgumentException("Saldo inicial inválido.");
        }

        if (objetivoFinanceiro == null || objetivoFinanceiro.isBlank()) {
            throw new IllegalArgumentException("Objetivo financeiro é obrigatório.");
        }

        if (perfilComportamento == null || perfilComportamento.isBlank()) {
            throw new IllegalArgumentException("Perfil comportamental é obrigatório.");
        }
    }
}