package com.dev.smartmonitor.persistence.dao.idao;

import android.content.ContentValues;

import com.dev.smartmonitor.persistence.dao.model.ConfiguracaoTempoSistema;

import java.util.List;

public interface IConfiguracaoTempoSistemaDAO {

    public long inserir(ConfiguracaoTempoSistema configuracaoTempoSistema);

    public List<ConfiguracaoTempoSistema> buscar(String[] projection, String selection, String[] selectionArgs, String sortOrder, String groupBy, String having);

    public int excluir(String selection, String[] selectionArgs);

    public int atualizar(ContentValues values, String selection, String[] selectionArgs);

}
