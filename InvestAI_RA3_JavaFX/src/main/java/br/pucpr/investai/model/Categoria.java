package br.pucpr.investai.model;

public class Categoria implements Identificavel, Validavel {
    private int id;
    private int usuarioId;
    private String nome;
    private String tipo;
    private String descricao;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getUsuarioId() { return usuarioId; }
    public void setUsuarioId(int usuarioId) { this.usuarioId = usuarioId; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public void validar() {
        if (nome == null || nome.isBlank()) throw new IllegalArgumentException("Nome da categoria é obrigatório.");
        if (tipo == null || !(tipo.equalsIgnoreCase("ganho") || tipo.equalsIgnoreCase("despesa"))) throw new IllegalArgumentException("Tipo deve ser 'ganho' ou 'despesa'.");
    }
}
