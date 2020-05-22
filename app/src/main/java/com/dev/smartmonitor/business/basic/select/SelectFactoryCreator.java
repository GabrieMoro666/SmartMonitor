package com.dev.smartmonitor.business.basic.select;

import android.content.Context;

public class SelectFactoryCreator {

    public ISelectFactory getFactry(Context context) {
        return new SelectFactory(context);
    }

}
