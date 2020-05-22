package com.dev.smartmonitor.business.basic.basic;

import android.content.Context;

public class BasicFactoryCreator {

    public IBasicFactory getFactry(Context context) {
        return new BasicFactory(context);
    }

}
