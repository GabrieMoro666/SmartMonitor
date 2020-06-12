package com.dev.smartmonitor.business.dados.aplicativo;

import java.util.Date;

public interface IDadosAplicativoFactory {

    public void construirTempoUso(long idAplicativo, Date dataInicial, Date dataFinal);

}
