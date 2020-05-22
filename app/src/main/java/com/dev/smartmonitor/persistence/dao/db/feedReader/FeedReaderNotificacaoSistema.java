package com.dev.smartmonitor.persistence.dao.db.feedReader;

import android.provider.BaseColumns;

public final class FeedReaderNotificacaoSistema {

    private FeedReaderNotificacaoSistema(){}

    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "notificacao_sistema";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_ID_CONFIGURACAO = "id_configuracao";
        public static final String COLUMN_NAME_ID_SISTEMA = "id_sistema";
        public static final String COLUMN_NAME_DATA = "data";
        public static final String COLUMN_NAME_TITULO = "titulo";
        public static final String COLUMN_NAME_DESCRICAO = "descricao";
        public static final String COLUMN_NAME_STATUS = "status";
    }
}
