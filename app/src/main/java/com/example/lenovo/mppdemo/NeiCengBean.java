package com.example.lenovo.mppdemo;

public class NeiCengBean {

     private String name;
     private String zhiwei;


    public NeiCengBean() {
    }
    public NeiCengBean(String name, String zhiwei) {
        this.name = name;
        this.zhiwei = zhiwei;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getZhiwei() {
        return zhiwei;
    }

    public void setZhiwei(String zhiwei) {
        this.zhiwei = zhiwei;
    }

    @Override
    public String toString() {
        return "NeiCengBean{" +
                "name='" + name + '\'' +
                ", zhiwei='" + zhiwei + '\'' +
                '}';
    }
}
