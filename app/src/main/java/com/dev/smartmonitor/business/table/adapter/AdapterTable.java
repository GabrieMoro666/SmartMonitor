package com.dev.smartmonitor.business.table.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.dev.smartmonitor.R;
import com.dev.smartmonitor.business.table.model.RowTable;

import java.util.List;

public class AdapterTable extends RecyclerView.Adapter{
    private List<RowTable> recyclerViewItems;

    public AdapterTable(List<RowTable> recyclerViewItems) {
        this.recyclerViewItems = recyclerViewItems;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View recycleViewItem;

        recycleViewItem = inflater.inflate(R.layout.layout_adapter_table_row, parent, false);
        return new RowTableHolder(recycleViewItem);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        RowTable rowTable = recyclerViewItems.get(position);

        RowTableHolder rowItemHolder = (RowTableHolder) holder;

        rowItemHolder.imageView.setImageDrawable(rowTable.getDrawable());
        rowItemHolder.textViewNomeAplicativo.setText(rowTable.getNomeAplicativo());
        rowItemHolder.textViewTempoUso.setText(rowTable.getTempoUso());
    }

    public class RowTableHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textViewNomeAplicativo;
        public TextView textViewTempoUso;

        public RowTableHolder(View itemView) {
            super(itemView);
            this.imageView = (ImageView)itemView.findViewById(R.id.imageViewRowTableIcon);
            this.textViewNomeAplicativo = (TextView)itemView.findViewById(R.id.textViewRowTableAplicativo);
            this.textViewTempoUso = (TextView)itemView.findViewById(R.id.textViewRowTableTempo);
        }
    }

    @Override
    public int getItemCount() {
        return recyclerViewItems.size();
    }
}
