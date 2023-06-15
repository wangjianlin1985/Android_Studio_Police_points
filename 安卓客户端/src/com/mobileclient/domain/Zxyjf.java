package com.mobileclient.domain;

import java.io.Serializable;

public class Zxyjf implements Serializable {
    /*id*/
    private int id;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    /*部门id*/
    private int bid;
    public int getBid() {
        return bid;
    }
    public void setBid(int bid) {
        this.bid = bid;
    }

    /*部门名称*/
    private String bname;
    public String getBname() {
        return bname;
    }
    public void setBname(String bname) {
        this.bname = bname;
    }

    /*部门类型*/
    private int btypes;
    public int getBtypes() {
        return btypes;
    }
    public void setBtypes(int btypes) {
        this.btypes = btypes;
    }

    /*警员id*/
    private String sid;
    public String getSid() {
        return sid;
    }
    public void setSid(String sid) {
        this.sid = sid;
    }

    /*警员姓名*/
    private String sname;
    public String getSname() {
        return sname;
    }
    public void setSname(String sname) {
        this.sname = sname;
    }

    /*积分条件ID*/
    private int jid;
    public int getJid() {
        return jid;
    }
    public void setJid(int jid) {
        this.jid = jid;
    }

    /*积分条件*/
    private String jftj;
    public String getJftj() {
        return jftj;
    }
    public void setJftj(String jftj) {
        this.jftj = jftj;
    }

    /*积分季度*/
    private String jfjd;
    public String getJfjd() {
        return jfjd;
    }
    public void setJfjd(String jfjd) {
        this.jfjd = jfjd;
    }

    /*积分日期*/
    private java.sql.Timestamp jfdate;
    public java.sql.Timestamp getJfdate() {
        return jfdate;
    }
    public void setJfdate(java.sql.Timestamp jfdate) {
        this.jfdate = jfdate;
    }

    /*积分季度开始时间*/
    private java.sql.Timestamp jdsdate;
    public java.sql.Timestamp getJdsdate() {
        return jdsdate;
    }
    public void setJdsdate(java.sql.Timestamp jdsdate) {
        this.jdsdate = jdsdate;
    }

    /*分数*/
    private float fs;
    public float getFs() {
        return fs;
    }
    public void setFs(float fs) {
        this.fs = fs;
    }

    /*数量*/
    private int sl;
    public int getSl() {
        return sl;
    }
    public void setSl(int sl) {
        this.sl = sl;
    }

    /*刑事发案积分*/
    private float xsfajf;
    public float getXsfajf() {
        return xsfajf;
    }
    public void setXsfajf(float xsfajf) {
        this.xsfajf = xsfajf;
    }

    /*号码走访积分*/
    private float hmzfjf;
    public float getHmzfjf() {
        return hmzfjf;
    }
    public void setHmzfjf(float hmzfjf) {
        this.hmzfjf = hmzfjf;
    }

    /*测评反馈积分*/
    private float cpfkjf;
    public float getCpfkjf() {
        return cpfkjf;
    }
    public void setCpfkjf(float cpfkjf) {
        this.cpfkjf = cpfkjf;
    }

    /*单位重视积分*/
    private float dwzsjf;
    public float getDwzsjf() {
        return dwzsjf;
    }
    public void setDwzsjf(float dwzsjf) {
        this.dwzsjf = dwzsjf;
    }

}