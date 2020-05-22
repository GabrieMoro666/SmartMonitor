package com.dev.smartmonitor.persistence.dao.factory;

import android.content.Context;

import com.dev.smartmonitor.persistence.dao.dao.AplicativoDAO;
import com.dev.smartmonitor.persistence.dao.dao.ChecagemSistemaDAO;
import com.dev.smartmonitor.persistence.dao.dao.ConfiguracaoTempoAplicativoDAO;
import com.dev.smartmonitor.persistence.dao.dao.ConfiguracaoTempoSistemaDAO;
import com.dev.smartmonitor.persistence.dao.dao.DadosUsoAplicativoDAO;
import com.dev.smartmonitor.persistence.dao.dao.DadosUsoSistemaDAO;
import com.dev.smartmonitor.persistence.dao.dao.NotificacaoAplicativoDAO;
import com.dev.smartmonitor.persistence.dao.dao.NotificacaoSistemaDAO;
import com.dev.smartmonitor.persistence.dao.dao.SistemaDAO;
import com.dev.smartmonitor.persistence.dao.idao.IAplicativoDAO;
import com.dev.smartmonitor.persistence.dao.idao.IChecagemSistemaDAO;
import com.dev.smartmonitor.persistence.dao.idao.IConfiguracaoTempoAplicativoDAO;
import com.dev.smartmonitor.persistence.dao.idao.IConfiguracaoTempoSistemaDAO;
import com.dev.smartmonitor.persistence.dao.idao.IDadosUsoAplicativoDAO;
import com.dev.smartmonitor.persistence.dao.idao.IDadosUsoSistemaDAO;
import com.dev.smartmonitor.persistence.dao.idao.INotificacaoAplicativoDAO;
import com.dev.smartmonitor.persistence.dao.idao.INotificacaoSistemaDAO;
import com.dev.smartmonitor.persistence.dao.idao.ISistemaDAO;

public class DAOFactory implements IDAOFactory {
    private Context context;

    public DAOFactory(Context context) {
        this.context = context;
    }

    public IAplicativoDAO createAplicativo() {
        return new AplicativoDAO(context);
    }

    public IChecagemSistemaDAO createChecagemSistema() {
        return new ChecagemSistemaDAO(context);
    }

    public IConfiguracaoTempoAplicativoDAO createConfiguracaoTempoAplicativo() {
        return new ConfiguracaoTempoAplicativoDAO(context);
    }

    public IConfiguracaoTempoSistemaDAO createConfiguracaoTempoSistema() {
        return new ConfiguracaoTempoSistemaDAO(context);
    }

    public IDadosUsoAplicativoDAO createDadosUsoAplicativo() {
        return new DadosUsoAplicativoDAO(context);
    }

    public IDadosUsoSistemaDAO createDadosUsoSistema() {
        return new DadosUsoSistemaDAO(context);
    }

    public INotificacaoAplicativoDAO createNotificacaoAplicativo() {
        return new NotificacaoAplicativoDAO(context);
    }

    public INotificacaoSistemaDAO createNotificacaoSistema() {
        return new NotificacaoSistemaDAO(context);
    }

    public ISistemaDAO createSistema() {
        return new SistemaDAO(context);
    }
}
