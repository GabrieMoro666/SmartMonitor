package com.dev.smartmonitor.persistence.dao.idao;

import android.content.ContentValues;

import com.dev.smartmonitor.persistence.dao.model.ConfiguracaoTempoAplicativo;

import java.util.List;

public interface IConfiguracaoTempoAplicativoDAO {

    public long inserir(ConfiguracaoTempoAplicativo configuracaoTempoAplicativo);

    public List<ConfiguracaoTempoAplicativo> buscar(String[] projection, String selection, String[] selectionArgs, String sortOrder, String groupBy, String having);

    public int excluir(String selection, String[] selectionArgs);

    public int atualizar(ContentValues values, String selection, String[] selectionArgs);
}
