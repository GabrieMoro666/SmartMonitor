package com.dev.smartmonitor.persistence.dao.factory;

import com.dev.smartmonitor.persistence.dao.idao.IAplicativoDAO;
import com.dev.smartmonitor.persistence.dao.idao.IChecagemSistemaDAO;
import com.dev.smartmonitor.persistence.dao.idao.IConfiguracaoTempoAplicativoDAO;
import com.dev.smartmonitor.persistence.dao.idao.IConfiguracaoTempoSistemaDAO;
import com.dev.smartmonitor.persistence.dao.idao.IDadosUsoAplicativoDAO;
import com.dev.smartmonitor.persistence.dao.idao.IDadosUsoSistemaDAO;
import com.dev.smartmonitor.persistence.dao.idao.INotificacaoAplicativoDAO;
import com.dev.smartmonitor.persistence.dao.idao.INotificacaoSistemaDAO;
import com.dev.smartmonitor.persistence.dao.idao.ISistemaDAO;

public interface IDAOFactory {

    public IAplicativoDAO createAplicativo();

    public IChecagemSistemaDAO createChecagemSistema();

    public IConfiguracaoTempoAplicativoDAO createConfiguracaoTempoAplicativo();

    public IConfiguracaoTempoSistemaDAO createConfiguracaoTempoSistema();

    public IDadosUsoAplicativoDAO createDadosUsoAplicativo();

    public IDadosUsoSistemaDAO createDadosUsoSistema();

    public INotificacaoAplicativoDAO createNotificacaoAplicativo();

    public INotificacaoSistemaDAO createNotificacaoSistema();

    public ISistemaDAO createSistema();
}
