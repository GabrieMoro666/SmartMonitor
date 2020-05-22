package com.dev.smartmonitor.persistence.dao.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.dev.smartmonitor.persistence.dao.db.dbHelper.FeedReaderDbHelper;
import com.dev.smartmonitor.persistence.dao.db.feedReader.FeedReaderConfiguracaoTempoAplicativo;
import com.dev.smartmonitor.persistence.dao.idao.IConfiguracaoTempoAplicativoDAO;
import com.dev.smartmonitor.persistence.dao.model.ConfiguracaoTempoAplicativo;

import java.util.LinkedList;
import java.util.List;

public class ConfiguracaoTempoAplicativoDAO implements IConfiguracaoTempoAplicativoDAO {

    private FeedReaderDbHelper dbHelper;

    public ConfiguracaoTempoAplicativoDAO(Context context){
        this.dbHelper = new FeedReaderDbHelper(context);
    }

    @Override
    public long inserir(ConfiguracaoTempoAplicativo configuracaoTempoAplicativo) {
        long newRowId = 0;
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        if (configuracaoTempoAplicativo.getId() != 0) values.put(FeedReaderConfiguracaoTempoAplicativo.FeedEntry.COLUMN_NAME_ID, configuracaoTempoAplicativo.getId());
        values.put(FeedReaderConfiguracaoTempoAplicativo.FeedEntry.COLUMN_NAME_ID_APLICATIVO, configuracaoTempoAplicativo.getIdAplicativo());
        values.put(FeedReaderConfiguracaoTempoAplicativo.FeedEntry.COLUMN_NAME_TEMPO_DIARIO, configuracaoTempoAplicativo.getTempoDiario());
        values.put(FeedReaderConfiguracaoTempoAplicativo.FeedEntry.COLUMN_NAME_TEMPO_CONTINUO, configuracaoTempoAplicativo.getTempoContinuo());

        newRowId = db.insert(FeedReaderConfiguracaoTempoAplicativo.FeedEntry.TABLE_NAME, null, values);

        return newRowId;
    }

    @Override
    public List<ConfiguracaoTempoAplicativo> buscar(String[] projection, String selection, String[] selectionArgs, String sortOrder, String groupBy, String having) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        List<ConfiguracaoTempoAplicativo>  ctaAll = new LinkedList<>();
        ConfiguracaoTempoAplicativo cta = null;

        Cursor cursor = db.query(
                FeedReaderConfiguracaoTempoAplicativo.FeedEntry.TABLE_NAME, // The table to query
                projection,                                                 // The array of columns to return (pass null to get all)
                (selection     == null ? null : selection    ),             // The columns for the WHERE clause
                (selectionArgs == null ? null : selectionArgs),             // The values for the WHERE clause
                (groupBy       == null ? null : groupBy      ),             // don't group the rows
                (having        == null ? null : having       ),             // don't filter by row groups
                (sortOrder     == null ? null : sortOrder    )              // The sort order
        );

        while (cursor.moveToNext()) {
            cta = new ConfiguracaoTempoAplicativo();

            cta.setId(cursor.getLong(cursor.getColumnIndex(FeedReaderConfiguracaoTempoAplicativo.FeedEntry.COLUMN_NAME_ID)));
            cta.setIdAplicativo(cursor.getLong(cursor.getColumnIndex(FeedReaderConfiguracaoTempoAplicativo.FeedEntry.COLUMN_NAME_ID_APLICATIVO)));
            cta.setTempoDiario(cursor.getString(cursor.getColumnIndex(FeedReaderConfiguracaoTempoAplicativo.FeedEntry.COLUMN_NAME_TEMPO_DIARIO)));
            cta.setTempoContinuo(cursor.getString(cursor.getColumnIndex(FeedReaderConfiguracaoTempoAplicativo.FeedEntry.COLUMN_NAME_TEMPO_CONTINUO)));

            ctaAll.add(cta);
        }
        cursor.close();

        return ctaAll;
    }

    @Override
    public int excluir(String selection, String[] selectionArgs) {
        int rowsDeleted;
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        rowsDeleted = db.delete(FeedReaderConfiguracaoTempoAplicativo.FeedEntry.TABLE_NAME, selection, selectionArgs);

        return rowsDeleted;
    }

    @Override
    public int atualizar(ContentValues values, String selection, String[] selectionArgs) {
        int rowsUpdate;
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        rowsUpdate = db.update(
                FeedReaderConfiguracaoTempoAplicativo.FeedEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs);

        return rowsUpdate;
    }

}
