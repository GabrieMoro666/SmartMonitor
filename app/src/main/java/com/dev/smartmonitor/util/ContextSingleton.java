package com.dev.smartmonitor.util;

import android.content.Context;

public class ContextSingleton {

    private static Context context = null;

    private ContextSingleton(){}

    public static Context getContext(){
        return context;
    }

    public static void setContext(Context context){
        ContextSingleton.context = context;
    }

}
