package com.dev.smartmonitor.view.navigationDrawer.principal.tabsFragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.dev.smartmonitor.R;
import com.dev.smartmonitor.business.basic.basic.BasicFactoryCreator;
import com.dev.smartmonitor.business.table.adapter.AdapterTable;
import com.dev.smartmonitor.business.table.model.RowTable;
import com.dev.smartmonitor.business.table.table.TableFactoryCreator;
import com.dev.smartmonitor.persistence.dao.model.DadosUsoAplicativo;
import com.dev.smartmonitor.util.Util;
import com.dev.smartmonitor.view.view.CustomDialogMensagem;

import java.util.List;

public class TableFragment extends Fragment {

    private Context context;
    private Spinner spinnerFiltroPeriodo;
    private EditText editTextTempoUsoAplicativo;
    private EditText editTextChecagem;
    private String[] listSpinnerFiltroPeriodo;
    private ArrayAdapter<String> spinnerAdapter;
    private RecyclerView recyclerViewTable;
    private RecyclerView.Adapter adapterTable;
    private RecyclerView.LayoutManager layoutManagerAdapterTable;
    private List<RowTable> recyclerViewItems;

    public TableFragment(Context context) {
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_table, container, false);

        init(view);

        return view;
    }

    class OnItemSelectedSpinnerFiltroPeriodo implements AdapterView.OnItemSelectedListener{

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            TableFactoryCreator tableFactory = new TableFactoryCreator();

            switch (position){
                case 0:
                    recyclerViewItems = tableFactory.getFactry(context).construirTableDia();

                    adapterTable = new AdapterTable(recyclerViewItems);
                    recyclerViewTable.setAdapter(adapterTable);
                    recyclerViewTable.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));

                    editTextTempoUsoAplicativo.setText(tableFactory.getFactry(context).calcularTempoUsoSistemaDia());
                    editTextChecagem.setText(tableFactory.getFactry(context).calcularChecagemSistemaDia());
                    break;
                case 1:
                    recyclerViewItems = tableFactory.getFactry(context).construirTableSemana();
                    adapterTable = new AdapterTable(recyclerViewItems);
                    recyclerViewTable.setAdapter(adapterTable);
                    recyclerViewTable.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));

                    editTextTempoUsoAplicativo.setText(tableFactory.getFactry(context).calcularTempoUsoSistemaSemana());
                    editTextChecagem.setText(tableFactory.getFactry(context).calcularChecagemSistemaSemana());
                    break;
                case 2:
                    recyclerViewItems = tableFactory.getFactry(context).construirTableMes();
                    adapterTable = new AdapterTable(recyclerViewItems);
                    recyclerViewTable.setAdapter(adapterTable);
                    recyclerViewTable.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));

                    editTextTempoUsoAplicativo.setText(tableFactory.getFactry(context).calcularTempoUsoSistemaMes());
                    editTextChecagem.setText(tableFactory.getFactry(context).calcularChecagemSistemaMes());
                    break;
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    private void init(View view){
        spinnerFiltroPeriodo = (Spinner)view.findViewById(R.id.spinnerPeriodoTable);
        editTextTempoUsoAplicativo = (EditText)view.findViewById(R.id.editTextUsoDispositivoTable);
        editTextChecagem = (EditText)view.findViewById(R.id.editTextChecagemTable);

        editTextTempoUsoAplicativo.setEnabled(false);
        editTextChecagem.setEnabled(false);

        listSpinnerFiltroPeriodo = new String[] {"Dia", "Semana", "Mês"};

        spinnerAdapter = new ArrayAdapter<String>(context, R.layout.support_simple_spinner_dropdown_item, listSpinnerFiltroPeriodo);
        spinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerFiltroPeriodo.setAdapter(spinnerAdapter);

        recyclerViewTable = (RecyclerView)view.findViewById(R.id.recyclerViewTable);

        layoutManagerAdapterTable = new LinearLayoutManager(context);
        recyclerViewTable.setLayoutManager(layoutManagerAdapterTable);

        spinnerFiltroPeriodo.setOnItemSelectedListener(new OnItemSelectedSpinnerFiltroPeriodo());
    }
}
