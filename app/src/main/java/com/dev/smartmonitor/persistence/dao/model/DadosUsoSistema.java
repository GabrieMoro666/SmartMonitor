package com.dev.smartmonitor.persistence.dao.model;

import java.util.Date;

public class DadosUsoSistema extends DataInicialFinal{
    private Long id;
    private Long idSistema;

    public DadosUsoSistema() {
        super();
        this.id = 0L;
        this.idSistema = 0L;
    }

    public DadosUsoSistema(Long id, Long idSistema, Date dataInicial, Date dataFinal) {
        super(dataInicial,dataFinal);
        this.id = id;
        this.idSistema = idSistema;
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

}
