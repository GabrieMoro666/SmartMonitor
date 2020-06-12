package com.dev.smartmonitor.business.dados.aplicativo;

import android.content.Context;

import com.dev.smartmonitor.business.basic.basic.BasicFactoryCreator;
import com.dev.smartmonitor.business.configuracao.configuracao.ConfiguracaoFactoryCreator;
import com.dev.smartmonitor.business.notification.emitir.EmitirNotificacaoFactoryCreator;
import com.dev.smartmonitor.persistence.dao.model.Aplicativo;
import com.dev.smartmonitor.persistence.dao.model.ConfiguracaoTempoAplicativo;
import com.dev.smartmonitor.persistence.dao.model.DadosUsoAplicativo;
import com.dev.smartmonitor.persistence.dao.model.DataInicialFinal;
import com.dev.smartmonitor.util.Util;

import java.util.Date;
import java.util.List;

public class DadosAplicativoFactory implements IDadosAplicativoFactory {

    private Context context;

    public DadosAplicativoFactory(Context context){
        this.context = context;
    }

    @Override
    public void construirTempoUso(long idAplicativo, Date dataInicial, Date dataFinal) {
        if (validarDadoUso(dataInicial, dataFinal)) {
            inserirDadosUso(idAplicativo, dataInicial, dataFinal);
            verificarConfiguracao(idAplicativo);
        }
    }

    private void verificarConfiguracao(long idAplicativo) {
        BasicFactoryCreator basicFactory = new BasicFactoryCreator();
        ConfiguracaoTempoAplicativo configuracaoTempoAplicativo;
        ConfiguracaoFactoryCreator configuracaoFactory = new ConfiguracaoFactoryCreator();
        int tempoUso;

        configuracaoTempoAplicativo = configuracaoFactory.getFactryConfiguracaoAplicativo(context).construirConfiguracaoAplicativo(idAplicativo);

        if (Util.calcularMinutosDeHoras(configuracaoTempoAplicativo.getTempoContinuo()) != 0 || Util.calcularMinutosDeHoras(configuracaoTempoAplicativo.getTempoDiario()) != 0) {
            tempoUso = calcularTempoUso(idAplicativo);

            if (tempoUso > Util.calcularMinutosDeHoras(configuracaoTempoAplicativo.getTempoContinuo())) {
                construirNotificacaoTempoContinuo(idAplicativo, configuracaoTempoAplicativo.getId(), configuracaoTempoAplicativo.getTempoContinuo(), tempoUso);
            }

            if (tempoUso > Util.calcularMinutosDeHoras(configuracaoTempoAplicativo.getTempoDiario())) {
                construirNotificacaoTempoDiario(idAplicativo, configuracaoTempoAplicativo.getId(), configuracaoTempoAplicativo.getTempoDiario(), tempoUso);
            }
        }
    }

    private void construirNotificacaoTempoDiario(long idAplicativo, long idConfiguracao, String tempoConfigurado, int tempoCalculado){
        String titulo, descricao;
        BasicFactoryCreator basicFactory = new BasicFactoryCreator();
        EmitirNotificacaoFactoryCreator emitirNotificacaoFactory = new EmitirNotificacaoFactoryCreator();
        Aplicativo aplicativo;

        aplicativo = basicFactory.getFactry(context).createSelectFactory().buscarAplicativoByIdAplicativo(idAplicativo);

        titulo = "Tempo diario atingido";
        descricao = "Aplicaivo " + aplicativo.getNome() + " atingiu o limite de tempo configurado " + Util.calcularDiaHoraMinutoDeMinutos(Util.calcularMinutosDeHoras(tempoConfigurado)) + " com " + Util.calcularDiaHoraMinutoDeMinutos(tempoCalculado);

        emitirNotificacaoFactory.getFactry(context).emitirNotificacaoAplicativo(idAplicativo, idConfiguracao, titulo, descricao);
    }

    private void construirNotificacaoTempoContinuo(long idAplicativo, long idConfiguracao, String tempoConfigurado, int tempoCalculado){
        String titulo, descricao;
        BasicFactoryCreator basicFactory = new BasicFactoryCreator();
        EmitirNotificacaoFactoryCreator emitirNotificacaoFactory = new EmitirNotificacaoFactoryCreator();
        Aplicativo aplicativo;

        aplicativo = basicFactory.getFactry(context).createSelectFactory().buscarAplicativoByIdAplicativo(idAplicativo);

        titulo = "Tempo continuo atingido";
        descricao = "Aplicaivo " + aplicativo.getNome() + " atingiu o limite de tempo configurado " + Util.calcularDiaHoraMinutoDeMinutos(Util.calcularMinutosDeHoras(tempoConfigurado)) + " com " + Util.calcularDiaHoraMinutoDeMinutos(tempoCalculado);

        emitirNotificacaoFactory.getFactry(context).emitirNotificacaoAplicativo(idAplicativo, idConfiguracao, titulo, descricao);
    }

    private boolean validarDadoUso(Date dataInicial, Date dataFinal) {
        int tempoCalculado;
        Date dataBaseInicial, dataBaseFinal;

        dataBaseInicial = Util.calcularPrimeiraDataAtual();
        dataBaseFinal = Util.calcularUltimaDataAtual();

        tempoCalculado = Util.calcularTempoDadosUso(dataBaseInicial, dataBaseFinal, new DataInicialFinal(dataInicial, dataFinal));

        return tempoCalculado > 0 ? true : false;
    }

    private int calcularTempoUso(long idAplicativo){
        BasicFactoryCreator basicFactory = new BasicFactoryCreator();
        Date dataInicial, dataFinal;
        List<DadosUsoAplicativo> dadosUsoAplicativos;
        int tempoUso;

        dataInicial = Util.calcularPrimeiraDataAtual();
        dataFinal = Util.calcularUltimaDataAtual();

        dadosUsoAplicativos = basicFactory.getFactry(context).createSelectFactory().buscarDadosUsoAplicativoByDataIdAplicativo(dataInicial, dataFinal, idAplicativo);

        tempoUso = Util.calcularTempoDadosUso(dataInicial, dataFinal, (List<DataInicialFinal>) ((List<? extends DataInicialFinal>) dadosUsoAplicativos));

        return tempoUso;
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
