package com.dev.smartmonitor.persistence.dao.model;

public class ConfiguracaoTempoAplicativo {
    private Long id;
    private Long idAplicativo;
    private String tempoDiario; //formato HH:mm
    private String tempoContinuo; //formato HH:mm

    public ConfiguracaoTempoAplicativo() {
        this.id = 0L;
        this.idAplicativo = 0L;
        this.tempoDiario = "";
        this.tempoContinuo = "";
    }

    public ConfiguracaoTempoAplicativo(Long id, Long idAplicativo, String tempoDiario, String tempoContinuo) {
        this.id = id;
        this.idAplicativo = idAplicativo;
        this.tempoDiario = tempoDiario;
        this.tempoContinuo = tempoContinuo;
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

    public void setTempoDiario(String tempoDiario) {
        this.tempoDiario = tempoDiario;
    }

    public String getTempoDiario() {
        return tempoDiario;
    }

    public void setTempoContinuo(String tempoContinuo) {
        this.tempoContinuo = tempoContinuo;
    }

    public String getTempoContinuo() {
        return tempoContinuo;
    }
}
