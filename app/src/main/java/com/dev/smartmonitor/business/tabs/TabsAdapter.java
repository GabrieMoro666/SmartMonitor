package com.dev.smartmonitor.business.tabs;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.dev.smartmonitor.view.navigationDrawer.principal.tabsFragments.TableFragment;
import com.dev.smartmonitor.view.navigationDrawer.principal.tabsFragments.ChartFragment;
import com.dev.smartmonitor.view.navigationDrawer.principal.tabsFragments.NotificationFragment;

public class TabsAdapter extends FragmentStatePagerAdapter {

    private static final String[] TAB_TITLES = new String[]{"Dados de uso", "Gráficos", "Notificações"};

    private Context context;

    public TabsAdapter(FragmentManager fragmentManager, Context context) {
        super(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment;

        switch (position){
            case 0:
                fragment = new TableFragment(context);
                break;
            case 1:
                fragment = new ChartFragment(context);
                break;
            case 2:
                fragment = new NotificationFragment(context);
                break;
            default:
                fragment = null;
                break;
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return this.TAB_TITLES.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TAB_TITLES[position];
    }

}