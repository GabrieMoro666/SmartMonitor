package com.dev.smartmonitor.business.chart;

import com.anychart.charts.Cartesian;

public interface IChartFactory {

    public Cartesian construirGraficoDeColunasDia();

    public Cartesian construirGraficoDeColunasSemana();

    public Cartesian construirGraficoDeColunasMes();

    public String calcularTempoUsoSistemaDia();

    public String calcularTempoUsoSistemaSemana();

    public String calcularTempoUsoSistemaMes();

    public String calcularChecagemSistemaDia();

    public String calcularChecagemSistemaSemana();

    public String calcularChecagemSistemaMes();

}
