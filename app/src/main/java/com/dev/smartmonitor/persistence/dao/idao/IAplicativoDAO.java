package com.dev.smartmonitor.persistence.dao.idao;

import android.content.ContentValues;

import com.dev.smartmonitor.persistence.dao.model.Aplicativo;

import java.util.List;

public interface IAplicativoDAO {

    public long inserir(Aplicativo aplicativo);

    public List<Aplicativo> buscar(String[] projection, String selection, String[] selectionArgs, String sortOrder, String groupBy, String having);

    public int atualizar(ContentValues values, String selection, String[] selectionArgs);

    public int excluir(String selection, String[] selectionArgs);

}
