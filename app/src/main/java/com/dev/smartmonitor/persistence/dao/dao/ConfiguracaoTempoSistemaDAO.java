package com.dev.smartmonitor.persistence.dao.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.dev.smartmonitor.persistence.dao.db.dbHelper.FeedReaderDbHelper;
import com.dev.smartmonitor.persistence.dao.db.feedReader.FeedReaderConfiguracaoTempoSistema;
import com.dev.smartmonitor.persistence.dao.idao.IConfiguracaoTempoSistemaDAO;
import com.dev.smartmonitor.persistence.dao.model.ConfiguracaoTempoSistema;

import java.util.LinkedList;
import java.util.List;

public class ConfiguracaoTempoSistemaDAO implements IConfiguracaoTempoSistemaDAO {

    private FeedReaderDbHelper dbHelper;

    public ConfiguracaoTempoSistemaDAO(Context context){
        this.dbHelper = new FeedReaderDbHelper(context);
    }

    @Override
    public long inserir(ConfiguracaoTempoSistema configuracaoTempoSistema) {
        long newRowId = 0;
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        if (configuracaoTempoSistema.getId() != 0) values.put(FeedReaderConfiguracaoTempoSistema.FeedEntry.COLUMN_NAME_ID, configuracaoTempoSistema.getId());
        values.put(FeedReaderConfiguracaoTempoSistema.FeedEntry.COLUMN_NAME_ID_SISTEMA, configuracaoTempoSistema.getIdSistema());
        values.put(FeedReaderConfiguracaoTempoSistema.FeedEntry.COLUMN_NAME_TEMPO_DIARIO, configuracaoTempoSistema.getTempoDiario());
        values.put(FeedReaderConfiguracaoTempoSistema.FeedEntry.COLUMN_NAME_TEMPO_CONTINUO, configuracaoTempoSistema.getTempoContinuo());

        newRowId = db.insert(FeedReaderConfiguracaoTempoSistema.FeedEntry.TABLE_NAME, null, values);

        return newRowId;
    }

    @Override
    public List<ConfiguracaoTempoSistema> buscar(String[] projection, String selection, String[] selectionArgs, String sortOrder, String groupBy, String having) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        List<ConfiguracaoTempoSistema>  ctsAll = new LinkedList<>();
        ConfiguracaoTempoSistema cts = null;

        Cursor cursor = db.query(
                FeedReaderConfiguracaoTempoSistema.FeedEntry.TABLE_NAME,    // The table to query
                projection,                                                 // The array of columns to return (pass null to get all)
                (selection     == null ? null : selection    ),             // The columns for the WHERE clause
                (selectionArgs == null ? null : selectionArgs),             // The values for the WHERE clause
                (groupBy       == null ? null : groupBy      ),             // don't group the rows
                (having        == null ? null : having       ),             // don't filter by row groups
                (sortOrder     == null ? null : sortOrder    )              // The sort order
        );

        while (cursor.moveToNext()) {
            cts = new ConfiguracaoTempoSistema();

            cts.setId(cursor.getLong(cursor.getColumnIndex(FeedReaderConfiguracaoTempoSistema.FeedEntry.COLUMN_NAME_ID)));
            cts.setIdSistema(cursor.getLong(cursor.getColumnIndex(FeedReaderConfiguracaoTempoSistema.FeedEntry.COLUMN_NAME_ID_SISTEMA)));
            cts.setTempoDiario(cursor.getString(cursor.getColumnIndex(FeedReaderConfiguracaoTempoSistema.FeedEntry.COLUMN_NAME_TEMPO_DIARIO)));
            cts.setTempoContinuo(cursor.getString(cursor.getColumnIndex(FeedReaderConfiguracaoTempoSistema.FeedEntry.COLUMN_NAME_TEMPO_CONTINUO)));

            ctsAll.add(cts);
        }
        cursor.close();

        return ctsAll;
    }

    @Override
    public int excluir(String selection, String[] selectionArgs) {
        int rowsDeleted;
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        rowsDeleted = db.delete(FeedReaderConfiguracaoTempoSistema.FeedEntry.TABLE_NAME, selection, selectionArgs);

        return rowsDeleted;
    }

    @Override
    public int atualizar(ContentValues values, String selection, String[] selectionArgs) {
        int rowsUpdate;
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        rowsUpdate = db.update(
                FeedReaderConfiguracaoTempoSistema.FeedEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs);

        return rowsUpdate;
    }

}
