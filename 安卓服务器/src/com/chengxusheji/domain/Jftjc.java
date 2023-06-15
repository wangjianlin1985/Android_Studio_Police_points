package com.chengxusheji.domain;

import java.sql.Timestamp;
public class Jftjc {
    /*积分id*/
    private int id;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    /*积分条件*/
    private String jftj;
    public String getJftj() {
        return jftj;
    }
    public void setJftj(String jftj) {
        this.jftj = jftj;
    }

    /*分数*/
    private float fs;
    public float getFs() {
        return fs;
    }
    public void setFs(float fs) {
        this.fs = fs;
    }

    /*积分类型*/
    private int typeid;
    public int getTypeid() {
        return typeid;
    }
    public void setTypeid(int typeid) {
        this.typeid = typeid;
    }

    /*mtypeid*/
    private int mtypeid;
    public int getMtypeid() {
        return mtypeid;
    }
    public void setMtypeid(int mtypeid) {
        this.mtypeid = mtypeid;
    }

    /*备注*/
    private String bz;
    public String getBz() {
        return bz;
    }
    public void setBz(String bz) {
        this.bz = bz;
    }

}