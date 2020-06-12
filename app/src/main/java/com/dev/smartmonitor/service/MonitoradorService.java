package com.dev.smartmonitor.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import com.dev.smartmonitor.R;
import com.dev.smartmonitor.business.aplicativo.analise.analise.AplicativoAnaliseFactoryCreator;
import com.dev.smartmonitor.business.dados.dados.DadosFactoryCreator;
import com.dev.smartmonitor.persistence.dao.model.Aplicativo;
import com.dev.smartmonitor.util.ContextSingleton;
import com.dev.smartmonitor.util.Util;
import com.dev.smartmonitor.view.view.SmartMonitor;

import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import static com.dev.smartmonitor.util.Channel.CHANNEL_NOTIFICACAO_DESCRICAO_SERVICO;
import static com.dev.smartmonitor.util.Channel.CHANNEL_NOTIFICACAO_ID_SERVICO;
import static com.dev.smartmonitor.util.Channel.CHANNEL_NOTIFICACAO_NOME_SERVICO;
import static com.dev.smartmonitor.util.ContextSingleton.getContext;


public class MonitoradorService extends Service {

    static class ThreadServico extends Thread {

        @Override
        public void run() {
            MonitoradorService monitoradorService = new MonitoradorService();
            monitoradorService.servico();
        }

    }

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(getApplicationContext(), "Serviço iniciado!", Toast.LENGTH_LONG).show();

        notificacaoServico();

        ThreadServico threadServico = new ThreadServico();

        threadServico.start();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_NOT_STICKY;
    }

    private void notificacaoServico() {
        int icone = R.mipmap.ic_launcher;
        Intent notificationIntent = new Intent(this, SmartMonitor.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_NOTIFICACAO_ID_SERVICO)
            .setContentTitle(CHANNEL_NOTIFICACAO_NOME_SERVICO)
            .setContentText(CHANNEL_NOTIFICACAO_DESCRICAO_SERVICO)
            .setSmallIcon(icone)
            .setContentIntent(pendingIntent)
            .setAutoCancel(false)
            .build();

        startForeground(1, notification);

    }

    private void servico() {
        String aplicativoAtual;
        String aplicativoAnterior = "";
        Date dataInicial = Util.calcularDataAtual(), dataFinal = Util.calcularDataAtual();

        while (true) {
            aplicativoAtual = buscarAplicativo();

            if (!aplicativoAtual.equals(aplicativoAnterior)) {
                if (!aplicativoAnterior.isEmpty()) {
                    processoServico(aplicativoAnterior, dataInicial, dataFinal);
                }

                aplicativoAnterior = aplicativoAtual;
                dataInicial = Util.calcularDataAtual();

                Log.e("TESTE", "APLICACAO EM EXECUCAO: " + aplicativoAtual);
            }else{
                try {
                    Log.e("TESTE", "Desmaiou");
                    dataFinal = Util.calcularDataAtual();
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    private void processoServico(String nomeAplicativo, Date dataInicial, Date dataFinal){
        AplicativoAnaliseFactoryCreator aplicativoAnaliseFactory = new AplicativoAnaliseFactoryCreator();
        DadosFactoryCreator dadosFactory = new DadosFactoryCreator();
        long idAplicativo;

        idAplicativo = aplicativoAnaliseFactory.getFactry(getContext()).analizarAplicativo(nomeAplicativo);
        dadosFactory.getFactryDadosAplicativo(getContext()).construirTempoUso(idAplicativo, dataInicial, dataFinal);
        dadosFactory.getFactryDadosSistema(getContext()).construirTempoUso(1L, dataInicial, dataFinal);
    }

    private String buscarAplicativo(){
        String aplicativoAtual = "";
        String pacoteAtual = "";

        pacoteAtual = buscarPacoteEmExecucao();
        aplicativoAtual = buscarNomeAplicativoDoPacote(pacoteAtual);

        return aplicativoAtual;
    }

    private String buscarPacoteEmExecucao() {
        String pacoteAtual = "";

        UsageStatsManager usm = (UsageStatsManager) getContext().getSystemService(Context.USAGE_STATS_SERVICE);
        long time = System.currentTimeMillis();
        List<UsageStats> appList = null;

        if (usm != null) {
            appList = usm.queryUsageStats(UsageStatsManager.INTERVAL_DAILY, time - 1000 * 1000, time);
        }

        if (appList != null && appList.size() > 0) {

            SortedMap<Long, UsageStats> mySortedMap = new TreeMap<Long, UsageStats>();

            for (UsageStats usageStats : appList) {
                mySortedMap.put(usageStats.getLastTimeUsed(), usageStats);
            }

            if (mySortedMap != null && !mySortedMap.isEmpty()) {
                pacoteAtual = mySortedMap.get(mySortedMap.lastKey()).getPackageName();
            }

        }

        return pacoteAtual;
    }

    private String buscarNomeAplicativoDoPacote(String nomePacote){
        String nomeAplicativo;

        try {
            PackageManager packageManager = getContext().getPackageManager();
            ApplicationInfo info = packageManager.getApplicationInfo(nomePacote, PackageManager.GET_META_DATA);

            nomeAplicativo = (String) packageManager.getApplicationLabel(info);

            return nomeAplicativo;

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "null";
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(getApplicationContext(), "Serviço destruído!", Toast.LENGTH_LONG).show();
    }

}
