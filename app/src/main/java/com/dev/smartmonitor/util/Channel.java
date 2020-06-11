package com.dev.smartmonitor.util;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.app.NotificationManager;
import android.os.Build;

public class Channel extends Application {

    public static final String CHANNEL_GRUPO_ID_SISTEMA                 = "GRUPO_SISTEMA";
    public static final String CHANNEL_GRUPO_ID_APLICATIVO              = "GRUPO_APLICATIVO";
    public static final String CHANNEL_GRUPO_ID_SERVICO                 = "GRUPO_SERVICO";

    public static final String CHANNEL_GRUPO_NOME_SISTEMA               = "Sistema";
    public static final String CHANNEL_GRUPO_NOME_APLICATIVO            = "Aplicativo";
    public static final String CHANNEL_GRUPO_NOME_SERVICO               = "Serviço";

    public static final String CHANNEL_NOTIFICACAO_ID_SISTEMA           = "CHANNEL_SISTEMA";
    public static final String CHANNEL_NOTIFICACAO_ID_APLICATIVO        = "CHANNEL_APLICATIVO";
    public static final String CHANNEL_NOTIFICACAO_ID_SERVICO           = "CHANNEL_SERVICO";

    public static final String CHANNEL_NOTIFICACAO_NOME_SISTEMA         = "Sistema";
    public static final String CHANNEL_NOTIFICACAO_NOME_APLICATIVO      = "Aplicativo";
    public static final String CHANNEL_NOTIFICACAO_NOME_SERVICO         = "Serviço";

    public static final String CHANNEL_NOTIFICACAO_DESCRICAO_SISTEMA    = "Notificação do sistema";
    public static final String CHANNEL_NOTIFICACAO_DESCRICAO_APLICATIVO = "Notificação do aplicativo";
    public static final String CHANNEL_NOTIFICACAO_DESCRICAO_SERVICO    = "Notificação do serviço";

    @Override
    public void onCreate() {
        super.onCreate();

        createNotificationGroup(CHANNEL_GRUPO_ID_SISTEMA, CHANNEL_GRUPO_NOME_SISTEMA);
        createNotificationGroup(CHANNEL_GRUPO_ID_APLICATIVO, CHANNEL_GRUPO_NOME_APLICATIVO);
        createNotificationGroup(CHANNEL_GRUPO_ID_SERVICO, CHANNEL_GRUPO_NOME_SERVICO);

        createNotificationChannel(CHANNEL_GRUPO_ID_SISTEMA, CHANNEL_NOTIFICACAO_ID_SISTEMA, CHANNEL_NOTIFICACAO_NOME_SISTEMA, CHANNEL_NOTIFICACAO_DESCRICAO_SISTEMA, NotificationManager.IMPORTANCE_DEFAULT);
        createNotificationChannel(CHANNEL_GRUPO_ID_APLICATIVO, CHANNEL_NOTIFICACAO_ID_APLICATIVO, CHANNEL_NOTIFICACAO_NOME_APLICATIVO, CHANNEL_NOTIFICACAO_DESCRICAO_APLICATIVO, NotificationManager.IMPORTANCE_DEFAULT);
        createNotificationChannel(CHANNEL_GRUPO_ID_APLICATIVO, CHANNEL_NOTIFICACAO_ID_SERVICO, CHANNEL_NOTIFICACAO_NOME_SERVICO, CHANNEL_NOTIFICACAO_DESCRICAO_SERVICO, NotificationManager.IMPORTANCE_LOW);
    }

    private void createNotificationChannel(String grupoId, String channelId, String channelNome, String channelDescricao, int importance){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationManager notificationManager = getSystemService(NotificationManager.class);

            NotificationChannel notificationChannel = new NotificationChannel(channelId, channelNome, importance);
            notificationChannel.setDescription(channelDescricao);
            notificationChannel.setGroup(grupoId);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    private void createNotificationGroup(String groupId, String groupNome){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationManager notificationManager = getSystemService(NotificationManager.class);

            NotificationChannelGroup notificationChannelGroup    = new NotificationChannelGroup(groupId   , groupNome);

            notificationManager.createNotificationChannelGroup(notificationChannelGroup);
        }
    }

}
