package com.dev.smartmonitor.persistence.dao.idao;

import android.content.ContentValues;

import com.dev.smartmonitor.persistence.dao.model.NotificacaoSistema;

import java.util.List;

public interface INotificacaoSistemaDAO {

    public long inserir(NotificacaoSistema notificacaoSistema);

    public List<NotificacaoSistema> buscar(String[] projection, String selection, String[] selectionArgs, String sortOrder, String groupBy, String having);

    public int excluir(String selection, String[] selectionArgs);

    public int atualizar(ContentValues values, String selection, String[] selectionArgs);

}
