package br.pucpr.investai.model;

import java.time.LocalDate;

public class NoticiaFinanceira implements Identificavel, Validavel {

    private int id;
    private String titulo;
    private String fonte;
    private String url;
    private String categoria;
    private String nivelImpacto;
    private LocalDate dataPublicacao;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getFonte() {
        return fonte;
    }

    public void setFonte(String fonte) {
        this.fonte = fonte;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getNivelImpacto() {
        return nivelImpacto;
    }

    public void setNivelImpacto(String nivelImpacto) {
        this.nivelImpacto = nivelImpacto;
    }

    public LocalDate getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(LocalDate dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public void validar() {
        if (titulo == null || titulo.isBlank()) {
            throw new IllegalArgumentException("Título da notícia é obrigatório.");
        }

        if (fonte == null || fonte.isBlank()) {
            throw new IllegalArgumentException("Fonte é obrigatória.");
        }

        if (url == null || url.isBlank()) {
            throw new IllegalArgumentException("URL é obrigatória.");
        }

        if (categoria == null || categoria.isBlank()) {
            throw new IllegalArgumentException("Categoria é obrigatória.");
        }

        if (nivelImpacto == null || nivelImpacto.isBlank()) {
            throw new IllegalArgumentException("Nível de impacto é obrigatório.");
        }

        if (dataPublicacao == null) {
            throw new IllegalArgumentException("Data de publicação é obrigatória.");
        }
    }
}