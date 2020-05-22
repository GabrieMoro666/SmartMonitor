package com.dev.smartmonitor.persistence.dao.idao;

import android.content.ContentValues;

import com.dev.smartmonitor.persistence.dao.model.DadosUsoAplicativo;

import java.util.List;

public interface IDadosUsoAplicativoDAO {

    public long inserir(DadosUsoAplicativo dadosUsoAplicativo);

    public List<DadosUsoAplicativo> buscar(String[] projection, String selection, String[] selectionArgs, String sortOrder, String groupBy, String having);

    public int atualizar(ContentValues values, String selection, String[] selectionArgs);

    public int excluir(String selection, String[] selectionArgs);

}
