package com.dev.smartmonitor.business.notification.emitir;

public interface IEmitirNotificacaoFactory {

    public void emitirNotificacaoSistema(long idSistema, long idConfiguracao, String titulo, String descricao);

    public void emitirNotificacaoAplicativo(long idAplicativo, long idConfiguracao, String titulo, String descricao);

}
