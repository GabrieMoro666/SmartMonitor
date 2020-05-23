package com.dev.smartmonitor.business.table.table;

import android.app.Activity;
import android.content.Context;

import com.dev.smartmonitor.business.basic.basic.BasicFactoryCreator;
import com.dev.smartmonitor.business.table.model.RowTable;
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

public class TableFactory implements ITableFactory {
    private Context context;

    public TableFactory(Context context){
        this.context = context;
    }

    @Override
    public List<RowTable> construirTableDia(){
        Date dataInicial, dataFinal;

        dataInicial = Util.calcularPrimeiraDataAtual();
        dataFinal = Util.calcularUltimaDataAtual();

        return construirTable(dataInicial, dataFinal);
    }

    @Override
    public List<RowTable> construirTableSemana(){
        Date dataInicial, dataFinal;

        dataInicial = Util.calcularPrimeiraDataSemana();
        dataFinal = Util.calcularUltimaDataSemana();

        return construirTable(dataInicial, dataFinal);
    }

    @Override
    public List<RowTable> construirTableMes(){
        Date dataInicial, dataFinal;

        dataInicial = Util.calcularPrimeiraDataMes();
        dataFinal = Util.calcularUltimaDataMes();

        return construirTable(dataInicial, dataFinal);
    }

    private List<RowTable> construirTable(Date dataInicial, Date dataFinal){
        BasicFactoryCreator basicFactory = new BasicFactoryCreator();
        List<Aplicativo> aplicativos = new LinkedList<>();
        List<DadosUsoAplicativo> dadosUsoAplicativos = new LinkedList<>();
        List<RowTable> rowTables = new LinkedList<>();
        RowTable rowTable;

        aplicativos = basicFactory.getFactry(context).createSelectFactory().buscarAplicativoAll("");

        for (Aplicativo a : aplicativos) {
            dadosUsoAplicativos = basicFactory.getFactry(context).createSelectFactory().buscarDadosUsoAplicativoByDataIdAplicativo(dataInicial, dataFinal, a.getId());

            rowTable = new RowTable();
            rowTable.setNomeAplicativo(a.getNome());
            rowTable.setTempoUso(Util.calcularDiaHoraMinutiDeMinutos(Util.calcularMinutosDeHoras(Util.calcularTempoDadosUso(dataInicial, dataFinal, (List<DataInicialFinal>) ((List<? extends DataInicialFinal>) dadosUsoAplicativos)))));

            rowTables.add(rowTable);
        }

        return rowTables;
    }

    @Override
    public String calcularTempoUsoSistemaDia(){
        Date dataInicial, dataFinal;

        dataInicial = Util.calcularPrimeiraDataAtual();
        dataFinal = Util.calcularUltimaDataAtual();

        return calcularTempoUsoSistema(dataInicial, dataFinal);
    }

    @Override
    public String calcularTempoUsoSistemaSemana(){
        Date dataInicial, dataFinal;

        dataInicial = Util.calcularPrimeiraDataSemana();
        dataFinal = Util.calcularUltimaDataSemana();

        return calcularTempoUsoSistema(dataInicial, dataFinal);
    }

    @Override
    public String calcularTempoUsoSistemaMes(){
        Date dataInicial, dataFinal;

        dataInicial = Util.calcularPrimeiraDataMes();
        dataFinal = Util.calcularUltimaDataMes();

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

            tempoUso =  Util.calcularDiaHoraMinutiDeMinutos(Util.calcularMinutosDeHoras(Util.calcularTempoDadosUso(dataInicial, dataFinal, (List<DataInicialFinal>) ((List<? extends DataInicialFinal>) dadosUsoSistemas))));
        }

        return tempoUso;
    }

    @Override
    public String calcularChecagemSistemaDia(){
        Date dataInicial, dataFinal;

        dataInicial = Util.calcularPrimeiraDataAtual();
        dataFinal = Util.calcularUltimaDataAtual();

        return calcularChecagemSistema(dataInicial, dataFinal);
    }

    @Override
    public String calcularChecagemSistemaSemana(){
        Date dataInicial, dataFinal;

        dataInicial = Util.calcularPrimeiraDataSemana();
        dataFinal = Util.calcularUltimaDataSemana();

        return calcularChecagemSistema(dataInicial, dataFinal);
    }

    @Override
    public String calcularChecagemSistemaMes(){
        Date dataInicial, dataFinal;

        dataInicial = Util.calcularPrimeiraDataMes();
        dataFinal = Util.calcularUltimaDataMes();

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
