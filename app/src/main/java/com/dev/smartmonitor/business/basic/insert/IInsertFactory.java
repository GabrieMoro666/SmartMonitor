package com.dev.smartmonitor.business.basic.insert;

import com.dev.smartmonitor.persistence.dao.model.Aplicativo;
import com.dev.smartmonitor.persistence.dao.model.ChecagemSistema;
import com.dev.smartmonitor.persistence.dao.model.ConfiguracaoTempoAplicativo;
import com.dev.smartmonitor.persistence.dao.model.ConfiguracaoTempoSistema;
import com.dev.smartmonitor.persistence.dao.model.DadosUsoAplicativo;
import com.dev.smartmonitor.persistence.dao.model.DadosUsoSistema;
import com.dev.smartmonitor.persistence.dao.model.NotificacaoAplicativo;
import com.dev.smartmonitor.persistence.dao.model.NotificacaoSistema;
import com.dev.smartmonitor.persistence.dao.model.Sistema;

public interface IInsertFactory {

    public long inserir(Sistema sistema);

    public long inserir(ChecagemSistema checagemSistema);

    public long inserir(Aplicativo aplicativo);

    public long inserir(ConfiguracaoTempoAplicativo configuracaoTempoAplicativo);

    public long inserir(ConfiguracaoTempoSistema configuracaoTempoSistema);

    public long inserir(DadosUsoAplicativo dadosUsoAplicativo);

    public long inserir(DadosUsoSistema dadosUsoSistema);

    public long inserir(NotificacaoAplicativo notificacaoAplicativo);

    public long inserir(NotificacaoSistema notificacaoSistema);

}
