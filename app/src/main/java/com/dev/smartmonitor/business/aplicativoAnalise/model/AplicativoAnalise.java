package com.dev.smartmonitor.business.aplicativoAnalise.model;

import com.dev.smartmonitor.persistence.dao.model.Aplicativo;

public class AplicativoAnalise {
    private Aplicativo aplicativo;
    private boolean verificado;

    public AplicativoAnalise(){
        this.aplicativo = null;
        this.verificado = false;
    }

    public AplicativoAnalise(Aplicativo aplicativo, boolean verificado){
        this.aplicativo = aplicativo;
        this.verificado = verificado;
    }

    public void setAplicativo(Aplicativo aplicativo) {
        this.aplicativo = aplicativo;
    }

    public Aplicativo getAplicativo() {
        return aplicativo;
    }

    public void setVerificado(boolean verificado) {
        this.verificado = verificado;
    }

    public boolean isVerificado() {
        return verificado;
    }

}
