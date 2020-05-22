package com.dev.smartmonitor.persistence.dao.db.feedReader;

import android.provider.BaseColumns;

public final class FeedReaderAplicativo {

    private FeedReaderAplicativo(){}

    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "aplicativo";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_ID_SISTEMA = "id_sistema";
        public static final String COLUMN_NAME_NOME = "nome";
        public static final String COLUMN_NAME_ATIVO = "ativo";
    }
}
