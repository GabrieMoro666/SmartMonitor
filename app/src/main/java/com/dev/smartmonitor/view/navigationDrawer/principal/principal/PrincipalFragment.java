package com.dev.smartmonitor.view.navigationDrawer.principal.principal;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.dev.smartmonitor.util.ContextSingleton;
import com.dev.smartmonitor.R;
import com.dev.smartmonitor.business.tabs.TabsAdapter;
import com.google.android.material.tabs.TabLayout;

public class PrincipalFragment extends Fragment {

    private PrincipalViewModel principalViewModel;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    TabsAdapter tabsAdapter;

    public PrincipalFragment(){
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_principal, container, false);

        tabLayout = (TabLayout) root.findViewById(R.id.tabLayout); //as tabs foram incluidas no xml appbarmain
        viewPager = (ViewPager) root.findViewById(R.id.viewPager);

        Context c = ContextSingleton.getContext();

        tabsAdapter = new TabsAdapter(getFragmentManager(), c);

        viewPager.setAdapter(tabsAdapter);
        tabLayout.setupWithViewPager(viewPager);

        return root;
    }
}
