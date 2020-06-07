package com.dev.smartmonitor.persistence.dao.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.dev.smartmonitor.persistence.dao.db.helper.FeedReaderDbHelper;
import com.dev.smartmonitor.persistence.dao.db.feedReader.FeedReaderDadosUsoSistema;
import com.dev.smartmonitor.persistence.dao.idao.IDadosUsoSistemaDAO;
import com.dev.smartmonitor.persistence.dao.model.DadosUsoSistema;
import com.dev.smartmonitor.util.Util;

import java.util.LinkedList;
import java.util.List;

public class DadosUsoSistemaDAO implements IDadosUsoSistemaDAO {

    private FeedReaderDbHelper dbHelper;

    public DadosUsoSistemaDAO(Context context){
        this.dbHelper = new FeedReaderDbHelper(context);
    }

    @Override
    public long inserir(DadosUsoSistema dadosUsoSistema) {
        long newRowId = 0;
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        if (dadosUsoSistema.getId() != 0) values.put(FeedReaderDadosUsoSistema.FeedEntry.COLUMN_NAME_ID, dadosUsoSistema.getId());
        values.put(FeedReaderDadosUsoSistema.FeedEntry.COLUMN_NAME_ID_SISTEMA, dadosUsoSistema.getIdSistema());
        values.put(FeedReaderDadosUsoSistema.FeedEntry.COLUMN_NAME_DATA_INICIAL, Long.toString(Util.formatarDataHoraPara(dadosUsoSistema.getDataInicial())));
        values.put(FeedReaderDadosUsoSistema.FeedEntry.COLUMN_NAME_DATA_FINAL, Long.toString(Util.formatarDataHoraPara(dadosUsoSistema.getDataFinal())));

        newRowId = db.insert(FeedReaderDadosUsoSistema.FeedEntry.TABLE_NAME, null, values);

        return newRowId;
    }

    @Override
    public List<DadosUsoSistema> buscar(String[] projection, String selection, String[] selectionArgs, String sortOrder, String groupBy, String having) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        List<DadosUsoSistema>  dusAll = new LinkedList<>();
        DadosUsoSistema dus = null;

        Cursor cursor = db.query(
                FeedReaderDadosUsoSistema.FeedEntry.TABLE_NAME, // The table to query
                projection,                                     // The array of columns to return (pass null to get all)
                (selection     == null ? null : selection    ), // The columns for the WHERE clause
                (selectionArgs == null ? null : selectionArgs), // The values for the WHERE clause
                (groupBy       == null ? null : groupBy      ), // don't group the rows
                (having        == null ? null : having       ), // don't filter by row groups
                (sortOrder     == null ? null : sortOrder    )  // The sort order
        );

        while (cursor.moveToNext()) {
            dus = new DadosUsoSistema();

            dus.setId(cursor.getLong(cursor.getColumnIndex(FeedReaderDadosUsoSistema.FeedEntry.COLUMN_NAME_ID)));
            dus.setIdSistema(cursor.getLong(cursor.getColumnIndex(FeedReaderDadosUsoSistema.FeedEntry.COLUMN_NAME_ID_SISTEMA)));
            dus.setDataInicial(Util.formatarDataHoraVolta(cursor.getLong(cursor.getColumnIndex(FeedReaderDadosUsoSistema.FeedEntry.COLUMN_NAME_DATA_INICIAL))));
            dus.setDataFinal(Util.formatarDataHoraVolta(cursor.getLong(cursor.getColumnIndex(FeedReaderDadosUsoSistema.FeedEntry.COLUMN_NAME_DATA_FINAL))));

            dusAll.add(dus);
        }
        cursor.close();

        return dusAll;
    }

    @Override
    public int excluir(String selection, String[] selectionArgs) {
        int rowsDeleted;
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        rowsDeleted = db.delete(FeedReaderDadosUsoSistema.FeedEntry.TABLE_NAME, selection, selectionArgs);

        return rowsDeleted;
    }

    @Override
    public int atualizar(ContentValues values, String selection, String[] selectionArgs) {
        int rowsUpdate;
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        rowsUpdate = db.update(
                FeedReaderDadosUsoSistema.FeedEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs);

        return rowsUpdate;
    }

}
