package com.dev.smartmonitor.business.configuracao.aplicativo.aplicativo;

import com.dev.smartmonitor.business.configuracao.aplicativo.adapter.model.RowConfiguracaoAplicativo;
import com.dev.smartmonitor.persistence.dao.model.ConfiguracaoTempoAplicativo;

import java.util.List;

public interface IConfiguracaoAplicativoFactory {

    public List<RowConfiguracaoAplicativo> construirConfiguracaoAplicativo();

    public void updateConfiguracaoAplicativo(long idAplicativo, String tempoDiario, String tempoContinuo);
}
