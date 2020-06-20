package com.dev.smartmonitor.business.aplicativo.analise.model;

public class AplicativoDispositivo {
    private String aplicativo;
    private String pacote;

    public AplicativoDispositivo(){
        this.aplicativo = "";
        this.pacote = "";
    }

    public AplicativoDispositivo(String aplicativo, String pacote){
        this.aplicativo = aplicativo;
        this.pacote = pacote;
    }

    public void setAplicativo(String aplicativo) {
        this.aplicativo = aplicativo;
    }

    public String getAplicativo() {
        return aplicativo;
    }

    public void setPacote(String pacote) {
        this.pacote = pacote;
    }

    public String getPacote() {
        return pacote;
    }

}
