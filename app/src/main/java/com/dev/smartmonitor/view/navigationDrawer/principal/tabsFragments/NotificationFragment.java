package com.dev.smartmonitor.view.navigationDrawer.principal.tabsFragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dev.smartmonitor.R;
import com.dev.smartmonitor.business.notification.adapter.AdapterNotification;
import com.dev.smartmonitor.business.notification.model.RowNotification;
import com.dev.smartmonitor.business.notification.notification.NotificationFactoryCreator;
import com.dev.smartmonitor.view.view.CustomDialogMensagem;

import java.util.List;

public class NotificationFragment extends Fragment {

    private Context context;
    private RecyclerView recyclerViewNotification;
    private RecyclerView.Adapter adapterNotification;
    private RecyclerView.LayoutManager layoutManagerAdapterNotification;
    private List<RowNotification> recyclerViewItems;

    public NotificationFragment(Context context) {
        this.context = context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notification, container, false);

        init(view);

        return view;
    }

    private void init(View view){
        NotificationFactoryCreator notificationFactory = new NotificationFactoryCreator();

        recyclerViewNotification = (RecyclerView)view.findViewById(R.id.recyclerViewNotification);

        layoutManagerAdapterNotification = new LinearLayoutManager(context);
        recyclerViewNotification.setLayoutManager(layoutManagerAdapterNotification);

        recyclerViewItems = notificationFactory.getFactry(context).buscarNotificacao();

        adapterNotification = new AdapterNotification(recyclerViewItems, context);
        recyclerViewNotification.setAdapter(adapterNotification);
        recyclerViewNotification.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
    }

}
