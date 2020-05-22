package com.dev.smartmonitor.persistence.dao.model;

public class ConfiguracaoTempoSistema {
    private Long id;
    private Long idSistema;
    private String tempoDiario; //formato HH:mm
    private String tempoContinuo; //formato HH:mm

    public ConfiguracaoTempoSistema() {
        this.id = 0L;
        this.idSistema = 0L;
        this.tempoDiario = null;
        this.tempoContinuo = null;
    }

    public ConfiguracaoTempoSistema(Long id, Long idSistema, String tempoDiario, String tempoContinuo) {
        this.id = id;
        this.idSistema = idSistema;
        this.tempoDiario = tempoDiario;
        this.tempoContinuo = tempoContinuo;
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
