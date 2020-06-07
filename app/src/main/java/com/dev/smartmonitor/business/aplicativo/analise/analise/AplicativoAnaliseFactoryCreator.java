package com.dev.smartmonitor.business.aplicativo.analise.analise;

import android.content.Context;

public class AplicativoAnaliseFactoryCreator {

    public IAplicativoAnaliseFactory getFactry(Context context) {
        return new AplicativoAnaliseFactory(context);
    }

}
