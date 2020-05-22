package com.dev.smartmonitor.business.notification.notification;

import android.content.Context;

public class NotificationFactoryCreator {

    public INotificationFactory getFactry(Context context) {
        return new NotificationFactory(context);
    }

}
