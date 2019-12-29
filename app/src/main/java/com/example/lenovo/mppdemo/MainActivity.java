package com.example.lenovo.mppdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.lenovo.mppdemo.adapter.WaiCengAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRlv;
    private ArrayList<WaiCengBean> wbeanList;
    private WaiCengAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initListener();
    }

    private void initListener() {
        adapter.onItemListener(new WaiCengAdapter.setOnClickListener() {
            @Override
            public void onListener(int position, ImageView img, RecyclerView neicengrlv) {
                boolean isVisib = adapter.wbeanList.get(position).isVisib;
                if (isVisib == false) {
                    img.setImageResource(R.mipmap.jiantou);
                    neicengrlv.setVisibility(View.VISIBLE);
                    adapter.wbeanList.get(position).setVisib(true);
                }
                if  (isVisib == true){
                    img.setImageResource(R.mipmap.anniu);
                    neicengrlv.setVisibility(View.GONE);
                    adapter.wbeanList.get(position).setVisib(false);
                }
            }
        });
    }

    private void initData() {
        wbeanList = new ArrayList<>();
        ArrayList<NeiCengBean> nbeanList = new ArrayList<>();
        WaiCengBean waiCengBean = new WaiCengBean();
        NeiCengBean neiCengBean = new NeiCengBean();
        for (int i = 0; i < 10; i++) {
            waiCengBean.setTitle("人事部" + i);
            neiCengBean.setName("哼哼哼" + i);
            neiCengBean.setZhiwei("经理总监");
            nbeanList.add(neiCengBean);
            waiCengBean.setNcbean(nbeanList);
            wbeanList.add(waiCengBean);
        }
        Log.e("TAG", "bean:" + wbeanList.size());
        adapter.setAll(wbeanList);
    }

    private void initView() {
        mRlv = (RecyclerView) findViewById(R.id.rlv);
        mRlv.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<WaiCengBean> waiCengBeans = new ArrayList<>();
        adapter = new WaiCengAdapter(waiCengBeans, this);
        mRlv.setAdapter(adapter);
    }

}
