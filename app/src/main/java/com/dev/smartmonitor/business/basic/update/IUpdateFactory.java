package com.dev.smartmonitor.business.basic.update;

import com.dev.smartmonitor.persistence.dao.model.Aplicativo;
import com.dev.smartmonitor.persistence.dao.model.ChecagemSistema;
import com.dev.smartmonitor.persistence.dao.model.ConfiguracaoTempoAplicativo;
import com.dev.smartmonitor.persistence.dao.model.ConfiguracaoTempoSistema;
import com.dev.smartmonitor.persistence.dao.model.DadosUsoAplicativo;
import com.dev.smartmonitor.persistence.dao.model.DadosUsoSistema;
import com.dev.smartmonitor.persistence.dao.model.NotificacaoAplicativo;
import com.dev.smartmonitor.persistence.dao.model.NotificacaoSistema;
import com.dev.smartmonitor.persistence.dao.model.Sistema;

public interface IUpdateFactory {

    public int atualizar(Sistema sistema);

    public int atualizar(ChecagemSistema checagemSistema);

    public int atualizar(Aplicativo aplicativo);

    public int atualizar(ConfiguracaoTempoAplicativo configuracaoTempoAplicativo);

    public int atualizar(ConfiguracaoTempoSistema configuracaoTempoSistema);

    public int atualizar(DadosUsoAplicativo dadosUsoAplicativo);

    public int atualizar(DadosUsoSistema dadosUsoSistema);

    public int atualizar(NotificacaoAplicativo notificacaoAplicativo);

    public int atualizar(NotificacaoSistema notificacaoSistema);

}
