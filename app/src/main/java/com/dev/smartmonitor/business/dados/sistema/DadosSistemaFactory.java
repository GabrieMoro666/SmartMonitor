package com.dev.smartmonitor.business.dados.sistema;

import android.content.Context;

import com.dev.smartmonitor.business.basic.basic.BasicFactory;
import com.dev.smartmonitor.business.basic.basic.BasicFactoryCreator;
import com.dev.smartmonitor.business.configuracao.configuracao.ConfiguracaoFactoryCreator;
import com.dev.smartmonitor.persistence.dao.model.ConfiguracaoTempoAplicativo;
import com.dev.smartmonitor.persistence.dao.model.DadosUsoAplicativo;
import com.dev.smartmonitor.util.Util;

import java.util.Date;

public class DadosSistemaFactory implements IDadosSistemaFactory {

    private Context context;

    public DadosSistemaFactory(Context context){
        this.context = context;
    }

    public void construirTempoUso(long idAplicativo, Date dataInicial, Date dataFinal) {
        inserirDadosUso(idAplicativo,dataInicial,dataFinal);

    }

    private void verificarConfiguracao(long idAplicativo) {
        BasicFactoryCreator basicFactory = new BasicFactoryCreator();
        ConfiguracaoTempoAplicativo configuracaoTempoAplicativo;
        ConfiguracaoFactoryCreator configuracaoFactory = new ConfiguracaoFactoryCreator();

        configuracaoTempoAplicativo = configuracaoFactory.getFactryConfiguracaoAplicativo(context).construirConfiguracaoAplicativo(idAplicativo);

        if (Util.calcularMinutosDeHoras(configuracaoTempoAplicativo.getTempoContinuo()) != 0 || Util.calcularMinutosDeHoras(configuracaoTempoAplicativo.getTempoDiario()) != 0) {

        }
    }

    private int calcularTempoUso(){


        return 0;
    }

    private void inserirDadosUso(long idAplicativo, Date dataInicial, Date dataFinal){
        BasicFactoryCreator basicFactory = new BasicFactoryCreator();
        DadosUsoAplicativo dadosUsoAplicativo;

        dadosUsoAplicativo = new DadosUsoAplicativo();

        dadosUsoAplicativo.setIdAplicativo(idAplicativo);
        dadosUsoAplicativo.setDataInicial(dataInicial);
        dadosUsoAplicativo.setDataFinal(dataFinal);

        basicFactory.getFactry(context).createInsertFactory().inserir(dadosUsoAplicativo);
    }
}
