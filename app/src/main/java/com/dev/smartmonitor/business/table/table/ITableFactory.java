package com.dev.smartmonitor.business.table.table;

import com.dev.smartmonitor.business.table.model.RowTable;

import java.util.List;

public interface ITableFactory {

    public List<RowTable> construirTableDia();

    public List<RowTable> construirTableSemana();

    public List<RowTable> construirTableMes();

    public String calcularTempoUsoSistemaDia();

    public String calcularTempoUsoSistemaSemana();

    public String calcularTempoUsoSistemaMes();

    public String calcularChecagemSistemaDia();

    public String calcularChecagemSistemaSemana();

    public String calcularChecagemSistemaMes();

}
