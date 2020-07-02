package com.dev.smartmonitor.business.checagem;

import android.content.Context;

import com.dev.smartmonitor.business.basic.basic.BasicFactoryCreator;
import com.dev.smartmonitor.persistence.dao.model.ChecagemSistema;
import com.dev.smartmonitor.util.Util;

public class ChecagemFactory implements IChecagemFactory{
    private Context context;

    public ChecagemFactory(Context context){
        this.context = context;
    }

    @Override
    public void checagem(){
        ChecagemSistema checagemSistema = null;

        checagemSistema = buscarChecagem();
        atualizarChecagem(checagemSistema);
    }

    private void atualizarChecagem(ChecagemSistema checagemSistema){
        BasicFactoryCreator basicFactory = new BasicFactoryCreator();

        checagemSistema.setQuantidade(checagemSistema.getQuantidade()+1);

        basicFactory.getFactry(context).createUpdateFactory().atualizar(checagemSistema);
    }

    private ChecagemSistema buscarChecagem(){
        BasicFactoryCreator basicFactory = new BasicFactoryCreator();
        ChecagemSistema checagemSistema = null;

        checagemSistema = basicFactory.getFactry(context).createSelectFactory().buscarChecagemSistemaByData(Util.calcularDataAtual());

        if (checagemSistema == null){
            checagemSistema = inserirChecagem();
        }

        return checagemSistema;
    }

    private ChecagemSistema inserirChecagem(){
        long idNewRow;
        BasicFactoryCreator basicFactory = new BasicFactoryCreator();
        ChecagemSistema checagemSistema = null;

        checagemSistema = new ChecagemSistema();
        checagemSistema.setIdSistema(1L);
        checagemSistema.setData(Util.calcularDataAtual());
        checagemSistema.setQuantidade(0);

        idNewRow = basicFactory.getFactry(context).createInsertFactory().inserir(checagemSistema);

        checagemSistema.setId(idNewRow);

        return checagemSistema;
    }

}
