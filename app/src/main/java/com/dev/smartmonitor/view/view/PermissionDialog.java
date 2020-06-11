package com.dev.smartmonitor.view.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TimePicker;

import com.dev.smartmonitor.R;

public class PermissionDialog extends Dialog {

    private Activity activity;
    private Button buttonPermitir;
    private Button buttonCancelar;
    private DialogPermissionListener listener;

    public interface DialogPermissionListener {
        void applyReturnConfirmacao(boolean confirmacao);
    }

    public PermissionDialog(Activity activity, DialogPermissionListener listener) {
        super(activity);
        this.activity = activity;
        this.listener = listener;
        this.setCanceledOnTouchOutside(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_custon_dialog_time_picker);

        init();
    }



    private void init(){

    }

}
