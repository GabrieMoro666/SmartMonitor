package com.dev.smartmonitor.business.notification.notification;

import android.content.Context;

import com.dev.smartmonitor.business.basic.basic.BasicFactoryCreator;
import com.dev.smartmonitor.business.notification.model.RowNotification;
import com.dev.smartmonitor.persistence.dao.model.NotificacaoAplicativo;
import com.dev.smartmonitor.persistence.dao.model.NotificacaoSistema;

import java.util.LinkedList;
import java.util.List;

public class NotificationFactory implements INotificationFactory{

    private Context context;

    public NotificationFactory(Context context){
        this.context = context;
    }

    @Override
    public List<RowNotification> buscarNotificacao(){
        List<RowNotification> rowNotifications = new LinkedList<>();

        buscarNotificacaoSistema(rowNotifications);
        buscarNotificacaoAplicativo(rowNotifications);

        return rowNotifications;
    }

    private void buscarNotificacaoSistema(List<RowNotification> rowNotifications){
        BasicFactoryCreator basicFactory = new BasicFactoryCreator();
        List<NotificacaoSistema> notificacaoSistemas;
        RowNotification rowNotification;

        notificacaoSistemas = basicFactory.getFactry(context).createSelectFactory().buscarNotificacaoSistemaAll(new String[] { "E"});

        for (NotificacaoSistema notificacaoSistema : notificacaoSistemas) {
            rowNotification = new RowNotification();

            rowNotification.setTitulo(notificacaoSistema.getTitulo());
            rowNotification.setDescricao(notificacaoSistema.getDescricao());
            rowNotification.setData(notificacaoSistema.getData());
            rowNotification.setTipo("S");
            rowNotification.setIdNotificacao(notificacaoSistema.getId());
            rowNotification.setIdConfiguracao(notificacaoSistema.getIdConfiguracao());
            rowNotification.setIdOrigem(notificacaoSistema.getIdSistema());

            rowNotifications.add(rowNotification);
        }
    }

    private void buscarNotificacaoAplicativo(List<RowNotification> rowNotifications){
        BasicFactoryCreator basicFactory = new BasicFactoryCreator();
        List<NotificacaoAplicativo> notificacaoAplicativos;
        RowNotification rowNotification;

        notificacaoAplicativos = basicFactory.getFactry(context).createSelectFactory().buscarNotificacaoAplicativoAll(new String[] { "E" });

        for (NotificacaoAplicativo notificacaoAplicativo : notificacaoAplicativos) {
            rowNotification = new RowNotification();

            rowNotification.setTitulo(notificacaoAplicativo.getTitulo());
            rowNotification.setDescricao(notificacaoAplicativo.getDescricao());
            rowNotification.setData(notificacaoAplicativo.getData());
            rowNotification.setTipo("A");
            rowNotification.setIdNotificacao(notificacaoAplicativo.getId());
            rowNotification.setIdConfiguracao(notificacaoAplicativo.getIdConfiguracao());
            rowNotification.setIdOrigem(notificacaoAplicativo.getIdAplicativo());

            rowNotifications.add(rowNotification);
        }
    }

    @Override
    public void atualizarNotificacao(RowNotification rowNotification){
        if (rowNotification.getTipo().equals("S")){
            atualizarNotificacaoSistema(rowNotification.getIdNotificacao(), rowNotification.getIdConfiguracao(), rowNotification.getIdOrigem());
        } else if (rowNotification.getTipo().equals("A")){
            atualizarNotificacaoAplicativo(rowNotification.getIdNotificacao(), rowNotification.getIdConfiguracao(), rowNotification.getIdOrigem());
        }
    }

    private void atualizarNotificacaoSistema(long idNotificacao, long idConfiguracao, long idSistema) {
        NotificacaoSistema notificacaoSistema;
        BasicFactoryCreator basicFactory = new BasicFactoryCreator();
        int rowsUpdate;

        notificacaoSistema = basicFactory.getFactry(context).createSelectFactory().buscarNotificacaoSistemaById(idNotificacao, idSistema, idConfiguracao);

        if (notificacaoSistema != null){
            notificacaoSistema.setStatus("X");

            rowsUpdate = basicFactory.getFactry(context).createUpdateFactory().atualizar(notificacaoSistema);
        }
    }

    private void atualizarNotificacaoAplicativo(long idNotificacao, long idConfiguracao, long idAplicativo) {
        NotificacaoAplicativo notificacaoAplicativo;
        BasicFactoryCreator basicFactory = new BasicFactoryCreator();
        int rowsUpdate;

        notificacaoAplicativo = basicFactory.getFactry(context).createSelectFactory().buscarNotificacaoAplicativoById(idNotificacao, idAplicativo, idConfiguracao);

        if (notificacaoAplicativo != null){
            notificacaoAplicativo.setStatus("X");

            rowsUpdate = basicFactory.getFactry(context).createUpdateFactory().atualizar(notificacaoAplicativo);
        }
    }

}
