package com.dev.smartmonitor.business.basic.delete;

import android.content.Context;

public class DeleteFactoryCreator {

    public IDeleteFactory getFactry(Context context) {
        return new DeleteFactory(context);
    }

}
