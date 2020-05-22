package com.dev.smartmonitor.persistence.dao.db.feedReader;

import android.provider.BaseColumns;

public final class FeedReaderConfiguracaoTempoAplicativo {

    private FeedReaderConfiguracaoTempoAplicativo(){}

    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "configuracao_tempo_aplicativo";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_ID_APLICATIVO = "id_aplicativo";
        public static final String COLUMN_NAME_TEMPO_DIARIO = "tempo_diario";
        public static final String COLUMN_NAME_TEMPO_CONTINUO = "tempo_continuo";
    }
}
