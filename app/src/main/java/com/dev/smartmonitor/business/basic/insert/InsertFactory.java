package com.dev.smartmonitor.business.basic.insert;

import android.content.Context;

import com.dev.smartmonitor.persistence.dao.factory.DAOFactoryCreator;
import com.dev.smartmonitor.persistence.dao.model.Aplicativo;
import com.dev.smartmonitor.persistence.dao.model.ChecagemSistema;
import com.dev.smartmonitor.persistence.dao.model.ConfiguracaoTempoAplicativo;
import com.dev.smartmonitor.persistence.dao.model.ConfiguracaoTempoSistema;
import com.dev.smartmonitor.persistence.dao.model.DadosUsoAplicativo;
import com.dev.smartmonitor.persistence.dao.model.DadosUsoSistema;
import com.dev.smartmonitor.persistence.dao.model.NotificacaoAplicativo;
import com.dev.smartmonitor.persistence.dao.model.NotificacaoSistema;
import com.dev.smartmonitor.persistence.dao.model.Sistema;

public class InsertFactory implements IInsertFactory{

    private Context context;

    public InsertFactory(Context context){
        this.context = context;
    }

    @Override
    public long inserir(Sistema sistema) {
        long newRowId;
        DAOFactoryCreator daoFactory = new DAOFactoryCreator();

        newRowId = daoFactory.getFactry(context).createSistema().inserir(sistema);

        return newRowId;
    }

    @Override
    public long inserir(ChecagemSistema checagemSistema) {
        long newRowId;
        DAOFactoryCreator daoFactory = new DAOFactoryCreator();

        newRowId = daoFactory.getFactry(context).createChecagemSistema().inserir(checagemSistema);

        return newRowId;
    }

    @Override
    public long inserir(Aplicativo aplicativo) {
        long newRowId;
        DAOFactoryCreator daoFactory = new DAOFactoryCreator();

        newRowId = daoFactory.getFactry(context).createAplicativo().inserir(aplicativo);

        return newRowId;
    }

    @Override
    public long inserir(ConfiguracaoTempoAplicativo configuracaoTempoAplicativo) {
        long newRowId;
        DAOFactoryCreator daoFactory = new DAOFactoryCreator();

        newRowId = daoFactory.getFactry(context).createConfiguracaoTempoAplicativo().inserir(configuracaoTempoAplicativo);

        return newRowId;
    }

    @Override
    public long inserir(ConfiguracaoTempoSistema configuracaoTempoSistema) {
        long newRowId;
        DAOFactoryCreator daoFactory = new DAOFactoryCreator();

        newRowId = daoFactory.getFactry(context).createConfiguracaoTempoSistema().inserir(configuracaoTempoSistema);

        return newRowId;
    }

    @Override
    public long inserir(DadosUsoAplicativo dadosUsoAplicativo) {
        long newRowId;
        DAOFactoryCreator daoFactory = new DAOFactoryCreator();

        newRowId = daoFactory.getFactry(context).createDadosUsoAplicativo().inserir(dadosUsoAplicativo);

        return newRowId;
    }

    @Override
    public long inserir(DadosUsoSistema dadosUsoSistema) {
        long newRowId;
        DAOFactoryCreator daoFactory = new DAOFactoryCreator();

        newRowId = daoFactory.getFactry(context).createDadosUsoSistema().inserir(dadosUsoSistema);

        return newRowId;
    }

    @Override
    public long inserir(NotificacaoAplicativo notificacaoAplicativo) {
        long newRowId;
        DAOFactoryCreator daoFactory = new DAOFactoryCreator();

        newRowId = daoFactory.getFactry(context).createNotificacaoAplicativo().inserir(notificacaoAplicativo);

        return newRowId;
    }

    @Override
    public long inserir(NotificacaoSistema notificacaoSistema) {
        long newRowId;
        DAOFactoryCreator daoFactory = new DAOFactoryCreator();

        newRowId = daoFactory.getFactry(context).createNotificacaoSistema().inserir(notificacaoSistema);

        return newRowId;
    }

}
