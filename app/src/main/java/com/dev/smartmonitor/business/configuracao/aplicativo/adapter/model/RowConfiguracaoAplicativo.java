package com.dev.smartmonitor.business.configuracao.aplicativo.adapter.model;

public class RowConfiguracaoAplicativo {

    private long idAplicativo;
    private String nome;
    private String tempoDiario;
    private String tempoContinuo;

    public RowConfiguracaoAplicativo(){
        this.idAplicativo = 0L;
        this.nome = "";
        this.tempoDiario = "";
        this.tempoContinuo = "";
    }

    public RowConfiguracaoAplicativo(long idAplicativo, String nome, String tempoDiario, String tempoContinuo){
        this.idAplicativo = idAplicativo;
        this.nome = nome;
        this.tempoDiario = tempoDiario;
        this.tempoContinuo = tempoContinuo;
    }

    public void setIdAplicativo(long idAplicativo) {
        this.idAplicativo = idAplicativo;
    }

    public long getIdAplicativo() {
        return idAplicativo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
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
