package br.pucpr.investai.model;

public class Usuario implements Identificavel, Validavel {

    private int id;
    private String nome;
    private String email;
    private String telefone;
    private boolean ativo;

    public Usuario() {
        this.ativo = true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public void validar() {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome do usuário é obrigatório.");
        }

        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("E-mail inválido.");
        }

        if (telefone == null || telefone.isBlank()) {
            throw new IllegalArgumentException("Telefone é obrigatório.");
        }
    }
}