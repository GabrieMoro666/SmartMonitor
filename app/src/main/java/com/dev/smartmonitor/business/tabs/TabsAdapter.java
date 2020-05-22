package com.dev.smartmonitor.business.tabs;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.dev.smartmonitor.R;
import com.dev.smartmonitor.view.navigationDrawer.principal.tabsFragments.TableFragment;
import com.dev.smartmonitor.view.navigationDrawer.principal.tabsFragments.GraficosFragment;
import com.dev.smartmonitor.view.navigationDrawer.principal.tabsFragments.NotificacoesFragment;

public class TabsAdapter extends FragmentStatePagerAdapter {

    private static final int[] TAB_TITLES = new int[]{R.string.aba_dados_uso, R.string.aba_graficos, R.string.aba_notificacoes};

    private Context context;

    public TabsAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                Toast.makeText(context, "CRIADO DADOS USO", Toast.LENGTH_SHORT).show();
                return new TableFragment(context);
            case 1:
                Toast.makeText(context, "CRIADO GRAFICOS", Toast.LENGTH_SHORT).show();
                return new GraficosFragment(context);
            case 2:
                Toast.makeText(context, "CRIADO NOTIFICACOES", Toast.LENGTH_SHORT).show();
                return new NotificacoesFragment(context);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return this.TAB_TITLES.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return context.getResources().getString(TAB_TITLES[position]);
    }

}