package com.dev.smartmonitor.view.navigationDrawer.configuracoesSistema;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.dev.smartmonitor.util.ContextSingleton;
import com.dev.smartmonitor.R;
import com.dev.smartmonitor.view.view.CustomDialogMensagem;

public class ConfiguracaoSistemaFragment extends Fragment {

    private ConfiguracaoSistemaViewModel configuracaoSistemaViewModel;

    public ConfiguracaoSistemaFragment(){
        CustomDialogMensagem customDialogMensagem = new CustomDialogMensagem((Activity) ContextSingleton.getContext(), "Teste");
        //customDialogMensagem.show();
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_configuracao_sistema, container, false);




        return root;
    }
}
