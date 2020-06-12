package com.dev.smartmonitor.business.dados.sistema;

import java.util.Date;

public interface IDadosSistemaFactory {

    public void construirTempoUso(long idSistema, Date dataInicial, Date dataFinal);

}
