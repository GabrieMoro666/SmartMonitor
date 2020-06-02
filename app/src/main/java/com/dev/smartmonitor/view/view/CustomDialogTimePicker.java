package com.dev.smartmonitor.view.view;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

import com.dev.smartmonitor.R;

public class CustomDialogTimePicker extends Dialog implements View.OnClickListener {
    private Activity activity;
    private Button buttonOk;
    private TimePicker timePicker;
    private CustomDialogTimePickerListener listener;
    private int hora;
    private int minuto;

    public interface CustomDialogTimePickerListener {
        void applyReturnTimePicker(String horaMinuto);
    }

    public CustomDialogTimePicker(Activity activity, CustomDialogTimePickerListener listener, int hora, int minuto) {
        super(activity);
        this.activity = activity;
        this.listener = listener;
        this.hora = hora;
        this.minuto = minuto;
        this.setCanceledOnTouchOutside(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_custon_dialog_time_picker);

        init();
    }

    private void init(){
        buttonOk = (Button)findViewById(R.id.buttonOk);
        buttonOk.setOnClickListener(this);
        timePicker = (TimePicker)findViewById(R.id.timePickerTempo);

        timePicker.setIs24HourView(true);
        timePicker.setHour(hora);
        timePicker.setMinute(minuto);
    }

    @Override
    public void onClick(View v) {
        String horaMinuto, hora, minuto;

        hora = ("00"+ Integer.toString(timePicker.getHour()));
        hora = hora.substring(hora.length()-2,hora.length());

        minuto = ("00"+ Integer.toString(timePicker.getMinute()));
        minuto = minuto.substring(minuto.length()-2,minuto.length());

        horaMinuto = hora + ":" + minuto;

        listener.applyReturnTimePicker(horaMinuto);

        dismiss();
    }

}
