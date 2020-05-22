package com.dev.smartmonitor.persistence.dao.model;

import java.util.Date;

public class DataInicialFinal {
    private Date dataInicial;    //formato dd/MM/yyyy HH:mm
    private Date dataFinal;      //formato dd/MM/yyyy HH:mm

    public DataInicialFinal(){
        this.dataInicial = null;
        this.dataFinal = null;
    }

    public DataInicialFinal(Date dataInicial, Date dataFinal){
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
    }

    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }

    public Date getDataInicial() {
        return dataInicial;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    public Date getDataFinal() {
        return dataFinal;
    }
}
