package com.dev.smartmonitor.business.table.model;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public class RowTable {
    private Drawable drawable;
    private String nomeAplicativo;
    private String tempoUso;

    public RowTable(){
        this.drawable = null;
        this.nomeAplicativo = "";
        this.tempoUso = "";
    }

    public RowTable(String nomeAplicativo, String tempoUso, Drawable drawable){
        this.drawable = drawable;
        this.nomeAplicativo = nomeAplicativo;
        this.tempoUso = tempoUso;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }

    public Drawable getDrawable() {
        return drawable;
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
