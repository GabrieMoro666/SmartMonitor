package com.dev.smartmonitor.persistence.dao.db.dbHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.dev.smartmonitor.persistence.dao.db.feedReader.FeedReaderAplicativo;
import com.dev.smartmonitor.persistence.dao.db.feedReader.FeedReaderChecagemSistema;
import com.dev.smartmonitor.persistence.dao.db.feedReader.FeedReaderConfiguracaoTempoAplicativo;
import com.dev.smartmonitor.persistence.dao.db.feedReader.FeedReaderConfiguracaoTempoSistema;
import com.dev.smartmonitor.persistence.dao.db.feedReader.FeedReaderDadosUsoAplicativo;
import com.dev.smartmonitor.persistence.dao.db.feedReader.FeedReaderDadosUsoSistema;
import com.dev.smartmonitor.persistence.dao.db.feedReader.FeedReaderNotificacaoAplicativo;
import com.dev.smartmonitor.persistence.dao.db.feedReader.FeedReaderNotificacaoSistema;
import com.dev.smartmonitor.persistence.dao.db.feedReader.FeedReaderSistema;

public class FeedReaderDbHelper extends SQLiteOpenHelper {
    Context context;

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "devil666.db";

    private static final String SQL_CREATE_ENTRIES_SISTEMA = "CREATE TABLE " +  FeedReaderSistema.FeedEntry.TABLE_NAME     + " ("                   +
                                                                                FeedReaderSistema.FeedEntry.COLUMN_NAME_ID + " INTEGER PRIMARY KEY" +
                                                                            ")";

    private static final String SQL_DELETE_ENTRIES_SISTEMA = "DROP TABLE IF EXISTS " + FeedReaderSistema.FeedEntry.TABLE_NAME;

    private static final String SQL_CREATE_ENTRIES_CHECAGEM_SISTEMA = "CREATE TABLE " +     FeedReaderChecagemSistema.FeedEntry.TABLE_NAME             + " ("                     +
                                                                                            FeedReaderChecagemSistema.FeedEntry.COLUMN_NAME_ID         + " INTEGER PRIMARY KEY, " +
                                                                                            FeedReaderChecagemSistema.FeedEntry.COLUMN_NAME_ID_SISTEMA + " INTEGER NOT NULL, "    +
                                                                                            FeedReaderChecagemSistema.FeedEntry.COLUMN_NAME_DATA       + " INTEGER NOT NULL, "    +
                                                                                            FeedReaderChecagemSistema.FeedEntry.COLUMN_NAME_QUANTIDADE + " INTEGER NOT NULL, "    +
                                                                                            " FOREIGN KEY(" +
                                                                                                FeedReaderChecagemSistema.FeedEntry.COLUMN_NAME_ID_SISTEMA +
                                                                                            ")" +
                                                                                            " REFERENCES " + FeedReaderSistema.FeedEntry.TABLE_NAME + "(" +
                                                                                                FeedReaderSistema.FeedEntry.COLUMN_NAME_ID +
                                                                                            ")" +
                                                                                        ")";

    private static final String SQL_DELETE_ENTRIES_CHECAGEM_SISTEMA = "DROP TABLE IF EXISTS " + FeedReaderChecagemSistema.FeedEntry.TABLE_NAME;

    private static final String SQL_CREATE_ENTRIES_APLICATIVO = "CREATE TABLE " +   FeedReaderAplicativo.FeedEntry.TABLE_NAME             + " ("                     +
                                                                                    FeedReaderAplicativo.FeedEntry.COLUMN_NAME_ID         + " INTEGER PRIMARY KEY, " +
                                                                                    FeedReaderAplicativo.FeedEntry.COLUMN_NAME_ID_SISTEMA + " INTEGER NOT NULL, "    +
                                                                                    FeedReaderAplicativo.FeedEntry.COLUMN_NAME_NOME       + " TEXT NOT NULL, "       +
                                                                                    FeedReaderAplicativo.FeedEntry.COLUMN_NAME_ATIVO      + " TEXT NOT NULL, "       +
                                                                                    "FOREIGN KEY(" +
                                                                                        FeedReaderAplicativo.FeedEntry.COLUMN_NAME_ID_SISTEMA +
                                                                                    ")" +
                                                                                    " REFERENCES " + FeedReaderSistema.FeedEntry.TABLE_NAME + "(" +
                                                                                        FeedReaderSistema.FeedEntry.COLUMN_NAME_ID +
                                                                                    ")" +
                                                                                ")";

    private static final String SQL_DELETE_ENTRIES_APLICATIVO = "DROP TABLE IF EXISTS " + FeedReaderAplicativo.FeedEntry.TABLE_NAME;


    private static final String SQL_CREATE_ENTRIES_CONFIGURACAO_TEMPO_APLICATIVO = "CREATE TABLE " +    FeedReaderConfiguracaoTempoAplicativo.FeedEntry.TABLE_NAME                 + " ("                     +
                                                                                                        FeedReaderConfiguracaoTempoAplicativo.FeedEntry.COLUMN_NAME_ID             + " INTEGER PRIMARY KEY, " +
                                                                                                        FeedReaderConfiguracaoTempoAplicativo.FeedEntry.COLUMN_NAME_ID_APLICATIVO  + " INTEGER NOT NULL, "    +
                                                                                                        FeedReaderConfiguracaoTempoAplicativo.FeedEntry.COLUMN_NAME_TEMPO_DIARIO   + " TEXT NOT NULL, "       +
                                                                                                        FeedReaderConfiguracaoTempoAplicativo.FeedEntry.COLUMN_NAME_TEMPO_CONTINUO + " TEXT NOT NULL, "       +
                                                                                                        "FOREIGN KEY(" +
                                                                                                            FeedReaderConfiguracaoTempoAplicativo.FeedEntry.COLUMN_NAME_ID_APLICATIVO +
                                                                                                        ")" +
                                                                                                        " REFERENCES " + FeedReaderAplicativo.FeedEntry.TABLE_NAME + "(" +
                                                                                                            FeedReaderAplicativo.FeedEntry.COLUMN_NAME_ID +
                                                                                                        ")" +
                                                                                                    ")";

    private static final String SQL_DELETE_ENTRIES_CONFIGURACAO_TEMPO_APLICATIVO = "DROP TABLE IF EXISTS " + FeedReaderConfiguracaoTempoAplicativo.FeedEntry.TABLE_NAME;

    private static final String SQL_CREATE_ENTRIES_CONFIGURACAO_TEMPO_SISTEMA = "CREATE TABLE " +   FeedReaderConfiguracaoTempoSistema.FeedEntry.TABLE_NAME                 + " ("                     +
                                                                                                    FeedReaderConfiguracaoTempoSistema.FeedEntry.COLUMN_NAME_ID             + " INTEGER PRIMARY KEY, " +
                                                                                                    FeedReaderConfiguracaoTempoSistema.FeedEntry.COLUMN_NAME_ID_SISTEMA     + " INTEGER NOT NULL, "    +
                                                                                                    FeedReaderConfiguracaoTempoSistema.FeedEntry.COLUMN_NAME_TEMPO_DIARIO   + " TEXT NOT NULL, "       +
                                                                                                    FeedReaderConfiguracaoTempoSistema.FeedEntry.COLUMN_NAME_TEMPO_CONTINUO + " TEXT NOT NULL, "       +
                                                                                                    "FOREIGN KEY(" +
                                                                                                        FeedReaderConfiguracaoTempoSistema.FeedEntry.COLUMN_NAME_ID_SISTEMA +
                                                                                                    ")" +
                                                                                                    " REFERENCES " + FeedReaderSistema.FeedEntry.TABLE_NAME + "(" +
                                                                                                        FeedReaderSistema.FeedEntry.COLUMN_NAME_ID +
                                                                                                    ")" +
                                                                                                ")";

    private static final String SQL_DELETE_ENTRIES_CONFIGURACAO_TEMPO_SISTEMA = "DROP TABLE IF EXISTS " + FeedReaderConfiguracaoTempoSistema.FeedEntry.TABLE_NAME;

    private static final String SQL_CREATE_ENTRIES_DADOS_USO_APLICATIVO = "CREATE TABLE " + FeedReaderDadosUsoAplicativo.FeedEntry.TABLE_NAME                 + " ("                     +
                                                                                            FeedReaderDadosUsoAplicativo.FeedEntry.COLUMN_NAME_ID             + " INTEGER PRIMARY KEY, " +
                                                                                            FeedReaderDadosUsoAplicativo.FeedEntry.COLUMN_NAME_ID_APLICATIVO  + " INTEGER NOT NULL, "    +
                                                                                            FeedReaderDadosUsoAplicativo.FeedEntry.COLUMN_NAME_DATA_INICIAL   + " INTEGER NOT NULL, "    +
                                                                                            FeedReaderDadosUsoAplicativo.FeedEntry.COLUMN_NAME_DATA_FINAL     + " INTEGER NOT NULL, "    +
                                                                                            "FOREIGN KEY(" +
                                                                                                FeedReaderDadosUsoAplicativo.FeedEntry.COLUMN_NAME_ID_APLICATIVO +
                                                                                            ")" +
                                                                                            " REFERENCES " + FeedReaderAplicativo.FeedEntry.TABLE_NAME + "(" +
                                                                                                FeedReaderAplicativo.FeedEntry.COLUMN_NAME_ID +
                                                                                            ")" +
                                                                                        ")";

    private static final String SQL_DELETE_ENTRIES_DADOS_USO_APLICATIVO = "DROP TABLE IF EXISTS " + FeedReaderDadosUsoAplicativo.FeedEntry.TABLE_NAME;

    private static final String SQL_CREATE_ENTRIES_DADOS_USO_SISTEMA = "CREATE TABLE " +    FeedReaderDadosUsoSistema.FeedEntry.TABLE_NAME               + " ("                     +
                                                                                            FeedReaderDadosUsoSistema.FeedEntry.COLUMN_NAME_ID           + " INTEGER PRIMARY KEY, " +
                                                                                            FeedReaderDadosUsoSistema.FeedEntry.COLUMN_NAME_ID_SISTEMA   + " INTEGER NOT NULL, "    +
                                                                                            FeedReaderDadosUsoSistema.FeedEntry.COLUMN_NAME_DATA_INICIAL + " INTEGER NOT NULL, "    +
                                                                                            FeedReaderDadosUsoSistema.FeedEntry.COLUMN_NAME_DATA_FINAL   + " INTEGER NOT NULL, "    +
                                                                                            "FOREIGN KEY(" +
                                                                                                FeedReaderDadosUsoSistema.FeedEntry.COLUMN_NAME_ID_SISTEMA +
                                                                                            ")" +
                                                                                            " REFERENCES " + FeedReaderSistema.FeedEntry.TABLE_NAME + "(" +
                                                                                                FeedReaderSistema.FeedEntry.COLUMN_NAME_ID +
                                                                                            ")" +
                                                                                        ")";

    private static final String SQL_DELETE_ENTRIES_DADOS_USO_SISTEMA = "DROP TABLE IF EXISTS " + FeedReaderDadosUsoSistema.FeedEntry.TABLE_NAME;

    private static final String SQL_CREATE_ENTRIES_NOTIFICACAO_APLICATIVO = "CREATE TABLE " +   FeedReaderNotificacaoAplicativo.FeedEntry.TABLE_NAME                    + " ("                     +
                                                                                                FeedReaderNotificacaoAplicativo.FeedEntry.COLUMN_NAME_ID                + " INTEGER PRIMARY KEY, " +
                                                                                                FeedReaderNotificacaoAplicativo.FeedEntry.COLUMN_NAME_ID_CONFIGURACAO   + " INTEGER NOT NULL, "    +
                                                                                                FeedReaderNotificacaoAplicativo.FeedEntry.COLUMN_NAME_ID_APLICATIVO     + " INTEGER NOT NULL, "    +
                                                                                                FeedReaderNotificacaoAplicativo.FeedEntry.COLUMN_NAME_DATA              + " INTEGER NOT NULL, "    +
                                                                                                FeedReaderNotificacaoAplicativo.FeedEntry.COLUMN_NAME_TITULO            + " TEXT NOT NULL, "       +
                                                                                                FeedReaderNotificacaoAplicativo.FeedEntry.COLUMN_NAME_DESCRICAO         + " TEXT NOT NULL, "       +
                                                                                                FeedReaderNotificacaoAplicativo.FeedEntry.COLUMN_NAME_STATUS            + " TEXT NOT NULL, "       +
                                                                                                "FOREIGN KEY(" +
                                                                                                    FeedReaderNotificacaoAplicativo.FeedEntry.COLUMN_NAME_ID_CONFIGURACAO +
                                                                                                ")" +
                                                                                                " REFERENCES " + FeedReaderConfiguracaoTempoAplicativo.FeedEntry.TABLE_NAME + "(" +
                                                                                                    FeedReaderConfiguracaoTempoAplicativo.FeedEntry.COLUMN_NAME_ID +
                                                                                                "), " +
                                                                                                "FOREIGN KEY(" +
                                                                                                    FeedReaderNotificacaoAplicativo.FeedEntry.COLUMN_NAME_ID_APLICATIVO +
                                                                                                ")" +
                                                                                                " REFERENCES " + FeedReaderAplicativo.FeedEntry.TABLE_NAME + "(" +
                                                                                                    FeedReaderAplicativo.FeedEntry.COLUMN_NAME_ID +
                                                                                                ")" +
                                                                                            ")";

    private static final String SQL_DELETE_ENTRIES_NOTIFICACAO_APLICATIVO = "DROP TABLE IF EXISTS " + FeedReaderNotificacaoAplicativo.FeedEntry.TABLE_NAME;

    private static final String SQL_CREATE_ENTRIES_NOTIFICACAO_SISTEMA = "CREATE TABLE " +  FeedReaderNotificacaoSistema.FeedEntry.TABLE_NAME                    + " ("                     +
                                                                                            FeedReaderNotificacaoSistema.FeedEntry.COLUMN_NAME_ID                + " INTEGER PRIMARY KEY, " +
                                                                                            FeedReaderNotificacaoSistema.FeedEntry.COLUMN_NAME_ID_CONFIGURACAO   + " INTEGER NOT NULL, "    +
                                                                                            FeedReaderNotificacaoSistema.FeedEntry.COLUMN_NAME_ID_SISTEMA        + " INTEGER NOT NULL, "    +
                                                                                            FeedReaderNotificacaoSistema.FeedEntry.COLUMN_NAME_DATA              + " INTEGER NOT NULL, "    +
                                                                                            FeedReaderNotificacaoSistema.FeedEntry.COLUMN_NAME_TITULO            + " TEXT NOT NULL, "       +
                                                                                            FeedReaderNotificacaoSistema.FeedEntry.COLUMN_NAME_DESCRICAO         + " TEXT NOT NULL, "       +
                                                                                            FeedReaderNotificacaoSistema.FeedEntry.COLUMN_NAME_STATUS            + " TEXT NOT NULL, "       +
                                                                                            "FOREIGN KEY(" +
                                                                                                FeedReaderNotificacaoSistema.FeedEntry.COLUMN_NAME_ID_CONFIGURACAO +
                                                                                            ")" +
                                                                                            " REFERENCES " + FeedReaderConfiguracaoTempoSistema.FeedEntry.TABLE_NAME + "(" +
                                                                                                FeedReaderConfiguracaoTempoSistema.FeedEntry.COLUMN_NAME_ID +
                                                                                            "), " +
                                                                                            "FOREIGN KEY(" +
                                                                                                FeedReaderNotificacaoSistema.FeedEntry.COLUMN_NAME_ID_SISTEMA +
                                                                                            ")" +
                                                                                            " REFERENCES " + FeedReaderSistema.FeedEntry.TABLE_NAME + "(" +
                                                                                                FeedReaderSistema.FeedEntry.COLUMN_NAME_ID +
                                                                                            ")" +
                                                                                        ")";

    private static final String SQL_DELETE_ENTRIES_NOTIFICACAO_SISTEMA = "DROP TABLE IF EXISTS " + FeedReaderNotificacaoSistema.FeedEntry.TABLE_NAME;

    public FeedReaderDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES_SISTEMA);
        db.execSQL(SQL_CREATE_ENTRIES_CHECAGEM_SISTEMA);
        db.execSQL(SQL_CREATE_ENTRIES_APLICATIVO);
        db.execSQL(SQL_CREATE_ENTRIES_CONFIGURACAO_TEMPO_APLICATIVO);
        db.execSQL(SQL_CREATE_ENTRIES_CONFIGURACAO_TEMPO_SISTEMA);
        db.execSQL(SQL_CREATE_ENTRIES_DADOS_USO_APLICATIVO);
        db.execSQL(SQL_CREATE_ENTRIES_DADOS_USO_SISTEMA);
        db.execSQL(SQL_CREATE_ENTRIES_NOTIFICACAO_APLICATIVO);
        db.execSQL(SQL_CREATE_ENTRIES_NOTIFICACAO_SISTEMA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES_SISTEMA);
        db.execSQL(SQL_DELETE_ENTRIES_CHECAGEM_SISTEMA);
        db.execSQL(SQL_DELETE_ENTRIES_APLICATIVO);
        db.execSQL(SQL_DELETE_ENTRIES_CONFIGURACAO_TEMPO_APLICATIVO);
        db.execSQL(SQL_DELETE_ENTRIES_CONFIGURACAO_TEMPO_SISTEMA);
        db.execSQL(SQL_DELETE_ENTRIES_DADOS_USO_APLICATIVO);
        db.execSQL(SQL_DELETE_ENTRIES_DADOS_USO_SISTEMA);
        db.execSQL(SQL_DELETE_ENTRIES_NOTIFICACAO_APLICATIVO);
        db.execSQL(SQL_DELETE_ENTRIES_NOTIFICACAO_SISTEMA);
    }
}
