package com.dev.smartmonitor.persistence.dao.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.dev.smartmonitor.persistence.dao.db.helper.FeedReaderDbHelper;
import com.dev.smartmonitor.persistence.dao.db.feedReader.FeedReaderSistema;
import com.dev.smartmonitor.persistence.dao.idao.ISistemaDAO;
import com.dev.smartmonitor.persistence.dao.model.Sistema;

import java.util.LinkedList;
import java.util.List;

public class SistemaDAO implements ISistemaDAO {
    private FeedReaderDbHelper dbHelper;

    public SistemaDAO(Context context){
        this.dbHelper = new FeedReaderDbHelper(context);
    }

    public long inserir(Sistema sistema) {
        long newRowId = 0;
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        if (sistema.getId() != 0) values.put(FeedReaderSistema.FeedEntry.COLUMN_NAME_ID, sistema.getId());

        newRowId = db.insert(FeedReaderSistema.FeedEntry.TABLE_NAME, null, values);

        return newRowId;
    }

    public List<Sistema> buscar(String[] projection, String selection, String[] selectionArgs, String sortOrder, String groupBy, String having) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        List<Sistema>  sAll = new LinkedList<>();
        Sistema s = null;

        Cursor cursor = db.query(
                FeedReaderSistema.FeedEntry.TABLE_NAME,         // The table to query
                projection,                                     // The array of columns to return (pass null to get all)
                (selection     == null ? null : selection    ), // The columns for the WHERE clause
                (selectionArgs == null ? null : selectionArgs), // The values for the WHERE clause
                (groupBy       == null ? null : groupBy      ), // don't group the rows
                (having        == null ? null : having       ), // don't filter by row groups
                (sortOrder     == null ? null : sortOrder    )  // The sort order
        );

        while (cursor.moveToNext()) {
            s = new Sistema();

            s.setId(cursor.getLong(cursor.getColumnIndex(FeedReaderSistema.FeedEntry.COLUMN_NAME_ID)));

            sAll.add(s);
        }
        cursor.close();

        return sAll;
    }

    public int excluir(String selection, String[] selectionArgs) {
        int rowsDeleted;
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        rowsDeleted = db.delete(FeedReaderSistema.FeedEntry.TABLE_NAME, selection, selectionArgs);

        return rowsDeleted;
    }

    public int atualizar(ContentValues values, String selection, String[] selectionArgs) {
        int rowsUpdate;
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        rowsUpdate = db.update(
                FeedReaderSistema.FeedEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs);

        return rowsUpdate;
    }

}
