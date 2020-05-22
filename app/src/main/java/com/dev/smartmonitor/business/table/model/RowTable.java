package com.dev.smartmonitor.business.table.model;

public class RowTable {
    private String nomeAplicativo;
    private String tempoUso;

    public RowTable(){
        this.nomeAplicativo = "";
        this.tempoUso = "";
    }

    public RowTable(String nomeAplicativo, String tempoUso){
        this.nomeAplicativo = nomeAplicativo;
        this.tempoUso = tempoUso;
    }

    public void setNomeAplicativo(String nomeAplicativo) {
        this.nomeAplicativo = nomeAplicativo;
    }

    public String getNomeAplicativo() {
        return nomeAplicativo;
    }

    public void setTempoUso(String tempoUso) {
        this.tempoUso = tempoUso;
    }

    public String getTempoUso() {
        return tempoUso;
    }
}
