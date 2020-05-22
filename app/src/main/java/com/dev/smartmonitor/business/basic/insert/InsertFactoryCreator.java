package com.dev.smartmonitor.business.basic.insert;

import android.content.Context;

public class InsertFactoryCreator {

    public IInsertFactory getFactry(Context context) {
        return new InsertFactory(context);
    }

}
