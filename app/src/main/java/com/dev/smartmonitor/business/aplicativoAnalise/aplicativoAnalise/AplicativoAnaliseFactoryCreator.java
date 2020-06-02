package com.dev.smartmonitor.business.aplicativoAnalise.aplicativoAnalise;

import android.content.Context;

public class AplicativoAnaliseFactoryCreator {

    public IAplicativoAnaliseFactory getFactry(Context context) {
        return new AplicativoAnaliseFactory(context);
    }

}
