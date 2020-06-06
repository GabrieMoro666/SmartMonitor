package com.dev.smartmonitor.util;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

public class Channel extends Application {

    public static final String CHANNEL_GRUPO_1 = "GRUPO_1";
    public static final String CHANNEL_GRUPO_2 = "GRUPO_2";

    public static final String CHANNEL_NOTIFICACAO_SISTEMA = "Notificação Smart Monitor (SISTEMA)";
    public static final String CHANNEL_NOTIFICACAO_APLICATIVO = "Notificação Smart Monitor (APLICATIVO)";

    @Override
    public void onCreate() {
        super.onCreate();

        createNotificationChannel();

    }

    private void createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            NotificationManager notificationManagerSistema = getSystemService(NotificationManager.class);
            NotificationManager notificationManagerAplicativo = getSystemService(NotificationManager.class);

            NotificationChannelGroup notificationChannelGroupSistema = new NotificationChannelGroup(CHANNEL_GRUPO_1, CHANNEL_GRUPO_1);
            NotificationChannelGroup notificationChannelGroupAplicativo = new NotificationChannelGroup(CHANNEL_GRUPO_2, CHANNEL_GRUPO_2);

            notificationManagerSistema.createNotificationChannelGroup(notificationChannelGroupSistema);
            notificationManagerAplicativo.createNotificationChannelGroup(notificationChannelGroupAplicativo);

            NotificationChannel notificationChannelSistema = new NotificationChannel(CHANNEL_NOTIFICACAO_SISTEMA, CHANNEL_NOTIFICACAO_SISTEMA, NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannelSistema.setDescription(CHANNEL_NOTIFICACAO_SISTEMA);
            notificationChannelSistema.setGroup(CHANNEL_GRUPO_1);
            notificationManagerSistema.createNotificationChannel(notificationChannelSistema);

            NotificationChannel notificationChannelAplicativo = new NotificationChannel(CHANNEL_NOTIFICACAO_APLICATIVO, CHANNEL_NOTIFICACAO_APLICATIVO, NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannelAplicativo.setDescription(CHANNEL_NOTIFICACAO_APLICATIVO);
            notificationChannelAplicativo.setGroup(CHANNEL_GRUPO_2);
            notificationManagerAplicativo.createNotificationChannel(notificationChannelAplicativo);
        }
    }

}
