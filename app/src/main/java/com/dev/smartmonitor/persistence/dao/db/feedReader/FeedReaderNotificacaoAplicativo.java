package com.dev.smartmonitor.persistence.dao.db.feedReader;

import android.provider.BaseColumns;

public final class FeedReaderNotificacaoAplicativo {

    private FeedReaderNotificacaoAplicativo(){}

    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "notificacao_aplicativo";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_ID_CONFIGURACAO = "id_configuracao";
        public static final String COLUMN_NAME_ID_APLICATIVO = "id_aplicativo";
        public static final String COLUMN_NAME_DATA = "data";
        public static final String COLUMN_NAME_TITULO = "titulo";
        public static final String COLUMN_NAME_DESCRICAO = "descricao";
        public static final String COLUMN_NAME_STATUS = "status";
    }
}
