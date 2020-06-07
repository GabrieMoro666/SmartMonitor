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
import com.dev.smartmonitor.business.basic.basic.BasicFactoryCreator;
import com.dev.smartmonitor.persistence.dao.model.NotificacaoAplicativo;
import com.dev.smartmonitor.persistence.dao.model.NotificacaoSistema;
import com.dev.smartmonitor.util.Util;
import com.dev.smartmonitor.view.view.CustomDialogMensagem;
import com.dev.smartmonitor.view.view.SmartMonitor;

import static com.dev.smartmonitor.util.Channel.CHANNEL_NOTIFICACAO_ID_APLICATIVO;
import static com.dev.smartmonitor.util.Channel.CHANNEL_NOTIFICACAO_ID_SISTEMA;

public class EmitirNotificacaoFactory implements IEmitirNotificacaoFactory {

    private Context context;

    public EmitirNotificacaoFactory(Context context) {
        this.context = context;
    }

    private NotificacaoSistema buscarNotificacaoSistema(long idSistema) {
        BasicFactoryCreator basicFactory = new BasicFactoryCreator();
        NotificacaoSistema notificacaoSistema;

        notificacaoSistema = basicFactory.getFactry(context).createSelectFactory().buscarNotificacaoSistemaByIdSistemaData(idSistema, Util.calcularDataAtual());

        return notificacaoSistema;
    }

    private void inserirNotificacao(NotificacaoSistema notificacaoSistema) {
        long idNewRow;
        BasicFactoryCreator basicFactory = new BasicFactoryCreator();

        idNewRow = basicFactory.getFactry(context).createInsertFactory().inserir(notificacaoSistema);

        notificacaoSistema.setId(idNewRow);
    }

    @Override
    public void emitirNotificacaoSistema(long idSistema, long idConfiguracao, String titulo, String descricao) {
        NotificacaoSistema notificacaoSistema;

        notificacaoSistema = buscarNotificacaoSistema(idSistema);

        if (notificacaoSistema == null) {
            notificacaoSistema = new NotificacaoSistema();

            notificacaoSistema.setIdSistema(idSistema);
            notificacaoSistema.setIdConfiguracao(idConfiguracao);
            notificacaoSistema.setData(Util.calcularDataAtual());
            notificacaoSistema.setTitulo(titulo);
            notificacaoSistema.setDescricao(descricao);
            notificacaoSistema.setStatus("E");

            inserirNotificacao(notificacaoSistema);

            emitirNotificacao(notificacaoSistema.getId(), titulo, descricao, CHANNEL_NOTIFICACAO_ID_SISTEMA);
        }
    }

    private NotificacaoAplicativo buscarNotificacaoAplicativo(long idAplicativo) {
        BasicFactoryCreator basicFactory = new BasicFactoryCreator();
        NotificacaoAplicativo notificacaoAplicativo;

        notificacaoAplicativo = basicFactory.getFactry(context).createSelectFactory().buscarNotificacaoAplicativoByIdAplicativoData(idAplicativo, Util.calcularDataAtual());

        return notificacaoAplicativo;
    }

    private void inserirNotificacao(NotificacaoAplicativo notificacaoAplicativo) {
        long idNewRow;
        BasicFactoryCreator basicFactory = new BasicFactoryCreator();

        idNewRow = basicFactory.getFactry(context).createInsertFactory().inserir(notificacaoAplicativo);

        notificacaoAplicativo.setId(idNewRow);
    }

    @Override
    public void emitirNotificacaoAplicativo(long idAplicativo, long idConfiguracao, String titulo, String descricao) {
        NotificacaoAplicativo notificacaoAplicativo;

        notificacaoAplicativo = buscarNotificacaoAplicativo(idAplicativo);

        if (notificacaoAplicativo == null) {
            notificacaoAplicativo = new NotificacaoAplicativo();

            notificacaoAplicativo.setIdAplicativo(idAplicativo);
            notificacaoAplicativo.setIdConfiguracao(idConfiguracao);
            notificacaoAplicativo.setData(Util.calcularDataAtual());
            notificacaoAplicativo.setTitulo(titulo);
            notificacaoAplicativo.setDescricao(descricao);
            notificacaoAplicativo.setStatus("E");

            inserirNotificacao(notificacaoAplicativo);

            emitirNotificacao(notificacaoAplicativo.getId(), titulo, descricao, CHANNEL_NOTIFICACAO_ID_APLICATIVO);
        }
    }

    private void emitirNotificacao(long id, String titulo, String descricao, String channel){
        int icone = R.mipmap.ic_launcher;

        Intent intent = new Intent(context, SmartMonitor.class);
        PendingIntent pendingIntent =  PendingIntent.getActivity(context, 0, intent, 0);

        NotificationCompat.Builder notificacao = new NotificationCompat.Builder(context, channel);
        notificacao.setSmallIcon(icone);
        notificacao.setContentTitle(titulo);
        notificacao.setContentText(descricao);
        notificacao.setContentIntent(pendingIntent);
        notificacao.setAutoCancel(true);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManagerCompat.notify((int) id, notificacao.build());
    }

}
