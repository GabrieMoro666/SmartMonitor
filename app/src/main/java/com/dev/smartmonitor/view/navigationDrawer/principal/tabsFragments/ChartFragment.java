package com.dev.smartmonitor.view.navigationDrawer.principal.tabsFragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.anychart.AnyChartView;
import com.dev.smartmonitor.R;
import com.dev.smartmonitor.business.chart.ChartFactoryCreator;


public class ChartFragment extends Fragment {

    private Context context;
    private Spinner spinnerFiltroPeriodo;
    private EditText editTextTempoUsoAplicativo;
    private EditText editTextChecagem;
    private String[] listSpinnerFiltroPeriodo;
    private ArrayAdapter<String> spinnerAdapter;
    private AnyChartView anyChartView;

    public ChartFragment(Context context) {
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chart, container, false);

        init(view);

        return view;
    }

    class OnItemSelectedSpinnerFiltroPeriodo implements AdapterView.OnItemSelectedListener{

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            ChartFactoryCreator chartFactory = new ChartFactoryCreator();

            switch (position){
                case 0:
                    anyChartView.setChart(chartFactory.getFactry(context).construirGraficoDeColunasDia());
                    editTextTempoUsoAplicativo.setText(chartFactory.getFactry(context).calcularTempoUsoSistemaDia());
                    editTextChecagem.setText(chartFactory.getFactry(context).calcularChecagemSistemaDia());
                    break;
                case 1:
                    anyChartView.setChart(chartFactory.getFactry(context).construirGraficoDeColunasSemana());
                    editTextTempoUsoAplicativo.setText(chartFactory.getFactry(context).calcularTempoUsoSistemaSemana());
                    editTextChecagem.setText(chartFactory.getFactry(context).calcularChecagemSistemaSemana());
                    break;
                case 2:
                    anyChartView.setChart(chartFactory.getFactry(context).construirGraficoDeColunasMes());
                    editTextTempoUsoAplicativo.setText(chartFactory.getFactry(context).calcularTempoUsoSistemaMes());
                    editTextChecagem.setText(chartFactory.getFactry(context).calcularChecagemSistemaMes());
                    break;
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    private void init(View view){
        spinnerFiltroPeriodo = (Spinner)view.findViewById(R.id.spinnerPeriodoChart);
        editTextTempoUsoAplicativo = (EditText)view.findViewById(R.id.editTextUsoDispositivoChart);
        editTextChecagem = (EditText)view.findViewById(R.id.editTextChecagemChart);

        editTextTempoUsoAplicativo.setEnabled(false);
        editTextChecagem.setEnabled(false);

        listSpinnerFiltroPeriodo = new String[] {"Dia", "Semana", "Mês"};

        spinnerAdapter = new ArrayAdapter<String>(context, R.layout.support_simple_spinner_dropdown_item, listSpinnerFiltroPeriodo);
        spinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerFiltroPeriodo.setAdapter(spinnerAdapter);

        anyChartView = (AnyChartView) view.findViewById(R.id.anyChartView);

        spinnerFiltroPeriodo.setOnItemSelectedListener(new OnItemSelectedSpinnerFiltroPeriodo());
    }
}
