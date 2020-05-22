package com.dev.smartmonitor.persistence.dao.idao;

import android.content.ContentValues;

import com.dev.smartmonitor.persistence.dao.model.DadosUsoSistema;

import java.util.List;

public interface IDadosUsoSistemaDAO {

    public long inserir(DadosUsoSistema dadosUsoSistema);

    public List<DadosUsoSistema> buscar(String[] projection, String selection, String[] selectionArgs, String sortOrder, String groupBy, String having);

    public int excluir(String selection, String[] selectionArgs);

    public int atualizar(ContentValues values, String selection, String[] selectionArgs);

}
