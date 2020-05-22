package com.dev.smartmonitor.persistence.dao.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.dev.smartmonitor.persistence.dao.db.dbHelper.FeedReaderDbHelper;
import com.dev.smartmonitor.persistence.dao.db.feedReader.FeedReaderChecagemSistema;
import com.dev.smartmonitor.persistence.dao.idao.IChecagemSistemaDAO;
import com.dev.smartmonitor.persistence.dao.model.ChecagemSistema;
import com.dev.smartmonitor.util.Util;

import java.util.LinkedList;
import java.util.List;

public class ChecagemSistemaDAO implements IChecagemSistemaDAO {

    private FeedReaderDbHelper dbHelper;

    public ChecagemSistemaDAO(Context context){
        this.dbHelper = new FeedReaderDbHelper(context);
    }

    @Override
    public long inserir(ChecagemSistema checagemSistema) {
        long newRowId = 0;
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        if (checagemSistema.getId() != 0) values.put(FeedReaderChecagemSistema.FeedEntry.COLUMN_NAME_ID, checagemSistema.getId());
        values.put(FeedReaderChecagemSistema.FeedEntry.COLUMN_NAME_ID_SISTEMA, checagemSistema.getIdSistema());
        values.put(FeedReaderChecagemSistema.FeedEntry.COLUMN_NAME_DATA, Long.toString(Util.formatarDataPara(checagemSistema.getData())));
        values.put(FeedReaderChecagemSistema.FeedEntry.COLUMN_NAME_QUANTIDADE, checagemSistema.getQuantidade());

        newRowId = db.insert(FeedReaderChecagemSistema.FeedEntry.TABLE_NAME, null, values);

        return newRowId;
    }

    @Override
    public List<ChecagemSistema> buscar(String[] projection, String selection, String[] selectionArgs, String sortOrder, String groupBy, String having) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        List<ChecagemSistema>  csAll = new LinkedList<>();
        ChecagemSistema cs = null;

        Cursor cursor = db.query(
                FeedReaderChecagemSistema.FeedEntry.TABLE_NAME, // The table to query
                projection,                                     // The array of columns to return (pass null to get all)
                (selection     == null ? null : selection    ), // The columns for the WHERE clause
                (selectionArgs == null ? null : selectionArgs), // The values for the WHERE clause
                (groupBy       == null ? null : groupBy      ), // don't group the rows
                (having        == null ? null : having       ), // don't filter by row groups
                (sortOrder     == null ? null : sortOrder    )  // The sort order
        );

        while (cursor.moveToNext()) {
            cs = new ChecagemSistema();

            cs.setId(cursor.getLong(cursor.getColumnIndex(FeedReaderChecagemSistema.FeedEntry.COLUMN_NAME_ID)));
            cs.setIdSistema(cursor.getLong(cursor.getColumnIndex(FeedReaderChecagemSistema.FeedEntry.COLUMN_NAME_ID_SISTEMA)));
            cs.setData(Util.formatarDataVolta(cursor.getLong(cursor.getColumnIndex(FeedReaderChecagemSistema.FeedEntry.COLUMN_NAME_DATA))));
            cs.setQuantidade(cursor.getInt(cursor.getColumnIndex(FeedReaderChecagemSistema.FeedEntry.COLUMN_NAME_QUANTIDADE)));

            csAll.add(cs);
        }
        cursor.close();

        return csAll;
    }

    @Override
    public int excluir(String selection, String[] selectionArgs) {
        int rowsDeleted;
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        rowsDeleted = db.delete(FeedReaderChecagemSistema.FeedEntry.TABLE_NAME, selection, selectionArgs);

        return rowsDeleted;
    }

    public int atualizar(ContentValues values, String selection, String[] selectionArgs) {
        int rowsUpdate;
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        rowsUpdate = db.update(
                FeedReaderChecagemSistema.FeedEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs);

        return rowsUpdate;
    }



}
