package com.dev.smartmonitor.persistence.dao.db.feedReader;

import android.provider.BaseColumns;

public final class FeedReaderChecagemSistema {

    private FeedReaderChecagemSistema(){}

    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "checagem_sistema";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_ID_SISTEMA = "id_sistema";
        public static final String COLUMN_NAME_DATA = "data";
        public static final String COLUMN_NAME_QUANTIDADE = "quantidade";
    }
}
