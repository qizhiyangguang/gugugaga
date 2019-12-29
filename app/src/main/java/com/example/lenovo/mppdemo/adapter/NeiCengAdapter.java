package com.example.lenovo.mppdemo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lenovo.mppdemo.NeiCengBean;
import com.example.lenovo.mppdemo.R;

import java.util.List;

public class NeiCengAdapter extends RecyclerView.Adapter {
    private final List<NeiCengBean> ncbean;
    private final Context context;

    public NeiCengAdapter(List<NeiCengBean> ncbean, Context context) {

        this.ncbean = ncbean;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.neiceng_item, viewGroup,false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ViewHolder holder = (ViewHolder) viewHolder;
        holder.tv_name.setText(ncbean.get(i).getName());
        holder.tv_zhiwei.setText(ncbean.get(i).getZhiwei());
    }

    @Override
    public int getItemCount() {
        return ncbean.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public View rootView;
        public TextView tv_name;
        public TextView tv_zhiwei;

        public ViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.tv_name = (TextView) rootView.findViewById(R.id.tv_name);
            this.tv_zhiwei = (TextView) rootView.findViewById(R.id.tv_zhiwei);
        }

    }
}
