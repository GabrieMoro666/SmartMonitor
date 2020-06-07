package com.dev.smartmonitor.persistence.dao.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.dev.smartmonitor.persistence.dao.db.helper.FeedReaderDbHelper;
import com.dev.smartmonitor.persistence.dao.db.feedReader.FeedReaderDadosUsoAplicativo;
import com.dev.smartmonitor.persistence.dao.idao.IDadosUsoAplicativoDAO;
import com.dev.smartmonitor.persistence.dao.model.DadosUsoAplicativo;
import com.dev.smartmonitor.util.Util;

import java.util.LinkedList;
import java.util.List;

public class DadosUsoAplicativoDAO implements IDadosUsoAplicativoDAO {

    private FeedReaderDbHelper dbHelper;

    public DadosUsoAplicativoDAO(Context context){
        this.dbHelper = new FeedReaderDbHelper(context);
    }

    @Override
    public long inserir(DadosUsoAplicativo dadosUsoAplicativo) {
        long newRowId = 0;
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        if (dadosUsoAplicativo.getId() != 0) values.put(FeedReaderDadosUsoAplicativo.FeedEntry.COLUMN_NAME_ID, dadosUsoAplicativo.getId());
        values.put(FeedReaderDadosUsoAplicativo.FeedEntry.COLUMN_NAME_ID_APLICATIVO, dadosUsoAplicativo.getIdAplicativo());
        values.put(FeedReaderDadosUsoAplicativo.FeedEntry.COLUMN_NAME_DATA_INICIAL, Long.toString(Util.formatarDataHoraPara(dadosUsoAplicativo.getDataInicial())));
        values.put(FeedReaderDadosUsoAplicativo.FeedEntry.COLUMN_NAME_DATA_FINAL, Long.toString(Util.formatarDataHoraPara(dadosUsoAplicativo.getDataFinal())));

        newRowId = db.insert(FeedReaderDadosUsoAplicativo.FeedEntry.TABLE_NAME, null, values);

        return newRowId;
    }

    @Override
    public List<DadosUsoAplicativo> buscar(String[] projection, String selection, String[] selectionArgs, String sortOrder, String groupBy, String having) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        List<DadosUsoAplicativo>  duaAll = new LinkedList<>();
        DadosUsoAplicativo dua = null;

        Cursor cursor = db.query(
                FeedReaderDadosUsoAplicativo.FeedEntry.TABLE_NAME, // The table to query
                projection,                                        // The array of columns to return (pass null to get all)
                (selection     == null ? null : selection    ),    // The columns for the WHERE clause
                (selectionArgs == null ? null : selectionArgs),    // The values for the WHERE clause
                (groupBy       == null ? null : groupBy      ),    // don't group the rows
                (having        == null ? null : having       ),    // don't filter by row groups
                (sortOrder     == null ? null : sortOrder    )     // The sort order
        );

        while (cursor.moveToNext()) {
            dua = new DadosUsoAplicativo();

            dua.setId(cursor.getLong(cursor.getColumnIndex(FeedReaderDadosUsoAplicativo.FeedEntry.COLUMN_NAME_ID)));
            dua.setIdAplicativo(cursor.getLong(cursor.getColumnIndex(FeedReaderDadosUsoAplicativo.FeedEntry.COLUMN_NAME_ID_APLICATIVO)));
            dua.setDataInicial(Util.formatarDataHoraVolta(cursor.getLong(cursor.getColumnIndex(FeedReaderDadosUsoAplicativo.FeedEntry.COLUMN_NAME_DATA_INICIAL))));
            dua.setDataFinal(Util.formatarDataHoraVolta(cursor.getLong(cursor.getColumnIndex(FeedReaderDadosUsoAplicativo.FeedEntry.COLUMN_NAME_DATA_FINAL))));

            duaAll.add(dua);
        }
        cursor.close();

        return duaAll;
    }

    @Override
    public int excluir(String selection, String[] selectionArgs) {
        int rowsDeleted;
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        rowsDeleted = db.delete(FeedReaderDadosUsoAplicativo.FeedEntry.TABLE_NAME, selection, selectionArgs);

        return rowsDeleted;
    }

    @Override
    public int atualizar(ContentValues values, String selection, String[] selectionArgs) {
        int rowsUpdate;
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        rowsUpdate = db.update(
                FeedReaderDadosUsoAplicativo.FeedEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs);

        return rowsUpdate;
    }

}
