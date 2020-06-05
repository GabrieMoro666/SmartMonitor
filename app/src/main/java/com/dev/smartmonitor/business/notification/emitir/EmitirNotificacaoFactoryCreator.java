package com.dev.smartmonitor.business.notification.emitir;

import android.content.Context;

public class EmitirNotificacaoFactoryCreator {

    public IEmitirNotificacaoFactory getFactry(Context context) {
        return new EmitirNotificacaoFactory(context);
    }

}
