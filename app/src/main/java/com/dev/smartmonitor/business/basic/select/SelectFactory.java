package com.dev.smartmonitor.business.basic.select;

import android.app.Activity;
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
import com.dev.smartmonitor.view.view.CustomDialogMensagem;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class SelectFactory implements ISelectFactory {

    private Context context;

    public SelectFactory(Context context){
        this.context = context;
    }

    @Override
    public List<Sistema> buscarSistemaAll() {
        List<Sistema>  sAll = new LinkedList<>();
        DAOFactoryCreator daoFactory = new DAOFactoryCreator();

        String[] projection = {
            FeedReaderSistema.FeedEntry.COLUMN_NAME_ID
        };

        sAll = daoFactory.getFactry(context).createSistema().buscar(projection, null, null, null, null, null);

        return sAll;
    }

    @Override
    public Sistema buscarSistemaById(long id) {
        List<Sistema>  sAll = new LinkedList<>();
        DAOFactoryCreator daoFactory = new DAOFactoryCreator();

        String[] projection = {
            FeedReaderSistema.FeedEntry.COLUMN_NAME_ID
        };

        String selection =  FeedReaderSistema.FeedEntry.COLUMN_NAME_ID   + " = ?";

        String[] selectionArgs = { Long.toString(id) };

        sAll = daoFactory.getFactry(context).createSistema().buscar(projection, selection, selectionArgs, null, null, null);

        return (sAll.size() > 0 ? sAll.get(0) : null);
    }

    @Override
    public List<ChecagemSistema> buscarChecagemSistemaAll() {
        List<ChecagemSistema>  csAll = new LinkedList<>();
        DAOFactoryCreator daoFactory = new DAOFactoryCreator();

        String[] projection = {
            FeedReaderChecagemSistema.FeedEntry.COLUMN_NAME_ID,
            FeedReaderChecagemSistema.FeedEntry.COLUMN_NAME_ID_SISTEMA,
            FeedReaderChecagemSistema.FeedEntry.COLUMN_NAME_DATA,
            FeedReaderChecagemSistema.FeedEntry.COLUMN_NAME_QUANTIDADE
        };

        csAll = daoFactory.getFactry(context).createChecagemSistema().buscar(projection, null, null, null, null, null);

        return csAll;
    }

    @Override
    public ChecagemSistema buscarChecagemSistemaById(long id, long idSistema) {
        List<ChecagemSistema>  csAll;
        DAOFactoryCreator daoFactory = new DAOFactoryCreator();

        String[] projection = {
            FeedReaderChecagemSistema.FeedEntry.COLUMN_NAME_ID,
            FeedReaderChecagemSistema.FeedEntry.COLUMN_NAME_ID_SISTEMA,
            FeedReaderChecagemSistema.FeedEntry.COLUMN_NAME_DATA,
            FeedReaderChecagemSistema.FeedEntry.COLUMN_NAME_QUANTIDADE
        };

        String selection =  FeedReaderChecagemSistema.FeedEntry.COLUMN_NAME_ID   + " = ? AND " + FeedReaderChecagemSistema.FeedEntry.COLUMN_NAME_ID_SISTEMA + " = ?";

        String[] selectionArgs = { Long.toString(id), Long.toString(idSistema) };

        csAll = daoFactory.getFactry(context).createChecagemSistema().buscar(projection, selection, selectionArgs, null, null, null);

        return (csAll.size() > 0 ? csAll.get(0) : null);
    }

    @Override
    public ChecagemSistema buscarChecagemSistemaByData(Date data) {
        List<ChecagemSistema>  csAll;
        DAOFactoryCreator daoFactory = new DAOFactoryCreator();

        String[] projection = {
                FeedReaderChecagemSistema.FeedEntry.COLUMN_NAME_ID,
                FeedReaderChecagemSistema.FeedEntry.COLUMN_NAME_ID_SISTEMA,
                FeedReaderChecagemSistema.FeedEntry.COLUMN_NAME_DATA,
                FeedReaderChecagemSistema.FeedEntry.COLUMN_NAME_QUANTIDADE
        };

        String selection = FeedReaderChecagemSistema.FeedEntry.COLUMN_NAME_DATA + " = ?";

        String[] selectionArgs = { Long.toString(Util.formatarDataPara(data)) };

        csAll = daoFactory.getFactry(context).createChecagemSistema().buscar(projection, selection, selectionArgs, null, null, null);

        return (csAll.size() > 0 ? csAll.get(0) : null);
    }

    @Override
    public List<ChecagemSistema> buscarChecagemSistemaByData(Date dataInicial, Date dataFinal) {
        List<ChecagemSistema>  csAll;
        DAOFactoryCreator daoFactory = new DAOFactoryCreator();

        String[] projection = {
                FeedReaderChecagemSistema.FeedEntry.COLUMN_NAME_ID,
                FeedReaderChecagemSistema.FeedEntry.COLUMN_NAME_ID_SISTEMA,
                FeedReaderChecagemSistema.FeedEntry.COLUMN_NAME_DATA,
                FeedReaderChecagemSistema.FeedEntry.COLUMN_NAME_QUANTIDADE
        };

        String selection = FeedReaderChecagemSistema.FeedEntry.COLUMN_NAME_DATA + " BETWEEN ? AND ?";

        String[] selectionArgs = { Long.toString(Util.formatarDataPara(dataInicial)), Long.toString(Util.formatarDataPara(dataFinal)) };

        csAll = daoFactory.getFactry(context).createChecagemSistema().buscar(projection, selection, selectionArgs, null, null, null);

        return csAll;
    }

    @Override
    public List<Aplicativo> buscarAplicativoAll(String ativo) {
        List<Aplicativo>  aAll = new LinkedList<>();
        DAOFactoryCreator daoFactory = new DAOFactoryCreator();

        String[] projection = {
            FeedReaderAplicativo.FeedEntry.COLUMN_NAME_ID,
            FeedReaderAplicativo.FeedEntry.COLUMN_NAME_ID_SISTEMA,
            FeedReaderAplicativo.FeedEntry.COLUMN_NAME_NOME,
            FeedReaderAplicativo.FeedEntry.COLUMN_NAME_ATIVO
        };

        String selection =  FeedReaderAplicativo.FeedEntry.COLUMN_NAME_ATIVO + " = ?";

        String[] selectionArgs = { ativo };

        aAll = daoFactory.getFactry(context).createAplicativo().buscar(projection, (ativo.isEmpty() ? null : selection),  (ativo.isEmpty() ? null : selectionArgs), null, null, null);

        return aAll;
    }

    @Override
    public Aplicativo buscarAplicativoById(long id, long idSistema) {
        List<Aplicativo>  aAll;
        DAOFactoryCreator daoFactory = new DAOFactoryCreator();

        String[] projection = {
                FeedReaderAplicativo.FeedEntry.COLUMN_NAME_ID,
                FeedReaderAplicativo.FeedEntry.COLUMN_NAME_ID_SISTEMA,
                FeedReaderAplicativo.FeedEntry.COLUMN_NAME_NOME,
                FeedReaderAplicativo.FeedEntry.COLUMN_NAME_ATIVO
        };

        String selection =  FeedReaderAplicativo.FeedEntry.COLUMN_NAME_ID + " = ? AND " +
                            FeedReaderAplicativo.FeedEntry.COLUMN_NAME_ID_SISTEMA + " = ?";

        String[] selectionArgs = { Long.toString(id), Long.toString(idSistema) };

        aAll = daoFactory.getFactry(context).createAplicativo().buscar(projection, selection, selectionArgs, null, null, null);

        return (aAll.size() > 0 ? aAll.get(0) : null);
    }

    @Override
    public Aplicativo buscarAplicativoByIdAplicativo(long id) {
        List<Aplicativo>  aAll;
        DAOFactoryCreator daoFactory = new DAOFactoryCreator();

        String[] projection = {
                FeedReaderAplicativo.FeedEntry.COLUMN_NAME_ID,
                FeedReaderAplicativo.FeedEntry.COLUMN_NAME_ID_SISTEMA,
                FeedReaderAplicativo.FeedEntry.COLUMN_NAME_NOME,
                FeedReaderAplicativo.FeedEntry.COLUMN_NAME_ATIVO
        };

        String selection =  FeedReaderAplicativo.FeedEntry.COLUMN_NAME_ID + " = ?";

        String[] selectionArgs = { Long.toString(id) };

        aAll = daoFactory.getFactry(context).createAplicativo().buscar(projection, selection, selectionArgs, null, null, null);

        return (aAll.size() > 0 ? aAll.get(0) : null);
    }

    @Override
    public Aplicativo buscarAplicativoByNome(String nome) {
        List<Aplicativo>  aAll;
        DAOFactoryCreator daoFactory = new DAOFactoryCreator();

        String[] projection = {
                FeedReaderAplicativo.FeedEntry.COLUMN_NAME_ID,
                FeedReaderAplicativo.FeedEntry.COLUMN_NAME_ID_SISTEMA,
                FeedReaderAplicativo.FeedEntry.COLUMN_NAME_NOME,
                FeedReaderAplicativo.FeedEntry.COLUMN_NAME_ATIVO
        };

        String selection =  FeedReaderAplicativo.FeedEntry.COLUMN_NAME_NOME + " = ?";

        String[] selectionArgs = { nome };

        aAll = daoFactory.getFactry(context).createAplicativo().buscar(projection, selection, selectionArgs, null, null, null);

        return (aAll.size() > 0 ? aAll.get(0) : null);
    }

    @Override
    public List<ConfiguracaoTempoAplicativo> buscarConfiguracaoTempoAplicativoAll() {
        List<ConfiguracaoTempoAplicativo>  ctaAll = new LinkedList<>();
        DAOFactoryCreator daoFactory = new DAOFactoryCreator();

        String[] projection = {
                FeedReaderConfiguracaoTempoAplicativo.FeedEntry.COLUMN_NAME_ID,
                FeedReaderConfiguracaoTempoAplicativo.FeedEntry.COLUMN_NAME_ID_APLICATIVO,
                FeedReaderConfiguracaoTempoAplicativo.FeedEntry.COLUMN_NAME_TEMPO_DIARIO,
                FeedReaderConfiguracaoTempoAplicativo.FeedEntry.COLUMN_NAME_TEMPO_CONTINUO
        };

        ctaAll = daoFactory.getFactry(context).createConfiguracaoTempoAplicativo().buscar(projection, null, null, null, null, null);

        return ctaAll;
    }

    @Override
    public ConfiguracaoTempoAplicativo buscarConfiguracaoTempoAplicativoById(long id, long idAplicativo) {
        List<ConfiguracaoTempoAplicativo>  ctaAll;
        DAOFactoryCreator daoFactory = new DAOFactoryCreator();

        String[] projection = {
                FeedReaderConfiguracaoTempoAplicativo.FeedEntry.COLUMN_NAME_ID,
                FeedReaderConfiguracaoTempoAplicativo.FeedEntry.COLUMN_NAME_ID_APLICATIVO,
                FeedReaderConfiguracaoTempoAplicativo.FeedEntry.COLUMN_NAME_TEMPO_DIARIO,
                FeedReaderConfiguracaoTempoAplicativo.FeedEntry.COLUMN_NAME_TEMPO_CONTINUO
        };

        String selection =  FeedReaderConfiguracaoTempoAplicativo.FeedEntry.COLUMN_NAME_ID + " = ? AND " +
                            FeedReaderConfiguracaoTempoAplicativo.FeedEntry.COLUMN_NAME_ID_APLICATIVO + " = ?";

        String[] selectionArgs = { Long.toString(id), Long.toString(idAplicativo) };

        ctaAll = daoFactory.getFactry(context).createConfiguracaoTempoAplicativo().buscar(projection, selection, selectionArgs, null, null, null);

        return (ctaAll.size() > 0 ? ctaAll.get(0) : null);
    }

    @Override
    public ConfiguracaoTempoAplicativo buscarConfiguracaoTempoAplicativoByIdAplicativo(long idAplicativo) {
        List<ConfiguracaoTempoAplicativo>  ctaAll;
        DAOFactoryCreator daoFactory = new DAOFactoryCreator();

        String[] projection = {
                FeedReaderConfiguracaoTempoAplicativo.FeedEntry.COLUMN_NAME_ID,
                FeedReaderConfiguracaoTempoAplicativo.FeedEntry.COLUMN_NAME_ID_APLICATIVO,
                FeedReaderConfiguracaoTempoAplicativo.FeedEntry.COLUMN_NAME_TEMPO_DIARIO,
                FeedReaderConfiguracaoTempoAplicativo.FeedEntry.COLUMN_NAME_TEMPO_CONTINUO
        };

        String selection =  FeedReaderConfiguracaoTempoAplicativo.FeedEntry.COLUMN_NAME_ID_APLICATIVO + " = ?";

        String[] selectionArgs = { Long.toString(idAplicativo) };

        ctaAll = daoFactory.getFactry(context).createConfiguracaoTempoAplicativo().buscar(projection, selection, selectionArgs, null, null, null);

        return (ctaAll.size() > 0 ? ctaAll.get(0) : null);
    }

    @Override
    public List<ConfiguracaoTempoSistema> buscarConfiguracaoTempoSistemaAll() {
        List<ConfiguracaoTempoSistema>  ctsAll = new LinkedList<>();
        DAOFactoryCreator daoFactory = new DAOFactoryCreator();

        String[] projection = {
                FeedReaderConfiguracaoTempoSistema.FeedEntry.COLUMN_NAME_ID,
                FeedReaderConfiguracaoTempoSistema.FeedEntry.COLUMN_NAME_ID_SISTEMA,
                FeedReaderConfiguracaoTempoSistema.FeedEntry.COLUMN_NAME_TEMPO_DIARIO,
                FeedReaderConfiguracaoTempoSistema.FeedEntry.COLUMN_NAME_TEMPO_CONTINUO
        };

        ctsAll = daoFactory.getFactry(context).createConfiguracaoTempoSistema().buscar(projection, null, null, null, null, null);

        return ctsAll;
    }

    @Override
    public ConfiguracaoTempoSistema buscarConfiguracaoTempoSistemaById(long id, long idSistema) {
        List<ConfiguracaoTempoSistema>  ctsAll;
        DAOFactoryCreator daoFactory = new DAOFactoryCreator();

        String[] projection = {
                FeedReaderConfiguracaoTempoSistema.FeedEntry.COLUMN_NAME_ID,
                FeedReaderConfiguracaoTempoSistema.FeedEntry.COLUMN_NAME_ID_SISTEMA,
                FeedReaderConfiguracaoTempoSistema.FeedEntry.COLUMN_NAME_TEMPO_DIARIO,
                FeedReaderConfiguracaoTempoSistema.FeedEntry.COLUMN_NAME_TEMPO_CONTINUO
        };

        String selection =  FeedReaderConfiguracaoTempoSistema.FeedEntry.COLUMN_NAME_ID + " = ? AND " +
                            FeedReaderConfiguracaoTempoSistema.FeedEntry.COLUMN_NAME_ID_SISTEMA + " = ?";

        String[] selectionArgs = { Long.toString(id), Long.toString(idSistema) };

        ctsAll = daoFactory.getFactry(context).createConfiguracaoTempoSistema().buscar(projection, selection, selectionArgs, null, null, null);

        return (ctsAll.size() > 0 ? ctsAll.get(0) : null);
    }

    @Override
    public ConfiguracaoTempoSistema buscarConfiguracaoTempoSistemaByIdSistema(long idSistema) {
        List<ConfiguracaoTempoSistema>  ctsAll;
        DAOFactoryCreator daoFactory = new DAOFactoryCreator();

        String[] projection = {
                FeedReaderConfiguracaoTempoSistema.FeedEntry.COLUMN_NAME_ID,
                FeedReaderConfiguracaoTempoSistema.FeedEntry.COLUMN_NAME_ID_SISTEMA,
                FeedReaderConfiguracaoTempoSistema.FeedEntry.COLUMN_NAME_TEMPO_DIARIO,
                FeedReaderConfiguracaoTempoSistema.FeedEntry.COLUMN_NAME_TEMPO_CONTINUO
        };

        String selection =  FeedReaderConfiguracaoTempoSistema.FeedEntry.COLUMN_NAME_ID_SISTEMA + " = ?";

        String[] selectionArgs = { Long.toString(idSistema) };

        ctsAll = daoFactory.getFactry(context).createConfiguracaoTempoSistema().buscar(projection, selection, selectionArgs, null, null, null);

        return (ctsAll.size() > 0 ? ctsAll.get(0) : null);
    }

    @Override
    public List<DadosUsoAplicativo> buscarDadosUsoAplicativoAll() {
        List<DadosUsoAplicativo>  duaAll = new LinkedList<>();
        DAOFactoryCreator daoFactory = new DAOFactoryCreator();

        String[] projection = {
                FeedReaderDadosUsoAplicativo.FeedEntry.COLUMN_NAME_ID,
                FeedReaderDadosUsoAplicativo.FeedEntry.COLUMN_NAME_ID_APLICATIVO,
                FeedReaderDadosUsoAplicativo.FeedEntry.COLUMN_NAME_DATA_INICIAL,
                FeedReaderDadosUsoAplicativo.FeedEntry.COLUMN_NAME_DATA_FINAL
        };

        duaAll = daoFactory.getFactry(context).createDadosUsoAplicativo().buscar(projection, null, null, null, null, null);

        return duaAll;
    }

    @Override
    public DadosUsoAplicativo buscarDadosUsoAplicativoById(long id, long idAplicativo) {
        List<DadosUsoAplicativo>  duaAll;
        DAOFactoryCreator daoFactory = new DAOFactoryCreator();

        String[] projection = {
                FeedReaderDadosUsoAplicativo.FeedEntry.COLUMN_NAME_ID,
                FeedReaderDadosUsoAplicativo.FeedEntry.COLUMN_NAME_ID_APLICATIVO,
                FeedReaderDadosUsoAplicativo.FeedEntry.COLUMN_NAME_DATA_INICIAL,
                FeedReaderDadosUsoAplicativo.FeedEntry.COLUMN_NAME_DATA_FINAL
        };

        String selection =  FeedReaderDadosUsoAplicativo.FeedEntry.COLUMN_NAME_ID + " = ? AND " +
                            FeedReaderDadosUsoAplicativo.FeedEntry.COLUMN_NAME_ID_APLICATIVO + " = ?";;

        String[] selectionArgs = { Long.toString(id), Long.toString(idAplicativo) };

        duaAll = daoFactory.getFactry(context).createDadosUsoAplicativo().buscar(projection, selection, selectionArgs, null, null, null);

        return (duaAll.size() > 0 ? duaAll.get(0) : null);
    }

    @Override
    public List<DadosUsoAplicativo> buscarDadosUsoAplicativoByIdAplicativo(long idAplicativo) {
        List<DadosUsoAplicativo>  duaAll;
        DAOFactoryCreator daoFactory = new DAOFactoryCreator();

        String[] projection = {
                FeedReaderDadosUsoAplicativo.FeedEntry.COLUMN_NAME_ID,
                FeedReaderDadosUsoAplicativo.FeedEntry.COLUMN_NAME_ID_APLICATIVO,
                FeedReaderDadosUsoAplicativo.FeedEntry.COLUMN_NAME_DATA_INICIAL,
                FeedReaderDadosUsoAplicativo.FeedEntry.COLUMN_NAME_DATA_FINAL
        };

        String selection =  FeedReaderDadosUsoAplicativo.FeedEntry.COLUMN_NAME_ID_APLICATIVO + " = ?";;

        String[] selectionArgs = { Long.toString(idAplicativo) };

        duaAll = daoFactory.getFactry(context).createDadosUsoAplicativo().buscar(projection, selection, selectionArgs, null, null, null);

        return duaAll;
    }

    @Override
    public List<DadosUsoAplicativo> buscarDadosUsoAplicativoByData(Date dataInicial, Date dataFinal) {
        List<DadosUsoAplicativo>  duaAll;
        DAOFactoryCreator daoFactory = new DAOFactoryCreator();

        String[] projection = {
                FeedReaderDadosUsoAplicativo.FeedEntry.COLUMN_NAME_ID,
                FeedReaderDadosUsoAplicativo.FeedEntry.COLUMN_NAME_ID_APLICATIVO,
                FeedReaderDadosUsoAplicativo.FeedEntry.COLUMN_NAME_DATA_INICIAL,
                FeedReaderDadosUsoAplicativo.FeedEntry.COLUMN_NAME_DATA_FINAL
        };

        String selection =  FeedReaderDadosUsoAplicativo.FeedEntry.COLUMN_NAME_DATA_INICIAL + " BETWEEN ? AND ? OR " +
                            FeedReaderDadosUsoAplicativo.FeedEntry.COLUMN_NAME_DATA_FINAL + " BETWEEN ? AND ?";

        String[] selectionArgs = { Long.toString(Util.formatarDataHoraPara(dataInicial)), Long.toString(Util.formatarDataHoraPara(dataFinal)), Long.toString(Util.formatarDataHoraPara(dataInicial)), Long.toString(Util.formatarDataHoraPara(dataFinal)) };

        duaAll = daoFactory.getFactry(context).createDadosUsoAplicativo().buscar(projection, selection, selectionArgs, null, null, null);

        return duaAll;
    }

    @Override
    public List<DadosUsoAplicativo> buscarDadosUsoAplicativoByDataIdAplicativo(Date dataInicial, Date dataFinal, long idAplicativo) {
        List<DadosUsoAplicativo>  duaAll;
        DAOFactoryCreator daoFactory = new DAOFactoryCreator();

        String[] projection = {
                FeedReaderDadosUsoAplicativo.FeedEntry.COLUMN_NAME_ID,
                FeedReaderDadosUsoAplicativo.FeedEntry.COLUMN_NAME_ID_APLICATIVO,
                FeedReaderDadosUsoAplicativo.FeedEntry.COLUMN_NAME_DATA_INICIAL,
                FeedReaderDadosUsoAplicativo.FeedEntry.COLUMN_NAME_DATA_FINAL
        };

        String selection =  "(" +   FeedReaderDadosUsoAplicativo.FeedEntry.COLUMN_NAME_DATA_INICIAL + " BETWEEN ? AND ? OR " +
                                    FeedReaderDadosUsoAplicativo.FeedEntry.COLUMN_NAME_DATA_FINAL + " BETWEEN ? AND ? ) AND " +
                                    FeedReaderDadosUsoAplicativo.FeedEntry.COLUMN_NAME_ID_APLICATIVO + " = ?";

        String[] selectionArgs = { Long.toString(Util.formatarDataHoraPara(dataInicial)), Long.toString(Util.formatarDataHoraPara(dataFinal)), Long.toString(Util.formatarDataHoraPara(dataInicial)), Long.toString(Util.formatarDataHoraPara(dataFinal)), Long.toString(idAplicativo) };

        duaAll = daoFactory.getFactry(context).createDadosUsoAplicativo().buscar(projection, selection, selectionArgs, null, null, null);

        return duaAll;
    }

    @Override
    public List<DadosUsoSistema> buscarDadosUsoSistemaAll() {
        List<DadosUsoSistema>  dusAll = new LinkedList<>();
        DAOFactoryCreator daoFactory = new DAOFactoryCreator();

        String[] projection = {
                FeedReaderDadosUsoSistema.FeedEntry.COLUMN_NAME_ID,
                FeedReaderDadosUsoSistema.FeedEntry.COLUMN_NAME_ID_SISTEMA,
                FeedReaderDadosUsoSistema.FeedEntry.COLUMN_NAME_DATA_INICIAL,
                FeedReaderDadosUsoSistema.FeedEntry.COLUMN_NAME_DATA_FINAL
        };

        dusAll = daoFactory.getFactry(context).createDadosUsoSistema().buscar(projection, null, null, null, null, null);

        return dusAll;
    }

    @Override
    public List<DadosUsoSistema> buscarDadosUsoSistemaByData(Date dataInicial, Date dataFinal) {
        List<DadosUsoSistema>  dusAll;
        DAOFactoryCreator daoFactory = new DAOFactoryCreator();

        String[] projection = {
                FeedReaderDadosUsoSistema.FeedEntry.COLUMN_NAME_ID,
                FeedReaderDadosUsoSistema.FeedEntry.COLUMN_NAME_ID_SISTEMA,
                FeedReaderDadosUsoSistema.FeedEntry.COLUMN_NAME_DATA_INICIAL,
                FeedReaderDadosUsoSistema.FeedEntry.COLUMN_NAME_DATA_FINAL
        };

        String selection =  FeedReaderDadosUsoSistema.FeedEntry.COLUMN_NAME_DATA_INICIAL + " BETWEEN ? AND ? OR " +
                            FeedReaderDadosUsoSistema.FeedEntry.COLUMN_NAME_DATA_FINAL   + " BETWEEN ? AND ?";

        String[] selectionArgs = { Long.toString(Util.formatarDataHoraPara(dataInicial)), Long.toString(Util.formatarDataHoraPara(dataFinal)), Long.toString(Util.formatarDataHoraPara(dataInicial)), Long.toString(Util.formatarDataHoraPara(dataFinal)) };

        dusAll = daoFactory.getFactry(context).createDadosUsoSistema().buscar(projection, selection, selectionArgs, null, null, null);

        return dusAll;
    }

    @Override
    public DadosUsoSistema buscarDadosUsoSistemaById(long id, long idSistema) {
        List<DadosUsoSistema>  dusAll;
        DAOFactoryCreator daoFactory = new DAOFactoryCreator();

        String[] projection = {
                FeedReaderDadosUsoSistema.FeedEntry.COLUMN_NAME_ID,
                FeedReaderDadosUsoSistema.FeedEntry.COLUMN_NAME_ID_SISTEMA,
                FeedReaderDadosUsoSistema.FeedEntry.COLUMN_NAME_DATA_INICIAL,
                FeedReaderDadosUsoSistema.FeedEntry.COLUMN_NAME_DATA_FINAL
        };

        String selection =  FeedReaderDadosUsoSistema.FeedEntry.COLUMN_NAME_ID + " = ? AND " +
                            FeedReaderDadosUsoSistema.FeedEntry.COLUMN_NAME_ID_SISTEMA + " = ?";;

        String[] selectionArgs = { Long.toString(id), Long.toString(idSistema) };

        dusAll = daoFactory.getFactry(context).createDadosUsoSistema().buscar(projection, selection, selectionArgs, null, null, null);

        return (dusAll.size() > 0 ? dusAll.get(0) : null);
    }

    @Override
    public NotificacaoAplicativo buscarNotificacaoAplicativoById(long id, long idAplicativo, long idConfiguracao) {
        List<NotificacaoAplicativo>  naAll;
        DAOFactoryCreator daoFactory = new DAOFactoryCreator();

        String[] projection = {
                FeedReaderNotificacaoAplicativo.FeedEntry.COLUMN_NAME_ID,
                FeedReaderNotificacaoAplicativo.FeedEntry.COLUMN_NAME_ID_APLICATIVO,
                FeedReaderNotificacaoAplicativo.FeedEntry.COLUMN_NAME_ID_CONFIGURACAO,
                FeedReaderNotificacaoAplicativo.FeedEntry.COLUMN_NAME_DATA,
                FeedReaderNotificacaoAplicativo.FeedEntry.COLUMN_NAME_TITULO,
                FeedReaderNotificacaoAplicativo.FeedEntry.COLUMN_NAME_DESCRICAO,
                FeedReaderNotificacaoAplicativo.FeedEntry.COLUMN_NAME_STATUS
        };

        String selection =  FeedReaderNotificacaoAplicativo.FeedEntry.COLUMN_NAME_ID              + " = ? AND " +
                            FeedReaderNotificacaoAplicativo.FeedEntry.COLUMN_NAME_ID_APLICATIVO   + " = ? AND " +
                            FeedReaderNotificacaoAplicativo.FeedEntry.COLUMN_NAME_ID_CONFIGURACAO + " = ?";

        String[] selectionArgs = { Long.toString(id), Long.toString(idAplicativo), Long.toString(idConfiguracao) };

        naAll = daoFactory.getFactry(context).createNotificacaoAplicativo().buscar(projection, selection, selectionArgs, null, null, null);

        return (naAll.size() > 0 ? naAll.get(0) : null);
    }

    @Override
    public List<NotificacaoAplicativo> buscarNotificacaoAplicativoAll(String[] status) {
        List<NotificacaoAplicativo>  naAll = new LinkedList<>();
        DAOFactoryCreator daoFactory = new DAOFactoryCreator();

        String[] projection = {
                FeedReaderNotificacaoAplicativo.FeedEntry.COLUMN_NAME_ID,
                FeedReaderNotificacaoAplicativo.FeedEntry.COLUMN_NAME_ID_APLICATIVO,
                FeedReaderNotificacaoAplicativo.FeedEntry.COLUMN_NAME_ID_CONFIGURACAO,
                FeedReaderNotificacaoAplicativo.FeedEntry.COLUMN_NAME_DATA,
                FeedReaderNotificacaoAplicativo.FeedEntry.COLUMN_NAME_TITULO,
                FeedReaderNotificacaoAplicativo.FeedEntry.COLUMN_NAME_DESCRICAO,
                FeedReaderNotificacaoAplicativo.FeedEntry.COLUMN_NAME_STATUS
        };

        String selection = "";
        if (status != null) {
            selection = FeedReaderNotificacaoAplicativo.FeedEntry.COLUMN_NAME_STATUS + " IN (" + Util.montarIn(status) + ")";
        }

        naAll = daoFactory.getFactry(context).createNotificacaoAplicativo().buscar(projection,  (status == null ? null : selection), null, null, null, null);

        return naAll;
    }

    @Override
    public List<NotificacaoAplicativo> buscarNotificacaoAplicativoByIdAplicativo(String[] status, long idAplicativo) {
        List<NotificacaoAplicativo>  naAll = new LinkedList<>();
        DAOFactoryCreator daoFactory = new DAOFactoryCreator();

        String[] projection = {
                FeedReaderNotificacaoAplicativo.FeedEntry.COLUMN_NAME_ID,
                FeedReaderNotificacaoAplicativo.FeedEntry.COLUMN_NAME_ID_APLICATIVO,
                FeedReaderNotificacaoAplicativo.FeedEntry.COLUMN_NAME_ID_CONFIGURACAO,
                FeedReaderNotificacaoAplicativo.FeedEntry.COLUMN_NAME_DATA,
                FeedReaderNotificacaoAplicativo.FeedEntry.COLUMN_NAME_TITULO,
                FeedReaderNotificacaoAplicativo.FeedEntry.COLUMN_NAME_DESCRICAO,
                FeedReaderNotificacaoAplicativo.FeedEntry.COLUMN_NAME_STATUS
        };

        String selection =  FeedReaderNotificacaoAplicativo.FeedEntry.COLUMN_NAME_ID_APLICATIVO + " = ?" +
                            (status == null ? "" : " AND " + FeedReaderNotificacaoAplicativo.FeedEntry.COLUMN_NAME_STATUS + " IN (" + Util.montarIn(status) + ")");
        String[] selectionArgs = { (Long.toString(idAplicativo)) };


        naAll = daoFactory.getFactry(context).createNotificacaoAplicativo().buscar(projection,  selection, selectionArgs, null, null, null);

        return naAll;
    }

    @Override
    public List<NotificacaoAplicativo> buscarNotificacaoAplicativoByData(String[] status, Date dataInicial, Date dataFinal) {
        List<NotificacaoAplicativo>  naAll = new LinkedList<>();
        DAOFactoryCreator daoFactory = new DAOFactoryCreator();

        String[] projection = {
                FeedReaderNotificacaoAplicativo.FeedEntry.COLUMN_NAME_ID,
                FeedReaderNotificacaoAplicativo.FeedEntry.COLUMN_NAME_ID_APLICATIVO,
                FeedReaderNotificacaoAplicativo.FeedEntry.COLUMN_NAME_ID_CONFIGURACAO,
                FeedReaderNotificacaoAplicativo.FeedEntry.COLUMN_NAME_DATA,
                FeedReaderNotificacaoAplicativo.FeedEntry.COLUMN_NAME_TITULO,
                FeedReaderNotificacaoAplicativo.FeedEntry.COLUMN_NAME_DESCRICAO,
                FeedReaderNotificacaoAplicativo.FeedEntry.COLUMN_NAME_STATUS
        };

        String selection =  FeedReaderNotificacaoAplicativo.FeedEntry.COLUMN_NAME_DATA + " BETWEEN ? AND ?" +
                (status == null ? "" : " AND " + FeedReaderNotificacaoAplicativo.FeedEntry.COLUMN_NAME_STATUS + " IN (" + Util.montarIn(status) + ")");
        String[] selectionArgs = { Long.toString(Util.formatarDataPara(dataInicial)), Long.toString(Util.formatarDataPara(dataFinal)) };


        naAll = daoFactory.getFactry(context).createNotificacaoAplicativo().buscar(projection,  selection, selectionArgs, null, null, null);

        return naAll;
    }

    @Override
    public NotificacaoAplicativo buscarNotificacaoAplicativoByIdAplicativoData(long idAplicativo, Date data) {
        List<NotificacaoAplicativo>  naAll = new LinkedList<>();
        DAOFactoryCreator daoFactory = new DAOFactoryCreator();

        String[] projection = {
                FeedReaderNotificacaoAplicativo.FeedEntry.COLUMN_NAME_ID,
                FeedReaderNotificacaoAplicativo.FeedEntry.COLUMN_NAME_ID_APLICATIVO,
                FeedReaderNotificacaoAplicativo.FeedEntry.COLUMN_NAME_ID_CONFIGURACAO,
                FeedReaderNotificacaoAplicativo.FeedEntry.COLUMN_NAME_DATA,
                FeedReaderNotificacaoAplicativo.FeedEntry.COLUMN_NAME_TITULO,
                FeedReaderNotificacaoAplicativo.FeedEntry.COLUMN_NAME_DESCRICAO,
                FeedReaderNotificacaoAplicativo.FeedEntry.COLUMN_NAME_STATUS
        };

        String selection =  FeedReaderNotificacaoAplicativo.FeedEntry.COLUMN_NAME_ID_APLICATIVO + " = ? AND " +
                            FeedReaderNotificacaoAplicativo.FeedEntry.COLUMN_NAME_DATA          + " = ?";
        String[] selectionArgs = { Long.toString(idAplicativo), Long.toString(Util.formatarDataPara(data)) };


        naAll = daoFactory.getFactry(context).createNotificacaoAplicativo().buscar(projection,  selection, selectionArgs, null, null, null);

        return (naAll.size() > 0 ? naAll.get(0) : null);
    }

    @Override
    public NotificacaoSistema buscarNotificacaoSistemaById(long id, long idSistema, long idConfiguracao) {
        List<NotificacaoSistema>  nsAll;
        DAOFactoryCreator daoFactory = new DAOFactoryCreator();

        String[] projection = {
                FeedReaderNotificacaoSistema.FeedEntry.COLUMN_NAME_ID,
                FeedReaderNotificacaoSistema.FeedEntry.COLUMN_NAME_ID_SISTEMA,
                FeedReaderNotificacaoSistema.FeedEntry.COLUMN_NAME_ID_CONFIGURACAO,
                FeedReaderNotificacaoSistema.FeedEntry.COLUMN_NAME_DATA,
                FeedReaderNotificacaoSistema.FeedEntry.COLUMN_NAME_TITULO,
                FeedReaderNotificacaoSistema.FeedEntry.COLUMN_NAME_DESCRICAO,
                FeedReaderNotificacaoSistema.FeedEntry.COLUMN_NAME_STATUS
        };

        String selection =  FeedReaderNotificacaoSistema.FeedEntry.COLUMN_NAME_ID              + " = ? AND " +
                            FeedReaderNotificacaoSistema.FeedEntry.COLUMN_NAME_ID_SISTEMA      + " = ? AND " +
                            FeedReaderNotificacaoSistema.FeedEntry.COLUMN_NAME_ID_CONFIGURACAO + " = ?";

        String[] selectionArgs = { Long.toString(id), Long.toString(idSistema), Long.toString(idConfiguracao) };

        nsAll = daoFactory.getFactry(context).createNotificacaoSistema().buscar(projection, selection, selectionArgs, null, null, null);

        return (nsAll.size() > 0 ? nsAll.get(0) : null);
    }

    @Override
    public List<NotificacaoSistema> buscarNotificacaoSistemaAll(String[] status) {
        List<NotificacaoSistema>  nsAll = new LinkedList<>();
        DAOFactoryCreator daoFactory = new DAOFactoryCreator();

        String[] projection = {
                FeedReaderNotificacaoSistema.FeedEntry.COLUMN_NAME_ID,
                FeedReaderNotificacaoSistema.FeedEntry.COLUMN_NAME_ID_SISTEMA,
                FeedReaderNotificacaoSistema.FeedEntry.COLUMN_NAME_ID_CONFIGURACAO,
                FeedReaderNotificacaoSistema.FeedEntry.COLUMN_NAME_DATA,
                FeedReaderNotificacaoSistema.FeedEntry.COLUMN_NAME_TITULO,
                FeedReaderNotificacaoSistema.FeedEntry.COLUMN_NAME_DESCRICAO,
                FeedReaderNotificacaoSistema.FeedEntry.COLUMN_NAME_STATUS
        };

        String selection = "";
        if (status != null) {
            selection = FeedReaderNotificacaoSistema.FeedEntry.COLUMN_NAME_STATUS + " IN (" + Util.montarIn(status) + ")";
        }

        nsAll = daoFactory.getFactry(context).createNotificacaoSistema().buscar(projection,  (status == null ? null : selection), null, null, null, null);

        return nsAll;
    }

    @Override
    public List<NotificacaoSistema> buscarNotificacaoSistemaByData(String[] status, Date dataInicial, Date dataFinal) {
        List<NotificacaoSistema>  nsAll = new LinkedList<>();
        DAOFactoryCreator daoFactory = new DAOFactoryCreator();

        String[] projection = {
                FeedReaderNotificacaoSistema.FeedEntry.COLUMN_NAME_ID,
                FeedReaderNotificacaoSistema.FeedEntry.COLUMN_NAME_ID_SISTEMA,
                FeedReaderNotificacaoSistema.FeedEntry.COLUMN_NAME_ID_CONFIGURACAO,
                FeedReaderNotificacaoSistema.FeedEntry.COLUMN_NAME_DATA,
                FeedReaderNotificacaoSistema.FeedEntry.COLUMN_NAME_TITULO,
                FeedReaderNotificacaoSistema.FeedEntry.COLUMN_NAME_DESCRICAO,
                FeedReaderNotificacaoSistema.FeedEntry.COLUMN_NAME_STATUS
        };

        String selection =  FeedReaderNotificacaoSistema.FeedEntry.COLUMN_NAME_DATA + " BETWEEN ? AND ?" +
                            (status == null ? "" : " AND " + FeedReaderNotificacaoSistema.FeedEntry.COLUMN_NAME_STATUS + " IN (" + Util.montarIn(status) + ")");
        String[] selectionArgs = { Long.toString(Util.formatarDataPara(dataInicial)), Long.toString(Util.formatarDataPara(dataFinal)) };


        nsAll = daoFactory.getFactry(context).createNotificacaoSistema().buscar(projection,  selection, selectionArgs, null, null, null);

        return nsAll;
    }

    @Override
    public NotificacaoSistema buscarNotificacaoSistemaByIdSistemaData(long idSistema, Date data) {
        List<NotificacaoSistema>  nsAll = new LinkedList<>();
        DAOFactoryCreator daoFactory = new DAOFactoryCreator();

        String[] projection = {
                FeedReaderNotificacaoSistema.FeedEntry.COLUMN_NAME_ID,
                FeedReaderNotificacaoSistema.FeedEntry.COLUMN_NAME_ID_SISTEMA,
                FeedReaderNotificacaoSistema.FeedEntry.COLUMN_NAME_ID_CONFIGURACAO,
                FeedReaderNotificacaoSistema.FeedEntry.COLUMN_NAME_DATA,
                FeedReaderNotificacaoSistema.FeedEntry.COLUMN_NAME_TITULO,
                FeedReaderNotificacaoSistema.FeedEntry.COLUMN_NAME_DESCRICAO,
                FeedReaderNotificacaoSistema.FeedEntry.COLUMN_NAME_STATUS
        };

        String selection =  FeedReaderNotificacaoSistema.FeedEntry.COLUMN_NAME_ID_SISTEMA  + " = ? AND " +
                            FeedReaderNotificacaoSistema.FeedEntry.COLUMN_NAME_DATA        + " = ? ";
        String[] selectionArgs = { Long.toString(idSistema), Long.toString(Util.formatarDataPara(data)) };

        nsAll = daoFactory.getFactry(context).createNotificacaoSistema().buscar(projection,  selection, selectionArgs, null, null, null);

        return (nsAll.size() > 0 ? nsAll.get(0) : null);
    }

}
