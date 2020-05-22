package com.dev.smartmonitor.persistence.dao.db.feedReader;

import android.provider.BaseColumns;

public final class FeedReaderSistema {

    private FeedReaderSistema(){}

    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "sistema";
        public static final String COLUMN_NAME_ID = "id";
    }
}
