package com.example.lenovo.mppdemo;

import java.util.List;

public class WaiCengBean {
    private String title;
    boolean isVisib;
    private List<NeiCengBean> ncbean;

    public boolean isVisib() {
        return isVisib;
    }

    public void setVisib(boolean visib) {
        isVisib = visib;
    }


    public WaiCengBean() {
    }

    public WaiCengBean(String title, List<NeiCengBean> ncbean) {
        this.title = title;
        this.ncbean = ncbean;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<NeiCengBean> getNcbean() {
        return ncbean;
    }

    public void setNcbean(List<NeiCengBean> ncbean) {
        this.ncbean = ncbean;
    }

    @Override
    public String toString() {
        return "WaiCengBean{" +
                "title='" + title + '\'' +
                ", ncbean=" + ncbean +
                '}';
    }
}
