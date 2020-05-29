package com.dev.smartmonitor.business.chart.chart;

import android.app.Activity;
import android.content.Context;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.core.cartesian.series.Column;
import com.anychart.enums.Anchor;
import com.anychart.enums.HoverMode;
import com.anychart.enums.Position;
import com.anychart.enums.TooltipPositionMode;

import com.dev.smartmonitor.business.basic.basic.BasicFactoryCreator;
import com.dev.smartmonitor.persistence.dao.model.Aplicativo;
import com.dev.smartmonitor.persistence.dao.model.ChecagemSistema;
import com.dev.smartmonitor.persistence.dao.model.DadosUsoAplicativo;
import com.dev.smartmonitor.persistence.dao.model.DadosUsoSistema;
import com.dev.smartmonitor.persistence.dao.model.DataInicialFinal;
import com.dev.smartmonitor.persistence.dao.model.Sistema;
import com.dev.smartmonitor.util.Util;
import com.dev.smartmonitor.view.view.CustomDialogMensagem;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class ChartFactory implements IChartFactory{

    private Context context;

    public ChartFactory(Context context){
        this.context = context;
    }

    private List<DataEntry> construirDataEntryDia(){
        Date dataInicial, dataFinal;

        dataFinal = Util.calcularUltimaDataAtual();
        dataInicial = Util.calcularPrimeiraDataAtual(Util.voltarDiaData(dataFinal));

        return constrirDataEntry(dataInicial, dataFinal);
    }

    private List<DataEntry> construirDataEntrySemana(){
        Date dataInicial, dataFinal;

        dataFinal = Util.calcularUltimaDataAtual();
        dataInicial = Util.calcularPrimeiraDataAtual(Util.voltarDiaData(dataFinal));

        return constrirDataEntry(dataInicial, dataFinal);
    }

    private List<DataEntry> construirDataEntryMes(){
        Date dataInicial, dataFinal;

        dataFinal = Util.calcularUltimaDataAtual();
        dataInicial = Util.calcularPrimeiraDataAtual(Util.voltarDiaData(dataFinal));

        return constrirDataEntry(dataInicial, dataFinal);
    }

    private List<DataEntry> constrirDataEntry(Date dataInicial, Date dataFinal){
        List<DataEntry> dataEntries = new LinkedList<>();
        BasicFactoryCreator basicFactory = new BasicFactoryCreator();
        List<Aplicativo> aplicativos = new LinkedList<>();
        List<DadosUsoAplicativo> dadosUsoAplicativos = new LinkedList<>();
        ValueDataEntry valueDataEntry;
        int tempoCalculado;

        aplicativos = basicFactory.getFactry(context).createSelectFactory().buscarAplicativoAll("");

        for (Aplicativo a : aplicativos) {
            dadosUsoAplicativos = basicFactory.getFactry(context).createSelectFactory().buscarDadosUsoAplicativoByDataIdAplicativo(dataInicial, dataFinal, a.getId());

            valueDataEntry = new ValueDataEntry(a.getNome(), Util.calcularTempoDadosUso(dataInicial, dataFinal, (List<DataInicialFinal>) ((List<? extends DataInicialFinal>) dadosUsoAplicativos)));

            dataEntries.add(valueDataEntry);
        }

        return dataEntries;
    }

    @Override
    public Cartesian construirGraficoDeColunasDia(){
        return construirGraficoDeColunas(construirDataEntryDia());
    }

    @Override
    public Cartesian construirGraficoDeColunasSemana(){
        return construirGraficoDeColunas(construirDataEntrySemana());
    }

    @Override
    public Cartesian construirGraficoDeColunasMes(){
        return construirGraficoDeColunas(construirDataEntryMes());
    }

    private Cartesian construirGraficoDeColunas(List<DataEntry> dataEntries){
        Cartesian cartesian = AnyChart.column();

        Column column = cartesian.column(dataEntries);

        column.tooltip().titleFormat("{%X}").position(Position.CENTER_BOTTOM).anchor(Anchor.CENTER_BOTTOM).offsetX(0d).offsetY(5d).format("{%Value}{groupsSeparator: }");

        cartesian.animation(true);
        cartesian.title("Tempo de utilização de aplicativos");

        cartesian.yScale().minimum(0d);

        cartesian.yAxis(0).labels().format("{%Value}{groupsSeparator: }");

        cartesian.tooltip().positionMode(TooltipPositionMode.POINT);
        cartesian.interactivity().hoverMode(HoverMode.BY_X);

        cartesian.xAxis(0).title("Aplicativos");
        cartesian.yAxis(0).title("Tempo de Uso");

        return cartesian;
    }

    @Override
    public String calcularTempoUsoSistemaDia(){
        Date dataInicial, dataFinal;

        dataFinal = Util.calcularUltimaDataAtual();
        dataInicial = Util.calcularPrimeiraDataAtual(Util.voltarDiaData(dataFinal));

        return calcularTempoUsoSistema(dataInicial, dataFinal);
    }

    @Override
    public String calcularTempoUsoSistemaSemana(){
        Date dataInicial, dataFinal;

        dataFinal = Util.calcularUltimaDataAtual();
        dataInicial = Util.calcularPrimeiraDataAtual(Util.voltarSemanaData(dataFinal));

        return calcularTempoUsoSistema(dataInicial, dataFinal);
    }

    @Override
    public String calcularTempoUsoSistemaMes(){
        Date dataInicial, dataFinal;

        dataFinal = Util.calcularUltimaDataAtual();
        dataInicial = Util.calcularPrimeiraDataAtual(Util.voltarMesData(dataFinal));

        return calcularTempoUsoSistema(dataInicial, dataFinal);
    }

    private String calcularTempoUsoSistema(Date dataInicial, Date dataFinal){
        BasicFactoryCreator basicFactory = new BasicFactoryCreator();
        Sistema sistema;
        List<DadosUsoSistema> dadosUsoSistemas = new LinkedList<>();
        String tempoUso = "";

        sistema = basicFactory.getFactry(context).createSelectFactory().buscarSistemaById(1L);

        if (sistema != null){
            dadosUsoSistemas = basicFactory.getFactry(context).createSelectFactory().buscarDadosUsoSistemaByData(dataInicial, dataFinal);

            tempoUso =  Util.calcularDiaHoraMinutiDeMinutos(Util.calcularTempoDadosUso(dataInicial, dataFinal, (List<DataInicialFinal>) ((List<? extends DataInicialFinal>) dadosUsoSistemas)));
        }

        return tempoUso;
    }

    @Override
    public String calcularChecagemSistemaDia(){
        Date dataInicial, dataFinal;

        dataFinal =  Util.calcularUltimaDataAtual();
        dataInicial = Util.calcularPrimeiraDataAtual(Util.voltarDiaData(dataFinal));

        return calcularChecagemSistema(dataInicial, dataFinal);
    }

    @Override
    public String calcularChecagemSistemaSemana(){
        Date dataInicial, dataFinal;

        dataFinal = Util.calcularUltimaDataAtual();
        dataInicial = Util.calcularPrimeiraDataAtual(Util.voltarSemanaData(dataFinal));

        return calcularChecagemSistema(dataInicial, dataFinal);
    }

    @Override
    public String calcularChecagemSistemaMes(){
        Date dataInicial, dataFinal;

        dataFinal = Util.calcularUltimaDataAtual();
        dataInicial = Util.calcularPrimeiraDataAtual(Util.voltarMesData(dataFinal));

        return calcularChecagemSistema(dataInicial, dataFinal);
    }

    private String calcularChecagemSistema(Date dataInicial, Date dataFinal){
        BasicFactoryCreator basicFactory = new BasicFactoryCreator();
        Sistema sistema;
        List<ChecagemSistema> checagemSistemas = new LinkedList<>();
        String checagem = "0";

        sistema = basicFactory.getFactry(context).createSelectFactory().buscarSistemaById(1L);

        if (sistema != null){
            checagemSistemas = basicFactory.getFactry(context).createSelectFactory().buscarChecagemSistemaByData(dataInicial, dataFinal);

            checagem = Integer.toString(Util.calcularChecagemSistema(checagemSistemas));
        }

        return checagem;
    }

}
