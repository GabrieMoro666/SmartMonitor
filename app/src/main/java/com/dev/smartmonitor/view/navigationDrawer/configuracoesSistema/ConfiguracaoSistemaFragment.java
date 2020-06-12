package com.dev.smartmonitor.view.navigationDrawer.configuracoesSistema;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.dev.smartmonitor.business.basic.basic.BasicFactoryCreator;
import com.dev.smartmonitor.business.configuracao.configuracao.ConfiguracaoFactoryCreator;
import com.dev.smartmonitor.persistence.dao.model.Aplicativo;
import com.dev.smartmonitor.persistence.dao.model.ConfiguracaoTempoAplicativo;
import com.dev.smartmonitor.persistence.dao.model.ConfiguracaoTempoSistema;
import com.dev.smartmonitor.util.ContextSingleton;
import com.dev.smartmonitor.R;
import com.dev.smartmonitor.util.Util;
import com.dev.smartmonitor.view.view.CustomDialogMensagem;
import com.dev.smartmonitor.view.view.CustomDialogTimePicker;

public class ConfiguracaoSistemaFragment extends Fragment {

    private Context context;
    private EditText editTextTempoDiario;
    private EditText editTextTempoContinuo;
    private ConfiguracaoFactoryCreator configuracaoFactory;
    private ConfiguracaoTempoSistema configuracaoTempoSistema;
    private CustomDialogMensagem customDialogMensagem;
    private BasicFactoryCreator basicFactory;

    public ConfiguracaoSistemaFragment(){
        this.context = ContextSingleton.getContext();
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_configuracao_sistema, container, false);

        init(view);

        return view;
    }

    class OnClickTempoDiario implements View.OnClickListener, CustomDialogTimePicker.CustomDialogTimePickerListener{

        @Override
        public void onClick(View v) {
            int hora, minuto;

            hora = Integer.parseInt(editTextTempoDiario.getText().toString().substring(0,2));
            minuto = Integer.parseInt(editTextTempoDiario.getText().toString().substring(3,5));

            CustomDialogTimePicker customDialogTimePicker = new CustomDialogTimePicker((Activity) context, this, hora, minuto);
            customDialogTimePicker.show();
        }

        @Override
        public void applyReturnTimePicker(String horaMinuto) {
            if (verificarTempo(horaMinuto)) {
                editTextTempoDiario.setText(horaMinuto);
                configuracaoTempoSistema.setTempoDiario(horaMinuto);
                configuracaoFactory.getFactryConfiguracaoSistema(context).updateConfiguracaoSitema(configuracaoTempoSistema.getIdSistema(), configuracaoTempoSistema.getTempoDiario(), configuracaoTempoSistema.getTempoContinuo());
            }
        }

        public boolean verificarTempo(String tempoDiario){
            Aplicativo aplicativo;

            if (Util.calcularMinutosDeHoras(editTextTempoContinuo.getText().toString()) != 0 && Util.calcularMinutosDeHoras(tempoDiario) != 0) {
                if (Util.calcularMinutosDeHoras(tempoDiario) < Util.calcularMinutosDeHoras(editTextTempoContinuo.getText().toString())){
                    customDialogMensagem = new CustomDialogMensagem((Activity) context, "Valor inválido, deve ser maior ou igual ao tempo continuo!");
                    customDialogMensagem.show();
                    return false;
                } else {
                    if ((aplicativo = verificarTempoAplicativos(tempoDiario, editTextTempoContinuo.getText().toString())) != null){
                        customDialogMensagem = new CustomDialogMensagem((Activity) context, "Valor inválido, verifique a configuração do aplicativo " + aplicativo.getNome()+ "!");
                        customDialogMensagem.show();
                        return false;
                    }
                    return true;
                }
            } else {
                if ((aplicativo = verificarTempoAplicativos(tempoDiario, editTextTempoContinuo.getText().toString())) != null){
                    customDialogMensagem = new CustomDialogMensagem((Activity) context, "Valor inválido, verifique a configuração do aplicativo " + aplicativo.getNome()+ "!");
                    customDialogMensagem.show();
                    return false;
                }
                return true;
            }
        }

        public Aplicativo verificarTempoAplicativos(String tempoDiario, String tempoContinuo){
            ConfiguracaoTempoAplicativo configuracaoTempoAplicativo = null;
            Aplicativo aplicativo = null;

            if (Util.calcularMinutosDeHoras(tempoDiario) != 0) {
                configuracaoTempoAplicativo = configuracaoFactory.getFactryConfiguracaoSistema(context).verificarConfiguracaoAplicativosTempoDiario(tempoDiario);
            }

            if (configuracaoTempoAplicativo == null) {
                if (Util.calcularMinutosDeHoras(tempoContinuo) != 0) {
                    configuracaoTempoAplicativo = configuracaoFactory.getFactryConfiguracaoSistema(context).verificarConfiguracaoAplicativosTempoContinuo(tempoContinuo);
                }
            }

            if (configuracaoTempoAplicativo != null) {
                aplicativo = basicFactory.getFactry(context).createSelectFactory().buscarAplicativoByIdAplicativo(configuracaoTempoAplicativo.getIdAplicativo());
            }

            return aplicativo;
        }
    }

    class OnClickTempoContinuo implements View.OnClickListener, CustomDialogTimePicker.CustomDialogTimePickerListener{

        @Override
        public void onClick(View v) {
            int hora, minuto;

            hora = Integer.parseInt(editTextTempoContinuo.getText().toString().substring(0,2));
            minuto = Integer.parseInt(editTextTempoContinuo.getText().toString().substring(3,5));

            CustomDialogTimePicker customDialogTimePicker = new CustomDialogTimePicker((Activity) context, this, hora, minuto);
            customDialogTimePicker.show();
        }

        @Override
        public void applyReturnTimePicker(String horaMinuto) {
            if (verificarTempo(horaMinuto)) {
                editTextTempoContinuo.setText(horaMinuto);
                configuracaoTempoSistema.setTempoContinuo(horaMinuto);
                configuracaoFactory.getFactryConfiguracaoSistema(context).updateConfiguracaoSitema(configuracaoTempoSistema.getIdSistema(), configuracaoTempoSistema.getTempoDiario(), configuracaoTempoSistema.getTempoContinuo());
            }
        }

        public boolean verificarTempo(String tempoContinuo) {
            Aplicativo aplicativo;

            if (Util.calcularMinutosDeHoras(editTextTempoDiario.getText().toString()) != 0 && Util.calcularMinutosDeHoras(tempoContinuo) != 0) {
                if (Util.calcularMinutosDeHoras(tempoContinuo) > Util.calcularMinutosDeHoras(editTextTempoDiario.getText().toString())) {
                    customDialogMensagem = new CustomDialogMensagem((Activity) context, "Valor inválido, deve ser menor ou igual ao tempo diario!");
                    customDialogMensagem.show();
                    return false;
                } else {
                    if ((aplicativo = verificarTempoAplicativos(editTextTempoDiario.getText().toString(), tempoContinuo)) != null){
                        customDialogMensagem = new CustomDialogMensagem((Activity) context, "Valor inválido, verifique a configuração do aplicativo " + aplicativo.getNome()+ "!");
                        customDialogMensagem.show();
                        return false;
                    }
                    return true;
                }
            } else {
                if ((aplicativo = verificarTempoAplicativos(editTextTempoDiario.getText().toString(), tempoContinuo)) != null){
                    customDialogMensagem = new CustomDialogMensagem((Activity) context, "Valor inválido, verifique a configuração do aplicativo " + aplicativo.getNome()+ "!");
                    customDialogMensagem.show();
                    return false;
                }
                return true;
            }
        }

        public Aplicativo verificarTempoAplicativos(String tempoDiario, String tempoContinuo){
            ConfiguracaoTempoAplicativo configuracaoTempoAplicativo = null;
            Aplicativo aplicativo = null;

            if (Util.calcularMinutosDeHoras(tempoDiario) != 0) {
                configuracaoTempoAplicativo = configuracaoFactory.getFactryConfiguracaoSistema(context).verificarConfiguracaoAplicativosTempoDiario(tempoDiario);
            }

            if (configuracaoTempoAplicativo == null) {
                if (Util.calcularMinutosDeHoras(tempoContinuo) != 0) {
                    configuracaoTempoAplicativo = configuracaoFactory.getFactryConfiguracaoSistema(context).verificarConfiguracaoAplicativosTempoContinuo(tempoContinuo);
                }
            }

            if (configuracaoTempoAplicativo != null) {
                aplicativo = basicFactory.getFactry(context).createSelectFactory().buscarAplicativoByIdAplicativo(configuracaoTempoAplicativo.getIdAplicativo());
            }

            return aplicativo;
        }
    }

    void init (View view) {
        editTextTempoDiario = (EditText)view.findViewById(R.id.editTextConfiguracaoSistemaDiario);
        editTextTempoContinuo = (EditText)view.findViewById(R.id.editTextConfiguracaoSistemaContinuo);

        editTextTempoDiario.clearFocus();
        editTextTempoDiario.setFocusable(false);

        editTextTempoContinuo.clearFocus();
        editTextTempoContinuo.setFocusable(false);

        editTextTempoDiario.setOnClickListener(new OnClickTempoDiario());
        editTextTempoContinuo.setOnClickListener(new OnClickTempoContinuo());

        configuracaoFactory = new ConfiguracaoFactoryCreator();
        configuracaoTempoSistema = configuracaoFactory.getFactryConfiguracaoSistema(context).construirConfiguracaoSistema(1L);

        editTextTempoDiario.setText(configuracaoTempoSistema.getTempoDiario());
        editTextTempoContinuo.setText(configuracaoTempoSistema.getTempoContinuo());

        basicFactory = new BasicFactoryCreator();
    }

}
