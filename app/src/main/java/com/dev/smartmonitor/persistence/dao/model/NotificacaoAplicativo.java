package com.dev.smartmonitor.persistence.dao.model;

import java.util.Date;

public class NotificacaoAplicativo {
    private Long id;
    private Long idConfiguracao;
    private Long idAplicativo;
    private Date data;
    private String titulo;      //Tempo continuo atingido //Tempo diario atingido //Tempo continuo passou o limite //Tempo diario passou o limite
    private String descricao;   //Aplicaivo <aplicativo> atingiu o limite de tempo configurado <tempo configurado> minutos com <tempo calculado> no dia <dia>
    private String status;      //E Aberto //X Concluido

    public NotificacaoAplicativo() {
        this.id = 0L;
        this.idConfiguracao = 0L;
        this.idAplicativo = 0L;
        this.data = null;
        this.titulo = "";
        this.descricao = "";
        this.status = "";
    }

    public NotificacaoAplicativo(Long id, Long idConfiguracao, Long idAplicativo, Date data, String titulo, String descricao, String status) {
        this.id = id;
        this.idConfiguracao = idConfiguracao;
        this.idAplicativo = idAplicativo;
        this.data = data;
        this.titulo = titulo;
        this.descricao = descricao;
        this.status = status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setIdConfiguracao(Long idConfiguracao) {
        this.idConfiguracao = idConfiguracao;
    }

    public Long getIdConfiguracao() {
        return idConfiguracao;
    }

    public void setIdAplicativo(Long idAplicativo) {
        this.idAplicativo = idAplicativo;
    }

    public Long getIdAplicativo() {
        return idAplicativo;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Date getData() {
        return data;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
