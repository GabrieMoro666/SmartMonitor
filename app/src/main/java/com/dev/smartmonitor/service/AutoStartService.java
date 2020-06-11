package com.dev.smartmonitor.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.content.ContextCompat;

public class AutoStartService extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String acao = intent.getAction();

        if(acao.equals(intent.ACTION_BOOT_COMPLETED) || acao.equals(intent.ACTION_LOCKED_BOOT_COMPLETED)){
            Intent intentServico = new Intent(context, MonitoradorService.class);
            ContextCompat.startForegroundService(context, intentServico);
        }

    }

}
