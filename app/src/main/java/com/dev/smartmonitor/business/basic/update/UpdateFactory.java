package com.dev.smartmonitor.business.basic.update;

import android.content.ContentValues;
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
import com.dev.smartmonitor.util.Util;

public class UpdateFactory implements IUpdateFactory{

    private Context context;

    public UpdateFactory(Context context){
        this.context = context;
    }

    @Override
    public int atualizar(Sistema sistema) {
        int rowsUpdate;
        DAOFactoryCreator daoFactory = new DAOFactoryCreator();

        ContentValues values = new ContentValues();
        values.put(FeedReaderSistema.FeedEntry.COLUMN_NAME_ID, sistema.getId());

        String selection = FeedReaderSistema.FeedEntry.COLUMN_NAME_ID + " = ?";

        String[] selectionArgs = { Long.toString(sistema.getId()) };

        rowsUpdate = daoFactory.getFactry(context).createSistema().atualizar(values, selection, selectionArgs);

        return rowsUpdate;
    }

    @Override
    public int atualizar(ChecagemSistema checagemSistema) {
        int rowsUpdate;
        DAOFactoryCreator daoFactory = new DAOFactoryCreator();

        ContentValues values = new ContentValues();
        values.put(FeedReaderChecagemSistema.FeedEntry.COLUMN_NAME_ID, checagemSistema.getId());
        values.put(FeedReaderChecagemSistema.FeedEntry.COLUMN_NAME_ID_SISTEMA, checagemSistema.getIdSistema());
        values.put(FeedReaderChecagemSistema.FeedEntry.COLUMN_NAME_DATA, Util.formatarDataPara(checagemSistema.getData()));
        values.put(FeedReaderChecagemSistema.FeedEntry.COLUMN_NAME_QUANTIDADE, checagemSistema.getQuantidade());

        String selection =  FeedReaderChecagemSistema.FeedEntry.COLUMN_NAME_ID         + " = ? AND " +
                            FeedReaderChecagemSistema.FeedEntry.COLUMN_NAME_ID_SISTEMA + " = ?";

        String[] selectionArgs = { Long.toString(checagemSistema.getId()), Long.toString(checagemSistema.getIdSistema()) };

        rowsUpdate = daoFactory.getFactry(context).createChecagemSistema().atualizar(values, selection, selectionArgs);

        return rowsUpdate;
    }

    @Override
    public int atualizar(Aplicativo aplicativo) {
        int rowsUpdate;
        DAOFactoryCreator daoFactory = new DAOFactoryCreator();

        ContentValues values = new ContentValues();
        values.put(FeedReaderAplicativo.FeedEntry.COLUMN_NAME_ID, aplicativo.getId());
        values.put(FeedReaderAplicativo.FeedEntry.COLUMN_NAME_ID_SISTEMA, aplicativo.getIdSistema());
        values.put(FeedReaderAplicativo.FeedEntry.COLUMN_NAME_NOME, aplicativo.getNome());
        values.put(FeedReaderAplicativo.FeedEntry.COLUMN_NAME_PACOTE, aplicativo.getPacote());
        values.put(FeedReaderAplicativo.FeedEntry.COLUMN_NAME_ATIVO, aplicativo.getAtivo());

        String selection =  FeedReaderAplicativo.FeedEntry.COLUMN_NAME_ID         + " = ? AND " +
                            FeedReaderAplicativo.FeedEntry.COLUMN_NAME_ID_SISTEMA + " = ?";

        String[] selectionArgs = { Long.toString(aplicativo.getId()), Long.toString(aplicativo.getIdSistema()) };

        rowsUpdate = daoFactory.getFactry(context).createAplicativo().atualizar(values, selection, selectionArgs);

        return rowsUpdate;
    }

    @Override
    public int atualizar(ConfiguracaoTempoAplicativo configuracaoTempoAplicativo) {
        int rowsUpdate;
        DAOFactoryCreator daoFactory = new DAOFactoryCreator();

        ContentValues values = new ContentValues();
        values.put(FeedReaderConfiguracaoTempoAplicativo.FeedEntry.COLUMN_NAME_ID, configuracaoTempoAplicativo.getId());
        values.put(FeedReaderConfiguracaoTempoAplicativo.FeedEntry.COLUMN_NAME_ID_APLICATIVO, configuracaoTempoAplicativo.getIdAplicativo());
        values.put(FeedReaderConfiguracaoTempoAplicativo.FeedEntry.COLUMN_NAME_TEMPO_DIARIO, configuracaoTempoAplicativo.getTempoDiario());
        values.put(FeedReaderConfiguracaoTempoAplicativo.FeedEntry.COLUMN_NAME_TEMPO_CONTINUO, configuracaoTempoAplicativo.getTempoContinuo());

        String selection =  FeedReaderConfiguracaoTempoAplicativo.FeedEntry.COLUMN_NAME_ID         + " = ? AND " +
                            FeedReaderConfiguracaoTempoAplicativo.FeedEntry.COLUMN_NAME_ID_APLICATIVO + " = ?";

        String[] selectionArgs = { Long.toString(configuracaoTempoAplicativo.getId()), Long.toString(configuracaoTempoAplicativo.getIdAplicativo()) };

        rowsUpdate = daoFactory.getFactry(context).createConfiguracaoTempoAplicativo().atualizar(values, selection, selectionArgs);

        return rowsUpdate;
    }

    @Override
    public int atualizar(ConfiguracaoTempoSistema configuracaoTempoSistema) {
        int rowsUpdate;
        DAOFactoryCreator daoFactory = new DAOFactoryCreator();

        ContentValues values = new ContentValues();
        values.put(FeedReaderConfiguracaoTempoSistema.FeedEntry.COLUMN_NAME_ID, configuracaoTempoSistema.getId());
        values.put(FeedReaderConfiguracaoTempoSistema.FeedEntry.COLUMN_NAME_ID_SISTEMA, configuracaoTempoSistema.getIdSistema());
        values.put(FeedReaderConfiguracaoTempoSistema.FeedEntry.COLUMN_NAME_TEMPO_DIARIO, configuracaoTempoSistema.getTempoDiario());
        values.put(FeedReaderConfiguracaoTempoSistema.FeedEntry.COLUMN_NAME_TEMPO_CONTINUO, configuracaoTempoSistema.getTempoContinuo());

        String selection =  FeedReaderConfiguracaoTempoSistema.FeedEntry.COLUMN_NAME_ID         + " = ? AND " +
                            FeedReaderConfiguracaoTempoSistema.FeedEntry.COLUMN_NAME_ID_SISTEMA + " = ?";

        String[] selectionArgs = { Long.toString(configuracaoTempoSistema.getId()), Long.toString(configuracaoTempoSistema.getIdSistema()) };

        rowsUpdate = daoFactory.getFactry(context).createConfiguracaoTempoSistema().atualizar(values, selection, selectionArgs);

        return rowsUpdate;
    }

    @Override
    public int atualizar(DadosUsoAplicativo dadosUsoAplicativo) {
        int rowsUpdate;
        DAOFactoryCreator daoFactory = new DAOFactoryCreator();

        ContentValues values = new ContentValues();
        values.put(FeedReaderDadosUsoAplicativo.FeedEntry.COLUMN_NAME_ID, dadosUsoAplicativo.getId());
        values.put(FeedReaderDadosUsoAplicativo.FeedEntry.COLUMN_NAME_ID_APLICATIVO, dadosUsoAplicativo.getIdAplicativo());
        values.put(FeedReaderDadosUsoAplicativo.FeedEntry.COLUMN_NAME_DATA_INICIAL, Util.formatarDataHoraPara(dadosUsoAplicativo.getDataInicial()));
        values.put(FeedReaderDadosUsoAplicativo.FeedEntry.COLUMN_NAME_DATA_FINAL, Util.formatarDataHoraPara(dadosUsoAplicativo.getDataFinal()));

        String selection =  FeedReaderDadosUsoAplicativo.FeedEntry.COLUMN_NAME_ID         + " = ? AND " +
                            FeedReaderDadosUsoAplicativo.FeedEntry.COLUMN_NAME_ID_APLICATIVO + " = ?";

        String[] selectionArgs = { Long.toString(dadosUsoAplicativo.getId()), Long.toString(dadosUsoAplicativo.getIdAplicativo()) };

        rowsUpdate = daoFactory.getFactry(context).createDadosUsoAplicativo().atualizar(values, selection, selectionArgs);

        return rowsUpdate;
    }

    @Override
    public int atualizar(DadosUsoSistema dadosUsoSistema) {
        int rowsUpdate;
        DAOFactoryCreator daoFactory = new DAOFactoryCreator();

        ContentValues values = new ContentValues();
        values.put(FeedReaderDadosUsoSistema.FeedEntry.COLUMN_NAME_ID, dadosUsoSistema.getId());
        values.put(FeedReaderDadosUsoSistema.FeedEntry.COLUMN_NAME_ID_SISTEMA, dadosUsoSistema.getIdSistema());
        values.put(FeedReaderDadosUsoSistema.FeedEntry.COLUMN_NAME_DATA_INICIAL, Util.formatarDataHoraPara(dadosUsoSistema.getDataInicial()));
        values.put(FeedReaderDadosUsoSistema.FeedEntry.COLUMN_NAME_DATA_FINAL, Util.formatarDataHoraPara(dadosUsoSistema.getDataFinal()));

        String selection =  FeedReaderDadosUsoSistema.FeedEntry.COLUMN_NAME_ID         + " = ? AND " +
                            FeedReaderDadosUsoSistema.FeedEntry.COLUMN_NAME_ID_SISTEMA + " = ?";

        String[] selectionArgs = { Long.toString(dadosUsoSistema.getId()), Long.toString(dadosUsoSistema.getIdSistema()) };

        rowsUpdate = daoFactory.getFactry(context).createDadosUsoSistema().atualizar(values, selection, selectionArgs);

        return rowsUpdate;
    }

    @Override
    public int atualizar(NotificacaoAplicativo notificacaoAplicativo) {
        int rowsUpdate;
        DAOFactoryCreator daoFactory = new DAOFactoryCreator();

        ContentValues values = new ContentValues();
        values.put(FeedReaderNotificacaoAplicativo.FeedEntry.COLUMN_NAME_ID, notificacaoAplicativo.getId());
        values.put(FeedReaderNotificacaoAplicativo.FeedEntry.COLUMN_NAME_ID_APLICATIVO, notificacaoAplicativo.getIdAplicativo());
        values.put(FeedReaderNotificacaoAplicativo.FeedEntry.COLUMN_NAME_ID_CONFIGURACAO, notificacaoAplicativo.getIdConfiguracao());
        values.put(FeedReaderNotificacaoAplicativo.FeedEntry.COLUMN_NAME_DATA, Util.formatarDataPara(notificacaoAplicativo.getData()));
        values.put(FeedReaderNotificacaoAplicativo.FeedEntry.COLUMN_NAME_TITULO, notificacaoAplicativo.getTitulo());
        values.put(FeedReaderNotificacaoAplicativo.FeedEntry.COLUMN_NAME_DESCRICAO, notificacaoAplicativo.getDescricao());
        values.put(FeedReaderNotificacaoAplicativo.FeedEntry.COLUMN_NAME_STATUS, notificacaoAplicativo.getStatus());

        String selection =  FeedReaderNotificacaoAplicativo.FeedEntry.COLUMN_NAME_ID              + " = ? AND " +
                            FeedReaderNotificacaoAplicativo.FeedEntry.COLUMN_NAME_ID_APLICATIVO   + " = ? AND " +
                            FeedReaderNotificacaoAplicativo.FeedEntry.COLUMN_NAME_ID_CONFIGURACAO + " = ?";

        String[] selectionArgs = { Long.toString(notificacaoAplicativo.getId()), Long.toString(notificacaoAplicativo.getIdAplicativo()), Long.toString(notificacaoAplicativo.getIdConfiguracao()) };

        rowsUpdate = daoFactory.getFactry(context).createNotificacaoAplicativo().atualizar(values, selection, selectionArgs);

        return rowsUpdate;
    }

    @Override
    public int atualizar(NotificacaoSistema notificacaoSistema) {
        int rowsUpdate;
        DAOFactoryCreator daoFactory = new DAOFactoryCreator();

        ContentValues values = new ContentValues();
        values.put(FeedReaderNotificacaoSistema.FeedEntry.COLUMN_NAME_ID, notificacaoSistema.getId());
        values.put(FeedReaderNotificacaoSistema.FeedEntry.COLUMN_NAME_ID_SISTEMA, notificacaoSistema.getIdSistema());
        values.put(FeedReaderNotificacaoSistema.FeedEntry.COLUMN_NAME_ID_CONFIGURACAO, notificacaoSistema.getIdConfiguracao());
        values.put(FeedReaderNotificacaoSistema.FeedEntry.COLUMN_NAME_DATA, Util.formatarDataPara(notificacaoSistema.getData()));
        values.put(FeedReaderNotificacaoSistema.FeedEntry.COLUMN_NAME_TITULO, notificacaoSistema.getTitulo());
        values.put(FeedReaderNotificacaoSistema.FeedEntry.COLUMN_NAME_DESCRICAO, notificacaoSistema.getDescricao());
        values.put(FeedReaderNotificacaoSistema.FeedEntry.COLUMN_NAME_STATUS, notificacaoSistema.getStatus());

        String selection =  FeedReaderNotificacaoSistema.FeedEntry.COLUMN_NAME_ID              + " = ? AND " +
                            FeedReaderNotificacaoSistema.FeedEntry.COLUMN_NAME_ID_SISTEMA   + " = ? AND " +
                            FeedReaderNotificacaoSistema.FeedEntry.COLUMN_NAME_ID_CONFIGURACAO + " = ?";

        String[] selectionArgs = { Long.toString(notificacaoSistema.getId()), Long.toString(notificacaoSistema.getIdSistema()), Long.toString(notificacaoSistema.getIdConfiguracao()) };

        rowsUpdate = daoFactory.getFactry(context).createNotificacaoSistema().atualizar(values, selection, selectionArgs);

        return rowsUpdate;
    }

}
