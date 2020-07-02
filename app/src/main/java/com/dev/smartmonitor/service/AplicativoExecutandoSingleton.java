package com.dev.smartmonitor.service;

public class AplicativoExecutandoSingleton {
    private static AplicativoExecutandoSingleton aplicativoExecutandoSingleton = null;
    private boolean execucaoOn = false;

    private AplicativoExecutandoSingleton(){}

    public static AplicativoExecutandoSingleton getAplicativoExecutando(){
        if (aplicativoExecutandoSingleton == null) {
            aplicativoExecutandoSingleton = new AplicativoExecutandoSingleton();
            aplicativoExecutandoSingleton.setExecucaoOn(false);
        }

        return aplicativoExecutandoSingleton;
    }

    public void setExecucaoOn(boolean execucaoOn) {
        this.execucaoOn = execucaoOn;
    }

    public boolean isExecucaoOn() {
        return execucaoOn;
    }
}
