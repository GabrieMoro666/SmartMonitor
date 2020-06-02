package com.dev.smartmonitor.business.aplicativoAnalise.aplicativoAnalise;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.dev.smartmonitor.business.aplicativoAnalise.model.AplicativoAnalise;
import com.dev.smartmonitor.business.basic.basic.BasicFactoryCreator;
import com.dev.smartmonitor.persistence.dao.model.Aplicativo;
import com.dev.smartmonitor.persistence.dao.model.Sistema;

import java.util.LinkedList;
import java.util.List;

public class AplicativoAnaliseFactory implements IAplicativoAnaliseFactory {

    private Context context;

    public AplicativoAnaliseFactory(Context context) {
        this.context = context;
    }

    private List<String> buscarAplicativosDispositivo(){
        List<String> aplicativos = new LinkedList<>();
        PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> listaAplicativos = packageManager.getInstalledPackages(0);

        for (PackageInfo pi : listaAplicativos) {
            if ((pi.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0){
                aplicativos.add(pi.applicationInfo.loadLabel(packageManager).toString());
            }
        }

        return aplicativos;
    }

    private List<AplicativoAnalise> buscarAplicativosAnalise(){
        List<AplicativoAnalise> aplicativosAnalise = new LinkedList<>();
        List<Aplicativo> aplicativos;
        BasicFactoryCreator basicFactory = new BasicFactoryCreator();

        aplicativos = basicFactory.getFactry(context).createSelectFactory().buscarAplicativoAll("");

        for (Aplicativo a : aplicativos) {
            AplicativoAnalise aplicativoAnalise = new AplicativoAnalise();

            aplicativoAnalise.setAplicativo(a);
            aplicativoAnalise.setVerificado(false);

            aplicativosAnalise.add(aplicativoAnalise);
        }

        return aplicativosAnalise;
    }

    @Override
    public void analizarAplicativo(){
        List<AplicativoAnalise> aplicativosAnalise;
        List<String> aplicativosDispositivo;

        aplicativosDispositivo = buscarAplicativosDispositivo();
        aplicativosAnalise = buscarAplicativosAnalise();

        for (String nomeAplicativo : aplicativosDispositivo) {
            analizarAplicativo(aplicativosAnalise, nomeAplicativo);
        }

        verificarAplicativosVerificados(aplicativosAnalise);
    }

    private void verificarAplicativosVerificados(List<AplicativoAnalise> aplicativosAnalise){
        for (AplicativoAnalise a : aplicativosAnalise) {
            if (!a.isVerificado()){
                a.getAplicativo().setAtivo("N");
                atualizarAplicativo(a.getAplicativo());
            }
        }
    }

    private void analizarAplicativo(List<AplicativoAnalise> aplicativosAnalise, String nomeAplicativo){
        for (AplicativoAnalise a : aplicativosAnalise) {
            if (a.getAplicativo().getNome().equals(nomeAplicativo)){
                a.setVerificado(true);
                a.getAplicativo().setAtivo("S");
                atualizarAplicativo(a.getAplicativo());
                return;
            }
        }

        inserirAplicativo(nomeAplicativo);
    }

    @Override
    public void analizarAplicativo(String aplicativoDispositivo){
        BasicFactoryCreator basicFactory;
        Aplicativo aplicativo;

        basicFactory = new BasicFactoryCreator();
        aplicativo = basicFactory.getFactry(context).createSelectFactory().buscarAplicativoByNome(aplicativoDispositivo);

        if (aplicativo == null){
            inserirAplicativo(aplicativoDispositivo);
        } else {
            aplicativo.setAtivo("S");
            atualizarAplicativo(aplicativo);
        }
    }

    private void inserirAplicativo(String nomeAplicativo){
        BasicFactoryCreator basicFactory;
        Aplicativo aplicativo = new Aplicativo();
        Sistema sistema;
        long idNewRow;

        basicFactory = new BasicFactoryCreator();

        sistema = basicFactory.getFactry(context).createSelectFactory().buscarSistemaById(1L);

        aplicativo.setIdSistema(sistema.getId());
        aplicativo.setNome(nomeAplicativo);
        aplicativo.setAtivo("S");

        idNewRow = basicFactory.getFactry(context).createInsertFactory().inserir(aplicativo);
    }

    private void atualizarAplicativo(Aplicativo aplicativo){
        BasicFactoryCreator basicFactory;
        int rowsUptadate;

        basicFactory = new BasicFactoryCreator();

        rowsUptadate = basicFactory.getFactry(context).createUpdateFactory().atualizar(aplicativo);
    }

}
