package com.dev.smartmonitor.view.navigationDrawer.teste;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dev.smartmonitor.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TesteFragment extends Fragment {

    public TesteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_teste, container, false);
    }
}
