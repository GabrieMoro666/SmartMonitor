package com.dev.smartmonitor.persistence.dao.model;

import java.util.Date;

public class DadosUsoAplicativo extends DataInicialFinal{
    private Long id;
    private Long idAplicativo;


    public DadosUsoAplicativo() {
        super();
        this.id = 0L;
        this.idAplicativo = 0L;
    }

    public DadosUsoAplicativo(Long id, Long idAplicativo, Date dataInicial, Date dataFinal) {
        super(dataInicial, dataFinal);
        this.id = id;
        this.idAplicativo = idAplicativo;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setIdAplicativo(Long idAplicativo) {
        this.idAplicativo = idAplicativo;
    }

    public Long getIdAplicativo() {
        return idAplicativo;
    }

}
