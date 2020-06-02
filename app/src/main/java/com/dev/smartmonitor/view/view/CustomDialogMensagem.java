package com.dev.smartmonitor.view.view;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dev.smartmonitor.R;

public class CustomDialogMensagem extends Dialog implements View.OnClickListener {

    private Activity activity;
    private Button buttonOk;
    private TextView textViewMensagem;
    private String mensagem;

    public CustomDialogMensagem(Activity activity, String mensagem) {
        super(activity);
        this.activity = activity;
        this.setCanceledOnTouchOutside(false);
        this.mensagem = mensagem;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dialog_mensagem);

        init();
    }

    private void init(){
        buttonOk = (Button)findViewById(R.id.buttonOk);
        buttonOk.setOnClickListener(this);
        textViewMensagem = (TextView) findViewById(R.id.textViewMensagem);

        textViewMensagem.setText(mensagem);
    }

    @Override
    public void onClick(View v) {
        dismiss();
    }
}
