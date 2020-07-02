package com.dev.smartmonitor.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.dev.smartmonitor.business.checagem.ChecagemFactoryCreator;
import com.dev.smartmonitor.service.AplicativoExecutandoSingleton;

public class ChecagemBroadCastReceiver extends BroadcastReceiver {
    private ChecagemFactoryCreator checagemFactory = new ChecagemFactoryCreator();
    private AplicativoExecutandoSingleton aplicativoExecutando = AplicativoExecutandoSingleton.getAplicativoExecutando();

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
            aplicativoExecutando.setExecucaoOn(false);
        } else {
            checagemFactory.getFactry(context).checagem();
            aplicativoExecutando.setExecucaoOn(true);
        }
    }

}
