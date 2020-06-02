package com.dev.smartmonitor.business.configuracao.aplicativo.aplicativo;

import android.content.Context;

import com.dev.smartmonitor.business.aplicativoAnalise.aplicativoAnalise.AplicativoAnaliseFactoryCreator;
import com.dev.smartmonitor.business.basic.basic.BasicFactoryCreator;
import com.dev.smartmonitor.business.configuracao.aplicativo.adapter.model.RowConfiguracaoAplicativo;
import com.dev.smartmonitor.persistence.dao.model.Aplicativo;
import com.dev.smartmonitor.persistence.dao.model.ConfiguracaoTempoAplicativo;

import java.util.LinkedList;
import java.util.List;

public class ConfiguracaoAplicativoFactory implements IConfiguracaoAplicativoFactory {

    private Context context;

    public ConfiguracaoAplicativoFactory(Context context){
        this.context = context;
    }

    @Override
    public List<RowConfiguracaoAplicativo> construirConfiguracaoAplicativo(){
        AplicativoAnaliseFactoryCreator aplicativoAnaliseFactory = new AplicativoAnaliseFactoryCreator();
        BasicFactoryCreator basicFactory = new BasicFactoryCreator();
        List<Aplicativo> aplicativos;
        List<RowConfiguracaoAplicativo> rows = new LinkedList<>();
        RowConfiguracaoAplicativo row;
        ConfiguracaoTempoAplicativo configuracaoTempoAplicativo;

        aplicativoAnaliseFactory.getFactry(context).analizarAplicativo();

        aplicativos = basicFactory.getFactry(context).createSelectFactory().buscarAplicativoAll("S");

        for (Aplicativo a: aplicativos) {
            configuracaoTempoAplicativo = basicFactory.getFactry(context).createSelectFactory().buscarConfiguracaoTempoAplicativoByIdAplicativo(a.getId());

            row = new RowConfiguracaoAplicativo();

            row.setIdAplicativo(a.getId());
            row.setNome(a.getNome());

            if (configuracaoTempoAplicativo != null){
                row.setTempoDiario(configuracaoTempoAplicativo.getTempoDiario());
                row.setTempoContinuo(configuracaoTempoAplicativo.getTempoContinuo());
            } else {
                row.setTempoDiario("00:00");
                row.setTempoContinuo("00:00");
                inserirConfiguracaoAplicativo(a.getId());
            }

            rows.add(row);
        }

        return rows;
    }

    private void inserirConfiguracaoAplicativo(long idAplicativo){
        BasicFactoryCreator basicFactory = new BasicFactoryCreator();
        ConfiguracaoTempoAplicativo configuracaoTempoAplicativo = new ConfiguracaoTempoAplicativo();
        long idNewRow;

        configuracaoTempoAplicativo.setIdAplicativo(idAplicativo);
        configuracaoTempoAplicativo.setTempoDiario("00:00");
        configuracaoTempoAplicativo.setTempoContinuo("00:00");

        idNewRow = basicFactory.getFactry(context).createInsertFactory().inserir(configuracaoTempoAplicativo);
    }

    @Override
    public void updateConfiguracaoAplicativo(long idAplicativo, String tempoDiario, String tempoContinuo){
        BasicFactoryCreator basicFactory = new BasicFactoryCreator();
        ConfiguracaoTempoAplicativo configuracaoTempoAplicativo = new ConfiguracaoTempoAplicativo();
        int rowsUpdate;

        configuracaoTempoAplicativo = basicFactory.getFactry(context).createSelectFactory().buscarConfiguracaoTempoAplicativoByIdAplicativo(idAplicativo);

        configuracaoTempoAplicativo.setTempoDiario(tempoDiario);
        configuracaoTempoAplicativo.setTempoContinuo(tempoContinuo);

        rowsUpdate = basicFactory.getFactry(context).createUpdateFactory().atualizar(configuracaoTempoAplicativo);
    }

}
