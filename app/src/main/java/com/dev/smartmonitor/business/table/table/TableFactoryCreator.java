package com.dev.smartmonitor.business.table.table;

import android.content.Context;

public class TableFactoryCreator {

    public ITableFactory getFactry(Context context) {
        return new TableFactory(context);
    }

}
