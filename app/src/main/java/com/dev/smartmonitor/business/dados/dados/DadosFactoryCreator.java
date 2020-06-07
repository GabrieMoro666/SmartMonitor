package com.dev.smartmonitor.business.dados.dados;

import android.content.Context;

import com.dev.smartmonitor.business.dados.aplicativo.DadosAplicativoFactory;
import com.dev.smartmonitor.business.dados.aplicativo.IDadosAplicativoFactory;
import com.dev.smartmonitor.business.dados.sistema.DadosSistemaFactory;
import com.dev.smartmonitor.business.dados.sistema.IDadosSistemaFactory;

public class DadosFactoryCreator {

    public IDadosAplicativoFactory getFactryDadosAplicativo(Context context) {
        return new DadosAplicativoFactory(context);
    }

    public IDadosSistemaFactory getFactryDadosSistema(Context context) {
        return new DadosSistemaFactory(context);
    }

}
