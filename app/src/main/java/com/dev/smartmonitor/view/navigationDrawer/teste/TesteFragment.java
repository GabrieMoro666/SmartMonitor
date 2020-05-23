package com.dev.smartmonitor.view.navigationDrawer.teste;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.dev.smartmonitor.R;
import com.dev.smartmonitor.business.basic.basic.BasicFactoryCreator;
import com.dev.smartmonitor.persistence.dao.model.Aplicativo;
import com.dev.smartmonitor.persistence.dao.model.ChecagemSistema;
import com.dev.smartmonitor.persistence.dao.model.ConfiguracaoTempoAplicativo;
import com.dev.smartmonitor.persistence.dao.model.ConfiguracaoTempoSistema;
import com.dev.smartmonitor.persistence.dao.model.DadosUsoAplicativo;
import com.dev.smartmonitor.persistence.dao.model.DadosUsoSistema;
import com.dev.smartmonitor.persistence.dao.model.DataInicialFinal;
import com.dev.smartmonitor.persistence.dao.model.NotificacaoAplicativo;
import com.dev.smartmonitor.persistence.dao.model.NotificacaoSistema;
import com.dev.smartmonitor.persistence.dao.model.Sistema;
import com.dev.smartmonitor.util.ContextSingleton;
import com.dev.smartmonitor.util.Util;
import com.dev.smartmonitor.view.view.CustomDialogMensagem;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class TesteFragment extends Fragment {

    private Button buttonPopularBanco;

    public TesteFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_teste, container, false);

        init(view);

        return view;
    }

    class OnClickPopularBanco implements View.OnClickListener{

        BasicFactoryCreator basicFactory;
        Sistema sistema;
        List<ChecagemSistema> checagemSistemas;
        List<Aplicativo> aplicativos;
        List<ConfiguracaoTempoAplicativo> configuracaoTempoAplicativos;
        List<ConfiguracaoTempoSistema> configuracaoTempoSistemas;
        List<DadosUsoAplicativo> dadosUsoAplicativos;
        List<DadosUsoSistema> dadosUsoSistemas;
        List<NotificacaoAplicativo> notificacaoAplicativos;
        List<NotificacaoSistema> notificacaoSistemas;
        CustomDialogMensagem customDialogMensagem;

        String mesAtual;
        int ultimoDiaMesAtual;

        @Override
        public void onClick(View v) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(Util.calcularDataAtual());

            ultimoDiaMesAtual = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            mesAtual =  ("00" + Integer.toString(calendar.get(Calendar.MONTH) + 1));
            mesAtual = mesAtual.substring(mesAtual.length()-2,mesAtual.length());

            basicFactory = new BasicFactoryCreator();

            popularSistema();
            pupularChecagemSistema();
            popularAplicativo();
            popularConfiguracaoTempoAplicativo();
            popularConfiguracaoTempoSistema();
            populateDadosUsoAplicativo();
            populateDadosUsoSistema();
            popularNotificacaoAplicativo();
            popularNotificacaoSistema();

//            testarCalculoTempoNotificacao();
//            testarCalculoDiaHoraMinuto();

            customDialogMensagem = new CustomDialogMensagem((Activity) ContextSingleton.getContext(), "Processo concluido");
            customDialogMensagem.show();
        }

        private void testarCalculoDiaHoraMinuto(){
            int minutos = (24 * 60 * 0) + (60 * 0) + (40);

            customDialogMensagem = new CustomDialogMensagem((Activity) ContextSingleton.getContext(), "From hell " + Util.calcularDiaHoraMinutiDeMinutos(minutos));
            customDialogMensagem.show();
        }

        private void testarCalculoTempoNotificacao(){
            DadosUsoAplicativo duA = new DadosUsoAplicativo();
            List<DadosUsoAplicativo> duAData = new LinkedList<>();

            duA.setId(1L);
            duA.setIdAplicativo(1L);
            duA.setDataInicial(Util.formatarDataHoraVolta(Long.parseLong("202005172330")));
            duA.setDataFinal(Util.formatarDataHoraVolta(Long.parseLong("202005180010")));

            duAData.add(duA);

            CustomDialogMensagem customDialogMensagem = new CustomDialogMensagem((Activity) ContextSingleton.getContext(), "Tempo calculado: " + Util.calcularTempoDadosUso(Util.formatarDataVolta(Long.parseLong("20200518")), Util.formatarDataVolta(Long.parseLong("20200518")), (List<DataInicialFinal>) ((List<? extends DataInicialFinal>)duAData)));
            customDialogMensagem.show();

            customDialogMensagem = new CustomDialogMensagem((Activity) ContextSingleton.getContext(), "Tempo calculado: " + Util.calcularTempoDadosUso(Util.formatarDataVolta(Long.parseLong("20200518")), Util.formatarDataVolta(Long.parseLong("20200518")), duA));
            customDialogMensagem.show();
        }

        private void popularNotificacaoSistema(){
            NotificacaoSistema notificacaoSistema;
            ConfiguracaoTempoSistema configuracaoTempoSistema;
            List<DadosUsoSistema> duSData;
            notificacaoSistemas = new LinkedList<>();
            String dataBase, dataInicial, dataFinal, dia, horaMinutoAplicativo;
            long idNewRow;

            for (int i = 1; i <= ultimoDiaMesAtual; i++) {
                dia = ("00" + Integer.toString(i));
                dia = dia.substring(dia.length() - 2, dia.length());

                dataBase = "2020" + mesAtual + dia;

                dataInicial = dataBase + "0000";
                dataFinal   = dataBase + "2359";

                duSData = basicFactory.getFactry((Activity) ContextSingleton.getContext()).createSelectFactory().buscarDadosUsoSistemaByData(Util.formatarDataHoraVolta(Long.parseLong(dataInicial)), Util.formatarDataHoraVolta(Long.parseLong(dataFinal)));

                horaMinutoAplicativo = Util.calcularTempoDadosUso(Util.formatarDataVolta(Long.parseLong(dataBase)), Util.formatarDataVolta(Long.parseLong(dataBase)), (List<DataInicialFinal>) ((List<? extends DataInicialFinal>)duSData));

                configuracaoTempoSistema = basicFactory.getFactry((Activity) ContextSingleton.getContext()).createSelectFactory().buscarConfiguracaoTempoSistemaByIdSistema(sistema.getId());

                if (Util.calcularMinutosDeHoras(horaMinutoAplicativo) > Util.calcularMinutosDeHoras(configuracaoTempoSistema.getTempoDiario())){
                    notificacaoSistema = new NotificacaoSistema();
                    notificacaoSistema.setIdSistema(sistema.getId());
                    notificacaoSistema.setIdConfiguracao(configuracaoTempoSistema.getId());
                    notificacaoSistema.setData(Util.formatarDataVolta(Long.parseLong(dataBase)));
                    notificacaoSistema.setTitulo("Tempo diario atingido");
                    notificacaoSistema.setDescricao("Sistema atingiu o limite de tempo configurado " + Util.calcularDiaHoraMinutiDeMinutos(Util.calcularMinutosDeHoras(configuracaoTempoSistema.getTempoDiario())) + " com " + Util.calcularDiaHoraMinutiDeMinutos(Util.calcularMinutosDeHoras(horaMinutoAplicativo)));
                    notificacaoSistema.setStatus("E");

                    idNewRow = basicFactory.getFactry((Activity) ContextSingleton.getContext()).createInsertFactory().inserir(notificacaoSistema);

                    notificacaoSistema = basicFactory.getFactry((Activity) ContextSingleton.getContext()).createSelectFactory().buscarNotificacaoSistemaById(idNewRow, sistema.getId(), configuracaoTempoSistema.getId());
                    notificacaoSistemas.add(notificacaoSistema);
                }

                for (DadosUsoSistema duaCuntinuo : duSData) {
                    horaMinutoAplicativo = Util.calcularTempoDadosUso(Util.formatarDataVolta(Long.parseLong(dataBase)), Util.formatarDataVolta(Long.parseLong(dataBase)), duaCuntinuo);
                    if (Util.calcularMinutosDeHoras(horaMinutoAplicativo) > Util.calcularMinutosDeHoras(configuracaoTempoSistema.getTempoContinuo())){
                        notificacaoSistema = new NotificacaoSistema();
                        notificacaoSistema.setIdSistema(sistema.getId());
                        notificacaoSistema.setIdConfiguracao(configuracaoTempoSistema.getId());
                        notificacaoSistema.setData(Util.formatarDataVolta(Long.parseLong(dataBase)));
                        notificacaoSistema.setTitulo("Tempo continuo atingido");
                        notificacaoSistema.setDescricao("Sistema atingiu o limite de tempo configurado " + Util.calcularDiaHoraMinutiDeMinutos(Util.calcularMinutosDeHoras(configuracaoTempoSistema.getTempoContinuo())) + " com " + Util.calcularDiaHoraMinutiDeMinutos(Util.calcularMinutosDeHoras(horaMinutoAplicativo)));
                        notificacaoSistema.setStatus("E");

                        idNewRow = basicFactory.getFactry((Activity) ContextSingleton.getContext()).createInsertFactory().inserir(notificacaoSistema);

                        notificacaoSistema = basicFactory.getFactry((Activity) ContextSingleton.getContext()).createSelectFactory().buscarNotificacaoSistemaById(idNewRow, sistema.getId(), configuracaoTempoSistema.getId());
                        notificacaoSistemas.add(notificacaoSistema);
                    }
                }
            }

//            customDialogMensagem = new CustomDialogMensagem(MainActivityTesteNotificacaoGradeGrafico.this,"Registros em notificações do sistema: " + basicFactory.getFactry(MainActivityTesteNotificacaoGradeGrafico.this).createSelectFactory().buscarNotificacaoSistemaAll(null).size());
//            customDialogMensagem.show();
        }

        private void popularNotificacaoAplicativo(){
            NotificacaoAplicativo notificacaoAplicativo;
            ConfiguracaoTempoAplicativo configuracaoTempoAplicativo;
            List<DadosUsoAplicativo> duAData;
            notificacaoAplicativos = new LinkedList<>();
            String dataBase, dataInicial, dataFinal, dia, horaMinutoAplicativo;
            long idNewRow;

            for (Aplicativo a : aplicativos) {
                for (int i = 1; i <= ultimoDiaMesAtual; i++) {
                    dia = ("00" + Integer.toString(i));
                    dia = dia.substring(dia.length() - 2, dia.length());

                    dataBase = "2020" + mesAtual + dia;

                    dataInicial = dataBase + "0000";
                    dataFinal   = dataBase + "2359";

                    duAData = basicFactory.getFactry((Activity) ContextSingleton.getContext()).createSelectFactory().buscarDadosUsoAplicativoByDataIdAplicativo(Util.formatarDataHoraVolta(Long.parseLong(dataInicial)), Util.formatarDataHoraVolta(Long.parseLong(dataFinal)), a.getId());

                    horaMinutoAplicativo = Util.calcularTempoDadosUso(Util.formatarDataVolta(Long.parseLong(dataBase)), Util.formatarDataVolta(Long.parseLong(dataBase)), (List<DataInicialFinal>) ((List<? extends DataInicialFinal>)duAData));

                    configuracaoTempoAplicativo = basicFactory.getFactry((Activity) ContextSingleton.getContext()).createSelectFactory().buscarConfiguracaoTempoAplicativoByIdAplicativo(a.getId());

                    if (Util.calcularMinutosDeHoras(horaMinutoAplicativo) > Util.calcularMinutosDeHoras(configuracaoTempoAplicativo.getTempoDiario())){
                        notificacaoAplicativo = new NotificacaoAplicativo();
                        notificacaoAplicativo.setIdAplicativo(a.getId());
                        notificacaoAplicativo.setIdConfiguracao(configuracaoTempoAplicativo.getId());
                        notificacaoAplicativo.setData(Util.formatarDataVolta(Long.parseLong(dataBase)));
                        notificacaoAplicativo.setTitulo("Tempo diario atingido");
                        notificacaoAplicativo.setDescricao("Aplicaivo " + a.getNome() + " atingiu o limite de tempo configurado " + Util.calcularDiaHoraMinutiDeMinutos(Util.calcularMinutosDeHoras(configuracaoTempoAplicativo.getTempoDiario())) + " com " + Util.calcularDiaHoraMinutiDeMinutos(Util.calcularMinutosDeHoras(horaMinutoAplicativo)));
                        notificacaoAplicativo.setStatus("E");

                        idNewRow = basicFactory.getFactry((Activity) ContextSingleton.getContext()).createInsertFactory().inserir(notificacaoAplicativo);

                        notificacaoAplicativo = basicFactory.getFactry((Activity) ContextSingleton.getContext()).createSelectFactory().buscarNotificacaoAplicativoById(idNewRow, a.getId(), configuracaoTempoAplicativo.getId());
                        notificacaoAplicativos.add(notificacaoAplicativo);
                    }

                    for (DadosUsoAplicativo duaCuntinuo : duAData) {
                        horaMinutoAplicativo = Util.calcularTempoDadosUso(Util.formatarDataVolta(Long.parseLong(dataBase)), Util.formatarDataVolta(Long.parseLong(dataBase)), duaCuntinuo);
                        if (Util.calcularMinutosDeHoras(horaMinutoAplicativo) > Util.calcularMinutosDeHoras(configuracaoTempoAplicativo.getTempoContinuo())){
                            notificacaoAplicativo = new NotificacaoAplicativo();
                            notificacaoAplicativo.setIdAplicativo(a.getId());
                            notificacaoAplicativo.setIdConfiguracao(configuracaoTempoAplicativo.getId());
                            notificacaoAplicativo.setData(Util.formatarDataVolta(Long.parseLong(dataBase)));
                            notificacaoAplicativo.setTitulo("Tempo continuo atingido");
                            notificacaoAplicativo.setDescricao("Aplicaivo " + a.getNome() + " atingiu o limite de tempo configurado " + Util.calcularDiaHoraMinutiDeMinutos(Util.calcularMinutosDeHoras(configuracaoTempoAplicativo.getTempoContinuo())) + " com " + Util.calcularDiaHoraMinutiDeMinutos(Util.calcularMinutosDeHoras(horaMinutoAplicativo)));
                            notificacaoAplicativo.setStatus("E");

                            idNewRow = basicFactory.getFactry((Activity) ContextSingleton.getContext()).createInsertFactory().inserir(notificacaoAplicativo);
                            notificacaoAplicativo = basicFactory.getFactry((Activity) ContextSingleton.getContext()).createSelectFactory().buscarNotificacaoAplicativoById(idNewRow, a.getId(), configuracaoTempoAplicativo.getId());
                            notificacaoAplicativos.add(notificacaoAplicativo);
                        }
                    }
                }
            }

//            customDialogMensagem = new CustomDialogMensagem(MainActivityTesteNotificacaoGradeGrafico.this,"Registros em notificações de aplicativos: " + basicFactory.getFactry(MainActivityTesteNotificacaoGradeGrafico.this).createSelectFactory().buscarNotificacaoAplicativoAll(null).size());
//            customDialogMensagem.show();
        }

        private void populateDadosUsoSistema(){
            DadosUsoSistema dadosUsoSistema;
            long idNewRow;
            dadosUsoSistemas = new LinkedList<>();
            int horaInicio, horaFim;
            String dia, dataInicio, dataFim, hora;

            for (int i = 1; i <= ultimoDiaMesAtual; i++){
                dia = ("00" + Integer.toString(i));
                dia = dia.substring(dia.length()-2,dia.length());

                dataInicio = "2020" + mesAtual + dia;
                dataFim = "2020" + mesAtual + dia;
                horaInicio = Math.abs(new Random().nextInt(1440) - 61);
                horaFim = new Random().nextInt(1440 - horaInicio) + 1 + horaInicio;

                hora = Util.calcularHorasDeMinutos(horaInicio);
                dataInicio = dataInicio + hora.substring(0,2) + hora.substring(3,5);

                hora = Util.calcularHorasDeMinutos(horaFim);
                dataFim = dataFim + hora.substring(0,2) + hora.substring(3,5);

//                    customDialogMensagem = new CustomDialogMensagem(MainActivityTesteNotificacaoGradeGrafico.this,"Data inicial: " + dataInicio + " Data final: " + dataFim);
//                    customDialogMensagem.show();

                dadosUsoSistema = new DadosUsoSistema();
                dadosUsoSistema.setIdSistema(sistema.getId());
                dadosUsoSistema.setDataInicial(Util.formatarDataHoraVolta(Long.parseLong(dataInicio)));
                dadosUsoSistema.setDataFinal(Util.formatarDataHoraVolta(Long.parseLong(dataFim)));

                idNewRow = basicFactory.getFactry((Activity) ContextSingleton.getContext()).createInsertFactory().inserir(dadosUsoSistema);

                dadosUsoSistema = basicFactory.getFactry((Activity) ContextSingleton.getContext()).createSelectFactory().buscarDadosUsoSistemaById(idNewRow, sistema.getId());
                dadosUsoSistemas.add(dadosUsoSistema);
            }

//            customDialogMensagem = new CustomDialogMensagem(MainActivityTesteNotificacaoGradeGrafico.this,"Registros em dados de uso do sistema: " + basicFactory.getFactry(MainActivityTesteNotificacaoGradeGrafico.this).createSelectFactory().buscarDadosUsoSistemaAll().size());
//            customDialogMensagem.show();
        }

        private void populateDadosUsoAplicativo(){
            DadosUsoAplicativo dadosUsoAplicativo;
            long idNewRow;
            dadosUsoAplicativos = new LinkedList<>();
            int horaInicio, horaFim;
            String dia, dataInicio, dataFim, hora;

            for (int i = 1; i <= ultimoDiaMesAtual; i++){
                for (int j = 0; j < aplicativos.size(); j++){
                    dia = ("00" + Integer.toString(i));
                    dia = dia.substring(dia.length()-2,dia.length());

                    dataInicio = "2020" + mesAtual + dia;
                    dataFim = "2020" + mesAtual + dia;
                    horaInicio = Math.abs(new Random().nextInt(1440) - 61);
                    horaFim = new Random().nextInt(1440 - horaInicio) + 1 + horaInicio;

                    hora = Util.calcularHorasDeMinutos(horaInicio);
                    dataInicio = dataInicio + hora.substring(0,2) + hora.substring(3,5);

                    hora = Util.calcularHorasDeMinutos(horaFim);
                    dataFim = dataFim + hora.substring(0,2) + hora.substring(3,5);

//                    customDialogMensagem = new CustomDialogMensagem(MainActivityTesteNotificacaoGradeGrafico.this,"Data inicial: " + dataInicio + " Data final: " + dataFim);
//                    customDialogMensagem.show();

                    dadosUsoAplicativo = new DadosUsoAplicativo();
                    dadosUsoAplicativo.setIdAplicativo(aplicativos.get(j).getId());
                    dadosUsoAplicativo.setDataInicial(Util.formatarDataHoraVolta(Long.parseLong(dataInicio)));
                    dadosUsoAplicativo.setDataFinal(Util.formatarDataHoraVolta(Long.parseLong(dataFim)));

                    idNewRow = basicFactory.getFactry((Activity) ContextSingleton.getContext()).createInsertFactory().inserir(dadosUsoAplicativo);

                    dadosUsoAplicativo = basicFactory.getFactry((Activity) ContextSingleton.getContext()).createSelectFactory().buscarDadosUsoAplicativoById(idNewRow, aplicativos.get(j).getId());
                    dadosUsoAplicativos.add(dadosUsoAplicativo);
                }
            }

//            customDialogMensagem = new CustomDialogMensagem(MainActivityTesteNotificacaoGradeGrafico.this,"Registros em dados de uso de aplicativos: " + basicFactory.getFactry(MainActivityTesteNotificacaoGradeGrafico.this).createSelectFactory().buscarDadosUsoAplicativoAll().size());
//            customDialogMensagem.show();
        }

        private void popularConfiguracaoTempoSistema(){
            ConfiguracaoTempoSistema configuracaoTempoSistema;
            long idNewRow;
            configuracaoTempoSistemas = new LinkedList<>();
            int tempoDiario, tempoContinuo;

            configuracaoTempoSistema = new ConfiguracaoTempoSistema();

            tempoDiario = new Random().nextInt(1380) + 61;
            tempoContinuo = new Random().nextInt(tempoDiario) + 1;

            configuracaoTempoSistema.setIdSistema(sistema.getId());
            configuracaoTempoSistema.setTempoDiario(Util.calcularHorasDeMinutos(tempoDiario));
            configuracaoTempoSistema.setTempoContinuo(Util.calcularHorasDeMinutos(tempoContinuo));

            idNewRow = basicFactory.getFactry((Activity) ContextSingleton.getContext()).createInsertFactory().inserir(configuracaoTempoSistema);

            configuracaoTempoSistema = basicFactory.getFactry((Activity) ContextSingleton.getContext()).createSelectFactory().buscarConfiguracaoTempoSistemaByIdSistema(sistema.getId());
            configuracaoTempoSistemas.add(configuracaoTempoSistema);

//            customDialogMensagem = new CustomDialogMensagem(MainActivityTesteNotificacaoGradeGrafico.this,"Id: " + idNewRow + " Sis: " + configuracaoTempoSistema.getIdSistema() + " Diario: " + configuracaoTempoSistema.getTempoDiario() + " Continuo: " + configuracaoTempoSistema.getTempoContinuo());
//            customDialogMensagem.show();
        }

        private void popularConfiguracaoTempoAplicativo(){
            ConfiguracaoTempoAplicativo configuracaoTempoAplicativo;
            long idNewRow;
            configuracaoTempoAplicativos = new LinkedList<>();
            int tempoDiario, tempoContinuo;


            for (int j = 0; j < aplicativos.size(); j++){
                configuracaoTempoAplicativo = new ConfiguracaoTempoAplicativo();

                tempoDiario = new Random().nextInt(1380) + 61;
                tempoContinuo = new Random().nextInt(tempoDiario) + 1;

//                customDialogMensagem = new CustomDialogMensagem(MainActivityTesteNotificacaoGradeGrafico.this,"Tempo diario: " + tempoDiario + " - " + Util.calcularHorasDeMinutos(tempoDiario) + " Tempo continuo: " + tempoContinuo + " - " + Util.calcularHorasDeMinutos(tempoContinuo));
//                customDialogMensagem.show();

                configuracaoTempoAplicativo.setIdAplicativo(aplicativos.get(j).getId());
                configuracaoTempoAplicativo.setTempoDiario(Util.calcularHorasDeMinutos(tempoDiario));
                configuracaoTempoAplicativo.setTempoContinuo(Util.calcularHorasDeMinutos(tempoContinuo));

                idNewRow = basicFactory.getFactry((Activity) ContextSingleton.getContext()).createInsertFactory().inserir(configuracaoTempoAplicativo);

                configuracaoTempoAplicativo = basicFactory.getFactry((Activity) ContextSingleton.getContext()).createSelectFactory().buscarConfiguracaoTempoAplicativoByIdAplicativo(aplicativos.get(j).getId());
                configuracaoTempoAplicativos.add(configuracaoTempoAplicativo);

//                customDialogMensagem = new CustomDialogMensagem(MainActivityTesteNotificacaoGradeGrafico.this,"Id: " + idNewRow + " App: " + configuracaoTempoAplicativo.getIdAplicativo() + " Diario: " + configuracaoTempoAplicativo.getTempoDiario() + " Continuo: " + configuracaoTempoAplicativo.getTempoContinuo());
//                customDialogMensagem.show();
            }
        }

        private void popularAplicativo(){
            Aplicativo aplicativo;
            long idNewRow;
            aplicativos = new LinkedList<>();

            for (int i = 1; i <= 30; i++){
                aplicativo = new Aplicativo();

                aplicativo.setIdSistema(sistema.getId());
                aplicativo.setNome("Devil " + Integer.toString(i));
                aplicativo.setAtivo("S");

                idNewRow = basicFactory.getFactry((Activity) ContextSingleton.getContext()).createInsertFactory().inserir(aplicativo);

                aplicativo = basicFactory.getFactry((Activity) ContextSingleton.getContext()).createSelectFactory().buscarAplicativoByIdAplicativo(idNewRow);
                aplicativos.add(aplicativo);

//                customDialogMensagem = new CustomDialogMensagem(MainActivityTesteNotificacaoGradeGrafico.this,"Id: " + idNewRow + " App: " + aplicativo.getNome() + " Ativo: " + aplicativo.getAtivo());
//                customDialogMensagem.show();
            }

        }

        private void pupularChecagemSistema(){
            ChecagemSistema checagemSistema;
            String dia;
            long idNewRow;
            checagemSistemas = new LinkedList<>();

            for (int i = 1; i <= 30; i++){
                checagemSistema = new ChecagemSistema();

                dia = ("00" + Integer.toString(i));
                dia = dia.substring(dia.length()-2,dia.length());

                checagemSistema.setIdSistema(sistema.getId());
                checagemSistema.setData(Util.formatarDataVolta(Long.parseLong("202005" + dia)));
                checagemSistema.setQuantidade(new Random().nextInt(100)+1);

                idNewRow = basicFactory.getFactry((Activity) ContextSingleton.getContext()).createInsertFactory().inserir(checagemSistema);

                checagemSistema = basicFactory.getFactry((Activity) ContextSingleton.getContext()).createSelectFactory().buscarChecagemSistemaById(idNewRow, sistema.getId());
                checagemSistemas.add(checagemSistema);

//                customDialogMensagem = new CustomDialogMensagem(MainActivityTesteNotificacaoGradeGrafico.this,"Id: " + idNewRow + " Data: " + new SimpleDateFormat("dd/MM/yyyy").format(checagemSistema.getData()) + " Qtde: " + checagemSistema.getQuantidade());
//                customDialogMensagem.show();
            }
        }

        private void popularSistema(){
            sistema = new Sistema();
            sistema.setId(1L);

            long idNewRow;
            idNewRow = basicFactory.getFactry((Activity) ContextSingleton.getContext()).createInsertFactory().inserir(sistema);

//            customDialogMensagem = new CustomDialogMensagem(MainActivityTesteNotificacaoGradeGrafico.this,"New id: " + idNewRow);
//            customDialogMensagem.show();

            sistema = basicFactory.getFactry((Activity) ContextSingleton.getContext()).createSelectFactory().buscarSistemaById(idNewRow);
        }
    }

    private void init(View view){
        buttonPopularBanco = (Button)view.findViewById(R.id.buttonPopularBanco);

        buttonPopularBanco.setOnClickListener(new OnClickPopularBanco());

    }
}
