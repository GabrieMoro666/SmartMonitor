package com.dev.smartmonitor.business.aplicativo.analise.analise;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.dev.smartmonitor.business.aplicativo.analise.model.AplicativoAnalise;
import com.dev.smartmonitor.business.aplicativo.analise.model.AplicativoDispositivo;
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

    private List<AplicativoDispositivo> buscarAplicativosDispositivo(){
        List<AplicativoDispositivo> aplicativosDispositivo = new LinkedList<>();
        AplicativoDispositivo aplicativoDispositivo = null;
        PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> listaAplicativos = packageManager.getInstalledPackages(0);

        for (PackageInfo pi : listaAplicativos) {
            if ((pi.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0){
                aplicativoDispositivo = new AplicativoDispositivo();
                aplicativoDispositivo.setAplicativo(pi.applicationInfo.loadLabel(packageManager).toString());
                aplicativoDispositivo.setPacote(pi.applicationInfo.packageName);

                aplicativosDispositivo.add(aplicativoDispositivo);
            }
        }

        return aplicativosDispositivo;
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
        List<AplicativoDispositivo> aplicativosDispositivo;

        aplicativosDispositivo = buscarAplicativosDispositivo();
        aplicativosAnalise = buscarAplicativosAnalise();

        for (AplicativoDispositivo ad : aplicativosDispositivo) {
            analizarAplicativo(aplicativosAnalise, ad.getAplicativo(), ad.getPacote());
        }

        verificarAplicativosVerificados(aplicativosAnalise);
    }

    private void verificarAplicativosVerificados(List<AplicativoAnalise> aplicativosAnalise){
        for (AplicativoAnalise a : aplicativosAnalise) {
            if (!a.isVerificado()){
                a.getAplicativo().setAtivo("S");
                atualizarAplicativo(a.getAplicativo());
            }
        }
    }

    private void analizarAplicativo(List<AplicativoAnalise> aplicativosAnalise, String nomeAplicativo, String nomePacote){
        for (AplicativoAnalise a : aplicativosAnalise) {
            if (a.getAplicativo().getNome().equals(nomeAplicativo)){
                a.setVerificado(true);
                a.getAplicativo().setAtivo("S");
                atualizarAplicativo(a.getAplicativo());
                return;
            }
        }

        inserirAplicativo(nomeAplicativo, nomePacote);
    }

    @Override
    public long analizarAplicativo(String aplicativoDispositivo, String nomePacote){
        BasicFactoryCreator basicFactory;
        Aplicativo aplicativo;

        basicFactory = new BasicFactoryCreator();
        aplicativo = basicFactory.getFactry(context).createSelectFactory().buscarAplicativoByNome(aplicativoDispositivo);

        if (aplicativo == null){
            return inserirAplicativo(aplicativoDispositivo, nomePacote);
        } else {
            aplicativo.setAtivo("S");
            atualizarAplicativo(aplicativo);

            return aplicativo.getId();
        }
    }

    private long inserirAplicativo(String nomeAplicativo, String nomePacote){
        BasicFactoryCreator basicFactory;
        Aplicativo aplicativo = new Aplicativo();
        Sistema sistema;
        long idNewRow;

        basicFactory = new BasicFactoryCreator();

        sistema = basicFactory.getFactry(context).createSelectFactory().buscarSistemaById(1L);

        aplicativo.setIdSistema(sistema.getId());
        aplicativo.setNome(nomeAplicativo);
        aplicativo.setPacote(nomePacote.isEmpty() ? "DESCONHECIDO" : nomePacote);
        aplicativo.setAtivo("S");

        idNewRow = basicFactory.getFactry(context).createInsertFactory().inserir(aplicativo);

        return idNewRow;
    }

    private void atualizarAplicativo(Aplicativo aplicativo){
        BasicFactoryCreator basicFactory;
        int rowsUptadate;

        basicFactory = new BasicFactoryCreator();

        rowsUptadate = basicFactory.getFactry(context).createUpdateFactory().atualizar(aplicativo);
    }

}
