package com.dev.smartmonitor.business.configuracao.configuracao;

import android.content.Context;

import com.dev.smartmonitor.business.configuracao.aplicativo.aplicativo.ConfiguracaoAplicativoFactory;
import com.dev.smartmonitor.business.configuracao.aplicativo.aplicativo.IConfiguracaoAplicativoFactory;
import com.dev.smartmonitor.business.configuracao.sistema.ConfiguracaoSistemaFactory;
import com.dev.smartmonitor.business.configuracao.sistema.IConfiguracaoSistemaFactory;

public class ConfiguracaoFactoryCreator {

    public IConfiguracaoAplicativoFactory getFactryConfiguracaoAplicativo(Context context) {
        return new ConfiguracaoAplicativoFactory(context);
    }

    public IConfiguracaoSistemaFactory getFactryConfiguracaoSistema(Context context) {
        return new ConfiguracaoSistemaFactory(context);
    }

}
