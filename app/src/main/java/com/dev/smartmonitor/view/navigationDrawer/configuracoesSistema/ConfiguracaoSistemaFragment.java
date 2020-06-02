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

import com.dev.smartmonitor.business.configuracao.configuracao.ConfiguracaoFactoryCreator;
import com.dev.smartmonitor.persistence.dao.model.ConfiguracaoTempoSistema;
import com.dev.smartmonitor.util.ContextSingleton;
import com.dev.smartmonitor.R;
import com.dev.smartmonitor.view.view.CustomDialogMensagem;
import com.dev.smartmonitor.view.view.CustomDialogTimePicker;

public class ConfiguracaoSistemaFragment extends Fragment {

    private Context context;
    private EditText editTextTempoDiario;
    private EditText editTextTempoContinuo;
    private ConfiguracaoFactoryCreator configuracaoFactory;
    private ConfiguracaoTempoSistema configuracaoTempoSistema;

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
        configuracaoTempoSistema = configuracaoFactory.getFactryConfiguracaoSistema(context).construirConfiguracaoSistema();

        editTextTempoDiario.setText(configuracaoTempoSistema.getTempoDiario());
        editTextTempoContinuo.setText(configuracaoTempoSistema.getTempoContinuo());
    }

}
