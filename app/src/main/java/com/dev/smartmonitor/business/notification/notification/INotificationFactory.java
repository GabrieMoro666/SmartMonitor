package com.dev.smartmonitor.business.notification.notification;

import com.dev.smartmonitor.business.notification.model.RowNotification;

import java.util.List;

public interface INotificationFactory {

    public List<RowNotification> buscarNotificacao();

    public void atualizarNotificacao(RowNotification rowNotification);

}
