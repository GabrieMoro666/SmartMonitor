package com.dev.smartmonitor.util;

import android.content.Context;

import com.dev.smartmonitor.business.basic.basic.BasicFactoryCreator;
import com.dev.smartmonitor.persistence.dao.model.Sistema;

public class Instalacao {

    public static void criarSistema(Context context) {
        BasicFactoryCreator basicFactory = new BasicFactoryCreator();
        Sistema sistema;

        sistema = basicFactory.getFactry(context).createSelectFactory().buscarSistemaById(1L);

        if (sistema == null) {
            sistema = new Sistema();

            sistema.setId(1L);

            basicFactory.getFactry(context).createInsertFactory().inserir(sistema);
        }
    }

}
