package com.dev.smartmonitor.business.basic.delete;

import com.dev.smartmonitor.persistence.dao.model.Aplicativo;
import com.dev.smartmonitor.persistence.dao.model.ChecagemSistema;
import com.dev.smartmonitor.persistence.dao.model.ConfiguracaoTempoAplicativo;
import com.dev.smartmonitor.persistence.dao.model.ConfiguracaoTempoSistema;
import com.dev.smartmonitor.persistence.dao.model.DadosUsoAplicativo;
import com.dev.smartmonitor.persistence.dao.model.DadosUsoSistema;
import com.dev.smartmonitor.persistence.dao.model.NotificacaoAplicativo;
import com.dev.smartmonitor.persistence.dao.model.NotificacaoSistema;
import com.dev.smartmonitor.persistence.dao.model.Sistema;

public interface IDeleteFactory {

    public int excluir(Sistema sistema);

    public int excluir(ChecagemSistema checagemSistema);

    public int excluir(Aplicativo aplicativo);

    public int excluir(ConfiguracaoTempoAplicativo configuracaoTempoAplicativo);

    public int excluir(ConfiguracaoTempoSistema configuracaoTempoSistema);

    public int excluir(DadosUsoAplicativo dadosUsoAplicativo);

    public int excluir(DadosUsoSistema dadosUsoSistema);

    public int excluir(NotificacaoAplicativo notificacaoAplicativo);

    public int excluir(NotificacaoSistema notificacaoSistema);

}
