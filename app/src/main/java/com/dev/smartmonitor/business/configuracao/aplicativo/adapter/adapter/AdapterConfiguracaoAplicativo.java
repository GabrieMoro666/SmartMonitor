package com.dev.smartmonitor.business.configuracao.aplicativo.adapter.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.dev.smartmonitor.R;
import com.dev.smartmonitor.business.configuracao.aplicativo.adapter.model.RowConfiguracaoAplicativo;
import com.dev.smartmonitor.business.configuracao.configuracao.ConfiguracaoFactoryCreator;
import com.dev.smartmonitor.persistence.dao.model.ConfiguracaoTempoSistema;
import com.dev.smartmonitor.util.Util;
import com.dev.smartmonitor.view.view.CustomDialogMensagem;
import com.dev.smartmonitor.view.view.CustomDialogTimePicker;

import java.util.List;

public class AdapterConfiguracaoAplicativo extends RecyclerView.Adapter {
    private List<RowConfiguracaoAplicativo> recyclerViewItems;
    private Context context;

    public AdapterConfiguracaoAplicativo(List<RowConfiguracaoAplicativo> recyclerViewItems, Context context) {
        this.recyclerViewItems = recyclerViewItems;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View recycleViewItem;

        recycleViewItem = inflater.inflate(R.layout.layout_adapter_configuracao_aplicativo_row, parent, false);
        return new RowConfiguracaoAplicativoHolder(recycleViewItem);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        RowConfiguracaoAplicativo rowConfiguracaoAplicativo = recyclerViewItems.get(position);

        RowConfiguracaoAplicativoHolder rowItemHolder = (RowConfiguracaoAplicativoHolder) holder;

        rowItemHolder.textViewAplicativo.setText(rowConfiguracaoAplicativo.getNome());
        rowItemHolder.imageView.setImageDrawable(rowConfiguracaoAplicativo.getDrawable());
        rowItemHolder.editTextTempoDiario.setText(rowConfiguracaoAplicativo.getTempoDiario());
        rowItemHolder.editTextTempoContinuo.setText(rowConfiguracaoAplicativo.getTempoContinuo());
    }

    public class RowConfiguracaoAplicativoHolder extends RecyclerView.ViewHolder {
        public TextView textViewAplicativo;
        public ImageView imageView;
        public EditText editTextTempoDiario;
        public EditText editTextTempoContinuo;
        private ConfiguracaoFactoryCreator configuracaoFactory;
        private ConfiguracaoTempoSistema configuracaoTempoSistema;

        public RowConfiguracaoAplicativoHolder(View itemView) {
            super(itemView);

            this.textViewAplicativo = (TextView)itemView.findViewById(R.id.textViewRowConfiguracaoAplicativoNome);
            this.imageView = (ImageView)itemView.findViewById(R.id.imageViewRowConfiguracaoAplicativoIcon);
            this.editTextTempoDiario = (EditText) itemView.findViewById(R.id.editTextRowConfiguracaoAplicativoDiario);
            this.editTextTempoContinuo = (EditText) itemView.findViewById(R.id.editTextRowConfiguracaoAplicativoContinuo);

            this.editTextTempoDiario.clearFocus();
            this.editTextTempoDiario.setFocusable(false);

            this.editTextTempoContinuo.clearFocus();
            this.editTextTempoContinuo.setFocusable(false);

            this.editTextTempoDiario.setOnClickListener(new OnClickEditTextDiario());
            this.editTextTempoContinuo.setOnClickListener(new OnClickEditTextContinuo());

            this.configuracaoFactory = new ConfiguracaoFactoryCreator();
            this.configuracaoTempoSistema = this.configuracaoFactory.getFactryConfiguracaoSistema(context).construirConfiguracaoSistema(1L);
        }

        class OnClickEditTextDiario implements View.OnClickListener, CustomDialogTimePicker.CustomDialogTimePickerListener  {

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
                ConfiguracaoFactoryCreator configuracaoFactory = new ConfiguracaoFactoryCreator();

                if (verificarTempo(horaMinuto)) {
                    editTextTempoDiario.setText(horaMinuto);
                    recyclerViewItems.get(getAdapterPosition()).setTempoDiario(horaMinuto);
                    configuracaoFactory.getFactryConfiguracaoAplicativo(context).updateConfiguracaoAplicativo(recyclerViewItems.get(getAdapterPosition()).getIdAplicativo(), editTextTempoDiario.getText().toString(), editTextTempoContinuo.getText().toString());
                }
            }

            public boolean verificarTempo(String tempoDiario){
                String tempoContinuo;
                CustomDialogMensagem customDialogMensagem;

                tempoContinuo = editTextTempoContinuo.getText().toString();

                if (Util.calcularMinutosDeHoras(tempoDiario) != 0 && Util.calcularMinutosDeHoras(tempoContinuo) != 0) {
                    if (Util.calcularMinutosDeHoras(tempoDiario) < Util.calcularMinutosDeHoras(tempoContinuo)){
                        customDialogMensagem = new CustomDialogMensagem((Activity) context, "Valor inválido, deve ser maior ou igual ao tempo continuo!");
                        customDialogMensagem.show();
                        return false;
                    } else {
                        if (Util.calcularMinutosDeHoras(configuracaoTempoSistema.getTempoDiario()) != 0) {
                            if (Util.calcularMinutosDeHoras(tempoDiario) > Util.calcularMinutosDeHoras(configuracaoTempoSistema.getTempoDiario())) {
                                customDialogMensagem = new CustomDialogMensagem((Activity) context, "Valor inválido, deve ser menor ou igual ao tempo diario do sistema!");
                                customDialogMensagem.show();
                                return false;
                            }
                        }
                        return true;
                    }
                } else {
                    if (Util.calcularMinutosDeHoras(configuracaoTempoSistema.getTempoDiario()) != 0) {
                        if (Util.calcularMinutosDeHoras(tempoDiario) > Util.calcularMinutosDeHoras(configuracaoTempoSistema.getTempoDiario())) {
                            customDialogMensagem = new CustomDialogMensagem((Activity) context, "Valor inválido, deve ser menor ou igual ao tempo diario do sistema!");
                            customDialogMensagem.show();
                            return false;
                        }
                    }
                    return true;
                }
            }
        }

        class OnClickEditTextContinuo implements View.OnClickListener, CustomDialogTimePicker.CustomDialogTimePickerListener  {

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
                ConfiguracaoFactoryCreator configuracaoFactory = new ConfiguracaoFactoryCreator();

                if (verificarTempo(horaMinuto)) {
                    editTextTempoContinuo.setText(horaMinuto);
                    recyclerViewItems.get(getAdapterPosition()).setTempoContinuo(horaMinuto);
                    configuracaoFactory.getFactryConfiguracaoAplicativo(context).updateConfiguracaoAplicativo(recyclerViewItems.get(getAdapterPosition()).getIdAplicativo(), editTextTempoDiario.getText().toString(), editTextTempoContinuo.getText().toString());
                }
            }

            public boolean verificarTempo(String tempoContinuo){
                String tempoDiario;
                CustomDialogMensagem customDialogMensagem;

                tempoDiario = editTextTempoDiario.getText().toString();

                if (Util.calcularMinutosDeHoras(tempoDiario) != 0 && Util.calcularMinutosDeHoras(tempoContinuo) != 0) {
                    if (Util.calcularMinutosDeHoras(tempoContinuo) > Util.calcularMinutosDeHoras(tempoDiario)){
                        customDialogMensagem = new CustomDialogMensagem((Activity) context, "Valor inválido, deve ser menor ou igual ao tempo diario!");
                        customDialogMensagem.show();
                        return false;
                    } else  {
                        if (Util.calcularMinutosDeHoras(configuracaoTempoSistema.getTempoContinuo()) != 0) {
                            if (Util.calcularMinutosDeHoras(tempoContinuo) > Util.calcularMinutosDeHoras(configuracaoTempoSistema.getTempoContinuo())) {
                                customDialogMensagem = new CustomDialogMensagem((Activity) context, "Valor inválido, deve ser menor ou igual ao tempo continuo do sistema!");
                                customDialogMensagem.show();
                                return false;
                            }
                        }
                        return true;
                    }
                } else {
                    if (Util.calcularMinutosDeHoras(configuracaoTempoSistema.getTempoContinuo()) != 0) {
                        if (Util.calcularMinutosDeHoras(tempoContinuo) > Util.calcularMinutosDeHoras(configuracaoTempoSistema.getTempoContinuo())) {
                            customDialogMensagem = new CustomDialogMensagem((Activity) context, "Valor inválido, deve ser menor ou igual ao tempo continuo do sistema!");
                            customDialogMensagem.show();
                            return false;
                        }
                    }
                    return true;
                }
            }
        }

    }

    @Override
    public int getItemCount() {
        return recyclerViewItems.size();
    }
}
