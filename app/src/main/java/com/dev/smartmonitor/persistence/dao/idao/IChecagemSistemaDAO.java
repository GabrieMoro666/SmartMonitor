package com.dev.smartmonitor.persistence.dao.idao;

import android.content.ContentValues;

import com.dev.smartmonitor.persistence.dao.model.ChecagemSistema;

import java.util.List;

public interface IChecagemSistemaDAO {

    public long inserir(ChecagemSistema checagemSistema);

    public List<ChecagemSistema> buscar(String[] projection, String selection, String[] selectionArgs, String sortOrder, String groupBy, String having);

    public int atualizar(ContentValues values, String selection, String[] selectionArgs);

    public int excluir(String selection, String[] selectionArgs);

}
