package com.dev.smartmonitor.persistence.dao.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.dev.smartmonitor.persistence.dao.db.helper.FeedReaderDbHelper;
import com.dev.smartmonitor.persistence.dao.db.feedReader.FeedReaderAplicativo;
import com.dev.smartmonitor.persistence.dao.idao.IAplicativoDAO;
import com.dev.smartmonitor.persistence.dao.model.Aplicativo;

import java.util.LinkedList;
import java.util.List;

public class AplicativoDAO implements IAplicativoDAO {

    private FeedReaderDbHelper dbHelper;

    public AplicativoDAO(Context context){
        this.dbHelper = new FeedReaderDbHelper(context);
    }

    @Override
    public long inserir(Aplicativo aplicativo) {
        long newRowId = 0;
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        if (aplicativo.getId() != 0) values.put(FeedReaderAplicativo.FeedEntry.COLUMN_NAME_ID, aplicativo.getId());
        values.put(FeedReaderAplicativo.FeedEntry.COLUMN_NAME_ID_SISTEMA, aplicativo.getIdSistema());
        values.put(FeedReaderAplicativo.FeedEntry.COLUMN_NAME_NOME, aplicativo.getNome());
        values.put(FeedReaderAplicativo.FeedEntry.COLUMN_NAME_PACOTE, aplicativo.getPacote());
        values.put(FeedReaderAplicativo.FeedEntry.COLUMN_NAME_ATIVO, aplicativo.getAtivo());

        newRowId = db.insert(FeedReaderAplicativo.FeedEntry.TABLE_NAME, null, values);

        return newRowId;
    }

    @Override
    public List<Aplicativo> buscar(String[] projection, String selection, String[] selectionArgs, String sortOrder, String groupBy, String having) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        List<Aplicativo>  aAll = new LinkedList<>();
        Aplicativo a = null;

        Cursor cursor = db.query(
                FeedReaderAplicativo.FeedEntry.TABLE_NAME,      // The table to query
                projection,                                     // The array of columns to return (pass null to get all)
                (selection     == null ? null : selection    ), // The columns for the WHERE clause
                (selectionArgs == null ? null : selectionArgs), // The values for the WHERE clause
                (groupBy       == null ? null : groupBy      ), // don't group the rows
                (having        == null ? null : having       ), // don't filter by row groups
                (sortOrder     == null ? null : sortOrder    )  // The sort order
        );

        while (cursor.moveToNext()) {
            a = new Aplicativo();

            a.setId(cursor.getLong(cursor.getColumnIndex(FeedReaderAplicativo.FeedEntry.COLUMN_NAME_ID)));
            a.setIdSistema(cursor.getLong(cursor.getColumnIndex(FeedReaderAplicativo.FeedEntry.COLUMN_NAME_ID_SISTEMA)));
            a.setNome(cursor.getString(cursor.getColumnIndex(FeedReaderAplicativo.FeedEntry.COLUMN_NAME_NOME)));
            a.setPacote(cursor.getString(cursor.getColumnIndex(FeedReaderAplicativo.FeedEntry.COLUMN_NAME_PACOTE)));
            a.setAtivo(cursor.getString(cursor.getColumnIndex(FeedReaderAplicativo.FeedEntry.COLUMN_NAME_ATIVO)));

            aAll.add(a);
        }
        cursor.close();

        return aAll;
    }

    @Override
    public int excluir(String selection, String[] selectionArgs) {
        int rowsDeleted;
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        rowsDeleted = db.delete(FeedReaderAplicativo.FeedEntry.TABLE_NAME, selection, selectionArgs);

        return rowsDeleted;
    }

    @Override
    public int atualizar(ContentValues values, String selection, String[] selectionArgs) {
        int rowsUpdate;
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        rowsUpdate = db.update(
                FeedReaderAplicativo.FeedEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs);

        return rowsUpdate;
    }

}
