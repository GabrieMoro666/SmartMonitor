package com.dev.smartmonitor.persistence.dao.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.dev.smartmonitor.persistence.dao.db.dbHelper.FeedReaderDbHelper;
import com.dev.smartmonitor.persistence.dao.db.feedReader.FeedReaderNotificacaoSistema;
import com.dev.smartmonitor.persistence.dao.idao.INotificacaoSistemaDAO;
import com.dev.smartmonitor.persistence.dao.model.NotificacaoSistema;
import com.dev.smartmonitor.util.Util;

import java.util.LinkedList;
import java.util.List;

public class NotificacaoSistemaDAO implements INotificacaoSistemaDAO {

    private FeedReaderDbHelper dbHelper;

    public NotificacaoSistemaDAO(Context context){
        this.dbHelper = new FeedReaderDbHelper(context);
    }

    @Override
    public long inserir(NotificacaoSistema notificacaoSistema) {
        long newRowId = 0;
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        if (notificacaoSistema.getId() != 0) values.put(FeedReaderNotificacaoSistema.FeedEntry.COLUMN_NAME_ID, notificacaoSistema.getId());
        values.put(FeedReaderNotificacaoSistema.FeedEntry.COLUMN_NAME_ID_CONFIGURACAO, notificacaoSistema.getIdConfiguracao());
        values.put(FeedReaderNotificacaoSistema.FeedEntry.COLUMN_NAME_ID_SISTEMA, notificacaoSistema.getIdSistema());
        values.put(FeedReaderNotificacaoSistema.FeedEntry.COLUMN_NAME_DATA, Util.formatarDataPara(notificacaoSistema.getData()));
        values.put(FeedReaderNotificacaoSistema.FeedEntry.COLUMN_NAME_TITULO, notificacaoSistema.getTitulo());
        values.put(FeedReaderNotificacaoSistema.FeedEntry.COLUMN_NAME_DESCRICAO, notificacaoSistema.getDescricao());
        values.put(FeedReaderNotificacaoSistema.FeedEntry.COLUMN_NAME_STATUS, notificacaoSistema.getStatus());

        newRowId = db.insert(FeedReaderNotificacaoSistema.FeedEntry.TABLE_NAME, null, values);

        return newRowId;
    }

    @Override
    public List<NotificacaoSistema> buscar(String[] projection, String selection, String[] selectionArgs, String sortOrder, String groupBy, String having) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        List<NotificacaoSistema>  nsAll = new LinkedList<>();
        NotificacaoSistema ns = null;

        Cursor cursor = db.query(
                FeedReaderNotificacaoSistema.FeedEntry.TABLE_NAME, // The table to query
                projection,                                           // The array of columns to return (pass null to get all)
                (selection     == null ? null : selection    ),       // The columns for the WHERE clause
                (selectionArgs == null ? null : selectionArgs),       // The values for the WHERE clause
                (groupBy       == null ? null : groupBy      ),       // don't group the rows
                (having        == null ? null : having       ),       // don't filter by row groups
                (sortOrder     == null ? null : sortOrder    )        // The sort order
        );

        while (cursor.moveToNext()) {
            ns = new NotificacaoSistema();

            ns.setId(cursor.getLong(cursor.getColumnIndex(FeedReaderNotificacaoSistema.FeedEntry.COLUMN_NAME_ID)));
            ns.setIdSistema(cursor.getLong(cursor.getColumnIndex(FeedReaderNotificacaoSistema.FeedEntry.COLUMN_NAME_ID_SISTEMA)));
            ns.setIdConfiguracao(cursor.getLong(cursor.getColumnIndex(FeedReaderNotificacaoSistema.FeedEntry.COLUMN_NAME_ID_CONFIGURACAO)));
            ns.setData(Util.formatarDataVolta(cursor.getLong(cursor.getColumnIndex(FeedReaderNotificacaoSistema.FeedEntry.COLUMN_NAME_DATA))));
            ns.setTitulo(cursor.getString(cursor.getColumnIndex(FeedReaderNotificacaoSistema.FeedEntry.COLUMN_NAME_TITULO)));
            ns.setDescricao(cursor.getString(cursor.getColumnIndex(FeedReaderNotificacaoSistema.FeedEntry.COLUMN_NAME_DESCRICAO)));
            ns.setStatus(cursor.getString(cursor.getColumnIndex(FeedReaderNotificacaoSistema.FeedEntry.COLUMN_NAME_STATUS)));

            nsAll.add(ns);
        }
        cursor.close();

        return nsAll;
    }

    @Override
    public int excluir(String selection, String[] selectionArgs) {
        int rowsDeleted;
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        rowsDeleted = db.delete(FeedReaderNotificacaoSistema.FeedEntry.TABLE_NAME, selection, selectionArgs);

        return rowsDeleted;
    }

    @Override
    public int atualizar(ContentValues values, String selection, String[] selectionArgs) {
        int rowsUpdate;
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        rowsUpdate = db.update(
                FeedReaderNotificacaoSistema.FeedEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs);

        return rowsUpdate;
    }

}
