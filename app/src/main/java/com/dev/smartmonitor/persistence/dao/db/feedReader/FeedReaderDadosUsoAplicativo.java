package com.dev.smartmonitor.persistence.dao.db.feedReader;

import android.provider.BaseColumns;

public final class FeedReaderDadosUsoAplicativo {

    private FeedReaderDadosUsoAplicativo(){}

    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "dados_uso_aplicativo";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_ID_APLICATIVO = "id_aplicativo";
        public static final String COLUMN_NAME_DATA_INICIAL = "data_inicial";
        public static final String COLUMN_NAME_DATA_FINAL = "data_final";
    }
}
