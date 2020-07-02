package com.dev.smartmonitor.business.configuracao.aplicativo.adapter.model;

import android.graphics.drawable.Drawable;

public class RowConfiguracaoAplicativo {
    private long idAplicativo;
    private String nome;
    private String tempoDiario;
    private String tempoContinuo;
    private Drawable drawable;

    public RowConfiguracaoAplicativo(){
        this.idAplicativo = 0L;
        this.nome = "";
        this.tempoDiario = "";
        this.tempoContinuo = "";
        this.drawable = null;
    }

    public RowConfiguracaoAplicativo(long idAplicativo, String nome, String tempoDiario, String tempoContinuo, Drawable drawable){
        this.idAplicativo = idAplicativo;
        this.nome = nome;
        this.tempoDiario = tempoDiario;
        this.tempoContinuo = tempoContinuo;
        this.drawable = drawable;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }

    public Drawable getDrawable() {
        return drawable;
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
