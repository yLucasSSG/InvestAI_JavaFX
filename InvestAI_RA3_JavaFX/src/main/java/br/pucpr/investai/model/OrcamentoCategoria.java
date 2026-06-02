package br.pucpr.investai.model;

import java.math.BigDecimal;

public class OrcamentoCategoria implements Identificavel, Validavel {
    private int id;
    private int usuarioId;
    private int categoriaId;
    private BigDecimal limiteMensal = BigDecimal.ZERO;
    private int mes;
    private int ano;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getUsuarioId() { return usuarioId; }
    public void setUsuarioId(int usuarioId) { this.usuarioId = usuarioId; }
    public int getCategoriaId() { return categoriaId; }
    public void setCategoriaId(int categoriaId) { this.categoriaId = categoriaId; }
    public BigDecimal getLimiteMensal() { return limiteMensal; }
    public void setLimiteMensal(BigDecimal limiteMensal) { this.limiteMensal = limiteMensal; }
    public int getMes() { return mes; }
    public void setMes(int mes) { this.mes = mes; }
    public int getAno() { return ano; }
    public void setAno(int ano) { this.ano = ano; }

    public void validar() {
        if (usuarioId <= 0) throw new IllegalArgumentException("ID do usuário deve ser maior que zero.");
        if (categoriaId <= 0) throw new IllegalArgumentException("ID da categoria deve ser maior que zero.");
        if (limiteMensal == null || limiteMensal.signum() <= 0) throw new IllegalArgumentException("Limite mensal deve ser maior que zero.");
        if (mes < 1 || mes > 12) throw new IllegalArgumentException("Mês deve estar entre 1 e 12.");
        if (ano < 2000) throw new IllegalArgumentException("Ano inválido.");
    }
}
