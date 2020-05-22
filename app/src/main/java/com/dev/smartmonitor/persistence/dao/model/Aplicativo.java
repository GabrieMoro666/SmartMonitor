package com.dev.smartmonitor.persistence.dao.model;

public class Aplicativo {
    private Long id;
    private Long idSistema;
    private String nome;
    private String ativo;

    public Aplicativo() {
        this.id = 0L;
        this.idSistema = 0L;
        this.nome = "";
        this.ativo = "";
    }

    public Aplicativo(Long id, Long idSistema, String nome, String ativo) {
        this.id= id;
        this.idSistema = idSistema;
        this.nome = nome;
        this.ativo = ativo;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setIdSistema(Long idSistema) {
        this.idSistema = idSistema;
    }

    public Long getIdSistema() {
        return idSistema;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setAtivo(String ativo) {
        this.ativo = ativo;
    }

    public String getAtivo() {
        return ativo;
    }
}
