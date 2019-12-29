package com.example.lenovo.mppdemo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lenovo.mppdemo.NeiCengBean;
import com.example.lenovo.mppdemo.R;
import com.example.lenovo.mppdemo.WaiCengBean;

import java.util.ArrayList;
import java.util.List;

public class WaiCengAdapter extends RecyclerView.Adapter {
    public ArrayList<WaiCengBean> wbeanList;
    private final Context context;
    private setOnClickListener ic;

    public WaiCengAdapter(ArrayList<WaiCengBean> wbeanList, Context context) {

        this.wbeanList = wbeanList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.waiceng_item, viewGroup,false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        final ViewHolder holder = (ViewHolder) viewHolder;
        holder.tv_name.setText(wbeanList.get(i).getTitle());
        holder.rlv.setLayoutManager(new LinearLayoutManager(context));

        List<NeiCengBean> ncbean = wbeanList.get(i).getNcbean();
        NeiCengAdapter adapter = new NeiCengAdapter(ncbean, context);
        holder.rlv.setAdapter(adapter);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ic.onListener(i,holder.img,holder.rlv);
            }
        });
    }

    @Override
    public int getItemCount() {
        return wbeanList.size();
    }

    public void setAll(ArrayList<WaiCengBean> wbeanLists) {
        if (wbeanLists != null && wbeanLists.size() > 0) {
            wbeanList.addAll(wbeanLists);
            notifyDataSetChanged();
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public TextView tv_name;
        public ImageView img;
        public RecyclerView rlv;

        public ViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.tv_name = (TextView) rootView.findViewById(R.id.tv_name);
            this.img = (ImageView) rootView.findViewById(R.id.img);
            this.rlv = (RecyclerView) rootView.findViewById(R.id.rlv);
        }

    }
    public interface setOnClickListener{
        void onListener(int position,ImageView img,RecyclerView neicengrlv);
    }
    public void onItemListener (setOnClickListener ic){
        this.ic = ic;
    }
}
