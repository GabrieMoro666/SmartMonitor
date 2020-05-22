package com.dev.smartmonitor.view.navigationDrawer.principal.tabsFragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dev.smartmonitor.R;
import com.dev.smartmonitor.view.view.CustomDialogMensagem;

/**
 * A simple {@link Fragment} subclass.
 */
public class GraficosFragment extends Fragment {

    public GraficosFragment(Context context) {
        // Required empty public constructor

        CustomDialogMensagem customDialogMensagem = new CustomDialogMensagem((Activity) context,"Gian gay");
        customDialogMensagem.show();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_graficos, container, false);
    }
}
