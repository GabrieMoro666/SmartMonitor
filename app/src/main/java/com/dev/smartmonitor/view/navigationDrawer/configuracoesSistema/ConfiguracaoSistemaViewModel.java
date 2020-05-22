package com.dev.smartmonitor.view.navigationDrawer.configuracoesSistema;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ConfiguracaoSistemaViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ConfiguracaoSistemaViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}