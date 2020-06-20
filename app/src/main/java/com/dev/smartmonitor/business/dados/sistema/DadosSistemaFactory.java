package com.dev.smartmonitor.business.dados.sistema;

import android.content.Context;

import com.dev.smartmonitor.business.basic.basic.BasicFactoryCreator;
import com.dev.smartmonitor.business.configuracao.configuracao.ConfiguracaoFactoryCreator;
import com.dev.smartmonitor.business.notification.emitir.EmitirNotificacaoFactoryCreator;
import com.dev.smartmonitor.persistence.dao.model.ConfiguracaoTempoSistema;
import com.dev.smartmonitor.persistence.dao.model.DadosUsoSistema;
import com.dev.smartmonitor.persistence.dao.model.DataInicialFinal;
import com.dev.smartmonitor.util.Util;

import java.util.Date;
import java.util.List;

public class DadosSistemaFactory implements IDadosSistemaFactory {

    private Context context;

    public DadosSistemaFactory(Context context){
        this.context = context;
    }

    @Override
    public void construirTempoUso(long idSistema, Date dataInicial, Date dataFinal) {
        if (validarDadoUso(dataInicial, dataFinal)) {
            inserirDadosUso(idSistema, dataInicial, dataFinal);
            verificarConfiguracao(idSistema);
        }
    }

    private void verificarConfiguracao(long idSistema) {
        BasicFactoryCreator basicFactory = new BasicFactoryCreator();
        ConfiguracaoTempoSistema configuracaoTempoSistema;
        ConfiguracaoFactoryCreator configuracaoFactory = new ConfiguracaoFactoryCreator();
        int tempoUso;

        configuracaoTempoSistema = configuracaoFactory.getFactryConfiguracaoSistema(context).construirConfiguracaoSistema(idSistema);

        if (Util.calcularMinutosDeHoras(configuracaoTempoSistema.getTempoContinuo()) != 0 || Util.calcularMinutosDeHoras(configuracaoTempoSistema.getTempoDiario()) != 0) {
            tempoUso = calcularTempoUso(idSistema);

            if (tempoUso > Util.calcularMinutosDeHoras(configuracaoTempoSistema.getTempoContinuo())) {
                construirNotificacaoTempoContinuo(idSistema, configuracaoTempoSistema.getId(), configuracaoTempoSistema.getTempoContinuo(), tempoUso);
            }

            if (tempoUso > Util.calcularMinutosDeHoras(configuracaoTempoSistema.getTempoDiario())) {
                construirNotificacaoTempoDiario(idSistema, configuracaoTempoSistema.getId(), configuracaoTempoSistema.getTempoDiario(), tempoUso);
            }
        }
    }

    private void construirNotificacaoTempoContinuo(long idSistema, long idConfiguracao, String tempoConfigurado, int tempoCalculado){
        String titulo, descricao;
        EmitirNotificacaoFactoryCreator emitirNotificacaoFactory = new EmitirNotificacaoFactoryCreator();

        titulo = "Tempo contínuo atingido";
        descricao = "Sistema atingiu o limite de tempo configurado " + Util.calcularDiaHoraMinutoDeMinutos(Util.calcularMinutosDeHoras(tempoConfigurado)) + " com " + Util.calcularDiaHoraMinutoDeMinutos(tempoCalculado);

        emitirNotificacaoFactory.getFactry(context).emitirNotificacaoAplicativo(idSistema, idConfiguracao, titulo, descricao);
    }

    private void construirNotificacaoTempoDiario(long idSistema, long idConfiguracao, String tempoConfigurado, int tempoCalculado){
        String titulo, descricao;
        EmitirNotificacaoFactoryCreator emitirNotificacaoFactory = new EmitirNotificacaoFactoryCreator();

        titulo = "Tempo diário atingido";
        descricao = "Sistema atingiu o limite de tempo configurado " + Util.calcularDiaHoraMinutoDeMinutos(Util.calcularMinutosDeHoras(tempoConfigurado)) + " com " + Util.calcularDiaHoraMinutoDeMinutos(tempoCalculado);

        emitirNotificacaoFactory.getFactry(context).emitirNotificacaoAplicativo(idSistema, idConfiguracao, titulo, descricao);
    }

    private boolean validarDadoUso(Date dataInicial, Date dataFinal) {
        int tempoCalculado;
        Date dataBaseInicial, dataBaseFinal;

        dataBaseInicial = Util.calcularPrimeiraDataAtual();
        dataBaseFinal = Util.calcularUltimaDataAtual();

        tempoCalculado = Util.calcularTempoDadosUso(dataBaseInicial, dataBaseFinal, new DataInicialFinal(dataInicial, dataFinal));

        return tempoCalculado > 0 ? true : false;
    }

    private int calcularTempoUso(long idSistema){
        BasicFactoryCreator basicFactory = new BasicFactoryCreator();
        Date dataInicial, dataFinal;
        List<DadosUsoSistema> dadosUsoSistemas;
        int tempoUso;

        dataInicial = Util.calcularPrimeiraDataAtual();
        dataFinal = Util.calcularUltimaDataAtual();

        dadosUsoSistemas = basicFactory.getFactry(context).createSelectFactory().buscarDadosUsoSistemaByData(dataInicial, dataFinal);

        tempoUso = Util.calcularTempoDadosUso(dataInicial, dataFinal, (List<DataInicialFinal>) ((List<? extends DataInicialFinal>) dadosUsoSistemas));

        return tempoUso;
    }

    private void inserirDadosUso(long idSistema, Date dataInicial, Date dataFinal){
        BasicFactoryCreator basicFactory = new BasicFactoryCreator();
        DadosUsoSistema dadosUsoSistema;

        dadosUsoSistema = new DadosUsoSistema();

        dadosUsoSistema.setIdSistema(idSistema);
        dadosUsoSistema.setDataInicial(dataInicial);
        dadosUsoSistema.setDataFinal(dataFinal);

        basicFactory.getFactry(context).createInsertFactory().inserir(dadosUsoSistema);
    }

}
