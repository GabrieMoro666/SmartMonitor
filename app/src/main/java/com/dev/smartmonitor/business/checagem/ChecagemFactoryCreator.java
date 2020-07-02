package com.dev.smartmonitor.business.checagem;

import android.content.Context;

public class ChecagemFactoryCreator {
    public IChecagemFactory getFactry(Context context) {
        return new ChecagemFactory(context);
    }
}
