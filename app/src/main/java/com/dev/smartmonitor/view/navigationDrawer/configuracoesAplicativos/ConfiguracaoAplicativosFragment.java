package com.dev.smartmonitor.view.navigationDrawer.configuracoesAplicativos;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dev.smartmonitor.R;
import com.dev.smartmonitor.business.configuracao.aplicativo.adapter.adapter.AdapterConfiguracaoAplicativo;
import com.dev.smartmonitor.business.configuracao.aplicativo.adapter.model.RowConfiguracaoAplicativo;
import com.dev.smartmonitor.business.configuracao.configuracao.ConfiguracaoFactoryCreator;
import com.dev.smartmonitor.util.ContextSingleton;
import com.dev.smartmonitor.view.view.CustomDialogMensagem;
import com.dev.smartmonitor.view.view.CustomDialogTimePicker;

import java.util.List;

public class ConfiguracaoAplicativosFragment extends Fragment {

    private Context context;
    private RecyclerView recyclerViewConfiguracaoAplicativo;
    private RecyclerView.Adapter adapterConfiguracaoAplicativo;
    private RecyclerView.LayoutManager layoutManagerAdapterConfiguracaoAplicativo;
    private List<RowConfiguracaoAplicativo> recyclerViewItems;

    public ConfiguracaoAplicativosFragment() {
        this.context = ContextSingleton.getContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_configuracao_aplicativos, container, false);

        init(view);

        return view;
    }

    private void init(View view){
        ConfiguracaoFactoryCreator configuracaoFactory = new ConfiguracaoFactoryCreator();

        recyclerViewConfiguracaoAplicativo = (RecyclerView)view.findViewById(R.id.recyclerViewConfiguracaoAplicativo);

        layoutManagerAdapterConfiguracaoAplicativo = new LinearLayoutManager(context);
        recyclerViewConfiguracaoAplicativo.setLayoutManager(layoutManagerAdapterConfiguracaoAplicativo);

        recyclerViewItems = configuracaoFactory.getFactryConfiguracaoAplicativo(context).construirConfiguracaoAplicativo();

        adapterConfiguracaoAplicativo = new AdapterConfiguracaoAplicativo(recyclerViewItems, context);
        recyclerViewConfiguracaoAplicativo.setAdapter(adapterConfiguracaoAplicativo);
        recyclerViewConfiguracaoAplicativo.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
    }

}
