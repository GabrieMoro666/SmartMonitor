package com.dev.smartmonitor.view.navigationDrawer.configuracoesAplicativos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ConfiguracaoAplicativosViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ConfiguracaoAplicativosViewModel() {
        mText = new MutableLiveData<>();
        //mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}