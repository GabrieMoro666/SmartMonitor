package com.dev.smartmonitor.business.configuracao.sistema;

import android.content.Context;

import com.dev.smartmonitor.business.basic.basic.BasicFactoryCreator;
import com.dev.smartmonitor.persistence.dao.model.Aplicativo;
import com.dev.smartmonitor.persistence.dao.model.ConfiguracaoTempoAplicativo;
import com.dev.smartmonitor.persistence.dao.model.ConfiguracaoTempoSistema;
import com.dev.smartmonitor.persistence.dao.model.Sistema;
import com.dev.smartmonitor.util.Util;

import java.util.List;

public class ConfiguracaoSistemaFactory implements IConfiguracaoSistemaFactory {

    private Context context;

    public ConfiguracaoSistemaFactory(Context context){
        this.context = context;
    }

    @Override
    public ConfiguracaoTempoSistema construirConfiguracaoSistema(long idSistema){
        BasicFactoryCreator basicFactory = new BasicFactoryCreator();
        Sistema sistema;
        ConfiguracaoTempoSistema configuracaoTempoSistema;

        sistema = basicFactory.getFactry(context).createSelectFactory().buscarSistemaById(idSistema);

        configuracaoTempoSistema = basicFactory.getFactry(context).createSelectFactory().buscarConfiguracaoTempoSistemaByIdSistema(sistema.getId());

        if (configuracaoTempoSistema != null) {
            return configuracaoTempoSistema;
        }
        else {
            configuracaoTempoSistema = new ConfiguracaoTempoSistema();

            configuracaoTempoSistema.setIdSistema(sistema.getId());
            configuracaoTempoSistema.setTempoDiario("00:00");
            configuracaoTempoSistema.setTempoContinuo("00:00");

            configuracaoTempoSistema.setId(inserirConfiguracaoSistema(sistema.getId()));
        }

        return configuracaoTempoSistema;
    }

    private long inserirConfiguracaoSistema(long idSistema){
        BasicFactoryCreator basicFactory = new BasicFactoryCreator();
        ConfiguracaoTempoSistema configuracaoTempoSistema = new ConfiguracaoTempoSistema();
        long idNewRow;

        configuracaoTempoSistema.setIdSistema(idSistema);
        configuracaoTempoSistema.setTempoDiario("00:00");
        configuracaoTempoSistema.setTempoContinuo("00:00");

        idNewRow = basicFactory.getFactry(context).createInsertFactory().inserir(configuracaoTempoSistema);

        return idNewRow;
    }

    @Override
    public void updateConfiguracaoSitema(long idSistema, String tempoDiario, String tempoContinuo){
        BasicFactoryCreator basicFactory = new BasicFactoryCreator();
        ConfiguracaoTempoSistema configuracaoTempoSistema = new ConfiguracaoTempoSistema();
        int rowsUpdate;

        configuracaoTempoSistema = basicFactory.getFactry(context).createSelectFactory().buscarConfiguracaoTempoSistemaByIdSistema(idSistema);

        configuracaoTempoSistema.setTempoDiario(tempoDiario);
        configuracaoTempoSistema.setTempoContinuo(tempoContinuo);

        rowsUpdate = basicFactory.getFactry(context).createUpdateFactory().atualizar(configuracaoTempoSistema);
    }

    @Override
    public ConfiguracaoTempoAplicativo verificarConfiguracaoAplicativosTempoDiario(String tempoDiario){
        BasicFactoryCreator basicFactory = new BasicFactoryCreator();
        List<Aplicativo> aplicativos;
        ConfiguracaoTempoAplicativo configuracaoTempoAplicativo;

        aplicativos = basicFactory.getFactry(context).createSelectFactory().buscarAplicativoAll("S");

        for (Aplicativo a : aplicativos) {
            configuracaoTempoAplicativo = basicFactory.getFactry(context).createSelectFactory().buscarConfiguracaoTempoAplicativoByIdAplicativo(a.getId());

            if (configuracaoTempoAplicativo != null) {
                if (Util.calcularMinutosDeHoras(configuracaoTempoAplicativo.getTempoDiario()) != 0) {
                    if (Util.calcularMinutosDeHoras(tempoDiario) < Util.calcularMinutosDeHoras(configuracaoTempoAplicativo.getTempoDiario())){
                        return configuracaoTempoAplicativo;
                    }
                }
            }
        }

        return null;
    }

    @Override
    public ConfiguracaoTempoAplicativo verificarConfiguracaoAplicativosTempoContinuo(String tempoContinuo){
        BasicFactoryCreator basicFactory = new BasicFactoryCreator();
        List<Aplicativo> aplicativos;
        ConfiguracaoTempoAplicativo configuracaoTempoAplicativo;

        aplicativos = basicFactory.getFactry(context).createSelectFactory().buscarAplicativoAll("S");

        for (Aplicativo a : aplicativos) {
            configuracaoTempoAplicativo = basicFactory.getFactry(context).createSelectFactory().buscarConfiguracaoTempoAplicativoByIdAplicativo(a.getId());

            if (configuracaoTempoAplicativo != null) {
                if (Util.calcularMinutosDeHoras(configuracaoTempoAplicativo.getTempoContinuo()) != 0) {
                    if (Util.calcularMinutosDeHoras(tempoContinuo) < Util.calcularMinutosDeHoras(configuracaoTempoAplicativo.getTempoContinuo())){
                        return configuracaoTempoAplicativo;
                    }
                }
            }
        }

        return null;
    }

}
