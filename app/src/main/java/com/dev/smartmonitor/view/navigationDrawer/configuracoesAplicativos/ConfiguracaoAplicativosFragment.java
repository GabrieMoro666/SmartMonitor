package com.dev.smartmonitor.view.navigationDrawer.configuracoesAplicativos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.dev.smartmonitor.R;

public class ConfiguracaoAplicativosFragment extends Fragment {

    private ConfiguracaoAplicativosViewModel configuracaoAplicativosViewModel;

    public ConfiguracaoAplicativosFragment(){

    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        configuracaoAplicativosViewModel = ViewModelProviders.of(this).get(ConfiguracaoAplicativosViewModel.class);

        View root = inflater.inflate(R.layout.fragment_configuracao_aplicativos, container, false);

//        final TextView textView = root.findViewById(R.id.text_slideshow);
//        configuracaoAplicativosViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

        return root;
    }
}
