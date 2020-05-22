package com.dev.smartmonitor.business.notification.model;

import java.util.Date;

public class RowNotification {
    private String titulo;
    private String descricao;
    private Date data;
    private String tipo;
    private long idNotificacao;
    private long idConfiguracao;
    private long idOrigem; //Aplicativo ou Sistema

    public RowNotification(){
        this.titulo = "";
        this.descricao = "";
        this.data = null;
        this.tipo = "";
        this.idNotificacao = 0;
        this.idConfiguracao = 0;
        this.idOrigem = 0;
    }

    public RowNotification(String titulo, String descricao, Date data, String tipo, long idNotificacao, long idConfiguracao, long idOrigem) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.data = data;
        this.tipo = tipo;
        this.idNotificacao = idNotificacao;
        this.idConfiguracao = idConfiguracao;
        this.idOrigem = idOrigem;
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

    public void setData(Date data) {
        this.data = data;
    }

    public Date getData() {
        return data;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setIdNotificacao(long idNotificacao) {
        this.idNotificacao = idNotificacao;
    }

    public void setIdConfiguracao(long idConfiguracao) {
        this.idConfiguracao = idConfiguracao;
    }

    public long getIdConfiguracao() {
        return idConfiguracao;
    }

    public long getIdNotificacao() {
        return idNotificacao;
    }

    public void setIdOrigem(long idOrigem) {
        this.idOrigem = idOrigem;
    }

    public long getIdOrigem() {
        return idOrigem;
    }

}
