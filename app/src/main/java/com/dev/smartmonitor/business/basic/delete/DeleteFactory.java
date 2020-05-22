package com.dev.smartmonitor.business.basic.delete;

import android.content.Context;

import com.dev.smartmonitor.persistence.dao.db.feedReader.FeedReaderAplicativo;
import com.dev.smartmonitor.persistence.dao.db.feedReader.FeedReaderChecagemSistema;
import com.dev.smartmonitor.persistence.dao.db.feedReader.FeedReaderConfiguracaoTempoAplicativo;
import com.dev.smartmonitor.persistence.dao.db.feedReader.FeedReaderConfiguracaoTempoSistema;
import com.dev.smartmonitor.persistence.dao.db.feedReader.FeedReaderDadosUsoAplicativo;
import com.dev.smartmonitor.persistence.dao.db.feedReader.FeedReaderDadosUsoSistema;
import com.dev.smartmonitor.persistence.dao.db.feedReader.FeedReaderNotificacaoAplicativo;
import com.dev.smartmonitor.persistence.dao.db.feedReader.FeedReaderNotificacaoSistema;
import com.dev.smartmonitor.persistence.dao.db.feedReader.FeedReaderSistema;
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

public class DeleteFactory implements IDeleteFactory{

    private Context context;

    public DeleteFactory(Context context){
        this.context = context;
    }

    @Override
    public int excluir(Sistema sistema) {
        int deletedRows;
        DAOFactoryCreator daoFactory = new DAOFactoryCreator();

        String selection = FeedReaderSistema.FeedEntry.COLUMN_NAME_ID + " = ?";

        String[] selectionArgs = { Long.toString(sistema.getId()) };

        deletedRows = daoFactory.getFactry(context).createSistema().excluir(selection, selectionArgs);

        return deletedRows;
    }

    @Override
    public int excluir(ChecagemSistema checagemSistema) {
        int deletedRows;
        DAOFactoryCreator daoFactory = new DAOFactoryCreator();

        String selection =  FeedReaderChecagemSistema.FeedEntry.COLUMN_NAME_ID         + " = ? AND " +
                            FeedReaderChecagemSistema.FeedEntry.COLUMN_NAME_ID_SISTEMA + " = ?";

        String[] selectionArgs = { Long.toString(checagemSistema.getId()), Long.toString(checagemSistema.getIdSistema()) };

        deletedRows = daoFactory.getFactry(context).createChecagemSistema().excluir(selection, selectionArgs);

        return deletedRows;
    }

    @Override
    public int excluir(Aplicativo aplicativo) {
        int deletedRows;
        DAOFactoryCreator daoFactory = new DAOFactoryCreator();

        String selection =  FeedReaderAplicativo.FeedEntry.COLUMN_NAME_ID         + " = ? AND " +
                            FeedReaderAplicativo.FeedEntry.COLUMN_NAME_ID_SISTEMA + " = ?";

        String[] selectionArgs = { Long.toString(aplicativo.getId()), Long.toString(aplicativo.getIdSistema()) };

        deletedRows = daoFactory.getFactry(context).createAplicativo().excluir(selection, selectionArgs);

        return deletedRows;
    }

    @Override
    public int excluir(ConfiguracaoTempoAplicativo configuracaoTempoAplicativo) {
        int deletedRows;
        DAOFactoryCreator daoFactory = new DAOFactoryCreator();

        String selection =  FeedReaderConfiguracaoTempoAplicativo.FeedEntry.COLUMN_NAME_ID            + " = ? AND " +
                            FeedReaderConfiguracaoTempoAplicativo.FeedEntry.COLUMN_NAME_ID_APLICATIVO + " = ?";

        String[] selectionArgs = { Long.toString(configuracaoTempoAplicativo.getId()), Long.toString(configuracaoTempoAplicativo.getIdAplicativo()) };

        deletedRows = daoFactory.getFactry(context).createConfiguracaoTempoAplicativo().excluir(selection, selectionArgs);

        return deletedRows;
    }

    @Override
    public int excluir(ConfiguracaoTempoSistema configuracaoTempoSistema) {
        int deletedRows;
        DAOFactoryCreator daoFactory = new DAOFactoryCreator();

        String selection =  FeedReaderConfiguracaoTempoSistema.FeedEntry.COLUMN_NAME_ID         + " = ? AND " +
                            FeedReaderConfiguracaoTempoSistema.FeedEntry.COLUMN_NAME_ID_SISTEMA + " = ?";

        String[] selectionArgs = { Long.toString(configuracaoTempoSistema.getId()), Long.toString(configuracaoTempoSistema.getIdSistema()) };

        deletedRows = daoFactory.getFactry(context).createConfiguracaoTempoSistema().excluir(selection, selectionArgs);

        return deletedRows;
    }

    @Override
    public int excluir(DadosUsoAplicativo dadosUsoAplicativo) {
        int deletedRows;
        DAOFactoryCreator daoFactory = new DAOFactoryCreator();

        String selection =  FeedReaderDadosUsoAplicativo.FeedEntry.COLUMN_NAME_ID            + " = ? AND " +
                            FeedReaderDadosUsoAplicativo.FeedEntry.COLUMN_NAME_ID_APLICATIVO + " = ?";

        String[] selectionArgs = { Long.toString(dadosUsoAplicativo.getId()), Long.toString(dadosUsoAplicativo.getIdAplicativo()) };

        deletedRows = daoFactory.getFactry(context).createDadosUsoAplicativo().excluir(selection, selectionArgs);

        return deletedRows;
    }

    @Override
    public int excluir(DadosUsoSistema dadosUsoSistema) {
        int deletedRows;
        DAOFactoryCreator daoFactory = new DAOFactoryCreator();

        String selection =  FeedReaderDadosUsoSistema.FeedEntry.COLUMN_NAME_ID         + " = ? AND " +
                            FeedReaderDadosUsoSistema.FeedEntry.COLUMN_NAME_ID_SISTEMA + " = ?";

        String[] selectionArgs = { Long.toString(dadosUsoSistema.getId()), Long.toString(dadosUsoSistema.getIdSistema()) };

        deletedRows = daoFactory.getFactry(context).createDadosUsoSistema().excluir(selection, selectionArgs);

        return deletedRows;
    }

    @Override
    public int excluir(NotificacaoAplicativo notificacaoAplicativo) {
        int deletedRows;
        DAOFactoryCreator daoFactory = new DAOFactoryCreator();

        String selection =  FeedReaderNotificacaoAplicativo.FeedEntry.COLUMN_NAME_ID              + " = ? AND " +
                            FeedReaderNotificacaoAplicativo.FeedEntry.COLUMN_NAME_ID_APLICATIVO   + " = ? AND " +
                            FeedReaderNotificacaoAplicativo.FeedEntry.COLUMN_NAME_ID_CONFIGURACAO + " = ?";

        String[] selectionArgs = { Long.toString(notificacaoAplicativo.getId()), Long.toString(notificacaoAplicativo.getIdAplicativo()), Long.toString(notificacaoAplicativo.getIdConfiguracao()) };

        deletedRows = daoFactory.getFactry(context).createNotificacaoAplicativo().excluir(selection, selectionArgs);

        return deletedRows;
    }

    @Override
    public int excluir(NotificacaoSistema notificacaoSistema) {
        int deletedRows;
        DAOFactoryCreator daoFactory = new DAOFactoryCreator();

        String selection =  FeedReaderNotificacaoSistema.FeedEntry.COLUMN_NAME_ID              + " = ? AND " +
                            FeedReaderNotificacaoSistema.FeedEntry.COLUMN_NAME_ID_SISTEMA   + " = ? AND " +
                            FeedReaderNotificacaoSistema.FeedEntry.COLUMN_NAME_ID_CONFIGURACAO + " = ?";

        String[] selectionArgs = { Long.toString(notificacaoSistema.getId()), Long.toString(notificacaoSistema.getIdSistema()), Long.toString(notificacaoSistema.getIdConfiguracao()) };

        deletedRows = daoFactory.getFactry(context).createNotificacaoSistema().excluir(selection, selectionArgs);

        return deletedRows;
    }

}
