package com.dev.smartmonitor.persistence.dao.db.feedReader;

import android.provider.BaseColumns;

public final class FeedReaderDadosUsoSistema {

    private FeedReaderDadosUsoSistema(){}

    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "dados_uso_sistema";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_ID_SISTEMA = "id_sistema";
        public static final String COLUMN_NAME_DATA_INICIAL = "data_inicial";
        public static final String COLUMN_NAME_DATA_FINAL = "data_final";
    }
}
