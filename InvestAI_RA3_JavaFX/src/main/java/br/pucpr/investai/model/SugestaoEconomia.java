package br.pucpr.investai.model;

public class SugestaoEconomia implements Identificavel, Validavel {

    private int id;
    private int usuarioId;
    private String titulo;
    private String descricao;
    private String fonte;
    private String categoriaNome;
    private int mes;
    private int ano;

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

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getFonte() {
        return fonte;
    }

    public void setFonte(String fonte) {
        this.fonte = fonte;
    }

    public String getCategoriaNome() {
        return categoriaNome;
    }

    public void setCategoriaNome(String categoriaNome) {
        this.categoriaNome = categoriaNome;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public void validar() {
        if (usuarioId <= 0) {
            throw new IllegalArgumentException("ID do usuário deve ser maior que zero.");
        }

        if (titulo == null || titulo.isBlank()) {
            throw new IllegalArgumentException("Título da sugestão é obrigatório.");
        }

        if (descricao == null || descricao.isBlank()) {
            throw new IllegalArgumentException("Descrição da sugestão é obrigatória.");
        }

        if (fonte == null || fonte.isBlank()) {
            throw new IllegalArgumentException("Fonte é obrigatória.");
        }

        if (mes < 1 || mes > 12) {
            throw new IllegalArgumentException("Mês deve estar entre 1 e 12.");
        }

        if (ano < 2000) {
            throw new IllegalArgumentException("Ano inválido.");
        }
    }
}