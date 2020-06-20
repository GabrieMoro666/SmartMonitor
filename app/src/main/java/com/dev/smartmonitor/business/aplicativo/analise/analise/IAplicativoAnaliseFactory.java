package com.dev.smartmonitor.business.aplicativo.analise.analise;

public interface IAplicativoAnaliseFactory {

    public void analizarAplicativo();

    public long analizarAplicativo(String aplicativoDispositivo, String nomePacote);

}
