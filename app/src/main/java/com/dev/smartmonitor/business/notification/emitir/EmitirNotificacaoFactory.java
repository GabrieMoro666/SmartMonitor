package com.dev.smartmonitor.business.notification.emitir;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.dev.smartmonitor.R;
import com.dev.smartmonitor.persistence.dao.model.NotificacaoAplicativo;
import com.dev.smartmonitor.persistence.dao.model.NotificacaoSistema;
import com.dev.smartmonitor.view.view.CustomDialogMensagem;
import com.dev.smartmonitor.view.view.SmartMonitor;

import static com.dev.smartmonitor.util.Channel.CHANNEL_NOTIFICACAO;

public class EmitirNotificacaoFactory implements IEmitirNotificacaoFactory {

    private Context context;

    public EmitirNotificacaoFactory(Context context) {
        this.context = context;
    }

    public void emitirNotificacao(NotificacaoSistema notificacaoSistema) {

    }

    public void emitirNotificacao(NotificacaoAplicativo notificacaoAplicativo) {

    }

    @Override
    public void teste(){
        emitirNotificacao(1L, "Notificação", "Notificamos que o Gian é gay");
        emitirNotificacao(2L, "Notificação 2", "Notificamos que o Gian é gay só pra confirmar");
    }

    private void emitirNotificacao(long id, String titulo, String descricao){
        int icone = R.mipmap.ic_launcher;//android.R.drawable.ic_dialog_info;

        Intent intent = new Intent(context, SmartMonitor.class);
        PendingIntent pendingIntent =  PendingIntent.getActivity(context, 0, intent, 0);

        NotificationCompat.Builder notificacao = new NotificationCompat.Builder(context, CHANNEL_NOTIFICACAO);
        notificacao.setSmallIcon(icone);
        notificacao.setContentTitle(titulo);
        notificacao.setContentText(descricao);
        notificacao.setContentIntent(pendingIntent);
        notificacao.setAutoCancel(true);
        //notificacao.build();

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManagerCompat.notify((int) id, notificacao.build());
    }

}
