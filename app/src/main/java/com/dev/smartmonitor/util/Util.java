package com.dev.smartmonitor.util;

import com.dev.smartmonitor.persistence.dao.model.ChecagemSistema;
import com.dev.smartmonitor.persistence.dao.model.DataInicialFinal;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Util {

    private Util(){}

    public static long formatarDataPara(Date data){
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");

        return Long.parseLong(format.format(data));
    }

    public static long formatarDataHoraPara(Date data){
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmm");

        return Long.parseLong(format.format(data));
    }

    public static Date formatarDataVolta(Long dataLong){
        String dataString;
        Date data;
        Calendar calendar = Calendar.getInstance();

        dataString = Long.toString(dataLong);

        calendar.set(Calendar.DAY_OF_MONTH,Integer.parseInt(dataString.substring(6,8)));
        calendar.set(Calendar.MONTH,Integer.parseInt(dataString.substring(4,6))-1);
        calendar.set(Calendar.YEAR,Integer.parseInt(dataString.substring(0,4)));

        data = calendar.getTime();

        return data;
    }

    public static Date formatarDataHoraVolta(Long dataLong){
        String dataString;
        Date data;
        Calendar calendar = Calendar.getInstance();

        dataString = Long.toString(dataLong);

        calendar.set(Calendar.DAY_OF_MONTH,Integer.parseInt(dataString.substring(6,8)));
        calendar.set(Calendar.MONTH,Integer.parseInt(dataString.substring(4,6))-1);
        calendar.set(Calendar.YEAR,Integer.parseInt(dataString.substring(0,4)));

        calendar.set(Calendar.HOUR_OF_DAY,Integer.parseInt(dataString.substring(8,10)));
        calendar.set(Calendar.MINUTE,Integer.parseInt(dataString.substring(10,12)));
        calendar.set(Calendar.SECOND,0);

        data = calendar.getTime();

        return data;
    }

    public static int calcularMinutosDeHoras(String horasMinutos) {
        int horas, minutos, minutosCalculados;

        horas = Integer.parseInt(horasMinutos.substring(0,2));
        minutos = Integer.parseInt(horasMinutos.substring(3,5));

        minutosCalculados = (horas * 60) + minutos;

        return minutosCalculados;
    }

    public static String calcularHorasDeMinutos(int minutos) {
        int horasCalculadas, minutosCalculos;
        String horasRet, minutosRet;

        horasCalculadas = minutos / 60;
        minutosCalculos = (int) ((((float)minutos / 60)-horasCalculadas) * 60);

        horasRet = ("00"+ Integer.toString(horasCalculadas));
        horasRet = horasRet.substring(horasRet.length()-2,horasRet.length());

        minutosRet = ("00"+ Integer.toString(minutosCalculos));
        minutosRet = minutosRet.substring(minutosRet.length()-2,minutosRet.length());

        return horasRet + ":" + minutosRet;
    }

    public static int calcularMinutosDeDuasDatas(Date dataInicial, Date dataFinal) {
        int minutosCalculados;
        long milliSecondsElapsed = dataFinal.getTime() - dataInicial.getTime();

        minutosCalculados = (int) TimeUnit.MINUTES.convert(milliSecondsElapsed, TimeUnit.MILLISECONDS);

        return minutosCalculados;
    }

    public static Date calcularPrimeiraDataMes(Date data){
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(data);

        calendar.set(Calendar.DAY_OF_MONTH, 1);

        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);

        data = calendar.getTime();

        return data;
    }

    public static Date calcularPrimeiraDataMes(){
        Date data = new Date(System.currentTimeMillis());
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(data);

        calendar.set(Calendar.DAY_OF_MONTH, 1);

        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);

        data = calendar.getTime();

        return data;
    }

    public static Date calcularUltimaDataMes(Date data){
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(data);

        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));

        calendar.set(Calendar.HOUR_OF_DAY,23);
        calendar.set(Calendar.MINUTE,59);
        calendar.set(Calendar.SECOND,0);

        data = calendar.getTime();

        return data;
    }

    public static Date calcularUltimaDataMes(){
        Date data = new Date(System.currentTimeMillis());
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(data);

        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));

        calendar.set(Calendar.HOUR_OF_DAY,23);
        calendar.set(Calendar.MINUTE,59);
        calendar.set(Calendar.SECOND,0);

        data = calendar.getTime();

        return data;
    }

    public static Date calcularPrimeiraDataSemana(Date data){
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(data);

        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());

        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);

        data = calendar.getTime();

        return data;
    }

    public static Date calcularPrimeiraDataSemana(){
        Date data = new Date(System.currentTimeMillis());
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(data);

        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());

        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);

        data = calendar.getTime();

        return data;
    }

    public static Date calcularUltimaDataSemana(Date data){
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(data);

        calendar.set(Calendar.DAY_OF_WEEK, calendar.getActualMaximum(Calendar.DAY_OF_WEEK));

        calendar.set(Calendar.HOUR_OF_DAY,23);
        calendar.set(Calendar.MINUTE,59);
        calendar.set(Calendar.SECOND,0);

        data = calendar.getTime();

        return data;
    }

    public static Date calcularUltimaDataSemana(){
        Date data = new Date(System.currentTimeMillis());
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(data);

        calendar.set(Calendar.DAY_OF_WEEK, calendar.getActualMaximum(Calendar.DAY_OF_WEEK));

        calendar.set(Calendar.HOUR_OF_DAY,23);
        calendar.set(Calendar.MINUTE,59);
        calendar.set(Calendar.SECOND,0);

        data = calendar.getTime();

        return data;
    }

    public static Date calcularDataAtual(){
        Date data = new Date(System.currentTimeMillis());
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(data);

        calendar.set(Calendar.SECOND,0);

        data = calendar.getTime();

        return data;
    }

    public static Date calcularPrimeiraDataAtual(){
        Date data = new Date(System.currentTimeMillis());
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(data);

        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);

        data = calendar.getTime();

        return data;
    }

    public static Date calcularPrimeiraDataAtual(Date data){
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(data);

        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);

        data = calendar.getTime();

        return data;
    }

    public static Date calcularUltimaDataAtual(){
        Date data = new Date(System.currentTimeMillis());
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(data);

        calendar.set(Calendar.HOUR_OF_DAY,23);
        calendar.set(Calendar.MINUTE,59);
        calendar.set(Calendar.SECOND,0);

        data = calendar.getTime();

        return data;
    }

    public static Date calcularUltimaDataAtual(Date data){
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(data);

        calendar.set(Calendar.HOUR_OF_DAY,23);
        calendar.set(Calendar.MINUTE,59);
        calendar.set(Calendar.SECOND,0);

        data = calendar.getTime();

        return data;
    }

    public static String montarIn(String[] args){
        StringBuilder argsIn = new StringBuilder();

        for (String arg : args) {
            argsIn.append("'" + arg + "',");
        }

        argsIn.deleteCharAt(argsIn.length() - 1);

        return argsIn.toString();
    }

    public static String calcularTempoDadosUso(Date dataBaseInicial, Date dataBaseFinal, List<DataInicialFinal> dataInicialFinals){
        int minutosTotais = 0;

        for (DataInicialFinal dIF: dataInicialFinals) {
            if (formatarDataPara(dIF.getDataInicial()) >= formatarDataPara(dataBaseInicial) && Util.formatarDataPara(dIF.getDataFinal()) <= formatarDataPara(dataBaseFinal)){
                minutosTotais += calcularMinutosDeDuasDatas(dIF.getDataInicial(), dIF.getDataFinal());
            }else if (formatarDataPara(dIF.getDataInicial()) == formatarDataPara(dataBaseInicial)){
                minutosTotais += calcularMinutosDeDuasDatas(dIF.getDataInicial(), calcularUltimaDataAtual(dataBaseInicial));
            }else if (formatarDataPara(dataBaseFinal) == formatarDataPara(dIF.getDataFinal())){
                minutosTotais += calcularMinutosDeDuasDatas(calcularPrimeiraDataAtual(dataBaseFinal), dIF.getDataFinal());
            }
        }

        return calcularHorasDeMinutos(minutosTotais);
    }

    public static String calcularTempoDadosUso(Date dataBaseInicial, Date dataBaseFinal, DataInicialFinal dIF){
        int minutosTotais = 0;

        if (formatarDataPara(dIF.getDataInicial()) >= formatarDataPara(dataBaseInicial) && Util.formatarDataPara(dIF.getDataFinal()) <= formatarDataPara(dataBaseFinal)){
            minutosTotais += calcularMinutosDeDuasDatas(dIF.getDataInicial(), dIF.getDataFinal());
        }else if (formatarDataPara(dIF.getDataInicial()) == formatarDataPara(dataBaseInicial)){
            minutosTotais += calcularMinutosDeDuasDatas(dIF.getDataInicial(), calcularUltimaDataAtual(dataBaseInicial));
        }else if (formatarDataPara(dataBaseFinal) == formatarDataPara(dIF.getDataFinal())){
            minutosTotais += calcularMinutosDeDuasDatas(calcularPrimeiraDataAtual(dataBaseFinal), dIF.getDataFinal());
        }

        return calcularHorasDeMinutos(minutosTotais);
    }

    public static int calcularChecagemSistema(List<ChecagemSistema> checagemSistemas){
        int checagem = 0;

        for (ChecagemSistema c : checagemSistemas) {
            checagem += c.getQuantidade();
        }

        return checagem;
    }

    public static String calcularDiaHoraMinutiDeMinutos(int minutos){
        int diasCalculado, horasCalculado, minutosCalculado;
        String mensagemDiasCalculado, mensagemHorasCalculado, mensagemminutosCalculado, mensagem;

        diasCalculado = minutos / (24 * 60);
        horasCalculado = (minutos - (diasCalculado * 24 * 60)) / 60;
        minutosCalculado = minutos - ((diasCalculado * 24 * 60) + (horasCalculado * 60));

        mensagemDiasCalculado    = diasCalculado    > 0 ? diasCalculado    + " dia"    + (diasCalculado    > 1 ? "s" : "") : "";
        mensagemHorasCalculado   = horasCalculado   > 0 ? horasCalculado   + " hora"   + (horasCalculado   > 1 ? "s" : "") : "";
        mensagemminutosCalculado = minutosCalculado > 0 ? minutosCalculado + " minuto" + (minutosCalculado > 1 ? "s" : "") : "";

        mensagem = mensagemDiasCalculado;
        mensagem += !mensagem.isEmpty() ? (!mensagemHorasCalculado.isEmpty() ? " e " + mensagemHorasCalculado : "") : mensagemHorasCalculado;
        mensagem += !mensagem.isEmpty() ? (!mensagemminutosCalculado.isEmpty() ? " e " + mensagemminutosCalculado : "") : mensagemminutosCalculado;

        return mensagem;
    }

}
