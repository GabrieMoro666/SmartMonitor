package com.dev.smartmonitor.persistence.dao.model;

import java.util.Date;

public class ChecagemSistema {
    private Long id;
    private Long idSistema;
    private Date data;
    private Integer quantidade;

    public ChecagemSistema() {
        this.id = 0L;
        this.idSistema = 0L;
        this.data = null;
        this.quantidade = 0;
    }

    public ChecagemSistema(Long id, Long idSistema, Date data, Integer quantidade) {
        this.id = id;
        this.idSistema = idSistema;
        this.data = data;
        this.quantidade = quantidade;
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

    public void setData(Date data) {
        this.data = data;
    }

    public Date getData() {
        return data;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Integer getQuantidade() {
        return quantidade;
    }
}
