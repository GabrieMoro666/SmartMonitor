package com.dev.smartmonitor.persistence.dao.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.dev.smartmonitor.persistence.dao.db.helper.FeedReaderDbHelper;
import com.dev.smartmonitor.persistence.dao.db.feedReader.FeedReaderNotificacaoAplicativo;
import com.dev.smartmonitor.persistence.dao.idao.INotificacaoAplicativoDAO;
import com.dev.smartmonitor.persistence.dao.model.NotificacaoAplicativo;
import com.dev.smartmonitor.util.Util;

import java.util.LinkedList;
import java.util.List;

public class NotificacaoAplicativoDAO implements INotificacaoAplicativoDAO {

    private FeedReaderDbHelper dbHelper;

    public NotificacaoAplicativoDAO(Context context){
        this.dbHelper = new FeedReaderDbHelper(context);
    }

    @Override
    public long inserir(NotificacaoAplicativo notificacaoAplicativo) {
        long newRowId = 0;
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        if (notificacaoAplicativo.getId() != 0) values.put(FeedReaderNotificacaoAplicativo.FeedEntry.COLUMN_NAME_ID, notificacaoAplicativo.getId());
        values.put(FeedReaderNotificacaoAplicativo.FeedEntry.COLUMN_NAME_ID_CONFIGURACAO, notificacaoAplicativo.getIdConfiguracao());
        values.put(FeedReaderNotificacaoAplicativo.FeedEntry.COLUMN_NAME_ID_APLICATIVO, notificacaoAplicativo.getIdAplicativo());
        values.put(FeedReaderNotificacaoAplicativo.FeedEntry.COLUMN_NAME_DATA, Util.formatarDataPara(notificacaoAplicativo.getData()));
        values.put(FeedReaderNotificacaoAplicativo.FeedEntry.COLUMN_NAME_TITULO, notificacaoAplicativo.getTitulo());
        values.put(FeedReaderNotificacaoAplicativo.FeedEntry.COLUMN_NAME_DESCRICAO, notificacaoAplicativo.getDescricao());
        values.put(FeedReaderNotificacaoAplicativo.FeedEntry.COLUMN_NAME_STATUS, notificacaoAplicativo.getStatus());

        newRowId = db.insert(FeedReaderNotificacaoAplicativo.FeedEntry.TABLE_NAME, null, values);

        return newRowId;
    }

    @Override
    public List<NotificacaoAplicativo> buscar(String[] projection, String selection, String[] selectionArgs, String sortOrder, String groupBy, String having) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        List<NotificacaoAplicativo>  naAll = new LinkedList<>();
        NotificacaoAplicativo na = null;

        Cursor cursor = db.query(
                FeedReaderNotificacaoAplicativo.FeedEntry.TABLE_NAME, // The table to query
                projection,                                           // The array of columns to return (pass null to get all)
                (selection     == null ? null : selection    ),       // The columns for the WHERE clause
                (selectionArgs == null ? null : selectionArgs),       // The values for the WHERE clause
                (groupBy       == null ? null : groupBy      ),       // don't group the rows
                (having        == null ? null : having       ),       // don't filter by row groups
                (sortOrder     == null ? null : sortOrder    )        // The sort order
        );

        while (cursor.moveToNext()) {
            na = new NotificacaoAplicativo();

            na.setId(cursor.getLong(cursor.getColumnIndex(FeedReaderNotificacaoAplicativo.FeedEntry.COLUMN_NAME_ID)));
            na.setIdAplicativo(cursor.getLong(cursor.getColumnIndex(FeedReaderNotificacaoAplicativo.FeedEntry.COLUMN_NAME_ID_APLICATIVO)));
            na.setIdConfiguracao(cursor.getLong(cursor.getColumnIndex(FeedReaderNotificacaoAplicativo.FeedEntry.COLUMN_NAME_ID_CONFIGURACAO)));
            na.setData(Util.formatarDataVolta(cursor.getLong(cursor.getColumnIndex(FeedReaderNotificacaoAplicativo.FeedEntry.COLUMN_NAME_DATA))));
            na.setTitulo(cursor.getString(cursor.getColumnIndex(FeedReaderNotificacaoAplicativo.FeedEntry.COLUMN_NAME_TITULO)));
            na.setDescricao(cursor.getString(cursor.getColumnIndex(FeedReaderNotificacaoAplicativo.FeedEntry.COLUMN_NAME_DESCRICAO)));
            na.setStatus(cursor.getString(cursor.getColumnIndex(FeedReaderNotificacaoAplicativo.FeedEntry.COLUMN_NAME_STATUS)));

            naAll.add(na);
        }
        cursor.close();

        return naAll;
    }

    @Override
    public int excluir(String selection, String[] selectionArgs) {
        int rowsDeleted;
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        rowsDeleted = db.delete(FeedReaderNotificacaoAplicativo.FeedEntry.TABLE_NAME, selection, selectionArgs);

        return rowsDeleted;
    }

    @Override
    public int atualizar(ContentValues values, String selection, String[] selectionArgs) {
        int rowsUpdate;
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        rowsUpdate = db.update(
                FeedReaderNotificacaoAplicativo.FeedEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs);

        return rowsUpdate;
    }

}
