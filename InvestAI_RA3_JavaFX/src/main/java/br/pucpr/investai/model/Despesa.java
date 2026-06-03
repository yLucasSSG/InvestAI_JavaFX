package br.pucpr.investai.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Despesa implements Identificavel, Validavel {

    private int id;
    private int usuarioId;
    private String descricao;
    private BigDecimal valor = BigDecimal.ZERO;
    private LocalDate dataDespesa;
    private boolean fixo;
    private int categoriaId;

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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LocalDate getDataDespesa() {
        return dataDespesa;
    }

    public void setDataDespesa(LocalDate dataDespesa) {
        this.dataDespesa = dataDespesa;
    }

    public boolean isFixo() {
        return fixo;
    }

    public void setFixo(boolean fixo) {
        this.fixo = fixo;
    }

    public int getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(int categoriaId) {
        this.categoriaId = categoriaId;
    }

    public void validar() {
        if (usuarioId <= 0) {
            throw new IllegalArgumentException("ID do usuário deve ser maior que zero.");
        }

        if (descricao == null || descricao.isBlank()) {
            throw new IllegalArgumentException("Descrição da despesa é obrigatória.");
        }

        if (valor == null || valor.signum() <= 0) {
            throw new IllegalArgumentException("Valor da despesa deve ser maior que zero.");
        }

        if (dataDespesa == null) {
            throw new IllegalArgumentException("Data da despesa é obrigatória.");
        }
    }
}