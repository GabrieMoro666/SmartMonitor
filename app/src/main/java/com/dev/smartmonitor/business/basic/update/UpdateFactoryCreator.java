package com.dev.smartmonitor.business.basic.update;

import android.content.Context;

public class UpdateFactoryCreator {

    public IUpdateFactory getFactry(Context context) {
        return new UpdateFactory(context);
    }

}
