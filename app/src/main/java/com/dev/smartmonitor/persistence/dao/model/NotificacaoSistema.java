package com.dev.smartmonitor.persistence.dao.model;

import java.util.Date;

public class NotificacaoSistema {
    private Long id;
    private Long idConfiguracao;
    private Long idSistema;
    private Date data;
    private String titulo;
    private String descricao;
    private String status;

    public NotificacaoSistema() {
        this.id = 0L;
        this.idConfiguracao = 0L;
        this.idSistema = 0L;
        this.data = null;
        this.titulo = "";
        this.descricao = "";
        this.status = "";
    }

    public NotificacaoSistema(Long id, Long idConfiguracao, Long idSistema, Date data, String titulo, String descricao, String status) {
        this.id = id;
        this.idConfiguracao = idConfiguracao;
        this.idSistema = idSistema;
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

    public void setIdSistema(Long idSistema) {
        this.idSistema = idSistema;
    }

    public Long getIdSistema() {
        return idSistema;
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
