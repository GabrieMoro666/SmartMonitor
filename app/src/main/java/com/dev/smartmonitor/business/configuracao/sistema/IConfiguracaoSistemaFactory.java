package com.dev.smartmonitor.business.configuracao.sistema;

import com.dev.smartmonitor.persistence.dao.model.ConfiguracaoTempoAplicativo;
import com.dev.smartmonitor.persistence.dao.model.ConfiguracaoTempoSistema;

public interface IConfiguracaoSistemaFactory {

    public ConfiguracaoTempoSistema construirConfiguracaoSistema(long idSistema);

    public void updateConfiguracaoSitema(long idSistema, String tempoDiario, String tempoContinuo);

    public ConfiguracaoTempoAplicativo verificarConfiguracaoAplicativosTempoDiario(String tempoDiario);

    public ConfiguracaoTempoAplicativo verificarConfiguracaoAplicativosTempoContinuo(String tempoContinuo);

}
